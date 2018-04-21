package com.dyf.dao.parklotadmin.inout;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.utils.Count;
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "新增离开停车场信息，同时将记录移动到备份表", urlPatterns = { "/AddOutInfoDao" })
public class AddOutInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddOutInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddOutInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String outDatetime = CreateDate.getDate();

		String carid = request.getParameter("carid");
		// 出去之前先查询一下是不是已经购买了停车位的车
		String selectisbuy = "select ownerphone from table_ownerinfo where ownerplatenum = '"+carid+"'";
		String selectintime = "select indatetime from table_inoutinfo  where carid = '"+carid+"'";
		DBBean dbBean2 = new DBBean();
		ResultSet rSet = dbBean2.executeQuery(selectisbuy);
		try {
			if(rSet.next()) {
				out.println("<script type='text/javascript'> alert('该车主已购买停车位。');</script>");
			}
			else {
				out.println("<script type='text/javascript'> alert('该车主未购买停车位。');</script>");
				rSet.close();
				ResultSet rSet2 = dbBean2.executeQuery(selectintime);
				while (rSet2.next()) {
					String intime = rSet2.getString("indatetime");
					double cost = Count.getCostMoney(intime, outDatetime);
					out.println("<script type='text/javascript'> alert('应支付："+String.valueOf(cost)+"');</script>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// int inOrOut = 1;
		// String parkId = request.getParameter("parkid");
		
		// String parkAdminId = (String) session.getAttribute("userid");
		/*
		 * String parklotName = null; try { parklotName =
		 * InOutUtil.getParklotName(parkAdminId); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
		SysoUtils.print("carid=" + carid + ",outdatetime=" + outDatetime);

		String updateSql = "update table_inoutinfo set outdatetime='" + outDatetime + "' where carid='" + carid
				+ "' limit 1";
		String copySql = "insert into table_inoutinfobackup ( SELECT * FROM table_inoutinfo where carid='" + carid
				+ "' LIMIT 1)";
		String deleteSql = "delete from table_inoutinfo where carid='" + carid + "' limit 1";
		SysoUtils.print("updateSql=" + updateSql);
		SysoUtils.print("copySql=" + copySql);
		SysoUtils.print("deleteSql=" + deleteSql);

		DBBean dbBean = new DBBean();
		int i = dbBean.executeUpdate(updateSql);
		int j = dbBean.executeUpdate(copySql);
		int k = dbBean.executeUpdate(deleteSql);
		if (i == 1 && j == 1 && k == 1) {
			SysoUtils.print("添加离开信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewOut.jsp");
		} else {
			SysoUtils.print("添加离开信息失败。");
			out.println("<script type='text/javascript'> alert('添加失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewOut.jsp");
		}
		dbBean.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
