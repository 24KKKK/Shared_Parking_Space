<%@page import="com.dyf.model.Table_Evaluate"%>
<%@page import="java.util.List"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>查看自己停车场评价</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
th,td {
	text-align:center;
}
</style>
</head>
<body>
	<br>
	
	<form class="form-inline" >
		&nbsp;&nbsp;&nbsp;&nbsp;
		
		<label class="control-label">平均分</label> 
		<input type="text"
			class="form-control" id="input_text_averagescore" readonly="true"
			placeholder="0" name="averagescore" value="${avg }">
	</form>
	<br>
	<!-- 显示停车场评价信息 -->
	<table name="table_listParklotEvaluate"
		class="table table-striped table-hover">
		<tr>
			<th width="20%">评分</th>
			<th width="60%">内容</th>
			<th width="20%">时间</th>
		</tr>
		<%
			Object obj = request.getAttribute("evaluates");
			List<Table_Evaluate> EvaluateList = null;
			if (obj instanceof List) {
				EvaluateList = (List<Table_Evaluate>) obj;
			}
			String content = null;
			int score = 0;
			String createdTime = null;
			//SysoUtils.print("ParklotInfoList:"+ParklotInfoList);
			//SysoUtils.print("ParklotInfoList.size():"+ParklotInfoList.size());
			if (null != EvaluateList && EvaluateList.size() != 0) {
				for (Table_Evaluate Evaluate : EvaluateList) {
					content = Evaluate.getContent();
					score = Evaluate.getScore();
					createdTime = Evaluate.getCreatetime();
		%>
		<tr>
			<td><%=score%></td>
			<td><%=content%></td>
			<td><%=createdTime%></td>
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
	<script src="/Shared_Parking_Space/js/My.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>
	<script src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>