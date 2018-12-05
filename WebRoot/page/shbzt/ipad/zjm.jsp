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
    	<img src="page/shbzt/ipad/images/zjm.jpg" usemap="#Map" border="0"/>
        <map name="Map">
			<area shape="poly" coords="2374,29,2675,29,2675,111,2374,111" href="javascript:void(0);" onclick="sendCmd('page/shbzt/ipad/hyc.jsp','0');" alt="欢迎词"/>
        	<area shape="poly" coords="261,204,457,204,457,286,261,286" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG22,1,2000;pc,30,1','');" alt="走道机器开"/>
            <area shape="poly" coords="476,204,672,204,672,286,476,286" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,30,0,3000;con,GY_HJ_DG22,0','确定关闭走道机器吗？');" alt="走道机器关"/>
        	<area shape="poly" coords="51,338,352,338,352,420,51,420" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG12,1','');" alt="logo灯开"/>
        	<area shape="poly" coords="372,338,673,338,673,420,372,420" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG12,0','确定关闭LOGO灯光吗？');" alt="logo灯关"/>
        	<area shape="poly" coords="51,443,352,443,352,525,51,525" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_551PJP,3','');" alt="走道小米信号"/>
        	<area shape="poly" coords="372,443,673,443,673,525,372,525" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_551PJP,2','');" alt="走道PC信号"/>
            <area shape="poly" coords="51,546,352,546,352,628,51,628" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DOOR,1','');" alt="门常开"/>
            <area shape="poly" coords="372,546,673,546,673,628,372,628" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DOOR,0','');" alt="门常关"/>
            <area shape="poly" coords="51,652,352,652,352,734,51,734" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DOOR,1,1000;con,GY_HJ_DOOR,0','');" alt="门开"/>
            <area shape="poly" coords="928,203,1124,203,1124,285,928,285" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG16,1,2000;pc,33,1','');" alt="序厅机器开"/>
            <area shape="poly" coords="1143,204,1339,204,1339,286,1143,286" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,33,0,3000;con,GY_HJ_DG16,0','确定关闭序厅机器吗？');" alt="序厅机器关"/>
            <area shape="poly" coords="719,339,1020,339,1020,421,719,421" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG10,1','');" alt="序厅筒灯开"/>
            <area shape="poly" coords="1039,339,1340,339,1340,421,1039,421" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG10,0','确定关闭序厅筒灯吗？');" alt="序厅筒灯关"/>
            <area shape="poly" coords="711,442,871,442,871,524,711,524" href="javascript:void(0);" onclick="sendCmd('page/shbzt/ipad/rfid.jsp','0');" alt="序厅RFID刷卡"/>
            <area shape="poly" coords="904,441,1104,441,1104,523,904,523" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?flash=33,words','0');" alt="序厅欢迎词"/>
            <area shape="poly" coords="1138,441,1338,441,1338,523,1138,523" href="javascript:void(0);" onclick="sendCmd('photo/deleteAll.do?noticeIpOrCodeName=127.0.0.1&noticeData=refresh','是否清除所有照片？');" alt="序厅清除照片"/>
            <area shape="poly" coords="928,548,1124,548,1124,630,928,630" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG17,1','');" alt="旋转屏机器开"/>
            <area shape="poly" coords="1143,549,1339,549,1339,631,1143,631" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG17,0','确定关闭旋转屏机器吗？');" alt="旋转屏机器关"/>
            <area shape="poly" coords="719,679,1020,679,1020,761,719,761" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG08,1','');" alt="旋转屏射灯开"/>
            <area shape="poly" coords="1039,677,1340,677,1340,759,1039,759" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG08,0','确定关闭旋转屏射灯吗？');" alt="旋转屏射灯关"/>
            <area shape="poly" coords="1599,202,1795,202,1795,284,1599,284" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG20,1;con,GY_HJ_TY09,1,2000;pc,37,1;pc,38,1','');" alt="查询区机器开"/>
            <area shape="poly" coords="1815,203,2011,203,2011,285,1815,285" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,37,0;pc,38,0,3000;con,GY_HJ_DG20,0;con,GY_HJ_TY09,0','确定关闭查询区机器吗？');" alt="查询区机器关"/>
            <area shape="poly" coords="1388,338,1689,338,1689,420,1388,420" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG07,1','');" alt="查询区射灯开"/>
            <area shape="poly" coords="1708,338,2009,338,2009,420,1708,420" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG07,0','确定关闭查询区射灯吗？');" alt="查询区射灯关"/>
            <area shape="poly" coords="1391,444,1692,444,1692,526,1391,526" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY09,1','');" alt="查询区投影开"/>
            <area shape="poly" coords="1711,444,2012,444,2012,526,1711,526" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY09,0','确定关闭查询区投影吗？');" alt="查询区投影关"/>
            <area shape="poly" coords="2264,203,2460,203,2460,285,2264,285" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG21,1,2000;pc,34,1;pc,35,1;pc,36,1','');" alt="滑动屏机器开"/>
            <area shape="poly" coords="2480,203,2676,203,2676,285,2480,285" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,34,0;pc,35,0;pc,36,0,3000;con,GY_HJ_DG21,0','确定关闭滑动屏机器吗？');" alt="滑动屏机器关"/>
            <area shape="poly" coords="2054,338,2355,338,2355,420,2054,420" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_552PJP,3','');" alt="小米信号"/>
            <area shape="poly" coords="2371,340,2672,340,2672,422,2371,422" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_552PJP,2','');" alt="PC信号"/>
            <area shape="poly" coords="154,937,350,937,350,1019,154,1019" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,1;GY_HJ_TY02,1;GY_HJ_TY03,1;GY_HJ_TY04,1;GY_HJ_TY05,1;GY_HJ_TY06,1;GY_HJ_TY07,1;GY_HJ_TY13,1;GY_HJ_TY14,1&pc=43,1;44,1;45,1;46,1','');" alt="沉浸室机器开"/>
            <area shape="poly" coords="370,937,566,937,566,1019,370,1019" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,0;GY_HJ_TY02,0;GY_HJ_TY03,0;GY_HJ_TY04,0;GY_HJ_TY05,0;GY_HJ_TY06,0;GY_HJ_TY07,0;GY_HJ_TY13,0;GY_HJ_TY14,0&pc=43,0;44,0;45,0;46,0','确定关闭沉浸室机器吗？');" alt="沉浸室机器关"/>
            <area shape="poly" coords="51,1061,352,1061,352,1143,51,1143" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,1;GY_HJ_TY02,1;GY_HJ_TY03,1;GY_HJ_TY04,1;GY_HJ_TY05,1;GY_HJ_TY06,1;GY_HJ_TY07,1;GY_HJ_TY13,1;GY_HJ_TY14,1','');" alt="沉浸室投影全开"/>
            <area shape="poly" coords="371,1062,672,1062,672,1144,371,1144" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,0;GY_HJ_TY02,0;GY_HJ_TY03,0;GY_HJ_TY04,0;GY_HJ_TY05,0;GY_HJ_TY06,0;GY_HJ_TY07,0;GY_HJ_TY13,0;GY_HJ_TY14,0','确定关闭沉浸室全部投影吗？');" alt="沉浸室投影全关"/>
            <area shape="poly" coords="51,1162,352,1162,352,1244,51,1244" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,1;GY_HJ_TY02,1','');" alt="沉浸室左侧投影全开"/>
            <area shape="poly" coords="371,1162,672,1162,672,1244,371,1244" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY01,0;GY_HJ_TY02,0','确定关闭沉浸室左侧投影吗？');" alt="沉浸室左侧投影关"/>
            <area shape="poly" coords="50,1264,351,1264,351,1346,50,1346" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY03,1;GY_HJ_TY04,1;GY_HJ_TY05,1','');" alt="沉浸室中间投影开"/>
            <area shape="poly" coords="373,1266,674,1266,674,1348,373,1348" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY03,0;GY_HJ_TY04,0;GY_HJ_TY05,0','确定关闭沉浸室中间投影吗？');" alt="沉浸室中间投影关"/>
            <area shape="poly" coords="54,1364,355,1364,355,1446,54,1446" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY06,1;GY_HJ_TY07,1','');" alt="沉浸室右侧投影开"/>
            <area shape="poly" coords="371,1364,672,1364,672,1446,371,1446" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY06,0;GY_HJ_TY07,0','确定关闭沉浸室右侧投影吗？');" alt="沉浸室右侧投影关"/>
            <area shape="poly" coords="53,1464,354,1464,354,1546,53,1546" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY13,1;GY_HJ_TY14,1','');" alt="沉浸室桌面投影开"/>
            <area shape="poly" coords="375,1464,676,1464,676,1546,375,1546" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY13,0;GY_HJ_TY14,0','确定关闭沉浸室桌面投影吗？');" alt="沉浸室桌面投影关"/>
            <area shape="poly" coords="53,1567,354,1567,354,1649,53,1649" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG04,1','');" alt="沉浸室灯光开"/>
            <area shape="poly" coords="376,1566,677,1566,677,1648,376,1648" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG04,0','确定关闭沉浸室灯光吗？');" alt="沉浸室灯光关"/>
            <area shape="poly" coords="55,1667,356,1667,356,1749,55,1749" href="javascript:void(0);" onclick="sendCmd('','');" alt="沉浸室展项开启"/>
            <area shape="poly" coords="378,1670,679,1670,679,1752,378,1752" href="javascript:void(0);" onclick="sendCmd('','');" alt="沉浸室返回屏保"/>
            <area shape="poly" coords="55,1763,356,1763,356,1845,55,1845" href="javascript:void(0);" onclick="sendCmd('','');" alt="沉浸室展项继续"/>
            <area shape="poly" coords="378,1766,679,1766,679,1848,378,1848" href="javascript:void(0);" onclick="sendCmd('','');" alt="沉浸室返回暂停"/>
            <area shape="poly" coords="926,825,1122,825,1122,907,926,907" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_TY08,1;con,GY_HJ_DG16,1,2000;pc,39,1;pc,40,1;pc,41,1','');" alt="体感区机器开"/>
            <area shape="poly" coords="1142,826,1338,826,1338,908,1142,908" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,39,0;pc,40,0;pc,41,0,3000;con,GY_HJ_TY08,0;con,GY_HJ_DG16,0','确定关闭体感区机器吗？');" alt="体感区机器关"/>
            <area shape="poly" coords="719,954,1020,954,1020,1036,719,1036" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY08,1','');" alt="体感区投影开"/>
            <area shape="poly" coords="1040,954,1341,954,1341,1036,1040,1036" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY08,0','确定关闭体感区投影吗？');" alt="体感区投影关"/>
            <area shape="poly" coords="719,1056,1020,1056,1020,1138,719,1138" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG05,1;con,GY_HJ_DG06,1','');" alt="体感区射灯开"/>
            <area shape="poly" coords="1042,1058,1343,1058,1343,1140,1042,1140" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG05,0;con,GY_HJ_DG06,0','确定关闭体感区射灯吗？');" alt="体感区射灯关"/>
            <area shape="poly" coords="719,1160,1020,1160,1020,1242,719,1242" href="javascript:void(0);" onclick="sendCmd('','');" alt="体感区展项开启"/>
            <area shape="poly" coords="1040,1160,1341,1160,1341,1242,1040,1242" href="javascript:void(0);" onclick="sendCmd('','');" alt="体感区返回屏保"/>
            <area shape="poly" coords="926,1297,1122,1297,1122,1379,926,1379" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG23,1;pc,41,1','');" alt="拼接屏机器开"/>
            <area shape="poly" coords="1141,1297,1337,1297,1337,1379,1141,1379" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,41,0;con,GY_HJ_DG23,0','确定关闭拼接屏机器吗？');" alt="拼接屏机器关"/>
            <area shape="poly" coords="718,1420,1019,1420,1019,1502,718,1502" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG23,1','');" alt="拼接屏联屏开"/>
            <area shape="poly" coords="1039,1420,1340,1420,1340,1502,1039,1502" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG23,0','确定关闭联屏吗？');" alt="拼接屏联屏关"/>
            <area shape="poly" coords="718,1522,1019,1522,1019,1604,718,1604" href="javascript:void(0);" onclick="sendCmd('','');" alt="拼接屏联屏全屏"/>
            <area shape="poly" coords="1041,1522,1342,1522,1342,1604,1041,1604" href="javascript:void(0);" onclick="sendCmd('','');" alt="拼接屏联屏2*2"/>
            <area shape="poly" coords="722,1625,1023,1625,1023,1707,722,1707" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_46PJP,2','');" alt="拼接屏小米信号"/>
            <area shape="poly" coords="1039,1626,1340,1626,1340,1708,1039,1708" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_46PJP,3','');" alt="拼接屏PC信号"/>
            <area shape="poly" coords="1591,824,1787,824,1787,906,1591,906" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=con,GY_HJ_DG10,1;con,GY_HJ_DG11,1;con,GY_HJ_DG12,1;pc,47,1','');" alt="发布室机器开"/>
            <area shape="poly" coords="1807,824,2003,824,2003,906,1807,906" href="javascript:void(0);" onclick="sendCmd('cmd/sendQueue.do?message=pc,47,0;con,GY_HJ_DG10,0;con,GY_HJ_DG11,0;con,GY_HJ_DG12,0','确定关闭发布室机器吗？');" alt="发布室机器关"/>
            <area shape="poly" coords="1388,955,1689,955,1689,1037,1388,1037" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG01,1','');" alt="发布室展台灯开"/>
            <area shape="poly" coords="1708,955,2009,955,2009,1037,1708,1037" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG01,0','确定关闭发布室展台灯光吗？');" alt="发布室展台灯关"/>
            <area shape="poly" coords="1387,1058,1688,1058,1688,1140,1387,1140" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG02,1','');" alt="发布室观众灯开"/>
            <area shape="poly" coords="1708,1058,2009,1058,2009,1140,1708,1140" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG02,0','确定关闭发布室观众灯光吗？');" alt="发布室观众灯关"/>
            <area shape="poly" coords="1387,1160,1688,1160,1688,1242,1387,1242" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY10,1;GY_HJ_TY11,1;GY_HJ_TY12,1','');" alt="发布室投影开"/>
            <area shape="poly" coords="1710,1159,2011,1159,2011,1241,1710,1241" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_TY10,0;GY_HJ_TY11,0;GY_HJ_TY12,0','确定关闭发布室投影吗？');" alt="发布室投影关"/>
            <area shape="poly" coords="1388,1258,1689,1258,1689,1340,1388,1340" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室会议模式"/>
            <area shape="poly" coords="1712,1259,2013,1259,2013,1341,1712,1341" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室小米模式"/>
            <area shape="poly" coords="1388,1357,1689,1357,1689,1439,1388,1439" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室mapping模式"/>
            <area shape="poly" coords="1714,1357,2015,1357,2015,1439,1714,1439" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室PPT4：3"/>
            <area shape="poly" coords="1386,1455,1687,1455,1687,1537,1386,1537" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室PPT16：9"/>
            <area shape="poly" coords="1710,1454,2011,1454,2011,1536,1710,1536" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室PPT16：10"/>
            <area shape="poly" coords="1385,1554,1686,1554,1686,1636,1385,1636" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室小米盒子开"/>
            <area shape="poly" coords="1710,1554,2011,1554,2011,1636,1710,1636" href="javascript:void(0);" onclick="sendCmd('','确定关闭发布室小米盒子吗？');" alt="发布室小米盒子关"/>
            <area shape="poly" coords="1386,1652,1687,1652,1687,1734,1386,1734" href="javascript:void(0);" onclick="sendCmd('','');" alt="发布室返回屏保"/>
            <area shape="poly" coords="2255,825,2451,825,2451,907,2255,907" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG25,1','');" alt="会议室机器开"/>
            <area shape="poly" coords="2471,825,2667,825,2667,907,2471,907" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG25,0','确定关闭会议室机器吗？');" alt="会议室机器关"/>
            <area shape="poly" coords="2052,956,2353,956,2353,1038,2052,1038" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG03,1','');" alt="会议室筒灯开"/>
            <area shape="poly" coords="2375,956,2676,956,2676,1038,2375,1038" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG03,0','确定关闭会议室筒灯吗？');" alt="会议室筒灯关"/>
            
            <area shape="poly" coords="255,1873,466,1873,466,2031,255,2031" href="javascript:void(0);" onclick="sendCmd('cmd/open.do','');" alt="开馆"/>
            <area shape="poly" coords="475,1873,686,1873,686,2031,475,2031" href="javascript:void(0);" onclick="sendCmd('cmd/close.do','确定闭关吗？');" alt="闭馆"/>
            <area shape="poly" coords="693,1875,904,1875,904,2033,693,2033" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DG,1','');" alt="开灯"/>
            <area shape="poly" coords="913,1874,1124,1874,1124,2032,913,2032" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DG,0','确定关闭所有灯光吗？');" alt="关灯"/>
            <area shape="poly" coords="1134,1874,1345,1874,1345,2032,1134,2032" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_TY,1','');" alt="开投影"/>
            <area shape="poly" coords="1354,1874,1565,1874,1565,2032,1354,2032" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_TY,0','确定关闭所有投影机吗？');" alt="关投影"/>
            <area shape="poly" coords="1576,1874,1787,1874,1787,2032,1576,2032" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DY,1','');" alt="开屏幕"/>
            <area shape="poly" coords="1796,1874,2007,1874,2007,2032,1796,2032" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DY,0','确定关闭所有屏幕吗？');" alt="关屏幕"/>
            <area shape="poly" coords="2014,1875,2225,1875,2225,2033,2014,2033" href="javascript:void(0);" onclick="sendCmd('cmd/startupAll.do','');" alt="开主机"/>
            <area shape="poly" coords="2235,1874,2446,1874,2446,2032,2235,2032" href="javascript:void(0);" onclick="sendCmd('cmd/poweroffAll.do','确定关闭所有主机吗？');" alt="关主机"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
</body>
</html>