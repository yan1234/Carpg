<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//传排行需要的值，前三的信息，吐槽数
	String msgProblem = (String)request.getSession().getAttribute("msgStatistic0");
	String msgCar = (String)request.getSession().getAttribute("msgStatistic1");
%>
<script type="text/javascript">
	var hotProblem = <%= msgProblem%>;
	var hotCar = <%= msgCar%>;
	
</script>
<jsp:include page="/HTML/right_rank.html"></jsp:include>
<script type="text/javascript" src="../JS/rank_total.js" ></script>
<script type="text/javascript">
	loadrightrank("hotProblem",hotProblem);
	loadrightrank("hotCar",hotCar);
</script> 