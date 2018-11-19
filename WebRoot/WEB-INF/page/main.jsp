<%@page import="com.zzqx.Global"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="resources/css/desktop.css" />
<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/lightbox.css"/>
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/js/lightbox.min.js"></script>
<script type="text/javascript">
	function openWindow(id,page) {
		var iframe = $("#"+id).find("iframe").eq(0);
		if($(iframe).attr("src")=="" || $(iframe).attr("src")==undefined) {
			var src = "r/page/"+page;
			$(iframe).prop("src",src);
		}
		$("#"+id).window("open");
	}
	function showMessage(object) {
		var obj;
		if(object.type == "error") {
			obj = {title:'消息',msg:"<span style='color:red;'>"+object.message+"</span>",timeout:3000,showType:'show'}
		} else if(object.type == "success"){
			obj = {title:'消息',msg:object.message,timeout:3000,showType:'show'}
		}
		$.messager.show(obj);
	}
	function showImg(url){
		$("#photos").empty();
		$("#photos").attr("href",url);
		$("#photos").append("<img class='example-image' src='"+url+"'/>");
		$("#photos").first().click();
	}
</script>
</head>

<body>
<div>
    <ul class="app">
        <li>
            <a href="javascript:void(0);" onclick="openWindow('sbgl','terminal/group');">
            <img src="resources/images/desktop/computer.png">
            <div>设备中心</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('nrgl','content/content');">
            <img src="resources/images/desktop/content.png">
            <div>内容管理</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('rygl','personnel/personnel');">
            <img src="resources/images/desktop/personnel.png">
            <div>人员管理</div></a>
        </li>
        <!-- <li>
            <a href="javascript:void(0);" onclick="openWindow('pbgl','onduty/onduty');">
            <img src="resources/images/desktop/workforce_management.png">
            <div>排班管理</div></a>
        </li> -->
        <li>
            <a href="javascript:void(0);" onclick="openWindow('pbgl','arrange/arrange');">
            <img src="resources/images/desktop/workforce_management.png">
            <div>排班管理</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('tjgl','stat/stat');">
            <img src="resources/images/desktop/stat_management.png">
            <div>统计管理</div></a>
        </li>
        <!-- <li>
            <a href="javascript:void(0);" onclick="openWindow('yygl','order/list');">
            <img src="resources/images/desktop/telephone_record.png">
            <div>预约管理</div></a>
        </li> -->
        <li>
            <a href="javascript:void(0);" onclick="openWindow('yhgl','user/user');">
            <img src="resources/images/desktop/admin.png">
            <div>用户管理</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('yj','yj/list');">
            <img src="resources/images/desktop/telephone_record.png">
            <div>意见建议</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('customer','customer/list');">
            <img src="resources/images/desktop/address_book256.png">
            <div>导入客户数据</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('subject','subject/list');">
            <img src="resources/images/desktop/liti-xitong-desktop-14.png">
            <div>题库</div></a>
        </li>
    </ul>
    <ul class="app">
    	<!-- 
        <li>
            <a href="javascript:void(0);" onclick="openWindow('xtgl');">
            <img src="resources/images/desktop/user_computer.png">
            <div>系统管理</div></a>
        </li>
        <li>
            <a href="javascript:void(0);" onclick="openWindow('dsq');">
            <img src="resources/images/desktop/clock.png">
            <div>定时开关机</div></a>
        </li> -->
        <li>
            <a href="javascript:void(0);" onclick="openWindow('interactionLog','interactionlog/interactionLog');">
                <img src="resources/images/desktop/liti-xitong-desktop-14.png">
                <div>数据</div></a>
        </li>
    </ul>
</div>
<div id="sbgl" class="easyui-window" title="设备中心" data-options="iconCls:'icon-pcs',minimizable:false,shadow:true,closed:true" style="width:920px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="nrgl" class="easyui-window" title="内容库" data-options="iconCls:'icon-contents',minimizable:false,shadow:true,closed:true" style="width:1270px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="rygl" class="easyui-window" title="人员管理" data-options="iconCls:'icon-customer',minimizable:false,shadow:true,closed:true" style="width:1010px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="pbgl" class="easyui-window" title="排班管理" data-options="iconCls:'icon-contents',minimizable:false,shadow:true,closed:true" style="width:1010px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="tjgl" class="easyui-window" title="统计管理" data-options="iconCls:'icon-contents',minimizable:false,shadow:true,closed:true" style="width:1010px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="yygl" class="easyui-window" title="预约管理" data-options="iconCls:'icon-telephone',minimizable:false,shadow:true,closed:true" style="width:880px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="yhgl" class="easyui-window" title="用户管理" data-options="iconCls:'icon-user',minimizable:false,shadow:true,closed:true" style="width:480px;height:300px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="xtgl" class="easyui-window" title="系统管理" data-options="iconCls:'icon-man',minimizable:false,shadow:true,closed:true" style="width:550px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="dsq" class="easyui-window" title="定时开关机管理" data-options="iconCls:'icon-clock',minimizable:false,shadow:true,closed:true" style="width:300px;height:130px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="yj" class="easyui-window" title="意见建议" data-options="iconCls:'icon-comment',minimizable:false,shadow:true,closed:true" style="width:1260px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="customer" class="easyui-window" title="导入客户数据" data-options="iconCls:'icon-customer',minimizable:false,shadow:true,closed:true" style="width:500px;height:200px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="subject" class="easyui-window" title="题库" data-options="iconCls:'icon-comment',minimizable:false,shadow:true,closed:true" style="width:910px;height:600px;overflow:hidden">
	<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<div id="interactionLog" class="easyui-window" title="数据" data-options="iconCls:'icon-comment',minimizable:false,shadow:true,closed:true" style="width:910px;height:600px;overflow:hidden">
    <iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
<a id="photos" class="example-image-link" data-lightbox="example-set" style="display:none;"></a>
<%
if(Global.authorizeStatus == 2) {
%>
<div id="auth" style="position: absolute;top:30px;right:30px;z-index: 1000;color:white;font-size:24px;" onclick="alert('剩余可用时间：<%=Math.ceil(Global.residue/(60*24))+"天"%>')"></div>
<% }%>
</body>
</html>