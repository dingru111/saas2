/**
 *   
 
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.dialog = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
			comfirm:function(initData){
				var data={className:'ask-pop',title:'提示',content:'确定执行此操作吗？',ok:function(){},cancel:function(){}};
				if(initData){
					data= $.extend(data, initData);
				};
				dialog.tipsPop(//new xDialog('操作成功').showxDialog();
						  data.className,//ask-pop、ok-pop、ban-pop、ero-pop
						  data.title,
						  data.content,
						  data.ok,
						  data.cancel
				);
			},
			alert:function(initData){
				var data={className:'ban-pop',title:'提示',content:'确定',ok:function(){},cancel:function(){}};
				if(initData){
					data= $.extend(data, initData);
				};
				dialog.tipsPop(//new xDialog('操作成功').showxDialog();
						  data.className,//ask-pop、ok-pop、ban-pop、ero-pop
						  data.title,
						  data.content,
						  data.ok
				);
			},
			window:function(initData){
				/* 组件使用ajax 提交表单
				 * data={title:'窗口',width:440,height:370,formId:null,url:'',pram:{},success:null,fail:null,cancel:null};
				 * title 标题
				 * width 窗口
				 * height 窗口
				 * formId 内嵌页面formid
				 * url 内嵌页面url
				 * pram 传参数 不支持中文
				 * success 提交成功后执行函数
				 * fail 提交失败后执行函数
				 * cancel 关闭窗口执行函数
				 */
				var data={title:'窗口',width:440,height:370,formId:null,url:'',pram:{},before:null,success:null,fail:null,cancel:null};
				if(initData){
					data= $.extend(data, initData);
				};
				var val=parseParam(data.pram);
				 
				var dia = dialog({
		            width: data.width,
		            height: data.height,
		            id: 'dialogframe__',
		            title: data.title,
		            url: data.url+'?'+val+'&data__='+new Date().getTime(),                  //另一个页面的url相对地址
		            ok: function(){
		            	var frameIndex=window.frames.length-1;
		                var flag=true;
		               /* if(data.before == undefined){
		                	data.before();
		                }*/
		                var $form = $(window.frames[frameIndex].document).find("#"+data.formId);
		                if($form.valid()){ //验证通过
		                	$form.ajaxSubmit({
				    		 	 async:false,
				    		 	 dataType:'json',
				    	         success: function(res) { // data 保存提交后返回的数据，一般为 json 数据
				    	             // 此处可对 data 作相关处理
					    	         if(res.success==1){
					    	        	 $.devfrw.dialog.tipOk();
					    	        	 if(data.success == undefined){
					    	        		 
					    	        	 }else{
					    	        		 data.success(res);  
					    	        	 }
					    	        	 flag=true;
					    	         }else{
					    	        	 $.devfrw.dialog.tipError();
					    	        	 if(data.fail == undefined){
					    	        		 
					    	        	 }else{
					    	        		 data.fail(res);   
					    	        	 }
					    	        	 flag= false;
					    	         }
				    	         }
				    	     }); 
		                }else{ //验证不通过
		                	flag = false;
		                }
		                
		             	return flag;
		            },
		            cancel: function(){
		            	if(data.cancel == undefined){
	    	        		 
	    	        	 }else{
	    	        		 data.cancel() ;
	    	        	 }
		            }
		          });
		          dia.showModal();
			},
			windowForm:function(initData){
				/* 组件使用ajax 提交表单
				 * data={title:'窗口',width:440,height:370,formId:null,url:'',pram:{},success:null,fail:null,cancel:null};
				 * title 标题
				 * width 窗口
				 * height 窗口
				 * formId 内嵌页面formid
				 * url 内嵌页面url
				 * pram 传参数 不支持中文
				 * success 提交成功后执行函数
				 * fail 提交失败后执行函数
				 * cancel 关闭窗口执行函数
				 */
				var data={title:'窗口',width:440,height:370,formId:null,url:'',pram:{},ok:null,success:null,fail:null,cancel:null};
				if(initData){
					data= $.extend(data, initData);
				};
				var val=parseParam(data.pram);
				 
				var dia = dialog({
		            width: data.width,
		            height: data.height,
		            id: 'dialogframe__',
		            title: data.title,
		            url: data.url+'?'+val+'&data__='+new Date().getTime(),                  //另一个页面的url相对地址
		            ok: function(){
		            	if(data.ok == undefined){
	    	        		 
	    	        	 }else{
	    	        		return  data.ok() ;
	    	        	 }
		            },
		            cancel: function(){
		            	if(data.cancel == undefined){
	    	        		 
	    	        	 }else{
	    	        		 data.cancel() ;
	    	        	 }
		            }
		          });
		          dia.showModal();
			},
			tip:function(message){
				if(message == undefined||message==""){
					message="操作成功";
				}
				new xDialog(message).showxDialog();
			},
			tipOk:function(message){
				if(message == undefined||message==""){
					message="操作成功";
				}
				new xDialog(message).showxDialog();
			},
			tipError:function(message){
				if(message == undefined||message==""){
					message="操作失败";
				}
				new xErrorDialog(message).showxDialog();
			} 
	};
	 /***
	 * 
	 *
	 *var obj={name:'tom','class':{className:'class1'},classMates:[{name:'lily'}]};
	 *parseParam(obj);
	 *结果："name=tom&class.className=class1&classMates[0].name=lily" 
	 *parseParam(obj,'stu');
	 *结果："stu.name=tom&stu.class.className=class1&stu.classMates[0].name=lily"
	 * 
	 * 
	 */ 
	function parseParam (param, key){
		 	    var paramStr="";
		 	    if(param instanceof String||param instanceof Number||param instanceof Boolean){ 
		 	        paramStr+="&"+key+"="+encodeURIComponent(param);
		     }else{
		 	        $.each(param,function(i){
		 	            var k=key==null?i:key+(param instanceof Array?"["+i+"]":"."+i);
		 	            paramStr+='&'+parseParam(this, k);
		         });
	 	    }
		 	    return paramStr.substr(1);
	};
	
})(jQuery);//直接复制 一定要;号
