<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"/>
<title>上传照片</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.fileInput{width:102px;height:34px; background:url("resources/images/uploadFileBtn.png");overflow:hidden;position:relative;}
 	.file{position:absolute;top:-100px;}
 	.uploadFileBtn{width:102px;height:34px;opacity:0;filter:alpha(opacity=0);cursor:pointer;}
</style>
</head>

<body>
	<form action="photo/upload.do" method="post" enctype="multipart/form-data">
		<div style="margin-left: 100px; margin-top: 30px;">
			<div class="fileInput left">
				<input type="file" name="file" id="file" class="file" onchange="document.getElementById('upfileResult').innerHTML=this.value"/>
		      	<input class="uploadFileBtn" type="button" value="上传图片" onclick="document.getElementById('file').click()" />
		    </div>
		    <span class="tip left" id="upfileResult"></span>
	    </div>
		<div style="margin-left: 100px; margin-top: 30px;"><input style="width: 60px;height: 30px;" type="submit" value="上传"/></div>
	</form>
</body>
</html>