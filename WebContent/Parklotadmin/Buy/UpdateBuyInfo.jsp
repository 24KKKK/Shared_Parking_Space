<%@page import="com.dyf.model.Table_BuyInfo"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.model.BuyInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示需要修改的购买信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
	<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>

	<%
		Object obj = request.getAttribute("updatebuyinfo");
		List<BuyInfo> buyInfos = null;
		if (obj instanceof List) {
			buyInfos = (List<BuyInfo>) obj;
		}

		String ownername = ""; // 购买者姓名
		String ownergender = ""; // 性别
		String ownerphone = ""; // 电话
		int buyparkid = 0; // 购买的车位号
		int buystartparktime = 25; // 停车的开始时间点
		int buyendparktime = 25; // 停车的结束时间点
		String buystartparkdate = ""; // 开始停车日期
		String buyendparkdate = ""; // 结束停车日期
		String owneridnumber = ""; // 身份证号
		String owneraddress = ""; // 购买者住址
		String parkadminid = ""; // 管理员id编号
		String parklotname = ""; // 停车场名称
		double buymoney = 0.0; // 购买停车位的应付金额
		String buycreatedtime = ""; // 购买的创建时间
		SysoUtils.print("长度："+buyInfos.size());

		 if (null != buyInfos && buyInfos.size() != 0) {
			for (BuyInfo buyInfo : buyInfos) {
				ownername = buyInfo.getOwnername();
				ownergender = (1 == buyInfo.getOwnergender()) ? "男" : "女";
				ownerphone = buyInfo.getOwnerphone();
				buyparkid = buyInfo.getBuyparkid();
				buystartparktime = buyInfo.getBuystartparktime();
				buyendparktime = buyInfo.getBuyendparktime();
				buystartparkdate = buyInfo.getBuystartparkdate();
				buyendparkdate = buyInfo.getBuyendparkdate();
				owneridnumber = buyInfo.getOwneridnumber();
				owneraddress = buyInfo.getOwneraddress();
				parkadminid = buyInfo.getParkadminid();
				parklotname = buyInfo.getParklotname();
				buymoney = buyInfo.getBuymoney();
				buycreatedtime = buyInfo.getBuycreatedtime();
			}
		} 
		 SysoUtils.print("名称为："+ownername);
	%>

	<form class="form-horizontal" name="form_updateBuyInfo"
		action="/Shared_Parking_Space/UpdateBuyInfoIntoDatebaseDao"
		method="post">

		<!-- 购买者信息 -->
		<div class="form-group">
			<label class="col-sm-2 control-label">姓名</label>
			<div class="col-sm-5">
				<input type="text" readonly="readonly" value="<%=ownername %>" class="form-control" id="input_text_ownername"
					name="ownername" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">电话</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_ownerphone"
					name="ownerphone" value="<%=ownerphone %>">
			</div>
		</div>

		<%-- <div class="form-group">
			<label class="col-sm-2 control-label">身份证号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_owneridnumber" name="owneridnumber"
					value="<%=owneridnumber %>">
			</div>
		</div> --%>

		<div class="form-group">
			<label class="col-sm-2 control-label">住址</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_owneraddress"
					name="owneraddress" value="<%=owneraddress %>">
			</div>
		</div>
		<!-- /购买者信息 -->

		<div class="form-group">
			<label class="col-sm-2 control-label">购买车位号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_buyparkid"
					name="buyparkid" value="<%=buyparkid %>">
			</div>
		</div>

		<!-- 停车时间点，开始到结束 -->
		
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">原停车时间</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control" 
				id="input_text_beforebuystartparktime" readonly="readonly" disabled="disabled"
				value="<%=buystartparktime %>">
			点至 <input type="text" class="form-control"
				id="input_text_beforebuyendparktime" readonly="readonly" disabled="disabled"
				value="<%=buyendparktime %>">点
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">新停车时间</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control"
				id="input_text_afterbuystartparktime" name="buystartparktime"
				value="<%=buystartparktime %>" onchange="secondcountmoney()">
			点至 <input type="text" class="form-control"
				id="input_text_afterbuyendparktime" name="buyendparktime"
				value="<%=buyendparktime %>" onchange="secondcountmoney()">点
		</div>

		<!-- 停车日期，开始到结束 -->
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">原停车日期</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control"
				id="input_text_beforebuystartparkdate" readonly="readonly" disabled="disabled"
				value="<%=buystartparkdate %>" >
			号至 <input type="text" class="form-control"
				id="input_text_beforebuyendparkdate" readonly="readonly" disabled="disabled"
				value="<%=buyendparkdate %>" >号
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">新停车日期</label> &nbsp;&nbsp;&nbsp;
			<input type="text" class="form-control form-updateparkdatetime"
				id="input_text_afterbuystartparkdate" name="buystartparkdate"
				value="<%=buystartparkdate %>" onchange="secondcountmoney()">
			号至 <input type="text" class="form-control form-updateparkdatetime"
				id="input_text_afterbuyendparkdate" name="buyendparkdate"
				value="<%=buyendparkdate %>" onchange="secondcountmoney()">号
		</div>
		
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">原金额为</label>
			&nbsp;&nbsp;&nbsp; <input type="text" class="form-control" value="<%=buymoney %>"
				id="input_text_beforebuymoney" name="beforebuymoney" readonly="readonly">元
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-2 control-label">加付金额为</label>
			&nbsp;&nbsp;&nbsp; <input type="text" class="form-control"
				id="input_text_afterbuymoney" name="buymoney" readonly="readonly">元
		</div>
		<input type="hidden" name="buycreatedtime" value="<%=buycreatedtime%>">
		

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
		src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script src="/Shared_Parking_Space/js/My.js"></script>
</body>
</html>