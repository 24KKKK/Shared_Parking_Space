package com.dyf.dao.systemadmin.parklotadmininfo;

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
import com.dyf.utils.SysoUtils;

@WebServlet(name = "UpdateParklotAdminInfoDao", urlPatterns = {
		"/UpdateParklotAdminInfoDao" }, description = "修改停车场管理员信息")
public class UpdateParklotAdminInfoDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
		SysoUtils.print("修改的修改id：" + id);

		DBBean db = new DBBean();
		String sql = "select parklotAdminId, parklotAdminPhone,parklotAdminAge,parklotAdminIdnumber,"
				+ "parklotAdminName,parklotAdminLoginId,parklotAdminLoginPass,parklotAdminCreatedTime from table_parklotadmininfo "
				+ "where parklotAdminId = " + "'" + id + "'";
		SysoUtils.print("修改的修改sql为：" + sql);
		ResultSet rs = db.executeQuery(sql);
		List<Table_ParklotAdminInfo> updateParklotAdminInfos = new ArrayList<Table_ParklotAdminInfo>();
		try {
			SysoUtils.print("进入try");

			while (rs.next()) {
				// String id = rs.getString("parklotAdminId");
				String phone = rs.getString("parklotAdminPhone");
				String str_age = rs.getString("parklotAdminAge");
				int age = Integer.parseInt(str_age);
				String idnumber = rs.getString("parklotAdminIdnumber");
				String name = rs.getString("parklotAdminName");
				String loginid = rs.getString("parklotAdminLoginId");
				String loginpass = rs.getString("parklotAdminLoginPass");
				String createdTime = rs.getString("parklotAdminCreatedTime");
				SysoUtils.print(",可以修改的信息为：" + phone + " " + age + " " + idnumber + " " + name + " " + loginid + " "
						+ loginpass + " ");
				Table_ParklotAdminInfo table_ParklotAdminInfo = new Table_ParklotAdminInfo(id, phone, age, idnumber,
						name, loginid, loginpass, createdTime);
				updateParklotAdminInfos.add(table_ParklotAdminInfo);
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("updateParklotAdminInfos", updateParklotAdminInfos);
			request.getRequestDispatcher("/Systemadmin/ParkinglotAdminInfo/UpdateParklotAdminInfo.jsp").forward(request,
					response);
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
