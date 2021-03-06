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
            document.getElementById("chartY").innerHTML=XTitle+"年代";
    		chartType="year_brand_count";    		
    		break;
    	case "year_brand_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"品牌一览";
            document.getElementById("chartY").innerHTML=XTitle+"品牌";
    		chartType="brand_cartype_count";
    		break;
    	case "brand_cartype_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"车型一览";
            document.getElementById("chartY").innerHTML=XTitle+"车型";
    		chartType="showComplain";
    		break;
    	case "problem_year_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"年代一览";
            document.getElementById("chartY").innerHTML=XTitle+"年代";
    		chartType="year_problem_count" ;
    		break;
    	case "year_problem_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"缺陷一览";
            document.getElementById("chartY").innerHTML=XTitle+"缺陷";
    		chartType="problem_cartype_count";
    		break;
    	case "problem_cartype_count" :
    		document.getElementById("rankTitle").innerHTML=XTitle+"车型一览";
            document.getElementById("chartY").innerHTML=XTitle+"车型";
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

	for (var i = 0; i < bjson.length; i++) {
	if(bjson[i]!= null){
		var a=document.createElement("li");
		//柱状图文字信息
		a.innerHTML=""+bjson[i].name;
		a.title="关于"+bjson[i].name+"收到的吐槽数:"+bjson[i].nub;
		var b=bjson[i].nub/(bjson[0].nub/0.7);
		//柱状图高度
		a.style.backgroundPositionY=""+b.toPercent();
		//添加点击事件
		a.onclick=function(){
            var str;
            if(chartType==="showComplain"){
              var atext=this.innerText;
              if (atext.indexOf(".")>0)  {
                atext= atext.split(".")[1]
              }
              str="statisticOperate!toComplaint?param="+encodeURI(encodeURI(atext));  
            }else{
              str="statisticOperate!getRank?type="+chartType+"&param="+encodeURI(encodeURI(this.innerText));
            }
		
		window.location.href=str;
		}
		
		rankBar.appendChild(a);}
	}
}

//加载右边排行
function loadrightrank (mtarget,mJson) {
    if (mtarget==="hotProblem") {
    mJson=[
    {"name":"汽车悬架~一汽-大众~速腾","nub":500},
    {"name":"变速器~一汽-大众~速腾","nub":300},
    {"name":"引擎~上海-通用~雪佛兰","nub":160}
    ];       
    }else{
    mJson=[
    {"name":"一汽-大众~速腾~汽车悬架","nub":500},
    {"name":"上海-通用~雪佛兰~引擎","nub":420},
    {"name":"长安~福特~变速器","nub":300}
    ];        
    }


    	//将小数转换为百分数
    	Number.prototype.toPercent = function(n){n = n || 2;return ( Math.round( this * Math.pow( 10, n + 2 ) ) / Math.pow( 10, n ) ).toFixed( n ) + '%';}
    	var sum=0;
    	for (var i = 0; i < mJson.length; i++) {
    		var n=i+1;
    		var mtitle=""+mtarget+""+n;
    		var mbar=""+mtarget+"Bar"+n;
    	    var atitle= document.getElementById(mtitle);
            var ah4=document.createElement("h4");
            var ah6=document.createElement("h6");
            var acontent=mJson[i].name.split("~");
            var aparam;
            if (mtarget==="hotProblem"){            
                ah4.innerHTML=""+acontent[0];                
                ah6.innerHTML=acontent[1]+" "+acontent[2];
                aparam=acontent[0];
            }else{
                ah4.innerHTML=acontent[0]+" "+acontent[1];
                ah6.innerHTML=""+acontent[2];  
                aparam=acontent[0];             
            }
            atitle.appendChild(ah4);
            atitle.appendChild(ah6);
    	    atitle.href += encodeURI(encodeURI(aparam));
    	    var b=mJson[i].nub/(mJson[0].nub/0.7);
    	    document.getElementById(mbar).style.width=""+b.toPercent();
    		
    	}
    }
    function createTipBox(){
        clearTipBox();
        var tipbox = document.createElement("div");
        tipbox.setAttribute("id","tipBox");
        tipbox.innerHTML="<p>此处应为吐槽数据统计图表</p><p>车曝光正在努力地收集数据中....</p><p>请点击“我要吐槽”为车曝光添加一份力量！</p>"
        var bb=document.getElementById("view_rankChart");
        bb.appendChild(tipbox);
        showTipBox();

    }
    function showTipBox(){
        document.getElementById("tipBox").style.height="100 px";
        setTimeout("document.getElementById('tipBox').style.height='0';clearTimeout();",2000);

    }
    function clearTipBox(){
        if (document.getElementById("view_rankChart")) {
            if (document.getElementById("tipBox")) {
        var father=document.getElementById("view_rankChart");
        var son=document.getElementById("tipBox");
        father.removeChild(son);  
            };
        };

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