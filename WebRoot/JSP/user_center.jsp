<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-促进车辆质量提升</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
		<script type="text/javascript" src="../JS/external/json2.js"></script>
		<script type="text/javascript" src="../JS/external/brand.js"></script>
		<script type="text/javascript" src="../JS/external/brand_select.js"></script>
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript">
        addLoadEvent(setmeta);//设置meta内容
	    </script>
	</head>
	<body>
<jsp:include page="/JSP/module_header.jsp" flush="true" ></jsp:include>
        <div  class="global_content">
            <div id="view_left" class="left">
				<ul>
					<li class="quicklook1"><a href="#"><h4>吐槽记录 》 》</h4><h6>自黑式嘲讽</h6></a></li>
    				<li class="quicklook2"><a href="#"><h4>我的投稿 》 》</h4><h6>要成为业余专家的人</h6></a></li>
				
				</ul>
			</div>
			<div id="view_main" class="left">
				<div id="view_mainNav">
					<ul>
						<li><a href="">这是您在车曝光的记录</a></li>
					</ul>
				</div>
				<ul class="left-li-clear" id="newsListContent">	
//var msgJson=jakeJson;
var msgJson = <%= msg %>;
var maxsize=4;//设置每次展示条数
var msgCount=0;//单页消息计数器
var msgType="news";//设置展示模式，新闻or吐槽
function rshow(){	
loadmessage(maxsize);//载入
setLiClick();
}

addLoadEvent(rshow);//添加点击事件
				</ul>
				<div id="view_more" class="view_complainBorder"><a >加载更多........</a></div>
				<div>
					<button class="dashButtons left">上一页</button>
					 <button class="dashButtons right">下一页</button>
				</div>
			</div>
			
			<div  class="view_right right">
                	
                		<form action="" method="post" id="view_researchComplains">		
                			<input type="search" value="" placeholder="搜索您关心的问题"/>
                			<button type="button"><img src="../images/img/research.png"/></button>
                		</form>
                	
				<div id="view_rightNav">
						<h3>常见问题&nbsp;》</h3>
					<ul>					
				    <li><a href="#">空调制冷/加热</a></li>
                	<li><a href="#">汽车外部配件</a></li>
                	<li><a href="#">汽车内部配件</a></li>
                	<li><a href="#">车体/油漆</a></li>
                	<li><a href="#">刹车</a></li>
                	<li><a href="#">离合器</a></li>
                	<li><a href="#">冷却系统</a></li>
                	<li><a href="#">动力传动系统</a></li>
                	<li><a href="#">汽车电力系统</a></li>
                	<li><a href="#">引擎</a></li>
                	<li><a href="#">排气系统</a></li>
                	<li><a href="#">能源系统</a></li>
                	<li><a href="#">灯</a></li>
                	<li><a href="#">安全带/安全气囊</a></li>
                	<li><a href="#">转向</a></li>
                	<li><a href="#">汽车悬架</a></li>
                	<li><a href="#">变速器</a></li>
                	<li><a href="#">车轮/轮毂</a></li>
                	<li><a href="#">车窗/挡风玻璃</a></li>

					</ul>
				</div>
				<div id="view_banner1" class="view_complainBorder"></div>
				<div id="view_banner2" class="view_complainBorder"></div>
			</div>
		</div>
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>


