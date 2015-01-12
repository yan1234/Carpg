<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String str = java.net.URLEncoder.encode("艳玲", "utf-8");
	String code = java.net.URLEncoder.encode(str);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择车辆首页</title>
	<link rel="stylesheet" type="text/css" href="mycss/css.css">
	<link rel="stylesheet" href="CSS/new_layout.css" />
	<script src="JS/util.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
	<div id="dbody">
		<div>
		<jsp:include page="../JSP/header.jsp" flush="true" ></jsp:include>
		</div>
		<div id="dcontent">
			<div id="cardetailleft">
				<div id="cardetailleft_top"><jsp:include page="login.jsp" /></div>
			</div>
			<div id="cardetailcenter">
				<div id="cardetailcenter_top">
					<div style="text-align: left;margin-top:10px;"><h1>${carinfo.csign }${carinfo.cseries }${carinfo.cstyle }</h1></div>
					<div id="cardetailcenter_img"><img src="${carinfo.cimage[0] }" width="300px" height="300px" /></div>
					<div id="cardetailcenter_display">
						<ul>
							<li id="cname">车名:${carinfo.cname }</li>
							<li>&yen;${carinfo.cprice }万</li>
							<li>车主:${carinfo.cowner }</li>
							<li>品牌:${carinfo.csign }</li>
						</ul>
					</div>
				</div>
				<div id="cardetailcenter_button" onclick="send();">
					 <a href="carinfo!shoppingCar.action?cid=${carinfo.cid }&cname=${carinfo.cname }&cprice=${carinfo.cprice}&cowner=${carinfo.cowner }&csign=${carinfo.csign}&cdetails=${carinfo.cdetails}&cimage=${carinfo.cimage[0]}"><img src="images/buy.PNG"/></a>
				</div>
				<div id="cardetailcenter_info">详情:${carinfo.cdetails }</div>
			</div>
			<div id="cardetailright"></div>
		</div>
		<div id="dbottom"><jsp:include page="bottom.jsp" /></div>
    </div>
  </body>
</html>
