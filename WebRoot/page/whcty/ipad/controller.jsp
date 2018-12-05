<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
<title>楚天云展厅控制界面</title>
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<style>
	body {background:#0C2249;margin:0;padding:0;}
</style>
<script type="text/javascript">
	var index = -1;
	function gotoPage(page) {
		$("#iframe").attr("src", page);
	}
</script>
</head>

<body>
	<iframe id="iframe" name="iframe" src="page/ipad/zjm.jsp" width="100%" height="1536px" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>