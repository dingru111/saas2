/*银行卡号格式化，示例：6216 6169 0200 1150 00*/
function formatBankCode(str) {
    var text = str;
    var rtn = "";
    for (var i = 0; i < 5; i++) {
        rtn += text.substring(i * 4, i * 4 + 4) + " ";
    }
    return rtn;
}
