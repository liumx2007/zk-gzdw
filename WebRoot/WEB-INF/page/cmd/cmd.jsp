<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <link rel="stylesheet" type="text/css" href="resources/bootstrap-3.3.7/css/bootstrap.css"/>
    <style type="text/css">
        table td {font-size: 14px;}
    </style>
    <script type="text/javascript" src="resources/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="resources/js/jquery-easyui/plugins/jquery.parser.js"></script>
    <script type="text/javascript" src="resources/js/time/moment.js"></script>
    <script type="text/javascript" src="resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //全局变量
        var myData ;
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
        //操作列
        function cz(value, row, index) {
            var cz =
                "<a href='javascript:void(0)' onclick='edit(\""+row.id+"\");' style='margin-right:5px;'>编辑</a>"+
                "<a href='javascript:void(0)' onclick='del(\""+row.id+"\");' style='margin-right:5px;'>删除</a>";
            return cz;
        }
        //编辑
        function edit(id) {
            $("#form_edit").form("clear");
            //请求单个指令集合
            $("#direct_list_edit").empty();
            $.ajax({
                type: "GET",
                url: "r/cmdList/one",
                data: "id="+id,
                success: function(object){
                    var eidtData = object.data;
                    $("#directListName_edit").val(eidtData.directListName);
                    $("#description_edit").val(eidtData.description);
                    myData = object.data.cmdList;
                    $.each(myData,function (index,item) {
                        var checkbox=$("<input type='checkbox' class='check_item' />");
                        var  lable = $("<label></label>").append(item.description);
                        var  input = $("<label style='display:none' class='id_class'></label>").append(item.direct);
                        $("<div></div>").append(checkbox).append(lable).append(input).appendTo("#direct_list_edit");
                    })
                }, error: function(msg) {

                }
            });
            modelOpen1();
        }
        //单个删除
        function del(id) {
            $.messager.confirm("确认框", '确定删除？', function(r){
                if (r){
                    $.ajax({
                        type: "POST",
                        url: "r/cmdList/delete",
                        data: "id="+id,
                        success: function(object){
                            if(object.msg = "操作成功") {
                                $('#cmd_list_table').datagrid("reload");
                            }
                            showMessage(object);
                        }, error: function(msg) {
                            showMessage({type:"error",message:"删除失败！"});
                        }
                    });
                }
            });
        }
        //添加按钮
        function add() {
            $("#form_add").form("clear");
            //请求单个指令集合
            $("#direct_list").empty();
            $.ajax({
                type: "GET",
                url: "r/cmd/list",
                dataType : "json",
                success: function(object){
                    myData = object.data;
                    $.each(myData,function (index,item) {
                        var checkbox=$("<input type='checkbox' class='check_item' />");
                        var  lable = $("<label></label>").append(item.description);
                        var  input = $("<label style='display:none' class='id_class'></label>").append(item.direct);
                        $("<div></div>").append(checkbox).append(lable).append(input).appendTo("#direct_list");
                    })
                }, error: function(msg) {

                }
            });
            modelOpen();
        }
        //批量删除
        function clear() {

        }
        //添加模态框
        function modelOpen() {
            $("#myModal").modal({
                backdrop:"static",
                keyboard: true
            });
        }
        //编辑模态框
        function modelOpen1() {
            $("#myModal_edit").modal({
                backdrop:"static",
                keyboard: true
            });
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
        <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>--%>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>--%>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="clear()">删除</a>
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
<!--添加和编辑表单-->
<!-- Modal 添加-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <form id="form_add" action="">
                    <div class="form-group">
                        <label for="directListName">指令名称</label>
                        <input type="text" class="form-control" id="directListName"  name="directListName">
                    </div>
                    <div class="form-group">
                        <label >指令集合：</label>
                        <div id="direct_list">

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description">指令描述</label>
                        <input type="text" class="form-control" id="description"  name="description">
                    </div>
                </form>
            </div>
            <!-- 尾部分页数据 -->
            <div class="row">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="add_save" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
<!--Model End-->
<!-- Modal 编辑-->
<div class="modal fade" id="myModal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel_edit">编辑</h4>
            </div>
            <div class="modal-body">
                <form id="form_edit" >
                    <div class="form-group">
                        <label for="directListName_edit">指令名称</label>
                        <input type="text" class="form-control" id="directListName_edit" name="directListName" >
                    </div>
                    <div class="form-group">
                        <label >指令集合：</label>
                        <div id="direct_list_edit">

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description_edit">指令描述</label>
                        <input type="text" class="form-control" id="description_edit" name="description" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
<!--Model End-->
</body>
</html>
<script type="text/javascript">
    $("#add_save").click(function () {
        var  directId = "";
            //遍历选中的指令的id
          $.each($(".check_item:checked"),function () {
                directId += $(this).parent("div").find("label:eq(1)").text()+",";
            })
        directId = directId.substring(0, directId.length-1);
        console.log(directId);
          var  directListName = $("#directListName").val();
          var  description = $("#description").val();
        $.ajax({
            type: "POST",
            url: "r/cmdList/save",
            data:"directListName="+directListName+"&description="+description+"&directList="+encodeURIComponent(directId),
            success: function(object){
                console.log(object);
            }, error: function(msg) {

            }
        });
        $("#myModal").modal('hide');
        $('#cmd_list_table').datagrid("reload");
    });
</script>