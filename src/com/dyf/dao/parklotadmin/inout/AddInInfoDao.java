package com.dyf.dao.parklotadmin.inout;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "停车场进入信息登记表", urlPatterns = { "/AddInInfoDao" })
public class AddInInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddInInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddInInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String carid = request.getParameter("carid");
		int inOrOut = 1;
		String parkId = request.getParameter("parkid");
		String inDatetime = CreateDate.getDate();
		String parkAdminId = (String) session.getAttribute("userid");
		String parklotName = null;
		try {
			parklotName = InOutUtil.getParklotName(parkAdminId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SysoUtils.print("carid=" + carid + ",parkid=" + parkId + ",indatetime=" + inDatetime + ",parkAdminId="
				+ parkAdminId + ",parklotName=" + parklotName);
		
		String insertSql = "insert into table_inoutinfo(carid,inorout,indatetime,parkid,parkadminid,parklotname) values ( "
							+"'"+carid+"',"+inOrOut+",'"+inDatetime+"',"+parkId+",'"+parkAdminId+"',"+"'"+parklotName+"')";
		SysoUtils.print("insertSql="+insertSql);
		
		DBBean dbBean = new DBBean();
		int i = dbBean.executeUpdate(insertSql);
		if (i == 1) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加入场信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.html");
		} else {
			SysoUtils.print("添加入场信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('添加失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.html");
		}
		dbBean.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
