package com.dyf.model;

public class Table_BuyInfo {

	private String parkadminid; // 管理员id编号
	private String parklotname; // 停车场名称
	private String buyidnumber;// 购买者身份证号
	private int buyparkid;// 购买者购买的车位号
	private String buystartparktime;// 购买的停车开始时间
	private String buyendparktime;// 购买的结束停车时间
	private String buystartparkdate;// 购买的停车开始日期
	private String buyendparkdate;// 购买的停车结束日期
	private double buymoney;// 应付金额
	private String buycreatedtime;// 购买日期
	
	@Override
	public String toString() {
		return "Table_BuyInfo [parkadminid=" + parkadminid + ", parklotname=" + parklotname + ", buyidnumber="
				+ buyidnumber + ", buyparkid=" + buyparkid + ", buystartparktime=" + buystartparktime
				+ ", buyendparktime=" + buyendparktime + ", buystartparkdate=" + buystartparkdate + ", buyendparkdate="
				+ buyendparkdate + ", buymoney=" + buymoney + ", buycreatedtime=" + buycreatedtime + "]";
	}
	/**
	 * @param parkadminid
	 * @param parklotname
	 * @param buyidnumber
	 * @param buyparkid
	 * @param buystartparktime
	 * @param buyendparktime
	 * @param buystartparkdate
	 * @param buyendparkdate
	 * @param buymoney
	 * @param buycreatedtime
	 */
	public Table_BuyInfo(String parkadminid, String parklotname, String buyidnumber, int buyparkid,
			String buystartparktime, String buyendparktime, String buystartparkdate, String buyendparkdate,
			double buymoney, String buycreatedtime) {
		super();
		this.parkadminid = parkadminid;
		this.parklotname = parklotname;
		this.buyidnumber = buyidnumber;
		this.buyparkid = buyparkid;
		this.buystartparktime = buystartparktime;
		this.buyendparktime = buyendparktime;
		this.buystartparkdate = buystartparkdate;
		this.buyendparkdate = buyendparkdate;
		this.buymoney = buymoney;
		this.buycreatedtime = buycreatedtime;
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
	public String getBuyidnumber() {
		return buyidnumber;
	}
	public void setBuyidnumber(String buyidnumber) {
		this.buyidnumber = buyidnumber;
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
	public String getBuycreatedtime() {
		return buycreatedtime;
	}
	public void setBuycreatedtime(String buycreatedtime) {
		this.buycreatedtime = buycreatedtime;
	}
	
	
}
