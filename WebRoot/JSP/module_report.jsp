<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	//获取首页新闻模块的数据（各5条）
	String msgReport0 = (String)request.getSession().getAttribute("msgReport0");
	String msgReport1 = (String)request.getSession().getAttribute("msgReport1");
%>
<script type="text/javascript">
var newsPreJson1=<%= msgReport0%>;
var newsPreJson2=<%= msgReport1%>;
function addnewsPre(objID,mjson){
    var aul=document.getElementById(objID);
    var lis=aul.getElementsByTagName("li");
    for (var i=0;i<lis.length;i++){
    if(mjson[i]){
    var atime = mjson[i].time.split("-");
    var mtime = atime[1]+"-"+atime[2];

    //给span赋值
    lis[i].children[0].innerText=mtime;
    //给a赋值
    lis[i].children[1].innerText=mjson[i].title;
    //给a加href
	//var str=JSON.stringify(mjson[i]); 
    }
    }
}
	//向下一个页面发送详细数据
	function post(i){
		var myjson = newsPreJson1;
		var count = i;
		if (i>=5){
			myjson = newsPreJson2;
			count = i - 5;
		}
		document.getElementById("paramModule").value = JSON.stringify(myjson[count]);
		document.getElementById("formModule").submit();
		return true;
	}
function loadnewsPre(){
addnewsPre("newsPre1",newsPreJson1);
addnewsPre("newsPre2",newsPreJson2);

}
addLoadEvent(loadnewsPre);
</script>
<jsp:include page="/HTML/right_news.html"></jsp:include>