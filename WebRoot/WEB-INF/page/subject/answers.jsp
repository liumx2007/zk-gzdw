<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>答案管理</title>
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
		$(function(){
			$("#answer_table").datagrid({
				url: "subject/getAnswers.do?subjectId=${subjectId}",
				idField: "id",
				split:false,
				fit:true,
				border:0,
				frozenColumns: [[{field:'ck',checkbox:true}]],
				rownumbers:true,
				toolbar: '#bar'
			});
		});
		function cz(value, row, index) {
			var cz = "";
           	cz="<a href='javascript:void(0)' onclick='sort(1,\""+row.id+"\")' style='margin-right:5px;'>"+
               "<img src='resources/images/icons/up.png' border='0' alt='上移'/></a>"+
           		"<a href='javascript:void(0)' onclick='sort(-1,\""+row.id+"\")' style='margin-right:5px;'>"+
           		"<img src='resources/images/icons/down.png' border='0' alt='下移'/></a>";
	   		return cz;
		}
		var buttons_new = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_new").form('validate')) {
				return;
			}
			var isRight = $("#form_new input[name='isRight']:checked").val();
			if(isRight != "0" && isRight != "1") {
				alert("请设置是否为正确答案！");
				return;
			}
			$.ajax({
				type: "POST",
			    url: "subject/addAnswer.do?subjectId=${subjectId}",
			    data: $("#form_new").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#answer_table').datagrid("reload");
				    	$("#answer_table").datagrid("clearSelections");
				    	$("#dialog_new").dialog("close");
					}
			    	window.top.showMessage(object);
				},error: function(data) {
					window.top.showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
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
			    url: "subject/editAnswer.do",
			    data: $("#form_edit").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#answer_table').datagrid("reload");
				    	$("#answer_table").datagrid("clearSelections");
				    	$("#dialog_edit").dialog("close");
					}
			    	window.top.showMessage(object);
				},error: function(data) {
					window.top.showMessage({type:'error',message:"<span style='color:red;'>修改失败！！！</span>"});
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
			var rows = $('#answer_table').datagrid("getSelections");
			if(rows.length == 0) return;
			var row = rows[0];
			$.ajax({
				type: "POST",
			    url: "subject/getAnswer.do?id="+row.id,
			    dataType : "json",
				success: function(object){
			    	$("#form_edit").form("clear");
			    	$("#id").val(object.id);
			    	$("#content_edit").textbox("setValue", object.content);
			    	$("#form_edit input[name='isRight'][value="+object.isRight+"]").prop("checked",true); 
			    	$("#dialog_edit").dialog("open");
				}
			});
		}
		function del() {
			var rows = $('#answer_table').datagrid("getSelections");
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
					    url: "subject/deleteAnswers.do?"+ids,
					    dataType : "json",
						success: function(object){
							if(object.type = "success") {
						    	$('#answer_table').datagrid("reload");
						    	$("#answer_table").datagrid("clearSelections");
							}
					    	window.top.showMessage(object);
						}, error: function(msg) {
							window.top.showMessage({type:"error",message:"删除失败！"});
						}
					});
				}
			});
		}
		function sort(d, id) {
			$.ajax({
				type: "POST",
			    url: "subject/sortAnswer.do?direction="+d+"&id="+id,
			    dataType: 'json',
				success: function(object){
					if(object.type == "success") {
						$("#answer_table").datagrid("reload");
					} else {
						window.top.showMessage(object);
					}
				}, error: function(msg) {
					window.top.showMessage({type:"error",message:"排序失败！"});
				}
			});
		}
		function getIsRight(value,row,index) {
			if(value == 0) {
				return "<span style='color:red;'>否</span>";
			} else if(value == 1) {
				return "<span style='color:green;'>是</span>";
			}
		}
		function reload() {
			$("#answer_table").datagrid("reload");
		}
	</script>
</head>

<body>
	<div id="bar" style="padding:5px;height:auto" border="false">
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
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="reload();" iconCls="icon-reload" plain="true"></a>
		            </td>
		        </tr>
		    </table>
	    </div>
	</div>
	<table class="easyui-datagrid" id="answer_table">
		<thead>
			<tr>
				<th align='left' data-options="field:'content',width:270,halign:'center'">答案</th>
				<th align='center' data-options="field:'isRight',width:80,halign:'center',formatter:getIsRight">是否正确</th>
				<th align='center' data-options="field:'cz',width:100,halign:'center',formatter:cz">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_new" class="easyui-dialog" closed="true" title="添加" style="width:300px;height:220px"
		data-options="iconCls:'icon-comment-add',closable:true,cache:false,modal:true,buttons:buttons_new">
 		  <form id="form_new">
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">内容：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="content_new" name="content" style="width:200px;height:100px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">是否正确：</td>
 		  			<td>
 		  				<label><input type="radio" name="isRight" value="1" />正确</label>
    					<label><input type="radio" name="isRight" value="0" />错误</label>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" class="easyui-dialog" closed="true" title="修改" style="width:450px;height:300px"
 		data-options="iconCls:'icon-comment-edit',closable:true,cache:false,modal:true,buttons:buttons_edit">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
					<td align="right" width="80px">内容：</td>
					<td>
						<input class="easyui-textbox" id="content_edit" name="content" style="width:300px;height:150px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,2000]',invalidMessage:'不能超过2000个字符！'"/>
					</td>
				</tr>
				<tr>
 		  			<td align="right" width="80px">是否正确：</td>
 		  			<td>
 		  				<label><input type="radio" name="isRight" value="1" />正确</label>
    					<label><input type="radio" name="isRight" value="0" />错误</label>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>