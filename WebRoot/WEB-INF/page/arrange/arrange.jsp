<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>排班管理</title>
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
	<script type="text/javascript">
	var getDate = "";
		function addTab(){
			$.ajax({
				type: "POST",
			    url: "arrange/addADate.do",
			    dataType : "text",
				success: function(object){
					refreshTabs();
			    	window.parent.showMessage(object);
				}, error: function(msg) {
					window.parent.showMessage({type:"error",message:"添加失败！"});
				}
			});
		}
		function editTab(){
			var tab = $('#tt').tabs('getSelected');
			var id = tab.panel('options').id;
			$.ajax({
				type: "POST",
			    url: "group/get.do?id="+id,
			    dataType : "json",
				success: function(object){
			    	$("#form_editGroup").form("clear");
			    	$("#id").val(object.id);
	 		    	$("#codeName_editGroup").textbox("setValue",object.codeName);
			    	$("#name_editGroup").textbox("setValue",object.name);
			    	$("#serverNodeMac_editGroup").textbox("setValue",object.serverNodeMac);
			    	$("#dialog_editGroup").dialog("open");
				}
			});
		}
		function removeTab(){
			var tab = $('#tt').tabs('getSelected');
			var id = tab.panel('options').id;
			var title = tab.panel('options').title;
			if (tab){
				$.messager.confirm("确认框", "确定删除设备组："+title+"？", function(r){
					if (r){
						$.ajax({
							type: "POST",
						    url: "group/delete.do?id="+id,
						    dataType : "json",
							success: function(object){
								refreshTabs();
						    	window.parent.showMessage(object);
							}, error: function(msg) {
								window.parent.showMessage({type:"error",message:"删除失败！"});
							}
						});
					}
				});
			}
		}
		function refreshTabs() {
			if(getDate == ""){
				var dTemp = new Date();
				getDate = dTemp.getFullYear() + "-" + (dTemp.getMonth()+1) + "-" + dTemp.getDate();
			}
			$.ajax({
				type: "GET",
			    url: "arrange/getAllArrangeDate.do?getDate=" + getDate,
				dataType : "json",
				success: function(object){
					closeAll();
					for(var i=0;i<object.length;i++) {
						var arrDate = object[i].arrange_date;
						var mon = arrDate.month + 1;
						var day = arrDate.date;
						$("#tt").tabs("add",{
							id:object[i].id,
							title: mon + "月" + day + "日",
							selected:false
						});
						if(object.length>0) {
							$("#tt").tabs("select", 0);
						}
					}
					$("#dialog_newGroup").dialog("close");
				},error: function(msg) {
					window.parent.showMessage({type:"error",message:"排版日期获取失败！"});
				}
			});
		}
		function select(title) {
			var tab = $("#tt").tabs("getTab", title);
			if(tab.panel('options').content != null) return;
			$("#tt").tabs("update",{
				tab:tab,
				options:{
					content:'<iframe name="'+tab.panel('options').id+'" src="r/page/arrange/arrangeDetial.do" width="100%" height="100%" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>'
				}
			});
		}
		function closeAll(){
 			var tabs = $("#tt").tabs("tabs");
 			$(tabs).each(function(){
 				$("#tt").tabs("close", 0);
 			});
		}
		var buttons_newGroup = [{
			text:"提交",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_newGroup").form('validate')) {
					return;
				}
				$.ajax({
					type: "POST",
				    url: "group/add.do",
					data: $("#form_newGroup").serialize(),
					dataType : "json",
					success: function(object){
						refreshTabs();
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
	 			$("#form_newGroup").form("clear");
			}
	 	}];
		var buttons_editGroup = [{
			text:"提交",
			iconCls:"icon-ok",
			handler:function(){
				if(!$("#form_editGroup").form('validate')) {
					return;
				}
				$.ajax({
					type: "POST",
				    url: "group/edit.do",
					data: $("#form_editGroup").serialize(),
					dataType : "json",
					success: function(object){
						$("#dialog_editGroup").dialog("close");
						refreshTabs();
						window.parent.showMessage(object);
					},error: function(msg) {
						window.parent.showMessage({type:"error",message:"修改资料夹失败！"});
					}
				});
			}
	 	},{
			text:"清除",
			iconCls:"icon-undo",
			handler:function(){
	 			$("#form_editGroup").form("clear");
			}
	 	}];
		$(function(){
			refreshTabs();
		});
		function selectDate(){
			$("#dialog_selectDate").dialog("open");
		}
		var buttons_selectDate = [ {
		text : "确定",
		iconCls : "icon-ok",
		handler : function() {
			if (!$("#form_selectDate").form('validate')) {
				return;
			}
			getDate = $("#selectDate").datebox('getValue');
			console.log(getDate);
			
			$("#dialog_selectDate").dialog("close");
			refreshTabs();
		}
	}];
	</script>
</head>

<body>
	<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',fit:true,border:false,onSelect:select">
	</div>
	<div id="tab-tools">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="selectDate()">查询</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addTab()">添加日期</a> -->
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editTab()">修改组</a> -->
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removeTab()">删除组</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="refreshTabs()">刷新</a>
	</div>
	<div id="p-tools">
		<a href="javascript:void(0)" class="icon-mini-edit" onclick="alert('edit')"></a>
	</div>
	<div id="dialog_selectDate" class="easyui-dialog" closed="true"
		title="选择排班时间" style="width:500px;height:200px"
		data-options="iconCls:'icon-data-edit',closable:true,cache:false,modal:true,buttons:buttons_selectDate">
		<form id="form_selectDate">
			<table>
				<tr>
					<td align="right" width="230px">选择时间：</td>
					<td ><input width="150px" id="selectDate" type="text" class="easyui-datebox" required="required"></td>
				</tr>
				<tr>
					<td width="100%" align="right"  colspan="2" style="color: red;">选择时间为开始排班时间，最多显示七天的数据</td>
				</tr>
				
			</table>
		</form>
	</div>
 	
</body>
</html>