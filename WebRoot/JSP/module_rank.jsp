<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//传排行需要的值，前三的信息，吐槽数
%>
<script type="text/javascript">
    var hotProblem=[
    {"name":"汽车悬架","nub":500},
    {"name":"变速器","nub":300},
    {"name":"发动机","nub":160}
    ];
    var hotCar=[
    {"name":"一汽-大众","nub":500},
    {"name":"上海-通用","nub":420},
    {"name":"长安","nub":300}
    ];	

</script>
<jsp:include page="/HTML/right_rank.html"></jsp:include>
<script type="text/javascript" src="../JS/rank_total.js" ></script>
<script type="text/javascript">
	loadrightrank("hotProblem",hotProblem);
	loadrightrank("hotCar",hotCar);
</script> 