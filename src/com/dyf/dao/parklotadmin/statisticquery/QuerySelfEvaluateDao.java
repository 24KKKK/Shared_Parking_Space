package com.dyf.dao.parklotadmin.statisticquery;

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
import com.dyf.model.Table_Evaluate;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "查看自己停车场的评价信息", urlPatterns = { "/QuerySelfEvaluateDao" })
public class QuerySelfEvaluateDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QuerySelfEvaluateDao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SysoUtils.print("进入QuerySelfEvaluateDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String adminid = (String) session.getAttribute("userid");
		
		double num = 0;
		double sumScore = 0;
		double avg = 0;
		List<Table_Evaluate> evaluates = new ArrayList<Table_Evaluate>();
		DBBean db = new DBBean();
		String querySql = "select score,content,createtime from table_evaluate where parklotname = (select parklotname from table_parklotinfo where parklotAdminId = '"+adminid+"')";
		SysoUtils.print("querySql:" + querySql);
		ResultSet rs = db.executeQuery(querySql);
		try {
			while (rs.next()) {
				String content = rs.getString("content");
				int score = rs.getInt("score");
				String createdTime = rs.getString("createtime");
				num++;
				sumScore+=score;
				SysoUtils.print(content + " " + score +" "+ createdTime);
				Table_Evaluate table_evaluate = new Table_Evaluate(score, content, createdTime);;
				evaluates.add(table_evaluate);
			}
			if (num != 0) {
				avg = sumScore/num;
				SysoUtils.print("num  sumScore  avg:"+num+" "+sumScore+" "+avg);
				request.setAttribute("avg", avg);
			}
			request.setAttribute("evaluates", evaluates);
			request.getRequestDispatcher("/Parklotadmin/StatisticQuery/ShowSelfEvaluate.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
