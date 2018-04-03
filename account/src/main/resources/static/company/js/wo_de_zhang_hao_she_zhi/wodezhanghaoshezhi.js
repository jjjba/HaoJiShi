$(document).ready(function() {
    $.ajax({
        type:"post",
        url:"../company/updatePhoneNu",
        dataType:"json",
        success:function (msg) {
            var PhoneNum = msg.data;
            console.log("-------"+PhoneNum.password);
            if(PhoneNum.password!=undefined && PhoneNum.password!='' && PhoneNum.password !=""){
                $("#shezhiliId").html("<a href='#'' class='clearfix' onclick='xiugaimima()'><div class='fl zbiuus01'>修改密码</div><div class='fr zbiuus02'><img src='../../company/images/yjts.png' /></div></a>")
            }else{
                $("#shezhiliId").html("<a href='#'' class='clearfix' onclick='shezhimima()'><div class='fl zbiuus01'>设置密码</div><div class='fr zbiuus02'><img src='../../company/images/yjts.png'/></div></a>")
            }
        }
    })
});
function fanhui() {
    window.location.href="../transition/go_wo_de";
}
function xiugaishoujihao() {
    window.location.href="../transition/wo_de_xiu_gai_shou_ji_hao";
}
function xiugaimima() {
    window.location.href="../transition/wo_de_xiu_gai_mi_ma";
}
function shezhimima() {
    window.location.href="../transition/wo_de_she_zhi_mi_ma";
}
