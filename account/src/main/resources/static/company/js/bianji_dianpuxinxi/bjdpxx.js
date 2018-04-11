$(document).ready(function() {
    $.ajax({
        url:"/company/Bjdpxx",
        type:"POST",
        success:function (msg) {
            /*sessionStorage.setItem("xgxm",msg.data.userName);
            console.log("1---"+msg.data.userName);
            sessionStorage.setItem("xgzw",msg.data.zhiWu);
            console.log("2---"+msg.data.zhiWu);
            sessionStorage.setItem("xgLogo",msg.data.iconPath);
            console.log("3---"+msg.data.iconPath);
            sessionStorage.setItem("xgdpmc",msg.data.name);
            console.log("4---"+msg.data.name);
            sessionStorage.setItem("xgdpmj",msg.data.companyDpmj);
            console.log("5---"+msg.data.companyDpmj);
            sessionStorage.setItem("xgdpfl",msg.data.companySpecial);
            console.log("6---"+msg.data.companySpecial);
            sessionStorage.setItem("xgdpdz",msg.data.companyAddr);
            console.log("7---"+msg.data.companyAddr);
            sessionStorage.setItem("xgdplx",msg.data.companyType);
            console.log("8---"+msg.data.companyType);
            sessionStorage.setItem("xgid",msg.data.id);
            console.log("6---"+msg.data.id);
            sessionStorage.setItem("xggstp",msg.data.companyPhoto);
            console.log("7---"+msg.data.companyPhoto);
            sessionStorage.setItem("xgjj",msg.data.companyInfo);
            console.log("8---"+msg.data.companyInfo);*/
            /*上下分割*/
            /*var xgxm = sessionStorage.getItem("xgxm");
            console.log(xgxm);
            var xgzw = sessionStorage.getItem("xgzw");
            console.log(xgzw);
            var xgLogog = sessionStorage.getItem("xgLogo");
            console.log(xgLogog);
            var xgdpmc = sessionStorage.getItem("xgdpmc");
            console.log(xgdpmc);
            var xgdpmj = sessionStorage.getItem("xgdpmj");
            console.log(xgdpmj);
            var xgdpfl = sessionStorage.getItem("xgdpfl");
            console.log(xgdpfl);
            var xgdpdz = sessionStorage.getItem("xgdpdz");
            console.log(xgdpdz);
            var xgdplx = sessionStorage.getItem("xgdplx");
            console.log(xgdplx);
            var xgid = sessionStorage.getItem("xgid");
            console.log(xgid);
            var xggstp = sessionStorage.getItem("xggstp");
            console.log(xggstp);
            var xgjj = sessionStorage.getItem("xgjj");
            console.log(xgjj);*/

            $(".xgxm").html(msg.data.userName);
            $("#trigger2").html(msg.data.zhiWu+"&nbsp;&nbsp;");
            if(msg.data.iconPath == null || msg.data.iconPath =='' || msg.data.iconPath ==""){
                var htm="上传店铺logo，提升企业形象 ";
                htm+="<img src='../../company/images/yjts.png' class='yjtiss' />";
                $(".xgLogog").html(htm);
            }else {
                var htm = "<img src='";
                htm+=xgLogog;
                htm+="' class='logosis' /><img src='../../company/images/yjts.png' class='yjtiss' />"
                $(".xgLogog").html(htm);
            }
            $(".xgdpmc").html(msg.data.name);
            $(".xgdpmj").html(msg.data.companyDpmj);
            $(".xgdpfl").html((msg.data.companySpecial).substring(0,13)+"...");
            $(".xgdpdz").html((msg.data.companyAddr).substring(0,13)+"...");
            $("#trigger3").html(msg.data.companyType);

            if(msg.data.companyInfo == null || msg.data.companyInfo =="" || msg.data.companyInfo == ''){
                $(".xgjj").html("完善店铺简介，招聘效果提升50%");
            }else {
                $(".xgjj").html(msg.data.companyInfo);
            }
        }
    })


})

function bjxm() {
    window.location.href="/transition/bjxm";
}
function xgdpjj() {
    window.location.href="/transition/xgdpjj";
}
function xgdpdz() {
    window.location.href="/transition/xgdpdz";
}
function xgdpfl() {
    window.location.href="/transition/xgdpfl";
}
function xgdpmj() {
    window.location.href="/transition/xgdpmj";
}
function xgdpmc() {
    window.location.href="/transition/xgdpmc";
}
