// JavaScript Document

$(function() {

	loadCompanyInfo();
	loadPositionByCid();
	$(".zkbutns").click(function(){
		$(".wzquys").addClass("zhksi");
		$(this).hide();
		});
	
});


function loadCompanyInfo() {
    $.ajax({
        type: "POST",
        url: "/company/getCompanyInfoByCompanyId",
        success: function (res) {
            if (res.success) {
            	$('.ztiosps').empty();
            	var list =res.data;

				var iconPath;
                iconPath =list.iconPath;
                if(iconPath == null || iconPath == ""){
                    iconPath="../../person/images/icon_company_default.png";
                }
                if(list.companySpecial == null || list.companySpecial ==""){
                    $('.xblbyos').append("该企业还没有填写相关标签~~~");
                }else {
                    var special =list.companySpecial.split(",");
                    for(var i = 0;i < special.length;i++){
                        $('.xblbyos').append("<span>"+special[i]+"</span>");
                    }
                }

            	var ztiosps ='<div class="corpnames">'+
                    '<div class="corpzuos">'+
                    '<img src="'+iconPath+'">'+
                    '</div>'+
                    '<div class="corpryous">'+
                    '<h1>'+list.name+'</h1>'+
                    '<p>'+list.companyType+' | '+list.companyScale+' | '+list.companyCity+'</p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="xblbyos">'+
                    '</div>';

				$('.ztiosps').append(ztiosps);
                $('.wzquys').append(list.companyInfo);

                if(list.companyPhoto == null || list.companyPhoto == ''){
                    $('.clearfix').append("还没有相关照片呦~~~");
				}else {
                    var photo =list.companyPhoto.split(",");
                    for(var j = 0;j < photo.length;j++){
                        $('#ho').append('<li><a href="#"><img src="'+photo[j]+'" /></a></li>');
                    }
				}
            }
        }
    })
}

function loadPositionByCid() {
    $.ajax({
        type: "POST",
        url: "/position/getPositionByCid",
        success: function (res) {
            if (res.success) {
                $('.position').empty();
                var list = res.data;
                var id,position_name,
                    money,experience,age,sex;
                if(list == null || list ==""){
                	$('.position').append("该公司还没有发布任何职位呦~~~~")
				}else {
                $.each(list, function (index, item) {
                    id = item.id;
                    position_name = item.position_name;
                    if (position_name == null || position_name == "") {
                        position_name = "未填写";
                    }
                    money = item.money;
                    if (money == null || money == "") {
                        money = "未填写";
                    }
                    experience = item.experience;
                    if (experience == null || experience == "") {
                        experience = "未填写";
                    }
                    age = item.age;
                    if (age == null || age == "") {
                        age = "未填写";
                    }
                    sex = item.sex;
                    if (sex == null || sex == "") {
                        sex = "未填写";
                    }

                    var position ='<a href="#" class="kuisis" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+id+'\')">'+
						'<div class="libjyos">'+
						'<div class="lzuliuys">'+
						'<h2>'+position_name+'</h2>'+
						'<p>'+money+'</p>'+
						'</div>'+
						'<div class="yomiyyys">'+
						experience+' | '+age+' | '+sex+
						'<img src="../../person/images/yjts.png" class="jyousso" />'+
						'</div>'+
						'</div>'+
						'</a>';
                    $('.position').append(position);
                });
                }
            }
        }
    });
}

function loadPositionInfo(url) {
	window.location.href=url;
}
