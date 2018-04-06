var zt;
var phone;
var pwd;
$(document).ready(function() {
     zt = $.cookie("zt");
     phone = $.cookie("phone");
     pwd = $.cookie("pwd");
     console.log(zt);
     console.log(phone);
     console.log(pwd);
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )){
        $.ajax({
            url:"/company/DengLuPuanDuan",
            type:"POST",
            success:function (msg) {
                /*console.log(msg.code);*/
                var htm = "<div class='sbtopxxs'><div class='sblefs'><img src='";
                htm += "msg.data.iconPath";
                htm += "'/></div><div class='sbyours fbzhiuse'><div class='lylyus'>";
                htm+=msg.data.name;
                htm+="</div>"

                var Obj = msg.data
                if(msg.code == 0){
                    window.location.href="/transition/go_wo_de";
                }
                if(msg.code == 1){
                    htm+="<div class='dcirzs'><a href='#'>未认证</a></div><a href='#' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 2){
                    htm+="<div class='dcirzs'><a href='#'>认证中</a></div><a href='#' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 3){
                    htm+="<div class='dcirzs'><a href='#'>未通过，请重新认证</a></div><a href='#' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 4){
                    htm+="<div class='dcirzs yrziis'><a href='#'>已认证</a></div><a href='#' class='fbzuysew'>发布职位</a></div></div>";
                }
                $("#wode").html(htm);
            }
        })
        /*$.ajax({
            url:"/company/wodeXuanze",
            type:"POST",
            data:{phone:phone},
            success:function (msg) {
                var htm = "<div class='sbtopxxs'><div class='sblefs'><img src='";
                    htm += "msg.data.iconPath";
                    htm += "'/></div><div class='sbyours fbzhiuse'><div class='lylyus'>";
                    htm+=msg.data.name;
                    htm+="</div><div class='dcirzs'><a href='#'>点此认证</a></div><a href='#' class='fbzuysew'>发布职位</a></div></div>";
                    $("#wode").html(htm);
            }
        })*/
    }
    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        var htm ="<div class='sbtopxxs bounone'><div class='sblefs'><img src='../../company/images/tuui.png'/></div><div class='sbyours fbzhiuse'><div class='lylyus'><a href='#' class='dlzces'>登录/注册</a></div></div></div>";
        $("#wode").html(htm);
    }
})
function zong() {
    $('.jinggao').show();
    setTimeout('$(".diyici").hide()',1000);
    window.location.href="/transition/go_zhu_ce";
}
function wodezhanghaoshezhi() {
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )) {
        window.location.href = "/transition/wo_de_zhang_hao_she_zhi";
    }
    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }
}
function wodezhiweiguanli() {
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )) {
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
    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }

}
function woderencaishoucang() {
    window.location.href="/transition/wo_de_ren_cai_shou_cang";
    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }
}
function wodechangjianwenti() {
    window.location.href="/transition/wo_de_chang_jian_wen_ti";
    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }
}
function gotozhiwei() {
        window.location.href="/transition/go_qiu_zhi_zhe";

}
function gotoshouye() {
        window.location.href="/transition/go_company_index";
}
function wodeqiehuanshenfen(){
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )) {
        window.location.href="/transition/wo_de_qie_huan_shen_fen";
    }

    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }

}

function wodejianliguanli(){
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )) {
        window.location.href="/transition/wo_de_jian_li_guan_li";
    }

    if(zt==undefined || zt=="" || zt == '' || zt== 2){
        zong();
    }
}
