<%@page import="com.dyf.model.Table_BuyInfoBackup"%>
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
<title>显示已经购买的停车位信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript">
//选择停车位号查询时，应该输入数字，要不然程序会报错
	function checkNum() {
		var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
		var selectkey = document.getElementById("selectkey1").value;
		var selectvalue = document.getElementById("input_text_selectinfo").value;
		if (selectkey == "parkid") {
			if (!re.test(selectvalue)) {
				alert("请输入数字");
				document.getElementById("input_text_selectinfo").value = "";
				document.getElementById("input_text_selectinfo").focus();
				return false;
			}
		}
	}
	
	//删除时的确定
	function del(buycreatedtime) {
		var a = confirm("确定删除吗？");
		if (a == true) {
			window.location.href = "/Shared_Parking_Space/DeleteBuyInfoDao?buycreatedtime="
					+ buycreatedtime;
		} else {
		}
	}
</script>
</head>
<body>
	<br>

	<!-- 场内停车信息筛选条件 -->
	<form class="form-inline"
		action="/Shared_Parking_Space/QueryBackUpBuyInfoDao" method="post">
		&nbsp;&nbsp;&nbsp;&nbsp;

		<div class="form-group">
			<select class="form-control col-xs-4" name="selectkey1"
				id="selectkey1">
				<option value="parkid">车位号</option>
				<option value="ownername">姓名</option>
				<option value="ownerphone">电话</option>
			</select> <input type="text" class="form-control col-sm-1"
				id="input_text_selectinfo" name="selectvalue1">
		</div>
		<button type="submit" onclick="checkNum();" class="btn btn-success">查询</button>
	</form>
	<!-- /场内停车信息筛选条件完成 -->
	<br>

	<!-- 显示场内车辆的基本信息 -->
	<table name="table_listBuyInfo" class="table table-striped table-hover">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;姓名</th>
			<th>电话</th>
			<th>车位号</th>
			<th>时间点</th>
			<th>可停车日期</th>
			<th>购买日期</th>
			<th>管理员编号</th>
			<th>操作</th>
			
		</tr>
		<%
			Object obj = request.getAttribute("table_BuyInfoBackups");
			List<Table_BuyInfoBackup> table_BuyInfoBackups = null;
			if (obj instanceof List) {
				table_BuyInfoBackups = (List<Table_BuyInfoBackup>) obj;
			}

			String ownername; // 购买者姓名
			String ownergender; // 性别
			String ownerphone; // 电话
			int buyparkid = 0; // 购买的车位号
			int buystartparktime = 25; // 停车的开始时间点
			int buyendparktime = 25; // 停车的结束时间点
			String buystartparkdate; // 开始停车日期
			String buyendparkdate; // 结束停车日期
			String owneridnumber; // 身份证号
			String owneraddress; // 购买者住址
			String parkadminid; // 管理员id编号
			String parklotname; // 停车场名称
			double buymoney = 0; // 购买停车位的应付金额
			String buycreatedtime; // 购买的创建时间

			if (null != table_BuyInfoBackups && table_BuyInfoBackups.size() != 0) {
				for (Table_BuyInfoBackup table_BuyInfoBackup : table_BuyInfoBackups) {
					ownername = table_BuyInfoBackup.getOwnername();
					ownergender = (1 == table_BuyInfoBackup.getOwnergender()) ? "男" : "女";
					ownerphone = table_BuyInfoBackup.getOwnerphone();
					buyparkid = table_BuyInfoBackup.getBuyparkid();
					buystartparktime = table_BuyInfoBackup.getBuystartparktime();
					buyendparktime = table_BuyInfoBackup.getBuyendparktime();
					buystartparkdate = table_BuyInfoBackup.getBuystartparkdate();
					buyendparkdate = table_BuyInfoBackup.getBuyendparkdate();
					owneridnumber = table_BuyInfoBackup.getOwneridnumber();
					owneraddress = table_BuyInfoBackup.getOwneraddress();
					parkadminid = table_BuyInfoBackup.getParkAdminId();
					buymoney = table_BuyInfoBackup.getBuymoney();
					buycreatedtime = table_BuyInfoBackup.getBuyCreatedTime();
		%>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=ownername%></td>
			<td><%=ownerphone%></td>
			<td><%=buyparkid%></td>
			<td><%=buystartparktime%>点-<%=buyendparktime%>点</td>
			<td><%=buystartparkdate%>——<%=buyendparkdate%></td>
			<td><%=buycreatedtime%></td>
			<td><%=parkadminid%></td>
			<td><a
				href="/Shared_Parking_Space/Parklotadmin/Buy/SeeMoreBackUpBuyInfo.jsp?buycreatedtime=<%=buycreatedtime%>">查看详细</a></td>
			<%-- <td><a class="btn btn-warning"
				href="UpdateBuyInfoDao?buycreatedtime=<%=buycreatedtime%>">修改</a> <a
				class="btn btn-danger" onclick="del('<%=buycreatedtime%>')">删除</a></td> --%>
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