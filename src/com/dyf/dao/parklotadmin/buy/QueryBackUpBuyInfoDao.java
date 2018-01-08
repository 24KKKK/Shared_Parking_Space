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
import com.dyf.model.Table_BuyInfoBackup;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "查看车位历史购买的信息", urlPatterns = { "/QueryBackUpBuyInfoDao" })
public class QueryBackUpBuyInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryBackUpBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入QueryBackUpBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		// 获取查询条件
		String selectKey1 = request.getParameter("selectkey1");
		String selectValue1 = request.getParameter("selectvalue1");
		SysoUtils.print("selectkey1:" + selectKey1 + " selectvalue1:" + selectValue1);
		// selectValue1.matches("\\d+");判断一个字符串能否转为数字，返回bool值
		String whereSql = "";
		// 判断第一个查询框的值，拼接查询sql
		if (selectValue1 != null && selectValue1 != "") {
			switch (selectKey1) {
			case "parkid":
				whereSql+=" and buyparkid = "+Integer.parseInt(selectValue1);
				break;
			case "ownername":
				whereSql+=" and ownername LIKE '%" + selectValue1 + "%'";
				break;
			case "ownerphone":
				whereSql+=" and ownerphone LIKE '%" + selectValue1 + "%'";
				break;

			default:
				break;
			}
		}

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		String parklotName = InOutUtil.getParklotName(adminId);
		List<Table_BuyInfoBackup> table_BuyInfoBackups = new ArrayList<Table_BuyInfoBackup>();

		String selectAllSql = "select ownername,ownerphone,ownergender,owneridnumber,owneraddress,buyparkid,buystartparktime,buyendparktime,buystartparkdate,buyendparkdate,buymoney,buycreatedtime,deletetime from table_buyinfobackup where parkadminid = '"+adminId+"'"+ whereSql;
		SysoUtils.print("查询历史购买信息的selectAllSql:" + selectAllSql);

		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectAllSql);
		try {
			int a = 0;
			while (rSet.next()) {
				a++;
				String ownername = rSet.getString("ownername");
				int ownergender = rSet.getInt("ownergender");
				String ownerphone = rSet.getString("ownerphone");
				String owneridnumber = rSet.getString("owneridnumber");
				String owneraddress = rSet.getString("owneraddress");
				int buyparkid = rSet.getInt("buyparkid");
				int buystartparktime = rSet.getInt("buystartparktime");
				int buyendparktime = rSet.getInt("buyendparktime");
				String buystartparkdate = rSet.getString("buystartparkdate");
				String buyendparkdate = rSet.getString("buyendparkdate");
				double buymoney = rSet.getDouble("buymoney");
				String buycreatedtime = rSet.getString("buycreatedtime");
				String deletetime = rSet.getString("deletetime");

				Table_BuyInfoBackup table_BuyInfoBackup = new Table_BuyInfoBackup(adminId, parklotName, ownername, ownerphone, ownergender, owneridnumber, owneraddress, buyparkid, buystartparktime, buyendparktime, buystartparkdate, buyendparkdate, buymoney, buycreatedtime, deletetime);
				table_BuyInfoBackups.add(table_BuyInfoBackup);
			}
			SysoUtils.print("querybuyinfo结果集循环次数："+a);
			request.setAttribute("table_BuyInfoBackups", table_BuyInfoBackups);
			request.getRequestDispatcher("/Parklotadmin/Buy/ShowBackUpBuyInfo.jsp").forward(request, response);
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
