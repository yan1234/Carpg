<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="JS/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="JS/ajax.js"></script>
    <script type="text/javascript" src="JS/Address/Address.js"></script>
    <script type="text/javascript" src="JS/Address/ChooseAddress.js"></script>

<SCRIPT LANGUAGE="JavaScript">
function doCheck() {
    var u = document.getElementById("username");
        if(u.value!="") {
            document.getElementById("feedback").innerHTML = "ϵͳ���ڴ��������������Ժ�";
            send_request("GET","index.jsp?username="+u.value,null,"text",showFeedbackInfo);
        }
        else {
            document.getElementById("feedback").innerHTML = "�������û����ơ�";
        }
}
function showFeedbackInfo() {
    if (http_request.readyState == 4) { // �ж϶���״̬
        if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
            document.getElementById("feedback").innerHTML = http_request.responseText;
        } else { //ҳ�治����
            alert("���������ҳ�����쳣��");
        }
    }
}
</SCRIPT>	
  </head>
  
  <body>
    �û�ע��<br>
    <span id="feedback">����ajax</span>
    <s:form action="userOperate">
    	<s:textfield name="name" id="name" label=" �û���" onblur="doCheck();" />
        <s:password name="password"  label=" ����" />
        <s:password name="password1"  label=" ȷ������" />  
        <s:select name="province" id="id_provSelect" list="{}" label="ʡ" onChange="loadCity(this.value);" />
        <s:select name="city" id="id_citySelect" list="{}"label="��" onChange="loadArea(this.value);" />
        <s:select name="section" id="id_areaSelect" list="{}" label="��"/>
        <script type="text/javascript">loadProvince('420115');</script>
        <s:textfield name="email" label=" ����" />
        <s:textfield name="tel" label=" �绰" />
        <s:hidden name="type" value="regist" />
        <s:submit value="ȷ��" />
    </s:form>
  </body>
</html>
