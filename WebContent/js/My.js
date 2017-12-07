/**
 * 查看停车场信息描述
 */
function seeDescri(name,des) {
	//alert(name);
	//var description = document.getElementById("des").value;
	alert(name+"描述信息："+des);
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