// JavaScript Document

var isRegist;//判断是否登陆
var isKuaiZhao;//是否开通快招服务  1开通并且未到期 2开通已到期 3未开通
var phone;  //求职者手机号
$(document).ready(function() {

    loadPersonal();
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
            console.log(res);
            $('.personal').empty();
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
                    '<div class="fr ciiuss">'+item.expectMoney+'</div>'+
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
                /*var die="";
                if(item.mySelfInfo.recordSchool !=null){
                    die+="最高学历：";
                    die+=item.mySelfInfo.recordSchool;
                    die+="<br>"
                }
                if(item.mySelfInfo.onceDo !=null){
                    die+="曾经做过：";
                    die+=item.mySelfInfo.onceDo;
                    die+="<br>"
                }
                if(item.mySelfInfo.myHometown!=null){
                    die+="他的家乡：";
                    die+=item.mySelfInfo.myHometown;
                }
                if(diea != "" && diea !=''){

                }*/
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

function tellPhone() {
    if(isRegist == "2"){
        $('#wanshan').show();
        $("#zaikanwanshan").click(function(){
            $("#wanshan").hide();
        });
        $("#likewanshan").click(function(){
            $("#wanshan").hide();
            window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
        });
    }else if(isRegist == "3"){
        $('#denglu').show();
        $("#zaikandenglu").click(function(){
            $("#denglu").hide();
        });
        $("#likedenglu").click(function(){
            $("#denglu").hide();
            window.location.href="/transition/go_zhu_ce";
        });
    }else {
        if(isKuaiZhao == "1"){
            $.ajax({
                url:"/company/updatePhoneNum",
                type:"POST",
                success : function (res) {
                    console.log("更改次数成功");
                    window.location.href="tel:"+phone;
                },
                error : function (res) {
                    console.log("更改次数失败");
                    window.location.href="tel:"+phone;
                }
            });
        }else if(isKuaiZhao == "2"){
            $('.xianyin03').show();
            $("#zaikanxufei").click(function(){
                $('.xianyin03').hide();
            });
            $("#likexufei").click(function(){
                $('.xianyin03').hide();
                window.location.href="/transition/go_kuai_zhao";
            });
        }else if(isKuaiZhao == "3"){
            $('.xianyin02').show();
            $("#zaikankaitong").click(function(){
                $("#xianyin02").hide();
            });
            $("#likekaitong").click(function(){
                $("#xianyin02").hide();
                window.location.href="/transition/go_kuai_zhao";
            });
        }
    }
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
    console.log(id);
    var phone = $.cookie("phone");
    var zt = $.cookie("zt");
    if(phone !=null && zt == 1){

    }else{

    }
}