(function($){
	 
	 $.devfrw.base={
			 
			/***************************************************************************
			 * 获取iframe里面的元素
			 * 
			 * @param {}
			 *            frameId
			 * @param {}
			 *            selId
			 * @return {}
			 */
			iframeItems : function(selId) {
				return $("#" + selId);
				/*var ifmId = $("div.l-dialog-content iframe").attr("id");
				if(ifmId){
					if (selId)
						return $(window.frames[ifmId].document).find("#" + selId);
					else
						return $(window.frames[ifmId].document);
				}else{
					return $("#" + selId);
				}*/
			},
	 		
			formSubmit:function(id){
	 			$("#"+id).submit();
	 		},
	 		load:function(url){
	 			console.log("加载页面");
	 			if(url){
	 				console.log( url);
	 				$("#rightDiv").empty();
	 				$("#rightDiv").load(url, {}, function(){
	 					console.log(url+'加载完成');
	 				});
	 			}
	 		}
	};
	
 
 
	
	
})(jQuery);