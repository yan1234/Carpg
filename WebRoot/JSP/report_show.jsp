<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-车讯列表</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script src="../JS/loadmsg.js" type="text/javascript" charset="utf-8"></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript">
	    	addLoadEvent(setmeta);
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
		    	<ul class="left-li-clear" id="newsListContent">				
					<li class="view_newsTitle">
						<h2 class="newsGrey"><b id="nColumn">汽车召回</b></h2>
					</li>
					<script type="text/javascript">
//var msgJson=jakeJson;
var msgJson = <%= msg%>;
var nColumn=1;//传一个type过来
var maxsize=4;//设置每次展示条数
var msgCount=0;//单页消息计数器
var msgType="news";//设置展示模式，新闻or吐槽
function rshow(){
setNewsColumn(nColumn);//设置新闻栏目名称	
loadmessage(maxsize);//数字表示展示新闻的条数
setLiClick();
}

addLoadEvent(rshow);//添加点击事件
					</script>
			 <!--<jsp:include page="/HTML/left_newslist.html"></jsp:include>-->
                  <form id="formShow" method="post" action="report_detail.jsp" onsubmit="return false;" hidden="true">
                  	<input type="text" id="paramShow" name="param" hidden="true" />
                  	<input type="submit" hidden="true"/>
                  </form>
			 	</ul>
			 	<div id="view_more">more</div>
		    </div>
		</div>
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>
