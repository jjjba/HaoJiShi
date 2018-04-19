$(document).ready(function () {
    $.ajax({
        type: "post",
        url: "/company/getRenCaishoucang",
        success: function (msg) {
            console.log(msg);
            var msh = msg.data;

            for (var i = 0; i < msh.length; i++) {
                var htm ="";
                htm+= "<div class='rcxinxis'><div class='rcktops' onclick='xiangqing(&quot;";
                htm+=msg.data[i].id;
                htm+="&quot;)'><div class='reclefs'><img src='";
                if(msg.data[i].avatar != null){
                    htm+=msg.data[i].avatar;
                }else {
                    if(msg.data[i].sex  == "男"){
                        htm+="../../company/images/tupian01.png";
                    }else{
                        htm+="../../company/images/tupian02.png";
                    }
                }
                htm+="'/></div><div class='recryous'><div class='clearfix rcvsise'>";
                if(msg.data[i].sex  == "男"){
                    htm+="<div class='fl rminsii'>";
                }
                if(msg.data[i].sex  == "女"){
                    htm+="<div class='fl rminsii02'>";
                }
                htm+=msg.data[i].name;
                htm+="<span>";
                if(msg.data[i].sex  == "男"){
                    htm+="<img src='../../company/images/biao05.png '>";
                }
                if(msg.data[i].sex  == "女"){
                    htm+="<img src='../../company/images/biao06.png '>";
                }

                htm+=msg.data[i].age;
                htm+="</span></div><div class='fr ciiuss'>";
                htm+=msg.data[i].expect_money;
                htm+="</div></div><div class='xnxyusie'> ";
                htm+=msg.data[i].job_experience;
                htm+=" | ";
                htm+=msg.data[i].state;
                htm+=" | ";
                htm+=msg.data[i].hope_city;
                htm+="</div></div></div><div class='pxbiuss'> <div class='pxlefts'>";
                htm+=msg.data[i].hope_job;
                htm+="</div><div class='pxyouls'><a href='tel:";
                htm+=msg.data[i].phone;
                htm+="'><img src='../../company/images/biao07.png' /></a></div></div></div>"
                $("#addid").append(htm);
            }

        }
    })
});

function renfan() {
    window.location.href = "/transition/go_wo_de";
}

function xiangqing(id) {
    console.log(id);
    sessionStorage.setItem("where","shoucang");
    sessionStorage.setItem("po_id",id);
    window.location.href="/transition/go_qiu_zhi_zhe_xiang_qing";
}