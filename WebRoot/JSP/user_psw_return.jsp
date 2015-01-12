<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="/HTML/login_findpsw.html"></jsp:include>
<script type="text/javascript" src="../JS/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../JS/ajax.js" ></script>

<script type="text/javascript">
	function send(){
		if (document.getElementById("verify").value != ""){
			document.getElementById("login_reg").action="userOperate!returnPsw";
			document.getElementById("login_reg").submit();
			parent.location.reload();
			return true;
		}else {alert("请输入验证码");return false;}
		
	}		
</script>  
