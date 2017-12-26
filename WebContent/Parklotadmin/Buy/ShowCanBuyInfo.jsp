<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.model.CanBuyInfo"%>
<%@ page import="java.util.List"%>
<%@page import="com.dyf.utils.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可供购买信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<br>

	<!-- 场内停车信息筛选条件 -->
	<form class="form-inline"
		action="/Shared_Parking_Space/QueryCanBuyInfoDao" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;

		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey1">
				<option value="parkid">车位号</option>
			</select> <input type="text" class="form-control col-sm-1"
				id="input_text_selectinfo" name="selectvalue1">
		</div>
		<button type="submit" class="btn btn-success">查询</button>

		<div class="form-group form-inline">
			<label class="control-label" name="selectkey2">&nbsp;&nbsp;&nbsp;时间段</label>
			<input type="text" class="form-control" id="input_text_selectinfo"
				name="selectvalue2">点至 <input type="text"
				class="form-control" id="input_text_selectinfo" name="selectvalue3" >点
		</div>
		<button type="submit" class="btn btn-success">查询</button>
	</form>
	<!-- /场内停车信息筛选条件完成 -->
	<br>

	<!-- 显示场内车辆的基本信息 --> 
	<table name="table_listCanBuyInfo"
		class="table table-striped table-hover">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;车位号</th>
			<th>可购买时间</th>
			<th>管理员编号</th>
			<th>停车场名称</th>
			<!-- <th>操作</th> -->
		</tr>
		<%
			Object obj = request.getAttribute("canBuyInfos");
			List<CanBuyInfo> canBuyInfos = null;
			if (obj instanceof List) {
				canBuyInfos = (List<CanBuyInfo>) obj;
			}
			/* SysoUtils.print("showininfo.jsp的table_InOutInfos.size为：" + table_InOutInfos.size()); */

			int parkId = 0;
			String parkTime = null;
			String adminId = null;
			String parklotName = null;

			if (null != canBuyInfos && canBuyInfos.size() != 0) {
				for (CanBuyInfo canBuyInfo : canBuyInfos) {
					parkId = canBuyInfo.getParkId();
					parkTime = canBuyInfo.getParkTime();
					adminId = canBuyInfo.getAdminId();
					parklotName = canBuyInfo.getParklotName();
		%>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=parkId%></td>
			<td><%=parkTime%></td>
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
	<%-- <div id="page" class="container" align="right">
		<ul class="pagination pagination-lg">
			<!-- <li><a href="#">上一页</a></li> -->
			<%
				/* if (table_InOutInfos.size() == 6) { */
					int pageNum = Page.getPageNum("table_inoutinfo", 6,request);
					//int pageNum = Page.getPageNum(parklotAdminInfoList.size(), 6);
					for (int i = 1; i <= pageNum; i++) {
			%>
			<li><a href="/Shared_Parking_Space/QueryInInfoDao?pagenum=<%=i%>"><%=i%></a></li> 
				 
			<%
				}
				/* } */
			%>
			<!-- <li><a href="#">下一页</a></li> -->
		</ul>
	</div> --%>
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
</body>
</html>