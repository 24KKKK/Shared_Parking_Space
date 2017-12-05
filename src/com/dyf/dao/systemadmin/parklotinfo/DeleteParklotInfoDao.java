package com.dyf.dao.systemadmin.parklotinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dyf.bean.DBBean;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "删除停车场信息", urlPatterns = { "/DeleteParklotInfoDao" })
public class DeleteParklotInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteParklotInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入DeleteParklotInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		SysoUtils.print("删除的删除name：" + name);

		DBBean db = new DBBean();
		String delSql = "delete from table_parklotinfo where parklotName = '" + name + "'";
		SysoUtils.print("删除的delSql为：" + delSql);
		int i = db.executeUpdate(delSql);
		if (i == 1) {
			SysoUtils.print("删除成功。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除成功');</script>");
			response.setHeader("refresh", "0;url=QueryParklotInfoDao");
		} else {
			SysoUtils.print("删除失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除失败');</script>");
			response.setHeader("refresh", "0;url=QueryParklotInfoDao");
		}
		db.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
