package com.dyf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateDate {
	//生成时间串，2017-08-02 10:35:09
	public static String getDate() {
		String DATETIME;   //时间日期
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    DATETIME = dateFormat.format(date);  //获取的系统时间例如2017-0-802 10:35:09
	    return DATETIME;
	}

}