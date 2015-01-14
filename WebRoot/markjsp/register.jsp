<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>注册页面</title>
  </head>
  
  <body>
  	<c:if test="${user==null}">
	<form action="userinfo!checkRegister.action" method="post">
		<table cellpadding="1" cellspacing="1" align="center" width="230px">
			<tr>
				<td colspan="2">
					账　　号：<input type="text" name="userid" style="width:120px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					密　　码：<input type="password" name="userpwd1" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					确认密码：<input type="password" name="userpwd2" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="提交"/>
					<input type="reset" value="重置" style="margin-left: 10px;"/>
					<a href="userinfo!checkLogin.action" style="margin-left: 15px;">返回登录</a>
				</td>
			</tr>
		</table>
	</form>
	</c:if>
	<c:if test="${user!=null}">
		你好，<a href="" >${user.userid }</a><hr/>
		<a href="userinfo!logout.action">安全退出</a>
	</c:if>
  </body>
</html>
