package com.dyf.model;

public class CanBuyInfo {
	
	private int parkId;  //车位号
	private String parkTime;  //可以停车的时间
	private String adminId;  //管理员编号id
	private String parklotName;  //停车场名称
	
	
	@Override
	public String toString() {
		return "CanBuyInfo [parkId=" + parkId + ", parkTime=" + parkTime + ", adminId=" + adminId + ", parklotName="
				+ parklotName + "]";
	}
	
	/**
	 * @param parkId
	 * @param parkTime
	 * @param adminId
	 * @param parklotName
	 */
	public CanBuyInfo(int parkId, String parkTime, String adminId, String parklotName) {
		super();
		this.parkId = parkId;
		this.parkTime = parkTime;
		this.adminId = adminId;
		this.parklotName = parklotName;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getParkTime() {
		return parkTime;
	}
	public void setParkTime(String parkTime) {
		this.parkTime = parkTime;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getParklotName() {
		return parklotName;
	}
	public void setParklotName(String parklotName) {
		this.parklotName = parklotName;
	}
	
	
}
