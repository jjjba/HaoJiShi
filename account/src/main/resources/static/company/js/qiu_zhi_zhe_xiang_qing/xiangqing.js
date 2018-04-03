// JavaScript Document
/**
 * @author 梁闯
 * @date 2018/03/28 22.46
 */
var isRegist;//判断是否登陆

var isCollect;//是否已经收藏该求职者 1收藏该求职者 2未收藏该求职者

var isKuaiZhao;//是否开通快招服务  1开通并且未到期 2开通已到期 3未开通

var phone;  //求职者手机号
  $(function() {
      loadPersonalInfo();
      loadUserInfo();

   	  $(".toolbarframe").hide();
	  $(".toolbarframe02").hide();
      $(".toolbarframe03").hide();
      $(".toolbarframe04").hide();
      $(".toolbarframe04").hide();
      $(".toolbarframe04").hide();
   
  $('.d-btn').click(function(){
  	if(isRegist == "2"){
  		$('#wanshanshoucang').show();
        $("#zaikanwanshanshoucang").click(function(){
            $("#wanshanshoucang").hide();
        });
        $("#likewanshanshoucang").click(function(){
            $("#wanshanshoucang").hide();
            window.location.href="../transition/go_zhu_ce_tian_xie_xin_xi";
        });
	}else if(isRegist == "3"){
        $('#denglushoucang').show();
        $("#zaikandenglushoucang").click(function(){
            $("#denglushoucang").hide();
        });
        $("#likedenglushoucang").click(function(){
            window.location.href="../transition/go_zhu_ce";
        });
	}else {
        if($(this).text()=="收藏"){
        	$.ajax({
				url:"/collection/collectPersonal",
				type:"POST",
				success : function (res) {
                    $('.scnass').html("<i></i>已收藏").addClass("curs");
                    $(".toolbarframe").show();
                    setTimeout('$(".toolbarframe").hide()',900);
                },
				error :function (res) {
                    $('.scnass').html("<i></i>收藏").removeClass("curs");
                    $(".toolbarframe03").show();
                    setTimeout('$(".toolbarframe03").hide()',900);
                }
			})
        }else{
            $.ajax({
                url:"/collection/cancelCollectPersonal",
                type:"POST",
                success : function (res) {
                    $('.scnass').html("<i></i>收藏").removeClass("curs");
                    $(".toolbarframe02").show();
                    setTimeout('$(".toolbarframe02").hide()',900);
                },
                error :function (res) {
                    $('.scnass').html("<i></i>已收藏").addClass("curs");
                    $(".toolbarframe04").show();
                    setTimeout('$(".toolbarframe04").hide()',900);
                }
            })
            $(this).html("<i></i>收藏").removeClass("curs");
            $(".toolbarframe").hide();
            $(".toolbarframe02").show();
            setTimeout('$(".toolbarframe02").hide()',900);
        }
	}
			});
   
   // $(".scnass").click(function(){
	//    $(".xianyin01").show();
	//    });
   //
   // $(".wyypins").click(function(){
	//
	//    $(".xianyin02").show();
	//    });
	//
	//    $(".wzkljgz01,.wzkljgz02").click(function(){
	//
	//
	// 	   $(".popupus").hide();
	//
	// 	   });
		 
      });

function loadPersonalInfo() {
	$.ajax({
		url:"../personal/getPersonalInfoById",
		type:"POST",
		success : function (res) {
			var list =res.data;
			phone =list.phone;
			var sex =list.sex;
			var avatar =list.avatar;
			if(avatar == null || avatar == ""){
                if(sex == "男"){
                    avatar = "../company/images/tupian01.png"
                }else {
                    avatar = "../company/images/tupian02.png"
                }
			}
			if(sex == "男"){
				sex = "../company/images/biao05.png"
			}else {
				sex = "../company/images/biao06.png"
			}
			var grzlyuis='<div class="sbuiys">'+
				'<div class="sbxtlfs">'+
                '<div class="lylyus02">'+list.name+' <span><img src="'+sex+'">'+list.age+'</span></div>'+
                '<div class="grxhzls">'+list.job_experience+' | '+list.state+'<br />'+list.address+'</div>'+
                '</div>'+
                '<div class="ycxctxs">'+
				'<img src="'+avatar+'" />'+
                '</div>'+
                '</div>'+
                '<div class="xbyouyis">'+
                '<span>吃苦耐劳</span>'+
                '</div>'+
                '</div>';
			$('.grzlyuis').append(grzlyuis);
			var zwxqius = '<div class="zwxtits">求职意向</div>'+
                '<div class="zwqxconts">'+
                '期望薪资：'+list.expect_money+'<br>'+
                '意向工作：'+list.hope_job+'<br>'+
                '意向城市：'+list.hope_city+
                '</div>';
            $('.zwxqius').append(zwxqius);
            if(list.mySelfInfo != null && list.mySelfInfo != ""){
            	$('#mySelfInfo').show();
                $('#myself').append(list.mySelfInfo);
			}
			if(list.myHometown!=null&&myHometown!=""&&list.recordSchool!=null&&recordSchool!=""&&list.onceDo!=null&&list.onceDo!=""){
                $('#ziliao').show();
                $('#xiangxiziliao').append('最高学历：'+list.recordSchool+'<br>' +
                    '曾经做过：'+list.onceDo+'<br>' +
                    '他的家乡：'+list.myHometown);
			}
			if(list.photo != null && list.photo != ""){
            	$('#photoes').show();
            	var photos =list.photo.split(",");
            	for(var i = 0;i < photos.length;i++){
            		$('#lightgallery').append('<li data-src="'+photos[i]+'"><img src="'+photos[i]+'" /></li>')
				}
			}
        }
	})
}

function loadUserInfo() {
	$.ajax({
		url:"../company/loadUserCompanyInfo",
		type:"POST",
		success :function (res) {
            isCollect =res.data[0].isCollect;
            isKuaiZhao =res.data[0].isKuaiZhao;
            isRegist =res.data[0].isRegist;
            console.log("isCollect============="+isCollect);
            console.log("isKuaiZhao============"+isKuaiZhao);
            console.log("isRegist=============="+isRegist);
            if(isCollect == "1"){
                $('.scnass').html("<i></i>已收藏").addClass("curs");
			}else {
            	$('.scnass').show();
			}
        }
	})
}

function tellPhone() {
    if(isRegist == "2"){
        $('#wanshandianhua').show();
        $("#zaikanwanshandianhua").click(function(){
            $("#wanshandianhua").hide();
        });
        $("#likewanshandianhua").click(function(){
            $("#wanshandianhua").hide();
            window.location.href="../transition/go_zhu_ce_tian_xie_xin_xi";
        });
    }else if(isRegist == "3"){
        $('#dengludianhua').show();
        $("#zaikandengludianua").click(function(){
            $("#dengludianhua").hide();
        });
        $("#likedengludianhua").click(function(){
            $("#dengludianhua").hide();
            window.location.href="../transition/go_zhu_ce";
        });
    }else {
        if(isKuaiZhao == "1"){
            $.ajax({
                url:"../company/updatePhoneNum",
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
                window.location.href="../transition/go_kuai_zhao";
            });
        }else if(isKuaiZhao == "3"){
            $('.xianyin02').show();
            $("#zaikankaitong").click(function(){
                $("#xianyin02").hide();
            });
            $("#likekaitong").click(function(){
                window.location.href="../transition/go_kuai_zhao";
            });
        }



    }

}
function goBack() {
    window.location.href="../transition/go_qiu_zhi_zhe";
}
	  





