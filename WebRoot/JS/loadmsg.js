//控制信息加载,用于report_show.jsp以及complaint_view.jsp
function loadmessage(maxsize) {
	//maxsize设置每次加载条数
	//type表示信息展示模式，吐槽模式，新闻模式
	
	for (var i = 0; i < maxsize; i++) {
		if (msgCount==msgJson.length) {
				//隐藏“加载更多”
				document.getElementById("view_more").hidden=true;
				break;
			}else{
				if(msgType=="news"){
				showNews();	
				}else if(msgType=="complain"){
				showComplain();
				}
				
			}
	}
	
}
//设置新闻列表页面的标题
function setNewsColumn (nColumn) {
	if (nColumn==1) {
		nColumn="汽车召回";
	}else if(nColumn==0){
		nColumn="缺陷调查";
	}else{
		nColumn="缺陷&召回";
	}
	document.getElementById("nColumn").innerHTML=""+nColumn;
}
//新闻列表页面创建DOM
function showNews () {
	if(msgJson[msgCount].car_type){
	var ali=document.createElement("li");
	ali.className="newsPreContent";
	ali.value=msgCount;
	var ap=document.createElement("p");
	var aspan=document.createElement("span");
	aspan.className="newsCategory left";
	aspan.innerHTML=msgJson[msgCount].car_type;
	var bspan=document.createElement("span");
	bspan.className=aspan.className;
	bspan.innerHTML="/"+msgJson[msgCount].agency;
	var cspan=document.createElement("span");
	cspan.className=aspan.className;
	cspan.innerHTML="/"+msgJson[msgCount].time;
	ap.appendChild(aspan);
	ap.appendChild(bspan);
	ap.appendChild(cspan);
	ali.appendChild(ap);
	var aimg=document.createElement("img");
	aimg.src="../images/"+msgJson[msgCount].image;
	aimg.alt=""+msgJson[msgCount].title;
	aimg.className="newsSmallPic left";
	ali.appendChild(aimg);
	var bp=document.createElement("p");
	bp.className="newsPreText";
	var ahref=document.createElement("a");
	ahref.href="#";
	var ahh=document.createElement("h2");
	ahh.innerHTML=""+msgJson[msgCount].title;
	var bhh=document.createElement("h5");
	bhh.innerHTML=""+getPreviewText(msgJson[msgCount].content);
	ahref.appendChild(ahh);
	ahref.appendChild(bhh);
	bp.appendChild(ahref);
	ali.appendChild(bp);
	var espan=document.createElement("span");
	espan.className="redLineNub right";
	espan.innerHTML="赞";
	ali.appendChild(espan);
	document.getElementById("newsListContent").appendChild(ali);
	msgCount++;
	}else{
	return false;
	}
}
//吐槽展示页面创建DOM
function showComplain (i) {
	//整条评论的容器
			if(msgJson[msgCount].car_brand){
			var view_mainComplains=document.createElement("div");
			view_mainComplains.className="view_mainComplains view_complainBorder";
			//user信息容器
			var avatar=document.createElement("div");
			avatar.className="avatar";
			var avatarPic=document.createElement("img");//用户头像
			//if (msgJson[msgCount].img!="") {
				//avatarPic.src="../images/"+msgJson[msgCount].img;
			//}else{
				avatarPic.src="../images/img/avatar.png";//默认用户头像
			//}
			avatarPic.className="avatarPic";
			var avatarName=document.createElement("p");
			avatarName.className="avatarName";
			if (msgJson[msgCount].user_name!="") {
				var timeinfo=msgJson[msgCount].time.split(" ");		       
				avatarName.innerHTML="<b>"+msgJson[msgCount].user_name+"</b><br/>"+timeinfo[1]+"<br/>"+timeinfo[0];
				}else{
				avatarName.innerHTML="<b>"+"车曝网友"+"</b><br/>"+timeinfo[1]+"<br/>"+timeinfo[0];					
				}
			avatar.appendChild(avatarPic);
			avatar.appendChild(avatarName);
			view_mainComplains.appendChild(avatar);
			//吐槽信息容器
			var view_complainContent=document.createElement("div");
			view_complainContent.className="view_complainContent";
			//吐槽标题
			var view_complainTitle=document.createElement("h4");
			view_complainTitle.className="view_complainTitle";
			view_complainTitle.innerHTML=""+msgJson[msgCount].car_brand+msgJson[msgCount].car_type+msgJson[msgCount].problem_detail;
			view_complainContent.appendChild(view_complainTitle);
			//问题标签
			var view_complainTips=document.createElement("ul");
			view_complainTips.className="view_complainTips";
			var tips=[msgJson[msgCount].problem_type,msgJson[msgCount].problem_problem,msgJson[msgCount].mileage,msgJson[msgCount].frequency];
			for (var n = 0; n <tips.length ; n++) {
				var a=document.createElement("li");
				a.innerHTML=""+tips[n];
				view_complainTips.appendChild(a);
			}
			view_complainContent.appendChild(view_complainTips);
			//吐槽内容
			var view_complainText=document.createElement("p");
			view_complainText.className="view_complainText";
			view_complainText.innerHTML=""+msgJson[msgCount].mark;
			view_complainContent.appendChild(view_complainText);
			//图片列表
			var view_complainPhoto=document.createElement("ul");
			view_complainPhoto.className="view_complainPhoto";
			var tempImg = msgJson[msgCount].image;
			var imgurl=tempImg.split(';');
			for (var j = 0; j < imgurl.length; j++) {
				var a=document.createElement("li");
				var b=document.createElement("img");
			    b.src="../images/"+imgurl[j];
			    a.appendChild(b);
			    view_complainPhoto.appendChild(a);
			}
			view_complainContent.appendChild(view_complainPhoto);
			//功能按键列表
			var view_complainAction=document.createElement("ul");
			view_complainAction.className="view_complainAction";
			var actiontext=['收藏','评论','转发','赞'];
			for (var b = 0; b < actiontext.length; b++) {
				var acli=document.createElement("li");				
				var actionBtn=document.createElement("a");
				actionBtn.id="actionBtn"+i+""+b;
				actionBtn.className="actionBtn";
				actionBtn.innerHTML=""+actiontext[b];
				acli.appendChild(actionBtn);
				view_complainAction.appendChild(acli);
			}
			view_complainContent.appendChild(view_complainAction);
			view_mainComplains.appendChild(view_complainContent);
			//添加到吐槽显示区
			document.getElementById("add_ccshere").appendChild(view_mainComplains);
			}else{
			return false;
			}
}
//新闻列表页面截取新闻内容预览，截取文章第一段的101个字，并加上....省略号
function getPreviewText (str) {
	var contentLength=100;//设置截取段落的长度
	//获得第一段，拆开第一个p，如果为空拆下一个
	var a=str.split("</p>");
	var atext;
	for (var i = 0; i < a.length; i++) {
		 var ainner=a[i].split(">");
		 if (ainner[1]) {
		 	atext=ainner[1];
		 	break;
		 }
	}
	if(atext.length>contentLength){
		atext=atext.substring(0,99);
	}
	atext+="......";//加上省略号输出
	return atext;
}

//给新闻列表加上点击事件
function setLiClick () {
	var lia=document.getElementsByClassName("newsPreContent");
	for (var i = 0; i < lia.length; i++) {
		lia[i].onclick=function (){
		document.getElementById("paramShow").value = JSON.stringify(msgJson[this.value]);
		document.getElementById("formShow").submit();
		return true;		
		}

	}
}
function postShow(){
		document.getElementById("paramShow").value = JSON.stringify(msgJson[this.value]);
		document.getElementById("formShow").submit();
		return true;
		}
//新闻详情页面
function loadnewsDetail () {
	document.getElementById("category").innerHTML=""+news.category;
	document.getElementById("nTitle").innerText=news.title;
	document.getElementById("info").innerHTML=news.author+"/"+news.time;
	if (news.image) {
		document.getElementById("image").src="../images/"+news.image;
	}
	document.getElementById("content").innerHTML=""+news.content;
	var source=document.getElementById("author");
	source.innerText=news.author;
	var aurl=document.createElement("a");
	aurl.href=""+news.url;
	aurl.innerText=news.url;
	source.appendChild(aurl);
}
//滚动到底部时加载更多
//滚动条在Y轴上的滚动距离
function getScrollTop(){
　　var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
　　if(document.body){
　　　　bodyScrollTop = document.body.scrollTop;
　　}
　　if(document.documentElement){
　　　　documentScrollTop = document.documentElement.scrollTop;
　　}
　　scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
　　return scrollTop;
}

//文档的总高度

function getScrollHeight(){
　　var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
　　if(document.body){
　　　　bodyScrollHeight = document.body.scrollHeight;
　　}
　　if(document.documentElement){
　　　　documentScrollHeight = document.documentElement.scrollHeight;
　　}
　　scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
　　return scrollHeight;
}

//浏览器视口的高度

function getWindowHeight(){
　　var windowHeight = 0;
　　if(document.compatMode == "CSS1Compat"){
　　　　windowHeight = document.documentElement.clientHeight;
　　}else{
　　　　windowHeight = document.body.clientHeight;
　　}
　　return windowHeight;
}

window.onscroll = function(){
　　if(getScrollTop() + getWindowHeight() == getScrollHeight()){
	 window.setTimeout("loadmessage(maxsize);setLiClick();",500);　　　　
　　}
};
var jakeJson=[
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
{"type":"1","category":"汽车召回","car":"沃尔沃XC60/S60L","seller":"浙江豪情汽车","time":"12-31","author":"国家质检总局","image":"../images/img/newsSmallPic.png","nTitle":"浙江豪情汽车召回部分国产沃尔沃XC60/S60L","content":"<p style=\"color=black\">日前，浙江豪情汽车制造有限公司根据《缺陷汽车产品召回管理条例》的要求，向国家质检总局备案了召回计划，决定自2014年12月31日起，召回2014年11月12日至2014年11月19日期间生产的部分国产2015款沃尔沃XC60及S60L汽车，共计529辆。</p>","praise":11,"url":"http://www.dpac.gov.cn/"},
];