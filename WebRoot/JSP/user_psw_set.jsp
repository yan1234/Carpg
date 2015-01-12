<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	//检验两次输入的密码一致
  function checkEquel(obj){
    var psw = document.getElementById("password").value;
    if (obj != psw){
      alert("请保证两次输入的密码一致!");
      return false;
    }
  }
	function update(){
		document.getElementById("login_reg").action="userOperate!updatePsw";
		document.getElementById("login_reg").submit();
		parent.location.reload();
	}
	
</script>  
<jsp:include page="../HTML/login_setpsw.html"></jsp:include>