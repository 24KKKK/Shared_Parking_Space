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
import com.dyf.utils.LocationUtil;
import com.dyf.utils.SysoUtils;

/**
 * Servlet implementation class AddParklotInfoDao
 */
@WebServlet(description = "添加停车场信息", urlPatterns = { "/AddParklotInfoDao" })
public class AddParklotInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddParklotInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddParklotInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String parklotName = request.getParameter("parklotname");
		int parklotAmount = Integer.parseInt(request.getParameter("parklotamount"));
		String parklotLng = request.getParameter("parklotLng");
		String parklotLat = request.getParameter("parklotLat");
		String parklotDescription = request.getParameter("parklotdescription");
		String parklotAdminId = request.getParameter("parklotadminid");
		String parklotCreatedTime = CreateDate.getDate();
		
		//停车场的省市区信息
		String[] arr = LocationUtil.getLocation(parklotLng, parklotLat);
		
		SysoUtils.print("新增的停车场信息：");
		SysoUtils.print(parklotName + " " + parklotAmount + " " + parklotLng + " " + parklotLat + " "
				+ parklotDescription + " " + parklotAdminId + " " + parklotCreatedTime+" "+arr[0]+" "+arr[1]+" "+arr[2]);

		String insertSql = "insert into table_parklotinfo (parklotName,parklotAmount,parklotLng,parklotLat,parklotDescription,parklotAdminId,parklotCreatedTime,parklotProvince,parklotCity,parklotArea) values('"
				+ parklotName + "'," + parklotAmount + ",'" + parklotLng + "','" + parklotLat + "','"
				+ parklotDescription + "','" + parklotAdminId + "','" + parklotCreatedTime + "','"+arr[0]+"','"+arr[1]+"','"+arr[2]+"')";
		SysoUtils.print("insertSql=" + insertSql);

		DBBean db = new DBBean();
		int i = db.executeUpdate(insertSql);
		if (i == 1) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加停车场信息成功。");
			out.println("<script type='text/javascript'> alert('成功添加停车场信息');</script>");
			response.setHeader("refresh", "0;url=Systemadmin/ParklotInfo/NewParklot.jsp");
		} else {
			SysoUtils.print("添加停车场信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('添加停车场信息失败');</script>");
			response.setHeader("refresh", "0;url=Systemadmin/ParklotInfo/NewParklot.jsp");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
