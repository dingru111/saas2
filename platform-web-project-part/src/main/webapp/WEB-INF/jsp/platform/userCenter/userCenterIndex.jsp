<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<style>
    .layui-laydate .layui-this{background:#1ed4cd !important;}
</style>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
    <div class="pd20">
        <div class="bgFFF pd20">
            <div class="clearfix bt" style="padding-bottom:15px;">
                <div class="clearfix fl">
                    <div class="fl leftBL">创建时间</div>
                    <div class="fl">
                        <div class="btn2 active">不限</div>
                        <div class="btn2">3个月内</div>
                        <div class="btn2">3-6个月内</div>
                        <div class="btn2">6-12个月内</div>
                        <div class="btn2">12个月以上</div>
                    </div>
                </div>
                <div class="clearfix fr">
                    <div class="fl leftBL">日期范围</div>
                    <input type="text" class="inputDate fl" id="inputDate" readonly>
                </div>
            </div>
            <div class="mt10 clearfix">
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">所在国家</div>
                    <select id="select_countryId" class="select3">
                        <option value="0">请选择</option>
                        <c:forEach items="${countryList}" var="country">
                            <option value="${country.id}">${country.areaName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">所在省份</div>
                    <select id="select_provinceId" class="select3">
                        <option value="-1">请选择</option>
                    </select>
                </div>
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">所在城市</div>
                    <select id="select_cityId" class="select3">
                        <option value="-1">请选择</option>
                    </select>
                </div>
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">客户类型</div>
                    <select class="select3" id="select_customerType">
                        <option value="0">普通客户</option>
                        <option value="1">VIP客户</option>
                        <option value="2">VVIP客户</option>
                        <option value="3">一级管理员</option>
                        <option value="4">二级管理员</option>
                    </select>
                </div>
                <%--<div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">账户角色</div>
                    <select class="select3" id="select_accountRole">
                        <c:choose>
                            <c:when test="${userSourse !=null && fn:length(userSourse)>0}">
                                <c:forEach items="${userSourse}" var="ust">
                                    <option value="${ust.accountRole}">${ust.roleName}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <option value="-1">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>--%>
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">账号来源</div>
                    <select class="select3" id="select_accountSource">
                        <c:choose>
                            <c:when test="${userSourse !=null && fn:length(userSourse)>0}">
                                <c:forEach items="${userSourse}" var="ust">
                                    <option value="${ust.accountSource}">${ust.systemName}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <option value="-1">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="clearfix fl UserCenterList">
                    <div class="fl leftBL">所属系统</div>
                    <select class="select3" id="select_accountRole">
                        <c:choose>
                            <c:when test="${userSourse !=null && fn:length(userSourse)>0}">
                                <c:forEach items="${userSourse}" var="ust">
                                    <option value="${ust.systemId}">${ust.systemName}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <option value="-1">请选择</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </div>
            <div class="txtC mt10">
                <div class="btn3" onclick="getData();">查询</div>
            </div>
        </div>
    </div>
    <div class="pd20">
        <div class="bgFFF">
            <div class="clearfix pdlr20 bt">
                <div class="fl LeftTitle">用户账号列表</div>
                <div class="fr RightTxt clearfix">
                    <form id="pageSeach" name="pageSeach"
                          action="${ctx}/nologin/platform/userCenter/userCenterData" method="post">
                        <input name="name" placeholder="请输入学校/公司名称" id="name" type="search" class="search fl" value=""/>
                        <input name="countryId" id="countryId" type="hidden" value="0"/>
                        <input name="provinceId" id="provinceId" type="hidden" value="-1"/>
                        <input name="cityId" id="cityId" type="hidden" value="-1"/>
                        <input name="customerType" id="customerType" type="hidden" value="-1"/>
                        <input name="accountRole" id="accountRole" type="hidden" value="-1"/>
                        <input name="accountSource" id="accountSource" type="hidden" value="-1"/>
                        <input name="startDate" id="startDate" type="hidden"/>
                        <input name="endDate" id="endDate" type="hidden"/>
                        <!-- 分页表单自定义标签一定要加上 -->
                        <page:form/>
                    </form>
                    <div class="searchBtn fl"><img onclick="getData();" src="${ctx}/commons/images/icon3.png"/></div>
                </div>
            </div>
            <div class="pd20">
                <div class="DownloadBtn">导出操作日志</div>
                <div class="operaP" id="shcool_data_div"></div>

                <div class="clear"></div>
            </div>
        </div>
        <div class="copyBottom">Copyright @ 2017 国泰安教育信息技术股份有限公司</div>
        <div class="clear"></div>
    </div>
<%--<%@ include file="/WEB-INF/jsp/commons/jsp/must_js.jsp" %>--%>
<script>
    $(document).ready(function () {
        $("select").select2({minimumResultsForSearch: Infinity});
        getData();

        //绑定选择框事件
        $("select").change(function () {
            var id = $(this).attr("id");
            var value = $(this).val();
            id = id.substring(7);
            $("#" + id).val(value);
            if (id == "countryId" || id == "provinceId") {
                var provinceCityType = 2; //国家->省
                var divId = "select_provinceId";
                if (id == "provinceId") { //选择的是省（即查找城市）
                    divId = "select_cityId";
                    if (value == "-1") { //且选择的是请选择
                        $("#" + divId).empty().html("<option value=\"-1\">请选择</option>");
                        return false;
                    }
                    provinceCityType = 3;
                } else {
                    //国选择的是请选择
                    if (value == "0") {
                        $("#" + divId).empty().html("<option value=\"-1\">请选择</option>");
                        return false;
                    }
                }
                getProvinceOrCity(divId, value, provinceCityType);
            }
        });
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

    //获取省市信息
    function getProvinceOrCity(selectId, pId, type) {
        $.ajax({
            type: "get",
            data: {pId: pId, type: type},
            url: "${ctx}/nologin/platform/baseData/school/getProvinceOrCity",
            //  dataType: "html",
            success: function (data) {
                var option = "<option value=\"-1\" >请选择</option>";
                $.each(data, function (index, e) {
                    option += "<option value=\"" + e.id + "\" >" + e.areaName + "</option>"
                });
                $("#" + selectId).empty().html(option);
            }
        });
    }
    layui.use(['laydate', 'layer'], function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#inputDate'
            ,type: 'datetime'
            ,format: 'yyyy年MM月dd日'
            ,range: true //或 range: '~' 来自定义分割字符
            ,done: function(value, date){ //监听日期
                //alert(value);
                var ad = value.split("\-");
                var adStr = ad[0];
                var edStr = ad[1];
                adStr = adStr.replace("年",'-').replace("月",'-').replace("日",'');
                edStr = edStr.replace("年",'-').replace("月",'-').replace("日",'');
                $("#startDate").val(adStr);
                $("#endDate").val(edStr);
                //alert( $("#startDate").val()+"------------------------"+$("#endDate").val());
            }
        });
    });
</script>
</html>
