<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>终端内容设置</title>
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
		function loaded() {
			var folders = $("#folder").combobox("getData");
			if(folders.length>0) {
				$("#folder").combobox("select",folders[1].id);
			}
		}
		function change(recode) {
			var id = $("#folder").combobox("getValue");
			if(id) {
				$("#content_list").datagrid({url:"content/getByRoot.do?id="+id});
			}
		}
		function setContent() {
			var rows = $("#content_list").datagrid("getSelections");
			if(rows.length==0) return;
			var ids = ""; 
			for(var index=0;index<rows.length;index++) {
				if (ids == "") { 
					ids = rows[index].id; 
				} else { 
					ids += "," + rows[index].id;
				} 
			}
			$.ajax({
				type: "POST",
			    url: "terminal/distributeContent.do?contentIds="+ids+"&terminalId=${terminal.id}",
			    dataType: 'json',
				success: function(object){
					window.parent.closeDialog();
					// window.parent.parent.showMessage(object);
                    window.parent.parent.showMessage({type:"Success",message:"终端内容设置成功！"});
				}, error: function(msg) {
					window.parent.parent.showMessage({type:"error",message:"终端内容设置失败！"});
				}
			});
		}
		function cancelContent() {
			$.ajax({
				type: "POST",
			    url: "terminal/cancelContent.do?terminalId=${terminal.id}",
			    dataType: 'json',
				success: function(object){
					window.parent.reloadTable();
					window.parent.parent.showMessage(object);
				}, error: function(msg) {
					window.parent.parent.showMessage({type:"error",message:"终端播放内容取消失败！"});
				}
			});
		}
	</script>
</head>

<body>
	<div></div>
	<table class="easyui-datagrid" id="content_list" 
		data-options="idField:'id',split:false,fit:true,border:0,
			frozenColumns:[[{field:'ck',checkbox:true}]],toolbar:'#tb'">
		<thead>
			<tr>
				<th align='center' data-options="field:'folder',width:80,halign:'center',formatter:function(v,r,i){return new Object(v).title;}">所属目录</th>
				<th align='center' data-options="field:'title',width:150,halign:'center'">内容标题</th>
				<th align='center' data-options="field:'fileName',width:150,halign:'center'">文件名</th>
				<th align='center' data-options="field:'type',width:100,halign:'center'">类型</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			根资料夹：<select id="folder" data-options="url:'folder/roots.do',
				valueField:'id',textField:'title',editable:false,onLoadSuccess:loaded,onChange:change"
				class="easyui-combobox" panelHeight="auto" style="width:100px">
			</select>
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',onClick:search">查找</a> -->
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',onClick:cancelContent">取消内容</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',onClick:setContent" style="float:right">确定</a>
		</div>
	</div>
</body>
</html>