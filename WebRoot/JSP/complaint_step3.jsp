<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	//得到上一个页面传到的车辆信息
	//String msg = request.getParameter("msg");
 %>
<script type="text/javascript">
//添加车辆问题
function addproblem (opValue) {
	if (opValue=="none") {
		document.getElementById("add_problem_text").hidden=false;
	}else{
		document.getElementById("add_problem_text").hidden=true;
	}
}
//添加造成的事故
function golSelect (objID,objValue) {
	
	if(objValue){
		document.getElementById(objID).nextSibling.nextSibling.hidden=false;
	}else{
		document.getElementById(objID).nextSibling.nextSibling.hidden=true;
	}
}
//检查表单
function checkSelect (objID) {
	var objs=document.getElementById(objID);
	if(objs.value=="default") {
		objs.style.color="#FF0000";
		objs.style.border="1px solid #FF0000";
	}else{
		objs.style.color="#000000";
		objs.style.border="1px solid #ABADB3";
	}
}
function doCheck () {
	if (document.getElementById("type").value=="default") {
		alert("请填写问题部位");
		return false;
	}
	if (document.getElementById("problem").value=="default") {
		alert("请添加问题描述");
		return false;
	}
	if (document.getElementById("start_time").value=="") {
		alert("请选择发生时间");
		//document.getElementById("start_time").style.border="1px solid #ABADB3";
		return false;
	}
	var objss=document.getElementsByName("course_detile");
	var toSub=document.getElementById("course");
	for (var i = 0; i < objss.length; i++) {
		if (objss[i].checked) {
			toSub.value=toSub.value+"~"+objss[i].value+objss[i].nextSibling.nextSibling.value;		
		}
	}
	if (document.getElementById("course_feel").value=="default") {
		alert("请选择故事感受");
		return false;
	}
	if (document.getElementById("mark").value==null) {
		alert("您的详细经过将更有助于维护您的权益，请填写事件的详细经过");
		return false;
	}
	
	document.getElementById("form").action="complaintOperate!complaintStep3";
	document.getElementById("form").submit();
	alert("车曝光已收到您的反馈 :-)");
	parent.location.reload();
}
</script>
<jsp:include page="/HTML/complain_addcomplain.html" ></jsp:include>
