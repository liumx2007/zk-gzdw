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
	var movies = new Array();
	//离任审计
	movies[0] = "cmd/send.do?play=PJP,27";
	movies[1] = "cmd/send.do?play=PJP,28";
	movies[2] = "cmd/send.do?play=PJP,29";
	movies[3] = "cmd/send.do?play=PJP,30";
	movies[4] = "cmd/send.do?play=PJP,31";
	movies[5] = "cmd/send.do?play=PJP,32";
	movies[6] = "cmd/send.do?play=PJP,33";
	//舆情
	movies[7] = "cmd/send.do?play=PJP,34";
	movies[8] = "cmd/send.do?play=PJP,35";
	movies[9] = "cmd/send.do?play=PJP,36";
	movies[10] = "cmd/send.do?play=PJP,37";
	movies[11] = "cmd/send.do?play=PJP,38";
	//数据湖北
	movies[12] = "cmd/send.do?play=PJP,74";
	movies[13] = "cmd/send.do?play=PJP,75";
	movies[14] = "cmd/send.do?play=PJP,76";
	movies[15] = "cmd/send.do?play=PJP,77";
	movies[16] = "cmd/send.do?play=PJP,78";
	movies[17] = "cmd/send.do?play=PJP,79";
	movies[18] = "cmd/send.do?play=PJP,80";
	movies[19] = "cmd/send.do?play=PJP,81";
	movies[20] = "cmd/send.do?play=PJP,82";
	movies[21] = "cmd/send.do?play=PJP,83";
	movies[22] = "cmd/send.do?play=PJP,84";
	//教育云
	movies[23] = "cmd/send.do?play=PJP,45";
	movies[24] = "cmd/send.do?play=PJP,46";
	movies[25] = "cmd/send.do?play=PJP,47";
	movies[26] = "cmd/send.do?play=PJP,48";
	movies[27] = "cmd/send.do?play=PJP,49";
	movies[28] = "cmd/send.do?play=PJP,50";
	movies[29] = "cmd/send.do?play=PJP,51";
	//工业云
	movies[30] = "cmd/send.do?play=PJP,85";
	movies[31] = "cmd/send.do?play=PJP,86";
	movies[32] = "cmd/send.do?play=PJP,87";
	movies[33] = "cmd/send.do?play=PJP,88";
	movies[34] = "cmd/send.do?play=PJP,89";
	movies[35] = "cmd/send.do?play=PJP,90";
	movies[36] = "cmd/send.do?play=PJP,91";
	movies[37] = "cmd/send.do?play=PJP,92";
	movies[38] = "cmd/send.do?play=PJP,93";
	movies[39] = "cmd/send.do?play=PJP,94";
	//平台特性
	movies[40] = "cmd/send.do?play=PJP,52";
	movies[41] = "cmd/send.do?play=PJP,53";
	movies[42] = "cmd/send.do?play=PJP,54";
	movies[43] = "cmd/send.do?play=PJP,55";
	movies[44] = "cmd/send.do?play=PJP,56";
	movies[45] = "cmd/send.do?play=PJP,57";
	movies[46] = "cmd/send.do?play=PJP,58";
	movies[47] = "cmd/send.do?play=PJP,59";
	movies[48] = "cmd/send.do?play=PJP,60";
	movies[49] = "cmd/send.do?play=PJP,61";
	movies[50] = "cmd/send.do?play=PJP,62";
	//平台价值
	movies[51] = "cmd/send.do?play=PJP,63";
	movies[52] = "cmd/send.do?play=PJP,64";
	movies[53] = "cmd/send.do?play=PJP,65";
	movies[54] = "cmd/send.do?play=PJP,66";
	movies[55] = "cmd/send.do?play=PJP,67";
	movies[56] = "cmd/send.do?play=PJP,68";
	movies[57] = "cmd/send.do?play=PJP,69";
	movies[58] = "cmd/send.do?play=PJP,70";
	movies[59] = "cmd/send.do?play=PJP,71";
	movies[60] = "cmd/send.do?play=PJP,72";
	movies[61] = "cmd/send.do?play=PJP,73";
	//大数据产业规划
	movies[62] = "cmd/send.do?play=PJP,39";
	movies[63] = "cmd/send.do?play=PJP,40";
	movies[64] = "cmd/send.do?play=PJP,41";
	movies[65] = "cmd/send.do?play=PJP,42";
	movies[66] = "cmd/send.do?play=PJP,43";
	movies[67] = "cmd/send.do?play=PJP,44";
	function sendCmd(url,index){
		var str = "";
		if(index == "0") {
			window.parent.gotoPage(url);
			return;
		} else if(index == "1") {
			str = "确定关闭投影？";
		} else if(index == "2") {
			str = "确定关闭矩阵屏区域灯光？";
		} else if(index=="3") {
			str = "确定关闭矩阵屏？";
		} else if(index=="4") {
			var zimu = document.getElementById("zimu1").value;
			document.getElementById("form1").submit();
			return;
		} else if(index=="5") {
			var zimu = document.getElementById("zimu2").value;
			document.getElementById("form2").submit();
			return;
		} else if(index=="6") {
			var zimu = document.getElementById("zimu3").value;
			document.getElementById("form3").submit();
			return;
		} else if(index == "7") {
			if(currentIndex < 1) return;
			else {
				url = movies[--window.parent.index];
			}
		} else if(index == "8") {
			url = movies[window.parent.index=window.parent.index==movies.length-1?0:window.parent.index+1];
		} else if(index == "13") {
			window.parent.index = 0;
		}
		if(str != "") {
			if(confirm(str)) {
				document.getElementById("myiframe").src=url;
			}
		} else {
			document.getElementById("myiframe").src=url;
		}
	}
</script>
</head>

<body>
	<div>
    	<img src="page/ipad/images/jzp.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="791,249,1007,249,1007,348,791,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/zjm.jsp','0');" alt="主界面"/>
          <area shape="poly" coords="1027,249,1243,249,1243,348,1027,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/dg.jsp','0');" alt="灯光"/>
          <area shape="poly" coords="1262,249,1478,249,1478,348,1262,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/xt.jsp','0');" alt="序厅"/>
          <area shape="poly" coords="1498,249,1714,249,1714,348,1498,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/hdq.jsp','0');" alt="互动区"/>
          <area shape="poly" coords="1733,249,1949,249,1949,348,1733,348" href="javascript:void(0);" onclick="sendCmd('page/ipad/jsc.jsp','0');" alt="解说词"/>
          <area shape="poly" coords="166,451,434,451,434,541,166,541" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,1&flash=PC22,movie;PC21,movie','9');" alt="主题片"/>
          <area shape="poly" coords="459,451,727,451,727,541,459,541" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,26&flash=PC22,word;PC21,word','10');" alt="欢迎词"/>
          <area shape="poly" coords="750,451,1018,451,1018,541,750,541" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,2&flash=PC22,word;PC21,word','11');" alt="会议主题"/>
          <area shape="poly" coords="1042,451,1310,451,1310,541,1042,541" href="javascript:void(0);" onclick="sendCmd('','12');" alt="领导致辞"/>
          <area shape="poly" coords="1333,452,1601,452,1601,542,1333,542" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,27&flash=PC22,word;PC21,word','13');" alt="宣讲"/>
          <area shape="poly" coords="1625,452,1893,452,1893,542,1625,542" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=PJP,4&flash=PC22,word;PC21,word','14');" alt="启动仪式"/>
          <area shape="poly" coords="1474,714,1705,714,1705,776,1474,776" href="javascript:void(0);" onclick="sendCmd('','4');" alt="欢迎词确定"/>
          <area shape="poly" coords="1471,904,1702,904,1702,966,1471,966" href="javascript:void(0);" onclick="sendCmd('','5');" alt="左侧投影确定"/>
          <area shape="poly" coords="1474,1098,1705,1098,1705,1160,1474,1160" href="javascript:void(0);" onclick="sendCmd('','6');" alt="右侧投影确定"/>
          <area shape="poly" coords="920,1234,1151,1234,1151,1296,920,1296" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=PJP,resume','');" alt="播放"/>
          <area shape="poly" coords="1175,1234,1406,1234,1406,1296,1175,1296" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=PJP,pause','');" alt="暂停"/>
          <area shape="poly" coords="1432,1234,1663,1234,1663,1296,1432,1296" href="javascript:void(0);" onclick="sendCmd('','7');" alt="上一页"/>
          <area shape="poly" coords="1687,1233,1918,1233,1918,1295,1687,1295" href="javascript:void(0);" onclick="sendCmd('','8');" alt="下一页"/>
          <area shape="poly" coords="104,1354,394,1354,394,1484,104,1484" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=TYJALL,1','');" alt="开投影"/>
          <area shape="poly" coords="415,1357,705,1357,705,1487,415,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=TYJALL,0','1');" alt="关投影"/>
          <area shape="poly" coords="721,1357,1011,1357,1011,1487,721,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,1','');" alt="开灯"/>
          <area shape="poly" coords="1027,1357,1317,1357,1317,1487,1027,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJPDG,0','2');" alt="关灯"/>
          <area shape="poly" coords="1330,1357,1620,1357,1620,1487,1330,1487" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJP,1','');" alt="开矩阵"/>
          <area shape="poly" coords="1644,1355,1934,1355,1934,1485,1644,1485" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=PJP,0','3');" alt="关矩阵"/>
        </map>
	</div>
<iframe id="myiframe" name="myiframe" src="" style="display:none;"></iframe>
<form id="form1" action="item/edit.do" method="post" target="myiframe">
    	<input type="hidden" name="item" value="pjp"></input>
		<input type="hidden" name="noticeIpOrCodeName" value="192.168.2.10"></input>
		<input type="hidden" name="noticeData" value="word"></input>
	  	<input id="zimu1" name="value" style="font-size:40px; color:#FFFFFF; position: absolute; z-index: 1002; left: 518px; top: 707px; height: 67px; width: 920px; border-style: none; background-color: transparent;" />
	</form>
    <form id="form2" action="item/edit.do" method="post" target="myiframe">
    	<input type="hidden" name="item" value="tyz"></input>
		<input type="hidden" name="noticeIpOrCodeName" value="192.168.2.21"></input>
		<input type="hidden" name="noticeData" value="word"></input>
	  	<input id="zimu2" name="value" style="font-size:40px; color:#FFFFFF; position: absolute; z-index: 1003; left: 518px; top: 897px; height: 67px; width: 920px; border-style: none; background-color: transparent;" />
	</form>
    <form id="form3" action="item/edit.do" method="post" target="myiframe">
    	<input type="hidden" name="item" value="tyy"></input>
		<input type="hidden" name="noticeIpOrCodeName" value="192.168.2.22"></input>
		<input type="hidden" name="noticeData" value="word"></input>
	  	<input id="zimu3" name="value" style="font-size:40px; color:#FFFFFF; position: absolute; z-index: 1004; left: 518px; top: 1091px; height: 67px; width: 920px; border-style: none; background-color: transparent;" />
	</form>
</body>
</html>