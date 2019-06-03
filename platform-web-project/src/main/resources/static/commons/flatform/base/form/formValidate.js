/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.formValidate = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		initConfig : function(controlOptions) {//外部可以直接访问的对象的方法， 要导入该文件 调用方法$.devfrw.base.demo.pub
			var settings = {}; 
			var initsettings={theme:'ArrowSolidBox',mode:'AutoTip',inIframe:true};
			$.extend(true, settings,initsettings, controlOptions || {});
			return $.formValidator.initConfig( settings);
		} 

		
	};
	
	
})(jQuery);//直接复制 一定要;号

