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
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "准备修改停车位购买信息", urlPatterns = { "/UpdateBuyInfoDao" })
public class UpdateBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = 2000232428450398131L;

	public UpdateBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入UpdateBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String buyCreatedTime = request.getParameter("buycreatedtime");
		SysoUtils.print("buyCreatedTime:" + buyCreatedTime);
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		SysoUtils.print("管理员id为："+adminId);
		String parklotName = InOutUtil.getParklotName(adminId);
		List<BuyInfo> buyInfos = new ArrayList<BuyInfo>();

		String selectSql = "SELECT table_ownerinfo.ownername, "
				+ "table_ownerinfo.ownergender, table_ownerinfo.ownerphone, "
				+ "table_buyinfo.buyparkid, table_buyinfo.buystartparktime,"
				+ " table_buyinfo.buyendparktime, table_buyinfo.buystartparkdate,"
				+ " table_buyinfo.buyendparkdate, table_ownerinfo.owneridnumber, "
				+ "table_ownerinfo.owneraddress, table_buyinfo.parkadminid, " + "table_buyinfo.buymoney"
				+ " FROM table_buyinfo INNER JOIN table_ownerinfo ON "
				+ "table_buyinfo.buyidnumber = table_ownerinfo.owneridnumber"
				+ " where table_buyinfo.parkadminid = '"+adminId+"' and table_buyinfo.buycreatedtime = '" + buyCreatedTime + "'";
		SysoUtils.print("查询需要修改的购买信息的selectSql:" + selectSql);

		String ownerName = "";
		int ownerGender = 1;
		String ownerPhone = "";
		int buyParkId = 0;
		int buyStartParkTime = 0;
		int buyEndParkTime = 0;
		String buyStartParkDate = "";
		String buyEndParkDate = "";
		String ownerIdNumber = "";
		String ownerAddress = "";
		double buyMoney = 0.0;
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSql);
		try {
			while (rSet.next()) {
				ownerName = rSet.getString("ownername");
				ownerGender = rSet.getInt("ownergender");
				ownerPhone = rSet.getString("ownerphone");
				buyParkId = rSet.getInt("buyparkid");
				buyStartParkTime = rSet.getInt("buystartparktime");
				buyEndParkTime = rSet.getInt("buyendparktime");
				buyStartParkDate = rSet.getString("buystartparkdate");
				buyEndParkDate = rSet.getString("buyendparkdate");
				ownerIdNumber = rSet.getString("owneridnumber");
				ownerAddress = rSet.getString("owneraddress");
				buyMoney = rSet.getDouble("buymoney");
				BuyInfo buyInfo = new BuyInfo(ownerName, ownerGender, ownerPhone, buyParkId, buyStartParkTime,
						buyEndParkTime, buyStartParkDate, buyEndParkDate, ownerIdNumber, ownerAddress, adminId,
						parklotName, buyMoney, buyCreatedTime);
				SysoUtils.print(buyInfo.toString());
				buyInfos.add(buyInfo);
			}
			request.setAttribute("updatebuyinfo", buyInfos);
			request.getRequestDispatcher("/Parklotadmin/Buy/UpdateBuyInfo.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rSet.close();
				dbBean.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
