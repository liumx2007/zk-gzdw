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
	var arrangeDateId = window.name;
	var personDatas;
	var positionDatas;
	$(function() {
		//获取所有人员
				$.ajax({
					type : "GET",
					url: "personnel/getAll.do",
					dataType : "json",
					success : function(object) {
						personDatas = object;
						$('#personData').combobox({
					      data : object,
					      valueField:'id',
					      textField:'name'
					     });
						onLoadData();
					},
					error : function(msg) {
						window.top.showMessage({
							type : "error",
							message : "获取数据失败！"
						});
					}
				});
		//获取位置信息
		$.ajax({
			type : "GET",
			url: "position/getAllPosition.do",
			dataType : "json",
			success : function(object) {
				positionDatas = object;
				$('#positionData').combobox({
			      data : positionDatas,
			      valueField:'id',
			      textField:'position_name'
			     });
			},
			error : function(msg) {
				window.top.showMessage({
					type : "error",
					message : "获取数据失败！"
				});
			}
		});
		
		
	});
	function onLoadData(){
		$("#content_table").datagrid({
			url : "arrange/getArrangeDetialByDate.do?arrangeDateId="+arrangeDateId,
			idField : "id",
			split : false,
			fit : true,
			border : 0,
			toolbar : toolBar
		});
	}
	
	var buttons_newData = [ {
		text : "确定",
		iconCls : "icon-ok",
		handler : function() {
			if (!$("#form_newData").form('validate')) {
				return;
			}
			$("#addDetialDateId").val(arrangeDateId);
			var persons = $("#personData").combobox('getValues').toString();
			var postData = $("#form_newData").serializeObject();
			postData.person_id = persons;
			console.log(postData);
			$.ajax({
				type: "GET",
			    url: "arrange/addArrangeDetial.do",
				data: postData,
				dataType : "json",
				success: function(object){
			    	$('#content_table').datagrid("reload");
			    	$("#content_table").datagrid("clearSelections");
			    	$("#dialog_newData").dialog("close");
			    	//window.top.showMessage(object);
				},
				error: function(msg) {
					window.top.showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
				}
			});
		}
	}, {
		text : "清除",
		iconCls : "icon-undo",
		handler : function() {
			$('#positionData').combobox('setValue','');
			$('#personData').combobox('setValue','');
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
			var epersons = $("#personEditData").combobox('getValues').toString();
			var editPostData = $("#form_editData").serializeObject();
			editPostData.person_id = epersons;
			console.log(editPostData);
			$.ajax({
				type: "POST",
			    url: "arrange/editDetial.do",
				data: editPostData,
				dataType : "text",
				success: function(object){
			    	$('#content_table').datagrid("reload");
			    	$("#content_table").datagrid("clearSelections");
			    	$("#dialog_editData").dialog("close");
			    	//window.top.showMessage(object);
				},
				error: function(msg) {
					window.top.showMessage({type:'error',message:"<span style='color:red;'>添加失败！</span>"});
				}
			});
		}
	}, {
		text : "清除",
		iconCls : "icon-undo",
		handler : function() {
			var rows = $('#content_table').datagrid("getSelections");
			if (rows.length == 0) return;
			var row = rows[0];
			$('#positionEditData').combobox('setValue','');
			$('#personEditData').combobox('setValue','');
			$("#form_editData").form("clear");
		}
	} ];
	var toolBar = [ {
		text : '添加排班',
		iconCls : 'icon-add',
		handler : function() {
			$('#positionData').combobox({
				url : "arrange/getNotArrangePositions.do?arrangeDateId="+arrangeDateId,
			    valueField:'id',
			    textField:'position_name'
			});
			$('#personData').combobox({
				url : "arrange/getNotArrangePersons.do?arrangeDateId="+arrangeDateId,
			    valueField:'id',
			    textField:'name'
			     });
			$("input[name='part'][value='0']").attr('checked',true);
			$("#dialog_newData").dialog("open");
		}
	}, '-', {
		text : '复制排班',
		iconCls : 'icon-add',
		handler : function() {
			$("#form_copyDate").form("clear");
			$("#dialog_copyDate").dialog("open");
		}
	}, '-', {
		text : '修改排班',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#content_table').datagrid("getSelections");
			if (rows.length == 0) return;
			var row = rows[0];
			console.log(row);
			
			$('#positionEditData').combobox({
				url : "arrange/getNotArrangePositions.do?arrangeDateId="+arrangeDateId+"&selfPosition="+row.position,
			    valueField:'id',
			    textField:'position_name'
			});
			$('#personEditData').combobox({
				url : "arrange/getNotArrangePersons.do?arrangeDateId="+arrangeDateId+"&selfPersonId="+row.person_id,
			    valueField:'id',
			    textField:'name'
			     });
			
			$("#positionEditData").combobox("setValue",row.position);
			$("#personEditData").combobox("setValues",row.person_id);
			$("#id_editData").val(row.id);
			$("#editDetialDateId").val(row.arrange_date_id);
			$("#dialog_editData").dialog("open");
			$("input[name='part'][value="+row.part+"]").attr('checked',true);
			$("#remark_EditData").val(row.remark);
		}
	}, '-', {
		text : '删除排班',
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
						type : "GET",
						url : "arrange/deleteDetial.do?" + ids,
						dataType : "json",
						success : function(object) {
							if (object.type = "success") {
								$('#content_table').datagrid("reload");
								$("#content_table").datagrid("clearSelections");
							}
							//window.showMessage(object);
						},
						error : function(msg) {
							//window.top.showMessage({
							//	type : "error",
							//	message : "删除失败！"
							//});
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
	function positions(value, row, index) {
		for(var i = 0;i<positionDatas.length;i++){
			if(value == positionDatas[i].id){
				return "<p>" + positionDatas[i].position_name + "</p>";
			}
		}
		
	};
	function names(value, row, index) {
		var show = "";
		for(var i = 0;i<personDatas.length;i++){
			if(value.indexOf(personDatas[i].id) >= 0){
				show = show + "<p>" + personDatas[i].name + "</p>";
			}
		}
		return show;
	};
	var buttons_copyDate = [{
		text : "确定",
		iconCls : "icon-ok",
		handler : function() {
			if (!$("#form_selectDate").form('validate')) {
				return;
			}
			var getDate = $("#selectDate").datebox('getValue');
			$("#dialog_selectDate").dialog("close");
			$.messager.confirm("确认框", "是否从"+getDate+"复制排班信息？", function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "arrange/copy.do?arrangeDateId="+arrangeDateId+"&source="+getDate,
						dataType : "json",
						success : function(object) {
							if (object.type = "success") {
								$('#content_table').datagrid("reload");
								$("#content_table").datagrid("clearSelections");
								$("#dialog_copyDate").dialog("close");
							}
							window.top.showMessage(object);
						},
						error : function(msg) {
							//window.top.showMessage({
							//	type : "error",
							//	message : "删除失败！"
							//});
						}
					});
				}
			});
		}
	}];
</script>
</head>

<body>
	
	<div class="easyui-layout" data-options="fit:true,border:0,split:false">
		<div data-options="region:'center',border:0,split:true"
			style="width:810px">
			<input type="hidden" id="currentNodeId" />
			<table id="content_table">
				<thead>
					<tr>
						<th align='center'
							data-options="field:'position',width:250,halign:'center',formatter:positions">岗位位置</th>
						<th align='center'
							data-options="field:'person_id',width:250,halign:'center',formatter:names">岗位人员</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="dialog_newData" class="easyui-dialog" closed="true"
		title="上传资料" style="width:400px;height:390px"
		data-options="iconCls:'icon-data-add',closable:true,cache:false,modal:true,buttons:buttons_newData">
		<form id="form_newData">
			<input type="hidden" name="id" id="id_newData" />
			<input type="hidden" name="arrange_date_id" id="addDetialDateId" />
			
			<table>
				<tr>
					<td align="right" width="150px">岗位位置：</td>
					<td><input class="easyui-combobox" id="positionData"
						name="position"
						data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">岗位人员：</td>
					<td><input class="easyui-combobox" id="personData" 
						name="person_id"
						data-options="multiple:true,panelHeight:'auto',editable:false,required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">上班时间：</td>
					<td><label><input name="part" type="radio" value="0" id="status_newData1" />  全天  </label>
						<label><input name="part" type="radio" value="1" id="status_newData2" />  上午  </label>
						<label><input name="part" type="radio" value="2" id="status_newData3" />  下午  </label>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px">备注：</td>
					<td><input class="easyui-textbox" id="remark_newData" name="remark"></input></td>
				</tr>
				
			</table>
		</form>
	</div>
	<div id="dialog_editData" class="easyui-dialog" closed="true"
		title="修改排班" style="width:450px;height:390px"
		data-options="iconCls:'icon-data-edit',closable:true,cache:false,modal:true,buttons:buttons_editData">
		<form id="form_editData">
			<input type="hidden" name="id" id="id_editData" />
			<input type="hidden" name="arrange_date_id" id="editDetialDateId" />
			<table>
				<tr>
					<td align="right" width="150px">岗位位置：</td>
					<td><input class="easyui-combobox" id="positionEditData"
						name="position"
						data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">岗位人员：</td>
					<td><input class="easyui-combobox" id="personEditData" 
						name="person_id"
						data-options="multiple:true,panelHeight:'auto',editable:false,required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
				</tr>
				<tr>
					<td align="right" width="150px">上班时间：</td>
					<td><label><input name="part" type="radio" value="0" id="part_EditData1" />  全天  </label>
						<label><input name="part" type="radio" value="1" id="part_EditData2" />  上午  </label>
						<label><input name="part" type="radio" value="2" id="part_EditData3" />  下午  </label>
					</td>
				</tr>
				<tr>
					<td align="right" width="150px">备注：</td>
					<td><input class="easyui-textbox" id="remark_EditData" name="remark"></input></td>
				</tr>
				
			</table>
		</form>
	</div>
	<div id="dialog_copyDate" class="easyui-dialog" closed="true"
		title="复制排班" style="width:500px;height:150px"
		data-options="iconCls:'icon-data-add',closable:true,cache:false,modal:true,buttons:buttons_copyDate">
		<form id="form_copyDate">
			<table>
				<tr>
					<td align="right" width="230px">选择日期：</td>
					<td ><input width="150px" id="selectDate" type="text" class="easyui-datebox" required="required"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>