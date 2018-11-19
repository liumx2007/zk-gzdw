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
<title>温州移动展厅控制界面</title>
<style>
	body {background:#0C2249;margin:0;padding:0;}
</style>
<script type="text/javascript">
	function sendCmd(url, msg){
		if(msg != "") {
			if("0" == msg) {
				if(url != "") {
					window.location.href=url;
				}
			} else if(confirm(msg)) {
				if(url != "") {
					document.getElementById("iframe").src=url;
				}
			}
		} else {
			if(url != "") {
				document.getElementById("iframe").src=url;
			}
		}
	}
</script>
</head>

<body>
	<div>
    	<img src="page/wzyd/ipad/images/nl.jpg" usemap="#Map" border="0"/>
        <map name="Map">
        	<area shape="poly" coords="916,51,1304,51,1304,162,916,162" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1318,50,1706,50,1706,161,1318,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
            
            <area shape="poly" coords="740,744,849,744,849,853,740,853" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=NL_SKCZ02,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="545,743,654,743,654,852,545,852" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=NL_SKCZ02,1','');" alt="屏幕on"/>
            <area shape="poly" coords="542,939,651,939,651,1048,542,1048" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-105,WZYD-106,WZYD-107,WZYD-108','');" alt="主机on"/>
            <area shape="poly" coords="738,940,847,940,847,1049,738,1049" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-105,WZYD-106,WZYD-107,WZYD-108','确定关闭主机吗？');" alt="主机off"/>
            <area shape="poly" coords="545,562,654,562,654,671,545,671" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=NL_CL13,1','');" alt="筒灯on"/>
            <area shape="poly" coords="742,562,851,562,851,671,742,671" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=NL_CL13,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            
            <area shape="poly" coords="557,1136,959,1137,960,1246,558,1246" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-107,VOL_RE','');" alt="恢复声音"/>
            <area shape="poly" coords="118,1133,520,1134,521,1243,119,1243" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-107,VOL_MIN','');" alt="静音"/>
            
            <area shape="poly" coords="1235,580,1637,581,1638,690,1236,690" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-106,0;WZYD-107,0;WZYD-108,0','');" alt="能力总览"/>
            <area shape="poly" coords="983,882,1385,883,1386,992,984,992" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4093','');" alt="基础能力"/>
            <area shape="poly" coords="1483,883,1885,884,1886,993,1484,993" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4092','');" alt="智能管道能力"/>
            <area shape="poly" coords="1483,1050,1885,1051,1886,1160,1484,1160" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4095','');" alt="物联网能力"/>
            <area shape="poly" coords="984,1050,1386,1051,1387,1160,985,1160" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4091','');" alt="开放平台能力"/>
            <area shape="poly" coords="1483,725,1885,726,1886,835,1484,835" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4094','');" alt="云资源"/>
            <area shape="poly" coords="982,723,1384,724,1385,833,983,833" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-105,81002B4229','');" alt="大数据能力"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>