<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增购买信息</title>
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body bgcolor="#ccd9ff">
	<!-- 新增购买信息<br> -->
	<br>
	<br>

	<form class="form-horizontal" name="form_newBuyInfo"
		action="/Shared_Parking_Space/AddBuyInfoDao" method="post">
		
		<!-- 购买者信息 -->
		<div class="form-group">
			<label class="col-sm-2 control-label">姓名</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_ownername"
					name="ownername" autofocus placeholder="请输入姓名，例如：张三">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">电话</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_ownerphone"
					name="ownerphone" placeholder="请输入电话，例如：13478329826">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">性别</label>
			<div class="col-sm-1">
				<select id="input_text_ownergender" class="form-control"
					name="ownergender">
					<option class="form-control" value="1" selected="selected">男</option>
					<option class="form-control" value="2">女</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">身份证号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_owneridnumber" name="owneridnumber"
					placeholder="请输入身份证号码">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">住址</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_owneraddress"
					name="owneraddress" placeholder="请输入住址">
			</div>
		</div>
		<!-- /购买者信息 -->

		<div class="form-group">
			<label class="col-sm-2 control-label">购买车位号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_buyparkid"
					name="buyparkid" placeholder="请输入要购买的车位号">
			</div>
		</div>

		<!-- 停车时间点，开始到结束 -->

		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">停车时间</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control"
				id="input_text_buystartparktime" name="buystartparktime"
				onchange="countmoney()"> 点至 <input type="text"
				class="form-control" id="input_text_buyendparktime"
				name="buyendparktime" onchange="countmoney()">点
		</div>

		<!-- 停车日期，开始到结束 -->
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">停车日期</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control form-buyparkdatetime"
				id="input_text_buystartparkdate" name="buystartparkdate"
				readonly="readonly" onchange="countmoney()"> 号至 <input
				type="text" class="form-control form-buyparkdatetime"
				id="input_text_buyendparkdate" name="buyendparkdate"
				readonly="readonly" onchange="countmoney()">号
		</div>

		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">应付金额</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control" id="input_text_buymoney"
				name="buymoney" readonly="readonly">元
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<button type="submit" class="btn btn-default btn-success"
					name="submit_addparklotinfo">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-default btn-warning">重置</button>
			</div>
		</div> 
	</form>

	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/locales/bootstrap-datetimepicker.fr.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="/Shared_Parking_Space/js/My.js"></script>

</body>
</html>