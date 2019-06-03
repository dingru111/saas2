/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
 
		  
	$.devfrw.util = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.util.date
		formatDate:function(nS){
				return new Date(parseInt(nS)).pattern("yyyy-MM-dd hh:mm:ss EEE")  ;
		}, 
	    encodeHtml : function(text){  
	    	  return text.replace(/[<>"&]/g, function(match, pos, originalText){
	    		    switch(match){
	    		    case "<": return "&lt;"; 
	    		    case ">":return "&gt;";
	    		    case "&":return "&amp;"; 
	    		    case "\"":return "&quot;"; 
	    		  } 
	    		  });
	    } 
	};
	
	function privateMethod(){//私有方法只有本文件可以访问
		 alert("私有方法");
	}
	
})(jQuery);//直接复制 一定要;号