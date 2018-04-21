<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增车牌号信息</title>
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body bgcolor="#ccd9ff">
	<br>
	<br>

	<form class="form-horizontal" name="form_newBuyInfo"
		action="/Shared_Parking_Space/AddPlateNumBuyInfoDao" method="post">

		<!-- 购买者信息 -->
		
		<div class="form-group">
			<label class="col-sm-2 control-label">身份证号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					id="input_text_owneridnumber" name="owneridnumber"
					placeholder="请输入身份证号码">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">车牌号</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="input_text_ownerplatenum"
					name="ownerplatenum" placeholder="请输入车牌号">
			</div>
		</div>
		<!-- /购买者信息 -->

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