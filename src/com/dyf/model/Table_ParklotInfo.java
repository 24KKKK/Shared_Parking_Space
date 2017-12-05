package com.dyf.model;

/**
 * 描述：系统管理员添加的停车场基本信息表Table_ParklotInfo
 * @author diy
 * @time 2017-12-5 14:55:33
 */
public class Table_ParklotInfo {
	
	private String parklotName;				// 停车场姓名name，主键
	private int parklotAmount;				// 停车场车位数量
	private String parklotLng;				// 停车场位置精度
	private String parklotLat;				// 停车场位置纬度
	private String parklotDescription;		// 停车场描述
	private String parklotAdminId;			// 停车场管理员工号（外键）
	private String parklotCreatedTime;		// 停车场的创建时间CreatedTime
	
	/**
	 * @param parklotName
	 * @param parklotAmount
	 * @param parklotLng
	 * @param parklotLat
	 * @param parklotDescription
	 * @param parklotAdminId
	 * @param parklotCreatedTime
	 */
	public Table_ParklotInfo(String parklotName, int parklotAmount, String parklotLng, String parklotLat,
			String parklotDescription, String parklotAdminId, String parklotCreatedTime) {
		super();
		this.parklotName = parklotName;
		this.parklotAmount = parklotAmount;
		this.parklotLng = parklotLng;
		this.parklotLat = parklotLat;
		this.parklotDescription = parklotDescription;
		this.parklotAdminId = parklotAdminId;
		this.parklotCreatedTime = parklotCreatedTime;
	}

	public String getParklotName() {
		return parklotName;
	}

	public void setParklotName(String parklotName) {
		this.parklotName = parklotName;
	}

	public int getParklotAmount() {
		return parklotAmount;
	}

	public void setParklotAmount(int parklotAmount) {
		this.parklotAmount = parklotAmount;
	}

	public String getParklotLng() {
		return parklotLng;
	}

	public void setParklotLng(String parklotLng) {
		this.parklotLng = parklotLng;
	}

	public String getParklotLat() {
		return parklotLat;
	}

	public void setParklotLat(String parklotLat) {
		this.parklotLat = parklotLat;
	}

	public String getParklotDescription() {
		return parklotDescription;
	}

	public void setParklotDescription(String parklotDescription) {
		this.parklotDescription = parklotDescription;
	}

	public String getParklotAdminId() {
		return parklotAdminId;
	}

	public void setParklotAdminId(String parklotAdminId) {
		this.parklotAdminId = parklotAdminId;
	}

	public String getParklotCreatedTime() {
		return parklotCreatedTime;
	}

	public void setParklotCreatedTime(String parklotCreatedTime) {
		this.parklotCreatedTime = parklotCreatedTime;
	}
	
}
