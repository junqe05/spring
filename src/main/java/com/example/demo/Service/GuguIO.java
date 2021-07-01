 package com.example.demo.Service;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class GuguIO {
	
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
