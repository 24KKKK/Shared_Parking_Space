/**
 * 查看停车场信息描述
 */
function seeDescri(name,des) {
	alert(name+"描述信息："+des);
}

/**
 * 当修改停车的时间点或者日期时，触发函数计算应付金额
 * input_text_buystartparktime 开始停车时间点
 * input_text_buyendparktime   结束停车时间点
 * input_text_buystartparkdate 开始停车日期
 * input_text_buyendparkdate   结束停车日期
 */
function countmoney(){
	var starttime = parseInt(document.getElementById("input_text_buystartparktime").value, 10) ;
	var endtime = parseInt(document.getElementById("input_text_buyendparktime").value, 10) ;
	var startdate = document.getElementById("input_text_buystartparkdate").value;
	var enddate = document.getElementById("input_text_buyendparkdate").value;
	//ale(starttime+" "+endtime+" "+startdate+" "+enddate);
	var diff = DateDiff(startdate, enddate);
	//ale("天数差值为："+diff);
	
	//开始计算应付金额
	var basicmoney = 1;  //购买停车位情况下，停车一小时一元
	var totalmoney = 0;  //应付总金额
	if (endtime>starttime) {
		totalmoney = endtime - starttime;  //默认endtime大于starttime，如果小于的话，用下面的判断重新计算一次
	}else {
		totalmoney = endtime+24-starttime;
	}
	//根据天数计算应付金额
	if (diff<=90) {
		totalmoney = totalmoney*diff;
	}
	else if (diff>90&&diff<=180) {
		totalmoney = totalmoney*diff*0.95;
	}else if (diff>180&&diff<=360) {
		totalmoney = totalmoney*diff*0.9;
	}else if (diff>360&&diff<=720) {
		totalmoney = totalmoney*diff*0.85;
	}else {
		totalmoney = totalmoney*diff*0.8;
	}
	if (isNaN(totalmoney.toString())) {
		//如果只输入前几个时间之后，应付金额不是数字，是NaN，当不是数字的时候，直接显示0
		totalmoney = 0;
	}
	document.getElementById("input_text_buymoney").value = totalmoney;
	
}

/**
 * 计算天数差的函数，通用  
 */
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays  
    aDate  =  sDate1.split("-")  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
    aDate  =  sDate2.split("-")  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
    return  iDays  
}    

/**
 * bootstrap的时间日期选择控件
 */
$(".form_datetime").datetimepicker({
	language: 'zh-CN',
	format: 'yyyy-mm-dd hh:ii:00',
    autoclose: true,
    todayBtn: true,
    startDate: '2017-01-01 00:00:00',
    //endDate: new Date(),  //当前时间之后的时间不能选择，这句可以使用
    startView: 1,
    todayHighlight: true,
    /* linkField: "mirror_field",
    linkFormat: "yyyy-mm-dd hh:ii:00" */
});


$(".form_buyparkdatetime").datetimepicker({
	language: 'zh-CN',
	format: 'yyyy-mm-dd',
    autoclose: true,
    todayBtn: true,
    startDate: '2017-01-01',
    //endDate: new Date(),  //当前时间之后的时间不能选择，这句可以使用
    startView: 2,
    maxView: 4,  //最高能按照年份选择时间
    minView: 3,  //日期时间选择器所能够提供的最精确的时间选择视图。选择日期
    todayHighlight: true,
    /* linkField: "mirror_field",
    linkFormat: "yyyy-mm-dd hh:ii:00" */
});

function ale(arg){
	var DEBUG = true;
	if (DEBUG) {
		alert(arg);
	}
}