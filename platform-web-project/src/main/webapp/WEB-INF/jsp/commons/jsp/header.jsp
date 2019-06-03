<%@ page language="java" pageEncoding="UTF-8"%>
<!--头部开始-->
<div class="head-wrap the-bg">
	<div class="wid-fixed">
		<div class="fl marl-20">
			<div class="logo fl">
				<img src="${resource}/commons/images/logo.png" alt="logo">
			</div>
			<div class="logo-text fl">
		 	${param.client} --- ${param.client==1}==
			<c:if test="${param.client==1}">国泰安商业银行  ·  网上银行个人版 </c:if>
			<c:if test="${param.client==2}">国泰安商业银行  ·  网上银行企业版 </c:if>
			<c:if test="${param.client==3}">国泰安商业银行  ·  网上银行管理中心 </c:if>
			<c:if test="${param.client==4}">国泰安网上商城</c:if>
			</div>
		</div>
		<div class="fr head-right w500 mt16">
			<img class="AllMain fr" onclick="javascript:window.location.href='${ctx }/logout'"  src="${resource}/commons/images/exit.png"/>
			<div class="fr clearfix mr20">
				<div class="fl leftC">系统端口：</div>
				<select id="clinet" class="fr" style="border-color:#fff;width:120px;" onchange="getRoles();" >
					<option value="1">网银端</option>
					<option value="2">支付端</option>
					<option value="3">购物端</option>
				</select>
			</div>
			<div class="fr clearfix mr10">
				<div class="fl leftC">角色：</div>
				<select id="cRoles" onchange="sendTo();" class="fr" style="border-color:#fff;width:120px;">
					<option value="0">请选择</option>
					<option value='1'>个人网银</option>
					<option value='2'>企业网银</option>
					<option value='3'>网银后台</option>
				</select>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
    function getRoles(){
    	 var id= $("#clinet").val();
    	 $("#cRoles").empty();
		 if(id==1){
			 $("#cRoles").append("<option value='0'>请选择</option>"); 
			 $("#cRoles").append("<option value='1'>个人网银</option>"); 
			 $("#cRoles").append("<option value='2'>企业网银</option>"); 
			 $("#cRoles").append("<option value='3'>网银后台</option>"); 
		 }
		 if(id==2){
			 $("#cRoles").append("<option value='0'>请选择</option>"); 
			 $("#cRoles").append("<option value='1'>个人支付</option>"); 
			 $("#cRoles").append("<option value='2'>企业支付</option>"); 
			 $("#cRoles").append("<option value='3'>支付后台</option>");
		 }
		 if(id==3){
			 $("#cRoles").append("<option value='0'>请选择</option>"); 
			 $("#cRoles").append("<option value='1'>网上商城</option>"); 
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
</script>
<!--头部结束-->