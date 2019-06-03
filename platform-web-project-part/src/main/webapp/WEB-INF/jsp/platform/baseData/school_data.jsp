<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<table class="MainTable mt20">
    <tr class="bg-gray">
        <th width="50">序号</th>
        <th>国家</th>
        <th>省份</th>
        <th>城市</th>
        <th>学校/公司</th>
        <th>层次</th>
        <th>操作</th>
    </tr>
    <c:choose>
        <c:when test="${pageInfo.results !=null && fn:length(pageInfo.results)>0 }">
            <c:forEach items="${pageInfo.results}" varStatus="i" var="schoolDto">
                <tr>
                    <td>${i.index+1 + (pageInfo.pageNo-1)*(pageInfo.pageSize) }</td>
                    <td>${schoolDto.countryName}</td>
                    <td>${schoolDto.provinceName}</td>
                    <td>${schoolDto.cityName}</td>
                    <td>${schoolDto.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${schoolDto.level==0}">小学</c:when>
                            <c:when test="${schoolDto.level==1}">初中</c:when>
                            <c:when test="${schoolDto.level==2}">高中</c:when>
                            <c:when test="${schoolDto.level==3}">中职</c:when>
                            <c:when test="${schoolDto.level==4}">高职</c:when>
                            <c:when test="${schoolDto.level==5}">本科</c:when>
                            <c:when test="${schoolDto.level==6}">研究生</c:when>
                        </c:choose>
                    </td>
                    <td><span onclick="addOrUpdate('${schoolDto.id}')" class="color2 curP">修改</span>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <th colspan="7">暂无数据</th>
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