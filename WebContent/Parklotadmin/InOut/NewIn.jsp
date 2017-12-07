<%@page import="com.dyf.utils.InOutUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增进入信息</title>
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body bgcolor="#ccd9ff">
<br><br>

<%
String adminId = (String) session.getAttribute("userid");
int parkid = InOutUtil.getParkNumber(adminId);
%>

<form class="form-horizontal" name="form_newInInfo"
		action="../../AddInInfoDao" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">车牌号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_carid" name="carid" 
					placeholder="请输入车牌号，例如：冀AR4305">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">车位号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_parkid" name="parkid" value="<%=parkid %>"
					placeholder="请输入车位号，例如：12">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<button type="submit" class="btn btn-default btn-success"
					name="submit_addininfo">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-default btn-warning">重置</button>
			</div>
		</div>
	</form>
</body>
</html>