<%--<%@page import="com.zzqx.mvc.entity.InteractionLog"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>指令集</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="resources/js/jquery-easyui/themes/icon.css"/>
    <style type="text/css">
        table td {font-size: 14px;}
    </style>
    <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/plugins/jquery.parser.js"></script>
    <script type="text/javascript" src="resources/js/time/moment.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#cmd_list_table").datagrid({
                url: "cmdList/list.do",
                idField: "id",
                split: false,
                fit: true,
                border: 0,
                frozenColumns: [[{field:'ck',checkbox:true}]],
                pagination:true,
                rownumbers: true,
//                toolbar: [
//                    {text:'添加',iconCls:'icon-add',handler:function(){
//                        $("#realName_add").textbox("setValue","");
//                        $("#userName_add").textbox("setValue","");
//                        $("#password_add").textbox("setValue","");
//                        $("#dialog_add").dialog("open");
//                    }}
//                ],
            });
            var p = $('#cmd_list_table').datagrid('getPager');
            if (p){
                $(p).pagination({
                    pageList: [10,15,20],//可以设置每页记录条数的列表
                    beforePageText: '第',//页数文本框前显示的汉字
                    afterPageText: '页    共 {pages} 页',
                    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
                });
            };
        });

//        function getName(v,r,i) {
//            var obj  = eval(v);
//            return obj.cmdListName;
//        };
//        function getCmdList(v,r,i) {
//
//        };
//        function getDescription(v,r,i) {
//
//        }
        function cz(value, row, index) {
            var cz =
                "<a href='javascript:void(0)' onclick='edit(\""+row.id+"\");' style='margin-right:5px;'>编辑</a>"+
                "<a href='javascript:void(0)' onclick='del(\""+row.id+"\");' style='margin-right:5px;'>删除</a>";
            return cz;
        }
        function edit(id) {

        }
        function del(id) {

        }
    </script>
</head>

<body>

<table id="cmd_list_table" toolbar="#tb">
    <thead>
    <tr>
        <th align='center' data-options="field:'directListName',width:150,halign:'center'">指令名称</th>
        <th align='center' data-options="field:'directList',width:300,halign:'center'">指令集合</th>
        <th align='center' data-options="field:'description',width:300,halign:'center'">指令描述</th>
        <th align='center' data-options="field:'cz',width:100,halign:'center',formatter:cz">操作</th>
    </tr>
    </thead>
</table>

<!--工具栏-->
<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>--%>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <%--<div>--%>
        <%--Date From: <input class="easyui-datebox" style="width:80px">--%>
        <%--To: <input class="easyui-datebox" style="width:80px">--%>
        <%--Language:--%>
        <%--<input class="easyui-combobox" style="width:100px"--%>
               <%--url="data/combobox_data.json"--%>
               <%--valueField="id" textField="text">--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>--%>
    <%--</div>--%>
</div>
</body>
</html>
