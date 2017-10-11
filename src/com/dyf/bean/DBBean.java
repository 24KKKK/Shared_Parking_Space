package com.dyf.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBBean {
	
	private String driverStr = "com.mysql.jdbc.Driver";
	private String connStr = "jdbc:mysql://localhost:3306/db_SharedParkingSpace";
	private String dbusername = "root";
	private String dbpassword = "root";
	private Connection conn = null;
	private Statement stmt = null;

	public DBBean() {
		try {
			Class.forName(driverStr);
			conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
			stmt = conn.createStatement();
			System.out.println("连接数据库成功！" );
		} catch (Exception ex) {
			System.err.println("连接数据库失败 \n错误原因： "+ex.getMessage() );
		}
	}

	public int executeUpdate(String s) {
		int result = 0;
		try {
			result = stmt.executeUpdate(s);
		} 
		catch (Exception ex) 
		{
			System.err.println("执行更新错误\n错误原因：  "+ex.getMessage());
		}
		return result;
	}

	public ResultSet  executeQuery(String s) {
		ResultSet rs = null;
		try {
			
			rs = stmt.executeQuery(s);
		}
		catch (Exception ex) {
			System.err.println("执行查询错误ִ\n错误原因： "+ex.getMessage());
		}
		return rs;
	}

	public void close() {
		try {
			stmt.close();
			conn.close();
		} 
		catch (Exception e) {
			System.err.println("关闭失败ִ\n错误原因： "+e.getMessage());
		}
		System.out.println("已断开与数据库的连接！" );
	}

}
