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
<title>新增停车场信息</title>
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body bgcolor="#ccd9ff">
	<!-- 新增停车场信息<br> -->
	<br>
	<br>

	<form class="form-horizontal" name="form_newParkinglotInfo"
		action="/Shared_Parking_Space/AddParklotInfoDao" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">名称</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_parklotname"
					name="parklotname" placeholder="请输入停车场名称，例如：光明停车场">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">车位数量</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_parklotamount" name="parklotamount"
					placeholder="请输入车位数量，例如：50">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地理精度</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_parklotLng"
					name="parklotLng" placeholder="请输入地理位置精度，例如：114.515377">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地理纬度</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_parklotLat"
					name="parklotLat" placeholder="请输入地理位置纬度，例如：38.088558">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">选择管理员编号</label>
			<div class="col-sm-5">
				<select id="input_text_parklotadminid" name="parklotadminid">
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
					placeholder="请输入停车场描述,最多500字"></textarea>
			</div>
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
</body>
</html>