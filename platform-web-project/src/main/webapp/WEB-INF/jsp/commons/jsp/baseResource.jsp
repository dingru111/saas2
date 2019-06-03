<link rel="stylesheet" href="${ctx}/commons/css/general.css">
<link rel="stylesheet" href="${ctx}/commons/css/style.css">
<link rel="stylesheet" href="${ctx}/commons/css/common.css">
<link rel="stylesheet" href="${ctx}/commons/css/select2.min.css">
<link rel="stylesheet" href="${ctx}/commons/js/layui/css/layui.css">
<script type="text/javascript" src="${ctx}/commons/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/general.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/flatform/base/global.js"></script>
<script type="text/javascript" src="${ctx}/commons/flatform/base/base.js"></script>
<script type="text/javascript" src="${ctx}/commons/flatform/base/dialog/alert.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/commons/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/flatform/base/page/page.js"></script>
 
<script type="text/javascript" src="${resource }/commons/js/jquery/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${resource }/commons/js/jquery/dist/additional-methods.js"></script>
<script type="text/javascript" src="${resource }/commons/js/jquery/dist/localization/messages_zh.js"></script>
 
<!-- 开发国际化的引用，主要完成后台信息在前台提示的国际化，一定要放在最后 -->
<script type="text/javascript">var i18n = new Object();</script>
<script type="text/javascript" src="${resource}/commons/js/i18n/message_zh_CN.js"></script>
 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//浏览器类型
	String ng_agent = request.getHeader("user-agent")!=null?request.getHeader("user-agent"):"";
	String ng_browser = "";
	if(ng_agent.indexOf("Firefox")>=0){
		ng_browser = "firefox";
	}else if(ng_agent.indexOf("MSIE")>=0){
		ng_browser = "msie";//或者是360兼容模式
	}else if(ng_agent.indexOf("Chrome")>=0 && ng_agent.indexOf("Safari")>=0 && ng_agent.indexOf("OPR")>=0){
		ng_browser = "opera";
	}else if(ng_agent.indexOf("Chrome")>=0 && ng_agent.indexOf("Safari")>=0){
		ng_browser = "chrome";//或者是360急速模式
	}else if(ng_agent.indexOf("Safari")>=0){
		ng_browser = "safari";
	}
%>
<script type="text/javascript">
	 var path = "<%=path%>";
	 var basePath = "<%=basePath%>";
	 var ng_browser = "<%=ng_browser%>";
	 $.devfrw.base.path=path;
	 $.devfrw.base.basePath=basePath;
 
</script>