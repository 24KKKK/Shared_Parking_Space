package com.dyf.dao.parklotadmin.inout;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.model.Table_InOutInfo;
import com.dyf.utils.CreateDate;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "查看停车场内的车辆信息", urlPatterns = { "/QueryInInfoDao" })
public class QueryInInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryInInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入QueryInInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		// 获取分页传过来的页码号
		String strPageNum = request.getParameter("pagenum");
		int pageNum = 1;
		if (strPageNum != null) {
			SysoUtils.print("页码号：" + strPageNum);
			pageNum = Integer.parseInt(strPageNum);
		}

		// 获取查询条件
		String selectKey1 = request.getParameter("selectkey1");
		String selectValue1 = request.getParameter("selectvalue1");
		String selectKey2 = request.getParameter("selectkey2");
		String selectValue2 = request.getParameter("selectvalue2");
		SysoUtils.print("selectkey1:" + selectKey1 + " selectvalue1:" + selectValue1);
		SysoUtils.print("selectkey2:" + selectKey2 + " selectvalue2:" + selectValue2);
		String whereSql = null;
		//判断第一个查询框的值，拼接查询sql
		if (selectKey1 != null && selectValue1 != null && selectValue1 != "") {
			switch (selectKey1) {
			case "carid":
				whereSql = " and carid LIKE '%" + selectValue1 + "%'";
				break;
			case "parkid":
				whereSql = " and parkid LIKE '%" + selectValue1 + "%'";
				break;
			default:
				break;
			}
		}
		
		//判断第二个查询框的值，拼接查询sql
				if (selectKey2 != null && selectValue2 != null && selectValue2 != "") {
					switch (selectKey2) {
					case "startindatetime":
						whereSql = " and indatetime between '"+selectValue2+"' and '"+CreateDate.getDate()+"'";
						break;
					case "endindatetime":
						//因为停车场里现有车辆不会很多，所以直接设置当前年份第一天到指定时间之间的时间段
						whereSql = " and indatetime between '"+selectValue2.substring(0, 4)+"-01-01 00:00:00"+"' and '"+selectValue2+"'";
						break;
					default:
						break;
					}
				}
		
		//查询现在场内车辆信息
		List<Table_InOutInfo> inOutInfos = new ArrayList<Table_InOutInfo>();
		HttpSession session = request.getSession();
		String adminid = (String) session.getAttribute("userid");
		String selectInInfoSql = "select carid,indatetime,parkid,parkadminid,parklotname from table_inoutinfo where parkadminid='"
				+ adminid + "' ";
		if(whereSql != null){
			selectInInfoSql+=whereSql;
		}
		SysoUtils.print("查询场内车辆信息的selectInInfoSql：" + selectInInfoSql);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectInInfoSql);
		try {
			while (rSet.next()) {
				String carId = rSet.getString("carid");
				String inDatetime = rSet.getString("indatetime").substring(0, 19);
				int parkId = rSet.getInt("parkid");
				String parkAdminId = rSet.getString("parkadminid");
				String parklotName = rSet.getString("parklotname");
				SysoUtils.print("查询出来的场内车辆信息："+carId+" "+inDatetime+" "+parkId+" "+parkAdminId+" "+parklotName);
				
				Table_InOutInfo table_InOutInfo = new Table_InOutInfo(carId, inDatetime, parkId, parkAdminId, parklotName);
				inOutInfos.add(table_InOutInfo);
			}
			request.setAttribute("inOutInfos", inOutInfos);
			request.getRequestDispatcher("/Parklotadmin/InOut/ShowInInfo.jsp").forward(request,
					response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
