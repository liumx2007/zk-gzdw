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
    	<img src="page/wzyd/ipad/images/hypt.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="916,48,1304,48,1304,159,916,159" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1318,49,1706,49,1706,160,1318,160" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/ms.jsp','0');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
            
            <area shape="poly" coords="585,643,694,643,694,752,585,752" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL14,1','');" alt="筒灯on"/>
            <area shape="poly" coords="780,644,889,644,889,753,780,753" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_CL14,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            <area shape="poly" coords="586,838,695,838,695,947,586,947" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_PJP,1','');" alt="屏幕on"/>
            <area shape="poly" coords="780,836,889,836,889,945,780,945" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_PJP,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="585,1036,694,1036,694,1145,585,1145" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-109,WZYD-110,WZYD-111,WZYD-112,WZYD-113,WZYD-114,WZYD-115,WZYD-116,WZYD-117,WZYD-118,WZYD-119,WZYD-120','');" alt="主机on"/>
            <area shape="poly" coords="784,1036,893,1036,893,1145,784,1145" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-109,WZYD-110,WZYD-111,WZYD-112,WZYD-113,WZYD-114,WZYD-115,WZYD-116,WZYD-117,WZYD-118,WZYD-119,WZYD-120','确定关闭主机吗？');" alt="主机off"/>
            <area shape="poly" coords="1489,1176,1891,1177,1892,1286,1490,1286" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,back;WZYD-110,back;WZYD-111,back&con=HY_PJPQH,1','');" alt="屏保"/>
            
            <area shape="poly" coords="986,527,1388,528,1389,637,987,637" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,A1;WZYD-111,A1&con=HY_PJPQH,2','');" alt="大数据"/>
            <area shape="poly" coords="991,654,1393,655,1394,764,992,764" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,B3;WZYD-111,B3&con=HY_PJPQH,10','');" alt="智慧旅游"/>
            <area shape="poly" coords="989,784,1391,785,1392,894,990,894" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,C1;WZYD-111,C1&con=HY_PJPQH,3','');" alt="阳光厨房"/>
            <area shape="poly" coords="990,914,1392,915,1393,1024,991,1024" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,C2;WZYD-111,C2&con=HY_PJPQH,4','');" alt="和慧眼"/>
            <area shape="poly" coords="989,1045,1391,1046,1392,1155,990,1155" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,D1;WZYD-111,D1&con=HY_PJPQH,5','');" alt="智慧环保"/>
            <area shape="poly" coords="989,1172,1391,1173,1392,1282,990,1282" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,E1;WZYD-111,E1&con=HY_PJPQH,6','');" alt="智慧海洋"/>
            <area shape="poly" coords="1488,526,1890,527,1891,636,1489,636" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,E2;WZYD-111,E2&con=HY_PJPQH,7','');" alt="森林防火系统"/>
            <area shape="poly" coords="1489,655,1891,656,1892,765,1490,765" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,E3;WZYD-111,E3&con=HY_PJPQH,8','');" alt="护林员系统"/>
            <area shape="poly" coords="1491,784,1893,785,1894,894,1492,894" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,F1;WZYD-111,F1&play=WZYD-119,19&con=HY_PJPQH,9','');" alt="车务通"/>
            <area shape="poly" coords="1489,914,1891,915,1892,1024,1490,1024" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,F2;WZYD-111,F2&con=HY_PJPQH,9&client=WZYD-119,cancel','');" alt="桥隧监管系统"/>
            <area shape="poly" coords="1488,1043,1890,1044,1891,1153,1489,1153" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=WZYD-109,G1;WZYD-111,G1&con=HY_PJPQH,7','');" alt="住建系统"/>
            
      </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>