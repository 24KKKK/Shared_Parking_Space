package com.dyf.dao.parklotadmin.buy;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.utils.BuyUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "查看可供购买信息", urlPatterns = { "/QueryCanBuyInfoDao" })
public class QueryCanBuyInfoDao extends HttpServlet {

	private static final long serialVersionUID = -410953187185527157L;

	public QueryCanBuyInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入QueryCanBuyInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		int[][] parkTimeArray = null; // 获取车位号已经停车时间的数组，比如3,6 12,23
		int countRowNum = 0;

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");

		// 先获得数组的行数，也就是查询出的数据的行数
		String countSql = "select count(*) as countrownum from table_buyinfo where parkadminid='" + adminId
				+ "' and buyparkid=5";
		SysoUtils.print("countSql:" + countSql);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(countSql);
		try {
			while (rSet.next()) {
				countRowNum = rSet.getInt("countrownum");
				SysoUtils.print("车位号的行数：" + countRowNum);
				parkTimeArray = new int[countRowNum][2];
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 从数据库中查询车位号已经停车的时间，然后为数组赋值，
		String selectSql = "select buyparkid,buystartparktime,buyendparktime from table_buyinfo where parkadminid='" + adminId
				+ "' and buyparkid=5";
		
		//+ "' and buyparkid=5";
		SysoUtils.print("selectSql:" + selectSql);

		ResultSet rSet2 = dbBean.executeQuery(selectSql);
		int i = 0;
		try {
			while (rSet2.next() && i < parkTimeArray.length) {
				parkTimeArray[i][0] = Integer.parseInt(rSet2.getString("buystartparktime"));
				parkTimeArray[i][1] = Integer.parseInt(rSet2.getString("buyendparktime"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rSet2.close();
				dbBean.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//停车位已经停车的时间赋值到了数组里，需要对数组进行从小到大的排序
		parkTimeArray = BuyUtil.parkTimeSort(parkTimeArray);
		String parktime = "";
		if (parkTimeArray[parkTimeArray.length-1][1]!=parkTimeArray[0][0]) {
			parktime+=parkTimeArray[parkTimeArray.length-1][1]+"点——"+parkTimeArray[0][0]+"点  ";
		}
		for (int a = 0; a < parkTimeArray.length-1; a++) {
			if (parkTimeArray[a][1]!=parkTimeArray[a+1][0]) {
				parktime+=parkTimeArray[a][1]+"点——"+parkTimeArray[a+1][0]+"点  ";
			}
		}
		SysoUtils.print("parktime:"+parktime);
		
		/*for (int j = 0; j < parkTimeArray.length; j++) {
			for (int k = 0; k < parkTimeArray[j].length; k++) {
				System.out.print(parkTimeArray[j][k]+" ");
			}
			System.out.println();
		}*/

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
