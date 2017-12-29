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

@WebServlet(description = "新增停车位购买信息", urlPatterns = { "/AddBuyInfoDao" })
public class AddBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = -1367038298887854643L;

	public AddBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		String parklotName = "";
		parklotName = InOutUtil.getParklotName(adminId);

		String ownerName = request.getParameter("ownername");
		String ownerPhone = request.getParameter("ownerphone");
		String strOwnerGender = request.getParameter("ownergender");
		int ownerGender = Integer.parseInt(strOwnerGender);
		String ownerIdNumber = request.getParameter("owneridnumber");
		String ownerAddress = request.getParameter("owneraddress");
		String strBuyParkId = request.getParameter("buyparkid");
		int buyParkId = Integer.parseInt(strBuyParkId);
		String buyStartParkTime = request.getParameter("buystartparktime");
		String buyEndParkTime = request.getParameter("buyendparktime");
		String buyStartParkDate = request.getParameter("buystartparkdate");
		String buyEndParkDate = request.getParameter("buyendparkdate");
		String strBuyMoney = request.getParameter("buymoney");
		double buyMoney = Double.parseDouble(strBuyMoney);
		String buyCreatedTime = CreateDate.getDate();

		Table_BuyInfo table_BuyInfo = new Table_BuyInfo(adminId, parklotName, ownerIdNumber, buyParkId,
				buyStartParkTime, buyEndParkTime, buyStartParkDate, buyEndParkDate, buyMoney, buyCreatedTime);
		SysoUtils.print("table_BuyInfo为:" + table_BuyInfo.toString());

		Table_OwnerInfo table_OwnerInfo = new Table_OwnerInfo(adminId, parklotName, ownerName, ownerPhone, ownerGender,
				ownerIdNumber, ownerAddress);
		SysoUtils.print("table_OwnerInfo为:" + table_OwnerInfo.toString());

		String insertBuySql = "insert into table_buyinfo (parkadminid,parklotname,buyidnumber,buyparkid,buystartparktime,buyendparktime,buystartparkdate,buyendparkdate,buymoney,buycreatedtime)VALUES('"
				+ adminId + "','" + parklotName + "','" + ownerIdNumber + "'," + buyParkId + "," + buyStartParkTime
				+ "," + buyEndParkTime + ",'" + buyStartParkDate + "','" + buyEndParkDate + "'," + buyMoney + ",'"
				+ buyCreatedTime + "');";
		SysoUtils.print("insertBuySql:" + insertBuySql);

		String insertOwnerSql = "INSERT INTO table_ownerinfo (parkadminid,parklotname,ownername,ownerphone,ownergender,owneridnumber,owneraddress,ownercreatedtime) VALUES ('"
				+ adminId + "','" + parklotName + "','" + ownerName + "',	'" + ownerPhone + "'," + ownerGender + ",'"
				+ ownerIdNumber + "','" + ownerAddress +"','"+ buyCreatedTime + "');";
		SysoUtils.print("insertOwnerSql:" + insertOwnerSql);
		
		DBBean dbBean= new DBBean();
		int i = dbBean.executeUpdate(insertBuySql);
		int j = dbBean.executeUpdate(insertOwnerSql);
		if (i==1&&j==1) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("成功添加购买信息");
			out.println("<script type='text/javascript'> alert('成功添加购买信息');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/Buy/NewBuyInfo.jsp");
		}else {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加购买信息失败");
			out.println("<script type='text/javascript'> alert('添加购买信息失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/Buy/NewBuyInfo.jsp");
		}
		dbBean.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
