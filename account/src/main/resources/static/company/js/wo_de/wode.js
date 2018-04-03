function wodezhanghaoshezhi() {
    window.location.href="/transition/wo_de_zhang_hao_she_zhi";
}
function wodezhiweiguanli() {
    console.log("开始执行");
    $.ajax({
        url:"/company/getCompanyOkorFalse",
        type:"POST",
        success:function (msg) {
            console.log("开始执行-----");
            console.log(msg.data);
            if(msg.data.matstate ==1){
                window.location.href="/transition/zhiweiguanlirenzhneg";
            }else {
                window.location.href="/transition/zhiweiguanliweirenzheng";
            }
        }
    })

}
function woderencaishoucang() {
    window.location.href="/transition/wo_de_ren_cai_shou_cang";
}
function wodechangjianwenti() {
    window.location.href="/transition/wo_de_chang_jian_wen_ti";
}
function gotozhiwei() {
    window.location.href="/transition/go_qiu_zhi_zhe";
}
function gotoshouye() {
    window.location.href="/transition/go_company_index";
}
function wodeqiehuanshenfen(){
    window.location.href="/transition/wo_de_qie_huan_shen_fen";
}

function wodejianliguanli(){
    window.location.href="/transition/wo_de_jian_li_guan_li";
}
