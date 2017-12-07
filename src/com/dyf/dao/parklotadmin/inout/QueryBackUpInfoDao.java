package com.dyf.dao.parklotadmin.inout;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "查看停车场停车的历史信息", urlPatterns = { "/QueryBackUpInfoDao" })
public class QueryBackUpInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryBackUpInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
