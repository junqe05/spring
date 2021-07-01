package com.example.demo.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service //객체 생성
public class GuguSvc {

	
	public List<String> getGugu(int dan) {
		   List<String> list = new ArrayList<>();
		   for(int i=1;i<=9;i++) {
			   list.add( String.format("%d * %d = %d",dan, i, dan*i ));
		   }
		   return list;
	}
	
	public boolean gugusave(List<String> list) {
		String dir = "C:/eclipse-workspace/demo/text/gugu.txt";
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter(dir);
			pw = new PrintWriter(fw);
			for(String l : list) {
				pw.println(l);
			}
			
			pw.close();
			return true;
		} catch (IOException e) {
			System.out.println("정보 저장에 실패했습니다.");
		} finally {
			pw.close();
		}
		return false;
	}

}
