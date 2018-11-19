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
			} else if("1" == msg) {
				document.getElementById("form").submit();
				return;
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
    	<img src="page/wzyd/ipad/images/wl.jpg" usemap="#Map" border="0"/>
        <map name="Map">
        	<area shape="poly" coords="917,50,1305,50,1305,161,917,161" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1315,50,1703,50,1703,161,1315,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="未来"/>
            
            <area shape="poly" coords="686,619,795,619,795,728,686,728" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL15,1','');" alt="筒灯on"/>
            <area shape="poly" coords="879,619,988,619,988,728,879,728" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL15,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            <area shape="poly" coords="880,812,989,812,989,921,880,921" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SKCZ09,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="685,813,794,813,794,922,685,922" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SKCZ09,1','');" alt="屏幕on"/>
            <area shape="poly" coords="885,1012,994,1012,994,1121,885,1121" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-133','确定关闭主机吗？');" alt="主机off"/>
            <area shape="poly" coords="684,1012,793,1012,793,1121,684,1121" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-133','');" alt="主机on"/>
            
            <area shape="poly" coords="1267,635,1669,636,1670,745,1268,745" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-133,pb','');" alt="屏保"/>
            <area shape="poly" coords="1264,824,1666,825,1667,934,1265,934" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-133,video','');" alt="视频"/>
            <area shape="poly" coords="1266,1017,1668,1018,1669,1127,1267,1127" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-133,qm','');" alt="留言"/>
            
            <area shape="poly" coords="1578,1181,1849,1181,1847,1254,1578,1254" href="javascript:void(0);" onclick="sendCmd('','1');" alt="签名标题"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
<form id="form" action="item/edit.do" method="post" target="iframe">
   	<input type="hidden" name="item" value="qmbt"></input>
	<input type="hidden" name="noticeIpOrCodeName" value="192.168.0.133"></input>
	<input type="hidden" name="noticeData" value="title"></input>
    <input name="value" style="font-size: 40px; color: #FFFFFF; position: absolute; z-index: 1002; left: 182px; top: 1185px; height: 67px; width: 1370px; border-style: none; background-color: transparent;" />
</form>
</body>
</html>