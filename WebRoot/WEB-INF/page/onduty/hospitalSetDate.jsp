<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <head>
  <title>设置医院休息日</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="fescowx/weixin/js/jquery-1.10.2.min.jss" ></script>
  <script type="text/javascript" src="fescowx/weixin/js/my-plug-in.js" ></script>
  <style>
	th {
		height: 40px;
		position: relative;
		border: 1px solid #FFFFFF;
	}
	.week_day {
		background-color: #EEEEEE;cursor: pointer;
	}
	.week_day:hover{
		color: red;
		font-size: 16px;
		background-color: burlywood;
	}
	#content{
		display:none;
	}
 </style>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" beforeSubmit="setWorkTime()" action="hospitalInfoController.do?save">
		<input id="id" name="id" type="hidden" value="${hospitalInfoPage.id }">
		<input id="selectDateWay" name="selectDateWay" type="hidden" value="${hospitalInfoPage.selectDateWay }">
		<input id="workTime" name="workTime" type="hidden" value="${hospitalInfoPage.workTime }">
  </t:formvalid>
  <div style="font-size:22px;margin:5px 0px">1.按星期选择</div>
  <div style="width: 700px;height: auto;margin: 0 auto;">
  	<div id="weekShow" style="font-size:20px;">
  		<input type="checkbox" id="week_0" name="weekSelect" value="0"/>星期日&nbsp;&nbsp;
  		<input type="checkbox" id="week_1" name="weekSelect" value="1"/>星期一&nbsp;&nbsp;
  		<input type="checkbox" id="week_2" name="weekSelect" value="2"/>星期二&nbsp;&nbsp;
  		<input type="checkbox" id="week_3" name="weekSelect" value="3"/>星期三&nbsp;&nbsp;
  		<input type="checkbox" id="week_4" name="weekSelect" value="4"/>星期四&nbsp;&nbsp;
  		<input type="checkbox" id="week_5" name="weekSelect" value="5"/>星期五&nbsp;&nbsp;
  		<input type="checkbox" id="week_6" name="weekSelect" value="6"/>星期六&nbsp;&nbsp;
  	</div>
  	<div style="font-size:22px;margin:5px 0px">2.按天选择</div>
	<div>
		<table id="dateShow" style="width: 700px;height: auto;margin: 0 auto;border: 1px solid #DCDCDC;">
		
		</table>
	</div>
  </div>
	<script type="text/javascript">
	var YMD = {};
	var week = [];
	var weekList = [];
	$(function(){
		var workTime = $("#workTime").val();
		firstShow(workTime);
		//selectDateClick();
		checkBoxClick();
		dataShow(workTime);
	});
	
	function checkBoxClick(){
		$("input[name='weekSelect']").each(function(){
			var checkNum = parseInt($(this).val());
			$(this).click(function(){
				if($(this).is(':checked')){
					if(weekList.indexOf(""+$(this).val()+"") == -1){
						weekList.push($(this).val());
					}
					$("#dateShow tr th:nth-child("+(checkNum+1)+")").each(function(){
						if($(this).hasClass("week_day")){
							var span = "<span style='position: absolute;top: 0px;right: 0px;width: 0;height: 0;border-top: 15px solid green;border-left: 15px solid transparent; '></span>";
							$(this).append(span);
							$(this).css({"color":"red","font-size":"16px","border":"1px solid green"});
							$(this).attr("flag","ON");
						}
					});
				}else{
					weekList.splice(jQuery.inArray($(this).val(),weekList),1);
					$("#dateShow tr th:nth-child("+(checkNum+1)+")").each(function(){
						if($(this).hasClass("week_day")){
							$(this).children("span").remove();
							$(this).css({"color":"","font-size":"","border":""});
							$(this).attr("flag","OFF");
						}
					});
				}
			});
		});
	}
	
	function checkboxSelect(){
		var temp = [];
		$("input:checkbox[name=weekSelect]:checked").each(function(){
			temp.push($(this).val());
		});
		return temp;
	}
	
	function setWorkTime(){
		var days = [];
		var weekArray = checkboxSelect();
		if(weekArray.length == 0){
			$("#selectDateWay").val("");
			$(".week_day").each(function(){
				if($(this).attr("flag") == "ON"){
					if(days.indexOf("["+$(this).attr("data-setting")+"]") == -1){
						days.push("["+$(this).attr("data-setting")+"]");
					}
				}
			});
		}else{
			$("#selectDateWay").val(weekArray.join(","));
			//获取当前年所有选中星期的日期
			var YMDTemp = {};
			$.setParams(YMDTemp);
			
			$.getMaxDay(YMDTemp);
			while(true){
				if(YMDTemp.day > YMDTemp.lastDay){
					if(YMDTemp.month == 12){
						break;
					}else{
						YMDTemp.month = YMDTemp.month + 1;
						YMDTemp.day = 1;
						$.getMaxDay(YMDTemp); 
					}
				}
				if(weekArray.indexOf(""+$.getWeek(YMDTemp)+"") != -1){
					days.push("["+YMDTemp.year+"-"+YMDTemp.month+"-"+YMDTemp.day+"]");
				}
				YMDTemp.day = YMDTemp.day + 1;
			}
			$(".week_day").each(function(){
				if($(this).attr("flag") == "ON"){
					if(days.indexOf("["+$(this).attr("data-setting")+"]") == -1){
						days.push("["+$(this).attr("data-setting")+"]");
					}
				}
				if($(this).attr("flag") == "OFF"){
					if(days.indexOf("["+$(this).attr("data-setting")+"]") != -1){
						days.splice($.inArray("["+$(this).attr("data-setting")+"]",days),1);
						//console.log("当前日期"+$(this).attr("data-setting")+"不属于选中范围内");
					}
				}
			});
			
		}
		console.log("保存天数"+days);
		$("#workTime").val(days.join(",")); 
	}
	
	function firstShow(workTime){
		var value = $("#selectDateWay").val(); //判断是否用到按星期选择  0:否;1:是
		if(value.length > 0){
			/* var arrayDay = workTime.split(",");
			var arrayLength = 7;
			if(arrayDay.length < 7){
				arrayLength = arrayDay.length;
			}
			for(var i=0; i<arrayLength; i++){
				var temp = arrayDay[i].replace("[","").replace("]","").split("-");
				var YMDTemp = {};
				YMDTemp.year = temp[0];
				YMDTemp.month = temp[1];
				YMDTemp.day = temp[2];
				console.log(YMDTemp);
				if(weekList.indexOf($.getWeek(YMDTemp)) == -1){
					weekList.push($.getWeek(YMDTemp));
				}
			} */
			weekList = value.split(",");
			for(var k=0; k<weekList.length; k++){
				switch(parseInt(weekList[k]))
				{
					case 0:
					  $("#week_0").attr("checked","checked");
					  break;
					case 1:
					  $("#week_1").attr("checked","checked");
					  break;
					case 2:
					  $("#week_2").attr("checked","checked");
					  break;
					case 3:
					  $("#week_3").attr("checked","checked");
					  break;
					case 4:
					  $("#week_4").attr("checked","checked");
					  break;
					case 5:
					  $("#week_5").attr("checked","checked");
					  break;
					case 6:
					  $("#week_6").attr("checked","checked");
					  break;
					default:
				}
			}
		}
		//console.log(weekList);
	}
	
	/* function selectDateClick(){
		$("input[name='selectDate']").each(function(){
			$(this).click(function(){
				value = $(this).val();
				isShow(value);
			});
		});
	}
	
	function isShow(value){
		if(value == 0){
			$("#weekShow").show();
			$("#dateShow").hide();
		}else{
			$("#weekShow").hide();
			$("#dateShow").show();
		}
	} */
	
	function dataShow(workTime){
		$.setParams(YMD);
		
		$.getMaxDay(YMD);
		var numTemp = 1; 
		var spanStr = "<tr><th style='background-color: #9999CD;hover:red'>星期日</th><th style='background-color: #9999CD;'>星期一</th><th style='background-color: #9999CD;'>星期二</th><th style='background-color: #9999CD;'>星期三</th><th style='background-color: #9999CD;'>星期四</th><th style='background-color: #9999CD;'>星期五</th><th style='background-color: #9999CD;'>星期六</th></tr>";
		while(numTemp <= YMD.showDay){
			if(YMD.day > YMD.lastDay){
				if(YMD.month >= 12){
					YMD.year = YMD.year + 1;
					YMD.month = 1;
					YMD.day = 1;
					$.getMaxDay(YMD);
				}else{
					YMD.month = YMD.month + 1;
					YMD.day = 1;
					$.getMaxDay(YMD);
				}
			}
			week[$.getWeek(YMD)] = YMD.year+"-"+YMD.month+"-"+YMD.day;
			if($.getWeek(YMD) == 6){
				spanStr += $.getSortWeek(week, workTime);
				alert(week);
				week = [];
			}
			if(numTemp == YMD.showDay && week.length != 0){
				spanStr += $.getSortWeek(week, workTime);
				//alert(week);
				week = [];
			}
			YMD.day = YMD.day + 1;
			numTemp = numTemp + 1;
		}
		
		
		$(".week_day").each(function(){
			if($(this).attr("flag") == 'ON'){
				$(this).css({"border":"1px solid green"});
			}
			$(this).click(function(){
				/* var temp = $(this).attr("data-setting").split("-");
				var YMDTemp = {};
				YMDTemp.year = temp[0];
				YMDTemp.month = temp[1];
				YMDTemp.day = temp[2];  */
				if($(this).attr("flag") != 'ON'){
					var span = "<span style='position: absolute;top: 0px;right: 0px;width: 0;height: 0;border-top: 15px solid green;border-left: 15px solid transparent; '></span>";
					$(this).append(span);
					$(this).css({"color":"red","font-size":"16px","border":"1px solid green"});
					$(this).attr("flag","ON");
				}else{
					$(this).children("span").remove();
					$(this).css({"color":"","font-size":"","border":""});
					$(this).attr("flag","OFF");
				}
				
			});
		});
	}
	</script>
 </body>