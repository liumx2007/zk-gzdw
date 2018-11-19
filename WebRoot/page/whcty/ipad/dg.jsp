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
			str = "确定关闭序厅灯光？";
		} else if(index == "2") {
			str = "确定关矩阵屏区域灯光？";
		} else if(index=="3") {
			str = "确定关闭互动区灯光？";
		} else if(index=="4") {
			str = "确定关闭所有灯光？";
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
    	<img src="page/ipad/images/dg.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="791,249,1007,249,1007,348,791,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/zjm.jsp','0');" alt="主界面"/>
          <area shape="poly" coords="1027,249,1243,249,1243,348,1027,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/xt.jsp','0');" alt="序厅"/>
          <area shape="poly" coords="1263,249,1479,249,1479,348,1263,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jzp.jsp','0');" alt="矩阵屏"/>
          <area shape="poly" coords="1499,249,1715,249,1715,348,1499,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/hdq.jsp','0');" alt="互动区"/>
          <area shape="poly" coords="1734,249,1950,249,1950,348,1734,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jsc.jsp','0');" alt="解说词"/>
          <area shape="poly" coords="285,563,586,563,586,663,285,663" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DG1,1','');" alt="开序厅灯"/>
          <area shape="poly" coords="662,562,963,561,963,661,662,661" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DG1,0','1');" alt="关序厅灯"/>
          <area shape="poly" coords="1038,563,1339,562,1339,662,1038,662" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,1','');" alt="开矩阵灯"/>
          <area shape="poly" coords="1416,564,1717,563,1717,663,1416,663" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,0','2');" alt="关矩阵灯"/>
          <area shape="poly" coords="283,727,618,726,618,827,283,826" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HDDG,1','');" alt="开互动区灯"/>
          <area shape="poly" coords="687,726,1022,725,1022,826,687,825" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HDDG,0','3');" alt="关互动区灯"/>
          <area shape="poly" coords="715,1355,1005,1355,1005,1485,715,1485" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DGALL,1','');" alt="灯光全开"/>
          <area shape="poly" coords="1021,1356,1311,1356,1311,1486,1021,1486" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=DGALL,0','4');" alt="灯光全关"/>
      </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>