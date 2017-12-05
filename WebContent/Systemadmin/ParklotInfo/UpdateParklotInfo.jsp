<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="com.dyf.model.Table_ParklotInfo"%>
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
<title>修改停车场信息</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<%
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		SysoUtils.print("进入UpdateParklotInfo.jsp");
		Object obj = request.getAttribute("updateParklotInfos");
		List<Table_ParklotInfo> parklotInfoList = null;
		if (obj instanceof List) {
			parklotInfoList = (List<Table_ParklotInfo>) obj;
		}
		String name = null;
		int amount = 0;
		String lng = null;
		String lat = null;
		String adminid = null;
		String description = null;
		String createdTime = null;

		if (null != parklotInfoList && parklotInfoList.size() != 0) {
			for (Table_ParklotInfo parklotInfo : parklotInfoList) {
				name = parklotInfo.getParklotName();
				amount = parklotInfo.getParklotAmount();
				lng = parklotInfo.getParklotLng();
				lat = parklotInfo.getParklotLat();
				adminid = parklotInfo.getParklotAdminId();
				description = parklotInfo.getParklotDescription();
				createdTime = parklotInfo.getParklotCreatedTime();
				SysoUtils.print("需要修改的信息已传值数据为：");
				SysoUtils.print(name + " " + amount + " " + lng + " " + lat + " " + adminid + " " + description);
			}
		}
	%>
	<br>
	<br>
	<form class="form-horizontal" name="form_UpdateParklotInfo"
		action="UpdateParklotInfoIntoDatabase" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">名称</label>
			<div class="col-sm-5">
				<input type="text" value="<%=name%>" class="form-control"
					readonly="readonly" id="input_text_parklotname" name="parklotname">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">车位数量</label>
			<div class="col-sm-5">
				<input type="text" value="<%=amount %>" class="form-control"
					id="input_text_parklotamount" name="parklotamount">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地理精度</label>
			<div class="col-sm-5">
				<input type="text" value="<%=lng %>" class="form-control"
					id="input_text_parklotLng" name="parklotLng">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地理纬度</label>
			<div class="col-sm-5">
				<input type="text" value="<%=lat %>" class="form-control"
					id="input_text_parklotLat" name="parklotLat"
					placeholder="请输入地理位置纬度，例如：38.088558">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">选择管理员编号</label>
			<div class="col-sm-5">
				<select id="input_text_parklotadminid" name="parklotadminid">
					<option value="<%=adminid%>" selected="selected"><%=adminid %></option>
					<%
						DBBean db = new DBBean();
						String selectSql = "select parklotAdminId from table_parklotadmininfo group by parklotAdminId desc";
						ResultSet rs = db.executeQuery(selectSql);
						try {
							while (rs.next()) {
					%>
					<option class="form-control"
						value="<%=rs.getString("parklotAdminId")%>">
						<%
							out.print(rs.getString("parklotAdminId"));
						%>
					</option>
					<%
						}
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								rs.close();
								db.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">停车场描述</label>
			<div class="col-sm-5">
				<textarea class="form-control" maxlength="500" rows="4"
					id="input_text_parklotdescription" name="parklotdescription"
					placeholder="请输入停车场描述,最多500字"><%=description %></textarea>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<button type="submit" class="btn btn-default btn-success"
					name="submit_addparklotinfo">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-default btn-warning">重置</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="QueryParklotInfoDao" class="btn btn-default btn-info">返回</a>
			</div>
		</div>
	</form>

</body>
</html>