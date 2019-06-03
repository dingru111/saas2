<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<table class="MainTable mt10">
    <tr class="bg-gray">
        <th width="50"><div class="ckBox"></div></th>
        <th width="50">序号11</th>
        <th>国家</th>
        <th>省份</th>
        <th>城市</th>
        <th>学习/公司名称</th>
        <th>姓名</th>
        <th>账号角色</th>
        <th>账号来源</th>
        <th>创建时间</th>
        <th>登陆次数</th>
        <th>客户类型</th>
        <th>所属系统</th>
        <th>操作结果</th>
    </tr>
    <c:choose>
        <c:when test="${pageInfo.results !=null && fn:length(pageInfo.results)>0 }">
            <c:forEach items="${pageInfo.results}" varStatus="i" var="uc">
                <tr>
                    <td><div class="ckBox active"></div></td>
                    <td>${i.index+1+ (pageInfo.pageNo-1)*(pageInfo.pageSize)}</td>
                    <td>${uc.countryName}</td>
                    <td>${uc.provinceName}</td>
                    <td>${uc.cityName}</td>
                    <td>${uc.schComName}</td>
                    <td>${uc.name}</td>
                    <td>${uc.accountRole}</td>
                    <td>${uc.accountSource}</td>
                    <td>${uc.sysCreateTime}</td>
                    <td>${uc.loginTimes}</td>
                    <td>${uc.customerType}</td>
                    <td>${uc.sysName}</td>
                    <td><span class="color2 curP">查看</span></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <th colspan="14">暂无数据</th>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
<div id="page" class="fr"></div>
<script type="application/javascript">
    $(document).ready(function () {
        /***
         ** 1 layui参数直接带入 2 'page'分页div的 id 3 pageSeach 查询表单的id 4 pageTable 被替换列表的 css
         */
        $("#___pageSize").val("${pageInfo.pageSize}");
        $("#___totalRecord").val("${pageInfo.totalPage}");
        $("#___totalRecord").val("${pageInfo.totalRecord}");
        $("#___startPage").val("${pageInfo.pageNo}");
        $.devfrw.page.submitBindForm(layui, 'page', 'pageSeach', 'pageTable');
    });
</script>