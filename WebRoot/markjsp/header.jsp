<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String user = (String)request.getSession().getAttribute("user");
	//定义变量保存登陆用户的信息
	String loginUsername = ""; 
	if (null != user && !user.equals("")){
		String[] temp = user.split("~");
		loginUsername = temp[2];	
	}
 %>
<script type="text/javascript" src="JS/util.js"></script>
<script type="text/javascript">

 	function aaa () {
 		var username = "<%= loginUsername%>";
 		showUser(username);
 	}
 			 	//显示用户登录状态
    function showUser(username){
 		//var username="123";
 		if (username != ""){
 			document.getElementById("UserName").innerHTML=""+username;
 			document.getElementById("header_showUserName").hidden=false;
 		}else{
 			document.getElementById("header_log_re").hidden=false;
 			document.getElementById("header_showUserName").hidden=true;
 		}
 	}
//当前页面高亮
function highlightPage() {
  if (!document.getElementsByTagName) return false;
  if (!document.getElementById) return false;  
  var headers = document.getElementsByTagName('header');
  if (headers.length == 0) return false;
  var navs = headers[0].getElementsByTagName('nav');
  if (navs.length == 0) return false;
  
  var links = navs[0].getElementsByTagName("a");
  for (var i=0; i<links.length; i++) {
	  var linkurl;
	  for (var i=0; i<links.length; i++) {
	    linkurl = links[i].getAttribute("href");
	    if (window.location.href.indexOf(linkurl) != -1) {
	      links[i].className = "here";
	      var linktext = links[i].lastChild.nodeValue.toLowerCase();
	      document.body.setAttribute("id",linktext);
	    }
	  }
  }
}
//控制iframe的显示与关闭
function onShowIframe (showHref) {
				document.getElementById("myiframe").src=""+showHref;								       
				document.getElementById("content_iframe").hidden=false;//iframe容器
				document.getElementById("overlay_mask").hidden=false;//遮罩层
				
 	    }
function closeIframe () {
				document.getElementById("content_iframe").hidden=true;				
				document.getElementById("overlay_mask").hidden=true;
		}
 addLoadEvent(aaa);
</script>
<jsp:include page="/HTML/header.html"></jsp:include>
