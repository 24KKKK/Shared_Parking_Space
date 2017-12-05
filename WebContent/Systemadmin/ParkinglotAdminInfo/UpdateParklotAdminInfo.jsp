<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.model.Table_ParklotAdminInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>修改停车场管理员信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<%
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Object obj = request.getAttribute("updateParklotAdminInfos");
		List<Table_ParklotAdminInfo> parklotAdminInfoList = null;
		if (obj instanceof List) {
			parklotAdminInfoList = (List<Table_ParklotAdminInfo>) obj;
		}
		String id = null;
		String phone = null;
		int age = 0;
		String idnumber = null;
		String name = null;
		String loginId = null;
		String loginPass = null;
		String createdTime = null;

		if (null != parklotAdminInfoList && parklotAdminInfoList.size() != 0) {
			for (Table_ParklotAdminInfo parklotAdminInfo : parklotAdminInfoList) {
				id = parklotAdminInfo.getParklotAdminId();
				phone = parklotAdminInfo.getParklotAdminPhone();
				age = parklotAdminInfo.getParklotAdminAge();
				idnumber = parklotAdminInfo.getParklotAdminIdnumber();
				name = parklotAdminInfo.getParklotAdminName();
				loginId = parklotAdminInfo.getParklotAdminLoginId();
				loginPass = parklotAdminInfo.getParklotAdminLoginPass();
				createdTime = parklotAdminInfo.getParklotAdminCreatedTime();
				SysoUtils.print("需要修改的信息已传值数据为：");
				SysoUtils.print(id + " " + phone + " " + age + " " + idnumber + " " + name + " " + loginId + " "
						+ loginPass);
			}
		}
	%>
	<br>
	<br>
	<form class="form-horizontal" name="form_UpdateParklotAdminInfo"
		action="UpdateParklotAdminInfoIntoDatabase" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">编号</label>
			<div class="col-sm-5">
				<input type="text" value="<%=id%>" class="form-control"
					readonly="readonly" id="input_text_parklotadminid"
					name="parklotadminid">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电话</label>
			<div class="col-sm-5">
				<input type="text" value="<%=phone%>" class="form-control"
					id="input_text_parklotadminphone" name="parklotadminphone"
					placeholder="请输入电话，例如：13478394271">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">年龄</label>
			<div class="col-sm-5">
				<input type="text" value="<%=age%>" class="form-control"
					id="input_text_parklotadminage" name="parklotadminage"
					placeholder="请输入年龄，例如：20">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">身份证号</label>
			<div class="col-sm-5">
				<input type="text" value="<%=idnumber%>" class="form-control"
					id="input_text_parklotadminidnumber" name="parklotadminidnumber"
					placeholder="请输入身份证号">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">姓名</label>
			<div class="col-sm-5">
				<input type="text" value="<%=name%>" class="form-control"
					id="input_text_parklotadminname" name="parklotadminname"
					placeholder="请输入姓名，例如：张三">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">登录账号</label>
			<div class="col-sm-5">
				<input type="text" value="<%=loginId%>" class="form-control"
					id="input_text_parklotadminloginid" name="parklotadminloginid"
					placeholder="请输入登录账号">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">密码</label>
			<div class="col-sm-5">
				<input type="password" value="<%=loginPass%>" class="form-control"
					id="input_text_parklotadminloginpass" name="parklotadminloginpass"
					placeholder="请输入密码">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<button type="submit" class="btn btn-default btn-success"
					name="submit_addparkinglotadmininfo">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-default btn-warning">重置</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="QueryParklotAdminInfoDao" class="btn btn-default btn-info">返回</a>
			</div>
		</div>
	</form>

</body>
</html>