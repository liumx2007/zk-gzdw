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
	<title>预约管理</title>
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
		$("#order_table").datagrid({
			url: "order/list.do",
			idField: "id",
			split: false,
			fit: true,
			border: 0,
			frozenColumns: [[{field:'ck',checkbox:true}]],
			pagination:true,
			rownumbers: true,
			toolbar: "#bar",
		});
		var p = $('#order_table').datagrid('getPager');
		if (p){
			$(p).pagination({
		        pageList: [10,15,20],//可以设置每页记录条数的列表 
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
		};
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
			    url: "order/add.do",
			    data: $("#form_new").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#order_table').datagrid("reload");
				    	$("#order_table").datagrid("clearSelections");
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
			    url: "order/edit.do",
			    data: $("#form_edit").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#order_table').datagrid("reload");
				    	$("#order_table").datagrid("clearSelections");
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
	function add() {
		$("#form_new").form("clear");
		$("#dialog_new").dialog("open");
		$("#am_new").prop("checked","checked");
	}
	function edit() {
		var rows = $('#order_table').datagrid("getSelections");
		if(rows.length == 0) return;
		var row = rows[0];
		$.ajax({
			type: "POST",
		    url: "order/get.do?id="+row.id,
		    dataType : "json",
			success: function(object){
		    	$("#form_edit").form("clear");
		    	$("#id").val(object.id);
		    	$("#name_edit").textbox("setValue",object.name);
		    	$("#status_edit").combobox("setValue",object.status);
		    	$("#telephone_edit").textbox("setValue",object.telephone);
		    	$("#number_edit").numberbox("setValue", object.number);
		    	$("#date_edit").textbox("setValue", (1900+object.date.year)+"-"+(object.date.month+1)+"-"+object.date.date);
		    	if("上午" == object.apm) {
		    		$("#am_edit").prop("checked","checked");
		    	} else {
		    		$("#pm_edit").prop("checked","checked");
		    	}
		    	$("#remark_edit").textbox("setValue",object.remark);
		    	$("#dialog_edit").dialog("open");
			}
		});
	}
	function del() {
		var rows = $('#order_table').datagrid("getSelections");
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
				    url: "order/delete.do?"+ids,
				    dataType : "json",
					success: function(object){
						if(object.type = "success") {
					    	$('#order_table').datagrid("reload");
					    	$("#order_table").datagrid("clearSelections");
						}
				    	window.parent.showMessage(object);
					}, error: function(msg) {
						window.parent.showMessage({type:"error",message:"删除失败！"});
					}
				});
			}
		});
	}
	function setCurrent() {
		var rows = $('#order_table').datagrid("getSelections");
		if(rows.length == 0) return;
		if(rows.length > 1) {
			alert("只能勾选一条参观信息！");
			return;
		}
		var row = rows[0];
		$.ajax({
			type: "POST",
		    url: "order/visit.do?id="+row.id,
		    dataType : "json",
			success: function(object){
				if(object.type = "success") {
			    	$('#order_table').datagrid("reload");
			    	$("#order_table").datagrid("clearSelections");
				}
		    	window.parent.showMessage(object);
			}, error: function(msg) {
				window.parent.showMessage({type:"error",message:"操作失败！"});
			}
		});
	}
	function getDate(value,row,index) {
		if(value) {
			var m = value.month<10?"0"+(value.month+1):value.month+1;
			var d = value.date<10?"0"+value.date:value.date;
			return (1900+value.year)+"-"+m+"-"+d+" "+row.apm;
		}
	}
	function getAddTime(value,row,index) {
		if(value) {
			var m = value.month<10?"0"+(value.month+1):value.month+1;
			var d = value.date<10?"0"+value.date:value.date;
			var h = value.hours<10?"0"+value.hours:value.hours;
			var mi = value.minutes<10?"0"+value.minutes:value.minutes;
			var s = value.seconds<10?"0"+value.seconds:value.seconds;
			return (1900+value.year)+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
		}
	}
	function getStatus(value,row,index) {
		if(value==<%=Order.STATUS_CURRENT%>) {
			return "<span style='color: green;'>正在参观</span>";
		} else if(value==<%=Order.STATUS_NOT%>) {
			return "<span style='color: #FF9900;'>预约中</span>";
		} else if(value==<%=Order.STATUS_OVERDUE%>) {
			return "<span style='color: #999999;'>未参观</span>";
		} else if(value==<%=Order.STATUS_VISITED%>) {
			return "<span>已经参观</span>";
		}
	}
	function reload() {
		$("#order_table").datagrid("reload");
	}
	function doSearch() {
		var data = {};
		var array = $("#form_search").serializeArray();
		$(array).each(function(){
			if((this.name=="queryParameter.status_eq" && this.value != "全部")
					|| this.name!="queryParameter.status_eq")
			data[this.name]=this.value;
		});
		$("#order_table").datagrid("reload", data);
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
		                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="setCurrent();" iconCls="icon-ok" plain="true">设为当前参观</a>
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
			姓名：<input class="easyui-textbox" name="queryParameter.name_li" data-options="prompt:'请输入姓名'" style="width: 100px;"/>
			电话号码：<input class="easyui-numberbox" name="queryParameter.telephone_li" data-options="prompt:'请输入电话号码'" style="width: 110px;"/>
			参观状态：
			<select class="easyui-combobox" name="queryParameter.status_eq" style="width:85px;">
				<option value="全部" selected>全部</option>
				<option value="<%=Order.STATUS_CURRENT%>">正在参观</option>
				<option value="<%=Order.STATUS_NOT%>">预约中</option>
				<option value="<%=Order.STATUS_VISITED%>">已经参观</option>
				<option value="<%=Order.STATUS_OVERDUE%>">未参观</option>
			</select>
			参股时间段: <input class="easyui-datebox" name="sDate" data-options="prompt:'开始日期',editable:false" style="width:100px">
			至: <input class="easyui-datebox" name="eDate" data-options="prompt:'结束日期',editable:false" style="width:100px">
			<a href="javascript:void(0);" onclick="doSearch();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</form>
		</div>
	</div>
	<table id="order_table">
		<thead>
			<tr>
				<th align='center' data-options="field:'name',width:80,halign:'center'">姓名</th>
				<th align='center' data-options="field:'status',width:60,halign:'center',formatter:getStatus">状态</th>
				<th align='center' data-options="field:'telephone',width:100,halign:'center'">电话</th>
				<th align='center' data-options="field:'number',width:50,halign:'center'">人数</th>
				<th align='center' data-options="field:'date',width:100,halign:'center',formatter:getDate">参观时间</th>
				<th align='center' data-options="field:'addTime',width:125,halign:'center',formatter:getAddTime">提交时间</th>
				<th align='center' data-options="field:'operator',width:50,halign:'center'">提交人</th>
				<th data-options="field:'remark',width:240">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_new" class="easyui-dialog" closed="true" title="添加预约" style="width:300px;height:270px"
   		data-options="iconCls:'icon-telephone-add',closable:true,cache:false,modal:true,buttons:buttons_new">
 		  <form id="form_new">
 		  	<input type="hidden" name="operator" value="<%=Order.OPERATOR_MANAGER%>"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">姓名：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_new" name="name" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">电话号码：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="telephone_new" name="telephone" 
 		  					data-options="required:true,validType:'phone',missingMessage:'不能为空'"></input>
 		  			</td>
 		  		</tr>
 		  		<tr>
				<td align="right">人数：</td>
					<td>
						<input class="easyui-numberbox" id="number_new" name="number" data-options="required:true,min:1,max:99,missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">日期：</td>
					<td>
						<input class="easyui-datebox" id="date_new" name="date" data-options="required:true,editable:false,missingMessage:'不能为空'">
					</td>
				</tr>
				<tr>
					<td align="right">上午/下午：</td>
					<td>
						<label><input name="apm" type="radio" value="上午" id="am_new"/>上午</label> 
						<label><input name="apm" type="radio" value="下午" id="pm_new"/>下午</label> 
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td>
						<input class="easyui-textbox" id="remark_new" name="remark" style="width:175px;height:50px" 
							data-options="multiline:true,validType:'length[0,100]',invalidMessage:'不能超过100个字符！'"/>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" class="easyui-dialog" closed="true" title="修改预约" style="width:300px;height:300px"
   		data-options="iconCls:'icon-telephone-edit',closable:true,cache:false,modal:true,buttons:buttons_edit">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">姓名：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_edit" name="name" data-options="required:true,validType:'length[1,20]',missingMessage:'不能为空',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">状态：</td>
 		  			<td>
 		  				<select class="easyui-combobox" id="status_edit" name="status" style="width:85px;">
							<option value="<%=Order.STATUS_CURRENT%>">正在参观</option>
							<option value="<%=Order.STATUS_NOT%>">预约中</option>
							<option value="<%=Order.STATUS_VISITED%>">已经参观</option>
							<option value="<%=Order.STATUS_OVERDUE%>">未参观</option>
						</select>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right">电话号码：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="telephone_edit" name="telephone" 
 		  					data-options="required:true,validType:'phone',missingMessage:'不能为空'"></input>
 		  			</td>
 		  		</tr>
 		  		<tr>
					<td align="right">人数：</td>
					<td>
						<input class="easyui-numberbox" id="number_edit" name="number" data-options="required:true,min:1,max:99,missingMessage:'不能为空'"/>
					</td>
				</tr>
				<tr>
					<td align="right">日期：</td>
					<td>
						<input class="easyui-datebox" id="date_edit" name="date" data-options="required:true,editable:false,missingMessage:'不能为空'">
					</td>
				</tr>
				<tr>
					<td align="right">上午/下午：</td>
					<td>
						<label><input name="apm" type="radio" value="上午" id="am_edit"/>上午</label> 
						<label><input name="apm" type="radio" value="下午" id="pm_edit"/>下午</label> 
					</td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td>
						<input class="easyui-textbox" id="remark_edit" name="remark" style="width:175px;height:50px" 
							data-options="multiline:true,validType:'length[0,100]',invalidMessage:'不能超过100个字符！'"/>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>