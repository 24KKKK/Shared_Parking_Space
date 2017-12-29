package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "删除停车位购买信息", urlPatterns = { "/DeleteBuyInfoDao" })
public class DeleteBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = 2821092561871873405L;

	public DeleteBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入DeleteBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		String parklotName = "";
		parklotName = InOutUtil.getParklotName(adminId);

		String createdTime = request.getParameter("buycreatedtime");
		String deleteBuyInfoSql = "delete from table_buyinfo where buycreatedtime = '" + createdTime + "';";
		String deleteOwnerInfoSql = "delete from table_ownerinfo where ownercreatedtime = '" + createdTime + "';";
		SysoUtils.print("deleteBuyInfoSql:" + deleteBuyInfoSql);
		SysoUtils.print("deleteOwnerInfoSql:" + deleteOwnerInfoSql);

		DBBean dbBean = new DBBean();
		int deleteBuyInfoResult = dbBean.executeUpdate(deleteBuyInfoSql);
		int deleteOwnerInfoResult = dbBean.executeUpdate(deleteOwnerInfoSql);

		if (deleteBuyInfoResult == 1 && deleteOwnerInfoResult == 1) {
			SysoUtils.print("删除成功。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除成功');</script>");
			response.setHeader("refresh", "0;url=QueryBuyInfoDao");
		} else {
			SysoUtils.print("删除失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('删除失败');</script>");
			response.setHeader("refresh", "0;url=QueryBuyInfoDao");
		}
		dbBean.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
