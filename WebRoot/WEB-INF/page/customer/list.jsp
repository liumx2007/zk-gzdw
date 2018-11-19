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
	<title>导入客户数据</title>
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
		var buttons_addBatch = [{
			text:"确定",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_addBatch").form('validate')) {
					return;
				}
				var filePath = $("#file_addBatch").val();
				var d=filePath.length-".csv".length;
			    if(d<0||filePath.lastIndexOf(".csv")!=d) {
			    	alert("请选择csv文件进行导入！");
			    	return;
			    }
				$("#dialog_addBatch").dialog("close");
				$.blockUI({ message: "<img src='resources/images/icons/loading.gif'/>正在导入..."});
				$.ajaxFileUpload ({
					url: "customer/addBatch.do",
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
		function addBatch() {
			$("#form_addBatch").form("clear");
			$("#dialog_addBatch").dialog("open");
		}
		$(function(){
			$("#dialog_addBatch").dialog({
				closed:true,
				title:"批量导入",
				iconCls:'icon-page-excel',
				closable:true,
				cache:false,
				modal:true,
				buttons:buttons_addBatch
			});
		});
	</script>
</head>

<body>
	<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="margin: 50px 0 0 200px" 
        onclick="addBatch()">导入数据</a>
	<div id="dialog_addBatch" style="width:400px;height:150px;padding:15px;">
 		  <form id="form_addBatch">
 		  	<table>
 		  		<tr>
					<td align="right">excel文件：</td>
					<td>
						<div><input type="file" id="file_addBatch" name="file" accept=".csv"></div>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>