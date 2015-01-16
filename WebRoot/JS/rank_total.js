//控制排行页面柱状图
new_element=document.createElement("script");
new_element.setAttribute("type","text/javascript");
new_element.setAttribute("src","../JS/external/json2.js");
document.getElementsByTagName("head").item(0).appendChild(new_element);



//设置下一次的排行类型
function setType(){	
	XTitle=decodeURI(decodeURI(XTitle));
    switch(chartType)
    {
    	case "brand_year_count" :
	        document.getElementById("rankTitle").innerHTML=XTitle+"年代一览";
    		chartType="year_brand_count";    		
    		break;
    	case "year_brand_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"品牌一览";
    		chartType="brand_cartype_count";
    		break;
    	case "brand_cartype_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"车型一览";
    		chartType="showComplain";
    		break;
    	case "problem_year_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"年代一览";
    		chartType="year_problem_count" ;
    		break;
    	case "year_problem_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"缺陷一览";
    		chartType="problem_cartype_count";
    		break;
    	case "problem_cartype_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"车型一览";
    		chartType="showComplain";
    		break;
    }
}
//写DOM，展示排行
function showChart (bjson) {
    //百分数转换的方法
	Number.prototype.toPercent = function(n){n = n || 2;return ( Math.round( this * Math.pow( 10, n + 2 ) ) / Math.pow( 10, n ) ).toFixed( n ) + '%';}
	//设置父容器ul的宽度
	var rankBar=document.getElementById("rankBar");
	rankBar.style.width=60*bjson.length+"px";
	//吐槽的总数
	var sum=0;
	for (var i = 0; i < bjson.length; i++) {
	if(bjson[i]!= null){
		sum+=bjson[i].nub;}		
	}
	for (var i = 0; i < bjson.length; i++) {
	if(bjson[i]!= null){
		var a=document.createElement("li");
		//柱状图文字
		a.innerHTML=""+bjson[i].name;
		a.Alt=""+bjson[i].nub;
		var b=bjson[i].nub/sum;//0.3是增高垫
		//柱状图高度
		a.style.backgroundPositionY=""+b.toPercent();
		//添加点击事件
		a.onclick=function(){
		var str="statisticOperate!getRank?type="+chartType+"&param="+encodeURI(encodeURI(this.innerText));
		window.location.href=str;
		}
		
		rankBar.appendChild(a);}
	}
}


function loadrightrank (mtarget,mJson) {
    	//将小数转换为百分数
    	Number.prototype.toPercent = function(n){n = n || 2;return ( Math.round( this * Math.pow( 10, n + 2 ) ) / Math.pow( 10, n ) ).toFixed( n ) + '%';}
    	var sum=0;
    	for (var i = 0; i < mJson.length; i++) {   	
    		sum+= mJson[i].nub;		
    	}
    	for (var i = 0; i < mJson.length; i++) {
    		var n=i+1;
    		var mtitle=""+mtarget+""+n;
    		var mbar=""+mtarget+"Bar"+n;
    	    var atitle= document.getElementById(mtitle);
    	    atitle.innerText=mJson[i].name;
    	    atitle.parentNode.href += encodeURI(encodeURI(mJson[i].name));
    	    var b=mJson[i].nub/sum;
    	    document.getElementById(mbar).style.width=""+b.toPercent();
    		
    	}
    }
    
    
	jakechartJson=[
	{"name":"汽车悬架","nub":701},
	{"name":"汽车外部配件","nub":180},
	{"name":"汽车内部配件","nub":100},
	{"name":"车体/油漆","nub":120},
	{"name":"刹车","nub":150},
	{"name":"离合器","nub":210},
	{"name":"冷却系统","nub":160},
	{"name":"动力传动系统","nub":150},
	{"name":"汽车电力系统","nub":140},
	{"name":"引擎","nub":140},
	{"name":"排气系统","nub":140},
	{"name":"能源系统","nub":130},
	{"name":"灯","nub":130},
	{"name":"安全带/安全气囊","nub":130},
	{"name":"空调制冷/加热","nub":130},
	{"name":"转向","nub":130},
	{"name":"变速器","nub":130},
	{"name":"车轮/轮毂","nub":130},
	{"name":"车窗/挡风玻璃","nub":130},
	];