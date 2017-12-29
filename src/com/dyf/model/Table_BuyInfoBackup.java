package com.dyf.model;

public class Table_BuyInfoBackup {

	private String parkAdminId; // 管理员编号
	private String parklotName;// 停车场名称
	private String ownername;// 购买者姓名
	private String ownerphone;// 购买者电话
	private int ownergender = 0;// 购买者性别，1男2女
	private String owneridnumber;// 购买者身份证号
	private String owneraddress;// 购买者住址
	private int buyparkid = 0;// 购买的车位号
	private String buystartparktime;// 开始停车时间
	private String buyendparktime;// 结束停车时间
	private String buystartparkdate;// 开始停车日期
	private String buyendparkdate;// 结束停车日期
	private double buymoney = 0.0;// 应付金额
	private String buyCreatedTime;// 购买创建时间
	private String deleteTime;// 购买删除时间

	@Override
	public String toString() {
		return "Table_BuyInfoBackup [parkAdminId=" + parkAdminId + ", parklotName=" + parklotName + ", ownername="
				+ ownername + ", ownerphone=" + ownerphone + ", ownergender=" + ownergender + ", owneridnumber="
				+ owneridnumber + ", owneraddress=" + owneraddress + ", buyparkid=" + buyparkid + ", buystartparktime="
				+ buystartparktime + ", buyendparktime=" + buyendparktime + ", buystartparkdate=" + buystartparkdate
				+ ", buyendparkdate=" + buyendparkdate + ", buymoney=" + buymoney + ", buyCreatedTime=" + buyCreatedTime
				+ ", deleteTime=" + deleteTime + "]";
	}

	/**
	 * @param parkAdminId
	 * @param parklotName
	 * @param ownername
	 * @param ownerphone
	 * @param ownergender
	 * @param owneridnumber
	 * @param owneraddress
	 * @param buyparkid
	 * @param buystartparktime
	 * @param buyendparktime
	 * @param buystartparkdate
	 * @param buyendparkdate
	 * @param buymoney
	 * @param buyCreatedTime
	 * @param deleteTime
	 */
	public Table_BuyInfoBackup(String parkAdminId, String parklotName, String ownername, String ownerphone,
			int ownergender, String owneridnumber, String owneraddress, int buyparkid, String buystartparktime,
			String buyendparktime, String buystartparkdate, String buyendparkdate, double buymoney,
			String buyCreatedTime, String deleteTime) {
		super();
		this.parkAdminId = parkAdminId;
		this.parklotName = parklotName;
		this.ownername = ownername;
		this.ownerphone = ownerphone;
		this.ownergender = ownergender;
		this.owneridnumber = owneridnumber;
		this.owneraddress = owneraddress;
		this.buyparkid = buyparkid;
		this.buystartparktime = buystartparktime;
		this.buyendparktime = buyendparktime;
		this.buystartparkdate = buystartparkdate;
		this.buyendparkdate = buyendparkdate;
		this.buymoney = buymoney;
		this.buyCreatedTime = buyCreatedTime;
		this.deleteTime = deleteTime;
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

	public int getBuyparkid() {
		return buyparkid;
	}

	public void setBuyparkid(int buyparkid) {
		this.buyparkid = buyparkid;
	}

	public String getBuystartparktime() {
		return buystartparktime;
	}

	public void setBuystartparktime(String buystartparktime) {
		this.buystartparktime = buystartparktime;
	}

	public String getBuyendparktime() {
		return buyendparktime;
	}

	public void setBuyendparktime(String buyendparktime) {
		this.buyendparktime = buyendparktime;
	}

	public String getBuystartparkdate() {
		return buystartparkdate;
	}

	public void setBuystartparkdate(String buystartparkdate) {
		this.buystartparkdate = buystartparkdate;
	}

	public String getBuyendparkdate() {
		return buyendparkdate;
	}

	public void setBuyendparkdate(String buyendparkdate) {
		this.buyendparkdate = buyendparkdate;
	}

	public double getBuymoney() {
		return buymoney;
	}

	public void setBuymoney(double buymoney) {
		this.buymoney = buymoney;
	}

	public String getBuyCreatedTime() {
		return buyCreatedTime;
	}

	public void setBuyCreatedTime(String buyCreatedTime) {
		this.buyCreatedTime = buyCreatedTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

}
