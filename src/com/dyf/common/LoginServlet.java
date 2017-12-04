package com.dyf.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysoUtils.print("用户名是："+username.toString());
		SysoUtils.print("密码是："+password.toString());
		if (username.equals("admin")&&password.equals("admin")) {
			response.setHeader("refresh", "0;url=../Systemadmin/index.html");
		} else {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
