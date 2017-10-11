package com.dyf.model;

import java.util.Date;

/**
 * 描述：系统管理员添加的停车场管理员基本信息表Table_ParklotAdminInfo
 * @author diy
 * @time 2017-10-10 11:13
 */
public class Table_ParklotAdminInfo {
	
	private String parklotAdminId;  		//停车场管理员id
	private String parklotAdminPhone;  		//停车场管理员电话phone
	private int parklotAdminAge;  			//停车场管理员年龄age
	private String parklotAdminIdnumber;    //停车场管理员身份证号idnumber
	private String parklotAdminName;		//停车场管理员姓名name
	private String parklotAdminLoginId;		//停车场管理员登录账号loginid
	private String parklotAdminLoginPass;	//停车场管理员登录密码loginpass
	private String parklotAdminCreatedTime;	//停车场管理员的创建时间CreatedTime
	
	
	/**
	 * @param parklotAdminId
	 * @param parklotAdminPhone
	 * @param parklotAdminAge
	 * @param parklotAdminIdnumber
	 * @param parklotAdminName
	 * @param parklotAdminLoginId
	 * @param parklotAdminLoginPass
	 * @param parklotAdminCreatedTime
	 */
	public Table_ParklotAdminInfo(String parklotAdminId, String parklotAdminPhone, int parklotAdminAge,
			String parklotAdminIdnumber, String parklotAdminName, String parklotAdminLoginId,
			String parklotAdminLoginPass, String parklotAdminCreatedTime) {
		super();
		this.parklotAdminId = parklotAdminId;
		this.parklotAdminPhone = parklotAdminPhone;
		this.parklotAdminAge = parklotAdminAge;
		this.parklotAdminIdnumber = parklotAdminIdnumber;
		this.parklotAdminName = parklotAdminName;
		this.parklotAdminLoginId = parklotAdminLoginId;
		this.parklotAdminLoginPass = parklotAdminLoginPass;
		this.parklotAdminCreatedTime=parklotAdminCreatedTime;
	}

	public String getParklotAdminId() {
		return parklotAdminId;
	}
	public void setParklotAdminId(String parklotAdminId) {
		this.parklotAdminId = parklotAdminId;
	}
	public String getParklotAdminPhone() {
		return parklotAdminPhone;
	}
	public void setParklotAdminPhone(String parklotAdminPhone) {
		this.parklotAdminPhone = parklotAdminPhone;
	}
	public int getParklotAdminAge() {
		return parklotAdminAge;
	}
	public void setParklotAdminAge(int parklotAdminAge) {
		this.parklotAdminAge = parklotAdminAge;
	}
	public String getParklotAdminIdnumber() {
		return parklotAdminIdnumber;
	}
	public void setParklotAdminIdnumber(String parklotAdminIdnumber) {
		this.parklotAdminIdnumber = parklotAdminIdnumber;
	}
	public String getParklotAdminName() {
		return parklotAdminName;
	}
	public void setParklotAdminName(String parklotAdminName) {
		this.parklotAdminName = parklotAdminName;
	}
	public String getParklotAdminLoginId() {
		return parklotAdminLoginId;
	}
	public void setParklotAdminLoginId(String parklotAdminLoginId) {
		this.parklotAdminLoginId = parklotAdminLoginId;
	}
	public String getParklotAdminLoginPass() {
		return parklotAdminLoginPass;
	}
	public void setParklotAdminLoginPass(String parklotAdminLoginPass) {
		this.parklotAdminLoginPass = parklotAdminLoginPass;
	}
	public String getParklotAdminCreatedTime() {
		return parklotAdminCreatedTime;
	}
	public void setParklotAdminCreatedTime(String parklotAdminCreatedTime) {
		this.parklotAdminCreatedTime = parklotAdminCreatedTime;
	}

}
