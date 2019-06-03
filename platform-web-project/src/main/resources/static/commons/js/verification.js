/* -----------------------------------
 *卢晨 2013-11-18
 *该JS用来验证表单且基于jquery
 *向表单中添加默认灰色文字   <input type='text' class = 'IsError' ShowValue = '文字' MsgName='表单名'>
 *验证是否能为空 <input type='text' class = 'IsError IsRequired' MsgName='表单名'>  验证表单为空时可以添加一个匿名属性 DefaultValue 是为了在特殊情况的判断空
 *验证表单最大长度<input type='text' class = 'IsError IsMaxLength' MaxLength = '20' MsgName='表单名'>
 *验证表单最小长度<input type='text' class = 'IsError IsMinLength' MinLength = '20' MsgName='表单名'>
 *验证数字       <input type='text' class = 'IsError IsNumber' MsgName='表单名'>
 *验证最大数     <input type='text' class = 'IsError IsMaxNumber' MaxNumber ='2000' MsgName='表单名'>
 *验证最小数     <input type='text' class = 'IsError IsMinNumber' MinNumber= '0' MsgName='表单名'>
 *验证浮点数     <input type='text' class = 'IsError IsFloat' MsgName='表单名'>
 *验证最大浮点数 <input type='text' class = 'IsError IsMaxFloat' MaxFloat ='9999.99' MsgName='表单名'>
 *验证最小浮点数 <input type='text' class = 'IsError IsMinFloat' MinFloat= '10.12' MsgName='表单名'>
 *验证邮箱       <input type='text' class = 'IsError IsMail' MsgName='表单名'>
 *验证字符       <input type='text' class = 'IsError IsChar' MsgName='表单名'>
 *验证汉字       <input type='text' class = 'IsError IsChinese' MsgName='表单名'>
 *验证座机 传真  <input type='text' class = 'IsError IsTelephone' MsgName='表单名'>
 *验证网址       <input type='text' class = 'IsError IsUrl' MsgName='表单名'>
 *验证唯一性 <input type='text' class = 'IsError IsRemote' RemoteUrl= 'Index/ExistsUserName' RemoteType='Post' RemoteData='{ userName: 'userid'}' MsgName='表单名'>
 *验证特定规则文本<input type='text' class = 'IsError IsText' MsgName='表单名'>
 *验证特定规则文本<input type='text' class = 'IsError IsVerificationCode' MsgName='验证码' Code='abce'>
 *提交表单前只需判断$(".help-inline").length==0 即表单验证通过
 *
 *验证单选必填<input type='radio' name="必填" class = 'IsError IsRequired' MsgName='表单名' msgBd='必填，错误信息显示元素的id名称'>
 *验证多选必填<input type='checkbox' name="必填" class = 'IsError IsRequired' MsgName='表单名' msgBd='必填，错误信息显示元素的id名称'>
 * IsHidNoVerify  加上该class后，隐藏后不校验
 * -------------------------------------
 */

var timeout = 120;

var errorImg;

var DefaultBorderColor = "#e0e0e0";


$(function () {
   // errorImg = FormatBaseUrl("images/errorImg.png");
	errorImg = base+"/images/errorImg.png";

    //初始化验证框中的初始值
    $(".IsError").each(function () {
        if ($(this).hasClass("IsSelect")) {
            $(this).parent().wrap("<div class ='div_div' style = 'display: inline ;'></div>");
        } else {
            $(this).wrap("<div class ='div_div' style = 'display: inline ;'></div>");
        }
    });

    var isErrorEvents = {
		blur:function(){
       		 if ($(this).closest(".div_div").length == 0) {
       	            $(this).wrap("<div class ='div_div' style = 'display: inline ;'></div>");
       	        }
       	        var textBox = $(this);
       	        if (timeout > 0) {
       	            setTimeout(function () {
       	                verification(textBox);
       	            }, timeout);
       	        } else {
       	            verification(textBox);
       	        }
       	},    
       	focus:function(){
       		/*if ($(this).closest(".div_div").length == 0) {
               $(this).wrap("<div class ='div_div' style = 'display: inline ;'></div>");
           }*/
           if ($(this).attr("type") == "text" || $(this).attr("type") == "password" || $(this).attr("type") == "file" || $(this).get(0).tagName == "TEXTAREA" || $(this).get(0).tagName == "SELECT") {
               $(this).next(".help-inline").remove();           
           }
       	},   
       	change:function(){
       		if ($(this).get(0).tagName == "SELECT") {
                   $(this).parent().next(".help-inline").remove();
                   $(this).parent().find(".selectbox").removeAttr("style");
                   verification($(this));
               }
       	},
	};
	$(document).on(isErrorEvents, ".IsError");

	$(".DefaultValue").on("blur", function () {
        if ($.trim($(this).val()).length < 1)
            $(this).val($(this).attr("DefaultValue")).css("color", "#c8c8c8");
    }).on("focus", function () {
        if ($.trim($(this).val()) == $(this).attr("DefaultValue"))
            $(this).val("").css("color", "#000");
    }).css("color", "#c8c8c8");
});

// 验证主体方法
function verification(textBox) {
	var msgCon = $("<font class='help-inline' style = 'right: 4px;color: #F57C7C;z-index:999;text-align: initial;display:block;'>" +
			"<span class='fl'></span>"+"<img class='fl' style ='position: relative; top: 2px; left: 0px;margin-left:20px;' src = '" + errorImg + "'/></font>");
    var msgTip = $("<span class='msgTip fl' style='margin-left:5px;height: 20px;line-height: 20px;margin-top: 1px;'></span>");
        
    if (textBox.attr("type") == "text" || textBox.attr("type") == "password" || 
    		textBox.attr("type") == "file" || textBox.attr("type") == "hidden" || textBox.get(0).tagName == "TEXTAREA" || 
    		textBox.get(0).tagName == "SELECT") {
    	textBox.next(".help-inline").remove();
       /* textBox.css({
            "background-color": "#ffffff",
            "border-color": ($(this).attr("DefaultBorderColor") == undefined || $(this).attr("DefaultBorderColor") == "") ? DefaultBorderColor : $(this).attr("DefaultBorderColor")
        });*/
        //隐藏不校验
        if(textBox.hasClass("IsHidNoVerify") && textBox.is(':hidden')){
        	return;
        }
        
        $(".help-inline").each(function (i) {
            $(this).css("z-index", 999 - i);
        });
        ////验证空字符串
        if (textBox.hasClass("IsRequired") && ($.trim(textBox.val()) == "" || textBox.val() == undefined || textBox.val() == textBox.attr("ShowValue") || textBox.val() == textBox.attr("defaulvalue") || textBox.val() == textBox.attr("DefaultValue"))) {
        	
            if (textBox.hasClass("IsSelect")) {
                textBox.parent().next(".help-inline").remove();
                msgCon.css("line-height", textBox.parent().find(".selectbox").outerHeight(true) + "px");
                textBox.parent().after(msgCon.append(msgTip.css({
                    left: -textBox.parent().outerWidth() + 'px',
                    top: textBox.parent().outerHeight() + 'px'
                }).text(textBox.attr("MsgName") + "不能为空")));
            } else {              
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'                    
                }).text(textBox.attr("MsgName") + "不能为空")));
            }           
            return;
        }
        
        //验证字符+数字（账号）
        if(textBox.hasClass("IsAccount") && $.trim(textBox.val()).length > 0 && !/^[A-Za-z0-9]+$/.test($.trim(textBox.val()))){
        	 textBox.after(msgCon.append(msgTip.css({
                 left: -textBox.outerWidth() + 'px',
                 top: textBox.outerHeight() + 'px'
             }).text(textBox.attr("MsgName") + "输入格式不正确,可由英文和数字组成")));
             return;
        }
        
      //验证英文 数字 空格 
        if(textBox.hasClass("IsEnglish") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z 0-9]+$/.test($.trim(textBox.val()))){
        	 textBox.after(msgCon.append(msgTip.css({
                 left: -textBox.outerWidth() + 'px',
                 top: textBox.outerHeight() + 'px'
             }).text(textBox.attr("MsgName") + "输入格式不正确,可由英文和数字组成")));
             return;
        }
        
        //验证最大长度
        if (textBox.hasClass("IsMaxLength") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()).length > parseInt(textBox.attr("MaxLength"))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "的长度不能大于" + textBox.attr("MaxLength") + "字")));
            return;
        }

        //验证最小长度
        if (textBox.hasClass("IsMinLength") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()).length < parseInt(textBox.attr("MinLength"))) {          
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "的长度不能小于" + textBox.attr("MinLength") + "个字符")));
            return;
        }

        //验证数字
        if (textBox.hasClass("IsNumber") && $.trim(textBox.val()).length > 0 && !/^(-)?[0-9]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "必须为整数")));
            return;
        }
        //验证数字从1开始
        if (textBox.hasClass("IsNumbers") && $.trim(textBox.val()).length > 0 && !/^(-)?[0-9]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "必须为大于0的整数")));
            return;
        }

        //验证QQ
        if (textBox.hasClass("IsQQ") && $.trim(textBox.val()).length > 0 && !/^(-)?[0-9]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "必须由数字组成")));
            return;
        }

        //验证最大数
        if (textBox.hasClass("IsMaxNumber") && parseInt(textBox.val()) > parseInt(textBox.attr("MaxNumber"))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "不能大于" + textBox.attr("MaxNumber"))));
            return;
        }

        //验证最小数
        if (textBox.hasClass("IsMinNumber") && parseInt(textBox.val()) < parseInt(textBox.attr("MinNumber"))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "不能小于" + textBox.attr("MinNumber"))));
            return;
        }

        //验证浮点数
        if (textBox.hasClass("IsFloat") && $.trim(textBox.val()).length > 0 && !/^-?([1-9]\d*\.?\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "必须为数字")));
            return;
        }

        //小数点后两位的正数数字
        if (textBox.hasClass("IsFloatPointTow") && $.trim(textBox.val()).length > 0 && !/^([1-9]+|[1-9]\d*\.?\d{1,2}|0?\.0[1-9]|0?\.[0-9][0-9]?|0)$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "不能小于0并最多精确到小数点后两位")));
            return;
        }

        //验证最大浮点数
        if (textBox.hasClass("IsMaxFloat") && parseFloat(textBox.val()) > parseFloat(textBox.attr("MaxFloat"))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "不能大于" + textBox.attr("MaxFloat"))));
            return;
        }

        //验证最小浮点数
        if (textBox.hasClass("IsMinFloat") && parseFloat(textBox.val()) < parseFloat(textBox.attr("MinFloat"))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "不能小于" + textBox.attr("MinFloat"))));
            return;
        }

        //验证邮箱
        if (textBox.hasClass("IsMail") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()) != "" &&
        		!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test($.trim(textBox.val()))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("邮件格式不正确，如：lisi0@gtadate.com")));
            return;
        }


        //验证字符
        if (textBox.hasClass("IsChar") && $.trim(textBox.val()).length > 0 && !/^[a-z\_\-A-Z]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("请输入a-z或A-Z之间的字母")));
            return;
        }
        //验证字母
        if (textBox.hasClass("IsLetter") && $.trim(textBox.val()).length > 0 && !/^[A-Z]+$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("请输入A-Z之间的字母")));
            return;
        }

        //验证中文
        if (textBox.hasClass("IsChinese") && $.trim(textBox.val()).length > 0 && !/^[\u4e00-\u9fff]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("请输入中文")));
            return;
        }

        //验证网址
        if (textBox.hasClass("IsUrl") && $.trim(textBox.val()).length > 0 && !/^(http(s)?:\/\/)(\w+\.){2,3}\w+$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("网址格式不正确，如：http://www.gtadata.com 或 https://www.gtadata.com.cn")));
            return;
        }
        //验证网址2:可输入/.等
        if (textBox.hasClass("IsUrlTwo") && $.trim(textBox.val()).length > 0) {
            var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
                    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
                    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
                    + "|" // 允许IP和DOMAIN（域名） 
                    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
                    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
                    + "[a-z]{2,6})" // first level domain- .com or .museum  
                    + "(:[0-9]{1,4})?" // 端口- :80  
                    + "((/?)|" // a slash isn't required if there is no file name  
                    + "(/.+)|"
                    + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";        
            var re = new RegExp(strRegex);

            if (re.test($.trim(textBox.val()))) {
                //return (true);
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text("网址格式不正确，如：http://www.gtadata.com 或 https://www.gtadata.com.cn")));
            }

            return;
        }

        //验证特定的文本规则   中英文、数字及“_”\"、-()（）
        if (textBox.hasClass("IsText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9fff_\-“”""''（）()、]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,可由中英文、数字及'\"”“、-()（）组成")));
            return;
        }
        
        //验证特定的文本规则   中英文、数字及“_”\"、-()（） （）-“”
        if (textBox.hasClass("IsSNText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9fff_\-“”""''（）()]*$/.test(textBox.val())) {
        	 textBox.after(msgCon.append(msgTip.css({
                 left: -textBox.outerWidth() + 'px',
                 top: textBox.outerHeight() + 'px'
             }).text("输入格式不正确,可由中英文、数字及（）-“”组成")));
             return;
        }
        
      //验证特定的文本规则   中英文、数字及_ - 中文的“”‘’《》，（） 英文的()  目前用来验证标题
        if (textBox.hasClass("IsNText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9fff_\-“”‘’《》，（）()]*$/.test(textBox.val())) {
        	 textBox.after(msgCon.append(msgTip.css({
                 left: -textBox.outerWidth() + 'px',
                 top: textBox.outerHeight() + 'px'
             }).text("输入格式不正确,可由中英文、数字及（）_-“”‘’《》，组成")));
             return;
        }

        //验证特定的文本规则   中英文、数字及“_”\"、-()（）
        if (textBox.hasClass("IsName") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9fff·_ ]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,可由中文、英文、数字及_和空格组成")));
            return;
        }
        //验证特定的文本规则   英文
        if (textBox.hasClass("IsNameText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,请输入英文")));
            return;
        }
        
        //验证特定的文本规则   中英文、数字及“_”\"、-()（）
//        if (textBox.hasClass("IsPwd") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9_]{6,20}$/.test(textBox.val())) {
//            textBox.after(msgCon.append(msgTip.css({
//                left: -textBox.outerWidth() + 'px',
//                top: textBox.outerHeight() + 'px'
//            }).text(textBox.attr("MsgName") + "输入格式不正确,可由6到20位英文、数字及下划线组成")));
//            return;
//        }
        
        if(textBox.hasClass("IsPwd") && !/^((?=.*?\d)(?=.*?[A-Za-z])|(?=.*?\d)(?=.*?[\W|_])|(?=.*?[A-Za-z])(?=.*?[\W|_]))[\dA-Za-z\W|_]{6,10}$/.test(textBox.val())){
        	 textBox.after(msgCon.append(msgTip.css({
               left: -textBox.outerWidth() + 'px',
               top: textBox.outerHeight() + 'px'
           }).text("输入要求6-10位，并由字母，数字或符号两种以上组合")));
           return;
        }

        //验证是否中英文、数字及下划线
        if (textBox.hasClass("IsRegularText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9_]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，可由英文、数字及下划线_组成")));
            return;
        }
        
        //验证是否中英文
        if (textBox.hasClass("IsCAEText") && $.trim(textBox.val()).length > 0 && !/^[\u4e00-\u9fa5a-zA-Z]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，可由中英文组成")));
            return;
        }
        
        //验证是否中英文 + 数字
        if (textBox.hasClass("IsCAENumText") && $.trim(textBox.val()).length > 0 && !/^[\u4e00-\u9fa5a-zA-Z0-9]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，可由中英文及数字组成")));
            return;
        }
        
      //验证是否英文、数字
        if (textBox.hasClass("IsEngText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，可由英文、数字组成")));
            return;
        }
        
        //验证是否英文、数字及横杠
        if (textBox.hasClass("IsResultText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\-]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，可由英文、数字及横杠“-”组成")));
            return;
        }
        //验证是否英文、数字及允许一位小数,数字输入对第一位为0不做限制，保存时舍去第一位的0
        if (textBox.hasClass("IsDecimalText") && $.trim(textBox.val()).length > 0 && !/(^[a-zA-Z]+$)|(^[0-9]\d*(\.\d)?$)/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("可输入数字或者字母，数字可含一位小数")));
            return;
        }
        //可以输入数字，并且含一位小数
        if (textBox.hasClass("IsSmallText") && $.trim(textBox.val()).length > 0 && !/^[1-9]\d*(\.\d)?$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("可输入数字，含一位小数")));
            return;
        }
        //可以输入数字
        //^([1-9]+|[1-9]\d{0,7}\.?\d{1,2}|0?\.0[1-9]|0?\.[1-9][1-9]?|0)$ 
        if (textBox.hasClass("IsSmallText2") && $.trim(textBox.val()).length > 0 && !/^(?=([0-9]{1,7}$|[0-9]{1,7}\.))(0|[1-9][0-9]*)(\.[0-9]{1,2})?$/.test(textBox.val())) {            
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("只能输入7位正整数，可含两位小数")));
            return;
        }

        //可以输入数字
        //^([1-9]+|[1-9]\d{0,7}\.?\d{1,2}|0?\.0[1-9]|0?\.[1-9][1-9]?|0)$ 
        if (textBox.hasClass("IsTuition") && $.trim(textBox.val()).length > 0 && !/^(?=([0-9]{1,10}$|[0-9]{1,10}\.))(0|[1-9][0-9]*)(\.[0-9]{1,2})?$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("只能输入10位正整数，可含两位小数")));
            return;
        }

        //验证是不包含特殊字符
        if (textBox.hasClass("IsSpecialText") && $.trim(textBox.val()).length > 0 && /^[^\/'']*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，不允许输入特殊字符")));
            return;
        }
        
       
        //不能输入<>特殊字符   :用于树上使用
        if (textBox.hasClass("IsTreeName") && $.trim(textBox.val()).length > 0 && (!/^[^<>]+$/.test(textBox.val()))) {
        	textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，不允许输入<>")));
            return;
        }
        
        //不能输入<>“”""''特殊字符   :用于避免前端页面显示出错或不全
        if (textBox.hasClass("IsOrleName") && $.trim(textBox.val()).length > 0 && (!/^[^<>“”""'']+$/.test(textBox.val()))) {
        	textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，不允许输入<>及引号")));
            return;
        }
        
      //验证禁止输入特殊字符
        if (textBox.hasClass("IsProSpecialText") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9fff]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确，不允许输入特殊字符")));
            return;
        }
        
        
        //验证输入的日期格式是否正确：判断输入框中输入的日期格式为yyyy-mm和正确的日期 
       if(textBox.hasClass("IsSpecialData") && $.trim(textBox.val()).length > 0 
    		   && !/^(\d{4})-(\d{2})$/.test(textBox.val()))
       {
    		   textBox.after(msgCon.append(msgTip.css({
                   left: -textBox.outerWidth() + 'px',
                   top: textBox.outerHeight() + 'px'
               }).text("日期格式不正确,请输入yyyy-MM")));
               return;
       }
  
         
        //验证传真、座机
        if (textBox.hasClass("IsTelephone") && $.trim(textBox.val()).length > 0 && !/^[0-9-()]{8,20}$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,可由8-20位的数字及()-组成")));
            return;
        }

        //验证手机或座机
        if (textBox.hasClass("IsPhone") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()) != "" && !/(^([0-9]{3,4}\-)?[0-9]{7,8}$)|(^(\+86)?1[0-9]{10}$)/.test($.trim(textBox.val()))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("电话格式不正确，如：【13625684653】【0755-7125814】")));
            return;
        }

        //验证身份证
        if (textBox.hasClass("IsIdCard") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()) != "" && !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("身份证格式不正确，如：430624198511247897")));
            return;
        }

        if (textBox.hasClass("IsIdCardBirthday") && $.trim(textBox.val()).length > 0 && $.trim(textBox.val()) != "") {
            var num = $.trim(textBox.val());
            var re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
            var arrSplit = num.match(re);
            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text("身份证日期格式不正确，如：430624198511247897")));
                return;
            }
        }


        //验证邮政编码
        if (textBox.hasClass("IsPostCode") && $.trim(textBox.val()).length > 0 && !/^[0-9]{6}$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("邮政编码格式不正确，可由6位数字组成")));
            return;
        }


        //验证IP
        if (textBox.hasClass("IsIP") && $.trim(textBox.val()).length > 0 && !/^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("IP地址格式不正确，如192.168.100.5")));
            return;
        }

        //验证正则表达式
        if (textBox.hasClass("IsReg") && $.trim(textBox.val()).length > 0 && !new RegExp("^" + textBox.attr("Reg") + "$").test($.trim(textBox.val()))) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgReg"))));
            return;
        }

        //验证下拉框“请选择”
        if (textBox.hasClass("IsSelect") && (textBox.find("option:selected").text() == "--请选择--")) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgReg"))));
            return;
        }

        //验证多个文本框的内容是否相等
        if (textBox.hasClass("IsTextCompare")) {
            var msgs = $(".IsTextCompare:visible");
            var msg1 = msgs[0];
            if ($(msg1).val() != textBox.val() && $(msg1).attr("id") != textBox.attr("id")) {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(textBox.attr("MsgName") + "填写不一致，请重新输入")));
                return;
            }
        }
        
        if (textBox.hasClass("IsTextComparePass")) {
            var msgs = $(".IsTextComparePass:visible");
            var msg1 = msgs[0];
            if ($(msg1).val() != textBox.val() && $(msg1).attr("id") != textBox.attr("id")) {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text("两次密码填写不一致，请重新输入")));
                return;
            }
        }

        //验证验证码是否正确
        if (textBox.hasClass("IsVerificationCode")) {
            var inputCode = $.trim(textBox.val().toLowerCase());
            var generateCode = $.trim(document.cookie.toLowerCase());
            if (inputCode.length != 4 || generateCode.indexOf("checkcode=" + inputCode) < 1) {
                if (inputCode.length == 0) {
                    textBox.after(msgCon.append(msgTip.css({
                        left: -textBox.outerWidth() + 'px',
                        top: textBox.outerHeight() + 'px'
                    }).text(textBox.attr("MsgName") + "为空，请重新输入")));
                }
                else {
                    textBox.after(msgCon.append(msgTip.css({
                        left: -textBox.outerWidth() + 'px',
                        top: textBox.outerHeight() + 'px'
                    }).text(textBox.attr("MsgName") + "错误，请重新输入")));
                }
                return;
            }
        }
        //验证数据唯一性
        if (textBox.hasClass("IsRemote")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已经存在，请重新输入";
            var data = {};
            
            /* modified by YTT at 2016-04-06 begin */
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
                for (var i = 0; i < RemoteDataArray.length; i++) {
                	data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            /* modified by YTT at 2016-04-06 end */
            
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (response) {
                        var valid = response === true || response === "true";
                        if (!valid) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(romotePrompt)));
                            return;
                        }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }
        
        //验证年级是否被占用
        if (textBox.hasClass("IsRemoteGrade")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已被占用";
            var data = {};
            
            /* modified by YTT at 2016-04-06 begin */
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
                for (var i = 0; i < RemoteDataArray.length; i++) {
                	data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            /* modified by YTT at 2016-04-06 end */
            
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (response) {
                        var valid = response === true || response === "true";
                        if (!valid) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(romotePrompt)));
                            return;
                        }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }
        
        /**
         * @function 验证数据在数据库中是否存在
         * @author tingting.yang
         * @date 2016-04-27
         */
        if (textBox.hasClass("IsExistInDB")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
            var returnNo = textBox.attr("ReturnNo");//存返回的数据编号
            var romotePrompt = "验证失败";
            var data = {};
            
            /* modified by YTT at 2016-04-06 begin */
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
                for (var i = 0; i < RemoteDataArray.length; i++) {
                	data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            /* modified by YTT at 2016-04-06 end */
            
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (data) {
                    	var response = data.result;
                    	var dataNo = data.dataNo;                    	
                        if (response != "" && response != null) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(response)));
                            return;
                        } else {
                        	$("#"+returnNo).val(dataNo);
                        }
                    },
                    error: function (e) {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }
        
      //验证值在同一个字典下唯一
        if (textBox.hasClass("IsRemoteValue")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已经存在，请重新输入";
            var data = {};
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
            	var arr = "";
            	for (var i = 0; i < RemoteDataArray.length; i++) {
            		$("input[name="+RemoteDataArray[i]+"]").each(function(){
                    	arr += $(this).val()+",";
                    });
            		if(arr.length>0){
            			data[$.trim(RemoteDataArray[i])]=arr;
            		}else{
            			data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
            		}
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (response) {
                        var valid = response === true || response === "true";
                        if (!valid) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(romotePrompt)));
                            return;
                        }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }

      //验证名称值在同一个字典下唯一
        if (textBox.hasClass("IsRemoteName")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已经存在，请重新输入";
            var data = {};
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
            	var arr = "";
            	var name="";
            	$("input[name="+RemoteDataArray[1]+"]").each(function(){
                	arr += $(this).val()+",";
                });
            	name=$("input[name="+RemoteDataArray[0]+"]").val();
            	data[$.trim(RemoteDataArray[0])]=name;
            	data[$.trim(RemoteDataArray[1])]=arr;
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (response) {
                        var valid = response === true || response === "true";
                        if (!valid) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(romotePrompt)));
                            return;
                        }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }
        
        //add by xuemei.huang 2016.04.05:验证数据库中是否已经存在
        if (textBox.hasClass("IsCheckRemoteRepeat")) {
            var romoteUrl = textBox.attr("remoteUrl") ? textBox.attr("remoteUrl") : "";
            var romoteType = textBox.attr("remoteType") ? textBox.attr("remoteType") : "Post";
            var romoteName = textBox.attr("ckvalue") ? textBox.attr("ckvalue") : "";
            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已经存在，请重新输入";
            var data = {};
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
                for (var i = 0; i < RemoteDataArray.length; i++) {
                	data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (data) {
                    	//console.log(data);
                    	if(data.result)
        	            {
        	            	var iHave = data.havName;
        	            	if(iHave > 0)
        	            	{
        	            		 textBox.after(msgCon.append(msgTip.css({
                                     left: -textBox.outerWidth() + 'px',
                                     top: textBox.outerHeight() + 'px'
                                 }).text(romotePrompt)));
                                 return;
        	            	}
        	            }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text(romotePrompt)));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text(romotePrompt)));
                return;
            }
        }

        ////验证文本框数值的大小
        //if (textBox.hasClass("IsNumCompare")) {
        //    var msgs = $(".A");
        //    var msg1 = msgs[0];
        //    if ($(msg1).val() <= textBox.val() && $(msg1).attr("id") != textBox.attr("id")) {
        //        textBox.css({ "background-color": "#fff2f2", "border-color": "#ff8080" });
        //        textBox.after("<font class='help-inline' style = 'position:absolute; z-index:101;width:20px;line-height:" + $(msg1).outerHeight(true) + "px;text-align: initial'>" +
        //                        "<img style ='position: relative; top: 2px; left: 3px' title ='" + textBox.attr("MsgName") + "的值不能大于" + msg1.attr("MsgName") + "' src = '" + errorImg + "'/></font>");
        //        //textBox.closest("div").css("position", "relative");
        //        return;
        //    }
        //}

        //判断上传文件的格式  列:filetype ="jpg/png/gif"
        if (textBox.attr("type") == "file" && textBox.attr("FileType") != undefined && textBox.attr("FileType") != "") {        	
        	var fileType = textBox.val().substr(textBox.val().lastIndexOf('.') + 1);
        	if(textBox.attr("TargetFileTips") != undefined && textBox.attr("TargetFileTips") != ""){
        		$("#" + textBox.attr("TargetFileTips")).next(".help-inline").remove();
        	}
            if (textBox.attr("FileType").indexOf(fileType.toLowerCase()) == -1) {
            	if(textBox.attr("TargetFileTips") != undefined && textBox.attr("TargetFileTips") != ""){
            		var targetFileTips = $("#" + textBox.attr("TargetFileTips"));
            		targetFileTips.after(msgCon.append(msgTip.css({
            			left: -targetFileTips.outerWidth() + 'px',
                        top: targetFileTips.outerHeight() + 'px'
                    }).text("支持" + textBox.attr("FileTips") + "文件")));
            	} else {
                    textBox.after(msgCon.append(msgTip.css({
                        left: -textBox.outerWidth() + 'px',
                        top: textBox.outerHeight() + 'px'
                    }).text("文件格式不正确，请选择格式为" + textBox.attr("FileType") + "的文件")));
            	}
            }
            
            /*if (textBox.attr("FileType").indexOf(fileType.toLowerCase()) == -1) {alert(textBox.attr("FileType")+">>"+fileType.toLowerCase());
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text("文件格式不正确，请选择格式为" + textBox.attr("FileType") + "的文件")));

            }*//* else {
                var img = new image();
                img.src = fileVal;
                alert(img.fileSize);
                var img = document.createElement("img");
                img.src = $(obj).val();
                var filesize = img.fileSize;
                img.onload = function () {
                    filesize = this.fileSize;
                }
                if (img.fileSize > 5242880) {
                    alert("图片文件过大！");
                }
                var imgObjPreview = $("#" + imgID);
                if (obj.files && obj.files[0]) {
                    imgObjPreview.css("display", "block");
                    imgObjPreview.src = window.URL.createObjectURL(obj.files[0]);
                } else {
                    obj.select();
                    var imgSrc = document.selection.createRange().text;
                    var localImagId = document.getElementById(divID);
                    localImagId.style.width = "155px";
                    localImagId.style.height = "90px";
                    try {
                        localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                        localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                    } catch (e) {
                        alert("对不起，数据传送出错！");
                        return false;
                    }
                    imgObjPreview.css("display", "none");
                    document.selection.empty();
                }
            }*/
        }
        
        //验证特定(班名)的文本规则   中英文、数字及()（）-
        if (textBox.hasClass("IsClassName") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9\u4e00-\u9FA5()（）-]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,可由中英文、数字或者()（）-组成")));
            return;
        }    
        
      //验证是否为正整数
        if (textBox.hasClass("IsInteger") && $.trim(textBox.val()).length > 0 && !/^[0-9]{1}[\d]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "必须为整数")));
            return;
        }    
        
        //验证是否为分数（保留一位小数）
        if (textBox.hasClass("IsScore") && !/^([1-9]\d|\d)$|^([1-9]\d|\d)(\.\d)$/.test(textBox.val())) {
        	textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text(textBox.attr("MsgName") + "为99以内的数，不包含负数")));
            return;
        }  
        
        //验证字符+数字（账号）
        if(textBox.hasClass("IsAccountNo") && $.trim(textBox.val()).length > 0 && !/^[A-Za-z0-9_]+$/.test(textBox.val())){
        	 textBox.after(msgCon.append(msgTip.css({
                 left: -textBox.outerWidth() + 'px',
                 top: textBox.outerHeight() + 'px'
             }).text(textBox.attr("MsgName") + "输入格式不正确,可由英文和数字组成")));
             return;
        }
        
      //验证特定的文本规则   中英文、数字、_及空格
        if (textBox.hasClass("IsUserName") && $.trim(textBox.val()).length > 0 && !/^[a-zA-Z0-9_\u4e00-\u9fff ]*$/.test(textBox.val())) {
            textBox.after(msgCon.append(msgTip.css({
                left: -textBox.outerWidth() + 'px',
                top: textBox.outerHeight() + 'px'
            }).text("输入格式不正确,可由中文、英文、_及空格组成")));
            return;
        }
        
        //验证学期名称不能为空
        if (textBox.hasClass("IsSchYear")) {
        	if($(".schYear").val()==""){
	            textBox.after(msgCon.append(msgTip.css({
	                left: -textBox.outerWidth() + 'px',
	                top: textBox.outerHeight() + 'px'
	            }).text("请选择学年名称")));
        	}else{
        		if($.trim(textBox.val()) == "" || textBox.val() == undefined || textBox.val() == textBox.attr("ShowValue") || textBox.val() == textBox.attr("defaulvalue") || textBox.val() == textBox.attr("DefaultValue")){
        		 textBox.after(msgCon.append(msgTip.css({
                     left: -textBox.outerWidth() + 'px',
                     top: textBox.outerHeight() + 'px'                    
                 }).text(textBox.attr("MsgName") + "不能为空")));
        		}
        	}
        	  return;
        }
        
        
        //验证学期是否被占用
        if (textBox.hasClass("IsRemoteTerm")) {
            var romoteUrl = textBox.attr("RemoteUrl") ? textBox.attr("RemoteUrl") : "";
            var romoteType = textBox.attr("RemoteType") ? textBox.attr("RemoteType") : "Post";
            var romoteName = textBox.attr("Name") ? textBox.attr("Name") : "";
//            var romotePrompt = textBox.attr("prompt")? textBox.attr("prompt") : textBox.attr("MsgName") + "已被占用";
            var data = {};
            
            /* modified by YTT at 2016-04-06 begin */
            var RemoteData = textBox.attr("RemoteData") ? textBox.attr("RemoteData") : "";
            if(RemoteData.length > 0){
            	var RemoteDataArray = RemoteData.split(",");
                for (var i = 0; i < RemoteDataArray.length; i++) {
                	data[$.trim(RemoteDataArray[i])] = $.trim($("#"+$.trim(RemoteDataArray[i])).val());
                }
            } else {
            	data[romoteName] = $.trim(textBox.val());
            }
            /* modified by YTT at 2016-04-06 end */
            
            var validationResult = false;
            if (romoteUrl != "") {
                $.ajax({
                    url: romoteUrl,
                    dataType: "json",
                    data: data,
                    type: romoteType,
                    async: false,
                    success: function (response) {
                        if (response.result.length>0) {
                            textBox.after(msgCon.append(msgTip.css({
                                left: -textBox.outerWidth() + 'px',
                                top: textBox.outerHeight() + 'px'
                            }).text(response.result)));
                            return;
                        }
                    },
                    error: function () {
                        textBox.after(msgCon.append(msgTip.css({
                            left: -textBox.outerWidth() + 'px',
                            top: textBox.outerHeight() + 'px'
                        }).text("学期被占用")));
                        return;
                    }
                });
            } else {
                textBox.after(msgCon.append(msgTip.css({
                    left: -textBox.outerWidth() + 'px',
                    top: textBox.outerHeight() + 'px'
                }).text("学期被占用")));
                return;
            }
        }
        
//        
        
    }
    
    //单选多选验证必填
    if(textBox.attr("type") == "checkbox" || textBox.attr("type") == "radio"){
    	var name = textBox.attr("name");
    	var type = textBox.attr("type");
    	var msgBd = textBox.attr("msgBd");//错误信息版绑定显示元素
    	if(textBox.hasClass("IsRequired") && name && msgBd){
    		//处理多选
    		if(type == "checkbox"){
    			if($('input[type=checkbox][name='+name+']:checked').length == 0){
    				$("#"+msgBd).html(msgCon.append(msgTip.css({
    	                left: -textBox.outerWidth() + 'px',
    	                top: textBox.outerHeight() + 'px'
    	            }).text(textBox.attr("MsgName") + "为必选项")))
    	            return;
    			}else{
    				$("#"+msgBd).html("");
    			}
    		}
    		//处理单选
    		if(type == "radio"){
    			if(!$('input[type=radio][name='+name+']').is(':checked')){
    				$("#"+msgBd).html(msgCon.append(msgTip.css({
    	                left: -textBox.outerWidth() + 'px',
    	                top: textBox.outerHeight() + 'px'
    	            }).text(textBox.attr("MsgName") + "为必选项")))
    	            return;
    			}else{
    				$("#"+msgBd).html("");
    			}
    		}
    		
    	}
    }
}


//获取字符串长度

function getStrLen(strSrc) {
    return strSrc.replace(/[^\x00-\xff]/g, 'xx').length;
}

//整体验证方法
function submitClick(content) {
    timeout = 0;
    if (content == "" || content == undefined) {
        $(".IsError").blur();
        if ($(".help-inline").length > 0) {
            timeout = 120;
            return false;
        }

    } else {
        $(".IsError", "#" + content).blur();
        if ($(".help-inline", "#" + content).length > 0) {
            timeout = 120;
            return false;
        }
    }
    return true;
}

function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    // webSite里有虚拟目录，WebApplication里没虚拟目录
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
        return (prePath + postPath);
   // return prePath;
}

function FormatBaseUrl(Url) {
    var basePath = getRootPath();
    var baseUrl = basePath + Url;
    return baseUrl;
}