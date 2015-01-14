<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.yj.carpg.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	List<Carinfo> list = (List<Carinfo>)request.getAttribute("lstCarinfo");
	Carinfo c = list.get(0);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>选择车辆首页</title>
		<link rel="stylesheet" type="text/css" href="mycss/css.css">
		<script type="text/javascript">
			var test = '<%= c.getCconfig()%>';
			alert(test);
		</script>
	</head>

	<body>
		<div id="dbody">
			<div id="dtop"></div>
			<div id="dnavi">
				<ul>
					<li>
						<a href="">首页</a>
					</li>
					<li>
						<a href="">车型</a>
					</li>
					<li>
						<a href="">客户中心</a>
					</li>
					<li>
						<a href="">我要吐槽</a>
					</li>
					<li>
						<a href="">建言纳谏</a>
					</li>
				</ul>
			</div>
			<div id="dcontent">
				<div id="dleft">
					<div id="dleft_top"><jsp:include page="login.jsp" /></div>
				</div>
				<div id="dcenter">
					<div class="filterbar">
						<table cellspacing="0" cellpadding="0" class="car_tbfilter">
							<colgroup>
								<col>
								<col class="w120">
								<col class="w90">
							</colgroup>
							<tr class="bg">
								<td style="width:600px;">
									<span class="checkbox"><input type="checkbox" id="isPic">
										<label for="isPic">
											只看有图
										</label>
									</span>
									<span class="checkbox cancelhand">
										<label>共找到 <font color="red">N</font> 辆二手车</label>
									</span>
								</td>
								<td style="width:100px;">
									价格
								</td>
								<td style="width:100px;">
									消费保障
								</td>
							</tr>
						</table>
					</div>


					<c:forEach var="carinfo" items="${lstCarinfo}">
						<div id="dcar">
						<table>
							<tr>
								<td style="width:130px; ">
									<a href="carinfo!findById.action?cid=${carinfo.cid }">
									<img width="130px;" height="97.5px;" src="${carinfo.cimage[0] }" /></a>
								</td>
								<td style="width:470px; background-color: pink;">
									<ul>
										<li><a href="carinfo!findById.action?cid=${carinfo.cid }">${carinfo.csign }${carinfo.cseries }${carinfo.cstyle }</a></li>
										<li>
											<c:choose><c:when test="${fn:length(carinfo.cdetails) > 25}">
											${fn:substring(carinfo.cdetails,0,25)} ...
											</c:when></c:choose>
										</li>
										<li>购于${carinfo.cregistertime }/${carinfo.cmileage }万公里</li>
									</ul>
								</td>
								<td style="width:100px;">
									<font color="red">${carinfo.cprice }</font>万元
								</td>
								<td style="width:100px;">
									<img width="100px;" height="50px;" src="images/xiaofeibaozhang.jpg"/>
								</td>
							</tr>
						</table>
						</div>
					</c:forEach>
					<div id="dcenter_index">
						<c:if test="${1==totalPage}">
						[上一页]
						<script>
							for(var i=0;i<${totalPage};i++){
								if(i+1==${pageIndex}){
									document.writeln("["+(i+1)+"]");
								}else{
									document.writeln("<a href='carinfo!pagingCarinfo.action?pageIndex="+(i+1)+"'>["+(i+1)+"]</a>");
								}
							}
						</script>
						[下一页]
					</c:if>
						<c:if test="${pageIndex==1&&1!=totalPage}">
						[上一页]
						<script>
							for(var i=0;i<${totalPage};i++){
								if(i+1==${pageIndex}){
									document.writeln("["+(i+1)+"]");
								}else{
									document.writeln("<a href='carinfo!pagingCarinfo.action?pageIndex="+(i+1)+"'>["+(i+1)+"]</a>");
								}
							}
						</script>
							<a href="carinfo!pagingCarinfo.action?pageIndex=${pageIndex+1 }">[下一页]</a>
						</c:if>
						<c:if test="${pageIndex==totalPage&&1!=totalPage}">
							<a href="carinfo!pagingCarinfo.action?pageIndex=${pageIndex-1 }">[上一页]</a>
							<script>
							for(var i=0;i<${totalPage};i++){
								if(i+1==${pageIndex}){
									document.writeln("["+(i+1)+"]");
								}else{
									document.writeln("<a href='carinfo!pagingCarinfo.action?pageIndex="+(i+1)+"'>["+(i+1)+"]</a>");
								}
							}
						</script>
						[下一页]
					</c:if>
						<c:if test="${pageIndex>1&&pageIndex<totalPage}">
							<a href="carinfo!pagingCarinfo.action?pageIndex=${pageIndex-1 }">[上一页]</a>
							<script>
							for(var i=0;i<${totalPage};i++){
								if(i+1==${pageIndex}){
									document.writeln("["+(i+1)+"]");
								}else{
									document.writeln("<a href='carinfo!pagingCarinfo.action?pageIndex="+(i+1)+"'>["+(i+1)+"]</a>");
								}
							}
						</script>
							<a href="carinfo!pagingCarinfo.action?pageIndex=${pageIndex+1 }">[下一页]</a>
						</c:if>
					</div>
				</div>
			</div>
			<div id="dbottom"><jsp:include page="bottom.jsp" /></div>
		</div>
	</body>
</html>
