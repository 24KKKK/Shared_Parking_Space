package com.dyf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author diy
 *
 */
public class CreateDate {
	//生成时间串，2017-08-02 10:35:09
	/**
	 * @return
	 */
	public static String getDate() {
		String dateTime;   //时间日期
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  //获取的系统时间例如2017-0-802 10:35:09
	    dateTime = dateFormat.format(date);  
	    return dateTime;
	}

}