package com.dyf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Count {
	/**
	 * 根据传进来的停车开始和结束时间，返回这次停车的费用
	 * @param starttime
	 * @param endtime
	 * @param startdate
	 * @param enddate
	 * @return double cost
	 */
	public static double getCostMoney(String starttime, String endtime) {
		int a[] = new int[3];
		String format = "yyyy-MM-dd HH:mm:ss";
		a=dateDiff(starttime, endtime, format);
		
		double mincount = a[0]*24*60+a[1]*60+a[2];
		int cost = 0;
		if (mincount<=120) {
			cost = (int)mincount/30+1;
			SysoUtils.print("花费："+cost+" 元");
			return cost;
		}else if (mincount>120&&mincount<=480) {
			cost = (int)(mincount-120)/60+1+4;
			SysoUtils.print("花费："+cost+" 元");
			return cost;
		}else {
			cost = (int)(mincount-480)/120+11;
			SysoUtils.print("花费："+cost+" 元");
			return cost;
		}
	}

	public static int[] dateDiff(String startTime, String endTime, String format)  {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		//long ns = 1000;// 一秒钟的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = 0;
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算相差剩余多少小时
		long hour2 = diff / nh; // 计算相差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		//long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
		int[] a = {Integer.parseInt(String.valueOf(day)),Integer.parseInt(String.valueOf(hour)),Integer.parseInt(String.valueOf(min))};
		System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟");
		return a;
	}
	
	public static void main(String[] args) {
		getCostMoney("2018-4-19 12:36:00", "2018-4-22 13:00:00");
	}

}
