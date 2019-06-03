/**
 * 验证码
 */
(function ($) {
    $.devfrw.verficationCode = {
        //yzmId 验证码div id ，yzmHiddenId 隐藏域input id
        init: function (yzmId,yzmHiddenId) {
            changeVcode(yzmId,yzmHiddenId);
        },
        //yzmId 验证码div id ，yzmHiddenId 隐藏域input id
        click:function(yzmId,yzmHiddenId){
            changeVcode(yzmId,yzmHiddenId);
        }

    };


})(jQuery);//直接复制 一定要;号

function rand(min,max) {
    var code = '';
    //设置长度，这里看需求，我这里设置了4
    var codeLength = 4;
    //设置随机字符
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R', 'S','T','U','V','W','X','Y','Z');
    //循环codeLength 我设置的4就是循环4次
    for(var i = 0; i < codeLength; i++){
        //设置随机数范围,这设置为0 ~ 36
        var index = Math.floor(Math.random()*36);
        //字符串拼接 将每次随机的字符 进行拼接
        code += random[index];
    }
    return code;
    // return Math.floor(Math.random()*(max-min))+min;
}
function changeVcode(yzmId,yzmHiddenId){
    var vcode= rand(1,2);
    $('#'+yzmId).html(vcode);
    $('#'+yzmHiddenId).val(vcode);
}