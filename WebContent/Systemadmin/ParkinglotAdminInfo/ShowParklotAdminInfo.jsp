<%@page import="com.dyf.utils.Page"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.model.Table_ParklotAdminInfo"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta chartset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>显示停车场管理员信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<table class="table table-striped table-hover">
		<tr>
			<th>ID</th>
			<th>电话</th>
			<th>年龄</th>
			<th>身份证号</th>
			<th>姓名</th>
			<th>登录账号</th>
			<th>密码</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<%
			Object obj = request.getAttribute("parklotAdminInfos");
			List<Table_ParklotAdminInfo> parklotAdminInfoList = null;
			if (obj instanceof List) {
				parklotAdminInfoList = (List<Table_ParklotAdminInfo>) obj;
			}
			String id = null;
			String phone = null;
			int age = 0;
			String idnumber = null;
			String name = null;
			String loginId = null;
			String loginPass = null;
			String createdTime = null;
			if (parklotAdminInfoList != null) {
				for (Table_ParklotAdminInfo parklotAdminInfo : parklotAdminInfoList) {
					id = parklotAdminInfo.getParklotAdminId();
					phone = parklotAdminInfo.getParklotAdminId();
					age = parklotAdminInfo.getParklotAdminAge();
					idnumber = parklotAdminInfo.getParklotAdminIdnumber();
					name = parklotAdminInfo.getParklotAdminName();
					loginId = parklotAdminInfo.getParklotAdminLoginId();
					loginPass = parklotAdminInfo.getParklotAdminLoginPass();
					createdTime = parklotAdminInfo.getParklotAdminCreatedTime();
		%>

		<tr>
			<td><%=id%></td>
			<td><%=phone%></td>
			<td><%=age%></td>
			<td><%=idnumber%></td>
			<td><%=name%></td>
			<td><%=loginId%></td>
			<td><%=loginPass%></td>
			<td><%=createdTime%></td>
			<td><a class="btn btn-warning" href="">修改</a> <a
				class="btn btn-danger" href="" onclick="del()">删除</a></td>
		</tr>

		<%
			}
			} else {
				out.println("没有记录");
			}
		%>

	</table>

	<!-- 分页组件 -->
	<div class="container" align="right">
		<ul class="pagination pagination-lg">
			<li><a href="#">上一页</a></li>
			<%
				int pageNum = Page.getPageNum("table_parklotadmininfo", 15);
				for (int i = 1; i <= pageNum; i++) {
			%>
			<li><a href="#"><%=i%></a></li>
			<%
				}
			%>
			<li><a href="#">下一页</a></li>
		</ul>
	</div>
	<!-- 分页结束 -->
	<script src="/Shared_Parking_Space/bootstrap/js/jquery-1.9.1.min.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
</body>
<script>
	function del() {
		var a = confirm("确定删除吗？");
		if (a == true) {
			parent.location.href = "../login.html";
		} else {
		}
	}
</script>
</html>