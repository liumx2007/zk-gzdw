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
	<title>集团客户业务人员管理</title>
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
	<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript">
	$(function(){
		$("#yj_table").datagrid({
			url: "yj/getDkhList.do",
			idField: "id",
			split: false,
			fit: true,
			border: 0,
			frozenColumns: [[{field:'ck',checkbox:true}]],
			rownumbers: true,
			toolbar: "#bar",
		});
	});
	var buttons_new = [{
		text:"确定",
		iconCls:"icon-ok",
		handler:function(){
			if(!$("#form_new").form('validate')) {
				return;
			}
			var fileName = $("#file_new").val();
			var fileType = fileName.substring(fileName.lastIndexOf("."));
			if (fileName != "" && fileType.toLowerCase() != ".jpg" && fileType.toLowerCase() != ".jpeg" && fileType.toLowerCase() != ".png" && fileType.toLowerCase() != ".gif") {
				alert("请上传合法的图片文件！");
			}
			var data = $("#form_new").serializeObject();
			$.ajaxFileUpload({
				url : "yj/addDkh.do",
				secureuri : false,
				fileElementId : [ 'file_new' ],
				data : data,
				dataType : 'text',
				type : 'post',
				success : function(object, status) {
					object = eval('(' + object + ')');
					if (object.type == "success") {
						$("#yj_table").datagrid("reload");
						$("#yj_table").datagrid("clearSelections");
				    	$("#dialog_new").dialog("close");
					}
					window.parent.showMessage(object);
				},
				error : function(data, status, e) {
					window.parent.showMessage({
						type : "error",
						message : "添加失败！"
					});
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
			var fileName = $("#file_edit").val();
			var fileType = fileName.substring(fileName.lastIndexOf("."));
			if (fileName != "" && fileType.toLowerCase() != ".jpg" && fileType.toLowerCase() != ".jpeg" && fileType.toLowerCase() != ".png" && fileType.toLowerCase() != ".gif") {
				alert("请上传合法的图片文件！");
			}
			var data = $("#form_edit").serializeObject();
			$.ajaxFileUpload({
				url : "yj/editDkh.do",
				secureuri : false,
				fileElementId : [ 'file_edit' ],
				data : data,
				dataType : 'text',
				type : 'post',
				success : function(object, status) {
					object = eval('(' + object + ')');
					if (object.type == "success") {
						$('#yj_table').datagrid("reload");
				    	$("#yj_table").datagrid("clearSelections");
				    	$("#dialog_edit").dialog("close");
					}
					window.parent.showMessage(object);
				},
				error : function(data, status, e) {
					window.parent.showMessage({
						type : "error",
						message : "添加失败！"
					});
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
	}
	function edit() {
		var rows = $('#yj_table').datagrid("getSelections");
		if(rows.length == 0) return;
		var row = rows[0];
		$.ajax({
			type: "POST",
		    url: "yj/getDkhById.do?id="+row.id,
		    dataType : "json",
			success: function(object){
		    	$("#form_edit").form("clear");
		    	$("#id").val(object.id);
		    	console.log(object.personnelId);
		    	$("#personnelId_edit").combobox("setValue", object.personnelId);
		    	$("#role_edit").combobox("setValue", object.role);
		    	$("#dialog_edit").dialog("open");
			}
		});
	}
	function del() {
		var rows = $('#yj_table').datagrid("getSelections");
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
				    url: "yj/deleteDkh.do?"+ids,
				    dataType : "json",
					success: function(object){
						if(object.type = "success") {
					    	$('#yj_table').datagrid("reload");
					    	$("#yj_table").datagrid("clearSelections");
						}
				    	window.parent.showMessage(object);
					}, error: function(msg) {
						window.parent.showMessage({type:"error",message:"删除失败！"});
					}
				});
			}
		});
	}
	function getRole(value,row,index) {
		return value == 1 ? "客户经理" : "客户专员";
	}
	function getIsShow(value,row,index) {
		if(value==1) {
			return "<span style='color: green;'>显示中</span>";
		} else {
			return "<span style='color: #999999;'>未显示</span>";
		}
	}
	function getFlag(value,row,index) {
		if(value==1) {
			return "<span style='color: green;'>是</span>";
		} else {
			return "<span style='color: #999999;'>否</span>";
		}
	}
	function getDkhPhoto(value,row,index) {
		if(value != '' && value != null) {
	     	value = '<img style="width:60px; height:60px" src="'+ value + '">';
		}
     	return  value;
	}
	function reload() {
		$("#yj_table").datagrid("reload");
	}
	function doSearch() {
		var data = {};
		var array = $("#form_search").serializeArray();
		$(array).each(function(){
			if((this.name=="queryParameter.isShow_eq" && this.value != "")
					|| this.name!="queryParameter.isShow_eq")
			data[this.name]=this.value;
		});
		data["isSearch"] = "1";
		$("#yj_table").datagrid("reload", data);
	}
	function cz(value, row, index) {
		var show = row.isShow == 1 ? "隐藏":"显示";
		var flag = row.flag == 1 ? "取消优先":"优先显示";
		var cz = "<a href='javascript:void(0)' onclick='toggle(\""+row.id+"\",\""+(row.isShow==1?0:1)+"\",\""+row.flag+"\");' style='margin-right:5px;'>"+show+"</a>"+
		"<a href='javascript:void(0)' onclick='toggle(\""+row.id+"\",\""+row.isShow+"\",\""+(row.flag==1?0:1)+"\");' style='margin-right:5px;'>"+flag+"</a>";
   		return cz;
	}
	function toggle(id, isShow, flag) {
		$.ajax({
				type: "POST",
			    url: "yj/toggle.do?id="+id+"&isShow="+isShow+"&flag="+flag,
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#yj_table').datagrid("reload");
				    	$("#yj_table").datagrid("clearSelections");
					}
			    	window.parent.showMessage(object);
				},error: function(data) {
					window.parent.showMessage({type:'error',message:"<span style='color:red;'>操作失败！</span>"});
				}
			});
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
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="reload();" iconCls="icon-reload" plain="true"></a>
		            </td>
		        </tr>
		    </table>
	    </div>
	</div>
	<table id="yj_table">
		<thead>
			<tr>
				<th align='center' data-options="field:'name',width:150,halign:'center'">姓名</th>
				<th align='center' data-options="field:'dkhPhoto',width:150,halign:'center',formatter:getDkhPhoto">头像</th>
				<th align='center' data-options="field:'role',width:120,halign:'center',formatter:getRole">职位</th>
				<th align='center' data-options="field:'jobNum',width:100,halign:'center'">工号</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_new" class="easyui-dialog" closed="true" title="添加" style="width:400px;height:300px"
		data-options="iconCls:'icon-user-add',closable:true,cache:false,modal:true,buttons:buttons_new">
 		  <form id="form_new">
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">员工：</td>
 		  			<td>
 		  				<input id="personnelId_new" class="easyui-combobox" name="personnelId" data-options="multiple:false,required:true,valueField:'personnelId',textField:'name',url:'yj/getPersonnelList.do'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">职位：</td>
 		  			<td>
 		  				<select id="role_new" class="easyui-combobox" name="role" style="width:200px;" data-options="multiple:false,required:true">   
						    <option value="2">客户专员</option>
						    <option value="1">客户经理</option>   
						</select>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">头像：</td>
 		  			<td>
 		  				<input type="file" id="file_new" name="file">
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" class="easyui-dialog" closed="true" title="修改" style="width:400px;height:300px"
 		data-options="iconCls:'icon-user-edit',closable:true,cache:false,modal:true,buttons:buttons_edit">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">员工：</td>
 		  			<td>
 		  				<input id="personnelId_edit" class="easyui-combobox" name="personnelId" data-options="multiple:false,required:true,valueField:'personnelId',textField:'name',url:'yj/getPersonnelList.do'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">职位：</td>
 		  			<td>
 		  				<select id="role_edit" class="easyui-combobox" name="role" style="width:200px;" data-options="multiple:false,required:true">   
						    <option value="2">客户专员</option>
						    <option value="1">客户经理</option>   
						</select>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">头像：</td>
 		  			<td>
 		  				<div><input type="file" id="file_edit" name="file"></div>
 		  				<span>为空不修改</span>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>