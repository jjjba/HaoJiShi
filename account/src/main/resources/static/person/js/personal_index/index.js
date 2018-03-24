// JavaScript Document
$(function() {

	loadPersonalBanner();

	loadPositionData();
    
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
                console.log('====='+list);
                var id, imageUrl, url;
                $.each(list, function (index, item) {
                    id = item.id;
                    url =item.url;
                    if(url == null || url == ""){
                        url ="#";
                    }
                    var scroll = "";
                    scroll +=
                        '<div class="scroll-container swiper-container">' +
                        '<div class="swiper-wrapper">' +
                        '<div class="swiper-slide"><a href="'+url+'"><img src="'+item.imageUrl+'" /></a></div>' +
                        '</div>' +
                        '</div>'+
                        '<div class="pagination"></div>';
                    $('.scroll').append(scroll);
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
                var id, company_city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale;
                $.each(list, function (index, item) {
                    id = item.id;
                    company_city =item.company_city;
                    if(company_city == null || company_city == ""){
                        company_city ="未填写";
                    }
                    position_name =item.position_name;
                    if(position_name == null || position_name == ""){
                        position_name ="未填写";
                    }
                    hot =item.hot;
                    if(hot == null || hot == ""){
                        hot ="0";
                    }
                    money =item.money;
                    if(money == null || money == ""){
                        money ="未填写";
                    }
                    experience =item.experience;
                    if(experience == null || experience == ""){
                        experience ="未填写";
                    }
                    age =item.age;
                    if(age == null || age == ""){
                        age ="未填写";
                    }
                    sex =item.sex;
                    if(sex == null || sex == ""){
                        sex ="未填写";
                    }
                    icon_path =item.icon_path;
                    if(icon_path == null || icon_path == ""){
                        icon_path="../../person/images/icon_company_default.png";
                    }
                    name =item.name;
                    if(name == null || name == ""){
                        name ="未填写";
                    }
                    company_type =item.company_type;
                    if(company_type == null || company_type == ""){
                        company_type ="未填写";
                    }
                    company_scale =item.company_scale;
                    if(company_scale == null || company_scale == ""){
                        company_scale ="未填写";
                    }
                    var positionList = "";
                    positionList +=
                        '<div class="reconblock">'+
                        '<a href="#" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+id+'\')">' +
                        '<div class="respise clearfix">' +
                        '<div class="fl reselefs">'+position_name+'</div>' +
                        '<div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">'+hot+'</span></div>' +
                        '</div>'+
                        '<div class="wagesyears clearfix">'+
                        '<div class="fl wayealfs">'+money+'</div>'+
                        '<div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>'+
                        '</div>'+
                        '<div class="corpnames">'+
                        '<div class="corpzuos">'+
                        '<img src="'+icon_path+'" />'+
                        '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>'+
                        '</div>'+
                        '<div class="corpryous">'+
                        '<h1>'+name+'</h1>'+
                        '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
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