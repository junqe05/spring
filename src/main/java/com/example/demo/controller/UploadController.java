package com.example.demo.controller;

import java.io.*;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.UploadSvc;
import com.example.demo.pds.UploadDAO;
import com.example.demo.pds.UploadVO;

@Controller
@RequestMapping
public class UploadController 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	UploadSvc svc;
	
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
		try {// 파일정보 배열을 순회하면서 파일을 한개씩 upload/ 안으로 옮긴다
			boolean add = svc.add(mfiles, author, savePath);
			String msg = String.format("파일(%d)저장성공(작성자:%s)", mfiles.length,author);
			return add?msg:"파일 저장 실패";
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
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		List<UploadVO> list = svc.list();
		model.addAttribute("list", list);
		return "/upload/list";
	}
	
}