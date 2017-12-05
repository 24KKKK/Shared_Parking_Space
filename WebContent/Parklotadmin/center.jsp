<%@page import="com.dyf.utils.SysoUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中间位置条</title>
<script type="text/javascript">
	setInterval(function() {
		var weekDayLabels = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六");
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second >= 0 && second <= 9)
			second = "0" + second;
		var currentime = year + "年" + month + "月" + day + "日 "
				+ weekDayLabels[now.getDay()] + " " + hour + ":" + minute + ":"
				+ second;
		document.getElementById("now_time").innerHTML = "您好，今天是：" + currentime;
	}, 1000)
</script>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		SysoUtils.print("center.jsp的username：" + username);
		if (username == null) {
			out.print("<script type='text/javascript'> alert('请先登录');</script>");
			response.sendRedirect("/Shared_Parking_Space/login.html");
			//response.setHeader("refresh", "0;url=/Shared_Parking_Space/login.html");
		} else {
			out.print(username);
		}
	%>
	<span id="now_time"></span>


	<!-- <span id="sp_time"> </span>
<script type="text/javascript">
setInterval(function(){
var dd = new Date();
var y = dd.getFullYear();
var m = dd.getMonth() + 1;
var d = dd.getDate();
var h = dd.getHours();
var mi = dd.getMinutes();
var s = dd.getSeconds();
var str = y + "年" + m + "月" + d + "日&nbsp;&nbsp;" + h + ":" + mi + ":" + s + "&nbsp;&nbsp;星期" + "日一二三四五六".charAt(dd.getDay());
document.getElementById("sp_time").innerHTML = "您好，今天是："+str;
},1000);
</script> -->
</body>
</html>