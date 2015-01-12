<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/HTML/login_re.html"></jsp:include>
<script type="text/javascript" src="../JS/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="../JS/ajax.js" ></script>
<script type="text/javascript">
//表单提交前的判断
	function doCheck(obj){
		var temp = obj;
		var code = document.getElementById("verify").value;
		//判断是否是登陆操作
		if (temp == "登陆"){
			document.getElementById("type").value = "login";
			
			var username = document.getElementById("email").value;
			var psw = document.getElementById("password").value;
			if (username=="" || psw == "" || code == ""){
				alert("用户名或密码不能为空！");
				return false;
			}
			else{
				//登陆用ajax判断
				check_login();				
			}
		}
		else if (temp == "注册"){
			document.getElementById("type").value = "login_re";
			//验证注册信息的合法性
			var email = document.getElementById("email").value;
			var psw1 = document.getElementById("password").value;
			var name = document.getElementById("name").value;
			if (email=="" || psw1=="" || name=="" || code==""){
				alert("请保证注册的信息不为空");
				return false;
			}else{
				//验证用户名是否存在（即邮箱）
				check_username();
			}
		}else{alert("test");}
	}
</script>

