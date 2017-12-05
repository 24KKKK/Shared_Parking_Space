package com.dyf.dao.systemadmin.parklotinfo;

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

@WebServlet(description = "修改过的停车场信息放入数据库", urlPatterns = { "/UpdateParklotInfoIntoDatabase" })
public class UpdateParklotInfoIntoDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateParklotInfoIntoDatabase() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入UpdateParklotInfoIntoDatabase.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String parklotName = request.getParameter("parklotname");
		int parklotAmount = Integer.parseInt(request.getParameter("parklotamount"));
		String parklotLng = request.getParameter("parklotLng");
		String parklotLat = request.getParameter("parklotLat");
		String parklotDescription = request.getParameter("parklotdescription");
		String parklotAdminId = request.getParameter("parklotadminid");
		SysoUtils.print("新增的停车场信息：");
		SysoUtils.print(parklotName + " " + parklotAmount + " " + parklotLng + " " + parklotLat + " "
				+ parklotDescription + " " + parklotAdminId);

		String updateSql = "update table_parklotinfo set parklotAmount=" + parklotAmount + ",parklotLng='" + parklotLng
				+ "',parklotLat='" + parklotLat + "',parklotDescription='" + parklotDescription + "',parklotAdminId='"
				+ parklotAdminId + "' where parklotName='" + parklotName + "'";
		SysoUtils.print("updateSql=" + updateSql);

		DBBean db = new DBBean();
		int i = db.executeUpdate(updateSql);
		if (i == 1) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("修改停车场信息成功。");
			out.println("<script type='text/javascript'> alert('更新成功');</script>");
			response.setHeader("refresh", "0;url=QueryParklotInfoDao");
		} else {
			SysoUtils.print("修改停车场信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('更新失败');</script>");
			response.setHeader("refresh", "0;url=QueryParklotInfoDao");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
