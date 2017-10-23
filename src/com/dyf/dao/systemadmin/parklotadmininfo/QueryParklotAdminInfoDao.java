package com.dyf.dao.systemadmin.parklotadmininfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dyf.bean.DBBean;
import com.dyf.model.Table_ParklotAdminInfo;
import com.dyf.utils.SysoUtils;

/**
 * Servlet implementation class QueryParklotAdminInfo
 */
public class QueryParklotAdminInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryParklotAdminInfoDao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		//获取分页传过来的页码号
		String strPageNum = request.getParameter("pagenum");
		int pageNum = 0;
		if (strPageNum!=null) {
			SysoUtils.print("页码号：" + strPageNum);
			pageNum = Integer.parseInt(strPageNum);
		}
		
		List<Table_ParklotAdminInfo> parklotAdminInfos = new ArrayList<Table_ParklotAdminInfo>();
		DBBean db = new DBBean();
		String querySql = "select parklotAdminId,parklotAdminPhone,parklotAdminAge,"
				+ "parklotAdminIdnumber,parklotAdminName,parklotAdminLoginId,"
				+ "parklotAdminLoginPass,parklotAdminCreatedTime from table_parklotadmininfo limit "+ pageNum +",7";
		SysoUtils.print("querySql:" + querySql);
		ResultSet rs = db.executeQuery(querySql);
		try {
			while(rs.next()) {
				String id = rs.getString("parklotAdminId");
				String phone = rs.getString("parklotAdminPhone");
				String str_age = rs.getString("parklotAdminAge");
				int age = Integer.parseInt(str_age);
				String idnumber = rs.getString("parklotAdminIdnumber");
				String name = rs.getString("parklotAdminName");
				String loginid = rs.getString("parklotAdminLoginId");
				String loginpass = rs.getString("parklotAdminLoginPass");
				String createdTime = rs.getString("parklotAdminCreatedTime");
				SysoUtils.print(id+" "+phone+" "+age+" "+idnumber+" "+name+" "+loginid+" "+loginpass+" "+createdTime);
				Table_ParklotAdminInfo table_ParklotAdminInfo = new Table_ParklotAdminInfo(id,phone,age,idnumber,name,loginid,loginpass,createdTime);;
				parklotAdminInfos.add(table_ParklotAdminInfo);
			}
			request.setAttribute("parklotAdminInfos", parklotAdminInfos);
			request.getRequestDispatcher("/Systemadmin/ParkinglotAdminInfo/ShowParklotAdminInfo.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
