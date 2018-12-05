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
	<title>意见建议管理</title>
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
	<script type="text/javascript" src="resources/js/jquery-easyui/plugins/datagrid-detailview.js"></script>
	<style type="text/css">
		table td {font-size: 14px;}
	</style>
	<script type="text/javascript">
	$(function(){
		$("#yj_table").datagrid({
			url: "yj/list.do",
			idField: "id",
			split: false,
			fit: true,
			border: 0,
			frozenColumns: [[{field:'ck',checkbox:true}]],
			view: detailview,
			detailFormatter:function(index,row){
				return '<table>'+
					'<tr><td style="width:70px;">留言内容：</td><td style="width:600px;">'+row.content+'</td></tr>'+
					'<tr><td>反馈内容：</td><td>'+row.feedback+'</td></tr>'+
				'</table>';
			},
			pagination:true,
			rownumbers: true,
			toolbar: "#bar"
		});
		var p = $('#yj_table').datagrid('getPager');
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
			    url: "yj/add.do",
			    data: $("#form_new").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#yj_table').datagrid("reload");
				    	$("#yj_table").datagrid("clearSelections");
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
			var hasFk = $("#form_edit input[name='hasFk']:checked").val();
			var fk = $.trim($("#feedback_edit").val());
			if(hasFk == "1" && fk=="") {
				alert("请填写反馈内容！");
				return;
			}
			if(fk!="" && hasFk == "0") {
				$("#form_edit input[name='hasFk'][value=1]").prop("checked",true); 
				alert("您提交了反馈意见，已自动将状态更改为“已反馈”！");
			}
			$.ajax({
				type: "POST",
			    url: "yj/edit.do",
			    data: $("#form_edit").serialize(),
			    dataType : "json",
				success: function(object){
					if(object.type=="success") {
				    	$('#yj_table').datagrid("reload");
				    	$("#yj_table").datagrid("clearSelections");
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
		var rows = $('#yj_table').datagrid("getSelections");
		if(rows.length == 0) return;
		var row = rows[0];
		$.ajax({
			type: "POST",
		    url: "yj/get.do?id="+row.id,
		    dataType : "json",
			success: function(object){
		    	$("#form_edit").form("clear");
		    	$("#id").val(object.id);
		    	$("#name_edit").textbox("setValue", object.name);
		    	$("#bh_edit").textbox("setValue", object.bh);
		    	$("#phone_edit").textbox("setValue", object.phone);
				$("#type_edit").combobox("setValue", object.type);
		    	$("#content_edit").textbox("setValue", object.content);
		    	$("#feedback_edit").textbox("setValue", object.feedback);
		    	$("#form_edit input[name='hasFk'][value="+object.hasFk+"]").prop("checked",true); 
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
				    url: "yj/delete.do?"+ids,
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
	function getType(value,row,index) {
		if(value==1) {
			return "<span style='color: green;'>表扬</span>";
		} else if(value==2) {
			return "<span style='color: #ffcc00;'>意见</span>";
		} else if(value==3) {
			return "<span style='color: #ffcc00;'>建议</span>";
		} else if(value==4) {
			return "<span style='color: red;'>投诉</span>";
		} else if(value==5) {
			return "<span style='color: red;'>举报</span>";
		}
	}
	function getHasFk(value,row,index) {
		if(value==1) {
			return "<span style='color: green;'>已反馈</a>";
		} else if(value==0) {
			return "<span style='color: #999999;'>未反馈</span>";
		}
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
		<div>
			<form id="form_search" style="margin: 0px;">
			提交人：
			<input class="easyui-textbox" name="queryParameter.name_li" style="width:100px">
			用户编号：
			<input class="easyui-textbox" name="queryParameter.bh_li" style="width:100px">
			联系电话：
			<input class="easyui-textbox" name="queryParameter.phone_li" style="width:100px">
			类型：
			<select class="easyui-combobox" name="queryParameter.type_eq" style="width:85px;">
				<option value="">全部</option>
				<option value="1">表扬</option>
				<option value="2">意见</option>
				<option value="3">建议</option>
				<option value="4">投诉</option>
				<option value="5">举报</option>
			</select>
			反馈状态：
			<select class="easyui-combobox" name="queryParameter.hasFk_eq" style="width:85px;">
				<option value="">全部</option>
				<option value="1">已反馈</option>
				<option value="2">未反馈</option>
			</select>
			显示状态：
			<select class="easyui-combobox" name="queryParameter.isShow_eq" style="width:85px;">
				<option value="1">显示</option>
				<option value="0">未显示</option>
				<option value="">全部</option>
			</select>
			提交时间段: <input class="easyui-datebox" name="sDate" data-options="prompt:'开始日期',editable:false" style="width:100px">
			至: <input class="easyui-datebox" name="eDate" data-options="prompt:'结束日期',editable:false" style="width:100px">
			<a href="javascript:void(0);" onclick="doSearch();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			</form>
		</div>
	</div>
	<table id="yj_table">
		<thead>
			<tr>
				<th align='center' data-options="field:'type',width:50,halign:'center',formatter:getType">类型</th>
				<th align='center' data-options="field:'hasFk',width:60,halign:'center',formatter:getHasFk">是否反馈</th>
				<th align='center' data-options="field:'name',width:80,halign:'center'">提交人</th>
				<th align='center' data-options="field:'bh',width:80,halign:'center'">用户编号</th>
				<th align='center' data-options="field:'phone',width:100,halign:'center'">联系电话</th>
				<th align='center' data-options="field:'addTime',width:150,halign:'center',formatter:getAddTime">提交时间</th>
				<th align='left' data-options="field:'content',width:400,halign:'center'">留言内容</th>
				<th align='center' data-options="field:'isShow',width:60,halign:'center',formatter:getIsShow">显示状态</th>
				<th align='center' data-options="field:'flag',width:60,halign:'center',formatter:getFlag">是否优先</th>
				<th align='center' data-options="field:'cz',width:120,halign:'center',formatter:cz">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dialog_new" class="easyui-dialog" closed="true" title="添加" style="width:400px;height:420px"
		data-options="iconCls:'icon-comment-add',closable:true,cache:false,modal:true,buttons:buttons_new">
 		  <form id="form_new">
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="80px">姓名：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_new" name="name" style="width:300px" 
							data-options="validType:'length[0,20]',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">用户编号：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="bh_new" name="bh" style="width:300px" 
							data-options="validType:'length[0,20]',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">电话：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="phone_new" name="phone" style="width:300px" 
							data-options="validType:'length[0,30]',invalidMessage:'不能超过30个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">类型：</td>
 		  			<td>
 		  				<select id="type_new" class="easyui-combobox" name="type" style="width:300px;" data-options="editable:false,multiple:false,required:true">   
						    <option value="1">表扬</option>
						    <option value="2">意见</option>   
						    <option value="3">建议</option>   
						    <option value="4">投诉</option>   
						    <option value="5">举报</option>   
						</select>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">内容：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="content_new" name="content" style="width:300px;height:200px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,1500]',invalidMessage:'不能超过1500个字符！'"/>
 		  			</td>
 		  		</tr>
 		  	</table>
 		  </form>
 	</div>
 	<div id="dialog_edit" class="easyui-dialog" closed="true" title="修改" style="width:450px;height:540px"
 		data-options="iconCls:'icon-comment-edit',closable:true,cache:false,modal:true,buttons:buttons_edit">
 		  <form id="form_edit">
 		  	<input type="hidden" id="id" name="id"/>
 		  	<table>
 		  		<tr>
 		  			<td align="right" width="100px">姓名：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="name_edit" name="name" style="width:300px" 
							data-options="validType:'length[0,20]',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="100px">用户编号：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="bh_edit" name="bh" style="width:300px" 
							data-options="validType:'length[0,20]',invalidMessage:'不能超过20个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">电话：</td>
 		  			<td>
 		  				<input class="easyui-textbox" id="phone_edit" name="phone" style="width:300px" 
							data-options="validType:'length[0,30]',invalidMessage:'不能超过30个字符！'"/>
 		  			</td>
 		  		</tr>
 		  		<tr>
 		  			<td align="right" width="80px">类型：</td>
 		  			<td>
 		  				<select id="type_edit" class="easyui-combobox" name="type" style="width:300px;" data-options="editable:false,multiple:false,required:true">   
						    <option value="1">表扬</option>
						    <option value="2">意见</option>
						    <option value="3">建议</option>   
						    <option value="4">投诉</option>
						    <option value="5">举报</option>   
						</select>
 		  			</td>
 		  		</tr>
 		  		<tr>
					<td align="right">内容：</td>
					<td>
						<input class="easyui-textbox" id="content_edit" name="content" style="width:300px;height:150px" 
							data-options="required:true,missingMessage:'不能为空',multiline:true,validType:'length[0,1500]',invalidMessage:'不能超过1500个字符！'"/>
					</td>
				</tr>
				<tr>
					<td align="right">是否反馈：</td>
					<td>
						<label><input type="radio" name="hasFk" value="1" />反馈</label>
    					<label><input type="radio" name="hasFk" value="0" />不反馈</label>
					</td>
				</tr>
				<tr>
					<td align="right">反馈内容：</td>
					<td>
						<input class="easyui-textbox" id="feedback_edit" name="feedback" style="width:300px;height:150px" 
							data-options="multiline:true,validType:'length[0,1500]',invalidMessage:'不能超过1500个字符！'"/>
					</td>
				</tr>
 		  	</table>
 		  </form>
 	</div>
</body>
</html>