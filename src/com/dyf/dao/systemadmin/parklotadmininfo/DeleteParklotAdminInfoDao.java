package com.dyf.dao.systemadmin.parklotadmininfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dyf.bean.DBBean;
import com.dyf.utils.SysoUtils;

/**
 * Servlet implementation class DeleteParklotAdminInfoDao
 */
@WebServlet(description = "删除停车场管理员信息", urlPatterns = { "/DeleteParklotAdminInfoDao" })
public class DeleteParklotAdminInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteParklotAdminInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入DeleteParklotAdminInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
		SysoUtils.print("删除的删除id：" + id);

		DBBean db = new DBBean();
		String delSql = "delete from table_parklotadmininfo where parklotAdminId = " + "'" + id + "'";
		SysoUtils.print("删除的delSql为：" + delSql);
		int i = db.executeUpdate(delSql);
		if (i == 1) {
			SysoUtils.print("删除成功。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除成功');</script>");
			response.setHeader("refresh", "0;url=QueryParklotAdminInfoDao");
		} else {
			SysoUtils.print("删除失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除失败');</script>");
			response.setHeader("refresh", "0;url=QueryParklotAdminInfoDao");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
