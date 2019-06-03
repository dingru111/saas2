/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.formValidateExt = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		validRepetition : function(validatorObj) {///扩展验证框架，可以验证有多个 name 重名的表单元素
			  if (validatorObj) {
				  validatorObj.prototype.elements = function () {
		               var validator = this,
		                 rulesCache = {};
		               return $(this.currentForm)
		               .find("input, select, textarea")
		               .not(":submit, :reset, :image, [disabled]")
		               .not(this.settings.ignore)
		               .filter(function () {
		                   if (!this.name && validator.settings.debug && window.console) {
		                       console.error("%o has no name assigned", this);
		                   }
		                   rulesCache[this.name] = true;
		                   return true;
		               });
		           }
		       } 
		},
		callPrivate:function( ){
		 
		}
		 
		 
	};
	
 
	
})(jQuery);//直接复制 一定要;号