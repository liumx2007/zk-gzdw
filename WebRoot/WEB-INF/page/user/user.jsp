<%@page import="com.zzqx.mvc.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object userObject = session.getAttribute("user");
User current = userObject == null ? null : (User) userObject;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/plugins/jquery.parser.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#dataGrid').datagrid({
				url: "user/all.do",
				idField: "id",
				border: 0,
				nowrap: true,
				striped: true,
				rownumbers: true,
				fit: true,
				toolbar: [
					{text:'添加操作员',iconCls:'icon-add',handler:function(){
						$("#realName_add").textbox("setValue","");
						$("#userName_add").textbox("setValue","");
						$("#password_add").textbox("setValue","");
						$("#dialog_add").dialog("open");
					}},'-',
					{text:'修改',iconCls:'icon-edit',handler:function(){
						var row = $("#dataGrid").datagrid("getSelected");
						if(<%=current.getRole()%>==<%=User.USER_ROLE_MANAGER%>||"<%=current.getId()%>"==row.id) {
							if (row) {
								$.ajax({
									type: "POST",
								    url: "user/get.do?id="+row.id,
									dataType : "json",
									success: function(object){
										$("#form_edit").form("clear");
										$("#id_edit").val(object.id);
								    	$("#realName_edit").textbox("setValue", object.realName);
								    	$("#userName_edit").textbox("setValue", object.userName);
									}
								});
								$("#dialog_edit").dialog("open");
				            }
						}
					}},'-',
					{text:'删除',iconCls:'icon-remove',handler:function(){
						var rows = $('#dataGrid').datagrid("getSelections");
						var ids = ""; 
						for(var index=0;index<rows.length;index++) {
							if(rows[index].id == "1") {
								continue;
							}
							if (ids == "") { 
								ids = "id=" + rows[index].id; 
							} else { 
								ids += "&id=" + rows[index].id;
							} 
						}
						if(ids != "") {
							$.messager.confirm('提示', '是否删除选中数据?', function (r) { 
								if(r){
									$.ajax({
										type: "POST",
									    url: "user/delete.do",
										data: ids,
										dataType : "json",
										success: function(object){
											if(object.type=="success") {
										    	$('#dataGrid').datagrid("reload");
										    	$("#dataGrid").datagrid("clearSelections");
											}
											window.parent.showMessage(object);
										},
										error: function(msg) {
											window.parent.showMessage({type:'error',message:"<span style='color:red;'>删除失败！</span>"});
										}
									});
								}
							});
						}
					}}
				],
				frozenColumns: [[
	                {field:'ck',checkbox:true}
				]],
		        columns: [[
					{field:'realName',title:'姓名',width:200,align:'center'},
					{field:'role',title:'角色',width:200,align:'center',formatter:function(v,r,i){return v==0?"管理员":"操作员"}}
				]]
			});
			$("#dialog_add").dialog({						
				closable: true,
				cache: false,
				modal: true,
				buttons:[{
					text:"提交",
					iconCls:"icon-ok",
					handler:function(){
						if(!$("#form_add").form('validate')) {
							return;
						}
						$.ajax({
							type: "POST",
						    url: "user/add.do",
							data: $("#form_add").serialize(),
							dataType : "json",
							success: function(object){
								if(object.type=="success") {
							    	$('#dataGrid').datagrid("reload");
							    	$("#dataGrid").datagrid("clearSelections");
							    	$("#dialog_add").dialog("close");
								}
								window.parent.showMessage(object);
							},
							error: function(msg) {
								window.parent.showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
							}
						});
					}
			 	},{
					text:"清除",
					iconCls:"icon-undo",
					handler:function(){
			 			$("#form_add").form("clear");
					}
			 	}]
			});
			$("#dialog_edit").dialog({						
				closable: true,
				cache: false,
				modal: true,
				buttons:[{
					text:"提交",
					iconCls:"icon-ok",
					handler:function(){
						if(!$("#form_edit").form('validate')) {
							return;
						}
						$.ajax({
							type: "POST",
						    url: "user/edit.do",
							data: $("#form_edit").serialize(),
							dataType : "json",
							success: function(object){
								if(object.type=="success") {
							    	$('#dataGrid').datagrid("reload");
							    	$("#dataGrid").datagrid("clearSelections");
							    	$("#dialog_edit").dialog("close");
								}
								window.parent.showMessage(object);
							},
							error: function(msg) {
								window.parent.showMessage({type:'error',message:"<span style='color:red;'>修改失败！</span>"});
							}
						});
					}
			 	}]
			});
		});
		function formatItem(row){            
			var s = '<span style="font-weight:bold">' + row.roleName + '</span>';
			if($.trim(row.remark)!="") {
				s += '<br/><span style="color:#888;margin-left:10px">' + row.remark + '</span>';
			}
			return s;
		}
	</script>
  </head>
  
  <body>
  	<table id="dataGrid"></table>
  	<div id="dialog_add" closed="true" title="添加" data-options="iconCls:'icon-add'" style="width:300px;height:200px;padding:10px">
  		  <form id="form_add">
  		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">姓名：</td>
 		  			<td><input name="realName" id="realName_add" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">用户名：</td>
 		  			<td><input name="userName" id="userName_add" class="easyui-textbox" data-options="required:true,validType:'length[5,15]',missingMessage:'不能为空',invalidMessage:'请输入5至15个字符！'"/></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">密码：</td>
 		  			<td><input type="password" name="password" id="password_add" value="" class="easyui-textbox" data-options="required:true,validType:'length[5,15]',missingMessage:'不能为空',invalidMessage:'请输入5至15个字符！'"/></td>
 		  		</tr>
 		  	</table>
 		  </form>
  	</div>
  	<div id="dialog_edit" closed="true" title="修改" data-options="iconCls:'icon-edit'" style="width:300px;height:210px;padding:10px">
  		  <form id="form_edit">
  		  	<input type="hidden" name="id" id="id_edit"/>
  		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">姓名：</td>
 		  			<td><input name="realName" id="realName_edit" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">用户名：</td>
 		  			<td><input name="userName" id="userName_edit" class="easyui-textbox" data-options="required:true,validType:'length[5,15]',missingMessage:'不能为空',invalidMessage:'请输入5至15个字符！'"/></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">密码：</td>
 		  			<td><input type="password" name="password" id="password_edit" value="" class="easyui-textbox" data-options="validType:'length[5,15]',invalidMessage:'请输入5至15个字符！'"/></td>
 		  		</tr>
 		  		<tr>
 		  			<td></td>
 		  			<td>密码为空则不修改</td>
 		  		</tr>
 		  	</table>
 		  </form>
  	</div>
  </body>
</html>
