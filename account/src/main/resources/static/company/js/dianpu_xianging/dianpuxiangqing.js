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
            var position = msg.dataOne;
            if(position !=null){
                var htm9 = "";
                for(var i =0;i<position.length;i++){
                    htm9+="<a href='#' class='kuisis'><div class='libjyos'><div class='lzuliuys'><h2>";
                    htm9+=position[i].positionType;
                    htm9+="</h2><p>";
                    htm9+=position[i].money;
                    htm9+="</p></div><div class='yomiyyys'>";
                    htm9+=position[i].experience;
                    htm9+="|";
                    htm9+=position[i].age;
                    htm9+="|";
                    htm9+=position[i].sex;
                    htm9+="<img src='../company/images/yjts.png' class='jyousso' /></div></div></a>";
                }
                console.log(htm9);
                $("#zhiweiList").html(htm9);
            }
            console.log(msg)
        }
    })
})