<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<title>专业行业管理</title>
<div>
    <div class="bgFFF" style="">
        <div class="clearfix pdlr20 bt">
            <div class="fl LeftTitle">
                <c:choose>
                    <c:when test="${type==0}">
                        专业方向列表
                    </c:when>
                    <c:otherwise>
                        细分行业列表
                    </c:otherwise>
                </c:choose></div>
            <div class="fr RightTxt clearfix">

                <form id="pageSeach" name="pageSeach"
                      action="${ctx}/nologin/platform/baseData/profession/professionData" method="post">
                    <input name="name" type="search" placeholder="请输入名称" class="search fl" value=""/>
                    <input name="type" id="professionType" type="hidden" value="${type}"/>
                    <!-- 分页表单自定义标签一定要加上 -->
                    <page:form/>
                </form>
                <div class="searchBtn fl"><img src="${ctx}/commons/images/icon3.png"/></div>
            </div>
        </div>
        <div class="pd20">
            <div class="clearfix">
                <div class="clearfix fl">
                    <div class="fl addBtn1" onclick="saveOrUpdateProfession();">新增</div>
                </div>
            </div>
            <table class="MainTable mt20 pageTable">

                <tr class="bg-gray">
                    <th width="50">序号</th>
                    <c:choose>
                        <c:when test="${type==0}">
                            <th>专业方向列表</th>
                        </c:when>
                        <c:otherwise>
                            <th>细分行业列表</th>
                        </c:otherwise>
                    </c:choose>
                    <th>操作</th>
                </tr>
                <c:choose>
                    <c:when test="${pageInfo.results !=null && fn:length(pageInfo.results)>0 }">
                        <c:forEach items="${pageInfo.results}" varStatus="i" var="profession">
                            <tr>
                                <td>${i.index+1 + (pageInfo.pageNo-1)*(pageInfo.pageSize) }</td>
                                <td>
                                        ${profession.name}
                                </td>
                                <td><span onclick="saveOrUpdateProfession('${profession.id}')"
                                          class="color2 curP">修改</span></td>
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

    <!--右侧内容结束-->
    <div class="clear"></div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        /***
         ** 1 layui参数直接带入 2 'page'分页div的 id 3 pageSeach 查询表单的id 4 pageTable 被替换列表的 css
         */
        $.devfrw.page.submitBindForm(layui, 'page', 'pageSeach', 'pageTable');
    });

    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });


    //新增、编辑
    function saveOrUpdateProfession(pId) {
        var saveOrUpdateProfessionFlag = false;
        var layerTitle = "专业方向";
        var tipsName = "方向名称";
        var professionId = "";
        if ($("#professionType").val() == "1") {
            layerTitle = "细分行业";
            tipsName = "细分行业名称";
        }
        if (pId != undefined) {
            professionId = pId;
            layerTitle = "编辑" + layerTitle
        } else {
            layerTitle = "新增" + layerTitle;
        }
        layer.open({
            type: 1,
            title: layerTitle,
            resize: false,
            shadeClose: false,
            closeBtn: 1,
            area: ['530px', '200px'],
            content: '<form id="saveOrUpdateProfession"  action="${ctx}/nologin/platform/baseData/profession/saveOrUpdate" onsubmit="return false;">\n' +
                '<div id="ap" style="margin:30px 35px;">' +
                '<div class="clearfix">' +
                '<div class="fl leftBB"><span class="color-red">*</span>' + tipsName + '：</div>' +
                '<input type="text" placeholder="请输入名称" name="name" class="input2 fl">' +
                '<input type="hidden" name="id" value="' + professionId + '"/>' +
                '<input type="hidden" name="type" value="' + $("#professionType").val() + '" />' +
                '</div>' +
                '</div>' +
                '</form>',
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                if (!$("#saveOrUpdateProfession").valid()) {
                    return false;
                }
                if (!saveOrUpdateProfessionFlag) {
                    saveOrUpdateProfessionFlag = true;
                } else {
                    return;
                }
                //按钮【按钮一】的回调
                $.ajax({
                    type: "POST",
                    url: $("#saveOrUpdateProfession").attr("action"),
                    data: $("#saveOrUpdateProfession").serialize(),
                    success: function (data) {
                        var msn = '' || i18n[''];
                        msn = msn || i18n[data.msg];
                        if (data.status) {
                            layer.close(index);
                            layer.alert(msn, {icon: 1, closeBtn: 0}, function (indextips) {
                                layer.close(indextips);
                                $('#pageSeach').submit();
                            });
                        } else { //未成功新增、编辑
                            saveOrUpdateProfessionFlag = false;
                            layer.msg(msn);
                        }
                    }/*,
                    error:function () { //系统错误，已try catch 故多余
                        saveOrUpdateProfessionFlag = false;
                        layer.msg("sysError");
                }*/
                });

            }
            , btn2: function (index, layero) { //取消按钮
                saveOrUpdateProfessionFlag = false;
                layer.close(index);
            }
            , success: function (layero, index) {
                $("#saveOrUpdateProfession").validate({
                    rules: {
                        name: {
                            required: true,
                            checkUnique: ["${ctx}/nologin/platform/baseData/profession/checkUniqueProfession?type=" + $("#professionType").val(), professionId],
                        }
                    }
                });
            }
        });
    }
</script>