<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%

	String param = request.getParameter("param");
	String msg = (String)request.getAttribute("msg");
	String deString = "";
	if (param != null && param!= ""){
		deString = URLDecoder.decode(URLDecoder.decode(param, "utf-8"));
	}
	
%>

<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-排行</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script type="text/javascript" src="../JS/rank_total.js" ></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript" src="../JS/external/json2.js"></script>
	    <script type="text/javascript">
        addLoadEvent(setmeta);//设置meta内容
	    </script>
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