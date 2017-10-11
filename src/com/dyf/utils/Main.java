package com.dyf.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dyf.bean.DBBean;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBBean dbBean = new DBBean();
		String sql = "select count(*) as countnum from table_parklotadmininfo";
		ResultSet rSet = dbBean.executeQuery(sql);
		try {
			while (rSet.next()) {
				String str = rSet.getString("countnum");
				SysoUtils.print(str);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
