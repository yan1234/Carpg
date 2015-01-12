//控制排行页面柱状图
new_element=document.createElement("script");
new_element.setAttribute("type","text/javascript");
new_element.setAttribute("src","../JS/external/json2.js");
document.getElementsByTagName("head").item(0).appendChild(new_element);

function jakeChart () {
	var bjson=[
	{"name":"一汽大众","nub":501},
	{"name":"上海通用雪佛兰","nub":290},
	{"name":"上海大众","nub":280},
	{"name":"长安福特","nub":260},
	{"name":"上海通用福特","nub":230},
	{"name":"东风日产","nub":210},
	{"name":"东风悦达起亚","nub":160},
	{"name":"长城","nub":150},
	{"name":"东风雪铁龙","nub":140},
	{"name":"东风标致","nub":140},
	{"name":"北京现代","nub":140},
	{"name":"奇瑞汽车","nub":130},
	];
	showChartB(bjson);
}
function jakeChartProblem () {
	var bjson=[
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
	showChartB(bjson);
}
function showChartB (bjson) {
	Number.prototype.toPercent = function(n){n = n || 2;return ( Math.round( this * Math.pow( 10, n + 2 ) ) / Math.pow( 10, n ) ).toFixed( n ) + '%';}
	var rankBar=document.getElementById("rankBar");
	rankBar.style.width=60*bjson.length+"px";
	var sum=0;
	for (var i = 0; i < bjson.length; i++) {
	if(bjson[i]!= null){
		sum+=bjson[i].nub;}		
	}
	for (var i = 0; i < bjson.length; i++) {
	if(bjson[i]!= null){
		var a=document.createElement("li");
		a.innerHTML=""+bjson[i].name;
		a.Alt=""+bjson[i].nub;
		var b=bjson[i].nub/sum+0.3;
		a.style.backgroundPositionY=""+b.toPercent();
		rankBar.appendChild(a);}
	}
}
	function setCar(value){
	document.getElementById("rankTitle").innerHTML=value+"问题榜";
    }