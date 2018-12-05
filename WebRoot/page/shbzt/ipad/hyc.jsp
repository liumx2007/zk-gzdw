<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.zzqx.mvc.entity.Rfid"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="apple-touch-fullscreen" content="yes" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
  	<title>来访者注册</title>
  	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
  	<style type="text/css">
		body{
			font:14px/28px "微软雅黑";
		}
		.contact *:focus{outline :none;}
		.contact{
			width: 700px;
			height: auto;
			background: #f6f6f6;
			margin: 40px auto;
			padding: 10px;
		}
		.contact ul {
			width: 650px;
			margin: 0 auto;
		}
		.contact ul li{
			border-bottom: 1px solid #dfdfdf;
			list-style: none;
			padding: 12px;
		}
		.contact ul li label {
			width: 120px;
			display: inline-block;
			float: left;
		}
		.contact ul li input[type=text],.contact ul li input[type=password]{
			width: 220px;
			height: 25px;
			border :1px solid #aaa;
			padding: 3px 8px;
			border-radius: 5px;
		}
		.contact ul li input:focus{
			border-color: #c00;
			
		}
		.contact ul li input[type=text]{
			transition: padding .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		.contact ul li input[type=password]{
			transition: padding  .25s;
			-o-transition: padding  .25s;
			-moz-transition: padding  .25s;
			-webkit-transition: padding  .25s;
		}
		.contact ul li input:focus{
			padding-right: 70px;
		}
		.btn {
			position: relative;
			left: 250px;
		}
		.tips{
			color: rgba(0, 0, 0, 0.5);
			padding-left: 10px;
		}
		.tips_true,.tips_false{
			padding-left: 10px;
		}
		.tips_true{
			color: green;
		}
		.tips_false{
			color: red;
		}
  </style>
<script type="text/javascript">
	function sumbit() {
		$.ajax({
			type: "POST",
		    url: "item/edit.do",
			data: $("#myform").serialize(),
			dataType : "json",
			success: function(object){
				if(object.type=="success") {
				}
				showMessage(object);
			},
			error: function(msg) {
				showMessage({type:'error',message:"<span style='color:red;'>设置失败！</span>"});
			}
		});
	}
	function back() {
		window.location.href="page/shbzt/ipad/controller.jsp";
	}
	function showMessage(object) {
		var obj;
		if(object.type == "error") {
			obj = {title:'消息',msg:"<span style='color:red;'>"+object.message+"</span>",timeout:3000,showType:'show'}
		} else if(object.type == "success"){
			obj = {title:'消息',msg:object.message,timeout:3000,showType:'show'}
		}
		$.messager.show(obj);
	}
</script>
</head>
<body>
	<div class="contact" >
		<form id="myform" name="myform">
			<input type="hidden" name="item" value="pjp"></input>
			<input type="hidden" name="noticeIpOrCodeName" value="127.0.0.1"></input>
			<input type="hidden" name="noticeData" value="word"></input>
			<ul>
				<li>
					<label>欢迎词：</label>
					<input type="text" name="value" id="value" placeholder="请输入欢迎词..."/>
				</li>
			</ul>
		</form>
		<b class="btn"><button onclick="sumbit();" style="width: 100px; height: 50px;">提交</button>
			<button onclick="back();" style="width: 100px; height: 50px;">返回</button></b>
	</div>
</body>
</html>