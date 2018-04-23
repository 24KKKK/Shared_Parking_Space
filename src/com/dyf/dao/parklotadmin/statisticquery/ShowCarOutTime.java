package com.dyf.dao.parklotadmin.statisticquery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.model.TimeNum;
import com.dyf.utils.InOutUtil;
import com.dyf.utils.SysoUtils;

import net.sf.json.JSONObject;

@WebServlet(description = "离开停车场车辆时段分布", urlPatterns = { "/ShowCarOutTime" })
public class ShowCarOutTime extends HttpServlet {

	private static final long serialVersionUID = 4682092563174117927L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入ShowCarOutTime");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		HttpSession session = request.getSession();
		String adminid = (String) session.getAttribute("userid");
		SysoUtils.print("登录用户id：" + adminid);
		String parkDate = request.getParameter("parkdate");
		SysoUtils.print("选择的日期为：" + parkDate);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<TimeNum> timeNums = new ArrayList<TimeNum>();

		String selectSQL = "select SUBSTRING(outdatetime,12,2) time,count(*) num from table_inoutinfobackup where parkadminid='"
				+ adminid + "' and SUBSTRING(outdatetime,1,10)='" + parkDate + "' GROUP BY time asc";
		SysoUtils.print("查询各时段的sql：" + selectSQL);
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSQL);
		
		try {
			while (rSet.next()) {
				int time = rSet.getInt("time");
				int num = rSet.getInt("num");
				TimeNum timeNum = new TimeNum(num, time);
				timeNums.add(timeNum);
			}
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
		map.put("data", timeNums);

		JSONObject jsonObject = JSONObject.fromObject(map);
		SysoUtils.print(jsonObject);
		response.getWriter().println(jsonObject);
		// return jsonObject;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
