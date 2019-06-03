//监听考试结束的错误请求
$(document).ajaxError(function(event,request, settings){
    var responseJSON=request.responseText;
    try  {
        responseJSON=eval('('+request.responseText+')');
        if(responseJSON !=undefined && responseJSON.status=="560"){
            layer.alert('考试已被提前结束，请点击确定关闭页面！', {
                skin: 'layer-ext-moon',closeBtn: 0,icon:3
            }, function (index) {
                closewin();
                layer.close(index);
            });
        }
    }catch(exception) {
        if(responseJSON !=undefined && responseJSON.indexOf("status=560") !=-1){
            layer.alert('考试已被提前结束，请点击确定关闭页面！', {
                skin: 'layer-ext-moon',closeBtn: 0,icon:3
            }, function (index) {
                closewin();
                layer.close(index);
            });
        }
    }
});