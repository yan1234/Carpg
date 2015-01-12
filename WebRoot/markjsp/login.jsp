<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
  </head>
  
  <body>
  	<c:if test="${user==null}">
	<form action="userinfo!checkLogin.action" method="post">
		<table cellpadding="1" cellspacing="1" align="center" width="198px">
			<tr>
				<td colspan="2">
					账号：<input type="text" name="email" style="width:120px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					密码：<input type="password" name="userpwd" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="登录"/>
					<input type="reset" value="重置" style="margin-left: 10px;"/>
					<a href="JSP/regist.jsp" style="margin-left: 15px;">未注册？</a>
				</td>
			</tr>
		</table>
	</form>
	</c:if>
	<c:if test="${user!=null}">
		你好，<a href="" >${user.username }</a><hr/>
		<a href="userinfo!logout.action">安全退出</a>
	</c:if>
  </body>
</html>
