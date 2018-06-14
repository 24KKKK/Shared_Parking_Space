package com.dyf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @deprecated 生成时间串
 * @author diy
 */
public class CreateDate {

	/**
	 * @deprecated 生成时间串，2017-08-02 10:35:09
	 * @return 时间串，例如2017-08-02 10:35:09
	 */
	public static String getDate() {
		String dateTime;   //时间日期
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  //获取的系统时间例如2017-08-02 10:35:09
	    dateTime = dateFormat.format(date);  
	    return dateTime;
	}
	
	/**
	 * @deprecated 生成小时和分钟点，例如 13:34
	 * @return 小时和分钟，13:34
	 */
	public static String getHourMin(){
		String hourMin = "";  // 小时和分钟点
		Date date = new Date();
		hourMin = String.valueOf(date.getHours())+":"+String.valueOf(date.getMinutes());
		return hourMin;
	}
	
	public static void main(String[] args) {
		SysoUtils.print(CreateDate.getHourMin());
		SysoUtils.print(CreateDate.getDate());
		
	}

}