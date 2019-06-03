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
    var pageData = {
        pageNo: 1,//起始页
        totalPage: 1,//每页记录数
        fromId: 'pageSeach',//分页查询表单id
        selectChangeSubmit: true,//是否打开select 自动提交 默认是
        hasSeachButton: false//是否打开select 自动提交 默认是
    };
    $.devfrw.page = {//外部可以直接访问的对象， 要导入该文件 调用对象$.devfrw.page
       /* init: function (layui, pageId, formId, pageTableCss, curPage,callback) {
        	//log(pageData);
        	 alert("init方法弃用请使用submitBindForm方法");
        	pageData.fromId=formId;
         
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                //执行一个laypage实例
                var pageSize = $("#___pageSize").val();
                var totalRecord = $("#___totalRecord").val();
                laypage.render({
                    elem: pageId,//'page'
                    count: totalRecord,//数据总数，从服务端得到 ${pageInfo.totalRecord }
                    limit: pageSize,//每页数据数 ${pageInfo.pageSize}
                    curr:curPage==null?1:curPage,//当前页
                    jump: function (obj, first) {
                        //首次不执行 pageSeach
                    	
                        if (!first) {
                        	$("#___startPage").val(obj.curr);
                            var url = $('#' + formId).attr("action");
                            var param = $('#' + formId).formSerialize();
                             // var param = pageData.formSerialize;
                            param=getSeachParam(formId,param);
                            url = url + "?" + param;
                            // log(url);

                            $.ajaxSetup({
                                async: false
                            });
                            $.post(url, {}, function (data) {
                                data = $.parseHTML(data);//pageTableCss pageTable
                                // log(data);
                                var pageHtml = $(data).find("." + pageTableCss).html();
                                $("." + pageTableCss).html(pageHtml);
                                if (typeof callback === "function") {
                                    callback();
                                }
                            });
                            $.ajaxSetup({
                                async: true
                            });
                        }
                    }
                });
            });
        },
        firstPage : function (formId){//和$.devfrw.page.init 混合使用。点击 “查询条件按钮”时候调用。
        	$("#"+formId+" .___startPage").val("");//默认第一页
        	var pageSeachData=getFormValues(formId);
        	$("#"+formId+" .___pageSeachData").val(pageSeachData);//缓存查询的数据
        },*/
        initPageSizeAndTotalRecord : function(pageSize,totalRecord){
        	$("#___pageSize").val(pageSize);
        	$("#___totalRecord").val(totalRecord);
        },
        submitBindForm: function (layui, pageId, formId, pageTableCss, callback) {
        	layui.use('laypage', function () {
                var laypage = layui.laypage;
                //页面初始化，进人列表执行 。 构建右下角的分页控件“ Laypage” 。
                getLaypage(laypage, pageId, formId, pageTableCss, callback);
             
                //绑定form提交事件（用户点击“查询”按钮时触发）
                $('#'+formId).submit(function() {
                	setPageVal(formId,"___startPage",1);//用户查询 默认设置第一页
                	var pageSeachData=getFormValues(formId);//得到查询的数据
                	$("#"+formId+" .___pageSeachData").val(pageSeachData);//缓存查询的数据
                	//提交请求查询结果页面
               	 	$('#'+formId).ajaxSubmit({
	         		 	async:false,
	         	        success: function(data){
	         		        data = $.parseHTML('<div>'+data+'</div>');//处理防止页面没有 div顶层元素导致选择器无效
	         		        //得到查询后得到的  pageSize“页面大小 ” totalRecord“总页数”
	         		        setPageVal(formId,"___pageSize",$(data).find("#"+formId+" .___pageSize").val());
	         		        setPageVal(formId,"___totalRecord",$(data).find("#"+formId+" .___totalRecord").val());
	         		        // 渲染填充列表中的页面
	         	            $("." + pageTableCss).html($(data).find("." + pageTableCss).html());
	         	            // 构建右下角的分页控件“ Laypage” 
	         	            getLaypage(laypage, pageId, formId, pageTableCss, callback);
	         	        }
               	 	}); 
       		   
               	 return false;
       		 });
            });
        },
        getFormValues: function (formId) {//
        	return getFormValues(formId);
        }
    };
    //构建右下角的分页控件“ Laypage” 
    function getLaypage(laypage, pageId, formId, pageTableCss, callback) {
    	var pageSize=getPageVal(formId,"___pageSize");//每页数据数
    	var totalRecord=getPageVal(formId,"___totalRecord");//总页数
    	 laypage.render({
        	 elem: pageId,//'page'
             count: totalRecord,//数据总数，从服务端得到 
             limit: pageSize,//每页数据数  
             jump: function (obj, first) {//点击页码  click触发此事件
            	 if (!first) {
            		 setPageVal(formId,"___startPage",obj.curr);//设置被选中的页号
            		 getPage(formId, pageTableCss, callback);//得到跳转的页面html并渲染
            	 }
             }
        });
    }
    function getPage(formId, pageTableCss, callback) {
    	/**
    	 * 得到查询的url和参数 
    	 * 查询的参数是用户在点击“查询按钮”时缓存在 ___pageSeachData 元素中的数据。
    	 * 这样可以解决用户在输入“查询条件”没有点击“查询按钮”，直接点击“页码”产生的查询参数丢失的问题
    	 * getSeachParam 方法 得到之前在点击“查询按钮”时缓存的查询条件
    	 * **/
    	 
        var url =  $('#' + formId).attr("action") + "?" + getSeachParam(formId, $('#' + formId).formSerialize());
        $.ajaxSetup({
            async: false
        });
        $.post(url, {}, function (data) {
            data = $.parseHTML(data);//pageTableCss pageTable
            var pageHtml = $(data).find("." + pageTableCss).html();
            $("." + pageTableCss).html(pageHtml);
            if (typeof callback === "function") {
                callback();
            }
        });
        $.ajaxSetup({
            async: true
        });
    }
    function  formValuesChange(formId){
    	if(getFormValues(formId)==$('#___pageSeachData').val()){
    		log("不变化");
    		return false;
    	}else{
    		log("变化");
    		return true;
    	}
    }
    function getFormValues(formId){
    	var pageSeachData="";
    	var t = $('#' + formId).serializeArray();
        $.each(t, function() {
          if(this.name!='startPage'&&
        		  this.name!='pageSize'&&
        		  this.name!='totalPage'&&
        		  this.name!='totalRecord'&&
        		  this.name!='pageSeachData' 
          	){
        	  pageSeachData+=this.name+"="+this.value+"&";
          }
        });
    	log(pageSeachData);
    	return pageSeachData;
    }
   
    function setPageVal(formId,css,val){
    	 $("#"+formId).find("."+css).val(val);
    }
    function getPageVal(formId,css){
    	return $("#"+formId).find("."+css).val();
    }
 
    function getSeachParam(formId,param){
    	//缓存的查询数据
    	var beforeData=$("#"+formId+" .___pageSeachData").val();
    	if(beforeData==""){//没有 缓存的查询数据
    		log("没有参数："+param);
    		 return param;
    	}else{//有 缓存的查询数据
	    	var t = $('#' + formId).serializeArray();
	    	
	        $.each(t, function() {//t中包含表单中的参数列表
	        	//替换除分页外的缓存数据
	          if(this.name!='startPage'&&
	        		  this.name!='pageSize'&&
	        		  this.name!='totalPage'&&
	        		  this.name!='totalRecord'&&
	        		  this.name!='pageSeachData' 
	          	){
	        	  var beforeValue= getQueryString(beforeData,this.name);
	        	  param=replaceParamVal(param,this.name,beforeValue);
	          }
	        });
	        log("替换参数："+param);
	        return param;
    	}
    }
    /*
    *获取URL参数
    */
    function getQueryString(nUrl,name)
    {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = nUrl.match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return "";
    };
  //替换指定传入参数的值,paramName为参数,replaceWith为新值
    function replaceParamVal(oUrl,paramName,replaceWith) {
        var re=eval('/('+ paramName+'=)([^&]*)/gi');
        var nUrl = oUrl.replace(re,paramName+'='+replaceWith);
        return nUrl;
    }
    function log(e) {
        console.log(e);
    }
})(jQuery);//直接复制 一定要;号

/**
 * 初始化每页显示数目以及总数目
 * @param pageSize 每页显示数目
 * @param totalRecord 总数目
 */
function initPageSizeAndTotalRecord(pageSize,totalRecord){
   // $("#___pageSize").val(pageSize);
   // $("#___totalRecord").val(totalRecord);
	$.devfrw.page.initPageSizeAndTotalRecord(pageSize,totalRecord);
}