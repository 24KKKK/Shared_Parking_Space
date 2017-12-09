package com.dyf.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;

/**
 * 和网页分页有关的工具类
 * @author diy
 *
 */
public class Page extends HttpServlet {
	private static final long serialVersionUID = 1093424081610238304L;

	/**
	 * 描述：根据表名称，以及每页需要显示的数量，返回总页数
	 * @param tableName 需要计算页数的表名String
	 * @param showNumPerPage 每页显示的数量int
	 * @return pageNum 总页数
	 */
	public static int getPageNum(String tableName, int showNumPerPage, HttpServletRequest request) {
		SysoUtils.print("tableName:" + tableName + ",showNumPerPage:" + showNumPerPage);
		
		int pageNum = 0;  //总页数
		
		String countNumSql = "select count(*) as countnum from " + tableName ;

		//如果是车辆进出表，需要加一个wheresql
		if(tableName.equals("table_inoutinfo")||tableName.equals("table_inoutinfobackup")){
			
			HttpSession session = request.getSession();
			String adminId = (String) session.getAttribute("userid");
			
			String whereSql = " where parkadminid = '"+adminId+"'";
			countNumSql+=whereSql;
			SysoUtils.print("Page里面的countNumSql:"+countNumSql);
		}
		
		
		SysoUtils.print("countNumSql:"+countNumSql);
		DBBean db = new DBBean();
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		SysoUtils.print("总页数：" + pageNum);
		return pageNum;
	}
	
	/**
	 * @deprecated 根据一共记录的条数int，以及每页需要显示的数量int，返回总页数
	 * @param listNum 记录的条数
	 * @param showNumPerPage 每页显示的数量
	 * @return 页数
	 */
	/*public static int getPageNum(int listNum, int showNumPerPage) {
		int pageNum = 0;  //总页数
		pageNum = listNum / showNumPerPage + 1; // 得到总页数
		SysoUtils.print("总行数：" + listNum);
		SysoUtils.print("总页数：" + pageNum);
		return pageNum;
	}*/

}
