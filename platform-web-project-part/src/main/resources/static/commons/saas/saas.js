/**
 *  分页
 * @param $
 * 使用
 * 1 在页面导入本js
 * 2 在页面中导入page标签
 * 3 初始化插件$.devfrw.page.init();
 *   如果有参数，使用{}对象格式传入参数
 *    $.devfrw.page.init({selectChangeSubmit:false});
 */
(function ($) {//每页开始直接复制
  
    $.devfrw.saas = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.page
       
    	log : function(){
    		alert("我是子项目文件");
    		log("我是子项目文件");
        } 
    };
     
    function log(e) {
        console.log(e);
    }
})(jQuery);//直接复制 一定要;号
 