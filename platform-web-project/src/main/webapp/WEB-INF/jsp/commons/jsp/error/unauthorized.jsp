<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function relogin(){
		parent.location = "${ctx}/";
	}
</script>
</head>
<body>
<div style="text-align: center;margin-top: 200px;font-size: 12px;">
您尚未获得授权 
<!-- 
<a href="javascript:void(0);" onclick="relogin();"> </a>

 -->
<a href="${ctx}/${url }">请重新登录</a>

</div>
</body>
</html>