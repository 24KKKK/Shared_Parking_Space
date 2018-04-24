<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>停车时长分布</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="/Shared_Parking_Space/js/echarts/echarts.js"></script>
<script type="text/javascript"
	src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>

<style type="text/css">
#main {
	width: 800px;
	height: 450px;
	margin-top: 20px;
	margin-left: 20px
}
</style>

</head>
<body>
	<!-- <div id="condi" style="width: 700px; height: 60px">
		<div class="form-group form-inline">
			<label class="col-lg-2 control-label">选择日期：</label> <input
				type="text" class="form-control form-statisdate" id="parkdate"
				name="buystartparkdate" readonly="readonly">

			<button class="btn btn-default btn-success" id="qry_carinbydate">查询</button>
		</div>
	</div> -->

	<div id="main"></div>

	<script type="text/javascript">
		// 初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '停车时长分布',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '15分钟以内', '15-30分钟', '30分钟-1小时', '1-2小时', '2-4小时',
						'4-8小时', '8小时-1天', '1天以上' ]
			},
			series : {
				name : '停车时长',
				type : 'pie',
				radius : '80%',
				center : [ '50%', '55%' ],
				data : [ {
					value : 1,
					name : '15分钟以内'
				} , {
					value : 1,
					name : '15-30分钟'
				}, {
					value : 1,
					name : '30分钟-1小时'
				}, {
					value : 1,
					name : '1-2小时'
				}, {
					value : 1,
					name : '2-4小时'
				}, {
					value : 1,
					name : '4-8小时'
				}, {
					value : 1,
					name : '8小时-1天'
				}, {
					value : 1,
					name : '1天以上'
				}  ],
				label : {
					show : true,
					position : 'top',
					formatter : "{b}\n {d}%"
				},
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			},
			toolbox : {
				show : true,
				feature : {
					saveAsImage : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : true
					},
					restore : {
						show : true
					},

				}
			},
		};

		// 使用刚指定的配置项和数据显示图表
		myChart.setOption(option);
	</script>

	<script type="text/javascript">
		$(function() {
			var option = myChart.getOption();
			$.ajax({
				type : "post",
				async : false,
				url : "/Shared_Parking_Space/ShowCarParkDuration",
				datatype : "json",
				success : function(result) {
					alert("请求正确");
					var leg = new Array('15分钟以内', '15-30分钟', '30分钟-1小时',
							'1-2小时', '2-4小时', '4-8小时', '8小时-1天', '1天以上');
					for (var i = 0; i < result.data.length; i++) {
						option.series[0].data[i].value = result.data[i].num;
						option.series[0].data[i].name = leg[i];
					}
					myChart.setOption(option);
				},
				error : function() {
					alert("请求错误");
				}
			});
			return false;
		});
	</script>

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