<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<div class="wid-fixed">
    <div class="main-left fl">
        <ul class="ma-le-ul">
            <li class="the-bor-co li-class" url="${ctx}/nologin/platform/baseData/school/schoolIndex" >
                <span class="ma-le-li-AccSch active " ></span><span>学校/公司名称管理</span>
            </li>
            <li class="the-bor-co li-class "  url="${ctx}/nologin/platform/baseData/profession/professionData?type=0" >
                <span class="ma-le-li-AccOcc "></span><span>专业管理</span>
            </li>
            <li class="the-bor-co li-class" url="${ctx}/nologin/platform/baseData/profession/professionData?type=1" >
                <span class="ma-le-li-AccTrade "></span><span>行业管理</span>
            </li>
        </ul>

    </div>

    <!--右侧内容开始-->
    <div class="main-right fl" >
        <div id="right_div" class="click-btn-par marlt-50" ></div>
        <div class="copyBottom">Copyright @ 2017 国泰安教育信息技术股份有限公司</div>
    </div>
    <!--右侧内容结束-->
    <div class="clear"></div>
</div>
<script type="application/javascript">
    $(".li-class").on("click",function () {
        $("#right_div").empty();
        $(".li-class").removeClass("active");
        $(this).addClass("active");
        $("#right_div").load($(this).attr("url"));
    });
    $(function () {
        $(".li-class").eq(0).click();
    });

</script>