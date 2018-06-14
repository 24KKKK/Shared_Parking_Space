<%@page import="com.dyf.utils.SysoUtils"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dyf.bean.DBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增离开信息</title>
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body bgcolor="#ccd9ff">
<br><br>
<form class="form-horizontal" name="form_newInInfo"
		action="../../AddOutInfoDao" method="post">
		<div class="form-group">
			<label class="col-sm-2 control-label">选择车牌号</label>
			<div class="col-sm-5">
				<select id="input_text_caridid" name="carid">
					<%
						DBBean db = new DBBean();
					String adminId = (String) session.getAttribute("userid");
						String selectSql = "select carid from table_inoutinfo where parkadminid = '"+adminId+"' group by indatetime desc";
						SysoUtils.print("查看"+adminId+"所在停车场可以出场的sql："+selectSql);
						ResultSet rs = db.executeQuery(selectSql);
						try {
							while (rs.next()) {
					%>
					<option class="form-control"
						value="<%=rs.getString("carid")%>">
						<%
							out.print(rs.getString("carid"));
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
			<div class="col-sm-offset-2 col-sm-8">
				<button type="submit" class="btn btn-default btn-success"
					name="submit_addoutinfo">保存</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <button type="reset" class="btn btn-default btn-warning">重置</button> -->
			</div>
		</div>
	</form>
</body>
</html>