package com.dyf.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dyf.bean.DBBean;

/**
 * 和网页分页有关的工具类
 * 
 * @author diy
 *
 */
public class Page {
	/**
	 * 描述：根据表中的记录行数，以及每页需要显示的数量，返回总页数
	 * 
	 * @param tableName 需要计算页数的表名String
	 * @param showNumPerPage 每页显示的数量int
	 * @return pageNum 总页数
	 */
	public static int getPageNum(String tableName, int showNumPerPage) {
		SysoUtils.print("tableName:" + tableName + ",showNumPerPage:" + showNumPerPage);
		DBBean db = new DBBean();
		int pageNum = 0;  //总页数
		String countNumSql = "select count(*) as countnum from " + tableName;
		SysoUtils.print("countNumSql:"+countNumSql);
		ResultSet rs = db.executeQuery(countNumSql);
		try {
			while (rs.next()) {
				String str_countNum = rs.getString("countnum");
				int countNum = Integer.parseInt(str_countNum); // 总行数
				pageNum = countNum / showNumPerPage + 1; // 得到总页数
				SysoUtils.print("总行数：" + countNum);
				SysoUtils.print("总页数：" + pageNum);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				db.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		SysoUtils.print("总页数：" + pageNum);
		return pageNum;
	}

}
