<%@page import="com.zzqx.mvc.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>数据列表</title>

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
    <script type="text/javascript">
        $(function(){
            $('#dataGrid').datagrid({
                url: "interactionlog/list.do",
                idField: "id",
                border: 0,
                nowrap: true,
                striped: true,
                rownumbers: true,
                fit: true,
                frozenColumns: [[
                    {field:'ck',checkbox:true}
                ]],
                columns: [[
                    {field:'interactionLogList.interaction.interactName',title:'交互名称',width:200,align:'center'},
                    {field:'interactionLogList.clickTime',title:'点击时间',width:200,align:'center'},
                    {field:'interactionLogList.folderType',title:'类型',width:200,align:'center',formatter:getType}
                ]]
            });
        });
        function getType(v,r,i) {
            if(v == 1){
                return "展项";
            }
            if(v == 2){
                return "业务";
            }
            if(v == 3){
                return "交互触点";
            }
        }
    </script>
</head>

<body>
<table id="dataGrid"></table>
</body>
</html>
