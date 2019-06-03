/**
 *  封装的基于jquery的 ajax方法
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.ajax  = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.ajax
		postJson : function(url,parm,fun) {//$.devfrw.ajax.postJson方法使用ajax同步响应返回json 使用post提交
			$.ajaxSetup({async: false });
			$.post(url,parm,fun,'json');
			$.ajaxSetup({async: true });
		},
		
		/**
		 *  请求成功后弹出Tips
		 * @param $
		 */
		postTips:function(url,param){
			$.devfrw.ajax.postJson(url,param,function(d){
				if(d.success){
					$.dialog.tips('操作成功');
				}else{
					$.dialog.tips('操作失败');
				}
			});
		} ,
		callPrivate:function( ){
			privateMethod();
		} 
		 
	};
	
	 
	
})(jQuery);//直接复制 一定要;号