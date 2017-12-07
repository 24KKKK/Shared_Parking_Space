<%@page import="com.dyf.model.Table_InOutInfo"%>
<%@page import="com.dyf.utils.Page"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@page import="java.sql.ResultSet"%>
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
	rel="stylesheet" media="screen">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

</head>

<body>
	<br>

	<!-- 场内停车信息筛选条件 -->
	<form class="form-inline" action="/Shared_Parking_Space/QueryInInfoDao"
		method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;

		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey1">
				<option value="carid">车牌号</option>
				<option value="parkid">车位号</option>
			</select> <input type="text" class="form-control" id="input_text_selectinfo"
				name="selectvalue1">
		</div>
		<button type="submit" class="btn btn-success">查询</button>
	
		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey2">
				<option value="startindatetime">时刻之后进入</option>
				<option value="endindatetime">时刻之前进入</option>
			</select>
			<input type="text" class="form-control form_datetime" id="input_text_selectinfo"
				name="selectvalue2" readonly="readonly">
		</div>
		<button type="submit" class="btn btn-success">查询</button>
	</form>
	<!-- 场内停车信息筛选条件完成 -->
	<br>

	<!-- 显示场内车辆的基本信息 -->
	<table name="table_listInInfo" class="table table-striped table-hover">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;车牌号</th>
			<th>进入时间</th>
			<th>车位号</th>
			<th>管理员编号</th>
			<th>停车场名字</th>
			<!-- <th>操作</th> -->
		</tr>
		<%
			Object obj = request.getAttribute("inOutInfos");
			List<Table_InOutInfo> table_InOutInfos = null;
			if (obj instanceof List) {
				table_InOutInfos = (List<Table_InOutInfo>) obj;
			}
			String carId = null;
			String inDatetime = null;
			int parkId = 0;
			String adminId = null;
			String parklotName = null;
			
			if (null != table_InOutInfos && table_InOutInfos.size() != 0) {
				for (Table_InOutInfo inOutInfo : table_InOutInfos) {
					carId = inOutInfo.getCarid();
					inDatetime = inOutInfo.getIndatetime();
					parkId = inOutInfo.getParkid();
					adminId = inOutInfo.getParkadminid();
					parklotName = inOutInfo.getParklotname();
		%>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=carId%></td>
			<td><%=inDatetime%></td>
			<td><%=parkId%></td>
			<td><%=adminId%></td>
			<td><%=parklotName%></td>
			<%-- <td><a class="btn btn-warning"
				href="UpdateInInfoDao?carId=<%=carId%>">修改</a> <a
				class="btn btn-danger" onclick="del('<%=carId%>')">删除</a></td> --%>
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
				if (table_InOutInfos.size() == 6) {
					int pageNum = Page.getPageNum("table_inoutinfo", 6);
					//int pageNum = Page.getPageNum(parklotAdminInfoList.size(), 6);
					for (int i = 1; i <= pageNum; i++) {
			%>
			<li><a
				href="/Shared_Parking_Space/QueryInInfoDao?pagenum=<%=i%>"><%=i%></a></li>
			<%
				}
				}
			%>
			<!-- <li><a href="#">下一页</a></li> -->
		</ul>
	</div>
	<!-- 分页结束 -->
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/locales/bootstrap-datetimepicker.fr.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script src="/Shared_Parking_Space/js/My.js"></script>
<script type="text/javascript">
	function del(infoID) {
		//alert("infoID="+infoID);
		var a = confirm("确定删除吗？");
		if (a == true) {
			window.location.href = "/Shared_Parking_Space/DeleteInInfoDao?carId="
					+ infoID;
			//parent.location.href = "login.html";
		} else {
		}
	}
</script>
</body>
</html>