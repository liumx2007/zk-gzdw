<%@page import="com.zzqx.mvc.entity.Order"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>题库管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/plugins/datagrid-detailview.js"></script>
	<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript">
	$(function(){
		$("#subject_table").datagrid({
			url: "subject/list.do",
			idField: "id",
			split: false,
			fit: true,
			border: 0,
			view: detailview,
			detailFormatter:function(index,row){
				return '<table>'+
					'<tr><td style="width:70px;">题目详解：</td><td style="width:600px;">'+row.explainDetail+'</td></tr>'+
				'</table>';
			},
			frozenColumns: [[{field:'ck',checkbox:true}]],
			pagination:true,
			rownumbers: true,
			toolbar: "#bar"
		});
		var p = $('#subject_table').datagrid('getPager');
		if (p){
			$(p).pagination({
		        pageList: [10,20,30],//可以设置每页记录条数的列表 
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		        pageSize:20
			});
		};
		$("#dialog_new").dialog({
			closed:true,
			title:"添加",
			iconCls:'icon-comment-add',
			closable:true,
			cache:false,
			modal:true,
			buttons:buttons_new
		});
		$("#dialog_edit").dialog({
			closed:true,
			title:"修改",
			iconCls:'icon-comment-edit',
			closable:true,
			cache:false,
			modal:true,
			buttons:buttons_edit
		});
		$("#dialog_addBatch").dialog({
			closed:true,
			title:"批量添加",
			iconCls:'icon-page-excel',
			closable:true,
			cache:false,
			modal:true,
			buttons:buttons_addBatch
		});
		$("#iframe_answers").dialog({
			closed:true,
			title:"答案列表",
			iconCls:'icon-pc-content',
			closable:true,
			cache:false,
			modal:true,
			onClose: reload
		});
	});
	var buttons_new = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_new").form('validate')) {
				return;
			}
			$.ajax({
				type: "POST",
			    url: "subject/add.do",
			    data: $("#form_new").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#subject_table').datagrid("reload");
				    	$("#subject_table").datagrid("clearSelections");
				    	$("#dialog_new").dialog("close");
					}
			    	window.parent.showMessage(object);
				},error: function(data) {
					window.parent.showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
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
			    url: "subject/edit.do",
			    data: $("#form_edit").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#subject_table').datagrid("reload");
				    	$("#subject_table").datagrid("clearSelections");
				    	$("#dialog_edit").dialog("close");
					}
			    	window.parent.showMessage(object);
				},error: function(data) {
					window.parent.showMessage({type:'error',message:"<span style='color:red;'>修改失败！</span>"});
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
	var buttons_addBatch = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_addBatch").form('validate')) {
				return;
			}
			$("#dialog_addBatch").dialog("close");
			$.blockUI({ message: "<img src='resources/images/icons/loading.gif'/>正在提取，请稍后..."});
			$.ajaxFileUpload ({
				url: "subject/addBatch.do",
				secureuri: false,
				fileElementId: ['file_addBatch'],
				dataType: 'text',
				type: 'post',
				success: function (object, status){
					object = eval('(' + object + ')');
					if(object.type == "success") {
						$("#subject_table").datagrid("reload");
					}
					window.parent.showMessage(object);
					$.unblockUI();
                },error: function (data, status, e){
                	window.parent.showMessage({type:"error",message:"上传失败！"});
                	$.unblockUI();
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
	function add() {
		$("#form_new").form("clear");
		$("#dialog_new").dialog("open");
		$("#am_new").prop("checked","checked");
	}
	function edit() {
		var rows = $('#subject_table').datagrid("getSelections");
		if(rows.length == 0) return;
		var row = rows[0];
		$.ajax({
			type: "POST",
		    url: "subject/get.do?id="+row.id,
		    dataType : "json",
			success: function(object){
		    	$("#form_edit").form("clear");
		    	$("#id").val(object.id);
		    	$("#content_edit").textbox("setValue", object.content);
		    	$("#explainDetail_edit").textbox("setValue", object.explainDetail);
		    	$("#dialog_edit").dialog("open");
			}
		});
	}
	function del() {
		var rows = $('#subject_table').datagrid("getSelections");
		if(rows.length == 0) return;
		var ids = ""; 
		for(var index=0;index<rows.length;index++) {
			if (ids == "") { 
				ids = "id=" + rows[index].id; 
			} else { 
				ids += "," + rows[index].id;
			} 
		}
		$.messager.confirm("确认框", '确定删除选中信息？', function(r){
			if (r){
				$.ajax({
					type: "POST",
				    url: "subject/delete.do?"+ids,
				    dataType : "json",
					success: function(object){
						if(object.type = "success") {
					    	$('#subject_table').datagrid("reload");
					    	$("#subject_table").datagrid("clearSelections");
						}
				    	window.parent.showMessage(object);
					}, error: function(msg) {
						window.parent.showMessage({type:"error",message:"删除失败！"});
					}
				});
			}
		});
	}
	function addBatch() {
		$("#form_addBatch").form("clear");
		$("#dialog_addBatch").dialog("open");
	}
	function getType(value,row,index) {
		if(value == 2) {
			return "<span>多选题</span>";
		} else if(value == 1) {
			return "<span>单选题</span>";
		} else {
			return "<span>未设答案</span>";
		}
	}
	function reload() {
		$("#subject_table").datagrid("reload");
	}
	function doSearch() {
		var data = {};
		var array = $("#form_search").serializeArray();
		$(array).each(function(){
			data[this.name]=this.value;
		});
		$("#subject_table").datagrid("reload", data);
	}
	function cz(value, row, index) {
		var cz = "<a href='javascript:void(0)' onclick='answer(\""+row.id+"\");' style='margin-right:5px;'>答案管理</a>";
   		return cz;
	}
	function answer(id) {
		var iframe = $("#iframe_answers").find("iframe").eq(0);
		$(iframe).prop("src","subject/answers.do?id="+id);
		$("#iframe_answers").dialog("open");
	}
	</script>
</head>

<body>
	<div id="bar" border="false">
		<div style="margin: 0px;">
			<table cellspacing="0" cellpadding="0">
		        <tr>
		            <td>
		            <a href="javascript:void(0);" class="easyui-linkbutton" onclick="add();" iconCls="icon-add" plain="true">添加</a>
		            </td>
		            <td><div class="datagrid-btn-separator"></div></td>
		            <td>
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="edit();" iconCls="icon-edit" plain="true">编辑</a>
		            </td>
		            <td><div class="datagrid-btn-separator"></div></td>
		            <td>
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove" plain="true">删除</a>
		            </td>
		            <td><div class="datagrid-btn-separator"></div></td>
		            <td>
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addBatch();" iconCls="icon-page-excel" plain="true">批量添加</a>
		            </td>
		            <td><div class="datagrid-btn-separator"></div></td>
		            <td>
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="reload();" iconCls="icon-reload" plain="true"></a>
		            </td>
		        </tr>
		    </table>
	    </div>
		<div>
			<form id="form_search" style="margin: 0px;">
			题目：
			<input class="easyui-textbox" name="queryParameter.content_li" style="width:200px">
			类型：
			<select class="easyui-combobox" name="queryParameter.type_eq" style="width:85px;">
				<option value="">全部</option>
				<option value="0">未设答案</option>
				<option value="1">单选题</option>
				<option value="2">多选题</option>
			</select>
			<a href="javascript:void(0);" onclick="doSearch();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</form>
		</div>
	</div>
	<table id="subject_table">
		<thead>
			<tr>
				<th align='left' data-options="field:'content',width:600,halign:'center'">题目</th>
				<th align='center' data-options="field:'type',width:100,halign:'center',formatter:getType">类型</th>
				<th align='center' data-options="field:'cz',width:100,halign:'center',formatter:cz">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_new" style="width:400px;height:350px">
 		  <form id="form_new">
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">内容：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="content_new" name="content" style="width:300px;height:150px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">详解：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="explainDetail_new" name="explainDetail" style="width:300px;height:100px" 
							data-options="multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" style="width:450px;height:350px">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
					<td align="right">内容：</td>
					<td>
						<input class="easyui-textbox" id="content_edit" name="content" style="width:300px;height:150px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
					</td>
				</tr>
				<tr>
 		  			<td align="right" width="80px">详解：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="explainDetail_edit" name="explainDetail" style="width:300px;height:100px" 
							data-options="multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_addBatch" style="width:400px;height:150px;padding:15px;">
 		  <form id="form_addBatch">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
					<td align="right">excel文件：</td>
					<td>
						<div><input type="file" id="file_addBatch" name="file" accept="text/csv,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"></div>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="iframe_answers" style="width:530px;height:400px">
		<iframe width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</body>
</html>