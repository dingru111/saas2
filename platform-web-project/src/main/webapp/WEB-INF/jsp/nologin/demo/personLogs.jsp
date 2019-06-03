<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
    <title>互联网金融支付实训系统-登录页</title>
    <%@ include file="/WEB-INF/jsp/commons/jsp/baseResource.jsp" %>
    <link rel="stylesheet" href="${ctx}/commons/css/nologin.css">
     <script type="text/javascript" src="${ctx}/commons/saas/project.js"></script>
</head>
<body>
<form id="pageSeach" name="pageSeach" action="${ctx }/nologin/demo1/personLogs" method="post">
	 <input name="loginIp" type="text" value="" /> 
 
	<page:form/><!-- 分页表单自定义标签一定要加上 -->  
</form>
<div class="click-btn-par marlt-50">
	<div onclick="javascript:$('#pageSeach').submit();" style="width:100px;text-align:center;line-height:28px;color:#fff;background:#7b7de5;border-radius:3px;">
	表单提交
	</div>
	<div class="operaP">
		<div class="mt10">
			<table class="tanTable pageTable" style="width: 100%">
				<tr>
					<th width="50">序号</th>
					<th>登录时间</th>
					<th>登录IP</th>
				</tr>
				<c:forEach items="${pageInfo.results}" varStatus="i" var="st">
					<tr>
						<td>${i.index+1 + (pageInfo.pageNo-1)*(pageInfo.pageSize) }</td>
						<td>
						<fmt:formatDate value="${st.loginTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${st.loginIp}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="page" class="fr"></div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//初始化分页组件确保在进入页面时就加载
	/***
	** 1 layui参数直接带入 2 'page'分页div的 id 3 pageSeach 查询表单的id 4 pageTable 被替换列表的 css
	*/
	 
	
	$.devfrw.project.log();
	$.devfrw.page.submitBindForm(layui,'page','pageSeach','pageTable',function(){
		console.log("分页页码点击执行完回调！");
	});
	
	
	//
});
function sub(){
	$("#pageSeach").submit();
}
</script>