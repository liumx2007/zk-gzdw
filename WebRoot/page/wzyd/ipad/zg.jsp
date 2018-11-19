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
			} else if("1" == msg || "2" == msg || "3" == msg || "4" == msg || "5" == msg || "6" == msg) {
				var target = msg=="1"?"入口":msg=="2"?"序厅":msg=="3"?"能力":msg=="4"?"行业":msg=="5"?"民生":"未来";
				var words = document.getElementById("led").value;
				url += msg+"_"+encodeURI(encodeURI(words));
				if(confirm("确定将"+target+"处LED字幕设置成“"+words+"”吗？")) {
					if(url != "") {
						document.getElementById("iframe").src=url;
					}
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
    	<img src="page/wzyd/ipad/images/zg.jpg" usemap="#Map" border="0"/>
        <map name="Map">
        	<area shape="poly" coords="919,50,1307,50,1307,161,919,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="一键开馆"/>
            <area shape="poly" coords="1317,49,1705,49,1705,160,1317,160" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="展项控制"/>
            <area shape="poly" coords="831,304,940,304,940,413,831,413" href="javascript:void(0);" onclick="sendCmd('cmd/close.do','确定闭馆吗？');" alt="整馆off"/>
            <area shape="poly" coords="677,305,786,305,786,414,677,414" href="javascript:void(0);" onclick="sendCmd('cmd/open.do','');" alt="整馆on"/>
            <area shape="poly" coords="1473,303,1582,303,1582,412,1473,412" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_TD,1','');" alt="灯光on"/>
            <area shape="poly" coords="1628,304,1737,304,1737,413,1628,413" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_TD,0','确定关闭灯光吗？');" alt="灯光off"/>
            <area shape="poly" coords="832,490,941,490,941,599,832,599" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_DD,0','确定关闭灯带吗？');" alt="灯带off"/>
            <area shape="poly" coords="678,489,787,489,787,598,678,598" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_DD,1','');" alt="灯带on"/>
            <area shape="poly" coords="1477,487,1586,487,1586,596,1477,596" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_LOGO,1','');" alt="logo灯on"/>
            <area shape="poly" coords="1631,487,1740,487,1740,596,1631,596" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_LOGO,0','确定关闭LOGO灯吗？');" alt="logo灯off"/>
            <area shape="poly" coords="830,675,939,675,939,784,830,784" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_TYJ,0','确定关闭投影吗？');" alt="投影off"/>
            <area shape="poly" coords="677,674,786,674,786,783,677,783" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=XT_TYJ,1','');" alt="投影on"/>
            <area shape="poly" coords="1476,673,1585,673,1585,782,1476,782" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_PJP,1','');" alt="拼接屏on"/>
            <area shape="poly" coords="1630,674,1739,674,1739,783,1630,783" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=HY_PJP,0','确定关闭拼接屏吗？');" alt="拼接屏off"/>
            <area shape="poly" coords="678,868,787,868,787,977,678,977" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_DNP,1','');" alt="屏幕on"/>
            <area shape="poly" coords="830,869,939,869,939,978,830,978" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=ALL_DNP,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="1478,868,1587,868,1587,977,1478,977" href="javascript:void(0);" onclick="sendCmd('cmd/poweronAll.do','');" alt="主机on"/>
            <area shape="poly" coords="1629,870,1738,870,1738,979,1629,979" href="javascript:void(0);" onclick="sendCmd('cmd/poweroffAll.do','确定关闭主机吗？');" alt="主机off"/>
            
            <area shape="poly" coords="770,1122,980,1122,980,1196,770,1196" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','3');" alt="能力"/>
            <area shape="poly" coords="494,1122,704,1122,704,1196,494,1196" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','2');" alt="序厅"/>
            <area shape="poly" coords="210,1122,420,1122,420,1196,210,1196" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','1')" alt="入口"/>
            <area shape="poly" coords="1054,1121,1264,1121,1264,1195,1054,1195" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','4');" alt="行业"/>
            <area shape="poly" coords="1336,1121,1546,1121,1546,1195,1336,1195" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','5');" alt="民生"/>
            <area shape="poly" coords="1613,1121,1823,1121,1823,1195,1613,1195" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=','6');" alt="未来"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
<input id="led" style="font-size: 40px; color: #FFFFFF; position: absolute; z-index: 1002; left: 131px; top: 1217px; height: 67px; width: 1779px; border-style: none; background-color: transparent;" />
</body>
</html>