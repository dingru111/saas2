  /**
 * 定义jQuery扩展属性 ccdc
 * 每个项目(app)的js,再次基础上进行扩展
 * 如: $.devfrw.app.module1={}
 * 
 * */
if(!$.devfrw || typeof $.devfrw !="object"){ //避免以后引入或者嵌入其他项目时导致重复定义 kj 这个全局对象
	$.devfrw = {};
}

//my97 datapicker jQuery插件
;(function($){ 
	  $.devfrw.sys = {};
	  var ua = navigator.userAgent.toLowerCase();
	  var s;
	  (s = ua.match(/msie ([\d.]+)/)) ? $.devfrw.sys.ie = s[1] :
	  (s = ua.match(/firefox\/([\d.]+)/)) ? $.devfrw.sys.firefox = s[1] :
      (s = ua.match(/chrome\/([\d.]+)/)) ? $.devfrw.sys.chrome = s[1] :
      (s = ua.match(/opera.([\d.]+)/)) ? $.devfrw.sys.opera = s[1] :
      (s = ua.match(/version\/([\d.]+).*safari/)) ? $.devfrw.sys.safari = s[1] : 0;
    // if ( $.devfrw.sys.ie) console.info('IE: ' + $.devfrw.sys.ie);
     // if ( $.devfrw.sys.firefox) console.info('Firefox: ' + $.devfrw.sys.firefox);
     // if ( $.devfrw.sys.chrome) console.info('Chrome: ' + $.devfrw.sys.chrome);
     // if ( $.devfrw.sys.opera) console.info('Opera: ' + $.devfrw.sys.opera);
    //  if ( $.devfrw.sys.safari) console.info('Safari: ' + $.devfrw.sys.safari);
     
})(jQuery);
/**
*按钮在按下后1秒内禁止继续按下
* */
function disableButtonMoreTime(){
	 $("input[type=button]").on("click",function(){
		var $this = $(this);
		$this.attr({"disabled":"disabled"});
		window.setTimeout(function(){
				 $this.removeAttr("disabled");
			 },1000);
	 });
}
$(function(){
	//ajax开始和结束时的遮罩效果
	// $(document).ajaxStart($.kjUIMask.start).ajaxStop(ajaxStop);
	 // disableButtonMoreTime();
	 
	//定义 个性化的页面初始化方法,解决js文件过多,导致页面初始化时,文件未加载完成就开始调用业务方法的bug
	//使用方式 , 在 独立页面,定义自己的  function initThisPage(){//...any code }
	if(typeof(initThisPage)=="function"){
		try{
			initThisPage();
		}catch(e){
			// alert("页面初始化方法出错"+e.message);
		}
		
	}
	 gc();
});




function gc(){
	try{
		CollectGarbage();//ie特有的释放内存函数
	}catch(e){}
}

/** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
*  可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * 
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423      
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
*/        

Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, //月份         
		"d+" : this.getDate(), //日         
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时         
		"H+" : this.getHours(), //小时         
		"m+" : this.getMinutes(), //分         
		"s+" : this.getSeconds(), //秒         
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度         
		"S" : this.getMilliseconds()
	//毫秒         
	};
	var week = {
		"0" : "日",
		"1" : "一",
		"2" : "二",
		"3" : "三",
		"4" : "四",
		"5" : "五",
		"6" : "六"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期"
								: "日")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}       



 
 

 
 

 
 
 
 



 