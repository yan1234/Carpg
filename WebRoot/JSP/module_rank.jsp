<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//传排行需要的值，前三的信息，吐槽数
%>
<script type="text/javascript">
    var hotProblem=[
    {"brand":"一汽-大众","car_type":"速腾","problem":"后悬纵臂变形、断裂","complainNub":500},
    {"brand":"一汽-大众","car_type":"速腾","problem":"DSG 变速箱问题","complainNub":300},
    {"brand":"雪佛兰科鲁兹","car_type":"速腾","problem":"发动机漏油","complainNub":160},
    ];
    var hotCar=[
    {"brand":"一汽-大众","car_type":"速腾","problem":"后悬纵臂、DSG","complainNub":500},
    {"brand":"上海-通用","car_type":"雪佛兰","problem":"发动机","complainNub":420},
    {"brand":"长安","car_type":"福特","problem":"变速箱","complainNub":300},
    ];	
</script>
<jsp:include page="/HTML/right_rank.html"></jsp:include>
<script type="text/javascript">
    function loadrightrank (mtarget,mJson) {
    	//将小数转换为百分数
    	Number.prototype.toPercent = function(n){n = n || 2;return ( Math.round( this * Math.pow( 10, n + 2 ) ) / Math.pow( 10, n ) ).toFixed( n ) + '%';}
    	var sum=0;
    	for (var i = 0; i < mJson.length; i++) {
    	if(mJson[i]!= null){
    		sum+= mJson[i].complainNub;
    		}
    	}
    	for (var i = 0; i < mJson.length; i++) {
    	if(mJson[i]!= null){
    		var n=i+1;
    		var mtitle=""+mtarget+"P"+n;
    		var mcar=""+mtarget+"C"+n;
    		var mbar=""+mtarget+"Bar"+n;
    	    document.getElementById(mtitle).innerText=mJson[i].problem;
    	    document.getElementById(mcar).innerText=mJson[i].brand+" "+mJson[i].car_type;
    	    var b=mJson[i].complainNub/sum+0.3;
    	    document.getElementById(mbar).style.width=""+b.toPercent();
    	}	
    	}
    }
	loadrightrank("hotProblem",hotProblem);
	loadrightrank("hotCar",hotCar);
</script> 