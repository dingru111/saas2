<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
    <title>SAAS-登录页</title>
    <%@ include file="/WEB-INF/jsp/commons/jsp/baseResource.jsp" %>
    <link rel="stylesheet" href="${ctx}/commons/css/nologin.css">
    <script type="text/javascript" src="${ctx}/commons/saas/saas.js"></script>
</head>
<body id="loginBody">
<div>
    <div class="clearfix" style="min-width:1200px;">
        <div class="loginLogo"><img src="${ctx}/commons/images/loginLogo.png"/></div>
        <div class="loginMain mt70">
            <img src="${ctx}/commons/images/loginTxt.png"/>
            <div class="clearfix mt50">
                <div class="color1 fl logB">用户名</div>
                <input class="loginInput fl pl10" type="text"/>
            </div>
            <div class="clearfix mt20">
                <div class="color1 fl logB">密　码</div>
                <input class="loginInput fl pl10" type="password"/>
            </div>
            <div class="txtC mt30">
                <input type="button" value="登录" class="loginBtn">
            </div>
        </div>
    </div>
</div>
</body>
</html>
