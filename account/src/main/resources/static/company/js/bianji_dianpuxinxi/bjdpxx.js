$(document).ready(function() {

            /*上下分割*/
            var xgxm = sessionStorage.getItem("xgxm");
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
            console.log(xgjj);

            var xgcityname = sessionStorage.getItem("xgcityname");
            var dptu = sessionStorage.getItem("dptp");
            $(".xgxm").html(xgxm);
            $("#trigger2").html(xgzw);
            if(!$.trim(xgLogog)){
                var htm="上传店铺logo，提升企业形象 ";
                htm+="<img src='../../company/images/yjts.png' class='yjtiss' />";
                $(".xgLogog").html(htm);
            }else {
                var htm = "<img src='";
                htm+=xgLogog;
                htm+="' class='logosis' /><img src='../../company/images/yjts.png' class='yjtiss' />"
                $(".xgLogog").html(htm);
            }
            $(".xgdpmc").html(xgdpmc);
            $(".xgdpmj").html(xgdpmj);
            $(".xgdpfl").html(xgdpfl.substring(0,13)+"...");
            $(".xgdpdz").html(xgdpdz.substring(0,13)+"...");
            $("#trigger3").html(xgdplx);

            if(xgjj == null || xgjj =="" || xgjj == ''){
                $(".xgjj").html("完善店铺简介，招聘效果提升50%");
            }else {
                $(".xgjj").removeClass("wtxyss");
                $(".xgjj").html(xgjj.substring(0,13)+"...");
            }
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
function dianpuYl() {
    window.location.href="/transition/dianpuYl";
}