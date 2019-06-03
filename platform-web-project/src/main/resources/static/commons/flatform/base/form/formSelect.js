/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.formSelect = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		selectCheckBoxOnly : function(checkBoxName) {//外部可以直接访问的对象的方法， 要导入该文件 调用方法$.devfrw.base.demo.pub
			$(':checkbox[name='+checkBoxName+']').each(function(){
				$(this).click(function(){
					if($(this).attr('checked')){
						$(':checkbox[name='+checkBoxName+']').removeAttr('checked');
						$(this).attr('checked','checked');
					}
				});
			}); 
		},
		callPrivate:function( ){
			privateMethod();
		}
		 
		 
	};
	
	function privateMethod(){//私有方法只有本文件可以访问
		 alert("私有方法");
	}
	
})(jQuery);//直接复制 一定要;号