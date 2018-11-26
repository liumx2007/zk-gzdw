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

    <title>交互体验数据</title>

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
            $("#log_table").datagrid({
                url: "interactionlog/list1.do",
                idField: "id",
                split: false,
                fit: true,
                border: 0,
                frozenColumns: [[{field:'ck',checkbox:true}]],
                pagination:true,
                rownumbers: true,
//                toolbar: "#bar",
            });
            var p = $('#log_table').datagrid('getPager');
            if (p){
                $(p).pagination({
                    pageList: [10,15,20],//可以设置每页记录条数的列表
                    beforePageText: '第',//页数文本框前显示的汉字
                    afterPageText: '页    共 {pages} 页',
                    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
                });
            };
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
        };
        function  FamterTime(v,r,i) {
            var date = Date.parse(v);
            return date;
        }
        function getName(v,r,i) {
            var obj  = eval(v);
            return obj.interactName;
        };
    </script>
</head>

<body>
    <%--<table id="dg" class="easyui-datagrid" title="交互体验数据" style="width:450px;height:250px"--%>
           <%--data-options="rownumbers:true,singleSelect:true,url:'interactionlog/list.do',method:'get'">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th data-options="field:'ck',checkbox:true"></th>--%>
            <%--<th data-options="field:'interactionLogList.interaction.interactName',width:80">交互名称</th>--%>
            <%--<th data-options="field:'interactionLogList.clickTime',width:100">点击时间</th>--%>
            <%--<th data-options="field:'interactionLogList.folderType',width:80,align:'right'">类型</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
    <%--</table>--%>
    <table id="log_table">
        <thead>
        <tr>
            <th align='center' data-options="field:'interaction',width:150,halign:'center',formatter:getName">交互名称</th>
            <th align='center' data-options="field:'clickTime',width:150,halign:'center',formatter:FamterTime">点击时间</th>
            <th align='center' data-options="field:'folderType',width:120,halign:'center',formatter:getType">类型</th>
        </tr>
        </thead>
    </table>
</body>
</html>
