<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>车曝光-投稿</title>
		<link rel="stylesheet" href="../CSS/new_layout.css" />
		<script src="../JS/external/calendar.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="../Ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="../Ueditor/ueditor.all.js"></script>
	    <script type="text/javascript" src="../JS/util.js" ></script>
	    <script src="../JS/setmeta.js" type="text/javascript" charset="utf-8"></script>
	    <script type="text/javascript">
        addLoadEvent(setmeta);//设置meta内容
	    </script>
<script type="text/javascript">
	function sendReport(){
		if (checkReport()) {
		document.getElementById("form").action="reportOperate!addReport";
		document.getElementById("form").submit();
		}else{
		return false;
		}
		
	}
	function checkReport(){
	getContent();
	//检查非空字段
	var a=["title","source","time","author","url","category","car_type","content"];
	var b=true;
	for (i=0;i<a.length;i++){
		var aobj=document.getElementById(a[i]);
		var aalert="请您填写完整的新闻信息--"+a[i]+"缺省";
		if(! validate_required(aobj,aalert)) {
		b=false;
		break;
		}	
	}
	return b;
	}
//将UEditor编辑框中的内容取出
	function getContent(){
		var content = UE.getEditor('editor').getContent();
		document.getElementById('content').value = content;	
	}
</script>
	</head>
	<body>
<jsp:include page="/JSP/module_header.jsp" flush="true" ></jsp:include>

<jsp:include page="/HTML/edit_news.html"></jsp:include>
		
<jsp:include page="/HTML/footer.html"></jsp:include>
	
	</body>
</html>
