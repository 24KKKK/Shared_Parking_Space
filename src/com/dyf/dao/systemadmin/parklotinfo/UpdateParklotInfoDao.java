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
import com.dyf.model.Table_ParklotInfo;
import com.dyf.utils.SysoUtils;

@WebServlet(description = "修改停车场信息", urlPatterns = { "/UpdateParklotInfoDao" })
public class UpdateParklotInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateParklotInfoDao() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SysoUtils.print("进入UpdateParklotInfoDao.java");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		SysoUtils.print("修改的修改name：" + name);

		DBBean db = new DBBean();
		String updateSql = "select parklotName, parklotAmount,parklotLng,parklotLat,"
				+ "parklotAdminId,parklotDescription,parklotCreatedTime from table_parklotinfo "
				+ "where parklotName = " + "'" + name + "'";
		SysoUtils.print("修改的修改updateSql为：" + updateSql);
		ResultSet rs = db.executeQuery(updateSql);
		List<Table_ParklotInfo> updateParklotInfos = new ArrayList<Table_ParklotInfo>();
		try {
			SysoUtils.print("进入try");

			while (rs.next()) {
				// String name = rs.getString("parklotName");
				int amount = rs.getInt("parklotAmount");
				String lng = rs.getString("parklotLng");
				String lat = rs.getString("parklotLat");
				String adminId = rs.getString("parklotAdminId");
				String description = rs.getString("parklotDescription");
				String createdTime = rs.getString("parklotCreatedTime");
				SysoUtils.print(",可以修改的信息为：" + amount + " " + lng + " " + lat + " " + adminId + " " + description);
				Table_ParklotInfo table_parklotInfo = new Table_ParklotInfo(name, amount, lng, lat, description,
						adminId, createdTime);
				updateParklotInfos.add(table_parklotInfo);
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("updateParklotInfos", updateParklotInfos);
			request.getRequestDispatcher("/Systemadmin/ParklotInfo/UpdateParklotInfo.jsp").forward(request, response);
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
