package com.dyf.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dyf.bean.DBBean;

public class InOutUtil {
	
	public static String getParklotName(String id) throws SQLException{
		SysoUtils.print("传入的停车场管理员id为："+id);
		String name = null;
		DBBean dbBean = new DBBean();
		String sql = "select parklotName from table_parklotInfo where parklotAdminId = " + "'"+id+"'";
		SysoUtils.print("查询name的sql为："+sql);
		ResultSet rSet = dbBean.executeQuery(sql);
		while(rSet.next()){
			name = rSet.getString("parklotName");
		}
		rSet.close();
		dbBean.close();
		SysoUtils.print("查询到的停车场名称为："+name);
		return name;
	}

}
