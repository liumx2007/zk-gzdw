<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zzqx.mvc.entity.Order"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object o = request.getAttribute("order");
Order order = o==null?null:(Order) o;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"/>
<title>二维码</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<%if(order!=null) {%>
	<div style="text-align:center;">
		<div><span style="font-size: 40px;color: green;">恭喜您，预约成功！</span></div>
		<img alt="二维码" src="<%=basePath+order.getQrcode()%>">
		<div><span style="font-size: 30px">请保存此二维码，参观时出示。</span></div>
	</div>
	<%} else { %>
	<script type="text/javascript">
		window.location.href="r/order";
	</script>
	<%} %>
</body>
</html>