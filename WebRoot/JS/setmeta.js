
//写入meta charset
function setmeta () {
	var ahead=document.getElementsByTagName("head");
	var aTitle=document.getElementsByTagName("title");
	var ameta=document.createElement("meta");
	ameta.setAttribute("charset","utf-8");
	var bmeta=document.createElement("meta");
	bmeta.setAttribute("name","title");
	bmeta.setAttribute("content","车曝光");
	var cmeta=document.createElement("meta");
	cmeta.setAttribute("name","keywords");
	cmeta.setAttribute("content","车曝光,汽车,轿车,二手车,曝光,质量,驾驶安全,反馈,调查报告,数据分析,召回,拍卖,竞价,消费者权益,315,车");
	var dmeta=document.createElement("meta");
	dmeta.setAttribute("name","description");
	dmeta.setAttribute("content","车曝光是国内首家收集整理车主驾驶体验信息的完全公开完全开放的民间网站，车曝光为广大车主以及准车主提供大量汽车质量的数据分析服务以及二手车交易信息服务。");
	var emeta=document.createElement("meta");
	emeta.setAttribute("name","renderer");
	emeta.setAttribute("content","webkit");
	var fmeta=document.createElement("meta");
	fmeta.setAttribute("http-equiv","X-UA-Compatible");
	fmeta.setAttribute("content","IE=edge,chrome=1");
	var gmeta=document.createElement("meta");
	gmeta.setAttribute("name","author");
	gmeta.setAttribute("content","zoe");
	var hmeta=document.createElement("meta");
	hmeta.setAttribute("name","robots");
	hmeta.setAttribute("content","index,follow")

    ahead[0].setAttribute("lang","zh-cmn-Hans");
	ahead[0].insertBefore(ameta,aTitle[0]);
	ahead[0].insertBefore(bmeta,aTitle[0]);
	ahead[0].insertBefore(cmeta,aTitle[0]);
	ahead[0].insertBefore(dmeta,aTitle[0]);
	ahead[0].insertBefore(emeta,aTitle[0]);
	ahead[0].insertBefore(fmeta,aTitle[0]);
	ahead[0].insertBefore(gmeta,aTitle[0]);
	ahead[0].insertBefore(hmeta,aTitle[0]);
	//判断是否为移动端，隐藏左侧二维码
	if (navigator.userAgent.match(/(iphone|ipod|android|ios|windows phone)/i)){
	document.getElementById("followUs").style.display="none";
	}
}