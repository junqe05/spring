package com.example.demo.pds;

public class UpfileVO {
	private int fileNum;
	private int num;
	private String filename;
	private long filesize;

	public UpfileVO(int num, String filename, long filesize) {
		super();
		this.num = num;
		this.filename = filename;
		this.filesize = filesize;
	}
	
	public UpfileVO(int fileNum, int num, String filename, long filesize) {
		super();
		this.fileNum = fileNum;
		this.num = num;
		this.filename = filename;
		this.filesize = filesize;
	}

	

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}


	
	

}