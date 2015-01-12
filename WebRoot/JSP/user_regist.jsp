<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//得到用户信息
String name = (String)request.getAttribute("useralias");
String email = (String)request.getAttribute("email");
String psw = (String)request.getAttribute("userpwd");
%>
<script type="text/javascript" src="../JS/util.js"></script>
<script type="text/javascript">
	//加载页面信息,主要是填入已经编写的信息，昵称，邮箱，密码
	function addInfo(){
		document.getElementById("name").value = "<%= name%>";
		document.getElementById("email").value = "<%= email%>";
		document.getElementById("password").value = "<%= psw%>";
	}
	//添加需要预加载的onload事件
	addLoadEvent(addInfo);
	//检验两次输入的密码一致
  	function checkEquel(obj){
    	var psw = document.getElementById("password").value;
    	if (obj != psw){
      		alert("请保证两次输入的密码一致!");
      		return false;
    	}
 	}
 	//将省市区的值由地区码变为文字
  function changeToText(){
    var province = document.getElementById("id_provSelect");
    var city = document.getElementById("id_citySelect");
    var area = document.getElementById("id_areaSelect");
    //得到地区码对应的文字
    var p = province.childNodes[province.selectedIndex].innerHTML;
    var c = city.childNodes[city.selectedIndex].innerHTML;
    var a = area.childNodes[area.selectedIndex].innerHTML;
    document.getElementById("province").value = p;
    document.getElementById("city").value = c;
    document.getElementById("section").value = a;
  }
  //验证信息
  function doCheck(){
  	var checkbox = document.getElementById("radiobutton");
  	if (checkbox.checked == true){
  		//将地区码转变为对应的文字
  		changeToText();
  		document.getElementById("login_reg").action="userOperate!regist";
		document.getElementById("login_reg").submit();
		parent.location.reload();
		//关闭iframe
		closeIframe();
  	}else{
  		alert("请仔细阅读说明协议");
  	}
  	
  }
</script>
<jsp:include page="../HTML/regist.html"></jsp:include>

