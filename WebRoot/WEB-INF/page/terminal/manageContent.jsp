<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>设备内容管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/plugins/jquery.parser.js"></script>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript">
		function cz(value, row, index) {
			var cz = "";
           	cz="<a href='javascript:void(0)' onclick='sort(1,\""+row.id+"\")' style='margin-right:5px;'>"+
               "<img src='resources/images/icons/up.png' border='0' alt='上移'/></a>"+
           		"<a href='javascript:void(0)' onclick='sort(-1,\""+row.id+"\")' style='margin-right:5px;'>"+
           		"<img src='resources/images/icons/down.png' border='0' alt='下移'/></a>";
           	if(new Object(row.content).type=="视频文件" || new Object(row.content).type=="网址") {
           		cz += "<a href='javascript:void(0)' onclick='model(\""+row.id+"\");' style='margin-right:5px;'>模式</a>";
           	}
           	cz += "<a href='javascript:void(0)' onclick='del(\""+row.id+"\");'>删除</a>";
	   		return cz;
		}
		function sort(d, id) {
			$.ajax({
				type: "POST",
			    url: "terminal/sortContent.do?direction="+d+"&terminalContentId="+id,
			    dataType: 'json',
				success: function(object){
					if(object.type == "success") {
						$("#content_list").datagrid("reload");
					} else {
						window.parent.parent.showMessage(object);
					}
				}, error: function(msg) {
					window.parent.parent.showMessage({type:"error",message:"排序失败！"});
				}
			});
		}
		function model(id) {
			$("#content_list").datagrid("selectRecord", id);
			var row = $("#content_list").datagrid("getSelected");
			if(new Object(row.content).type=="视频文件") {
				$("input[name='model_video']").each(function(){
					if($(this).val() == row.model) {
						$(this).prop("checked","checked");
					}
				});
				$("#dialog_model_video").dialog("open");
			} else if(new Object(row.content).type=="网址") {
				$("input[name='model_web']").each(function(){
					if($(this).val() == row.model) {
						$(this).prop("checked","checked");
					}
				});
				$("#dialog_model_web").dialog("open");
			}
		}
		function del(id) {
			$.ajax({
				type: "POST",
			    url: "terminal/removeContent.do?terminalContentId="+id,
			    dataType: 'json',
				success: function(object){
					if(object.type == "success") {
						$("#content_list").datagrid("reload");
						window.parent.reloadTable();
					} else {
						window.parent.parent.showMessage(object);
					}
				}, error: function(msg) {
					window.parent.parent.showMessage({type:"error",message:"删除失败！"});
				}
			});
		}
		var buttons_model = [{
			text:"确定",
			iconCls:"icon-ok",
			handler:function(){
				var row = $("#content_list").datagrid("getSelected");
				var model = "1";
				if(new Object(row.content).type=="视频文件") {
					model = $("input[name='model_video']:checked").val();
				} else {
					model = $("input[name='model_web']:checked").val();
				}
				if(row) {
					$.ajax({
						type: "POST",
					    url: "terminal/setModel.do?terminalContentId="+row.id+"&model="+model,
					    dataType: 'json',
						success: function(object){
							$("#dialog_model_video").dialog("close");
							$("#dialog_model_web").dialog("close");
							$("#content_list").datagrid("reload");
							window.parent.parent.showMessage(object);
						}, error: function(msg) {
							window.parent.parent.showMessage({type:"error",message:"模式设置失败！"});
						}
					});
				}
			}},{
				text:"取消",
				iconCls:"icon-undo",
				handler:function(){
					$("#dialog_model_video").dialog("close");
				}
		 	}];
	</script>
</head>

<body>
	<table class="easyui-datagrid" id="content_list" 
		data-options="url:'terminal/terminalContents.do?terminalId=${terminal.id}',
			idField:'id',split:false,fit:true,border:0,singleSelect:true,rownumbers:true">
		<thead>
			<tr>
				<th align='center' data-options="field:'contentTitle',width:100,halign:'center',formatter:function(v,r,i){return new Object(r.content).title}">内容标题</th>
				<th align='center' data-options="field:'contentFileName',width:150,halign:'center',formatter:function(v,r,i){return new Object(r.content).fileName}">文件名</th>
				<th align='center' data-options="field:'contentType',width:80,halign:'center',formatter:function(v,r,i){return new Object(r.content).type}">类型</th>
				<th align='center' data-options="field:'cz',width:120,halign:'center',formatter:cz">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_model_video" class="easyui-dialog" closed="true" title="设置播放模式" style="width:250px;height:120px"
   		data-options="iconCls:'icon-pc-content',closable:true,cache:false,modal:true,buttons:buttons_model">
			<div><label><input name="model_video" type="radio" value="1" checked="checked"/>播放一次</label></div>
			<div><label><input name="model_video" type="radio" value="-1"/>循环播放</label></div>
			<div>自定义模式：<input id="custom_video"/></div>
 	</div>
 	<div id="dialog_model_web" class="easyui-dialog" closed="true" title="设置打开模式" style="width:250px;height:150px"
   		data-options="iconCls:'icon-pc-content',closable:true,cache:false,modal:true,buttons:buttons_model">
			<div><label><input name="model_web" type="radio" value="1"/>内嵌展示</label></div>
			<div><label><input name="model_web" type="radio" value="2" checked="checked"/>使用IE浏览器打开</label></div>
			<div><label><input name="model_web" type="radio" value="3" checked="checked"/>使用谷歌浏览器打开</label></div>
			<div>自定义模式：<input id="custom_web"/></div>
 	</div>
</body>
</html>