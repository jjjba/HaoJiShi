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
     var okOr = sessionStorage.getItem("ZhuCeOk");
    /* if(okOr == "OK"){

     }*/
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )){
        $.ajax({
            url:"/company/DengLuPuanDuan",
            type:"POST",
            data:{"zt":zt,"phone":phone,"pwd":pwd},
            success:function (msg) {
                if(msg.dataOne == 1){
                    $("#wyzgz").html("我要找工作"+"<img src='../../company/images/yjts.png' />");
                }
                if(msg.dataOne == 2){
                    $("#wyzgz").html("我要招人"+"<img src='../../company/images/yjts.png' />");
                }
                var htm = "<div class='sbtopxxs'><div class='sblefs'><img src= '";
                if(msg.data.iconPath == "" || msg.data.iconPath == ''|| msg.data.iconPath== undefined){
                    htm +="../../company/images/tuui.png";
                }else{
                    htm += msg.data.iconPath;
                }

                htm += "'/></div><div class='sbyours fbzhiuse'><div class='lylyus'>";
                htm+=msg.data.name;
                htm+="</div>"

                var Obj = msg.data
                if(msg.code == 1){
                    htm+="<div class='dcirzs'><a href='#' onclick='RenZheng()'>点此认证</a></div><a href='#' onclick='Fbzw()' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 2){
                    htm+="<div class='dcirzs'><a href='#'>认证中</a></div><a href='#' onclick='Fbzw()' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 3){
                    htm+="<div class='dcirzs'><a href='#'>未通过，请重新认证</a></div><a href='#' onclick='Fbzw()' class='fbzuysew'>发布职位</a></div></div>";
                }
                if(msg.code == 4){
                    htm+="<div class='dcirzs yrziis'><a href='#'>已认证</a></div><a href='#' onclick='Fbzw()' class='fbzuysew'>发布职位</a></div></div>";
                }
                $("#wode").html(htm);
            }
        })
    }
   else {
        var htm ="<div class='sbtopxxs bounone'><div class='sblefs'><img src='../../company/images/tuui.png'/></div><div class='sbyours fbzhiuse'><div class='lylyus'><a href='#'onclick='zongZ();' class='dlzces'>登录/注册</a></div></div></div>";
        $("#wode").html(htm);
        $(".WsXX").css("display","none");
    }
})

function Fbzw() {
    window.location.href="/transition/bianji_zhiwei";
}
function zong() {
    $('.jinggao').show();
    setTimeout('$(".diyici").hide()',1000);
    window.location.href="/transition/go_zhu_ce";
}
function zongZ() {
    window.location.href="/transition/go_zhu_ce";
}
function wodezhanghaoshezhi() {
    if(zt != 1){
        zong();
    }else {
        window.location.href = "/transition/wo_de_zhang_hao_she_zhi";
    }
}
function wodezhiweiguanli() {
    if(zt != 1){
        zong();
    }else {
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

}
function woderencaishoucang() {
    window.location.href="/transition/wo_de_ren_cai_shou_cang";
    if(zt != 1){
        zong();
    }
}
function wodechangjianwenti() {

    if(zt != 1){
        zong();
    }else {
        window.location.href="/transition/wo_de_chang_jian_wen_ti";
    }
}
function gotozhiwei() {
        window.location.href="/transition/go_qiu_zhi_zhe";

}
function gotoshouye() {
        window.location.href="/transition/go_company_index";
}
function wodeqiehuanshenfen(){
    if(zt != 1){
        zong();
    }else {
        window.location.href="/transition/wo_de_qie_huan_shen_fen";
    }
}

function wodejianliguanli(){
        if(zt != 1){
            zong();
        }else {
            window.location.href="/transition/wo_de_jian_li_guan_li";
        }
}
function RenZheng() {
    window.location.href="/transition/RenZheng"
}
function kuaizhao() {
    window.location.href="/transition/kuaizhao"
}
