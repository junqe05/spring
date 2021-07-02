package com.example.demo.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pds.UploadDAO;
import com.example.demo.pds.UploadVO;

@Service
public class UploadSvc {
	@Autowired
	UploadDAO dao;
	
	public boolean add(MultipartFile[] mfiles, String author, String savePath) throws IllegalStateException, IOException {
		
		int ulnum = dao.upload(author);
		boolean insert = false;
		for(int i=0;i<mfiles.length;i++) {
			mfiles[i].transferTo(
			  new File(savePath+"/"+mfiles[i].getOriginalFilename()));
			//getOriginalFilename() : 파일경로를 제외한 순수한 파일명만 추출한다.
			String name = mfiles[i].getOriginalFilename();
			insert = dao.upfile(mfiles[i], ulnum);
			System.out.println(insert?name+"저장 성공":name+"저장 실패");
			if(!insert) {
				System.out.println(name+"저장 실패");
				throw new RuntimeException( "파일 저장 오류");
			}
		}	
		return insert?true:false;
	}
	
	public List<UploadVO> list(){
		List<UploadVO> list = dao.list();

		

		return list;
	}
}
/* MultipartFile 주요 메소드
String cType = mfiles[i].getContentType();
String pName = mfiles[i].getName();
Resource res = mfiles[i].getResource();
long fSize = mfiles[i].getSize(); // 파일의 크기
boolean empty = mfiles[i].isEmpty();
*/