<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript">
		var buttons_newFolder = [{
			text:"提交",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_newFolder").form('validate')) {
					return;
				}
				$.ajax({
					type: "POST",
				    url: "folder/add.do",
					data: $("#form_newFolder").serialize(),
					dataType : "json",
					success: function(object){
						if(object.type == "success") {
							var node = $('#folder_tree').tree('getSelected');
							var parent = $('#folder_tree').tree('getParent', node.target);
							if(parent) {
								$('#folder_tree').tree("reload", parent.target);
							} else {
								$('#folder_tree').tree("reload");
							}
					    	$("#dialog_newFolder").dialog("close");
						}
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
	 			$("#form_newFolder").form("clear");
			}
	 	}];
		var buttons_newData = [{
			text:"确定",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_newData").form('validate')) {
					return;
				}
				var way = $("input[name='way']:checked").val();
				var fileName = "";
				if(way == "1") {
					fileName = $("#file_newData").val();
				} else {
					fileName = $("#localFile_newData").val();
				}
				if(fileName=="" && way=="1") {
					alert("请选择上传文件！");
					return;
				}
				if(fileName=="" && way=="2") {
					alert("请填写文件路径！");
					return;
				}
				if(fileName.indexOf(".") == -1 && !(fileName.indexOf("http://") == 0 || fileName.indexOf("https://") == 0)){
					alert("请输入合法的有后缀名的文件！");
					return;
				} else if(way == "1" && fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf(".")).length>20){
					alert("文件的名称字数不能超出20！");
					return;
				} else if(way == "2" && fileName.length>500){
					alert("路径过长！");
					return;
				}
				var rex = /^[a-zA-Z\d\s_]*$/;
				var fileType = fileName.substring(fileName.lastIndexOf("."));
// 				if(fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
// 					||fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx") {
// 					if(!rex.test(fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf(".")))) {
// 						alert("文档文件名不合法，文件名只能包含“字母、数字、下划线和空格”");
// 						return;
// 					}
// 				}
				if(way == "2" && $.trim($("#mappingName_newData").val()).length > 20) {
					alert("映射名称过长！");
					return;
				}
				if(way == "1") {
					var data = $("#form_newData").serializeObject();
					$("#dialog_newData").dialog("close");
					var msg = "正在上传，请稍后...";
					if(fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
	 					||fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx" || fileType.toLowerCase() == ".pdf") {
						msg = "上传完后，文档文件需要解析，不要关闭和刷新页面，请耐心等待响应...";
					}
					$.blockUI({ message: "<img src='resources/images/icons/loading.gif'/>"+msg});
					$.ajaxFileUpload ({
						url: "content/add.do",
						secureuri: false,
						fileElementId: ['file_newData'],
						data: data,
						dataType: 'text',
						type: 'post',
						success: function (object, status){
							object = eval('(' + object + ')');
							if(object.type == "success") {
								$("#content_table").datagrid("reload");
							}
							window.parent.showMessage(object);
							$.unblockUI();
		                }, error: function (data, status, e){
		                	window.parent.showMessage({type:"error",message:"上传失败！"});
		                	$.unblockUI();
		                }
				 	});
				} else {
					$.ajax({
					    url: "content/add.do",
						type: "POST",
						data: $("#form_newData").serialize(),
						dataType : "json",
						success: function(object){
							if(object.type == "success") {
								$("#dialog_newData").dialog("close");
								$("#content_table").datagrid("reload");
							}
							window.parent.showMessage(object);
						},error: function(msg) {
							window.parent.showMessage({type:"error",message:"添加失败！"});
						}
					});
				}
			}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_newData").form("clear");
			}
	 	}];
		var buttons_editData = [{
			text:"确定",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_editData").form('validate')) {
					return;
				}
				var rows = $('#content_table').datagrid("getSelections");
				if(rows.length == 0) return;
				var row = rows[0];
				var way = $("#form_editData input[name='way']:checked").val();
				var fileName = "";
				if(way == "1") {
					fileName = $("#file_editData").val();
				} else {
					fileName = $("#localFile_editData").val();
				}
				if(fileName != "") {
					if(fileName.indexOf(".") == -1 && !(fileName.indexOf("http://") == 0 || fileName.indexOf("https://") == 0)){
						alert("请输入合法的有后缀名的文件");
						return;
					} else if(way == "1" && fileName.substring(fileName.lastIndexOf("\\")+1,fileName.lastIndexOf(".")).length>20){
						alert("上传文件的名称字数不能超出20");
						return;
					} else if(way == "2" && fileName.length>500){
						alert("路径过长！");
						return;
					}
				} else {
					if(way == "1" && row.way == 2) {
						alert("请选择上传文件！");
						return;
					}
				}
				var rex = /^[a-zA-Z\d\s_]*$/;
				var fileType = fileName.substring(fileName.lastIndexOf("."));
// 				if(fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
// 						||fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx") {
// 					if(!rex.test(fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf(".")))) {
// 						alert("文档文件名不合法，文件名只能包含“字母、数字、下划线和空格”");
// 						return;
// 					}
// 				}
				if(way == "2" && $.trim($("#mappingName_editData").val()).length > 20) {
					alert("映射名称过长！");
					return;
				}
				if(way == "1") {
					var data = $("#form_editData").serializeObject();
					$("#dialog_editData").dialog("close");
					var msg = "正在上传，请稍后...";
					if(fileType.toLowerCase() == ".ppt" || fileType.toLowerCase() == ".pptx" || fileType.toLowerCase() == ".doc"
	 					||fileType.toLowerCase() == ".docx" || fileType.toLowerCase() == ".xls" || fileType.toLowerCase() == ".xlsx" || fileType.toLowerCase() == ".pdf") {
						msg = "上传完后，文档文件需要解析，不要关闭和刷新页面，请耐心等待响应...";
					}
					$.blockUI({ message: "<img src='resources/images/icons/loading.gif'/>"+msg});
					$.ajaxFileUpload ({
						url: "content/edit.do",
						secureuri: false,
						fileElementId: ['file_editData'],
						data: data,
						dataType: 'text',
						type: 'post',
						success: function (object, status){
							object = eval('(' + object + ')');
							if(object.type == "success") {
								$("#content_table").datagrid("reload");
							}
							window.parent.showMessage(object);
							$.unblockUI();
		                },error: function (data, status, e){
		                	window.parent.showMessage({type:"error",message:"上传失败！"});
		                	$.unblockUI();
		                }
				 	});
				} else {
					$.ajax({
					    url: "content/edit.do",
						type: "POST",
						data: $("#form_editData").serialize(),
						dataType : "json",
						success: function(object){
							if(object.type == "success") {
								$("#dialog_editData").dialog("close");
								$("#content_table").datagrid("reload");
							}
							window.parent.showMessage(object);
						},error: function(msg) {
							window.parent.showMessage({type:"error",message:"修改失败！"});
						}
					});
				}
			}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
				var rows = $('#content_table').datagrid("getSelections");
				if(rows.length == 0) return;
				var row = rows[0];
				var way = $("#form_editData input[name='way']:checked").val();
	 			$("#form_editData").form("clear");
	 			$("#way_editData"+way).prop("checked", true);
	 			$("#id_editData").val(row.id);
			}
	 	}];
		var toolBar = [{
			text:'上传资料',
			iconCls:'icon-add',
			handler:function(){
				$("#form_newData").form("clear");
				$("#way_newData1").prop("checked","checked");
				changeNewDataWay();
				$("#folderId_newData").val($("#currentNodeId").val());
				$("#dialog_newData").dialog("open");
			}},'-',{
			text:'修改资料',
			iconCls:'icon-edit',
			handler:function(){
				var rows = $('#content_table').datagrid("getSelections");
				if(rows.length == 0) return;
				var row = rows[0];
				$("#form_editData").form("clear");
				$("#id_editData").val(row.id);
				$("#title_editData").textbox("setValue", row.title);
				$("#way_editData"+row.way).prop("checked", true);
				if(row.way == "1") {
					if(row.type=="演示文档" || row.type == "便携式文档") {
						$("#filePath").val("<%=basePath%>"+row.folder.path+row.subFileName);
					} else {
						$("#filePath").val("<%=basePath%>"+row.folder.path+row.fileName);
					}
				} else {
					$("#filePath").val(row.fileName);
				}
				var tr = $("#newTr_editData");
				if(tr) {
					tr.remove();
				}
				if(row.way == 1) {
					$("#tr_editData").html("<td align='right'>上传文件：</td><td><div><input type='file' id='file_editData' name='file'></div><span>为空不修改</span></td>");
				} else {
					$("#tr_editData").html("<td align='right'><font color='red'>*</font>路径：</td><td><input type='text' id='localFile_editData' name='localFile' value='"+row.fileName+"'></td>");
					$("#form_editData table").first().append("<tr id='newTr_editData'><td align='right'>可执行文件映像名称：</td><td><input type='text' id='mappingName_editData' name='mappingName' value='"+row.mappingName+"'></td></tr>");
				}
				$("#dialog_editData").dialog("open");
			}},'-',{
			text:'删除资料',
			iconCls:'icon-remove',
			handler:function(){
				var rows = $('#content_table').datagrid("getSelections");
				if(rows.length == 0) return;
				var ids = ""; 
				for(var index=0;index<rows.length;index++) {
					if (ids == "") { 
						ids = "id=" + rows[index].id; 
					} else { 
						ids += "," + rows[index].id;
					} 
				}
				$.messager.confirm("确认框", "确定删除选定内容吗？", function(r){
					if (r){
						$.ajax({
							type: "POST",
						    url: "content/delete.do?"+ids,
						    dataType : "json",
							success: function(object){
								if(object.type = "success") {
							    	$('#content_table').datagrid("reload");
							    	$("#content_table").datagrid("clearSelections");
								}
						    	window.parent.showMessage(object);
							}, error: function(msg) {
								window.parent.showMessage({type:"error",message:"删除失败！"});
							}
						});
					}
				});
			}},'-',{
			iconCls:'icon-reload',
			handler:function(){
				$("#content_table").datagrid("reload");
			}}
		];
		function rightClick(e,node) {
			e.preventDefault();
			$(this).tree('select', node.target);
			var item1 = $("#mm").menu("findItem","移除");
			var item3 = $("#mm").menu("findItem","修改");
			if(node.isSystem==1) {
				$("#mm").menu("disableItem", item1.target);
				$("#mm").menu("disableItem", item3.target);
			} else {
				$("#mm").menu("enableItem", item1.target);
				$("#mm").menu("enableItem", item3.target);
			}
			var item2 = $("#mm").menu("findItem","刷新");
			$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
			currentNode = node;
		}
		function append(){
			var node = $('#folder_tree').tree('getSelected');
			$("#form_newFolder").form("clear");
			$("#parentId_newFolder").val(node.id);
			$("#dialog_newFolder").dialog("open");
		}
		function toEdit() {
			var node = $('#folder_tree').tree('getSelected');
			$('#folder_tree').tree('beginEdit', node.target);
		}
		function refreshItem() {
			var node = $('#folder_tree').tree('getSelected');
			$('#folder_tree').tree('reload', node.target);
		}
		function removeItem() {
			var node = $('#folder_tree').tree('getSelected');
			$.ajax({
				type: "POST",
			    url: "folder/delete.do",
			    data: "id="+node.id,
			    dataType: 'json',
				success: function(object){
					if(object.type == "success") {
						var node = $('#folder_tree').tree('getSelected');
						var parent = $('#folder_tree').tree('getParent', node.target);
						$('#folder_tree').tree("reload", parent.target);
						$("#content_table").datagrid({
							title:"当前资料夹：未指定"
						});
					}
					window.parent.showMessage(object);
				},error: function(msg) {
					window.parent.showMessage({type:"error",message:"移除失败！"});
				}
			});
		}
		function select(node) {
			$("#content_table").datagrid("clearSelections");
			$("#content_table").datagrid({
				title:"当前资料夹："+node.text,
				url:"content/getByFolder.do?id="+node.id
			});
			$("#currentNodeId").val(node.id)
		}
		function edit(node) {
			var data = {"id":node.id,"title":node.text};
			$.ajax({
				type: "POST",
			    url: "folder/edit.do",
				data: data,
				dataType: "json",
				success: function(object){
					if(object.type == "error") {
						var parent = $('#folder_tree').tree('getParent', node.target);
						$('#folder_tree').tree("reload", parent.target);
						window.parent.showMessage(object);
					}
				},error: function(msg) {
					var parent = $('#folder_tree').tree('getParent', node.target);
					$('#folder_tree').tree("reload", parent.target);
					window.parent.showMessage({type:"error",message:"修改失败！"});
				}
			});
		}
		function loaded() {
			var rootNode = $("#folder_tree").tree("getRoot");
			$("#folder_tree").tree("select", rootNode.target);
			$("#folder_tree").tree("expandAll");
		}
		function getTitle(v,r,i) {
			if(v!="" && r.isSystem==1) {
				return "<span style='color:red'>"+v+"</span>";
			} else {
				return v;
			}
		}
		function getSize(v,r,i) {
			if(v<1024) {
				return v + "字节";
			} else if(1024 <= v && v < 1024*1024) {
				return (v/1024).toFixed(2)+"KB"
			} else if(1024*1024 <= v && v < 1024*1024*1024) {
				return (v/(1024*1024)).toFixed(2)+"MB"
			} else if(1024*1024*1024 <= v) {
				return (v/(1024*1024*1024)).toFixed(2)+"GB"
			}
		}
		function getCheckStatus(v,r,i) {
			if(v==0){
			    var v = "未审核"
                return "<span style='color:red'>"+v+"</span>";
			    // return "未审核";
			}else {
                return "已审核";
			}
        }
		function changeNewDataWay() {
			var way = $("#form_newData input[name='way']:checked").val();
			//var tr = $("#form_newData table").first().find("tr").last();
			var tr = $("#tr_newData");
			var html = "";
			if(way == "1") {
				html = "<td align='right'><font color='red'>*</font>上传文件：</td><td><input type='file' id='file_newData' name='file'></td>"
				var newTr = $("#newTr_newData");
				if(newTr) {
					newTr.remove();
				}
			} else {
				html = "<td align='right'><font color='red'>*</font>路径：</td><td><input type='text' id='localFile_newData' name='localFile'></td>"
				$("#form_newData table").first().append("<tr id='newTr_newData'><td align='right'>可执行文件映像名称：</td><td><input type='text' id='mappingName_newData' name='mappingName'></td></tr>");
			}
			tr.html(html);
		}
		function changeEditDataWay() {
			var rows = $('#content_table').datagrid("getSelections");
			if(rows.length == 0) return;
			var row = rows[0];
			var fileName = "";
			var way = $("#form_editData input[name='way']:checked").val();
			var tr = $("#tr_editData");
			var html = "";
			if(way == "1") {
				if(row.way == 1) {
					html = "<td align='right'>上传文件：</td><td><div><input type='file' id='file_editData' name='file'></div><div><span>为空不修改</span></div></td>";
				} else {
					html = "<td align='right'><font color='red'>*</font>上传文件：</td><td><input type='file' id='file_editData' name='file'></td>";
				}
				var newTr = $("#newTr_editData");
				if(newTr) {
					newTr.remove();
				}
			} else {
				if(row.way == 2) {
					fileName = row.fileName;
				}
				html = "<td align='right'><font color='red'>*</font>路径：</td><td><input type='text' id='localFile_editData' name='localFile' value='"+fileName+"'></td>";
				$("#form_editData table").first().append("<tr id='newTr_editData'><td align='right'>可执行文件映像名称：</td><td><input type='text' id='mappingName_editData' name='mappingName' value='"+row.mappingName+"'></td></tr>");
			}
			tr.html(html);
		}
		function cz(value, row, index) {
			var cz = "";
           	cz="<a href='javascript:void(0)' onclick='sort(1,\""+row.id+"\")' style='margin-right:5px;'>"+
               "<img src='resources/images/icons/up.png' border='0' alt='上移'/></a>"+
           		"<a href='javascript:void(0)' onclick='sort(-1,\""+row.id+"\")' style='margin-right:5px;'>"+
           		"<img src='resources/images/icons/down.png' border='0' alt='下移'/></a>";
	   		return cz;
		}
		function sort(d, id) {
			$.ajax({
				type: "POST",
			    url: "content/sortContent.do?direction="+d+"&contentId="+id,
			    dataType: 'json',
				success: function(object){
					if(object.type == "success") {
						$("#content_table").datagrid("reload");
					} else {
						window.parent.parent.showMessage(object);
					}
				}, error: function(msg) {
					window.parent.parent.showMessage({type:"error",message:"排序失败！"});
				}
			});
		}
		$(function(){
			$("#content_table").datagrid({
				idField: "id",
				frozenColumns:[[{field:'ck',checkbox:true}]],
				split: false,
				fit: true,
				rownumbers: true,
				border: 0,
				toolbar: toolBar
			});
		});
	</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:0,split:false">
	<div data-options="region:'west',border:0,split:true" style="width:200px">
		<div class="easyui-panel" data-options="iconCls:'icon-tree',fit:true,border:0,split:false" title="资料夹列表" style="width:300px;">
			<ul id="folder_tree" class="easyui-tree" data-options="
				url:'folder/getByParent.do',
				lines:true,
				onContextMenu: rightClick,
				onAfterEdit: edit,
				onClick: select,
				onLoadSuccess: loaded"></ul>
	    </div>
    </div>
	<div data-options="region:'center',border:0,split:true" style="width:810px">
		<input type="hidden" id="currentNodeId"/>
			<table id="content_table" title="当前资料夹：未指定">
				<thead>
					<tr>
						<th align='center' data-options="field:'serialNumber',width:30,halign:'center'">编号</th>
						<th align='center' data-options="field:'title',width:180,halign:'center',formatter:getTitle">标题</th>
						<th align='center' data-options="field:'fileName',width:180,halign:'center'">文件名</th>
						<th align='center' data-options="field:'type',width:130,halign:'center'">类型</th>
						<th align='center' data-options="field:'size',width:130,halign:'center',formatter:getSize">文件大小</th>
						<th align='center' data-options="field:'checkStatus',width:130,halign:'center',formatter:getCheckStatus">审核状态</th>
						<th align='center' data-options="field:'checkName',width:130,halign:'center'">审核人</th>
						<th align='center' data-options="field:'cz',width:80,halign:'center',formatter:cz">操作</th>
					</tr>
				</thead>
			</table>
	</div>
</div>
<div id="dialog_newFolder" class="easyui-dialog" closed="true" title="新建资料夹" style="width:400px;height:220px;padding:35px"
   	data-options="iconCls:'icon-folder-add',closable:true,cache:false,modal:true,buttons:buttons_newFolder">
 		  <form id="form_newFolder">
 		  	<input type="hidden" name="parentId" id="parentId_newFolder"/>
 		  	<table>
 		  		<tr>
 		  			<td width="150px" align="right">标题：</td>
 		  			<td><input class="easyui-textbox" id="title_newFolder" name="title" data-options="required:true,missingMessage:'资料夹名不能为空',validType:'length[1,50]',invalidMessage:'不能超过50个字符！'"></input></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">文件夹名称：</td>
 		  			<td><input class="easyui-textbox"  id="folderName_newFolder" name="folderName" data-options="required:true,missingMessage:'文件夹名不能为空',validType:'folderName'"></input></td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_newData" class="easyui-dialog" closed="true" title="上传资料" style="width:400px;height:200px"
   		data-options="iconCls:'icon-data-add',closable:true,cache:false,modal:true,buttons:buttons_newData">
 		  <form id="form_newData">
 		  	<input type="hidden" name="folder.id" id="folderId_newData"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="150px">标题：</td>
 		  			<td><input class="easyui-textbox" id="title_newData" name="title" 
 		  			data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="150px">方式：</td>
 		  			<td>
 		  				<label><input name="way" type="radio" value="1" id="way_newData1" onchange="changeNewDataWay();"/>上传文件</label>
 		  				<label><input name="way" type="radio" value="2" id="way_newData2" onchange="changeNewDataWay();"/>手动填写路径</label>
 		  			</td>
 		  		</tr>
				<tr id="tr_newData">
					<td align="right"><font color="red">*</font>上传文件：</td>
					<td><input type="file" id="file_newData" name="file"></td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_editData" class="easyui-dialog" closed="true" title="修改资料" style="width:450px;height:220px"
   		data-options="iconCls:'icon-data-edit',closable:true,cache:false,modal:true,buttons:buttons_editData">
 		  <form id="form_editData">
 		  	<input type="hidden" name="id" id="id_editData"/>
 		  	<table>
 		  		<tr>
					<td align="right">真实路径：</td>
					<td>
						<input id="filePath" readonly style="width:250px;"></input>
					</td>
				</tr>
 		  		<tr>
 		  			<td align="right" width="100px">标题：</td>
 		  			<td><input class="easyui-textbox" id="title_editData" name="title" 
 		  			data-options="required:true,validType:'length[1,50]',missingMessage:'不能为空',invalidMessage:'不能超过50个字符！'"></input></td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="150px">方式：</td>
 		  			<td>
 		  				<label><input name="way" type="radio" value="1" id="way_editData1" onchange="changeEditDataWay();"/>上传文件</label>
 		  				<label><input name="way" type="radio" value="2" id="way_editData2" onchange="changeEditDataWay();"/>手动填写路径</label>
 		  			</td>
 		  		</tr>
				<tr id="tr_editData">
					<td align="right">上传文件：</td>
					<td>
						<div><input type="file" id="file_editData" name="file"></div>
						<span>为空不修改</span>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="append()" data-options="iconCls:'icon-add'">新建</div>
		<div class="menu-sep"></div>
		<div onclick="toEdit()" data-options="iconCls:'icon-edit'">修改</div>
		<div class="menu-sep"></div>
		<div onclick="refreshItem()" data-options="iconCls:'icon-reload'">刷新</div>
		<div class="menu-sep"></div>
		<div onclick="removeItem()" data-options="iconCls:'icon-remove'">移除</div>
	</div>
</body>
</html>