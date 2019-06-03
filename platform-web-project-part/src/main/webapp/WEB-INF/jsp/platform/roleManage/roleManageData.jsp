<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<table class="MainTable mt20">
    <tr class="bg-gray">
        <th width="50"><div class="ckBox"></div></th>
        <th width="50">序号</th>
        <th>账号角色名称</th>
        <th>权限描述</th>
        <th>操作</th>
    </tr>
    <%--<tr>
        <td><div class="ckBox active"></div></td>
        <td>1</td>
        <td>林小姐</td>
        <td>11222</td>
        <td><span class="color2 curP">查看</span>　<span class="color2 curP">修改</span></td>
    </tr>--%>
    <c:choose>
        <c:when test="${pageInfo.results !=null && fn:length(pageInfo.results)>0}">
            <c:forEach items="${pageInfo.results}" varStatus="i" var="results">
                <tr>
                    <td><div class="ckBox"></div></td>
                    <td roleId ="${results.id}">${i.index+1+(pageInfo.pageNo - 1)*(pageInfo.pageSize)}</td>
                    <td>${results.roleName}</td>
                    <td>${results.description}</td>
                    <td><span class="color2 curP">查看</span>　<span class="color2 curP">修改</span></td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5">暂无数据</td>
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