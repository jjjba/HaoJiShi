$(document).ready(function() {
    var xgxm = sessionStorage.getItem("xgxm");
    var xgzw = sessionStorage.getItem("xgzw");
    var xgLogog = sessionStorage.getItem("xgLogo");
    var xgdpmc = sessionStorage.getItem("xgdpmc");
    var xgdpmj = sessionStorage.getItem("xgdpmj");
    var xgdpfl = sessionStorage.getItem("xgdpfl");
    var xgdpdz = sessionStorage.getItem("xgdpdz");
    var xgdplx = sessionStorage.getItem("xgdplx");
    var xgid = sessionStorage.getItem("xgid");
    var xggstp = sessionStorage.getItem("xggstp");
    var xgjj = sessionStorage.getItem("xgjj");
    $(".xgxm").text(xgxm);
    $(".xgzw").text(xgzw);
    if(xgLogog != null && xgLogog !='' && xgLogog !=""){
        var htm = "<img src='";
        htm+=xgLogog;
        htm+="' class='logosis' /><img src='../../company/images/yjts.png' class='yjtiss' />"
        $(".xgLogog").html(htm);
    }else {
         var htm="上传店铺logo，提升企业形象";
        htm+="<img src='../../company/images/yjts.png' class='yjtiss' />";
        $(".xgLogog").html(htm);
    }
    $(".xgdpmc").text(xgdpmc);
    $(".xgdpmj").text(xgdpmj);
    $(".xgdpfl").text(xgdpfl.substring(0,13)+"...");
    $(".xgdpdz").text(xgdpdz.substring(0,13)+"...");
    $(".xgdplx").text(xgdplx);
    $(".xgjj").text(xgjj);

})