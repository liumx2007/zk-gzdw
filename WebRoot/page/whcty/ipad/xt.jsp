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
	var currentIndex = -1;
	function sendCmd(url,index){
		var str = "";
		if(index == "0") {
			window.parent.gotoPage(url);
			return;
		} else if(index == "1") {
			str = "确定关闭序厅主机？";
			document.getElementById("iframe").src="cmd/send.do?con=";
		} else if(index == "2") {
			str = "确定关闭序厅灯光？";
		} else if(index == "3") {
			var zimu = document.getElementById("zimu1").value;
			document.getElementById("form1").submit();
			return;
		} else if(index == "4") {
			var zimu = document.getElementById("zimu2").value;
			document.getElementById("form2").submit();
			return;
		} else if(index == "5") {
			document.getElementById("iframe").src="cmd/send.do?con=";
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
	<img src="page/ipad/images/xt.jpg" usemap="#Map" border="0"/></div>
	<map name="Map">
          <area shape="poly" coords="791,249,1007,249,1007,348,791,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/zjm.jsp','0');" alt="主界面"/>
          <area shape="poly" coords="1027,249,1243,249,1243,348,1027,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/dg.jsp','0');" alt="灯光"/>
          <area shape="poly" coords="1263,249,1479,249,1479,348,1263,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jzp.jsp','0');" alt="矩阵屏"/>
          <area shape="poly" coords="1499,249,1715,249,1715,348,1499,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/hdq.jsp','0');" alt="互动区"/>
          <area shape="poly" coords="1734,249,1950,249,1950,348,1734,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jsc.jsp','0');" alt="解说词"/>
          <area shape="poly" coords="1565,668,1796,668,1796,730,1565,730" href="javascript:void(0);" onclick="sendCmd('','3');" alt="欢迎词确定"/>
          <area shape="poly" coords="1564,878,1795,878,1795,940,1564,940" href="javascript:void(0);" onclick="sendCmd('','4');" alt="会议主题确定"/>
          <area shape="poly" coords="418,1357,708,1357,708,1487,418,1487" href="javascript:void(0);" onclick="sendCmd('cmd/startup.do?id=2,3','5');" alt="开主机"/>
          <area shape="poly" coords="721,1358,1011,1358,1011,1488,721,1488" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?id=2,3','1');" alt="关主机"/>
          <area shape="poly" coords="1027,1357,1317,1357,1317,1487,1027,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DG1,1','');" alt="开灯"/>
          <area shape="poly" coords="1330,1358,1620,1358,1620,1488,1330,1488" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DG1,0','2');" alt="关灯"/>
    </map>
<form id="form1" action="item/edit.do" method="post" target="iframe1" style="">
		<input type="hidden" name="item" value="hyc"></input>
		<input type="hidden" name="noticeIpOrCodeName" value="192.168.2.11"></input>
		<input type="hidden" name="noticeData" value="word"></input>
		<input id="zimu1" name="value" style="font-size:40px; color:#FFFFFF; position: absolute; z-index: 1000; left: 610px; top: 662px; height: 67px; width: 920px; border-style: none; background-color: transparent;" />
	</form>
    <form id="form2" action="item/edit.do" method="post" target="iframe1">
    	<input type="hidden" name="item" value="hyzt"></input>
		<input type="hidden" name="noticeIpOrCodeName" value="192.168.2.12"></input>
		<input type="hidden" name="noticeData" value="word"></input>
	  	<input id="zimu2" name="value" style="font-size:40px; color:#FFFFFF; position: absolute; z-index: 1001; left: 610px; top: 872px; height: 67px; width: 920px; border-style: none; background-color: transparent;" />
	</form>
<iframe id="iframe1" name="iframe1" src="" style="display:none;"></iframe>
<iframe id="iframe2" name="iframe2" src="" style="display:none;"></iframe>
</body>
</html>