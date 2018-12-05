<%@ page language="java"  contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>内容管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="resources/js/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="resources/js/jquery-easyui/themes/icon.css" />
<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>



<style type="text/css">
table td {
	font-size: 14px;
}
</style>
<script type="text/javascript">
	var showJobMap = null;
	var showBindStatusMap = null;
	$(function() {
		$("#content_table").datagrid({
			url : "personnel/getAll.do",
			idField : "id",
			split : false,
			singleSelect: true,
			fit : true,
			border : 0,
			toolbar : toolBar
		});
		$.ajax({
			type : "GET",
			url : "position/getAllPosition.do",
			dataType : "json",
			success : function(object) {
				showJobMap = object;
			},
			error : function(msg) {
				window.parent.showMessage({
					type : "error",
					message : "获取数据失败！"
				});
			}
		});
		
		$.ajax({
			type : "GET",
			url : "personnel/showBindStatusLogic.do",
			dataType : "json",
			async: false,
			success : function(object) {
				//var dataObj=eval("("+object+")");
				var map= object[0];
				showBindStatusMap = map;
			},
			error : function(msg) {
				window.parent.showMessage({
					type : "error",
					message : "获取数据失败！"
				});
			}
		});
	});
	
	var buttons_newData = [ {
		text : "确定",
		iconCls : "icon-ok",
		handler : function() {
			if (!$("#form_newData").form('validate')) {
				return;
			}
			var way = $("input[name='sex']:checked").val();
			var fileName = "";
			if (fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf(".")).length > 20) {
				alert("文件的名称字数不能超出20！");
				return;
			}
			var rex = /^[a-zA-Z\d\s_]*$/;
			var fileType = fileName.substring(fileName.lastIndexOf("."));
			if (true) {
				var data = $("#form_newData").serializeObject();
				$("#dialog_newData").dialog("close");
				var msg = "正在上传，请稍后...";
				if (fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
					|| fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx" || fileType.toLowerCase() == ".pdf") {
					msg = "上传完后，文档文件需要解析，不要关闭和刷新页面，请耐心等待响应...";
				}
				$.blockUI({
					message : "<img src='resources/images/icons/loading.gif'/>" + msg
				});
				$.ajaxFileUpload({
					url : "personnel/add.do",
					secureuri : false,
					fileElementId : [ 'file_newData' ],
					data : data,
					dataType : 'text',
					type : 'post',
					success : function(object, status) {
						object = eval('(' + object + ')');
						if (object.type == "success") {
							$("#content_table").datagrid("reload");
						}
						window.parent.showMessage(object);
						$.unblockUI();
					},
					error : function(data, status, e) {
						window.parent.showMessage({
							type : "error",
							message : "上传失败！"
						});
						$.unblockUI();
					}
				});
			}
		}
	}, {
		text : "清除",
		iconCls : "icon-undo",
		handler : function() {
			$("#form_newData").form("clear");
		}
	} ];
	var buttons_editData = [ {
		text : "确定",
		iconCls : "icon-ok",
		handler : function() {
			if (!$("#form_editData").form('validate')) {
				return;
			}
			var way = $("input[name='sex']:checked").val();
			var fileName = "";
			if (fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf(".")).length > 20) {
				alert("文件的名称字数不能超出20！");
				return;
			}
			var rex = /^[a-zA-Z\d\s_]*$/;
			var fileType = fileName.substring(fileName.lastIndexOf("."));
			if (true) {
				var data = $("#form_editData").serializeObject();
				$("#dialog_editData").dialog("close");
				var msg = "正在上传，请稍后...";
				if (fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
					|| fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx" || fileType.toLowerCase() == ".pdf") {
					msg = "上传完后，文档文件需要解析，不要关闭和刷新页面，请耐心等待响应...";
				}
				$.blockUI({
					message : "<img src='resources/images/icons/loading.gif'/>" + msg
				});
				$.ajaxFileUpload({
					url : "personnel/editPerson.do",
					secureuri : false,
					fileElementId : [ 'file_editData' ],
					data : data,
					dataType : 'text',
					type : 'post',
					success : function(object, status) {
						object = eval('(' + object + ')');
						if (object.type == "success") {
							$("#content_table").datagrid("reload");
						}
						window.parent.showMessage(object);
						$.unblockUI();
					},
					error : function(data, status, e) {
						window.parent.showMessage({
							type : "error",
							message : "上传失败！"
						});
						$.unblockUI();
					}
				});
			}
		}
	}, {
		text : "清除",
		iconCls : "icon-undo",
		handler : function() {
			var rows = $('#content_table').datagrid("getSelections");
			if (rows.length == 0) return;
			var row = rows[0];
			var way = $("#form_editData input[name='way']:checked").val();
			$("#form_editData").form("clear");
			$("#way_editData" + way).prop("checked", true);
			$("#id_editData").val(row.id);
		}
	} ];
	var toolBar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$("#form_newData").form("clear");
			$("#sex_newData1").prop("checked", "checked");
			$("#dialog_newData").dialog("open");
		}
	}, '-', {
		text : '修改',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#content_table').datagrid("getSelections");
			if (rows.length == 0) return;
			var row = rows[0];
			$("#form_editData").form("clear");
			$("#id_editData").val(row.id);
			$("#name_editData").textbox("setValue", row.name);
			//$("#age_editData").textbox("setValue", row.age);
			$("#job_num_editData").textbox("setValue", row.job_num);
			//$("#work_editData").textbox("setValue", row.work);
			//$("#work_position_editData").textbox("setValue", row.work_position);
			$("#sex_editData" + row.sex).prop("checked", true);
			$("#photo_editData").textbox("setValue", row.photo);
			//$("#status_editData" + row.work_status).prop("checked", true);
			$("#watch_code_editShow").textbox("setValue", row.watch_code);
			$("#watch_code_editData").val(row.watch_code);
			$("#passWork_editData").textbox("setValue", "******");
			$("#bind_status").val(row.bind_status);
			
			if(showBindStatusMap==null){
				$.ajax({
					type : "GET",
					url : "personnel/showBindStatusLogic.do",
					dataType : "json",
					async: false,
					success : function(object) {
						//var dataObj=eval("("+object+")");
						var map= object[0];
						showBindStatusMap = map;
					},
					error : function(msg) {
						window.parent.showMessage({
							type : "error",
							message : "获取数据失败！"
						});
					}
				});
			}
			
			var data = [];
			for(var key in showBindStatusMap){
				data.push({"id":key,"text":showBindStatusMap[key]});
			}
			//$("#sel_bind_status").combobox("loadData",data).combobox("setValue", row.bind_status);
			$("#sel_bind_status").combobox("setValue", row.bind_status);
			
			
			
			var tr = $("#newTr_editData");
			if (tr) {
				tr.remove();
			}

			$("#dialog_editData").dialog("open");
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#content_table').datagrid("getSelections");
			if (rows.length == 0) return;
			var ids = "";
			for (var index = 0; index < rows.length; index++) {
				if (ids == "") {
					ids = "id=" + rows[index].id;
				} else {
					ids += "," + rows[index].id;
				}
			}
			$.messager.confirm("确认框", "确定删除选定内容吗？", function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "personnel/delete.do?" + ids,
						dataType : "json",
						success : function(object) {
							if (object.type = "success") {
								$('#content_table').datagrid("reload");
								$("#content_table").datagrid("clearSelections");
							}
							window.parent.showMessage(object);
						},
						error : function(msg) {
							window.parent.showMessage({
								type : "error",
								message : "删除失败！"
							});
						}
					});
				}
			});
		}
	}, '-', {
		iconCls : 'icon-reload',
		handler : function() {
			$("#content_table").datagrid("reload");
		}
	}
	];
	function cz(value, row, index) {
		var cz = "";
		if (value == 1) {
			cz = "<p>男</p>";
		} else {
			cz = "<p>女</p>";
		}
		return cz;
	}
	function showJobLogic(value, row, index){
		if(value==null || value==""){
			return "<p></p>";
		}
		return "<p>"+value.position_name+"</p>";
	}
	
	function showBindStatusLogic(value, row, index){
		var temp = "<p>";
		if(value==1){
			temp = "<p style='color:red;'>";
		}
		if(showBindStatusMap==null){
			$.ajax({
				type : "GET",
				url : "personnel/showBindStatusLogic.do",
				dataType : "json",
				async: false,
				success : function(object) {
					//var dataObj=eval("("+object+")");
					var map= object[0];
					showBindStatusMap = map;
				},
				error : function(msg) {
					window.parent.showMessage({
						type : "error",
						message : "获取数据失败！"
					});
				}
			});
		}
		return temp+showBindStatusMap[value]+"</p>";
	}
	
	function status(value, row, index) {
		var status = "";
		if (value == 2) {
			status = "<p>忙碌</p>";
		} else if (value == 1) {
			status = "<p>空闲</p>";
		} else if (value == 3) {
			status = "<p>暂离</p>";
		} else {
			status = "<p>休息</p>";
		}
		return status;
	}
	function imgFormatter(value,row,index){
	   	if('' != value && null != value)
	     	value = '<img style="width:60px; height:60px" src="'+ "<%=basePath%>" + value + '">';
	     	return  value;
	   	}
	function editWatchBand(value,row,index){
		var str = '<a href="javascript:void(0)" class="easyui-linkbutton" onclick="unBand(\''+row.id+'\')" alt="上移" >解绑</a>';  
        return str;
	}
	function unBand(id){
		$.messager.confirm("确认框", "确定解绑腕表吗？", function(r) {
			if (r) {
				$.ajax({
					type : "POST",
					url : "personnel/unBandById.do?id=" + id,
					dataType : "json",
					success : function(object) {
						if (object.type = "success") {
							$('#content_table').datagrid("reload");
							$("#content_table").datagrid("clearSelections");
						}
						window.parent.showMessage(object);
					},
					error : function(msg) {
						window.parent.showMessage({
							type : "error",
							message : "解绑失败！"
						});
					}
				});
			}
		});
	}
	
</script>
</head>

<body>
	

	<div class="easyui-layout" data-options="fit:true,border:0,split:false">
		<div data-options="region:'center',border:0,split:true"
			style="width:810px">
			<input type="hidden" id="currentNodeId" />
			<!-- <table id="content_table" title="当前资料夹：未指定"> -->
			<table id="content_table">
				<thead>
					<tr>
						<th align='center'
							data-options="field:'name',width:80,halign:'center'">姓名</th>
						<th align='center'
							data-options="field:'job_num',width:80,halign:'center'">工号</th>
						<th align='center'
							data-options="field:'sex',width:50,halign:'center',formatter:cz">性别</th>
							<th align='center'
							data-options="field:'photo',width:80,halign:'center',formatter: imgFormatter">头像</th>
						<th align='center' 
							data-options="field:'my_work',width:160,halign:'center',formatter:showJobLogic">当前岗位</th>
						
						<!-- <忙碌、空闲、暂离、休息> -->
						<th align='center'
							data-options="field:'work_status',width:80,halign:'center',formatter:status">工作状态</th>
						<th align='center'
							data-options="field:'bind_status',width:80,halign:'center',formatter:showBindStatusLogic">绑定状态</th>
						<!-- <th align='center'
							data-options="field:'work_time',width:90,halign:'center'">工作状态时长</th> -->
						<th align='center'
							data-options="field:'watch_code',width:250,halign:'center'">手表Code</th>
						<th align='center'
							data-options="field:'edit',width:100,halign:'center',formatter:editWatchBand">腕表解绑</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="dialog_newData" class="easyui-dialog" closed="true"
		title="上传资料" style="width:400px;height:390px"
		data-options="iconCls:'icon-user-add',closable:true,cache:false,modal:true,buttons:buttons_newData">
		<form id="form_newData">
			<input type="hidden" name="id" id="id_newData" />
			<table>
				<tr>
					<td align="right" width="150px">姓名：</td>
					<td><input class="easyui-textbox" id="name_newData"
						name="name"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">密码：</td>
					<td><input class="easyui-textbox" id="passWork_newData" name="passWork"  type = "password"
						data-options="validType:'length[6,50]',invalidMessage:'多于6不超过50个字符！'"></input></td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">年龄：</td>
					<td><input class="easyui-textbox" id="age_newData" name="age"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr> -->
				<tr>
					<td align="right" width="150px">工号：</td>
					<td><input class="easyui-textbox" id="job_num_newData" name="job_num"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				
				<!-- <tr>
					<td align="right" width="150px">工作状态：</td>
					<td><label><input name="work_status" type="radio"
							value="2" id="status_newData1" />忙碌</label> <label><input
							name="work_status" type="radio" value="1" id="status_newData2" />空闲</label>
						<label><input name="work_status" type="radio" value="3"
							id="status_newData3" />暂离</label> <label><input
							name="work_status" type="radio" value="4" id="status_newData4" />休息</label>
					</td>
				</tr> -->
				<tr>
					<td align="right" width="150px">性别：</td>
					<td><label><input name="sex" type="radio" value="1"
							id="sex_newData1" />男</label> <label><input name="sex"
							type="radio" value="2" id="sex_newData2" />女</label></td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">职位：</td>
					<td><input class="easyui-textbox" id="work_newData"
						name="work"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">所处岗位位置：</td>
					<td><input class="easyui-textbox" id="work_position_newData"
						name="work_position"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr> -->
				<tr id="tr_newData">
					<td align="right"><font color="red">*</font>上传头像：</td>
					<td><input type="file" id="file_newData" name="file"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dialog_editData" class="easyui-dialog" closed="true"
		title="修改资料" style="width:450px;height:390px"
		data-options="iconCls:'icon-user-edit',closable:true,cache:false,modal:true,buttons:buttons_editData">
		<form id="form_editData">
			<input type="hidden" name="id" id="id_editData" />
			<input type="hidden" name="bind_status" id="bind_status" />
			<table>
				<tr>
					<td align="right" width="150px">姓名：</td>
					<td><input class="easyui-textbox" id="name_editData"
						name="name"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">密码：</td>
					<td><input class="easyui-textbox" id="passWork_editData" name="passWork" type = "password"
						data-options="validType:'length[6,50]',invalidMessage:'多于6不超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">工号：</td>
					<td><input class="easyui-textbox" id="job_num_editData" name="job_num"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">绑定状态：</td>
					<td>
						<select id="sel_bind_status" class="easyui-combobox" name="bind_status" style="width:120px;">
							<option value="0">已绑定</option>
							<option value="1">未绑定</option>
						</select>
							
					</td>
				</tr> -->
				<!-- <tr>
					<td align="right" width="150px">工作状态：</td>
					<td><label><input name="work_status" type="radio"
							value="1" id="status_editData1" />忙碌</label> <label><input
							name="work_status" type="radio" value="2" id="status_editData2" />空闲</label>
						<label><input name="work_status" type="radio" value="3"
							id="status_editData3" />暂离</label> <label><input
							name="work_status" type="radio" value="4" id="status_editData4" />休息</label>
					</td>
				</tr> -->
				<tr>
					<td align="right" width="150px">性别：</td>
					<td><label><input name="sex" type="radio" value="1"
							id="sex_editData1" />男</label> <label><input name="sex"
							type="radio" value="2" id="sex_editData2" />女</label></td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">职位：</td>
					<td><input class="easyui-textbox" id="work_editData"
						name="work"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr> -->
				<tr>
					<td align="right" width="150px">手表Code：</td>
					<td><input class="easyui-textbox"  id="watch_code_editShow" name="watch_codeShow" disabled="disabled" />
						<input type="hidden" id="watch_code_editData" name="watch_code" />
					</td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">所处岗位位置：</td>
					<td><input class="easyui-textbox" id="work_position_editData"
						name="work_position"
						data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr> -->
				<tr id="tr_editData">
					<td align="right"><font color="red">*</font>上传头像：</td>
					<td><input type="file" id="file_editData" name="file"></td>
				</tr>
				<!-- <tr>
					<td align="right" width="150px">照片：</td>
					<td><input readonly class="easyui-textbox" id="photo_editData"
						name="photo"></input></td>
				</tr> -->
			</table>
		</form>
	</div>
</body>
</html>