package com.dyf.model;

/**
 * 停车场进出信息登记表
 * @time 2017-12-5 20:59:50
 * @author diy
 */
public class Table_InOutInfo {
	
	private String carid;			//来车的车牌号（主键）冀AR4305
	private String indatetime;		//车进场时间	2017-12-5 21:02:32
	private String outdatetime;		//车出场时间	2017-12-5 21:03:00
	private int parkid;				//停车车位号	13
	private String parkadminid;		//记录员(外键)	停车场管理员
	private String parklotname;		//停车场名字(外键)		盛世长安小区停车场
	
	/**
	 * 全部参数
	 * @param carid
	 * @param indatetime
	 * @param outdatetime
	 * @param parkid
	 * @param parkadminid
	 * @param parklotname
	 */
	public Table_InOutInfo(String carid, String indatetime, String outdatetime, int parkid, String parkadminid,
			String parklotname) {
		super();
		this.carid = carid;
		this.indatetime = indatetime;
		this.outdatetime = outdatetime;
		this.parkid = parkid;
		this.parkadminid = parkadminid;
		this.parklotname = parklotname;
	}

	/**
	 * 不包含outdatetime，因为outdatetime中没有数据，在QueryInInfoDao中使用此构造方法
	 * @param carid
	 * @param indatetime
	 * @param parkid
	 * @param parkadminid
	 * @param parklotname
	 */
	public Table_InOutInfo(String carid, String indatetime, int parkid, String parkadminid, String parklotname) {
		super();
		this.carid = carid;
		this.indatetime = indatetime;
		this.parkid = parkid;
		this.parkadminid = parkadminid;
		this.parklotname = parklotname;
	}



	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getIndatetime() {
		return indatetime;
	}

	public void setIndatetime(String indatetime) {
		this.indatetime = indatetime;
	}

	public String getOutdatetime() {
		return outdatetime;
	}

	public void setOutdatetime(String outdatetime) {
		this.outdatetime = outdatetime;
	}

	public int getParkid() {
		return parkid;
	}

	public void setParkid(int parkid) {
		this.parkid = parkid;
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
	
}
