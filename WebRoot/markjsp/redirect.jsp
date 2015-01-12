<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>3秒跳转页面</title>
    <script type="text/javascript">
    	var i=2;
    	function sentTo(){
			var obj=document.getElementById("s");
			obj.innerHTML="页面将在"+i+"秒后跳转到主页面....<a href='JSP/login.jsp'>（点此直接进入）</a>";
			if(i--==0){
				window.location="JSP/login.jsp";
			}
    	}
    	window.setInterval(sentTo,1000);
    </script>
  </head>
  
  <body>
    <span id="s" style="margin-left: 200px;">页面将在3秒后跳转到主页面....<a href="JSP/login.jsp">（点此直接进入）</a></span>
  </body>
</html>
