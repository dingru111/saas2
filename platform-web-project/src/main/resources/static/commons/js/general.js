//点击头像下拉
$(".por-ha-up").click(function(){
	//箭头对象
	var $perobj=$(this).find(".per-dire");
	//显示隐藏部分
	var $low=$(this).siblings('.por-ha-low:eq(0)');
	//获取class
	var $cla=$perobj.attr("class");
	if($cla.indexOf("up")<0){
		$perobj.addClass('up');
		$(this).addClass("active");
		$low.show();
	}else{
		$perobj.removeClass('up');
		$(this).removeClass("active");
		$low.hide();
	}
});
//左侧点击
$(".ma-le-ul li").click(function(){
	$(".ma-le-ul li").each(function(index, el) {
		$(el).removeClass('active');
		$(el).find('.NavFF').hide();
	});
	$(this).addClass('active').find('.NavFF').show();
});
$(".NavSon").click(function(){
	$(".NavSon").removeClass('active');
	$(this).addClass('active').siblings();
})

//按钮点击样式更改
$(".click-btn").click(function(){
	$(this).parents(".click-btn-par:eq(0)").find(".click-btn").each(function(index, el) {
		$(el).removeClass('active')
	});
	$(this).addClass('active');
});

//初始化下拉框（宽度）
$(function(){
	var $setxt=$(".select-text");
	$setxt.each(function(index, el) {
		var par=$(el).parent(".select");
		$(el).width(par.width()-40);
	});
	
})
//下拉框点击
$(".select").click(function(){
	var $dire=$(this).find(".select-dire");
	var $data=$(this).find(".select-list-data");
	$(this).addClass('active');
	if($dire.attr("class").indexOf("active")<0){
		$dire.addClass('active');
		$data.show();
	}else{
		$dire.removeClass('active');
		$data.hide();
	}
	event.stopPropagation();//阻止冒泡

});

//点击下拉框的函数
function clickli(li){
	var $text=li.parents(".select-list-data").siblings(".select-text");
	var $dire=li.parents(".select-list-data").siblings('.select-dire');
	var $datapar=li.parents(".select-list-data");
	var $sele=$datapar.parents(".select");
	$sele.removeClass('active');
	$datapar.find("li").each(function(index, el) {
		$(el).removeClass('active');
	});
	$text.text(li.text());//文本框添加选中
	li.addClass('active');//选中样式
	$datapar.hide();//下拉框隐藏
	$dire.removeClass('active');//更改方向箭头
	event.stopPropagation();//阻止冒泡
}
//点击监听下拉关闭
$(document).click(function(e){
	if($(e.target).parents(".select").length<1){
		$(".select").removeClass('active');//去掉选中样式
		$(".select-list-data").hide();//隐藏下拉框
		$('.select-dire').removeClass("active");//箭头改变
	}
});
//点击表格选中框
$(".check-icon").click(function(){
	//获取check的父元素
	var $parta=$(this).parents(".check-par");
	//获取所有check元素(不包过全选)
	var $checkall=$parta.find(".check-icon").not('.check-all');
	//获取check元素的个数
	var $checklen=$checkall.length;
	//当前元素的class
	var $thisclass=$(this).attr("class");
	//是否是全选（如果是）
	if($thisclass.indexOf("check-all")>-1){
			//如果全选是选中
			if($thisclass.indexOf("active")>-1){
				//去掉所有选中状态
				$parta.find(".check-icon").removeClass("active");		
			}else{
				$parta.find(".check-icon").addClass("active");			
			}
		}
		else{
			//如果是选中
			if($thisclass.indexOf("active")>-1){
				$(this).removeClass('active');//去掉当前选中状态
				var $notlen=$parta.find(".check-icon:not('.active')").not('.check-all').length;
				//去掉后 如果没有没有一个是选中的
				if($notlen==$checklen){
					$parta.find(".check-icon").removeClass("active");	
				}else{
					$parta.find(".check-all").removeClass('active');
				}	
				
			}
			//如果是未选中
			else{
				$(this).addClass('active');//设置当前选中状态
				var $activelen=$parta.find(".check-icon.active").not('.check-all').length;
				//如果选中以后表格中为全部选中
				if($checklen==$activelen){
					$parta.find(".check-icon").addClass("active");
				}
			}
			

		}

});

//分页
$(".page-ul li").click(function(){
	$(".page-ul li").each(function(index, el) {
		$(el).removeClass('active');
	});
	$(this).addClass('active');
});


//初始化弹出框
$(".pop").each(function(index, el) {
	$(el).css({
			"margin-left":-$(this).width()/2,
			"margin-top":-$(this).height()/2
		})
});

//关闭弹出框
$(".pop-btn,.close,.cancel").on("click",function(){
		popHide($(this),$(".mask"));
});
//隐藏弹出框
function popHide(obj,mask){
	obj.parents(".pop").hide();
	mask.hide();
}
//显示弹出框
function popShow(pop,mask){
	pop.show();
	mask.show();
	
}
