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

@WebServlet(description = "将修改过的停车场管理员信息放入数据库", urlPatterns = { "/UpdateParklotAdminInfoIntoDatabase" })
public class UpdateParklotAdminInfoIntoDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateParklotAdminInfoIntoDatabase() {
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

		SysoUtils.print("修改后的传过来的值为：");
		SysoUtils.print(parklotAdminId + " " + parklotAdminPhone + " " + parklotAdminAge + " " + parklotAdminIdnumber
				+ " " + parklotAdminName + " " + parklotAdminLoginId + " " + parklotAdminLoginPass);

		String updateSql = "update table_parklotAdminInfo set parklotAdminPhone=" + "'" + parklotAdminPhone + "'"
				+ ",parklotAdminAge=" + "'" + parklotAdminAge + "'" + ",parklotAdminIdnumber=" + "'"
				+ parklotAdminIdnumber + "'" + ",parklotAdminName=" + "'" + parklotAdminName + "'"
				+ ",parklotAdminLoginId=" + "'" + parklotAdminLoginId + "'" + ",parklotAdminLoginPass=" + "'"
				+ parklotAdminLoginPass + "'" + "where parklotAdminId=" + "'" + parklotAdminId + "'";

		SysoUtils.print("updateSql=" + updateSql);

		DBBean db = new DBBean();
		int i = db.executeUpdate(updateSql);
		SysoUtils.print("update i=" + i);
		if (i == 1) {
			SysoUtils.print("修改停车场管理员信息成功。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('更新成功');</script>");
			response.setHeader("refresh", "0;url=QueryParklotAdminInfoDao");
		} else {
			SysoUtils.print("修改停车场管理员信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('更新失败');</script>");
			response.setHeader("refresh", "0;url=QueryParklotAdminInfoDao");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
