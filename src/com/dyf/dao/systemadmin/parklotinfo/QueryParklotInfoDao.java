package com.dyf.dao.systemadmin.parklotinfo;

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

import com.dyf.bean.DBBean;
import com.dyf.model.Table_ParklotAdminInfo;
import com.dyf.model.Table_ParklotInfo;
import com.dyf.utils.SysoUtils;

/**
 * Servlet implementation class QueryParklotInfoDao
 */
@WebServlet(description = "查询停车场信息", urlPatterns = { "/QueryParklotInfoDao" })
public class QueryParklotInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryParklotInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SysoUtils.print("进入QueryParklotInfoDao.java");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		// 获取分页传过来的页码号
		String strPageNum = request.getParameter("pagenum");
		int pageNum = 1;
		if (strPageNum != null) {
			SysoUtils.print("页码号：" + strPageNum);
			pageNum = Integer.parseInt(strPageNum);
		}

		// 获取查询条件
		String selectKey = request.getParameter("selectkey");
		String selectValue = request.getParameter("selectvalue");
		SysoUtils.print("selectkey:" + selectKey + " selectvalue:" + selectValue);
		String whereSql = "";
		if (selectKey != null && selectValue != null) {
			switch (selectKey) {
			case "parklotname":
				SysoUtils.print(selectKey + "  " + selectValue);
				whereSql = "where parklotName LIKE '%" + selectValue + "%'";
				break;
			case "parklotadminid":
				whereSql = "where parklotAdminId LIKE '%" + selectValue + "%'";
				break;
			case "parklotamount":
				whereSql = "where parklotAmount=" + selectValue + " ";
				break;

			default:
				break;
			}
		}

		List<Table_ParklotInfo> parklotInfos = new ArrayList<Table_ParklotInfo>();
		DBBean db = new DBBean();
		String querySql = "select parklotName,parklotAmount,parklotLng,"
				+ "parklotLat,parklotAdminId,parklotDescription," + "parklotCreatedTime from table_parklotinfo "
				+ whereSql + " group by parklotCreatedTime desc limit " + (pageNum - 1) * 6 + ",6";
		SysoUtils.print("querySql:" + querySql);
		ResultSet rs = db.executeQuery(querySql);
		try {
			while (rs.next()) {
				String name = rs.getString("parklotName");
				int amount = rs.getInt("parklotAmount");
				String lng = rs.getString("parklotLng");
				String lat = rs.getString("parklotLat");
				String adminId = rs.getString("parklotAdminId");
				String description = rs.getString("parklotDescription");
				String createdTime = rs.getString("parklotCreatedTime");
				SysoUtils.print(name + " " + amount + " " + lng + " " + lat + " " + adminId + " " + description + " "
						+ createdTime);
				Table_ParklotInfo table_ParklotInfo = new Table_ParklotInfo(name, amount, lng, lat, description,
						adminId, createdTime);
				;
				parklotInfos.add(table_ParklotInfo);
			}
			request.setAttribute("parklotInfos", parklotInfos);
			request.getRequestDispatcher("/Systemadmin/ParklotInfo/ShowParklotInfo.jsp").forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
