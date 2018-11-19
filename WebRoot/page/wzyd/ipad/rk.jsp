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
    	<img src="page/wzyd/ipad/images/rk.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="919,50,1307,50,1307,161,919,161" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1315,50,1703,50,1703,161,1315,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            
        	<area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
            
            
            <area shape="poly" coords="1481,646,1590,646,1590,755,1481,755" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL07,1','');" alt="环境灯on"/>
            <area shape="poly" coords="1680,645,1789,645,1789,754,1680,754" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL07,0','确定关闭环境灯吗？');" alt="环境灯off"/>
            <area shape="poly" coords="1679,831,1788,831,1788,940,1679,940" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_DD,0','确定关闭灯带吗？');" alt="灯带off"/>
            <area shape="poly" coords="1481,833,1590,833,1590,942,1481,942" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_DD,1','');" alt="灯带on"/>
            <area shape="poly" coords="1680,1019,1789,1019,1789,1128,1680,1128" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_SKCZ01,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="1481,1018,1590,1018,1590,1127,1481,1127" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_SKCZ01,1','');" alt="屏幕on"/>
            <area shape="poly" coords="606,1020,715,1020,715,1129,606,1129" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-100','');" alt="主机on"/>
            <area shape="poly" coords="800,1018,909,1018,909,1127,800,1127" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-100','确定关闭主机吗？');" alt="主机off"/>
            <area shape="poly" coords="804,831,913,831,913,940,804,940" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_LOGO,0','确定关闭LOGO灯吗？');" alt="logo灯off"/>
            <area shape="poly" coords="611,833,720,833,720,942,611,942" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_LOGO,1','');" alt="logo灯on"/>
            <area shape="poly" coords="605,646,714,646,714,755,605,755" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL06,1','');" alt="顶灯on"/>
            <area shape="poly" coords="800,645,909,645,909,754,800,754" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_CL06,0','确定关闭顶灯吗？');" alt="顶灯off"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>