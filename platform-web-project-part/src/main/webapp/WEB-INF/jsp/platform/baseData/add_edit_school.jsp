<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    html, body {
        width: auto;
        height: auto;
        cursor: default;
        overflow: hidden;
    }
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/commons/jsp/baseResource.jsp" %>
<form id="saveOrUpdateSchool" action="/project/nologin/platform/baseData/school/saveOrUpdateSchool"
      onsubmit="return false;" novalidate="novalidate">
    <div id="ap" style="margin:30px 35px;">
        <div class="clearfix">
            <div class="fl leftBB"><span class="color-red">*</span>学校/公司名称：</div>
            <input placeholder="请输入名称" class="input2 fl" type="text" maxlength="20" name="name" value="${school.name}">
            <input type="hidden" name="id" id="id" value="${school.id}">
        </div>
        <div class="clearfix mt10">
            <div class="fl leftBB"><span class="color-red">*</span>所属国家：</div>
            <select class="select2" id="select_countryId" name="countryId">
                <option value="0">请选择</option>
                <c:forEach items="${countryList}" var="country">
                    <option value="${country.id}"
                            <c:if test="${country.id==school.countryId}">selected</c:if>> ${country.areaName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="clearfix mt10">
            <div class="fl leftBB"><span class="color-red">*</span>所属省份：</div>
            <select class="select2" id="select_provinceId" name="provinceId">
                <option value="-1">请选择</option>
            </select>
        </div>
        <div class="clearfix mt10">
            <div class="fl leftBB"><span class="color-red">*</span>所属城市：</div>
            <select class="select2" id="select_cityId" name="cityId">
                <option value="-1">请选择</option>
            </select>
        </div>
        <div class="clearfix mt10">
            <div class="fl leftBB"><span class="color-red">*</span>学历层次：</div>
            <select class="select2" id="select_level" name="level">
                <option value="-1">请选择</option>
                <option value="6">研究生</option>
                <option value="5">本科</option>
                <option value="4">高职</option>
                <option value="3">中职</option>
                <option value="2">高中</option>
                <option value="1">初中</option>
                <option value="0">小学</option>
            </select>
        </div>
        <div class="clearfix mt10">
            <div class="fl leftBB"><span class="color-red">*</span>所属类型：</div>
            <select class="select2" id="select_type" name="type">
                <option value="-1">请选择</option>
                <option value="0">综合类</option>
                <option value="1">理工类</option>
                <option value="2">师范类</option>
                <option value="3">农林类</option>
                <option value="4">政法类</option>
                <option value="5">医药类</option>
                <option value="6">财经类</option>
                <option value="7">民族类</option>
                <option value="8">语言类</option>
                <option value="9">艺术类</option>
                <option value="10">体育类</option>
                <option value="11">军事类</option>
                <option value="12">旅游类</option>
                <option value="13">N/A</option>
            </select>
        </div>
    </div>
</form>
<script type="application/javascript">
    $(function () {
        $("select").select2({minimumResultsForSearch: Infinity});

        //若编辑则初始化信息
        if ($("#id").val() != "") {
            $("#select_type").select2("val", "${school.type}");
            $("#select_level").select2("val", "${school.level}");
            getProvinceOrCity("select_provinceId", $("#select_countryId").val(), 2, "${school.provinceId}");
            getProvinceOrCity("select_cityId", "${school.provinceId}", 3, "${school.cityId}");
        }
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

    //获取省市信息
    function getProvinceOrCity(selectId, pId, type, valueId) {
        $.ajax({
            type: "get",
            data: {pId: pId, type: type},
            url: "${ctx}/nologin/platform/baseData/school/getProvinceOrCity",
            //  dataType: "html",
            success: function (data) {
                var option = "<option value=\"-1\" >请选择</option>";
                $.each(data, function (index, e) {
                    if (valueId != undefined && e.id == valueId) {
                        option += "<option selected value=\"" + e.id + "\" >" + e.areaName + "</option>"
                    } else {
                        option += "<option value=\"" + e.id + "\" >" + e.areaName + "</option>"
                    }

                });
                $("#" + selectId).empty().html(option);
            }
        });
    }
</script>
</html>