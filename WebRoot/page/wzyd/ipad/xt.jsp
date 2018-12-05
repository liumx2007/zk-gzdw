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
    	<img src="page/wzyd/ipad/images/xt.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="920,47,1308,47,1308,158,920,158" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1316,49,1704,49,1704,160,1316,160" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
            
            <area shape="poly" coords="781,530,890,530,890,639,781,639" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_TYJ,0','确定关闭投影吗？');" alt="投影off"/>
        	<area shape="poly" coords="586,530,695,530,695,639,586,639" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_TYJ,1','');" alt="投影on"/>
            <area shape="poly" coords="781,696,890,696,890,805,781,805" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-101','确定关闭投影主机吗？');" alt="投影主机off"/>
            <area shape="poly" coords="586,696,695,696,695,805,586,805" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-101','');" alt="投影主机on"/>
            <area shape="poly" coords="587,863,696,863,696,972,587,972" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_SKCZ01,1','');" alt="拼接屏on"/>
            <area shape="poly" coords="786,863,895,863,895,972,786,972" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_SKCZ01,0','确定关闭拼接屏吗？');" alt="拼接屏off"/>
			<area shape="poly" coords="586,1173,695,1173,695,1282,586,1282" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-102,WZYD-103,WZYD-104','');" alt="主机on"/>
            <area shape="poly" coords="785,1173,894,1173,894,1282,785,1282" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-102,WZYD-103,WZYD-104','确定关闭主机吗？');" alt="主机off"/>
            <area shape="poly" coords="586,1021,695,1021,695,1130,586,1130" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL12,1','');" alt="筒灯on"/>
            <area shape="poly" coords="784,1021,893,1021,893,1130,784,1130" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL12,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            
            <area shape="poly" coords="1488,643,1890,644,1891,753,1489,753" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WZYD-101,43_{e:con=XT_CL12,1$play=WZYD-101,42}&con=XT_CL12,0','');" alt="形象片"/>
            <area shape="poly" coords="989,644,1391,645,1392,754,990,754" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WZYD-101,42&con=XT_CL12,1','');" alt="屏保"/>
			<area shape="poly" coords="989,858,1391,859,1392,968,990,968" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-101,resume','');" alt="播放"/>
            <area shape="poly" coords="996,1059,1398,1060,1399,1169,997,1169" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-101,pause','');" alt="暂停"/>
			<area shape="poly" coords="1492,1052,1894,1053,1895,1162,1493,1162" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-101,VOL_RE','');" alt="恢复"/>
            <area shape="poly" coords="1492,856,1894,857,1895,966,1493,966" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WZYD-101,VOL_MIN','');" alt="静音"/>
		</map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>