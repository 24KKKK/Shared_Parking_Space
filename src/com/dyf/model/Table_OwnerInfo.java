package com.dyf.model;

public class Table_OwnerInfo {

	private String parkadminid;  //管理员id编号
	private String parklotname;  //停车场名称
	private String ownername;  //购买者姓名
	private String ownerphone;  //购买者电话
	private int ownergender;  //购买者性别，1男2女
	private String owneridnumber;  //购买者身份证号
	private String owneraddress;  //购买者地址
	
	@Override
	public String toString() {
		return "Table_OwnerInfo [parkadminid=" + parkadminid + ", parklotname=" + parklotname + ", ownername="
				+ ownername + ", ownerphone=" + ownerphone + ", ownergender=" + ownergender + ", owneridnumber="
				+ owneridnumber + ", owneraddress=" + owneraddress + "]";
	}
	/**
	 * @param parkadminid
	 * @param parklotname
	 * @param ownername
	 * @param ownerphone
	 * @param ownergender
	 * @param owneridnumber
	 * @param owneraddress
	 */
	public Table_OwnerInfo(String parkadminid, String parklotname, String ownername, String ownerphone, int ownergender,
			String owneridnumber, String owneraddress) {
		super();
		this.parkadminid = parkadminid;
		this.parklotname = parklotname;
		this.ownername = ownername;
		this.ownerphone = ownerphone;
		this.ownergender = ownergender;
		this.owneridnumber = owneridnumber;
		this.owneraddress = owneraddress;
	}
	public String getParkadminid() {
		return parkadminid;
	}
	public void setParkadminid(String parkadminid) {
		this.parkadminid = parkadminid;
	}
	public String getParklotname() {
		return parklotname;
	}
	public void setParklotname(String parklotname) {
		this.parklotname = parklotname;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getOwnerphone() {
		return ownerphone;
	}
	public void setOwnerphone(String ownerphone) {
		this.ownerphone = ownerphone;
	}
	public int getOwnergender() {
		return ownergender;
	}
	public void setOwnergender(int ownergender) {
		this.ownergender = ownergender;
	}
	public String getOwneridnumber() {
		return owneridnumber;
	}
	public void setOwneridnumber(String owneridnumber) {
		this.owneridnumber = owneridnumber;
	}
	public String getOwneraddress() {
		return owneraddress;
	}
	public void setOwneraddress(String owneraddress) {
		this.owneraddress = owneraddress;
	}
	
}
