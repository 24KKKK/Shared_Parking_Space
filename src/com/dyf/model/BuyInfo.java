package com.dyf.model;

public class BuyInfo {

	private String ownername; // 购买者姓名
	private int ownergender; // 性别
	private String ownerphone; // 电话
	private int buyparkid; // 购买的车位号
	private int buystartparktime; // 停车的开始时间点
	private int buyendparktime; // 停车的结束时间点
	private String buystartparkdate; // 开始停车日期
	private String buyendparkdate; // 结束停车日期
	private String owneridnumber; // 身份证号
	private String owneraddress; // 购买者住址
	private String parkadminid; // 管理员id编号
	private String parklotname; // 停车场名称
	private double buymoney; // 购买停车位的应付金额
	private String buycreatedtime; // 购买的创建时间
	
	/**
	 * @param ownername
	 * @param ownergender
	 * @param ownerphone
	 * @param buyparkid
	 * @param buystartparktime
	 * @param buyendparktime
	 * @param buystartparkdate
	 * @param buyendparkdate
	 * @param owneridnumber
	 * @param owneraddress
	 * @param parkadminid
	 * @param parklotname
	 * @param buymoney
	 * @param buycreatedtime
	 */
	public BuyInfo(String ownername, int ownergender, String ownerphone, int buyparkid, int buystartparktime,
			int buyendparktime, String buystartparkdate, String buyendparkdate, String owneridnumber,
			String owneraddress, String parkadminid, String parklotname, double buymoney, String buycreatedtime) {
		super();
		this.ownername = ownername;
		this.ownergender = ownergender;
		this.ownerphone = ownerphone;
		this.buyparkid = buyparkid;
		this.buystartparktime = buystartparktime;
		this.buyendparktime = buyendparktime;
		this.buystartparkdate = buystartparkdate;
		this.buyendparkdate = buyendparkdate;
		this.owneridnumber = owneridnumber;
		this.owneraddress = owneraddress;
		this.parkadminid = parkadminid;
		this.parklotname = parklotname;
		this.buymoney = buymoney;
		this.buycreatedtime = buycreatedtime;
	}
	@Override
	public String toString() {
		return "BuyInfo [ownername=" + ownername + ", ownergender=" + ownergender + ", ownerphone=" + ownerphone
				+ ", buyparkid=" + buyparkid + ", buystartparktime=" + buystartparktime + ", buyendparktime="
				+ buyendparktime + ", buystartparkdate=" + buystartparkdate + ", buyendparkdate=" + buyendparkdate
				+ ", owneridnumber=" + owneridnumber + ", owneraddress=" + owneraddress + ", parkadminid=" + parkadminid
				+ ", parklotname=" + parklotname + ", buymoney=" + buymoney + ", buycreatedtime=" + buycreatedtime
				+ "]";
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public int getOwnergender() {
		return ownergender;
	}
	public void setOwnergender(int ownergender) {
		this.ownergender = ownergender;
	}
	public String getOwnerphone() {
		return ownerphone;
	}
	public void setOwnerphone(String ownerphone) {
		this.ownerphone = ownerphone;
	}
	public int getBuyparkid() {
		return buyparkid;
	}
	public void setBuyparkid(int buyparkid) {
		this.buyparkid = buyparkid;
	}
	public int getBuystartparktime() {
		return buystartparktime;
	}
	public void setBuystartparktime(int buystartparktime) {
		this.buystartparktime = buystartparktime;
	}
	public int getBuyendparktime() {
		return buyendparktime;
	}
	public void setBuyendparktime(int buyendparktime) {
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
	public double getBuymoney() {
		return buymoney;
	}
	public void setBuymoney(double buymoney) {
		this.buymoney = buymoney;
	}
	public String getBuycreatedtime() {
		return buycreatedtime;
	}
	public void setBuycreatedtime(String buycreatedtime) {
		this.buycreatedtime = buycreatedtime;
	}
	

}
