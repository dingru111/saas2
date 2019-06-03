<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<div class="pd20">
    <div class="bgFFF" style="">
        <div class="clearfix pdlr20 bt">
            <div class="fl LeftTitle">后台管理系统目录</div>
            <div class="fr RightTxt clearfix">
                <form id="pageSeach" name="pageSeach"
                      action="${ctx}/nologin/platform/roleManage/roleManageData" method="post">
                    <input type="search" class="search fl" placeholder="请输入系统名称" name="sysName" id="sysName"/>
                    <!-- 分页表单自定义标签一定要加上 -->
                    <page:form/>
                </form>
                <div class="searchBtn fl"><img onclick="getData();" src="${ctx}/commons/images/icon3.png"/></div>
            </div>
        </div>
        <div class="pd20">
            <div class="clearfix">
                <div class="clearfix fl ">
                    <div class="fl addBtn1">新增</div>
                    <div class="fl color1" style="line-height:30px;">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</div>
                    <div class="fl reduceBtn1">删除</div>
                </div>
                <div class="fr clearfix">
                    <div class="leftB fl">系统选择:</div>
                    <select class="select1">
                        <option>全部</option>
                        <option>全部</option>
                    </select>
                </div>
            </div>
            <div class="operaP" id="shcool_data_div"></div>
        </div>
    </div>
    <div class="copyBottom">Copyright @ 2017 国泰安教育信息技术股份有限公司</div>
    <div class="clear"></div>
</div>
<script>
$(document).ready(function () {
    $("select").select2({minimumResultsForSearch: Infinity});
    getData();

});
//获取列表数据
function getData() {
    $.ajax({
        type: "post",
        data: $("#pageSeach").serialize(),
        url: $("#pageSeach").attr("action"),
        dataType: "html",
        success: function (data) {
            $("#shcool_data_div").html(data);
        },
        error: function (e) {
            alert(JSON.stringify(e))
        }
    });
}
</script>
