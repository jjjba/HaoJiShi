// JavaScript Document

$(document).ready(function() {

    setTimeout(loadPersonal(),1);
   /* loadUserInfo();*/
	$(".wzkljgz01").click(function(){
		$(".popupus").hide();
		});
		
	$(".wzkljgz02").click(function(){
		$(".popupus").hide();
		});

	$(".dkhuyys").hide();
	$(".pxyouls").click(function(){
		$(".dkhuyys").show();
		});
	
	
});

function loadCompanyBanner() {
    $.ajax({
        type: "POST",
        url: "/banner/getCompanyBanner",
        success: function (res) {
            if (res.success) {
                var list = res.data;
                var id, url;
                $.each(list, function (index, item) {
                    id = item.id;
                    url =item.url;
                    if(url == null || url == ""){
                        url ="#";
                    }
                });
            }
        }
    });
}
function loadPersonal(){
    var phone = $.cookie("phone");
    $.ajax({
        url:"/company/getIndexPersonal",
        type:"POST",
        data:{phone:phone},
        success : function (res) {
            sessionStorage.setItem("com_id",res.dataOne);
            var list =res.data;
            var avatar,sex,cla;
            $.each(list, function (index, item) {
                avatar =item.avatar;
                phone =item.phone;
                if(avatar == null || avatar == ""){
                    if(item.sex == "男"){

                        avatar ="../../company/images/tupian01.png"
                    }else {
                        avatar ="../../company/images/tupian02.png"
                    }
                }
                sex =item.sex;
                if(sex == "男"){
                    sex ="../../company/images/biao05.png"
                    cla = "<div class=\"fl rminsii\">";
                }else {
                    sex ="../../company/images/biao06.png"
                    cla = "<div class=\"fl rminsii02\">";
                }
                var personalList ='<a href="#" onclick="loadPersonalInfoById(&quot;'+item.id+'&quot;)" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    cla+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expect_money +'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.job_experience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div></a>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hope_job+'</div>'+
                    '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="puanduan(&quot;'+item.id+'&quot;)"/></div>'+
                    '</div>'+
                    '</div>';
                $('.personalList').append(personalList);
            });
        }
    })
}

function loadUserInfo() {
    $.ajax({
        url:"/company/loadUserCompanyInfo",
        type:"POST",
        success :function (res) {
            if(res.data != null){
                isCollect =res.data[0].isCollect;
                isKuaiZhao =res.data[0].isKuaiZhao;
                isRegist =res.data[0].isRegist;
            }
        }
    })
}

function loadPersonalInfoById(id) {
    console.log(id);
    sessionStorage.setItem("where","sy");
    sessionStorage.setItem("po_id",id);
    window.location.href="/transition/go_qiu_zhi_zhe_xiang_qing";
}

function gokuaizhao() {
    window.location.href="/transition/go_kuai_zhao";
}

function gozhaopin() {
    window.location.href="/transition/go_zhao_pin_jian_bao";
}

function goweituo() {
    window.location.href="/transition/go_wei_tuo_zhao_pin";
}

function goqiuzhizhe() {
    window.location.href="/transition/go_qiu_zhi_zhe";
}

function gowode() {
    window.location.href="/transition/go_wo_de";
}

function gozixun() {
    window.location.href="/transition/go_zi_xun_yue_lan";
}

function puanduan(id) {
    var com_id = sessionStorage.getItem("com_id");
    console.log(com_id);
    console.log("进来了");
    if(com_id != null && com_id!='' && com_id!="" && com_id !=undefined && com_id>0){
        $.ajax({
            url: "/company/PDJyMy",
            data:{id:id},
            success: function (msg) {
                if (msg.data == 1) {
                    //正常开通
                    if (msg.dataOne != null) {
                        console.log(msg.dataOne);
                        window.location.href = "tel:" + msg.dataOne;
                    }
                }
                if (msg.data == 2) {
                    //未开通
                    $("#weikaitong").show();
                }
                if (msg.data == 3) {
                    //已经过期了
                    $("#yijingguoqi").show();
                }
            }
        })
    }else {
        console.log("进来了");
        $("#dengludianhua").show();
    }

}