// JavaScript Document
$(document).ready(function() {

    // var mySwiper = new Swiper('.scroll-container',{
    //     pagination: '.pagination',
    //     autoplay:3000,
    //     loop:true,
    //     grabCursor: true,
    //     paginationClickable: true,
    //     cssHeight:false,
    //     calculateHeight:true
    // });

	loadCompanyBanner();
    loadIndexModule();
    loadPersonal();

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

function loadIndexModule() {
    $.ajax({
        type: "POST",
        url: "/indexModule/getIndexModule",
        success: function (res) {
            if (res.success) {
                var list = res.data;
                if(list.length == 2){
                    $('#twoIndexModule').show()
                    $.each(list, function (index, item) {
                        $('#first').append('<li class="fl"><a href="'+item.url+'"><img src="'+item.imageUrl+'" /></a></li>');
                    })
                }else if(list.length == 4){
                    $('#fourIndexModule').show()
                    $('#one').append('<li class="fl"><a href="'+list[0].url+'"><img src="'+list[0].imageUrl+'" /></a></li>');
                    $('#one').append('<li class="fl"><a href="'+list[1].url+'"><img src="'+list[1].imageUrl+'" /></a></li>');
                    $('#two').append('<li class="fl"><a href="'+list[2].url+'"><img src="'+list[2].imageUrl+'" /></a></li>');
                    $('#two').append('<li class="fl"><a href="'+list[3].url+'"><img src="'+list[3].imageUrl+'" /></a></li>');
                }
            }
        }
    });
}


function loadPersonal(){
    $.ajax({
        url:"/personal/getIndexPersonal",
        type:"POST",
        success : function (res) {
            $('.personal').empty();
            var list =res.data;
            var avatar,sex;
            $.each(list, function (index, item) {
                avatar =item.avatar;
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
                }else {
                    sex ="../../company/images/biao06.png"
                }
                var personalList ='<a href="#" onclick="loadPersonalInfoById(\'/transition/go_qiu_zhi_zhe_xiang_qing?id='+item.id+'\')" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    '<div class="fl rminsii">'+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expectMoney+'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.jobExperience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hopeJob+'</div>'+
                    '<div class="pxyouls"><a href="#" onclick="tellPhone()"><img src="./../company/images/biao07.png" /></div></a>'+
                    '</div>'+
                    '</div>'+
                    '</a>';
                $('.personalList').append(personalList);
            });
        }
    })
}

function loadPersonalInfoById(url) {
    window.location.href=url;
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