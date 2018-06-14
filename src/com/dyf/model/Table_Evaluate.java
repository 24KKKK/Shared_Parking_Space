package com.dyf.model;

public class Table_Evaluate {
	
	private String openid;
	private String parklotname;
	private int score;
	private String content;
	private String createtime;
	
	/**
	 * @param openid
	 * @param parklotname
	 * @param score
	 * @param content
	 * @param createtime
	 */
	public Table_Evaluate(String openid, String parklotname, int score, String content, String createtime) {
		super();
		this.openid = openid;
		this.parklotname = parklotname;
		this.score = score;
		this.content = content;
		this.createtime = createtime;
	}
	
	/**
	 * @param score
	 * @param content
	 * @param createtime
	 */
	public Table_Evaluate(int score, String content, String createtime) {
		super();
		this.score = score;
		this.content = content;
		this.createtime = createtime;
	}



	public String getOpenid() {
		return openid;
	}



	public void setOpenid(String openid) {
		this.openid = openid;
	}



	public String getParklotname() {
		return parklotname;
	}



	public void setParklotname(String parklotname) {
		this.parklotname = parklotname;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getCreatetime() {
		return createtime;
	}



	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	

}
