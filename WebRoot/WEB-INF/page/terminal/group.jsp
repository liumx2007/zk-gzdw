<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>设备组管理</title>
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
	<script type="text/javascript">
		function addTab(){
			$("#form_newGroup").form("clear");
			$("#dialog_newGroup").dialog("open");
		}
		function editTab(){
			var tab = $('#tt').tabs('getSelected');
			var id = tab.panel('options').id;
			$.ajax({
				type: "POST",
			    url: "group/get.do?id="+id,
			    dataType : "json",
				success: function(object){
			    	$("#form_editGroup").form("clear");
			    	$("#id").val(object.id);
	 		    	$("#codeName_editGroup").textbox("setValue",object.codeName);
			    	$("#name_editGroup").textbox("setValue",object.name);
			    	$("#serverNodeMac_editGroup").textbox("setValue",object.serverNodeMac);
			    	$("#dialog_editGroup").dialog("open");
				}
			});
		}
		function removeTab(){
			var tab = $('#tt').tabs('getSelected');
			var id = tab.panel('options').id;
			var title = tab.panel('options').title;
			if (tab){
				$.messager.confirm("确认框", "确定删除设备组："+title+"？", function(r){
					if (r){
						$.ajax({
							type: "POST",
						    url: "group/delete.do?id="+id,
						    dataType : "json",
							success: function(object){
								refreshTabs();
						    	window.parent.showMessage(object);
							}, error: function(msg) {
								window.parent.showMessage({type:"error",message:"删除失败！"});
							}
						});
					}
				});
			}
		}
		function refreshTabs() {
			$.ajax({
				type: "POST",
			    url: "group/getAll.do",
				dataType : "json",
				success: function(object){
					closeAll();
					for(var i=0;i<object.length;i++) {
						$("#tt").tabs("add",{
							id:object[i].id,
							title:object[i].name,
							selected:false
						});
						if(object.length>0) {
							$("#tt").tabs("select", 0);
						}
					}
					$("#dialog_newGroup").dialog("close");
				},error: function(msg) {
					window.parent.showMessage({type:"error",message:"设备组获取失败！"});
				}
			});
		}
		function select(title) {
			var tab = $("#tt").tabs("getTab", title);
			if(tab.panel('options').content != null) return;
			$("#tt").tabs("update",{
				tab:tab,
				options:{
					content:'<iframe name="'+tab.panel('options').id+'" src="r/page/terminal/terminal.do" width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>'
				}
			});
		}
		function closeAll(){
 			var tabs = $("#tt").tabs("tabs");
 			$(tabs).each(function(){
 				$("#tt").tabs("close", 0);
 			});
		}
		var buttons_newGroup = [{
			text:"提交",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_newGroup").form('validate')) {
					return;
				}
				$.ajax({
					type: "POST",
				    url: "group/add.do",
					data: $("#form_newGroup").serialize(),
					dataType : "json",
					success: function(object){
						refreshTabs();
						window.parent.showMessage(object);
					},error: function(msg) {
						window.parent.showMessage({type:"error",message:"新建资料夹失败！"});
					}
				});
			}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_newGroup").form("clear");
			}
	 	}];
		var buttons_editGroup = [{
			text:"提交",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_editGroup").form('validate')) {
					return;
				}
				$.ajax({
					type: "POST",
				    url: "group/edit.do",
					data: $("#form_editGroup").serialize(),
					dataType : "json",
					success: function(object){
						$("#dialog_editGroup").dialog("close");
						refreshTabs();
						window.parent.showMessage(object);
					},error: function(msg) {
						window.parent.showMessage({type:"error",message:"修改资料夹失败！"});
					}
				});
			}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_editGroup").form("clear");
			}
	 	}];
		$(function(){
			refreshTabs();
		});
	</script>
</head>

<body>
	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',fit:true,border:false,onSelect:select">
	</div>
	<div id="tab-tools">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addTab()">添加组</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editTab()">修改组</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removeTab()">删除组</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="refreshTabs()">刷新组</a>
	</div>
	<div id="p-tools">
		<a href="javascript:void(0)" class="icon-mini-edit" onclick="alert('edit')"></a>
	</div>
	<div id="dialog_newGroup" class="easyui-dialog" closed="true" title="新建设备组" style="width:400px;height:200px;padding:10px"
   	data-options="iconCls:'icon-pcs-add',closable:true,cache:false,modal:true,buttons:buttons_newGroup">
 		  <form id="form_newGroup">
 		  	<table>
 		  		<tr>
 		  			<td width="150px" align="right">设备组名称：</td>
 		  			<td><input class="easyui-textbox" id="name_newGroup" name="name" data-options="required:true,missingMessage:'设备组名称不能为空',validType:'length[1,10]',invalidMessage:'不能超过10个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">设备组代号：</td>
 		  			<td><input class="easyui-textbox"  id="codeName_newGroup" name="codeName" data-options="required:true,missingMessage:'设备组代号不能为空',validType:'length[1,10]',invalidMessage:'不能超过10个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">节点服务器MAC地址：</td>
 		  			<td><input class="easyui-textbox"  id="serverNodeMac_newGroup" name="serverNodeMac" data-options="validType:'mac'"></td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_editGroup" class="easyui-dialog" closed="true" title="修改设备组" style="width:400px;height:200px;padding: 10px"
   	data-options="iconCls:'icon-pcs-edit',closable:true,cache:false,modal:true,buttons:buttons_editGroup">
 		  <form id="form_editGroup">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
 		  			<td width="150px" align="right">设备组名称：</td>
 		  			<td><input class="easyui-textbox" id="name_editGroup" name="name" data-options="required:true,missingMessage:'设备组名称不能为空',validType:'length[1,10]',invalidMessage:'不能超过10个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">设备组代号：</td>
 		  			<td><input class="easyui-textbox"  id="codeName_editGroup" name="codeName" readonly data-options="required:true,missingMessage:'设备组代号不能为空',validType:'length[1,10]',invalidMessage:'不能超过10个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">节点服务器MAC地址：</td>
 		  			<td><input class="easyui-textbox"  id="serverNodeMac_editGroup" name="serverNodeMac" data-options="validType:'mac'"></td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>