<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-车讯列表</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<link rel="stylesheet" href="../CSS/color.css" />
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script src="../JS/loadnews.js" type="text/javascript" charset="utf-8"></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript">
	    	addLoadEvent(setmeta);
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
		    	<ul class="left-li-clear" id="newsListContent">				
					<li class="view_newsTitle">
						<h2 class="newsGrey"><b id="nColumn">汽车召回</b></h2>
					</li>
					<script type="text/javascript">
var newsCount=0;//设置计数器
var maxsize=5;//设置加载一次的新闻条数
var jakeJson=[
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
];
var newsJson=jakeJson;//需传值
var nColumn=1;//传一个type过来
setNewsColumn(nColumn);//设置新闻栏目名称	
loadnews(maxsize);//数字表示展示新闻的条数
addLoadEvent(setLiClick);//添加点击事件
					</script>
			 <!--<jsp:include page="/HTML/left_newslist.html"></jsp:include>-->
			 
			 	</ul>
			 	<div id="view_more">more</div>
		    </div>
		</div>
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>
