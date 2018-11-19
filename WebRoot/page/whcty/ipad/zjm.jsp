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
			str = "确定闭馆？";
		} else if(index == "2") {
			str = "确定关闭所有灯光？";
		} else if(index=="3") {
			str = "确定关闭投影？";
		} else if(index=="4") {
			str = "确定关闭所有主机？";
		} else if(index=="5") {
			str = "确定关闭矩阵？";
		} else if(index=="6") {
			str = "确定关闭所有屏幕？";
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
    	<img src="page/ipad/images/zjm.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="791,249,1007,249,1007,348,791,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/dg.jsp','0');" alt="灯光"/>
          <area shape="poly" coords="1027,249,1243,249,1243,348,1027,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/xt.jsp','0');" alt="序厅"/>
          <area shape="poly" coords="1263,249,1479,249,1479,348,1263,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jzp.jsp','0');" alt="矩阵屏"/>
          <area shape="poly" coords="1499,249,1715,249,1715,348,1499,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/hdq.jsp','0');" alt="互动区"/>
          <area shape="poly" coords="1734,249,1950,249,1950,348,1734,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jsc.jsp','0');" alt="解说词"/>
          <area shape="poly" coords="265,563,481,563,481,662,265,662" href="javascript:void(0);" onclick="sendCmd('cmd/open.do','');" alt="开馆"/>
          <area shape="poly" coords="521,562,737,562,737,661,521,661" href="javascript:void(0);" onclick="sendCmd('cmd/close.do','1');" alt="闭馆"/>
          <area shape="poly" coords="777,563,993,563,993,662,777,662" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DGALL,1','');" alt="开灯"/>
          <area shape="poly" coords="1032,563,1248,563,1248,662,1032,662" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DGALL,0','2');" alt="关灯"/>
          <area shape="poly" coords="1291,563,1507,563,1507,662,1291,662" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=TYJALL,1','');" alt="开投影"/>
          <area shape="poly" coords="1547,563,1763,563,1763,662,1547,662" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=TYJALL,0','3');" alt="关投影"/>
          <area shape="poly" coords="265,746,481,746,481,845,265,845" href="javascript:void(0);" onclick="sendCmd('cmd/startupAll.do','');" alt="开主机"/>
          <area shape="poly" coords="521,747,737,747,737,846,521,846" href="javascript:void(0);" onclick="sendCmd('cmd/poweroffAll.do','4');" alt="关主机"/>
          <area shape="poly" coords="777,746,993,746,993,845,777,845" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJP,1','');" alt="开矩阵"/>
          <area shape="poly" coords="1032,746,1248,746,1248,845,1032,845" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJP,0','5');" alt="关矩阵"/>
          <area shape="poly" coords="1291,746,1507,746,1507,845,1291,845" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PMALL,1','');" alt="开屏幕"/>
          <area shape="poly" coords="1546,746,1762,746,1762,845,1546,845" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PMALL,0','6');" alt="关屏幕"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>