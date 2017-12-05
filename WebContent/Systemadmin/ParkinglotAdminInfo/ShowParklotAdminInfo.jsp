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
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>显示停车场管理员信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript">
	function del(infoID) {
		//alert("infoID="+infoID);
		var a = confirm("确定删除吗？");
		if (a == true) {
			window.location.href = "/Shared_Parking_Space/DeleteParklotAdminInfoDao?id="
					+ infoID;
			//parent.location.href = "login.html";
		} else {

		}
	}
</script>
</head>

<body>
	<br>

	<!-- 停车场管理员筛选条件 -->
	<form class="form-inline"
		action="/Shared_Parking_Space/QueryParklotAdminInfoDao" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;

		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey">
				<option value="parklotname">停车场名称</option>
				<option value="parklotadminphone">电话</option>
				<option value="parklotadminname">管理员姓名</option>
			</select> <input type="text" class="form-control" id="input_text_selectinfo"
				name="selectvalue">
		</div>
		<button type="submit" class="btn btn-success">查询</button>
	</form>
	<br>

	<!-- 显示停车场管理员基本信息 -->
	<table name="table_listParklotAdminInfo"
		class="table table-striped table-hover">
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
			//SysoUtils.print("parklotAdminInfoList:"+parklotAdminInfoList);
			//SysoUtils.print("parklotAdminInfoList.size():"+parklotAdminInfoList.size());
			if (null != parklotAdminInfoList && parklotAdminInfoList.size() != 0) {
				for (Table_ParklotAdminInfo parklotAdminInfo : parklotAdminInfoList) {
					id = parklotAdminInfo.getParklotAdminId();
					phone = parklotAdminInfo.getParklotAdminPhone();
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
			<td><a class="btn btn-warning"
				href="UpdateParklotAdminInfoDao?id=<%=id%>">修改</a> <a
				class="btn btn-danger" onclick="del('<%=id%>')">删除</a></td>
		</tr>

		<%
			}
			} else {
		%>
		<tr>
			<td>没有记录</td>
		</tr>
		<%
			}
		%>

	</table>

	<!-- 分页组件 -->
	<div id="page" class="container" align="right">
		<ul class="pagination pagination-lg">
			<!-- <li><a href="#">上一页</a></li> -->
			<%
				if (parklotAdminInfoList.size() == 6) {
					int pageNum = Page.getPageNum("table_parklotadmininfo", 6);
					//int pageNum = Page.getPageNum(parklotAdminInfoList.size(), 6);
					for (int i = 1; i <= pageNum; i++) {
			%>
			<li><a
				href="/Shared_Parking_Space/QueryParklotAdminInfoDao?pagenum=<%=i%>"><%=i%></a></li>
			<%
				}
				}
			%>
			<!-- <li><a href="#">下一页</a></li> -->
		</ul>
	</div>
	<!-- 分页结束 -->
	<script src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>