<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"/>
<title>登录控制界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/login_files/style_log.css">
<link rel="stylesheet" type="text/css" href="resources/login_files/style.css">
<link rel="stylesheet" type="text/css" href="resources/login_files/userpanel.css">
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
document.onkeydown = function(event_e) {
    if(window.event) {
        event_e = window.event;
    }
    var int_keycode = event_e.charCode||event_e.keyCode;
    if( int_keycode == "13" && check()) {
    	login();
        return false;
    }  
}
$(function(){
	$("#loginId").focus();
});

function login() {
	if(check()) {
		submit();
	}
}

function check() {
	var username = $.trim($("#userName").val());
    var password = $.trim($("#password").val());
    if(username != "" && password != "") {
    	return true;
    } else {
    	$("#message").text("账号和密码不能为空！");
    	return false;
    }
}

function submit() {
	var un = $("#userName").val();
	var pw = $("#password").val();
	if(un=="admin" && pw=="admin") {
		window.location.href="page/shbzt/ipad/zjm.jsp";
	} else {
		alert("帐号或密码错误！");
	}
}
</script>
</head>

<body class="login" mycollectionplug="bind">
<div class="login_m">
<div class="login_logo"><img src="resources/images/logo.png" width="180" height="100"></div>
<div class="login_boder">

<div class="login_padding" id="login_model">
	<form id="lform">
	  	<h2>用户名：</h2>
	  	<label>
	    	<input type="text" name="userName" id="userName" class="txt_input txt_input2">
	  	</label>
	  	<h2>密码：</h2>
	  	<label>
	    	<input type="password" name="password" id="password" class="txt_input">
	  	</label>
	  	<div class="rem_sub">
	  		<span id="message" style="color: red;"></span>
	    	<label>
	      		<input type="button" onclick="login()" class="sub_button" name="button" id="button" value="登录控制界面" style="opacity: 0.7;">
	    	</label>
	  	</div>
  	</form>
</div>
</div>
</div>
</body>
</html>