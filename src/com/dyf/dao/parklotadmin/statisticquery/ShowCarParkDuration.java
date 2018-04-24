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
import com.dyf.utils.Count;
import com.dyf.utils.SysoUtils;

import net.sf.json.JSONObject;

@WebServlet(description = "停车时长分布", urlPatterns = { "/ShowCarParkDuration" })
public class ShowCarParkDuration extends HttpServlet {

	private static final long serialVersionUID = 4317691927421705890L;
	
	public ShowCarParkDuration() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入ShowCarParkDuration");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=UTF-8");
		HttpSession session = request.getSession();
		String adminid = (String) session.getAttribute("userid");
		SysoUtils.print("登录用户id：" + adminid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<TimeNum> timeNums = new ArrayList<TimeNum>();
		int[] minArr = {0,0,0,0,0,0,0,0};
		int minPosi= 0;
		
		String selectSQL = "select indatetime intime,outdatetime outtime from table_inoutinfobackup where parkadminid = '"+adminid+"'";
		DBBean dbBean = new DBBean();
		ResultSet rSet = dbBean.executeQuery(selectSQL);
		try {
			while(rSet.next()){
				String inTime = rSet.getString("intime");
				String outTime = rSet.getString("outtime");
				long minute = Count.minuteDiff(inTime, outTime, "yyyy-MM-dd HH:mm:ss");
				minPosi = Count.getMinPosi(minute);
				minArr[minPosi]++;
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
		for(int i = 0;i<minArr.length;i++) {
			SysoUtils.print("停车时长和数量分别为："+i+", "+minArr[i]);
			TimeNum timeNum = new TimeNum(minArr[i], i );
			timeNums.add(timeNum);
		}
		map.put("data", timeNums);
		JSONObject jsonObject = JSONObject.fromObject(map);
		SysoUtils.print(jsonObject);
		response.getWriter().println(jsonObject);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
