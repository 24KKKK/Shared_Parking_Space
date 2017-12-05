package com.dyf.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dyf.bean.DBBean;
import com.dyf.utils.SysoUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    /*
     * 对登录界面的值进行判断，admin直接进入系统管理员，如果不是admin，再进行数据库判断是否是停车场管理员，跳转相应页面
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysoUtils.print("用户名是："+username);
		SysoUtils.print("密码是："+password);
		
		if (username.equals("admin")&&password.equals("admin")) {
			//HttpSession session = request.getSession();
			//session.setAttribute("username", username);
			response.setHeader("refresh", "0;url=/Shared_Parking_Space/Systemadmin/index.html");
		} else {
			DBBean db = new DBBean();
			String selSql = "select parklotAdminId, parklotAdminName from table_parklotAdminInfo where parklotAdminLoginId="+"'"+username+"'"+" and parklotAdminLoginPass="+"'"+password+"'";
			SysoUtils.print("selsql="+selSql);
			ResultSet rs = db.executeQuery(selSql);
			int flag = 0;
			try {
				while(rs.next()){
					String adminId = rs.getString("parklotAdminId");
					String name = rs.getString("parklotAdminName");
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					session.setAttribute("userid", adminId);
					flag = 1;
					response.setHeader("refresh", "0;url=/Shared_Parking_Space/Parklotadmin/index.html");
				}
				rs.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag==0){
				PrintWriter out = response.getWriter();
				out.println("<script language='javaScript'> alert('用户名或密码错误,请重新登录!');</script>");
				response.setHeader("refresh", "0;url=/Shared_Parking_Space/login.html");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
