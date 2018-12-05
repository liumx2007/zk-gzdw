<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="com.zzqx.mvc.entity.Order"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Order> list = (ArrayList<Order>) request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no"/>
	<meta name="format-detection" content="telephone=no"/>
	<meta name="format-detection" content="email=no"/>
	<title>预约参观</title>
    <link media="all" type="text/css" rel="stylesheet" href="resources/css/order/style.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/order/common.css"/>
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="resources/mobiscroll-2.16/css/mobiscroll.css"/>
	<script type="text/javascript" src="resources/mobiscroll-2.16/js/mobiscroll.js"></script>
	<script type="text/javascript">
		var browser={
		    versions:function(){
	            var u = navigator.userAgent, app = navigator.appVersion;
	            return {//移动终端浏览器版本信息
	                trident: u.indexOf('Trident') > -1, //IE内核
	                presto: u.indexOf('Presto') > -1, //opera内核
	                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
	                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
	                mac: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
	                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
	                iPad: u.indexOf('iPad') > -1, //是否iPad
	                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
	            };
	         }(),
	         language:(navigator.browserLanguage || navigator.language).toLowerCase()
		}
		var theme = "default";
		if(browser.versions.mobile) {
			if(browser.versions.mac||browser.versions.iPhone||browser.versions.iPad) {
				theme = "ios";
			} else if(browser.versions.android) {
				theme = "android-holo";
			} else {
				theme = "wp";
			}
		}
		var invalid = new Array();
		<%
			Map<Date, String> map = new HashMap<>();
			for(Order order : list) {
				if(map.containsKey(order.getDate()) && !map.get(order.getDate()).equals(order.getApm())) {
					map.put(order.getDate(), "全天");
				} else {
					map.put(order.getDate(), order.getApm());
				}
			}
			Iterator<Map.Entry<Date, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Date, String> entry = it.next();
				Date key = entry.getKey();
				Calendar c = Calendar.getInstance();
				c.setTime(key);
				String value = map.get(key);
				if("全天".equals(value)) {%>
					invalid.push(new Date(<%=c.get(Calendar.YEAR)%>,<%=c.get(Calendar.MONTH)%>,<%=c.get(Calendar.DAY_OF_MONTH)%>));
				<%} else if("上午".equals(value)) {%>
				invalid.push({
					d: new Date(<%=c.get(Calendar.YEAR)%>,<%=c.get(Calendar.MONTH)%>,<%=c.get(Calendar.DAY_OF_MONTH)%>),
					start: "00:00",
					end: "12:00"
				});
				<%} else if("下午".equals(value)) {%>
					invalid.push({
						d: new Date(<%=c.get(Calendar.YEAR)%>,<%=c.get(Calendar.MONTH)%>,<%=c.get(Calendar.DAY_OF_MONTH)%>),
						start: "11:59",
						end: "23:59"
					});
				<%} 
			}
		%>
		$(function(){
			var currYear = (new Date()).getFullYear();  
	      	//初始化日期控件
			var opt = {
				preset: 'date', //日期，可选：date\datetime\time\tree_list\image_text\select
				theme: theme, //皮肤样式，可选：default\android\android-ics light\android-ics\ios\jqm\sense-ui\wp light\wp
				display: 'modal', //显示方式 ，可选：modal\inline\bubble\top\bottom
				mode: 'scroller', //日期选择模式，可选：scroller\clickpick\mixed
				lang:'zh',
				dateFormat: 'yy-mm-dd', // 日期格式
				timeFormat: "A",
				timeWheels: "A",
				setText: '确定', //确认按钮名称
				cancelText: '取消',//取消按钮名籍我
				dateOrder: 'yymmdd', //面板中日期排列格式
				dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
				ampmText: "上午/下午",
				amText: "上午", pmText: "下午",
				showNow: false,  
           		nowText: "今",
           		minDate: new Date(),
            	endYear: currYear + 100, //结束年份
            	invalid: invalid,
            	onShow: function() {
            		$("#p_date").removeClass("cl newblerror");
					$("#p_date").addClass("cl newbl");
            	},
            	onSelect: function(text, inst) {
            		var year = text.substr(0, 10);
            		var apm = text.substr(11, 2);
            		$("#date").val(year);
            		$("#apm").val(apm);
            	}
			};
			$("#sdate").mobiscroll().datetime(opt);
			$("#number").keydown(onlyNumber);
			$("#code").keydown(onlyNumber);
			$("#telephone").keydown(onlyNumber);
		});
		function onlyNumber(event){
		    var keyCode = event.keyCode;
		    if((keyCode<48 || keyCode>57) && keyCode!=8){
		        return false;
		    }
		}
		function b(obj) {
			var v = $.trim($(obj).val());
			var id = $(obj).attr("id");
			if(v != "") {
				if(id!="code") {
					$("#p_"+id).removeClass("cl newblerror");
					$("#p_"+id).addClass("cl newbl");
					if(id=="number") {
						if(v<0) v = 1;
						else if(v>99) v = 99;
						$("#number").val(v);
					}
				} else {
					$("#p_"+id).removeClass("cl newblerror fl");
					$("#p_"+id).addClass("cl newbl fl");
				}
			}
		}
		function submit() {
			var name = $("#name").val();
			var telephone = $("#telephone").val();
			var number = $("#number").val();
			var date = $("#date").val();
			var code = $("#code").val();
			var flag = true;
			if($.trim(name) == "") {
				$("#p_name").removeClass("cl newbl");
				$("#p_name").addClass("cl newblerror");
				flag = false;
			}
			if($.trim(telephone) == "" || !/^1\d{10}$/.test(telephone)) {
				$("#p_telephone").removeClass("cl newbl");
				$("#p_telephone").addClass("cl newblerror");
				flag = false;
			}
			if($.trim(number) == "") {
				$("#p_number").removeClass("cl newbl");
				$("#p_number").addClass("cl newblerror");
				flag = false;
			}
			if($.trim(date) == "") {
				$("#p_date").removeClass("cl newbl");
				$("#p_date").addClass("cl newblerror");
				flag = false;
			}
			if($.trim(code) == "") {
				$("#p_code").removeClass("cl newbl fl");
				$("#p_code").addClass("cl newblerror fl");
				flag = false;
			}
			if(flag) {
				$.ajax({
					type: "POST",
				    url: "order/add.do",
				    data: $("#form").serialize(),
				    dataType : "json",
					success: function(object){
						if(object.type == "success") {
							window.location.href="r/order/code/"+object.data;
						} else {
							reloadCode();
							$("#loginErrMsg").text(object.message);
						}
					},error: function(XMLHttpRequest, textStatus, errorThrown) {
						$("#loginErrMsg").text("预约失败！");
					}
				});
			}
		}
		function reloadCode() {
			$("#codeImage").attr("src","randomImage.jsp?random="+Math.random());
		}
		Array.prototype.indexOf = function(e){
		  for(var i=0,j; j=this[i]; i++){
		    if(j==e){return i;}
		  }
		  return -1;
		}
	</script>
</head>
<body>
	<div class="nav-banner pr">
		<div class="title2_n1"><a href="#">预约参观</a></div>
	</div>
	
	<div class="login-news">
	    <div class="loginbox">
	        <form class="form" id="form" name="form">
	        	<input type="hidden" id="date" name="date"/>
	        	<input type="hidden" id="apm" name="apm"/>
	        	<input type="hidden" name="operator" value="<%=Order.OPERATOR_CUSTOMER%>"/>
	            <div class="logininputs mb">
	            	<label>
	                <p class="cl newbl" id="p_name"><span class="fl" style="text-align: right;"><span style="color: red">*</span>姓  名：</span>
					<input type="text" maxlength="20" name="name" id="name" onblur="b(this);"/>
	                </p>
	                </label>
	            </div>
	            <div class="logininputs mb">
	            	<label>
	                <p class="cl newbl" id="p_telephone"><span class="fl" style="text-align: right;"><span style="color: red">*</span>电话号码：</span>
					<input style="width: 100px;" type="number" name="telephone" id="telephone" onblur="b(this);"/>
	                </p>
	                </label>
	            </div> 
	            <div class="logininputs mb">
	            	<label>
	                <p class="cl newbl" id="p_number"><span class="fl" style="text-align: right;"><span style="color: red">*</span>人  数：</span>
					<input style="width: 50px;" type="number" name="number" id="number" onblur="b(this);"/>
	                </p>
	               </label>
	            </div>
	            <div class="logininputs mb">
	            	<label>
                	<p class="cl newbl" id="p_date"><span class="fl" style="text-align: right;"><span style="color: red">*</span>日  期：</span>
	                	<input type="text" name="sdate" id="sdate" onblur="b(this);"/>
                	</p>
                	</label>
            	</div>
            	<div class="logininputs mb">
            		<label>
                	<p class="cl newbl fl" id="p_code" style="width: 130px;"><span class="fl" style="text-align: right;"><span style="color: red">*</span>验证码：</span>
                		<input type="number" style="width: 50px;" name="code" id="code" onblur="b(this);"/>
                	</p>
                	</label>
                	<span class="yz fl" onclick="reloadCode();"><img src="randomImage.jsp" width="60" height="19" id="codeImage"/></span>
	            </div>
	            <div class="cl new_dl"></div>
	        </form>
	    </div>
	    <div class="loginErrMsg" id="loginErrMsg"></div>
	   	<div class="loginbox">
	        <div class="sub">
	            <input name="reg_btn" id="reg_btn_id" type="button" value="提交" class="sub_na" onclick="submit();"/>
	        </div>
	    </div>
	</div>
</body>
</html>