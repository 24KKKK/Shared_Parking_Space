<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>进入停车场车辆时段分布</title>
<link href="/Shared_Parking_Space/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="/Shared_Parking_Space/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="/Shared_Parking_Space/js/echarts/echarts.js"></script>
<script type="text/javascript"
	src="/Shared_Parking_Space/bootstrap/js/jquery.1.9.1.min.js"></script>


</head>
<body>
	<div id="condi" style="width: 700px; height: 60px">
		<div class="form-group form-inline">
			<label class="col-lg-2 control-label">选择日期：</label> <input
				type="text" class="form-control form-statisdate" id="parkdate"
				name="buystartparkdate" readonly="readonly">

			<button class="btn btn-default btn-success" id="qry_carinbydate">查询</button>
		</div>
	</div>

	<div id="main" style="width: 800px; height: 450px"></div>

	<script type="text/javascript">
		// 初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			// 图表名称
			title : {
				text : '进入车辆时段分布'
			},
			// 工具栏
			tooltip : {},

			// 图例
			legend : {
				date : [ '销量' ]
			},
			// X轴
			xAxis :{
				//data : [ "衬衫", "羊毛", "雪纺衫", "裤子", "高跟鞋", "袜子" ],
				data:[],
				name:'时刻'
			},
			// Y轴
			yAxis : {
				name:'车辆数'
			},
			// 数据
			series : [ {
				name : '销量',
				type : 'bar',
				//data : [ 5, 20, 36, 10, 10, 20 ],
				data:[],
				label: {
	                normal: {
	                    show: true,
	                    position: 'top'
	                }
	            },
				markPoint : {
					data : [ {
						name : '最大值',
						type : 'max'
					}, {
						name : '最小值',
						type : 'min'
					} ]
				}
			} ],
			toolbox : {
				show : true,
				feature : {
					saveAsImage : {
						type : 'png',
						show : true,
						title : '保存为图片'
					},
					dataZoom : {
						show : true
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					}
				}
			}
		};

		// 使用刚指定的配置项和数据显示图表
		myChart.setOption(option);
	</script>
	
	<script type="text/javascript">
		$(function() {
			$("#qry_carinbydate").click(function() {
				var option = myChart.getOption();
				$.ajax({
					type : "post",
					async : false,
					url : "/Shared_Parking_Space/ShowCarInTime",
					datatype : "json",
					data : {
						/* parkdate:$("#parkdate").val() */
						parkdate : "2017-12-05"
					},
					success : function(result) {
						alert("请求正确");
						var x = new Array();
						var y = new Array();
						for (var i = 0; i < result.data.length; i++) {
							x[i] = result.data[i].time;
							y[i] = result.data[i].num;
						}
						option.xAxis[0].data = x;
						option.series[0].data = y;
						//alert(option.xAxis.data.toString());
						myChart.setOption(option);
					},
					error : function() {
						alert("请求错误");
					}
				});
				return false;
			});
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