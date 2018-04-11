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
    var xggstp = sessionStorage.getItem("xggstp");
    //店铺图片LOGO
    if(xgLogog!=null){
        $(".corpzuos").html("<image src='"+xgLogog+"'>");
    }else {
        $(".corpzuos").html("<image src='../../company/images/tuui.png'>");
    }
    //第一个
    var htm = "<h1>";
    htm += xgdpmc;
    htm +="</h1><p>";
    htm+=xgdplx;
    htm+=" | ";
    htm+=xgdpmj;
    htm+= " | ";
    htm+=xgcityname;
    htm+="</p>";
    $(".corpryous").html(htm);
    //第二个
    var htm1 = "</span>";
    var xgdpfl1 = xgdpfl.substring(0,xgdpfl.length-1);
    var att = new Array();
    att = xgdpfl1.split(",");
    for(var i =0;i<att.length;i++){
        htm1+="<span>";
        htm1+=att[i];
        htm1+="</span>"
    }
    htm1+="</span>";
    $(".xblbyos").html(htm1);
    //第三个
    $(".wzquys").html(xgjj);
    //图片
    var htm2 = "";
    if(xggstp != null){
        var xggstp1 = xggstp.substring(0,xggstp.length-1);
        var att = new Array();
        att = xggstp1.split(",");
        for(var i =0;i<att.length;i++){
            htm2+="<li data-src='";
            htm2+=att[i];
            htm2+="'><img src='";
            htm2+=att[i];
            htm2+="' /></li>"
        }
        $("#lightgallery").html(htm2);
    }

    //职位列表
    $.ajax({
        url: "/company/getZhiWeiALL",
        type: "POST",
        success: function (msg) {
            console.log(msg);
            var company = msg.data;
            var position = msg.dataOne;
            console.log(msg);
        }
    })
})