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
	$(function(){
		setInterval("refresh()", 2000);//1000为1秒钟
	});
	function refresh() {
		$.ajax({
			type: "GET",
		    url: "rfid/getLastRfid.do",
			success: function(object){
				$("#rfid").val(object);
				if($("#rfid").val()!="") {
					divrfid.innerHTML='<font class="tips_true">已经刷卡</font>';
				} else {
					divrfid.innerHTML='请刷卡';
				}
			},
			error: function(msg) {
				
			}
		});
	}
	function checkna(){
		var na = $("#name").val();
 		if(na.length < 1 || na.length > 20) {
 			divname.innerHTML='<font class="tips_false">长度是1~20个字符</font>'; 
		} else {
		    divname.innerHTML='<font class="tips_true">输入正确</font>';
		}
	}
	function checkphone(){
		var na = $("#phoneNumber").val();
 		if(na.length > 50) {
 			divphone.innerHTML='<font class="tips_false">长度不能超过50个字符</font>'; 
		} else {
			divphone.innerHTML='长度0~50个字符';
		}
	}
	function checkemail(){
		var na = $("#email").val();
 		if(na.length > 50) {
 			divemail.innerHTML='<font class="tips_false">长度不能超过50个字符</font>'; 
		} else {
			divemail.innerHTML='长度0~50个字符';
		}
	}
	function checkcom(){
		var na = $("#companyName").val();
 		if(na.length > 50) {
 			divcom.innerHTML='<font class="tips_false">长度不能超过50个字符</font>'; 
		} else {
			divcom.innerHTML='长度0~50个字符';
		}
	}
	function checkjob(){
		var na = $("#job").val();
 		if(na.length > 50) {
 			divjob.innerHTML='<font class="tips_false">长度不能超过50个字符</font>'; 
		} else {
			divjob.innerHTML='长度0~50个字符';
		}
	}
	function sumbit() {
		var b = true;
		var rfid = $("#rfid").val();
		var na = $("#name").val();
		var phone = $("#phoneNumber").val();
		var email = $("#email").val();
		var com = $("#companyName").val();
		var job = $("#job").val();
		if(rfid=="") {
			divrfid.innerHTML='<font class="tips_false">请刷卡</font>';
			b = false;
		} else {
			divrfid.innerHTML='<font class="tips_true">已经刷卡</font>';
		}
		if(na.length < 1 || na.length > 20) {
 			divname.innerHTML='<font class="tips_false">长度是1~20个字符</font>';
 			b = false;
		} else {  
		    divname.innerHTML='<font class="tips_true">输入正确</font>';
		}
		if(phone.length > 50) {
 			divphone.innerHTML='<font class="tips_false">长度不能超过50个字符</font>';
 			b = false;
		}
		if(email.length > 50) {
 			divemail.innerHTML='<font class="tips_false">长度不能超过50个字符</font>';
 			b = false;
		}
		if(com.length > 50) {
 			divcom.innerHTML='<font class="tips_false">长度不能超过50个字符</font>';
 			b = false;
		}
		if(job.length > 50) {
 			divjob.innerHTML='<font class="tips_false">长度不能超过50个字符</font>';
 			b = false;
		}
		if(!b) return;
		$.ajax({
			type: "POST",
		    url: "rfid/save.do",
			data: $("#myform").serialize(),
			dataType : "json",
			success: function(object){
				if(object.type=="success") {
					$("#rfid").val("");
					$("#name").val("");
					$("#phoneNumber").val("");
					$("#email").val("");
					$("#companyName").val("");
					$("#job").val("");
					divname.innerHTML="";
					divrfid.innerHTML="请刷卡";
					divemail.innerHTML="长度0~50个字符";
					divrfid.innerHTML="长度0~50个字符";
					divname.innerHTML="长度0~50个字符";
					divrfid.innerHTML="长度0~50个字符";
				}
				showMessage(object);
			},
			error: function(msg) {
				showMessage({type:'error',message:"<span style='color:red;'>录入失败！</span>"});
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
			<ul>
				<li>
					<label>RFID：</label>
					<input type="text" name="rfid" id="rfid" placeholder="请刷卡..."  readonly="readonly"/><span class="tips" id="divrfid">请刷卡</span>
				</li>
				<li>
					<label>姓名：</label>
					<input type="text" name="name" id="name" placeholder="请输入姓名..."  onblur="checkna()" required/><span class="tips" id="divname">长度1~20个字符</span>
				</li>
				<li>
					<label>电话号码：</label>
					<input type="text" name="phoneNumber" id="phoneNumber" placeholder="请输入电话号码..."  onblur="checkphone()"/><span class="tips" id="divphone">长度0~50个字符</span>
				</li>
				<li>
					<label>邮箱：</label>
					<input type="text" name="email" id="email" placeholder="请输入邮箱..."  onblur="checkemail()"/><span class="tips" id="divemail">长度0~50个字符</span>
				</li>
				<li>
					<label>公司名：</label>
					<input type="text" name="companyName" id="companyName" placeholder="请输入公司名称..."  onblur="checkcom()"/><span class="tips" id="divcom">长度0~50个字符</span>
				</li>
				<li>
					<label>职位：</label>
					<input type="text" name="job" id="job" placeholder="请输入职位..."  onblur="checkcom()"/><span class="tips" id="divjob">长度0~50个字符</span>
				</li>
			</ul>
		</form>
		<b class="btn"><button onclick="sumbit();" style="width: 100px; height: 50px;">提交</button>
			<button onclick="back();" style="width: 100px; height: 50px;">返回</button></b>
	</div>
</body>
</html>