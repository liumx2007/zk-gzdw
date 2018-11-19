<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>统计分析</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="resources/js/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="resources/js/jquery-easyui/themes/icon.css" />
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>
<style type="text/css">
table td {
	font-size: 14px;
}
</style>

<!-- 图表JS文件应用 -->
<script type="text/javascript" src="resources/js/highcharts.js"></script>
<script src="resources/js/modules/exporting.js"></script>
<script src="resources/js/themes/grid.js"></script>
<script type="text/javascript">
	var precent,names,free,busy,leave = new Array();
	var chartC;
	$(function() {
		$("#way1").hide();
		$("#way2").hide();
		$("#ot").hide();
		chartC = new Highcharts.Chart({
			chart : {
				type : 'column',
				renderTo : "container",
			},
			title : {
				text : '无标题'
			},
			subtitle : {
				text : ''
			},
			xAxis : {
				categories : [
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0',
					'0'
				],
				crosshair : true
			},
			yAxis : {
				min : 0,
				title : {
					text : '无标题'
				}
			},
			tooltip : {
				headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					'<td style="padding:0"><b>{point.y:0.1f} 分钟</b></td></tr>',
				footerFormat : '</table>',
				shared : true,
				useHTML : true
			},
			plotOptions : {
				column : {
					pointPadding : 0.2,
					borderWidth : 0
				}
			},
			series : [ {
				name : '空闲',
				data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
			},{
				name : '忙碌',
				data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
			},{
				name : '离开',
				data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
			} ]
		});

		$('#stat_button').click(function() {
			var start = $("#startTime").datebox('getValue');
			var end = $("#endTime").datebox('getValue');
			var way = $("#statWay").combobox('getValue');
			if(start == "" || end == ""){
				window.parent.showMessage({type:"error",message:"请选择时间！"});
				return ;
			}
			var startTime = new Date(start);
			var endTime = new Date(end);
			if(endTime < startTime){
				window.parent.showMessage({type:"error",message:"结束时间不能小于开始时间！"});
				return ;
			}
			if(way =="1"){
				$("#way1").show();
				$("#way2").hide();
			}else{
				$("#way2").show();
				$("#way1").hide();
				$("#personComBoBox").combobox({
					onChange : function(newData, oldData) {
						for(var i=0;i<names.length;i++){
							if(newData == names[i]){
								//alert("name:"+newData+"free:"+free[i]+"leave:"+leave[i]+"busy:"+busy[i]);
								$('#pie').highcharts({
							        chart: {
							            plotBackgroundColor: null,
							            plotBorderWidth: null,
							            plotShadow: false
							        },
							        title: {
							            text: start + "到" + end + newData +'状态时长占比统计'
							        },
							        tooltip: {
							            headerFormat: '{series.name}<br>',
							            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
							        },
							        plotOptions: {
							            pie: {
							                allowPointSelect: true,
							                cursor: 'pointer',
							                dataLabels: {
							                    enabled: true,
							                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							                    style: {
							                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							                    }
							                }
							            }
							        },
							        series: [{
							            type: 'pie',
							            name: '状态时长占比',
							            data: [
							                {
							                    name: '忙碌',
							                    y: busy[i],
							                    sliced: true,
							                    selected: true
							                },
							                ['空闲',   free[i]],
							                ['离开',   leave[i]],
							                
							            ]
							        }]
							    });
							}
						}
					}
				});
			}
			
			chartC.yAxis[0].update({
				title : {
					text : '状态时长/分钟'
				}
			});
			var stat_url = "stat/statByDate.do?start=" + start + "&end=" + end;
			
			$.ajax({
				type : "GET",
				url : stat_url,
				dataType : "json",
				success : function(object) {
					$("#busy_asc").show();
					$("#busy_precent_asc").show();
					
					//var dataObj=eval("("+object+")");
					names = object.names;
					free = object.free;
					busy = object.busy;
					leave = object.leave;
					precent = object.precent;
					chartC.xAxis[0].update({
						categories : names
					});
					chartC.series[0].update({
						name : '空闲',
						data : free
					});
					chartC.series[1].update({
						name : '忙碌',
						data : busy
					});
					chartC.series[2].update({
						name : '离开',
						data : leave
					});
				},
				error : function(msg) {
					window.parent.showMessage({
						type : "error",
						message : "获取数据失败！"
					});
				}
			});
			
			chartC.title.update({
				text : start + "到" + end + ' 员工状态时长统计'
			});
			//chartC.series[0].setData(data);
			chartC.yAxis[0].setExtremes(null);
		});


	});

	function busy_asc(){
		$("#ot").show();
		var orderData = [];
		for(var i=0;i<names.length;i++){
			var entity = {"name":""};
			entity.name = names[i];
			entity.busy = busy[i].toFixed(2);;
			entity.busy_precent = precent[i].toFixed(2);;
			orderData.push(entity);
		}
		var ntemp,btemp,ptemp;
		for(var i=0;i<orderData.length-1;i++){
			for(var j=0;j<orderData.length-i-1;j++){
				if(orderData[j].busy<orderData[j+1].busy){
					ntemp=orderData[j].name;
					orderData[j].name=orderData[j+1].name;
					orderData[j+1].name=ntemp;
					
					btemp=orderData[j].busy;
					orderData[j].busy=orderData[j+1].busy;
					orderData[j+1].busy=btemp;
					
					ptemp=orderData[j].busy_precent;
					orderData[j].busy_precent=orderData[j+1].busy_precent;
					orderData[j+1].busy_precent=ptemp;
				}
			}
		}
		$("#orderList").datagrid({
			data:orderData
		})
	};
	function busy_precent_asc(){
		$("#ot").show();
		var orderData = [];
		for(var i=0;i<names.length;i++){
			var entity = {"name":""};
			entity.name = names[i];
			entity.busy = busy[i].toFixed(2);;
			entity.busy_precent = precent[i].toFixed(2);;
			orderData.push(entity);
		}
		var ntemp,btemp,ptemp;
		for(var i=0;i<orderData.length-1;i++){
			for(var j=0;j<orderData.length-i-1;j++){
				if(orderData[j].busy_precent<orderData[j+1].busy_precent){
					ntemp=orderData[j].name;
					orderData[j].name=orderData[j+1].name;
					orderData[j+1].name=ntemp;
					
					btemp=orderData[j].busy;
					orderData[j].busy=orderData[j+1].busy;
					orderData[j+1].busy=btemp;
					
					ptemp=orderData[j].busy_precent;
					orderData[j].busy_precent=orderData[j+1].busy_precent;
					orderData[j+1].busy_precent=ptemp;
				}
			}
		}
		$("#orderList").datagrid({
			data:orderData
		})
	};
	
</script>
</head>

<body>
	统计方式：
	<select editable="false" id="statWay" class="easyui-combobox" name="dept" style="width:160px;margin: 5px">    
    	<option value="1">整体统计</option>  
    	<option value="2">个人统计</option>
	</select>
	开始时间：<input editable="false" data-options="panelWidth:320" id="startTime" type="text" class="easyui-datebox" required="required">
	结束时间：<input editable="false" data-options="panelWidth:320" id="endTime" type="text" class="easyui-datebox" required="required">
	<input id="stat_button" class="easyui-button" type='button'
		style="width:80px;" value="查询" id="statbtn" />
	<div id = "way1">
	
	<div id="container"
		style="min-width: 100%; height: 270px; margin: 0 auto"></div>
	<input id="busy_asc" class="easyui-button" type='button'
		style="width:160px; height: 35px;margin: 3px" value="按忙碌排名统计" id="statbtn" onclick="busy_asc()"/>
	<input id="busy_precent_asc" class="easyui-button" type='button'
		style="width:160px; height: 35px;margin: 3px" value="按忙碌占比片名统计" id="statbtn" onclick="busy_precent_asc()"/>
	<div id = "ot">
		<table id = "orderList" class="easyui-datagrid" style="width: 95%">    
	    <thead>    
	        <tr>    
	            <th data-options="field:'name'" style="width: 30%">姓名</th>    
	            <th data-options="field:'busy'" style="width: 30%">忙碌时间/min</th>    
	            <th data-options="field:'busy_precent'" style="width: 30%">忙碌时间占比/%</th>    
	        </tr>    
	    </thead>    
	        
	</table> 
	</div>
	
	</div>
	<div id="way2" hidden="true">
		选择人员：
		<input id="personComBoBox" class="easyui-combobox" style="width:160px;margin: 5px"
    		data-options="valueField:'name',textField:'name',url:'personnel/getAll.do'">
    		<div id="pie"
		style="min-width: 100%; height: 330px; margin: 0 auto"></div>
	</div>
</body>
</html>