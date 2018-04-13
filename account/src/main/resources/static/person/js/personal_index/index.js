// JavaScript Document
$(function() {

	loadPersonalBanner();
    var phone =$.cookie('phone');
    if(phone != null){
        $.ajax({
            url:"/personal/setuserId",
            type:"POST",
            data:{phone : phone},
            success : function () {
                loadPositionData();
            }
        })
    }else{
        $.ajax({
            url:"/personal/setuserId",
            type:"POST",
            data:{phone : 0},
            success : function () {
                loadPositionData();
            }
        })
    }



    
	$(".wzkljgz01").click(function(){
		
		$(".popupus").hide();
		
		});
		
	$(".wzkljgz02").click(function(){
		
		$(".popupus").hide();
		
		});
	
});

function loadPersonalBanner() {
    $.ajax({
        type: "POST",
        url: "/banner/getPersonalBanner",
        success: function (res) {
            if (res.success) {
                var list = res.data;
                var id, url;
                $.each(list, function (index, item) {
                    id = item.id;
                    url =item.url;
                    if(url == null || url == "" || url == "未设置"){
                        url ="#";
                    }
                    $('.banner').append('<div class="swiper-slide"><a href="'+url+'"><img src="'+item.imageUrl+'" /></a></div>');
                });

            }
        }
    });
}


function loadPositionData() {
    $.ajax({
        type: "POST",
        url: "/position/getPositionInIndex",
        success: function (res) {
            if (res.success) {
                $('.positionList').empty();
                var list = res.data;
                var id, city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale,area;
                $.each(list, function (index, item) {
                    id = item.id;
                    city = item.city;
                    area = item.area;
                    position_name = item.position_name;
                    money = item.money;
                    experience = item.experience;
                    age = item.age;
                    sex = item.sex;
                    name = item.name;
                    company_type = item.company_type;
                    company_scale = item.company_scale;
                    hot =item.hot;
                    if(hot == null || hot == ""){
                        hot ="0";
                    }
                    icon_path =item.icon_path;
                    if(icon_path == null || icon_path == ""){
                        icon_path="../../person/images/icon_company_default.png";
                    }

                    var positionList =
                        '<div class="reconblock">\n' +
                        '    <a href="#" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+id+'\')">\n' +
                        '      <div class="respise clearfix">\n' +
                        '        <div class="fl reselefs">'+position_name+'</div>\n' +
                        '        <div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">热度'+hot+'</span></div>\n' +
                        '      </div>\n' +
                        '      <div class="wagesyears clearfix">\n' +
                        '        <div class="fl wayealfs">'+money+'</div>\n' +
                        '        <div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>\n' +
                        '      </div>\n' +
                        '        <div class="corpnames">\n' +
                        '          <div class="corpzuos">\n' +
                        '            <img src="'+icon_path+'" />\n' +
                        '            <div class="yrzs"><img src="../../person/images/biao02.png" /></div>\n' +
                        '          </div>\n' +
                        '          <div class="corpryous">\n' +
                        '            <h1>'+name+'</h1>\n' +
                        '            <p>'+company_type+' | '+company_scale+' | '+city+'-'+area+'</p>\n' +
                        '          </div>\n' +
                        '        </div>\n' +
                        '     </a>\n' +
                        '</div>';
                    $('.positionList').append(positionList);
                });
            }
        }
    });
}


function goPosition() {
    window.location.href="/transition/transition_all_position";
}

function goMySelf() {
    window.location.href="/transition/transition_goMySelf";
}

function loadPositionInfo(url) {
    window.location.href=url;
}