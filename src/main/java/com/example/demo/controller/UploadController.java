package com.example.demo.controller;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pds.PDSDAO;

@Controller
@RequestMapping
public class UploadController 
{
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	PDSDAO psd;

	@GetMapping("/upload")
	public String getForm() {
		return "upload_form";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("files")MultipartFile[] mfiles,
			HttpServletRequest request,
			@RequestParam("author") String author) {
		ServletContext context = request.getServletContext();
		// upload 폴어데 대한 정대경로를 구한다. upload/안에 파일을 저장하기 위함
		String savePath = context.getRealPath("/WEB-INF/upload");
		int ulnum = psd.upload(author);
		try {// 파일정보 배열을 순회하면서 파일을 한개씩 upload/ 안으로 옮긴다
			for(int i=0;i<mfiles.length;i++) {
				mfiles[i].transferTo(
				  new File(savePath+"/"+mfiles[i].getOriginalFilename()));
				//getOriginalFilename() : 파일경로를 제외한 순수한 파일명만 추출한다.
				String name = mfiles[i].getOriginalFilename();
				boolean insert = psd.upfile(mfiles[i], ulnum);
				System.out.println(insert?name+"저장 성공":name+"저장 실패");
				
				/* MultipartFile 주요 메소드
				String cType = mfiles[i].getContentType();
				String pName = mfiles[i].getName();
				Resource res = mfiles[i].getResource();
				long fSize = mfiles[i].getSize(); // 파일의 크기
				boolean empty = mfiles[i].isEmpty();
				*/
			}
			String msg = String.format("파일(%d)저장성공(작성자:%s)", mfiles.length,author);
			return msg; //@ResponseBody이기 때문에 문자열을 바로 보냄
		} catch (Exception e) {
			e.printStackTrace();
			return "파일 저장 실패:";
		}
	}
	
	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> download(
			HttpServletRequest request,
			@PathVariable String filename){
		Resource resource = resourceLoader.getResource("WEB-INF/upload/"+filename);
		// getResource() 파일 이름으로 리소스 객체 생성 
		System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            //resource.getFile().getAbsolutePath() : 절대 경로
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) { // 브라우저가 열수 없는 파일일 경우 파일을 열지 않고 다운로드하도록 설정
        	System.out.println("다운로드 실행");
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok() //or() : http body builder
                .contentType(MediaType.parseMediaType(contentType)) // contentType 지정. 다운로드 되도록 설정
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"") 
                //http header(메타 데이터)부분 설정. 브라우저에 대한 명령 첨부 데이터 셋팅
                .body(resource);//resource(파일 데이터) 전송
	}
}