package com.dyf.utils;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//int i = Page.getPageNum("table_inoutinfo", 5);
		//SysoUtils.print("i:"+i);
		
		/*int k = InOutUtil.getParkAmount("测试停车场1");
		SysoUtils.print("可以停车的车位号为："+ k );*/
		
		//InOutUtil.getArrParkId("测试停车场1");
		//InOutUtil.getParkNumber("测试停车场1");
		
	}
}

/*DBBean dbBean = new DBBean();
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
}*/
