package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "准备修改停车位购买信息", urlPatterns = { "/UpdateBuyInfoDao" })
public class UpdateBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = 2000232428450398131L;

	public UpdateBuyInfoDao() {
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
