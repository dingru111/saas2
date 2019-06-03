/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.dialog = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		tip : function(layui,message) {//外部可以直接访问的对象的方法， 要导入该文件 调用方法$.devfrw.base.demo.pub
			//alert("对象下的方法外部直接可以访问");
			if(!tipFlag){
				return ;
			}
			tipFlag=false;
			layui.use(['layer','jquery'],function(){
				var layer = layui.layer;
				layer.msg(message, function(){
					tipFlag=true;
				});
			});
			
		},
		alert : function(layui,message) {
			var layer = layui.layer;
			layer.alert(message);
		},
		message : function(layer,lineHeight,area,message,callback){//['340px', '158px']
			if(area==null ){
				area=['340px', '158px'];
			}
			layer.open({
		        type: 1,
		        title: "提示",
		        resize: false,
		        shadeClose: false,
		        closeBtn: 0,
		        area: area,
		        content: '<div class="txtC" style="line-height:'+lineHeight+';">'+message+'</div>',
		        btn: ['确定'],
		        yes: function (index, layero) {
		            layer.close(index);
		            if (typeof callback === "function") {
                        callback();
                    }
		        }
		    });
		},
		confirm  : function(layer,message,yesCallback,noCallback){
			layer.confirm(message, {icon: 3},
		             function (index, layero) {
						 if (typeof yesCallback === "function") {
							 yesCallback(index,layero);
		                 }
		                 layer.close(index);
		             }, function(index){
		            	 if (typeof noCallback === "function") {
		            		 noCallback(index);
		                 }
		                 layer.close(index);
		             }
		     );
		}
	

	
	};
	var tipFlag=true;
	function privateMethod(){//私有方法只有本文件可以访问
		 alert("对象下的私有方法！");
	}
	
})(jQuery);//直接复制 一定要;号