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
    	<img src="page/wzyd/ipad/images/hyzd.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          	<area shape="poly" coords="919,50,1307,50,1307,161,919,161" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1316,50,1704,50,1704,161,1316,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
            
            <area shape="poly" coords="805,924,914,924,914,1033,805,1033" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_SKCZ07,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="612,924,721,924,721,1033,612,1033" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_SKCZ07,1','');" alt="屏幕on"/>
            <area shape="poly" coords="1481,728,1590,728,1590,837,1481,837" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL10,1','');" alt="环境灯on"/>
            <area shape="poly" coords="1680,727,1789,727,1789,836,1680,836" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL10,0','确定关闭屏环境灯吗？');" alt="环境灯off"/>
            <area shape="poly" coords="799,725,908,725,908,834,799,834" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL14,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            <area shape="poly" coords="607,725,716,725,716,834,607,834" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL14,1','');" alt="筒灯on"/>
            <area shape="poly" coords="1482,925,1591,925,1591,1034,1482,1034" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-121,WZYD-122,WZYD-123,WZYD-124,WZYD-125,WZYD-126,WZYD-127','');" alt="主机on"/>
            <area shape="poly" coords="1680,925,1789,925,1789,1034,1680,1034" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-121,WZYD-122,WZYD-123,WZYD-124,WZYD-125,WZYD-126,WZYD-127','确定关闭主机吗？');" alt="主机off"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>