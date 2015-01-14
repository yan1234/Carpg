<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.yj.carpg.dto.CarinfoItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择车辆首页</title>
	<link rel="stylesheet" type="text/css" href="mycss/css.css">
  </head>
  
  <body>
	<div id="dbody">
		<div id="dtop"></div>
		<div id="dnavi">
			<ul>
				<li><a href="">首页</a></li>
				<li><a href="">车型</a></li>
				<li><a href="">客户中心</a></li>
				<li><a href="">我要吐槽</a></li>
				<li><a href="">建言纳谏</a></li>
			</ul>
		</div>
		<div id="dcontent">
			<div id="dleft">
				<div id="dleft_top"><jsp:include page="login.jsp" /></div>
			</div>
			<div id="dcenter">
				<table width="100%" border="1px">
					<tr>
						<td><input type="checkbox" checked="checked"/>全选</td>
						<td>商品显示</td>
						<td>商品名称</td>
						<td>商品单价</td>
						<td>商品数量</td>
					</tr>
					<c:forEach var="carinfoItem" items="${shopcar}">
					<tr>
						<td><input type="checkbox" checked="checked"/></td>
						<td><img width="130px;" height="130px;" src="${carinfoItem.carinfo.cimage[0] }" /></td>
						<td>${carinfoItem.carinfo.cname }</td>
						<td>${carinfoItem.carinfo.cprice }</td>
						<td><input type="text" value="${carinfoItem.count }" name="count" style="width: 50px;"/></td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">合计：123456</td>
					</tr>
				</table>
				<a href="carinfo!pagingCarinfo.action" style="float:right;"><img src="images/continue_buy.PNG"></a>
			</div>
			<div id="dright"></div>
		</div>
		<div id="dbottom"><jsp:include page="bottom.jsp" /></div>
    </div>
  </body>
</html>
