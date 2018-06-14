package com.dyf.dao.parklotadmin.inout;

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
import com.dyf.utils.Count;
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "新增离开停车场信息，同时将记录移动到备份表", urlPatterns = { "/AddOutInfoDao" })
public class AddOutInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddOutInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddOutInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String outDatetime = CreateDate.getDate();
		String parkAdminId = (String) session.getAttribute("userid");
		String parklotName = InOutUtil.getParklotName(parkAdminId);

		Boolean isBuy = false; // 标记是否是已购买停车位
		Boolean isReserve = false; // 标记是否是已预订停车位

		DBBean dbBean2 = new DBBean();
		String carid = request.getParameter("carid");

		// 出去之前先查询一下是不是已经购买了停车位的车
		String selectisbuy = "select ownerphone from table_ownerinfo where ownerplatenum = '" + carid + "'";
		ResultSet rSet = dbBean2.executeQuery(selectisbuy);
		try {
			if (rSet.next()) {
				isBuy = true;
				rSet.close();
				out.println("<script type='text/javascript'> alert('该车主已购买停车位。');</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 再查询一下是不是已经预定了停车位
		if (!isBuy) {
			String selectisreserve = "select startdate,starttime, enddate,endtime from table_qquserreserve where parklotname='"+parklotName+"' and openid=(select openid from table_qquserinfo where platenum='"
					+ carid + "') group by enddate desc,endtime desc limit 1";
			SysoUtils.print("离开时，查询是否预定了停车位："+selectisreserve);
			ResultSet rSet2 = dbBean2.executeQuery(selectisreserve);
			try {
				while (rSet2.next()) {
					out.println("<script type='text/javascript'> alert('该车主已预定停车位。');</script>");

					String startDate = rSet2.getString("startdate");
					String startTime = rSet2.getString("starttime");
					String endDate = rSet2.getString("enddate");
					String endTime = rSet2.getString("endtime");
					SysoUtils.print("离开时间outdatetime：" + outDatetime);
					String outHour = outDatetime.substring(11, 13);
					String outMinute = outDatetime.substring(14, 16);
					if (endDate.equals(outDatetime.substring(0, 10))) {
						if (Integer.parseInt(outHour) < Integer.parseInt(endTime.substring(0, 2))
								|| (outHour.equals(endTime.substring(0, 2))
										&& Integer.parseInt(outMinute) < Integer.parseInt(endTime.substring(3, 5)))) {
							out.println("<script type='text/javascript'> alert('该车主已预定，未超时。');</script>");
						} else {
							String startDateTime = endDate + " " + endTime + ":00";
							double moreMoney = Count.getCostMoney(startDateTime, outDatetime);
							String insertSQL = "insert into table_qquserrecord (openid,optionname,rechargenum,operatetime) values((select openid from table_qquserinfo where platenum='"
									+ carid + "'),'2'," + moreMoney + ",'" + outDatetime + "');";
							String updateSQL = "update table_qquserbalance set balance = balance-" + moreMoney
									+ " where openid = (select openid from table_qquserinfo where platenum='" + carid
									+ "');";
							int i = dbBean2.executeUpdate(insertSQL);
							int j = dbBean2.executeUpdate(updateSQL);
							if (i == 1 && j == 1) {
								out.println("<script type='text/javascript'> alert('已预订，停车超时,已自动扣费"
										+ String.valueOf(moreMoney) + "元。');</script>");
							}
						}
						isReserve = true; // 已预订停车位
					} else {
						out.println("<script type='text/javascript'> alert('该车主未预定停车位。');</script>");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!isBuy && !isReserve) {
			// 查询车牌号进来的时间以进行计费
			String selectintime = "select indatetime from table_inoutinfo  where parklotname='"+parklotName+"' and carid = '" + carid + "'";
			ResultSet rSet2 = dbBean2.executeQuery(selectintime);
			try {
				while (rSet2.next()) {
					String intime = rSet2.getString("indatetime");
					double cost = Count.getCostMoney(intime, outDatetime);
					out.println("<script type='text/javascript'> alert('未购买，未预定，应支付：" + String.valueOf(cost)
							+ " 元 ');</script>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// int inOrOut = 1;
		// String parkId = request.getParameter("parkid");
		
		SysoUtils.print("carid=" + carid + ",outdatetime=" + outDatetime);

		String updateSql = "update table_inoutinfo set outdatetime='" + outDatetime + "' where parklotname='"+parklotName+"' and carid='" + carid
				+ "' limit 1";
		String copySql = "insert into table_inoutinfobackup ( SELECT * FROM table_inoutinfo where parklotname='"+parklotName+"' and carid='" + carid
				+ "' LIMIT 1)";
		String deleteSql = "delete from table_inoutinfo where parklotname='"+parklotName+"' and carid='" + carid + "' limit 1";
		SysoUtils.print("updateSql=" + updateSql);
		SysoUtils.print("copySql=" + copySql);
		SysoUtils.print("deleteSql=" + deleteSql);

		DBBean dbBean = new DBBean();
		int i = dbBean.executeUpdate(updateSql);
		int j = dbBean.executeUpdate(copySql);
		int k = dbBean.executeUpdate(deleteSql);
		if (i == 1 && j == 1 && k == 1) {
			SysoUtils.print("添加离开信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewOut.jsp");
		} else {
			SysoUtils.print("添加离开信息失败。");
			out.println("<script type='text/javascript'> alert('添加失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewOut.jsp");
		}
		dbBean.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
