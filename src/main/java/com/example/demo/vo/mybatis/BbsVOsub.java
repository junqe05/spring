package com.example.demo.vo.mybatis;

public class BbsVOsub {
	private int num;
	private String title;
	private String writer;
	private String wdate;
	private String contents;
	private int hit;
	private int lvl;
	private int mgr;
	private int topid;
	
	public BbsVOsub() {}


	public BbsVOsub(int num, String title, String writer, String wdate, int hit, int mgr, int topid) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.wdate = wdate;
		this.hit = hit;
		this.mgr = mgr;
		this.topid = topid;
	}

	public BbsVOsub(String title, String writer, String contents, int mgr, int topid) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.mgr = mgr;
		this.topid = topid;
	}



	public BbsVOsub(int num, String title, String writer, String wdate, String contents, int hit, int mgr, int topid) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.wdate = wdate;
		this.contents = contents;
		this.hit = hit;
		this.mgr = mgr;
		this.topid = topid;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public int getTopid() {
		return topid;
	}

	public void setTopid(int topid) {
		this.topid = topid;
	}
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	

}
