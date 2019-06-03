<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/jsp/taglibs.jsp"%>
<!--头部开始-->
<div class="head-wrap the-bg">
	<div class="wid-fixed">
		<div class="fl marl-20">
			<div class="logo fl">
				<img src="${resource}/commons/images/logo.png" alt="logo">
			</div>
			<div class="logo-text fl">
				<c:if test="${param.client==1}">国泰安商业银行  ·  网上银行个人版 </c:if>
				<c:if test="${param.client==2}">国泰安商业银行  ·  网上银行企业版 </c:if>
				<c:if test="${param.client==3}">国泰安商业银行  ·  网上银行管理中心 </c:if>
				<c:if test="${param.client==4}">国泰安网上商城</c:if>
			</div>
		</div>
		<div class="fr head-right w500 mt16">
			<img class="AllMain fr" onclick="javascript:window.location.href='${ctx }/logout'"  src="${resource}/commons/images/exit.png"/>
			<div class="fr clearfix mr20">
				<div class="fl leftC">角色：</div>

				<select id="cRoles" onchange="sendTo();" class="fr" style="border-color:#fff;width:120px;">
				</select>
			</div>
			<div class="fr clearfix mr10">
				<div class="fl leftC">系统端口：</div>
				<select id="clinet" class="fr" style="border-color:#fff;width:120px;" onchange="getRoles();" >
					<option value="1">网银端</option>
					<option value="2">支付端</option>
					<option value="3">购物端</option>
				</select>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
    //投资人、企业财务和网银管理员
    var ebank=[{0:'请选择'},{1:'投资人'},{2:"企业财务"},{3:'网银管理员'}];
    //投资人、企业财务和支付管理员
    var epay=[{'0':'请选择'},{'1':'投资人'},{'2':"企业财务"},{'3':'支付管理员'}];
    //
    var emall=[{'0':'请选择'},{'1':'网购客户'},{'2':"商城后台"}];
    function getRoles(){
        var id= $("#clinet").val();
        $("#cRoles").empty();
        if(id==1){
            $("#cRoles").append("<option value='0'>请选择</option>");
            $("#cRoles").append("<option value='1'>投资人</option>");
            $("#cRoles").append("<option value='2'>企业财务</option>");
            $("#cRoles").append("<option value='3'>网银管理员</option>");
        }
        if(id==2){
            $("#cRoles").append("<option value='0'>请选择</option>");
            $("#cRoles").append("<option value='1'>投资人</option>");
            $("#cRoles").append("<option value='2'>企业财务</option>");
            $("#cRoles").append("<option value='3'>支付管理员</option>");
        }
        if(id==3){
            $("#cRoles").append("<option value='0'>请选择</option>");
            $("#cRoles").append("<option value='1'>网购客户</option>");
            $("#cRoles").append("<option value='2'>商城后台</option>");
        }
    }
    function sendTo(){
        console.log( $("#clinet").val()+"--"+ $("#cRoles").val());
        var id= $("#clinet").val();
        var cRoles=$("#cRoles").val();
        if(id==1){
            if(cRoles==1){
                window.location.href="/ebank/nologin/logout?idNum="+cRoles;
            }
            if(cRoles==2){
                window.location.href="/ebank/nologin/logout?idNum="+cRoles;
            }
            if(cRoles==3){
                window.location.href="/ebank/nologin/logout?idNum="+cRoles;
            }

        }
        if(id==2){
            if(cRoles==1){
                window.location.href="/epay/nologin/logout?idNum="+cRoles;
            }
            if(cRoles==2){
                window.location.href="/epay/nologin/logout?idNum="+cRoles;
            }
            if(cRoles==3){
                window.location.href="/epay/nologin/logout?idNum="+cRoles;
            }
        }
        if(id==3){
            if(cRoles==1){
                window.location.href="/ebank/nologin/logout?idNum=4";
            }
            if(cRoles==2){
                window.location.href="/ebank/nologin/logout?idNum=5";
            }
        }
    }

    //获取cookies
    function getCookie(c_name){
        if (document.cookie.length>0)
        {
            var c_start=document.cookie.indexOf(c_name + "=")
            if (c_start!=-1){
                c_start=c_start + c_name.length+1
                var  c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return ""
    }
    setHeader();
    //设置头
    function setHeader(){
        var headerMessage=getCookie('headerMessage').split("&");
        var idNum=1;
        var cRoles=0;
        if(headerMessage != ""){
            idNum=headerMessage[0];
            cRoles=headerMessage[1];
        }
        var cRolesElement =document.getElementById("cRoles");

        /* for(var i=0;i<cRolesElement.childNodes.length;i++){
             cRolesElement.removeChild(cRolesElement.options[0]);
             cRolesElement.remove(0);
         }*/

        var eOptions=ebank;
        if(idNum==2){
            eOptions=epay;
        }else{
            if(idNum==3){
                eOptions=emall;
            }
        }
        for(var index in eOptions){
            var option = document.createElement("option");
            for(var key in eOptions[index]){
                option.value =key;
                option.innerHTML =eOptions[index][key] ;
                cRolesElement.appendChild(option)
            }

        }

        setSelectChecked("clinet",idNum);
        setSelectChecked("cRoles",cRoles);
    }
    function setSelectChecked(selectId, checkValue){
        var select = document.getElementById(selectId);
        for(var i=0; i<select.options.length; i++){
            if(select.options[i].value == checkValue){
                select.options[i].selected = true;
                break;
            }
        }
    };
</script>
<!--头部结束-->