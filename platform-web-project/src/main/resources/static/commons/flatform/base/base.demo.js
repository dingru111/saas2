/**
 *  
 * @param $
 */
(function($) {//每页开始直接复制
	$.devfrw.alert = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.base.demo
		pub : function() {//外部可以直接访问的对象的方法， 要导入该文件 调用方法$.devfrw.base.demo.pub
			alert("对象下的方法外部直接可以访问");
		},
		callPrivate:function( ){
			alert("对象下的callPrivate方法！");
			privateMethod();
		},
		json:function( ){
			$.ajaxSetup({   
		         async : false  
		    }); //?classId=af9c4e2930fe43e2b987a2ef7336a057&examId=bd72ba8c19f84fc5a1ff7a1ecf81a3a7
			$.getJSON("/exam-system/examResult/classExamResult",
					{classId:'af9c4e2930fe43e2b987a2ef7336a057',examId:'bd72ba8c19f84fc5a1ff7a1ecf81a3a7'}, function(json){
				console.log(json);
				alert(json.data.gradeCase.avg);
			});
			$.ajaxSetup({   
		         async : true  
		    });
		}
		 
		 
		 
	};
	
	function privateMethod(){//私有方法只有本文件可以访问
		 alert("对象下的私有方法！");
	}
	
})(jQuery);//直接复制 一定要;号