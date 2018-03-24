// JavaScript Document

$(function() {

	loadCompanyInfo();

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
                $('.ztiosps').empty();
                var list = res.data;
            }
        }
    });
}
