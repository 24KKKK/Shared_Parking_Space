package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.model.BuyInfo;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "查看车位已经购买的信息", urlPatterns = { "/QueryBuyInfoDao" })
public class QueryBuyInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入QueryBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("adminid");
		List<BuyInfo> buyInfos = new ArrayList<BuyInfo>();

		String selectAllSql = "SELECT table_ownerinfo.ownername, table_ownerinfo.ownergender, table_ownerinfo.ownerphone, table_buyinfo.buyparkid, table_buyinfo.buystartparktime, table_buyinfo.buyendparktime, table_buyinfo.buystartparkdate, table_buyinfo.buyendparkdate, table_ownerinfo.owneridnumber, table_ownerinfo.owneraddress, table_buyinfo.parkadminid, table_buyinfo.parklotname, table_buyinfo.buymoney, table_buyinfo.buycreatedtime FROM table_buyinfo INNER JOIN table_ownerinfo ON table_buyinfo.buyidnumber = table_ownerinfo.owneridnumber where 1=1 ";
		SysoUtils.print("查询全部购买信息selectAllSql:" + selectAllSql);

		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectAllSql);
		try {
			while (rSet.next()) {
				String ownername = rSet.getString("ownername");
				int ownergender = rSet.getInt("ownergender");
				String ownerphone = rSet.getString("ownerphone");
				int buyparkid = rSet.getInt("buyparkid");
				int buystartparktime = rSet.getInt("buystartparktime");
				int buyendparktime = rSet.getInt("buyendparktime");
				String buystartparkdate = rSet.getString("buystartparkdate");
				String buyendparkdate = rSet.getString("buyendparkdate");
				String owneridnumber = rSet.getString("owneridnumber");
				String owneraddress = rSet.getString("owneraddress");
				String parkadminid = rSet.getString("parkadminid");
				String parklotname = rSet.getString("parklotname");
				double buymoney = rSet.getDouble("buymoney");
				String buycreatedtime = rSet.getString("buycreatedtime");

				BuyInfo buyInfo = new BuyInfo(ownername, ownergender, ownerphone, buyparkid, buystartparktime,
						buyendparktime, buystartparkdate, buyendparkdate, owneridnumber, owneraddress, parkadminid,
						parklotname, buymoney, buycreatedtime);
				buyInfos.add(buyInfo);
			}
			request.setAttribute("buyinfos", buyInfos);
			request.getRequestDispatcher("/Parklotadmin/Buy/ShowBuyInfo.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
