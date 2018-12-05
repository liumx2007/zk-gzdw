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
<title>武汉长飞展厅控制界面</title>
<style>
	body {background:#0C2249;margin:0;padding:0;}
</style>
<script type="text/javascript">
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
	function sendCmd(url, msg){
		if(msg != "") {
			if("0" == msg) {
				if(url != "") {
					window.location.href=url;
				}
			} else if("1" == msg) {
				document.getElementById("form1").submit();
				return;
			} else if("2" == msg) {
				document.getElementById("form2").submit();
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
    	<img src="images/ipad.jpg" usemap="#Map" border="0"/>
        <map name="Map">
          <area shape="poly" coords="1413,203,1630,203,1630,287,1413,287" href="javascript:void(0);" onclick="sendCmd('cmd/poweron.do?codeName=WHCF1-22,WHCF1-24,WHCF1-25','')" alt="主机开"/>
          <area shape="poly" coords="684,480,901,480,901,564,684,564" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DG,0','确定关闭灯光？')" alt="灯光关"/>
          <area shape="poly" coords="1638,342,1855,342,1855,426,1638,426" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG07,0','确定关闭屏幕？')" alt="屏幕关"/>
          <area shape="poly" coords="1639,203,1856,203,1856,287,1639,287" href="javascript:void(0);" onclick="sendCmd('cmd/poweroff.do?codeName=WHCF1-22,WHCF1-24,WHCF1-25','确定关闭主机？')" alt="主机关"/>
          <area shape="poly" coords="682,201,899,201,899,285,682,285" href="javascript:void(0);" onclick="sendCmd('cmd/close.do','确定闭馆？')" alt="整馆关"/>
          <area shape="poly" coords="456,341,673,341,673,425,456,425" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_55PJP,1','')" alt="拼接屏开"/>
          <area shape="poly" coords="456,201,673,201,673,285,456,285" href="javascript:void(0);" onclick="sendCmd('cmd/open.do','')" alt="整馆开"/>
          <area shape="poly" coords="682,341,899,341,899,425,682,425" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_55PJP,0','确定关闭拼接屏？')" alt="拼接屏关"/>
          
          <area shape="poly" coords="1412,342,1629,342,1629,426,1412,426" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_HJ_DG07,1','')" alt="屏幕开"/>
          <area shape="poly" coords="458,480,675,480,675,564,458,564" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?con=GY_BAT_DG,1','')" alt="灯光开"/>
		  <area shape="poly" coords="456,636,673,636,673,720,456,720" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,2','')" alt="2016年宣传片（中文）"/>
          <area shape="poly" coords="683,638,900,638,900,722,683,722" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,6','')" alt="2016年宣传片（英文）"/>
          <area shape="poly" coords="910,638,1127,638,1127,722,910,722" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,5','')" alt="2016年中汇报片"/>
          <area shape="poly" coords="1134,637,1351,637,1351,721,1134,721" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,4','')" alt="光纤生产工艺"/>
          <area shape="poly" coords="1359,637,1576,637,1576,721,1359,721" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,3','')" alt="光缆生产工艺"/>
          <area shape="poly" coords="1584,637,1801,637,1801,721,1584,721" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?play=WHCF1-22,1','')" alt="欢迎词"/>
          <area shape="poly" coords="456,727,673,727,673,811,456,811" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?client=WHCF1-22,cancel','')" alt="VR"/>
          <area shape="poly" coords="683,727,900,727,900,811,683,811" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?toServer=10.100.10.23,8000,&ge;cal2，4&gt;','')" alt="触摸桌"/>
          <area shape="poly" coords="909,727,1126,727,1126,811,909,811" href="javascript:void(0);" onclick="sendCmd('cmd/send.do?toServer=10.100.10.23,8000,&ge;cal2，1&gt;','')" alt="大屏主机"/>
          <area shape="poly" coords="1631,1005,1848,1005,1848,1089,1631,1089" href="javascript:void(0);" onclick="sendCmd('','2')" alt="欢迎词英文确定"/>
          <area shape="poly" coords="1630,897,1847,897,1847,981,1630,981" href="javascript:void(0);" onclick="sendCmd('','1')" alt="欢迎词确定"/>
        </map>
</div>
<iframe id="iframe" name="iframe" src="" style="display:none;"></iframe>
<form id="form1" action="item/edit.do" method="post" target="iframe">
   	<input type="hidden" name="item" value="hyc"></input>
	<input type="hidden" name="noticeIpOrCodeName" value="10.100.10.22"></input>
	<input type="hidden" name="noticeData" value="word"></input>
    <input name="value" style="font-size: 40px; color: #0000FF; position: absolute; z-index: 1002; left: 174px; top: 908px; height: 67px; width: 1370px; border-style: none; background-color: transparent;" />
</form>
<form id="form2" action="item/edit.do" method="post" target="iframe">
   	<input type="hidden" name="item" value="hycEn"></input>
	<input type="hidden" name="noticeIpOrCodeName" value="10.100.10.22"></input>
	<input type="hidden" name="noticeData" value="wordEn"></input>
    <input name="value" style="font-size: 40px; color: #0000FF; position: absolute; z-index: 1002; left: 175px; top: 1020px; height: 67px; width: 1370px; border-style: none; background-color: transparent;" />
</form>
</body>
</html>