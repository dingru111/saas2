<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
    <title>国泰安金融SaaS平台后台管理系统</title>
    <%@ include file="/WEB-INF/jsp/commons/jsp/baseResource.jsp" %>
    <style type="text/css">
        table {
            font-size: 14px;
        }
    </style>
</head>
<body>
<!--头部开始-->
<div class="head-wrap the-bg">
    <div class="wid-fixed">
        <div class="fl marl-20">
            <div class="logo fl">
                <img src="${ctx}/commons/images/logo.png" alt="">
            </div>
        </div>
        <div class="fl nav flex2">
            <a class="NavLi active" href="javascript:void(0);"
               url="${ctx}/nologin/platform/mainHome/mainHomeData">首页</a>
            <a class="NavLi" url="${ctx}/nologin/platform/roleManage/roleManageIndex">角色管理</a>
            <a class="NavLi" href="">账号管理</a>
            <a class="NavLi" href="javascript:void(0);"
               url="${ctx}/nologin/platform/userCenter/userCenterIndex">用户中心</a>
            <a class="NavLi" href="">数据分析</a>
            <a class="NavLi active" href="javascript:void(0);"
               url="${ctx}/nologin/platform/baseData/school/baseDataIndex">基础数据</a>
        </div>
        <div class="fr head-right" style="margin-top:12px;width:200px;">
            <!--<img class="AllMain fr" src="images/exit.png"/>-->
            <div class="fr clearfix">
                <img class="fl" src="${ctx}/commons/images/headpor.png"/>
                <div class="UserName fl">
                    乔治娜
                    <div class="TabUser DisNone">
                        <img class="mt10" src="${ctx}/commons/images/headpor.png"/>
                        <div>乔治娜</div>
                        <div class="active">退出</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="mian" id="main_div">

</div>
</body>
<script type="application/javascript">
    $(".NavLi").on("click", function () {
        $(this).addClass("active").siblings().removeClass("active");
        var url = $(this).attr('url');
        $("#main_div").empty();
        $("#main_div").load(url, "", function () {
            console.log(url + '加载完成');
        });
    });
    $(".UserName").on("click", function () {
        $(this).find(".TabUser").toggle();
    });
    $(function () {
        $(".NavLi").eq(0).click();
    });
</script>
</html>