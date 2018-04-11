$(document).ready(function() {
    var Name = sessionStorage.getItem("Name");
    var dwmj =sessionStorage.getItem("Mj");
    var dwmc =sessionStorage.getItem("Dmmmmc");
    var dplx = sessionStorage.getItem("dplx");
    var zhiwei = sessionStorage.getItem("zhiwei");

    var dpfl = sessionStorage.getItem("Dpfl");
    var poiaddress = sessionStorage.getItem("poiaddress");
    var poiname = sessionStorage.getItem("poiname");

    var name = $(".xm").html();
    var mc = $(".mc").html();
    var mj = $(".mj").html();
    var num =0;
    if(Name != null){
        num++;
        $(".xm").html(Name +"<img src='../../company/images/yjts.png' class='yjtiss' />");
    }
    if(dwmj != null){
        $(".mj").html(dwmj +"<img src='../../company/images/yjts.png' class='yjtiss' />");
        num++;
    }
    if(dwmc != null){
        $(".mc").html(dwmc +"<img src='../../company/images/yjts.png' class='yjtiss' />");
        num++;
    }
    if(dplx != null){
        num++;
        $("#trigger3").html(dplx);
    }
    if(zhiwei != null){
        num++;
        $("#trigger2").html(zhiwei);
    }
    if(poiaddress !=null && poiname !=null){
        num++;
        $(".Dpdz").html((poiaddress+poiname).substring(0,13)+"..."+"<img src='../../company/images/yjts.png' class='yjtiss' />");
    }
    if(dpfl != null){
        num++;
        $(".dpfl").html(dpfl.substring(0,15)+"..."+"<img src='../../company/images/yjts.png' class='yjtiss'/>");
    }
})

function Address() {
    window.location.href="/transition/getAddress";
}
function Dname() {
    window.location.href="/transition/BossName";
}
function dianpuName() {
    window.location.href="/transition/dianpuName";
}
function dianpuMj() {
    window.location.href="/transition/dianpuMj";
}
function dpfl() {
    window.location.href="/transition/dpfl";
}
function xinxibaocun() {
    var num = 0;
    var Name = sessionStorage.getItem("Name");
    var dwmj =sessionStorage.getItem("Mj");
    var dwmc =sessionStorage.getItem("Dmmmmc");
    var dplx = sessionStorage.getItem("dplx");
    var zhiwei = sessionStorage.getItem("zhiwei");
    var dpfl = sessionStorage.getItem("Dpfl");
    var cityname = sessionStorage.getItem("cityname");
    var lat = sessionStorage.getItem("lat");
    var lng = sessionStorage.getItem("lng");
    var poiaddress = sessionStorage.getItem("poiaddress");
    var poiname = sessionStorage.getItem("poiname");
    var phone = sessionStorage.getItem("phone");
    if(Name!=null && dwmj!=null &&dwmc!=null && dplx!=null && zhiwei!=null &&dpfl!=null &&cityname!=null && poiaddress!=null && poiname!=null){
        num = 7;
    }
    if(num == 7){
        $.ajax({
            url:"/company/addNewCompany",
            type:"POST",
            data:{Name:Name,dwmj:dwmj,dwmc:dwmc,dplx:dplx,zhiwei:zhiwei,dpfl:dpfl,cityname:cityname,lat:lat,lng:lng,poiaddress:poiaddress,poiname:poiname,phone:phone},
            success:function (msg) {
                var succ = msg.data;
                $.cookie("zt",1);
                $.cookie("phone",phone);
                sessionStorage.setItem("ZhuCeOk","OK");
                window.location.href="/transition/go_wo_de";
            }
        })
    }else{
        $(".quanbubitian").show();
        setTimeout('$(".quanbubitian").hide()',1000);
    }

}