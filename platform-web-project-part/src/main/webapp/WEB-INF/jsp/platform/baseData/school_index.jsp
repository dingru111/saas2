<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp" %>
<div class="bgFFF pd20">
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
            <div class="fl leftBL">所属层次</div>
            <select id="select_level" class="select3">
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
        <div class="clearfix fl UserCenterList">
            <div class="fl leftBL">所属类型</div>
            <select id="select_type" class="select3">
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
    <div class="txtC mt10">
        <div class="btn3" onclick="getData();">查询</div>
    </div>
</div>
<div class="mt20">
    <div class="bgFFF" style="">
        <div class="clearfix pdlr20 bt">
            <div class="fl LeftTitle">学校/公司名称列表</div>
            <div class="fr RightTxt clearfix">
                <form id="pageSeach" name="pageSeach"
                      action="${ctx}/nologin/platform/baseData/school/schoolData" method="post">
                    <input name="name" placeholder="请输入学校/公司名称" id="name" type="search" class="search fl" value=""/>
                    <input name="countryId" id="countryId" type="hidden" value="0"/>
                    <input name="provinceId" id="provinceId" type="hidden" value="-1"/>
                    <input name="cityId" id="cityId" type="hidden" value="-1"/>
                    <input name="level" id="level" type="hidden" value="-1"/>
                    <input name="type" id="type" type="hidden" value="-1"/>
                    <!-- 分页表单自定义标签一定要加上 -->
                    <page:form/>
                </form>
                <div class="searchBtn fl"><img onclick="getData();" src="${ctx}/commons/images/icon3.png"/></div>
            </div>
        </div>
        <div class="pd20">
            <div class="clearfix">
                <div class="clearfix fl">
                    <div class="fl addBtn1" onclick="addOrUpdate();">新增</div>
                </div>
            </div>

            <div class="operaP" id="shcool_data_div"></div>

        </div>
    </div>
    <div class="copyBottom">Copyright @ 2017 国泰安教育信息技术股份有限公司</div>

    <!--右侧内容结束-->
    <div class="clear"></div>
</div>
<script type="text/javascript">
    $(function () {
        $("select").select2({minimumResultsForSearch: Infinity});
        //获取数据
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

    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });

    //新增、编辑学校
    function addOrUpdate(id) {
        var title = "新增学校/公司";
        if (id != undefined) {
            title = "编辑学校/公司";
        } else {
            id = "";
        }
        var reSubmit = false;
        layer.open({
            type: 2,
            title: title,
            resize: false,
            shadeClose: false,
            closeBtn: 1,
            area: ['550px', '400px'],
            content: "${ctx}/nologin/platform/baseData/school/addEditSchool?id=" + id,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                if (!layer.getChildFrame("#saveOrUpdateSchool", index).valid()) {
                    return false;
                }
                if (!reSubmit) {
                    reSubmit = true;
                } else {
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: layer.getChildFrame('#saveOrUpdateSchool', index).attr("action"),
                    data: layer.getChildFrame('#saveOrUpdateSchool', index).serialize(),
                    success: function (data) {
                        var msn = '' || i18n[''];
                        msn = msn || i18n[data.msg];
                        if (data.status) {
                            layer.msg(msn);
                            layer.close(index);
                            getData();
                        } else {
                            layer.msg(msn);
                            reSubmit = false;
                        }
                    },
                    error: function () {
                        layer.msg(i18n['sysError']);
                        reSubmit = false;
                    }
                });
            }
            , btn2: function (index, layero) {
                layer.close(index);
            }
            , success: function (layero, index) {
                layer.getChildFrame('#saveOrUpdateSchool', index).validate({
                    rules: {
                        name: {
                            required: true,
                            checkUnique: ["${ctx}/nologin/platform/baseData/school/checkUniqueSchool", id]
                        },
                        countryId: {
                            selectRequired: ["0"]
                        },
                        provinceId: {
                            selectRequired: ["-1"]
                        },
                        cityId: {
                            selectRequired: ["-1"]
                        },
                        level: {
                            selectRequired: ["-1"]
                        },
                        type: {
                            selectRequired: ["-1"]
                        },
                    }
                });
            }
        });
    }
</script>

