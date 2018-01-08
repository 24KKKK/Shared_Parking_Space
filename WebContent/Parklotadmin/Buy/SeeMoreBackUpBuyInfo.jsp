<%@page import="com.dyf.model.Table_BuyInfoBackup"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="java.util.ArrayList"%>
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
<title>显示停车位购买详细信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="screen,print">
<link href="/Shared_Parking_Space/css/my.css" rel="stylesheet"
	type="text/css" media="screen,print">
</head>
<body>
	<jsp:useBean id="dbBean" class="com.dyf.bean.DBBean"></jsp:useBean>
	<%
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String buyCreatedTime = request.getParameter("buycreatedtime");
		/* HttpSession session = request.getSession(); */
		String adminId = (String) session.getAttribute("userid");
		String adminName = (String)  session.getAttribute("username");
		List<Table_BuyInfoBackup> table_BuyInfoBackups = new ArrayList<Table_BuyInfoBackup>();

		String selectAllSql = "select parklotname,ownername,ownerphone,ownergender,owneridnumber,owneraddress,buyparkid,buystartparktime,buyendparktime,buystartparkdate,buyendparkdate,buymoney,buycreatedtime,deletetime from table_buyinfobackup where parkadminid = '"+adminId+"' and buycreatedtime = '"+buyCreatedTime+"'";
		SysoUtils.print("SeeMoreBackUpBuyInfo.jsp的查询全部购买信息selectAllSql:" + selectAllSql);

		String ownername = "";
		int ownergender = 0;
		String gender = "男";
		String ownerphone = "";
		int buyparkid = 0;
		int buystartparktime = 25;
		int buyendparktime = 25;
		String buystartparkdate = "";
		String buyendparkdate = "";
		String owneridnumber = "";
		String owneraddress = "";
		String parkadminid = "";
		String parklotname = "";
		double buymoney = 0.0;
		String buycreatedtime = "";

		ResultSet rSet = dbBean.executeQuery(selectAllSql);
		try {
			while (rSet.next()) {
				parklotname = rSet.getString("parklotname");
				ownername = rSet.getString("ownername");
				ownergender = rSet.getInt("ownergender");
				if(ownergender == 2){
					gender = "女";
				}
				ownerphone = rSet.getString("ownerphone");
				buyparkid = rSet.getInt("buyparkid");
				buystartparktime = rSet.getInt("buystartparktime");
				buyendparktime = rSet.getInt("buyendparktime");
				buystartparkdate = rSet.getString("buystartparkdate");
				buyendparkdate = rSet.getString("buyendparkdate");
				owneridnumber = rSet.getString("owneridnumber");
				owneraddress = rSet.getString("owneraddress");
				parklotname = rSet.getString("parklotname");
				buymoney = rSet.getDouble("buymoney");
				buycreatedtime = rSet.getString("buycreatedtime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			rSet.close();
			dbBean.close();
		}
	%>

	<div id="printinfo">
		<div class="tableinfo">
			<table class="table table-bordered">
				<tr>
					<td colspan="6" class="print-title">停车位购买信息</td>
				</tr>
				<tr>
					<td colspan="1">姓名</td>
					<td colspan="1"><%=ownername%></td>
					<td colspan="1">电话</td>
					<td colspan="1"><%=ownerphone%></td>
					<td colspan="1">性别</td>
					<td colspan="1"><%=gender%></td>
				</tr>
				<tr>
					<td colspan="1">身份证号</td>
					<td colspan="2"><%=owneridnumber%></td>
					<td colspan="1">车位购买时间</td>
					<td colspan="2"><%=buycreatedtime%></td>
				</tr>
				<tr>
					<td colspan="1">停车时间</td>
					<td colspan="1"><%=buystartparktime%>点—<%=buyendparktime%>点</td>
					<td colspan="1">停车日期</td>
					<td colspan="1"><%=buystartparkdate%> 至 <%=buyendparkdate%></td>
					<td colspan="1">车位号</td>
					<td colspan="1"><%=buyparkid%>号</td>
				</tr>
				<tr>
					<td colspan="1">住址</td>
					<td colspan="5"><%=owneraddress%></td>
				</tr>
				<tr>
					<td colspan="1">管理员编号</td>
					<td colspan="1"><%=adminId%></td>
					<td colspan="1">停车场名称</td>
					<td colspan="1"><%=parklotname%></td>
					<td colspan="1">应付金额</td>
					<td colspan="1"><%=buymoney%>元</td>
				</tr>
			</table>
		</div>

		<div class="center_info">
			<p>这是中间的协议内容。。。</p>
		</div>
		
			<table class="table-signature">
				<tr>
					<td class="signaturebackup1">停车场负责人:&nbsp;&nbsp;</td>
					<td class="signaturebackup2"><%=adminName%></td>
				</tr>
				<tr>
					<td class="signaturebackup1">停车位购买人:&nbsp;&nbsp;</td>
					<td class="signaturebackup2"><%=ownername%></td>
				</tr>
				<tr>
					<td class="signaturebackup1" >车位购买时间:&nbsp;&nbsp;</td>
					<td class="signaturebackup2"><%=buycreatedtime%></td>
				</tr>
			</table>
		
		<%-- <div class="signature">
			<p>停车场负责人:&nbsp;&nbsp;<%=adminName %></p><br>
			<p>购买人:&nbsp;&nbsp;<%=ownername %></p><br>
		</div>
		
		<div class="date">
			<p>日期：
			&nbsp;&nbsp;&nbsp;&nbsp;<%=buycreatedtime.substring(0,4) %>年
			&nbsp;&nbsp;<%=buycreatedtime.substring(5,7) %>月
			&nbsp;&nbsp;<%=buycreatedtime.substring(8,10) %>日</p>
		</div> --%>
		
	</div>

	<div class="btn_print">
		<input id="btn_printinfo" type="button" class="btn btn-success"
			onclick="print()" value="打印" />
	</div>

	<!-- 如果您使用的是高版本jQuery调用下面jQuery迁移辅助插件即可，有本地的：
	/Shared_Parking_Space/js/jquery-migrate-1.2.1.min.js
<script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>-->
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>
	<script language="javascript"
		src="/Shared_Parking_Space/js/jquery.jqprint-0.3.js"></script>
	<script language="javascript"
		src="/Shared_Parking_Space/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="/Shared_Parking_Space/bootstrap/js/bootstrap.min.js"></script>
	<script language="javascript">
		function print() {
			$("#printinfo").jqprint();
		}
	</script>
</body>
</html>