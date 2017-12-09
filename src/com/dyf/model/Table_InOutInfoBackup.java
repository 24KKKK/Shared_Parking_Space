package com.dyf.model;

public class Table_InOutInfoBackup {
	
	private String carId;
	private String inDateTime;
	private String outDateTime;
	private int parkId;
	private String parkAdminId;
	private String parklotName;
	
	
	
	@Override
	public String toString() {
		return "Table_InOutInfoBackup [车牌号=" + carId + ", 进入时间=" + inDateTime + ", 离开时间=" + outDateTime
				+ ", 车位号=" + parkId + ", 管理员id=" + parkAdminId + ", 停车场名称=" + parklotName + "]";
	}
	/**
	 * @param carId
	 * @param inDateTime
	 * @param outDateTime
	 * @param parkId
	 * @param parkAdminId
	 * @param parklotName
	 */
	public Table_InOutInfoBackup(String carId, String inDateTime, String outDateTime, int parkId, String parkAdminId,
			String parklotName) {
		super();
		this.carId = carId;
		this.inDateTime = inDateTime;
		this.outDateTime = outDateTime;
		this.parkId = parkId;
		this.parkAdminId = parkAdminId;
		this.parklotName = parklotName;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getInDateTime() {
		return inDateTime;
	}
	public void setInDateTime(String inDateTime) {
		this.inDateTime = inDateTime;
	}
	public String getOutDateTime() {
		return outDateTime;
	}
	public void setOutDateTime(String outDateTime) {
		this.outDateTime = outDateTime;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getParkAdminId() {
		return parkAdminId;
	}
	public void setParkAdminId(String parkAdminId) {
		this.parkAdminId = parkAdminId;
	}
	public String getParklotName() {
		return parklotName;
	}
	public void setParklotName(String parklotName) {
		this.parklotName = parklotName;
	}
	
	


}
