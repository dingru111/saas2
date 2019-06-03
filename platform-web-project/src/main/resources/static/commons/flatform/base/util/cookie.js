/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.cookie = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		pub : function() {//外部可以直接访问的对象的方法， 要导入该文件 调用方法$.devfrw.base.demo.pub
			alert("对象下的方法外部直接可以访问");
		},
		callPrivate:function( ){
			alert("对象下的callPrivate方法！");
			privateMethod();
		}
		 
		 
	};
	
	function privateMethod(){//私有方法只有本文件可以访问
		 alert("对象下的私有方法！");
	}
	
})(jQuery);//直接复制 一定要;号