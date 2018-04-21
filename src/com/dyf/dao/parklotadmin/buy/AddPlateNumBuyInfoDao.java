package com.dyf.dao.parklotadmin.buy;

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
import com.dyf.model.Table_BuyInfo;
import com.dyf.model.Table_OwnerInfo;
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "新增车牌号信息", urlPatterns = { "/AddPlateNumBuyInfoDao" })
public class AddPlateNumBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = 6290197649689989965L;

	public AddPlateNumBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddPlateNumBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		String parklotName = "";
		parklotName = InOutUtil.getParklotName(adminId);

		String ownerIdNumber = request.getParameter("owneridnumber");
		String ownerPlateNum = request.getParameter("ownerplatenum");
		
		String updateOwnerSql = "update table_ownerinfo set ownerplatenum = '"+ownerPlateNum+"' where owneridnumber = '"+ownerIdNumber+"'";
		SysoUtils.print("updateOwnerSql:" + updateOwnerSql);
		
		DBBean dbBean= new DBBean();
		int i = dbBean.executeUpdate(updateOwnerSql);
		
		if (i!=0) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("成功添加车牌号信息");
			out.println("<script type='text/javascript'> alert('成功添加车牌号信息');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/Buy/AddPlateNumBuyInfo.jsp");
		}else {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加车牌号信息失败");
			out.println("<script type='text/javascript'> alert('添加车牌号信息失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/Buy/AddPlateNumBuyInfo.jsp");
		}
		dbBean.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
