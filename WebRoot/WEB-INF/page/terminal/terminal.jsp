<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>设备管理</title>
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
		table td {font-size: 14px;margin: 0px;padding: 0px;}
	</style>
	<script type="text/javascript">
	function cz(value, row, index) {
		var cz = "<a href='javascript:void(0)' onclick='content(\""+row.id+"\");' style='margin-right:5px;'>内容</a>"+
				"<a href='javascript:void(0)' onclick='edit(\""+row.id+"\");' style='margin-right:5px;'>编辑</a>"+
				"<a href='javascript:void(0)' onclick='del(\""+row.id+"\");' style='margin-right:5px;'>删除</a>";
   		return cz;
	}
	function getStatus(v,r,i) {
		var ico = v=="true"?"on.ico":"off.ico";
		return "<img src='resources/images/icons/"+ico+"'/>";
	}
	function getContents(v,r,i) {
		var terminalContents = new Object(v);
		if(terminalContents.length == 0) {
			return "";
		} else if(terminalContents.length == 1) {
			return "<a href='javascript:void(0)' onclick='editContents(\""+r.id+"\")'>"+terminalContents[0].content.title+"</a>";
		} else {
			return "<a href='javascript:void(0)' onclick='editContents(\""+r.id+"\")'>内容组</a>";
		}
	}
	function editContents(terminalId) {
		var iframe = $("#terminalContentManage").find("iframe").eq(0);
		$(iframe).prop("src","terminal/manageContent.do?id="+terminalId);
		$("#terminalContentManage").dialog("open");
	}
	function edit(id) {
		$.ajax({
			type: "POST",
		    url: "terminal/get.do?id="+id,
		    dataType : "json",
			success: function(object){
		    	$("#form_edit").form("clear");
		    	$("#id").val(object.id);
		    	$("#groupId").val(groupId);
		    	$("#serialNumber_edit").numberbox("setValue", object.serialNumber);
		    	if(object.name == "") {
		    		$("#codeName_edit").textbox("readonly", false);
		    	} else {
		    		$("#codeName_edit").textbox("readonly", true);
		    	}
 		    	$("#codeName_edit").textbox("setValue",object.codeName);
		    	$("#name_edit").textbox("setValue",object.name);
		    	$("#ip_edit").textbox("setValue",object.ip);
		    	$("#mac_edit").textbox("setValue",object.mac);
		    	$("#remark_edit").textbox("setValue",object.remark);
		    	$("#dialog_edit").dialog("open");
			}
		});
	}
	function content(id) {
		var iframe = $("#terminalContentSet").find("iframe").eq(0);
		$(iframe).prop("src","terminal/setContent.do?id="+id);
		$("#terminalContentSet").dialog("open");
	}
	function del(id) {
		$.messager.confirm("确认框", '确定删除终端？', function(r){
			if (r){
				$.ajax({
					type: "POST",
				    url: "terminal/delete.do?id="+id,
				    dataType : "json",
					success: function(object){
						if(object.type = "success") {
					    	$('#terminal_table').datagrid("reload");
						}
				    	showMessage(object);
					}, error: function(msg) {
						showMessage({type:"error",message:"删除失败！"});
					}
				});
			}
		});
	}
	function setVol(cmd) {
		var rows = $('#terminal_table').datagrid("getSelections");
		var cmds = ""; 
		for(var index=0;index<rows.length;index++) {
			if (cmds == "") { 
				cmds = rows[index].codeName+","+cmd; 
			} else { 
				cmds += ";" + rows[index].codeName+","+cmd;
			} 
		}
		if(rows.length>0) {
			$.ajax({
				type: "POST",
			    url: "cmd/send.do?client="+cmds,
			    dataType : "json",
				success: function(object){
				}, error: function(msg) {
					showMessage({type:"error",message:"音量设置失败！"});
				}
			});
		}
	}
	function addNew() {
		$("#form_new").form("clear");
		$("#dialog_new").dialog("open");
	}
	function poweroffAll() {
		$.messager.confirm("确认框", '确定关闭全部终端？', function(r){
			if (r){
				$.ajax({
					type: "POST",
				    url: "cmd/poweroffAll.do?groupId="+groupId,
				    dataType : "json",
					success: function(object){
						if(object.type=="success") {
					    	$('#terminal_table').datagrid("reload");
						}
						showMessage(object);
					}, error: function(data) {
						showMessage({type:"error",message:"关闭失败！"});
					}
				});
			}
		});
	}
	function poweronAll() {
		$.messager.confirm("确认框", '确定开启全部终端？', function(r){
			if (r){
				$.ajax({
					type: "POST",
				    url: "cmd/poweronAll.do?groupId="+groupId,
				    dataType : "json",
					success: function(object){
						if(object.type="success") {
					    	$('#terminal_table').datagrid("reload");
						}
				    	showMessage(object);
					}, error: function(data) {
						showMessage({type:"error",message:"开启失败！"});
					}
				});
			}
		});
	}
	function poweroff() {
		var rows = $('#terminal_table').datagrid("getSelections");
		var ids = ""; 
		for(var index=0;index<rows.length;index++) {
			if (ids == "") { 
				ids = "id=" + rows[index].id; 
			} else { 
				ids += "," + rows[index].id;
			} 
		}
		if(rows.length>0) {
			$.messager.confirm("确认框", '确定要关闭吗？', function(r){
				if (r){
					$.ajax({
						type: "POST",
					    url: "cmd/poweroff.do?"+ids,
					    dataType : "json",
						success: function(object){
							if(object.type == "success") {
						    	$('#terminal_table').datagrid("reload");
						    	$("#terminal_table").datagrid("clearSelections");
							}
					    	showMessage(object);
						}, error: function(data) {
							showMessage({type:"error",message:"关闭失败！"});
						}
					});
				}
			});
		}
	}
	function poweron() {
		var rows = $('#terminal_table').datagrid("getSelections");
		var ids = ""; 
		for(var index=0;index<rows.length;index++) {
			if (ids == "") { 
				ids = "id=" + rows[index].id; 
			} else { 
				ids += "," + rows[index].id;
			} 
		}
		if(rows.length>0) {
			$.messager.confirm("确认框", '确定要开机吗？', function(r){
				if (r){
					$.ajax({
						type: "POST",
					    url: "cmd/poweron.do?"+ids,
					    dataType : "json",
						success: function(object){
							if(object.type == "success") {
						    	$('#terminal_table').datagrid("reload");
						    	$("#terminal_table").datagrid("clearSelections");
							}
					    	showMessage(object);
						}, error: function(data) {
							showMessage({type:"error",message:"开机失败！"});
						}
					});
				}
			});
		}
	}
	function reboot() {
		var rows = $('#terminal_table').datagrid("getSelections");
		var ids = ""; 
		for(var index=0;index<rows.length;index++) {
			if (ids == "") {
				ids = "id=" + rows[index].id; 
			} else { 
				ids += "," + rows[index].id;
			} 
		}
		if(rows.length>0) {
			$.messager.confirm("确认框", '确定要重启吗？', function(r){
				if (r){
					$.ajax({
						type: "POST",
					    url: "cmd/reboot.do?"+ids,
					    dataType : "json",
						success: function(object){
							if(object.type == "success") {
						    	$('#terminal_table').datagrid("reload");
						    	$("#terminal_table").datagrid("clearSelections");
							}
					    	showMessage(object);
						}, error: function(data) {
							showMessage({type:"error",message:"重启失败！"});
						}
					});
				}
			});
		}
	}
	function refreshContent() {
		var rows = $('#terminal_table').datagrid("getSelections");
		var ids = ""; 
		for(var index=0;index<rows.length;index++) {
			if (ids == "") { 
				ids = "id=" + rows[index].id; 
			} else { 
				ids += "," + rows[index].id;
			} 
		}
		if(rows.length>0) {
			$.ajax({
				type: "POST",
			    url: "terminal/refresh.do?"+ids,
			    dataType : "json",
				success: function(object){
					if(object.type == "success") {
				    	$('#terminal_table').datagrid("reload");
				    	$("#terminal_table").datagrid("clearSelections");
					}
			    	showMessage(object);
				}, error: function(data) {
					showMessage({type:"error",message:"刷新失败！"});
				}
			});
		}
	}
	function refreshList() {
		$('#terminal_table').datagrid('reload');
	}
	function clearTemp() {
		var rows = $('#terminal_table').datagrid("getSelections");
		var cmds = ""; 
		for(var index=0;index<rows.length;index++) {
			if (cmds == "") { 
				cmds = rows[index].codeName+",clear"; 
			} else { 
				cmds += ";" + rows[index].codeName+",clear";
			} 
		}
		if(rows.length>0) {
			$.ajax({
				type: "POST",
			    url: "cmd/send.do?client="+cmds,
			    dataType : "json",
				success: function(object){
				}, error: function(msg) {
					showMessage({type:"error",message:"音量设置失败！"});
				}
			});
		}
	}
	var buttons_new = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_new").form('validate')) {
				return;
			}
			$.ajax({
				type: "POST",
			    url: "terminal/add.do?groupId="+groupId,
			    data: $("#form_new").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#terminal_table').datagrid("reload");
				    	$("#terminal_table").datagrid("clearSelections");
				    	$("#dialog_new").dialog("close");
					}
			    	showMessage(object);
				},error: function(data) {
					showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
				}
			});
		}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_new").form("clear");
			}
	 	}];
	var buttons_edit = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_edit").form('validate')) {
				return;
			}
			$.ajax({
				type: "POST",
			    url: "terminal/edit.do",
			    data: $("#form_edit").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#terminal_table').datagrid("reload");
				    	$("#terminal_table").datagrid("clearSelections");
				    	$("#dialog_edit").dialog("close");
					}
			    	showMessage(object);
				},error: function(data) {
					showMessage({type:'error',message:"<span style='color:red;'>修改失败！</span>"});
				}
			});
		}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_new").form("clear");
			}
	 	}];
	function closeDialog() {
		$("#terminalContentSet").dialog("close");
		$("#terminal_table").datagrid("reload");
	}
	function reloadTable() {
		$("#terminal_table").datagrid("reload");
	}
	var groupId = window.name;
	$(function(){
		$("#terminal_table").datagrid({
			url: "terminal/group.do?id="+groupId,
			idField: "id",
			split: false,
			fit: true,
			border: 0,
			frozenColumns: [[{field:'ck',checkbox:true}]],
			toolbar: "#tb"
		});
		$("#dialog_new").dialog({
			closed:true,
			title:"新建终端",
			iconCls:'icon-pc-add',
			closable:true,
			cache:false,
			modal:true,
			buttons:buttons_new
		});
		$("#dialog_edit").dialog({
			closed:true,
			title:"修改终端",
			iconCls:'icon-pc-edit',
			closable:true,
			cache:false,
			modal:true,
			buttons:buttons_edit
		});
		$("#terminalContentSet").dialog({
			closed:true,
			title:"终端内容设置",
			iconCls:'icon-pc-content',
			closable:true,
			cache:false,
			modal:true
		});
		$("#terminalContentManage").dialog({
			closed:true,
			title:"终端内容设置",
			iconCls:'icon-pc-content',
			closable:true,
			cache:false,
			modal:true
		});
	});
	function showMessage(object) {
		var topWin=(function(p,c){while(p!=c){c=p;p=p.parent}return c;})(window.parent,window);
		topWin.showMessage(object);
	}
	</script>
</head>

<body>
	<table id="terminal_table">
		<thead>
			<tr>
				<th align='center' data-options="field:'serialNumber',width:60,halign:'center'">终端编号</th>
				<th align='center' data-options="field:'codeName',width:80,halign:'center'">代号</th>
				<th align='center' data-options="field:'status',width:40,halign:'center',formatter:getStatus">状态</th>
				<th align='center' data-options="field:'name',width:110,halign:'center'">终端名称</th>
				<th align='center' data-options="field:'terminalContents',width:110,halign:'center',formatter:getContents">播放内容</th>
				<th align='center' data-options="field:'ip',width:100,halign:'center'">IP地址</th>
				<th align='center' data-options="field:'mac',width:120,halign:'center'">MAC地址</th>
				<th align='center' data-options="field:'cz',width:100,halign:'center',formatter:cz">操作</th>
				<th data-options="field:'remark',width:150">备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<table>
				<tr>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pc-add" plain="true" onclick="addNew()">新建终端</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshContent()">刷新内容</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pc-lightning" plain="true" onclick="clearTemp()">清除本地缓存</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refreshList()">刷新列表</a></td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pcs-delete" plain="true" onclick="poweroffAll()">关闭全部终端</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pcs-lightning" plain="true" onclick="poweronAll()">开启全部终端</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pc-delete" plain="true" onclick="poweroff()">关闭</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pc-restart" plain="true" onclick="reboot()">重启</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-pc-lightning" plain="true" onclick="poweron()">开机</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-sound-up" plain="true" onclick="setVol('VOL_AD 5000')">加大</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-sound-down" plain="true" onclick="setVol('VOL_AD -5000')">减小</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-sound" plain="true" onclick="setVol('VOL_MAX')">最大</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-sound-mute" plain="true" onclick="setVol('VOL_MIN')">静音</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-sound-recover" plain="true" onclick="setVol('VOL_RE')">恢复</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="dialog_new" style="width:400px;height:250px">
 		  <form id="form_new">
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="150px">终端编号：</td>
 		  			<td><input class="easyui-numberbox" id="serialNumber_new" name="serialNumber" 
 		  				data-options="required:true,min:1,max:999,missingMessage:'不能为空'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">代号：</td>
 		  			<td><input class="easyui-textbox" id="codeName_new" name="codeName" 
 		  				data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">终端名称：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_new" name="name" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
				<td align="right">IP地址：</td>
					<td>
						<input class="easyui-textbox" id="ip_new" name="ip" data-options="required:true,validType:'ip',missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">MAC地址：</td>
					<td>
						<input class="easyui-textbox" id="mac_new" name="mac" data-options="required:true,validType:'mac',missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td>
						<input class="easyui-textbox" id="remark_new" name="remark" data-options="validType:'length[0,100]',invalidMessage:'不能超过100个字符！'"/>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" style="width:400px;height:250px">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<input type="hidden" id="groupId" name="group.id"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="150px">编号：</td>
 		  			<td><input class="easyui-numberbox" id="serialNumber_edit" name="serialNumber" 
 		  				data-options="required:true,min:0,max:999,missingMessage:'不能为空'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">代号：</td>
 		  			<td><input class="easyui-textbox" id="codeName_edit" name="codeName" readonly 
 		  				data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">终端名称：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_edit" name="name" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
				<td align="right">IP地址：</td>
					<td>
						<input class="easyui-textbox" id="ip_edit" name="ip" data-options="required:true,validType:'ip',missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">MAC地址：</td>
					<td>
						<input class="easyui-textbox" id="mac_edit" name="mac" data-options="required:true,validType:'mac',missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td>
						<input class="easyui-textbox" id="remark_edit" name="remark" data-options="validType:'length[0,100]',invalidMessage:'不能超过100个字符！'"/>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="terminalContentSet" style="width:530px;height:300px">
		<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
	<div id="terminalContentManage" style="width:500px;height:300px">
		<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</body>
</html>