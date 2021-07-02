package com.example.demo.pds;

import java.sql.Date;

public class UploadVO {
	private int num;
	private String writer;
	private String subject;
	private Date date;
	private String content;
	private UpfileVO[] files;
	
	
	public UploadVO(String writer, String subject ,String content) {
		super();
		this.writer = writer;
		this.subject = subject;
		this.content = content;
	}
	
	public UploadVO(int num, String writer, Date date, String subject ,String content) {
		super();
		this.num = num;
		this.writer = writer;
		this.subject = subject;
		this.date = date;
		this.content = content;
	}

	public UploadVO() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date  date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	public UpfileVO[] getFiles() {
		return files;
	}

	public void setFiles(UpfileVO[] files) {
		this.files = files;
	}


	
	

}
