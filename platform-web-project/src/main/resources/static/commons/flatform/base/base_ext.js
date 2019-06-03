;(function($,win){
	//全局窗体的位置,这里
	var platformWindow = win.top;
	
	/**
	 *  全局方法调用封装
	 *  这里主要封装了一些App需要调用的接口
	 *  @param name 方法名称
	 *  @param method 方法体
	 * */
	function devfrwMethod(name,method){
		this.name=name;
		this.func = method;
	}
	
	
	// 方法容器
	$.devfrw.base._methods=[];
	
	//示例方法  --------------------------------- ,要写注释
	/**
	 * 	@see 示例方法,演示如何添加全局方法
	 * 	@param 
	 * 	@return 无返回值
	 * */ 
	$.devfrw.base._methods.push(new devfrwMethod("demo",function(){
		platformWindow.alert("我是示例接口");
	}));
	//示例方法结束 --------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	$.each($.devfrw.base._methods,function(i,v){
		$.devfrw.base[v.name] = v.func;
	});
	
	
	
	// 要扩展(覆盖)此接口,请看下面示例
	$.extend($.devfrw.base,{
		demo:function(){
			platformWindow.alert("我被扩展了");
		}
		
	});
	
	
})(jQuery,window);