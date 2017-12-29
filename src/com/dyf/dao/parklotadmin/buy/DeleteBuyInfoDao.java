package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.model.Table_BuyInfoBackup;
import com.dyf.utils.CreateDate;
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
		String selectBuyInfosql = "SELECT table_ownerinfo.ownername,table_ownerinfo.ownergender,table_ownerinfo.ownerphone,table_buyinfo.buyparkid,table_buyinfo.buystartparktime,table_buyinfo.buyendparktime,table_buyinfo.buystartparkdate,table_buyinfo.buyendparkdate,table_ownerinfo.owneridnumber,table_ownerinfo.owneraddress,table_buyinfo.buymoney FROM table_buyinfo INNER JOIN table_ownerinfo ON table_buyinfo.buycreatedtime = table_ownerinfo.ownercreatedtime where table_buyinfo.buycreatedtime = '"
				+ createdTime + "' and table_buyinfo.parkadminid = '" + adminId + "'";
		SysoUtils.print("selectBuyInfosql:" + selectBuyInfosql);

		String ownername = "";
		String ownerphone = "";
		int ownergender = 0;
		String owneridnumber = "";
		String owneraddress = "";
		int buyparkid = 0;
		String buystartparktime = "";
		String buyendparktime = "";
		String buystartparkdate = "";
		String buyendparkdate = "";
		double buymoney = 0.0;
		String deletetime = "";

		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectBuyInfosql);
		try {
			while (rSet.next()) {
				ownername = rSet.getString("ownername");
				ownerphone = rSet.getString("ownerphone");
				ownergender = Integer.parseInt(rSet.getString("ownergender"));
				owneridnumber = rSet.getString("owneridnumber");
				owneraddress = rSet.getString("owneraddress");
				buyparkid = Integer.parseInt(rSet.getString("buyparkid"));
				buystartparktime = rSet.getString("buystartparktime");
				buyendparktime = rSet.getString("buyendparktime");
				buystartparkdate = rSet.getString("buystartparkdate");
				buyendparkdate = rSet.getString("buyendparkdate");
				buymoney = Double.parseDouble(rSet.getString("buymoney"));
				deletetime = CreateDate.getDate();
				Table_BuyInfoBackup buyInfoBackup = new Table_BuyInfoBackup(adminId, parklotName, ownername, ownerphone,
						ownergender, owneridnumber, owneraddress, buyparkid, buystartparktime, buyendparktime,
						buystartparkdate, buyendparkdate, buymoney, createdTime, deletetime);
				SysoUtils.print(buyInfoBackup.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String insertBuyInfoBackupSql = "INSERT INTO table_buyinfobackup VALUES('" + adminId + "','" + parklotName
				+ "','" + ownername + "','" + ownerphone + "'," + ownergender + ",'" + owneridnumber + "','"
				+ owneraddress + "'," + buyparkid + "," + buystartparktime + "," + buyendparktime + ",'"
				+ buystartparkdate + "','" + buyendparkdate + "'," + buymoney + ",'" + createdTime + "','" + deletetime
				+ "');";
		String deleteBuyInfoSql = "delete from table_buyinfo where" + " buycreatedtime = '" + createdTime
				+ "' and parkadminid = '" + adminId + "' ;";
		String deleteOwnerInfoSql = "delete from table_ownerinfo where ownercreatedtime = '" + createdTime
				+ "' and parkadminid = '" + adminId + "' ;";
		SysoUtils.print("insertBuyInfoBackupSql:" + insertBuyInfoBackupSql);
		SysoUtils.print("deleteBuyInfoSql:" + deleteBuyInfoSql);
		SysoUtils.print("deleteOwnerInfoSql:" + deleteOwnerInfoSql);

		int insertBuyInfoBackupResult = dbBean.executeUpdate(insertBuyInfoBackupSql);
		int deleteBuyInfoResult = dbBean.executeUpdate(deleteBuyInfoSql);
		int deleteOwnerInfoResult = dbBean.executeUpdate(deleteOwnerInfoSql);

		if (insertBuyInfoBackupResult == 1 && deleteBuyInfoResult == 1 && deleteOwnerInfoResult == 1) {
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
		try {
			rSet.close();
			dbBean.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
