<%@page import="com.dyf.utils.Page"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.model.Table_ParklotInfo"%>
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
<title>显示停车场信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript">
	function del(name) {
		//alert("infoID="+infoID);
		var a = confirm("确定删除吗？");
		if (a == true) {
			window.location.href = "/Shared_Parking_Space/DeleteParklotInfoDao?name="
					+ name;
			//parent.location.href = "login.html";
		} else {

		}
	}
</script>
</head>

<body>
	<br>

	<!-- 停车场筛选条件 -->
	<form class="form-inline"
		action="/Shared_Parking_Space/QueryParklotInfoDao" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;

		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey">
				<option value="parklotname">停车场名称</option>
				<option value="parklotadminid">管理员编号</option>
				<option value="parklotamount">车位数量</option>
			</select> <input type="text" class="form-control" id="input_text_selectinfo"
				name="selectvalue">
		</div>
		<button type="submit" class="btn btn-success">查询</button>
	</form>
	<br>

	<!-- 显示停车场基本信息 -->
	<table name="table_listParklotInfo"
		class="table table-striped table-hover">
		<tr>
			<th>停车场名称</th>
			<th>车位数量</th>
			<th>地理精度</th>
			<th>地理纬度</th>
			<th>管理员编号</th>
			<th width="75">描述</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<%
			Object obj = request.getAttribute("parklotInfos");
			List<Table_ParklotInfo> ParklotInfoList = null;
			if (obj instanceof List) {
				ParklotInfoList = (List<Table_ParklotInfo>) obj;
			}
			String name = null;
			int amount = 0;
			String Lng = null;
			String Lat = null;
			String adminId = null;
			String description = null;
			String createdTime = null;
			//SysoUtils.print("ParklotInfoList:"+ParklotInfoList);
			//SysoUtils.print("ParklotInfoList.size():"+ParklotInfoList.size());
			if (null != ParklotInfoList && ParklotInfoList.size() != 0) {
				for (Table_ParklotInfo ParklotInfo : ParklotInfoList) {
					name = ParklotInfo.getParklotName();
					amount = ParklotInfo.getParklotAmount();
					Lng = ParklotInfo.getParklotLng();
					Lat = ParklotInfo.getParklotLat();
					adminId = ParklotInfo.getParklotAdminId();
					description = ParklotInfo.getParklotDescription();
					createdTime = ParklotInfo.getParklotCreatedTime();
		%>
		<tr>
			<td><%=name%></td>
			<td><%=amount%></td>
			<td><%=Lng%></td>
			<td><%=Lat%></td>
			<td><%=adminId%></td>
			<td><a onclick="seeDescri('<%=name%>','<%=description%>')">查看描述</a></td>
			<td><%=createdTime%></td>
			<td><a class="btn btn-warning"
				href="UpdateParklotInfoDao?name=<%=name%>">修改</a> <a
				class="btn btn-danger" onclick="del('<%=name%>')">删除</a></td>
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
				if (ParklotInfoList.size() == 6) {
					int pageNum = Page.getPageNum("table_Parklotinfo", 6);
					//int pageNum = Page.getPageNum(ParklotInfoList.size(), 6);
					for (int i = 1; i <= pageNum; i++) {
			%>
			<li><a
				href="/Shared_Parking_Space/QueryParklotInfoDao?pagenum=<%=i%>"><%=i%></a></li>
			<%
				}
				}
			%>
			<!-- <li><a href="#">下一页</a></li> -->
		</ul>
	</div>
	<!-- 分页结束 -->
	<script src="/Shared_Parking_Space/js/My.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>