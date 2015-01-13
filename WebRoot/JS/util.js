//主要是一些js工具类
//通用类

//用于添加多个window.onload事件
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

//验证邮箱的合法性,调用使用onblur,obj为当前输入框对象
 function check_email_format(obj){
    var mail = obj.value;
    //对电子邮件的验证
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!myreg.test(mail)){
      alert("请输入正确的邮箱!");
      return false;
    }
 }
 //控制输入框只能输入数字，调用使用onkeydown
 function numOnly(){
 	if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
     	if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
     		event.returnValue=false;
 }
 
 //控制输入框只能输入数字和字母,调用使用onkeyup，obj为当前对象
 function charOnly(obj){
 	obj.value = obj.value.replace(/[^\d|chun]/g,'');
 }
 //控制输入框只能输入浮点数,调用使用onkeyup，obj为当前对象
 function floatOnly(obj){   
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
//检查表单非空，若为空，弹出提示框
function validate_required(field,alerttxt)
{
with (field)
{
if (value==null||value=="")
  {alert(alerttxt);return false;}
else {return true;}
}
}
//判断并添加或删除元素的class
function hasClass( elements,cName ){    
    return !!elements.className.match( new RegExp( "(\\s|^)" + cName + "(\\s|$)") );   
}  
function addClass( elements,cName ){    
    if( !hasClass( elements,cName ) ){    
        elements.className += " " + cName;    
    }    
}   
function removeClass( elements,cName ){    
    if( hasClass( elements,cName ) ){    
        elements.className = elements.className.replace( new RegExp( "(\\s|^)" + cName + "(\\s|$)" ), " " );  
    }    
}
//字符转义
function transStr(){
  str=str.replace(/\//,"\\\/");
  str=str.replace(/\&/,"\\\&");
  str=str.replace(/\?/,"\\\?/");
}