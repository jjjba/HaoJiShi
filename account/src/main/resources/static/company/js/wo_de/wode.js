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
     if(okOr == "OK"){
         $(".zccg").show();
         sessionStorage.setItem("ZhuCeOk","");
     }else {
         $(".zccg").hide();
     }
     if(phone == "" || phone =='' || phone == undefined || phone == null){
         zong();
     }
    if(zt == 1 && (phone!= undefined && phone !=''&& phone!="" )){
        $.ajax({
            url:"/company/DengLuPuanDuan",
            type:"POST",
            data:{"zt":zt,"phone":phone,"pwd":pwd},
            success:function (msg) {
                if(msg.dataOne == "个人用户"){
                    var htm ="<div class='sbtopxxs bounone'><div class='sblefs'><img src='../../company/images/tuui.png'/></div><div class='sbyours fbzhiuse'><div class='lylyus'><a href='#'onclick='wanshan();' class='dlzces'>请完善信息</a></div></div></div>";
                    $(".WsXX").css("display","none");
                    $(".sbtopxxs").html(htm);
                    $("#bjbj").text("个人用户");
                }else {

                    if(msg.dataOne == 1){
                        $("#wyzgz").html("我要找工作"+"<img src='../../company/images/yjts.png' />");
                    }
                    if(msg.dataOne == 2){
                        $("#wyzgz").html("我要招人"+"<img src='../../company/images/yjts.png' />");
                    }
                    var htm = "<div class='sblefs'><img src= '";
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
                        htm+="<div class='dcirzs yrziis'><a href='#'>已认证</a></div><a href='#' onclick='Fbzw()' class='fbzuysew'>发布职位</a></div>";
                    }
                    $(".sbtopxxs").html(htm);
                    console.log(msg.dataOne);
                }
            }
        })
    }
   else {
        var htm ="<div class='sbtopxxs bounone'><div class='sblefs'><img src='../../company/images/tuui.png'/></div><div class='sbyours fbzhiuse'><div class='lylyus'><a href='#'onclick='zongZ();' class='dlzces'>登录/注册</a></div></div></div>";
        $(".sbtopxxs").html(htm);
        $(".WsXX").css("display","none");
    }
})

function Fbzw() {
    window.location.href="/transition/bianji_zhiwei";
}
function zong() {
    $(".rndl").show();
}
function zongZ() {
    window.location.href="/transition/go_zhu_ce";
}
function wanshan() {
    window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
}
function wodezhanghaoshezhi() {
    if(zt != 1){
        zong();
    }else {
        if($("#bjbj").text()=="个人用户"){
            wanshan();
        }else {
            window.location.href = "/transition/wo_de_zhang_hao_she_zhi";
        }

    }
}
function wodezhiweiguanli() {
    if(zt != 1){
        zong();
    }else {
        if($("#bjbj").text()=="个人用户"){
            wanshan();
        }else {
            $.ajax({
                url: "/company/getCompanyOkorFalse",
                type: "POST",
                success: function (msg) {
                    console.log("开始执行-----");
                    console.log(msg.data);
                    if (msg.data.matstate == 1 || msg.data.matstate == 2 || msg.data.matstate == 3 || msg.data.matstate == 4) {
                        window.location.href = "/transition/zhiweiguanlirenzhneg";
                    }
                }
            })
        }
        }


}
function woderencaishoucang() {
    if(zt != 1){
        zong();
    }else {
        if($("#bjbj").text()=="个人用户"){
            wanshan();
        }else {
            window.location.href = "/transition/wo_de_ren_cai_shou_cang";
        }
    }
}
function wodechangjianwenti() {

    if(zt != 1){
        zong();
    }else {
        if($("#bjbj").text()=="个人用户"){
            wanshan();
        }else {
            window.location.href = "/transition/wo_de_chang_jian_wen_ti";
        }
    }
}
function gotozhiwei() {
    if($("#bjbj").text()=="个人用户"){
        wanshan();
    }else {
        window.location.href = "/transition/go_qiu_zhi_zhe";
    }

}
function gotoshouye() {
        window.location.href="/transition/go_company_index";
}
function wodeqiehuanshenfen(){
    if(zt != 1){
        zong();
    }else {
        if($("#bjbj").text()=="个人用户"){
            wanshan();
        }else {
            window.location.href = "/transition/wo_de_qie_huan_shen_fen";
        }
    }
}

function wodejianliguanli(){
        if(zt != 1){
            zong();
        }else {
            if($("#bjbj").text()=="个人用户"){
                wanshan();
            }else {
                window.location.href = "/transition/wo_de_jian_li_guan_li";
            }
        }
}
function RenZheng() {
        window.location.href="/transition/RenZheng";
}
function kuaizhao() {
    window.location.href="/transition/kuaizhao";
}
function wanshanCompany() {
    $.ajax({
        url:"/company/Bjdpxx",
        type:"POST",
        success:function (msg) {
            sessionStorage.setItem("xgxm",msg.data.userName);
            sessionStorage.setItem("xgzw",msg.data.zhiWu);
            if(msg.data.iconPath != null && msg.data.iconPath != "" && msg.data.iconPath != ''){
                sessionStorage.setItem("xgLogo",msg.data.iconPath);
            }
            sessionStorage.setItem("xgdpmc",msg.data.name);
            sessionStorage.setItem("xgdpmj",msg.data.companyDpmj);
            sessionStorage.setItem("xgdpfl",msg.data.companySpecial);
            sessionStorage.setItem("xgdpdz",msg.data.companyAddr);
            sessionStorage.setItem("xgdplx",msg.data.companyType);
            sessionStorage.setItem("xgid",msg.data.id);
            if(msg.data.companyPhoto != null && msg.data.companyInfo != null){
                sessionStorage.setItem("xggstp",msg.data.companyPhoto);
                sessionStorage.setItem("xgjj",msg.data.companyInfo);
            }
           window.location.href = "/transition/bianji_dianpuxinxi";
        }
    })

}
