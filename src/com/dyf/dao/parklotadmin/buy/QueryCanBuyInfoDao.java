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
import com.dyf.model.CanBuyInfo;
import com.dyf.utils.BuyUtil;
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
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

		// 获取查询条件
		String selectKey1 = request.getParameter("selectkey1");
		String selectValue1 = request.getParameter("selectvalue1");
		String selectKey2 = request.getParameter("selectkey2");
		String selectValue2 = request.getParameter("selectvalue2");
		String selectValue3 = request.getParameter("selectvalue3");
		SysoUtils.print("selectkey1:" + selectKey1 + " selectvalue1:" + selectValue1);
		SysoUtils.print("selectkey2:" + selectKey2 + " selectvalue2:" + selectValue2 + " selectvalue3:" + selectValue3);
		int selectValueInt2 = 25;
		int selectValueInt3 = 25;
		if ( selectValue2!=null && selectValue2!="" && selectValue3!=null &&selectValue3!="") {
			selectValueInt2 = Integer.parseInt(selectValue2);
			selectValueInt3 = Integer.parseInt(selectValue3);
		}
		SysoUtils.print("selectValueInt2:"+selectValueInt2+" selectValueInt3:"+selectValueInt3);

		String whereSql = "";
		// 判断第一个查询框的值，拼接查询sql
		if (selectValue1 != null && selectValue1 != "") {
			whereSql = " and buyparkid = " + selectValue1;
		}

		// 判断第二个查询框的值，拼接查询sql
		/*
		 * if (selectKey2 != null && selectValue2 != null && selectValue2 != ""
		 * && selectValue3 != null && selectValue3 != "") { whereSql =
		 * " and buystartparktime between '" + selectValue2 + "' and '
		 * " + CreateDate.getDate() + "'"; }
		 */

		int[][] parkTimeArray = null; // 获取车位号已经停车时间的二维数组，比如3,6 12,23
		int countRowNum = 0; // 获得比如5号车位已经有几行的购买数据，为下面时间排序用的
		int parklotAmount = 0; // 停车场车位数量，需要从第一个开始遍历车位号，获得每个车位号的已经停车时间进行排序
		String parkTime = ""; // 将车位可供购买的时间，拼接成一个字符串，比如8点—9点 12点—14点 18点—20点
		List<CanBuyInfo> canBuyInfos = new ArrayList<CanBuyInfo>();

		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("userid");
		String parklotName = "";
		parklotName = InOutUtil.getParklotName(adminId);
		parklotAmount = InOutUtil.getParkAmount(adminId);

		// 如果是查询停车场全部停车位的时间时
		if ( (selectValue1==null||selectValue1=="") && selectValueInt2 == 25 && selectValueInt3 == 25) {
			SysoUtils.print("进入第一个判断");
			// 遍历停车场车位号，得出停车时间
			for (int parkid = 1; parkid <= parklotAmount; parkid++) {
				// 先获得数组的行数，也就是查询出的数据的行数
				String countSql = "select count(*) as countrownum from table_buyinfo where parkadminid='" + adminId
						+ "' and buyparkid=" + parkid;
				SysoUtils.print("第一个countSql:" + countSql);
				DBBean dbBean = new DBBean();
				ResultSet rSet = dbBean.executeQuery(countSql);
				try {
					while (rSet.next()) {
						countRowNum = rSet.getInt("countrownum");
						SysoUtils.print(parkid + "车位号的行数：" + countRowNum);
						if (countRowNum != 0) {
							parkTimeArray = new int[countRowNum][2];
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rSet.close();
						dbBean.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (countRowNum != 0) {
					// 从数据库中查询车位号已经停车的时间，然后为数组赋值，
					String selectSql = "select buyparkid,buystartparktime,buyendparktime from table_buyinfo where parkadminid='"
							+ adminId + "' and buyparkid=" + parkid;

					// + "' and buyparkid=5";
					SysoUtils.print("selectSql:" + selectSql);

					DBBean dbBean2 = new DBBean();
					ResultSet rSet2 = dbBean2.executeQuery(selectSql);
					int i = 0;
					try {
						while (rSet2.next() && i < parkTimeArray.length) {
							parkTimeArray[i][0] = Integer.parseInt(rSet2.getString("buystartparktime"));
							parkTimeArray[i][1] = Integer.parseInt(rSet2.getString("buyendparktime"));
							i++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							rSet2.close();
							dbBean2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// 停车位已经停车的时间赋值到了数组里，需要对数组进行从小到大的排序
					parkTimeArray = BuyUtil.parkTimeSort(parkTimeArray);
					parkTime = "";
					// 将剩余可以停车的时间连接起来，凑成一个string parktime
					if (parkTimeArray[parkTimeArray.length - 1][1] != parkTimeArray[0][0]) {
						parkTime += parkTimeArray[parkTimeArray.length - 1][1] + "点—" + parkTimeArray[0][0] + "点  ";
					}
					for (int a = 0; a < parkTimeArray.length - 1; a++) {
						if (parkTimeArray[a][1] != parkTimeArray[a + 1][0]) {
							parkTime += parkTimeArray[a][1] + "点—" + parkTimeArray[a + 1][0] + "点  ";
						}
					}
					SysoUtils.print(parkid + "可供购买停车时间parkTime:" + parkTime);
				} else {
					parkTime = "";
					parkTime = "全天可购买";
				}

				CanBuyInfo canBuyInfo = new CanBuyInfo(parkid, parkTime, adminId, parklotName);
				canBuyInfos.add(canBuyInfo);

			}
		} else if (selectValue1 != null && selectValue1 != "" && selectValueInt2 == 25 && selectValueInt3 == 25) {
			SysoUtils.print("进入第二个判断，查询指定停车位号：");
			// 查询指定车位的时间
			// 先获得数组的行数，也就是查询出的数据的行数
			String countSql = "select count(*) as countrownum from table_buyinfo where parkadminid='" + adminId+"' "+whereSql;
			SysoUtils.print("第二个countSql:" + countSql);
			DBBean dbBean = new DBBean();
			ResultSet rSet = dbBean.executeQuery(countSql);
			try {
				while (rSet.next()) {
					countRowNum = rSet.getInt("countrownum");
					SysoUtils.print(selectValue1 + "车位号的行数：" + countRowNum);
					if (countRowNum != 0) {
						parkTimeArray = new int[countRowNum][2];
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rSet.close();
					dbBean.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (countRowNum != 0) {
				// 从数据库中查询车位号已经停车的时间，然后为数组赋值，
				String selectSql = "select buyparkid,buystartparktime,buyendparktime from table_buyinfo where parkadminid='"
						+ adminId + "' "+whereSql;
				SysoUtils.print("selectSql:" + selectSql);

				DBBean dbBean2 = new DBBean();
				ResultSet rSet2 = dbBean2.executeQuery(selectSql);
				int i = 0;
				try {
					while (rSet2.next() && i < parkTimeArray.length) {
						parkTimeArray[i][0] = Integer.parseInt(rSet2.getString("buystartparktime"));
						parkTimeArray[i][1] = Integer.parseInt(rSet2.getString("buyendparktime"));
						i++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rSet2.close();
						dbBean2.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				// 停车位已经停车的时间赋值到了数组里，需要对数组进行从小到大的排序
				parkTimeArray = BuyUtil.parkTimeSort(parkTimeArray);
				parkTime = "";

				// 将剩余可以停车的时间连接起来，凑成一个string parktime
				if (parkTimeArray[parkTimeArray.length - 1][1] != parkTimeArray[0][0]) {
					parkTime += parkTimeArray[parkTimeArray.length - 1][1] + "点—" + parkTimeArray[0][0] + "点  ";
				}
				for (int a = 0; a < parkTimeArray.length - 1; a++) {
					if (parkTimeArray[a][1] != parkTimeArray[a + 1][0]) {
						parkTime += parkTimeArray[a][1] + "点—" + parkTimeArray[a + 1][0] + "点  ";
					}
				}
				SysoUtils.print(selectValue1 + "可供购买停车时间parkTime:" + parkTime);
			} else {
				parkTime = "";
				parkTime = "全天可购买";
			}

			CanBuyInfo canBuyInfo = new CanBuyInfo(Integer.parseInt(selectValue1), parkTime, adminId, parklotName);
			canBuyInfos.add(canBuyInfo);
		} else if (selectValue1=="" && selectValueInt2 != 25
				&& selectValueInt3 != 25) {
			SysoUtils.print("进入第三个判断，根据指定停车时间段进行查询：");

			// 遍历停车场车位号，得出停车时间
			for (int parkid = 1; parkid <= parklotAmount; parkid++) {
				// 先获得数组的行数，也就是查询出的数据的行数
				String countSql = "select count(*) as countrownum from table_buyinfo where parkadminid='" + adminId+ "' " + whereSql;
				SysoUtils.print("第三个countSql:" + countSql);
				DBBean dbBean = new DBBean();
				ResultSet rSet = dbBean.executeQuery(countSql);
				try {
					while (rSet.next()) {
						countRowNum = rSet.getInt("countrownum");
						SysoUtils.print(parkid + "车位号的行数：" + countRowNum);
						if (countRowNum != 0) {
							parkTimeArray = new int[countRowNum][2];
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rSet.close();
						dbBean.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (countRowNum != 0) {
					// 从数据库中查询车位号已经停车的时间，然后为数组赋值，
					String selectSql = "select buyparkid,buystartparktime,buyendparktime from table_buyinfo where parkadminid='"
							+ adminId + "' and buyparkid=" + parkid;

					// + "' and buyparkid=5";
					SysoUtils.print("selectSql:" + selectSql);

					DBBean dbBean2 = new DBBean();
					ResultSet rSet2 = dbBean2.executeQuery(selectSql);
					int i = 0;
					try {
						while (rSet2.next() && i < parkTimeArray.length) {
							parkTimeArray[i][0] = Integer.parseInt(rSet2.getString("buystartparktime"));
							parkTimeArray[i][1] = Integer.parseInt(rSet2.getString("buyendparktime"));
							i++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							rSet2.close();
							dbBean2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// 停车位已经停车的时间赋值到了数组里，需要对数组进行从小到大的排序
					parkTimeArray = BuyUtil.parkTimeSort(parkTimeArray);
					// 得到可以停车时间数组
					parkTimeArray = BuyUtil.getCanParkTime(parkTimeArray);

					boolean flag = false; // 查询指定停车时间，如果有符合指定时间的，就放进数组
					// 可停车时间排序之后，进行条件查询，把选定停车时间之内的停车位显示出来
					for (int b = 0; b < parkTimeArray.length; b++) {
						flag = false;
						if (selectValueInt2 >= parkTimeArray[b][0] && selectValueInt3 <= parkTimeArray[b][1]) {
							flag = true;
							break;
						}
					}

					if (flag) {
						parkTime = "";
						// 将剩余可以停车的时间连接起来，凑成一个string parktime
						for (int a = 0; a < parkTimeArray.length - 1; a++) {
							if (parkTimeArray[a][0] != parkTimeArray[a][1]) {
								parkTime += parkTimeArray[a][0] + "点—" + parkTimeArray[a][1] + "点  ";
							}
						}
						/*
						 * if (parkTimeArray[parkTimeArray.length - 1][1] !=
						 * parkTimeArray[0][0]) { parkTime +=
						 * parkTimeArray[parkTimeArray.length - 1][1] + "点—" +
						 * parkTimeArray[0][0] + "点  "; } for (int a = 0; a <
						 * parkTimeArray.length - 1; a++) { if
						 * (parkTimeArray[a][1] != parkTimeArray[a + 1][0]) {
						 * parkTime += parkTimeArray[a][1] + "点—" +
						 * parkTimeArray[a + 1][0] + "点  "; } }
						 */
						SysoUtils.print(parkid + "可供购买停车时间parkTime:" + parkTime);
						CanBuyInfo canBuyInfo = new CanBuyInfo(parkid, parkTime, adminId, parklotName);
						canBuyInfos.add(canBuyInfo);
					}

				}

			}

		}

		request.setAttribute("canBuyInfos", canBuyInfos);
		request.getRequestDispatcher("/Parklotadmin/Buy/ShowCanBuyInfo.jsp").forward(request, response);

		/*
		 * for (int j = 0; j < parkTimeArray.length; j++) { for (int k = 0; k <
		 * parkTimeArray[j].length; k++) { System.out.print(parkTimeArray[j][k]+
		 * " "); } System.out.println(); }
		 */

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
