var hidenFormat="{a} <br/>{b}:*** ({d}%)";
var showFormat="{a} <br/>{b}:{c} ({d}%)";
var myAssetEchartsData=[];
var myLiabilityEchartsData=[];
var myAssetTotal=0;
var myLiabilityTotal=0;
var myformat=showFormat;
var E00002_flag;

var ctime;
var countTime=300;
var countDownFlag=true;

function putdata(myEcharts,mydata,myformat){
    myEcharts.setOption({

        tooltip: {
            trigger: 'item',
            formatter:myformat,
            textStyle:{
                fontSize:12
            }
        },
        grid:{

        },
        series: [
            {
                name:'金额',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '12'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:mydata
            }
        ],
        color:['#8f90e5','#58bc92','#dc8d68','#cd72cd','#1eb6ff']
    });
    window.addEventListener("resize", function () {
        myEcharts.resize();
    });
}
//刷新资产负债的数据
function  freshAssetLiability(url,layer) {
    if(!countDownFlag){
        var m=parseInt(countTime/60);
        var s=countTime%60;
        layer.msg("您的刷新过于频繁,请于 "+m+"′"+s+"″ 重试");
        return;
    }
    countDownFlag=false;
    $.ajax({
        type:'GET',
        url:url,
        success:function (data) {
            if (data.status) {
                $("#update_time").text("当前数据截至: "+data.msg);
                myLiabilityTotal=0;
                myAssetTotal=0;
                myAssetEchartsData=[];
                myLiabilityEchartsData=[];
                $(data.obj).each(function (i, ele) {
                    var json={};
                    var money=Number(ele.money.toFixed(2));
                    if(i<5) {
                        myAssetTotal += money;
                        json.value = money;
                        json.name =ele.name;
                        myAssetEchartsData.push(json);
                    }else{
                        myLiabilityTotal += money;
                        json.value = money;
                        json.name =ele.name;
                        myLiabilityEchartsData.push(json);
                    }
                    $("#al_money_"+i).val(money.toFixed(2));
                });
                if (E00002_flag == "true") { //资产负债不显示
                    myformat=hidenFormat;
                }else{
                    showAssetLiabilityMoney();
                }
                putdata(assetEcharts,myAssetEchartsData,myformat);
                putdata(liabilityEcharts,myLiabilityEchartsData,myformat);
                countDown(countTime,countDownFlag);
            }else {
                countDownFlag=true;
                layer.msg(i18n[data.msg],{icon: 2});
            }
        },
        error:function () {
            layer.msg(i18n['sysError'], {icon: 3});
        }
    });
}
/**
 * 负债显示人民币
 */
function showAssetLiabilityMoney(){
    for(var i=0;i<7;i++){
        $("#al_money_span_"+i).text($("#al_money_"+i).val());
    }
    $("#my_asset_total").text(myAssetTotal.toFixed(2));
    $("#my_liability_total").text(myLiabilityTotal.toFixed(2));
    myformat=showFormat;
}

/**
 * 倒计时函数
 */
function countDown(counttime,countDownflag) {
    countDownFlag=countDownflag;
    countTime=counttime;
    if (--counttime > 0) {
        ctime = window.setTimeout("countDown(" + counttime +","+countDownflag+ ")", 1000);
    } else {
        window.clearTimeout(ctime)
        countDownFlag=true;
        countTime=300;
    }

}