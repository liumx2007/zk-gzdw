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
<style>
	body {background:#0C2249;margin:0;padding:0;}
</style>
<script type="text/javascript">
	function sendCmd(url,index){
		var str = "";
		if(index == "0") {
			window.parent.gotoPage(url);
			return;
		} else if(index == "1") {
			str = "确定关闭互动区灯光？";
		}
		if(str != "") {
			if(confirm(str)) {
				document.getElementById("iframe").src=url;
			}
		} else {
			document.getElementById("iframe").src=url;
		}
	}
</script>
</head>

<body>
	<div>
    	<img src="page/ipad/images/hdq.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="791,249,1007,249,1007,348,791,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/zjm.jsp','0');" alt="主界面"/>
          <area shape="poly" coords="1027,249,1243,249,1243,348,1027,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/dg.jsp','0');" alt="灯光"/>
          <area shape="poly" coords="1262,249,1478,249,1478,348,1262,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/xt.jsp','0');" alt="序厅"/>
          <area shape="poly" coords="1498,249,1714,249,1714,348,1498,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jzp.jsp','0');" alt="矩阵屏"/>
          <area shape="poly" coords="1733,249,1949,249,1949,348,1733,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jsc.jsp','0');" alt="解说词"/>
          <area shape="poly" coords="721,1356,1011,1356,1011,1486,721,1486" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HDDG,1','');" alt="开灯"/>
          <area shape="poly" coords="1027,1357,1317,1357,1317,1487,1027,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HDDG,0','1');" alt="关灯"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>