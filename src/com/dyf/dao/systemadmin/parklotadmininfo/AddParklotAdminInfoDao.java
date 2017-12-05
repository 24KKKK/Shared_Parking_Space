package com.dyf.dao.systemadmin.parklotadmininfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dyf.bean.DBBean;
import com.dyf.utils.CreateDate;
import com.dyf.utils.SysoUtils;

/**
 * 描述：将停车场管理员基本信息插入数据库
 */
@WebServlet(name = "AddParklotAdminInfoDao", urlPatterns = { "/AddParklotAdminInfoDao",
		"/*/AddParklotAdminInfoDao" }, description = "增加停车场管理员信息")
public class AddParklotAdminInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddParklotAdminInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddParklotAdminInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String parklotAdminId = request.getParameter("parklotadminid");
		String parklotAdminPhone = request.getParameter("parklotadminphone");
		int parklotAdminAge = Integer.parseInt(request.getParameter("parklotadminage"));
		String parklotAdminIdnumber = request.getParameter("parklotadminidnumber");
		String parklotAdminName = request.getParameter("parklotadminname");
		String parklotAdminLoginId = request.getParameter("parklotadminloginid");
		String parklotAdminLoginPass = request.getParameter("parklotadminloginpass");
		String parklotAdminCreatedTime = CreateDate.getDate();
		SysoUtils.print(parklotAdminId + " " + parklotAdminPhone + " " + parklotAdminAge + " " + parklotAdminIdnumber
				+ " " + parklotAdminName + " " + parklotAdminLoginId + " " + parklotAdminLoginPass + " "
				+ parklotAdminCreatedTime);

		String insertSql = "insert into table_parklotAdminInfo (parklotadminid,"
				+ "parklotadminphone,parklotadminage,parklotadminidnumber,parklotadminname,"
				+ "parklotadminloginid,parklotadminloginpass,parklotAdminCreatedTime) values('" + parklotAdminId + "','"
				+ parklotAdminPhone + "'," + parklotAdminAge + ",'" + parklotAdminIdnumber + "','" + parklotAdminName
				+ "'," + "'" + parklotAdminLoginId + "','" + parklotAdminLoginPass + "','" + parklotAdminCreatedTime
				+ "')";
		SysoUtils.print("insertSql=" + insertSql);

		DBBean db = new DBBean();
		int i = db.executeUpdate(insertSql);
		if (i == 1) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加停车场管理员信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功');</script>");
			response.setHeader("refresh", "0;url=Systemadmin/ParkinglotAdminInfo/NewParkinglotAdmin.html");
		} else {
			SysoUtils.print("添加停车场管理员信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('添加失败');</script>");
			response.setHeader("refresh", "0;url=Systemadmin/ParkinglotAdminInfo/NewParkinglotAdmin.html");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
