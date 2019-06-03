<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<div class="pd20">
    <div class="bgFFF" style="">
        <div class="clearfix pdlr20 bt">
            <div class="fl LeftTitle">后台管理系统目录</div>
            <div class="fr RightTxt clearfix">
                <form id="pageSeach" name="pageSeach" action="${ctx}/nologin/platform/mainHome/mainHomeData"
                      method="post">
                    <input type="search" class="search fl" name="name" placeholder="请输入系统名称"/>
                    <input name="isCollect" id="isCollect" type="hidden" value="-1"/>
                    <!-- 分页表单自定义标签一定要加上 -->
                    <page:form/>
                </form>
                <div class="searchBtn fl" onclick="$('#pageSeach').submit();"><img
                        src="${ctx}/commons/images/icon3.png"/></div>
            </div>
        </div>
        <div class="pd20">
            <div class=" txtC">
                <div class="clearfix di" style="border-radius:3px;overflow:hidden;display:inline-block">
                    <div class="tabLi fl active curP" style="border-radius:3px 0 0 3px;" value="-1">全部</div>
                    <div class="tabLi fl curP" style="border-radius:0 3px 3px 0;" value="1">收藏</div>
                </div>
            </div>
            <table class="MTable mt20 pageTable ">
                <tr class="bg-gray">
                    <th width="80"></th>
                    <th>后台管理系统名称</th>
                    <th>前台网站</th>
                    <th>操作</th>
                </tr>
                <c:choose>
                    <c:when test="${pageInfo.results !=null && fn:length(pageInfo.results)>0 }">
                        <c:forEach items="${pageInfo.results}" varStatus="i" var="userSystemDto">
                            <tr>
                                <td>
                                    <div class="Thum"></div>
                                </td>
                                <td>${userSystemDto.name }</td>
                                <td class="color1">${userSystemDto.realmName}</td>
                                <td><span class="color1 curP">进入</span>
                                    <c:choose>
                                        <c:when test="${userSystemDto.isCollect==0}">
                                            <span class="curP collection"
                                                  onclick="singleIsCollect(this,'${userSystemDto.id}');">收藏</span>
                                        </c:when>
                                        <c:otherwise><span class="curP collection active"
                                                           onclick="singleIsCollect(this,'${userSystemDto.id}');">收藏</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th colspan="3">暂无数据</th>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
            <div id="page" class="fr"></div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="copyBottom">Copyright @ 2017 国泰安教育信息技术股份有限公司</div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        /***
         ** 1 layui参数直接带入 2 'page'分页div的 id 3 pageSeach 查询表单的id 4 pageTable 被替换列表的 css
         */
        $.devfrw.page.submitBindForm(layui, 'page', 'pageSeach', 'pageTable');
        //收藏（全部）的切换
        $(".tabLi").click(function () {
            debugger;
            $(this).addClass("active").siblings().removeClass("active");
            $("#isCollect").val($(this).attr("value"));
            $('#pageSeach').submit();
        });
    });


    //收藏
    function singleIsCollect(obj, id) {
        var isCollect = 0;
        var $obj = $(obj);
        //收藏到未收藏返回 false；反之则反。
        var isCollectFlag = $obj.hasClass("active") ? false : true;
        if (isCollectFlag) {
            isCollect = 1;
        }
        $.ajax({
            url: "${ctx}/nologin/platform/mainHome/changeCollect",
            type: "get",
            data: {"id": id, "isCollect": isCollect},
            success: function (data) {
                if (data.status) {
                    if (isCollectFlag) {
                        $obj.addClass("active");
                    } else {
                        $obj.removeClass("active");
                    }
                }
            }

        });
    }

</script>