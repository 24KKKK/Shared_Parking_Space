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
import com.dyf.utils.CreateDate;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "停车场进入信息登记", urlPatterns = { "/AddInInfoDao" })
public class AddInInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddInInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入AddInInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String carid = request.getParameter("carid");
		//int inOrOut = 1;
		String parkId = request.getParameter("parkid");
		String inDatetime = CreateDate.getDate();
		String parkAdminId = (String) session.getAttribute("userid");
		String parklotName = null;
		parklotName = InOutUtil.getParklotName(parkAdminId);
		SysoUtils.print("carid=" + carid + ",parkid=" + parkId + ",indatetime=" + inDatetime + ",parkAdminId="
				+ parkAdminId + ",parklotName=" + parklotName);
		DBBean dbBean = new DBBean();
		String insertSql = "insert into table_inoutinfo(carid,indatetime,parkid,parkadminid,parklotname) values ( "
							+"'"+carid+"','"+inDatetime+"',"+parkId+",'"+parkAdminId+"',"+"'"+parklotName+"')";
		SysoUtils.print("insertSql="+insertSql);
		// 查询进来的车是不是预定车位的
		String selectSql = "select startdate,starttime,enddate,endtime from table_qquserreserve where parklotname = '"+parklotName+"' and openid = (select openid from table_qquserinfo where platenum = '"+carid+"') group by startdate desc,starttime desc limit 1;";
		SysoUtils.print("查询进来的车是不是预定车位的:"+selectSql);
		ResultSet rSet = dbBean.executeQuery(selectSql);
		String startdate = "";
		String starttime = "";
		String enddate = "";
		String endtime = "";
		boolean isRes = false;
		try {
			while(rSet.next()){
				 startdate = rSet.getString("startdate");
				 starttime = rSet.getString("starttime");
				 enddate = rSet.getString("enddate");
				 endtime = rSet.getString("endtime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (startdate.equals(inDatetime.substring(0, 10))) {
			if ((Integer.parseInt(starttime.substring(0, 2))<Integer.parseInt(inDatetime.substring(11, 13))) || ((Integer.parseInt(starttime.substring(0, 2))==Integer.parseInt(inDatetime.substring(11, 13)))&&(Integer.parseInt(starttime.substring(3, 5))<=Integer.parseInt(inDatetime.substring(14, 16))))) {
				isRes = true;
			}
		}
		
		int i = dbBean.executeUpdate(insertSql);
		try {
			rSet.close();
			dbBean.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//boolean flag = false;
		if (i == 1 && isRes == false) {
			//flag = true;
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加入场信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功，临时车辆');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.jsp");
		} else if (i == 1 && isRes == true ) {
			PrintWriter out = response.getWriter();
			SysoUtils.print("添加入场信息成功。");
			out.println("<script type='text/javascript'> alert('添加成功,是预定车位车辆,离开时间为："+enddate+" "+endtime+",请停至"+parkId+"号车位。"+"');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.jsp");
		} else {
			//flag = true;
			SysoUtils.print("添加入场信息失败。");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('添加失败');</script>");
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.jsp");
		}
		
		
			 /*else {
				SysoUtils.print("添加入场信息失败。");
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'> alert('添加失败');</script>");
				response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/InOut/NewIn.jsp");
			}*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
