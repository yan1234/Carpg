<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-促进车辆质量提升</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
		<script type="text/javascript" src="../JS/test/brand.js"></script>
		<script type="text/javascript" src="../JS/test/selectJs_20140324.js"></script>
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript">
        addLoadEvent(setmeta);//设置meta内容
	    </script>
	</head>
	<body>
<jsp:include page="header.jsp" flush="true" ></jsp:include>
        <div  class="global_content">
			<div  class="index_rank right">
             <jsp:include page="/HTML/right_rank.html"></jsp:include>
             <jsp:include page="/HTML/right_news.html"></jsp:include>
	    	</div>
		
		    <div class="index_content left">
			 <jsp:include page="/HTML/left_index.html"></jsp:include>
		    </div>
		</div>
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>
