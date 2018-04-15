// JavaScript Document
/**
 * @author 梁闯
 * @date 2018/03/28 22.46
 */
  $(function() {
      loadPersonalInfo();
      loadUserInfo();
      puanDuanSOrBs();
})
//收藏
function shoucang() {
    var kfsc = sessionStorage.getItem("kfsc");
    console.log(kfsc);
    if(kfsc ==1){
        $("#denglushoucang").show();
    }
    if(kfsc ==2){
        $("#wanshanshoucang1").show();
    }
    if(kfsc ==3){
        $("#wanshanshoucang").show();
    }
    if(kfsc ==4){
        var po_id = sessionStorage.getItem("po_id");
        $.ajax({
            url:"/company/shoucangJL",
            data:{id:po_id},
            success:function (msg) {
                if(msg.data == 1){
                    $(".toolbarframe").show();
                    setTimeout('$(".toolbarframe").hide()',1000);
                    $(".scnass").addClass("curs");
                    $(".scnass").attr("onclick","quxiao()")
                    $(".scnass").html("<i></i>已收藏");
                }else{
                    $(".toolbarframe03").show();
                    setTimeout('$(".toolbarframe03").hide()',1000);
                }
            }
        })
    }
}
//打电话
function telPhone() {
    var kfsc = sessionStorage.getItem("kfsc");
    console.log(kfsc);
    if(kfsc ==1){
        $("#dengludianhua").show();
    }
    if(kfsc ==2){
        $("#wanshandianhua1").show();
    }
    if(kfsc ==4){
        var po_id = sessionStorage.getItem("po_id");
        $.ajax({
            url: "/company/PDJyMy",
            data:{id:po_id},
            success: function (msg) {
                if(msg.data == 1){
                    //正常开通
                    if(msg.dataOne != null){
                        console.log(msg.dataOne);
                       /* $("#lianxiPhone").attr("href","tel:"+msg.dataOne.phone);
                        $("#lianxiPhone").trigger("click");*/
                       window.location.href="tel:"+msg.dataOne;
                    }
                }
                if(msg.data ==2){
                    //未开通
                    $("#weikaitong").show();
                }
                if(msg.data ==3){
                    //已经过期了
                    $("#yijingguoqi").show();
                }
            }
        })
    }
}
function quxiao() {
    var po_id = sessionStorage.getItem("po_id");
    $.ajax({
        url:"/company/quxiaoJL",
        data:{id:po_id},
        success:function (msg) {
            if(msg.data == 1){
                $(".toolbarframe02").show();
                setTimeout('$(".toolbarframe02").hide()',1000);
                $(".scnass").removeClass("curs");
                $(".scnass").attr("onclick","shoucang()")
                $(".scnass").html("<i></i>收藏");
            }else{
                $(".toolbarframe04").show();
                setTimeout('$(".toolbarframe04").hide()',1000);
            }
        }
    })
}

//判断收藏还是不收藏
function puanDuanSOrBs() {
    var po_id = sessionStorage.getItem("po_id");
    $.ajax({
        url:"/company/PDJL",
        data:{id:po_id},
        success:function (msg) {
            if(msg.data == 1){
                $(".scnass").addClass("curs");
                $(".scnass").attr("onclick","quxiao()")
                $(".scnass").html("<i></i>已收藏");
            }
        }
    })
}












function loadPersonalInfo() {
    var id = sessionStorage.getItem("po_id");
    console.log("id是"+"-------------"+id);
	$.ajax({
		url:"/personal/getPersonalInfoById",
		type:"POST",
        data:{id:id},
		success : function (res) {
		    console.log(res);
			var list =res.data;
			phone =list.phone;
			var sex =list.sex;
			var avatar =list.avatar;
			var spa="";
			if(avatar == null || avatar == ""){
                if(sex == "男"){
                    avatar = "../../company/images/tupian01.png"
                }else {
                    avatar = "../../company/images/tupian02.png"
                }
			}
			if(sex == "男"){
				sex = "../../company/images/biao05.png"
			}else {
				sex = "../../company/images/biao06.png"
			}
			if(list.special != null){
                var att = new Array();
                att = (list.special).split(",");
                for(var i =0;i<att.length;i++){
                    spa+="<span>";
                    spa+=att[i];
                    spa+="</span>"
                }
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
                spa+
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
                $('#myself').append(list.mySelfInfo);
			}else {
                $("#myself").append("这厮比较懒，自我介绍都不做···");
            }
			if(list.recordSchool!=null&& list.recordSchool!=""){
                $('#xiangxiziliao').append('最高学历：'+list.recordSchool+'<br>');
			}
			if(list.onceDo!=null&& list.onceDo!=""){
                $('#xiangxiziliao').append('曾经做过：'+list.onceDo+'<br>');
            }
            if(list.myHometown!=null&&list.myHometown!=""){
                $('#xiangxiziliao').append('他的家乡：'+list.myHometown);
            }
            if(list.myHometown ==null && list.onceDo == null && list.recordSchool==null){
                $("#xiangxiziliao").append("这厮比较懒，也没有填···");
            }
			if(list.photo != null && list.photo != ""){
            	var photos =list.photo.split(",");
            	for(var i = 0;i < photos.length;i++){
            		$('#lightgallery').append('<li data-src="'+photos[i]+'"><img src="'+photos[i]+'" /></li>')
				}
			}else{
                $("#lightgallery").append("这厮不爱美，么地照片啊···");
            }
        }
	})
}

function loadUserInfo() {
    var zt = $.cookie("zt");
    var phone = $.cookie("phone");
    if(zt==null && phone == null){
        sessionStorage.setItem("kfsc",1);//可否收藏  1 代表游客登录 未登录
    }else {
        if(phone != null){
            $.ajax({
                url:"/company/loadUserCompanyInfo",
                type:"POST",
                data:{phone:phone},
                success :function (res) {
                sessionStorage.setItem("kfsc",res.data);
            }
        })
        }

    }

}

function tellPhone() {
    if(isRegist == "2"){
        $('#wanshandianhua').show();
        $("#zaikanwanshandianhua").click(function(){
            $("#wanshandianhua").hide();
        });
        $("#likewanshandianhua").click(function(){
            $("#wanshandianhua").hide();
            window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
        });
    }else if(isRegist == "3"){
        $('#dengludianhua').show();
        $("#zaikandengludianua").click(function(){
            $("#dengludianhua").hide();
        });
        $("#likedengludianhua").click(function(){
            $("#dengludianhua").hide();
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
                window.location.href="/transition/go_kuai_zhao";
            });
        }else if(isKuaiZhao == "3"){
            $('.xianyin02').show();
            $("#zaikankaitong").click(function(){
                $("#xianyin02").hide();
            });
            $("#likekaitong").click(function(){
                window.location.href="/transition/go_kuai_zhao";
            });
        }



    }

}
function goBack() {
    var wher = sessionStorage.getItem("where");
    if(wher == "sy"){
        window.location ="/account/companyIndex";
    }
    if(wher == "zc"){
        window.location.href="/transition/go_qiu_zhi_zhe";
    }
    if(wher == "shoucang"){
        window.location.href = "/transition/wo_de_ren_cai_shou_cang";
    }
    if(wher == "zuoce"){
        window.location.href="/transition/go_qiu_zhi_zhe";
    }
}
	  





