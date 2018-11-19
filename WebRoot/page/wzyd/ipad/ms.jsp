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
    	<img src="page/wzyd/ipad/images/ms.jpg" usemap="#Map" border="0"/>
        <map name="Map">
        	<area shape="poly" coords="917,50,1305,50,1305,161,917,161" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/zg.jsp','0');" alt="一键开馆"/>
            <area shape="poly" coords="1316,50,1704,50,1704,161,1316,161" href="javascript:void(0);" onclick="sendCmd('','');" alt="展项控制"/>
            <area shape="poly" coords="553,295,712,295,711,451,553,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/rk.jsp','0');" alt="入口"/>
            <area shape="poly" coords="755,295,914,295,913,451,755,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/xt.jsp','0');" alt="序厅"/>
            <area shape="poly" coords="956,295,1115,295,1114,451,956,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/nl.jsp','0');" alt="能力"/>
            <area shape="poly" coords="1156,295,1315,295,1314,451,1156,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hypt.jsp','0');" alt="行业平台"/>
            <area shape="poly" coords="1358,295,1517,295,1516,451,1358,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/hyzd.jsp','0');" alt="行业终端"/>
            <area shape="poly" coords="1559,295,1718,295,1717,451,1559,451" href="javascript:void(0);" onclick="sendCmd('','');" alt="民生"/>
            <area shape="poly" coords="1745,295,1904,295,1903,451,1745,451" href="javascript:void(0);" onclick="sendCmd('page/wzyd/ipad/wl.jsp','0');" alt="未来"/>
                        
            <area shape="poly" coords="915,527,1024,527,1024,636,915,636" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL16,0','确定关闭顶灯吗？');" alt="顶灯off"/>
            <area shape="poly" coords="719,529,828,529,828,638,719,638" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL16,1','');" alt="顶灯on"/>
            <area shape="poly" coords="917,854,1026,854,1026,963,917,963" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL15,0','确定关闭筒灯吗？');" alt="筒灯off"/>
            <area shape="poly" coords="718,853,827,853,827,962,718,962" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL15,1','');" alt="筒灯on"/>
            <area shape="poly" coords="918,694,1027,694,1027,803,918,803" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL17,0','确定关闭环境灯吗？');" alt="环境灯off"/>
            <area shape="poly" coords="724,695,833,695,833,804,724,804" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_CL17,1','');" alt="环境灯on"/>
            <area shape="poly" coords="919,1020,1028,1020,1028,1129,919,1129" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SKCZ08,0','确定关闭屏幕吗？');" alt="屏幕off"/>
            <area shape="poly" coords="721,1020,830,1020,830,1129,721,1129" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SKCZ08,1','');" alt="屏幕on"/>
            <area shape="poly" coords="723,1168,832,1168,832,1277,723,1277" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WZYD-128,WZYD-129,WZYD-130,WZYD-131,WZYD-132','');" alt="主机on"/>
            <area shape="poly" coords="919,1168,1028,1168,1028,1277,919,1277" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WZYD-128,WZYD-129,WZYD-130,WZYD-131,WZYD-132','确定关闭主机吗？');" alt="主机off"/>
            
            <area shape="poly" coords="1303,708,1705,709,1706,818,1304,818" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SPJZ,1','');" alt="机顶盒"/>
            <area shape="poly" coords="1302,958,1704,959,1705,1068,1303,1068" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=MS_SPJZ,2','');" alt="视频会议"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>