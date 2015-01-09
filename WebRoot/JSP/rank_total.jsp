<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%

	String param = request.getParameter("param");
	String deString = "";
	if (param != null && param!= ""){
		deString = URLDecoder.decode(URLDecoder.decode(param, "utf-8"));
	}
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>车曝光-排行</title>
		<meta name="title" content="车曝网" />
		<meta name="keywords" content="车曝网,汽车,轿车,二手车,曝光,质量,驾驶安全,反馈,调查报告,数据分析,召回,拍卖,竞价,消费者权益,315,车"/>
		<meta name="description" content="车曝网是国内首家收集整理车主驾驶体验信息的完全公开完全开放的民间网站，车曝网为广大车主以及准车主提供大量汽车质量的数据分析服务以及二手车交易信息服务。" />
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script type="text/javascript" src="../JS/rank_total.js" ></script>
<script type="text/javascript">

	function rankload(){
	var carTitle='<%= deString %>';
	setCar(carTitle);
	jakeChartProblem();
	}
	addLoadEvent(rankload);	
</script>
	</head>
	<body>
<jsp:include page="/JSP/module_header.jsp" flush="true" ></jsp:include>
        <div  class="global_content">
			<div  class="index_rank right">
             <jsp:include page="/JSP/module_rank.jsp" flush="true"></jsp:include>
             <jsp:include page="/JSP/module_report.jsp" flush="true"></jsp:include>
	    	</div>
		
		    <div class="index_content left">
			 <jsp:include page="/HTML/left_rank_problem.html"></jsp:include>
		    </div>
		</div>
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>