// JavaScript Document
function shiqu() {
    var phoneNumber =$('#phoneNumber').val();
    if(phoneNumber =="" || phoneNumber =='' || phoneNumber==undefined){
        $('.alera').show();
        setTimeout('$(".alera").hide()',1000);
        $("#hqyzm").attr("class","");
        $("#hqyzm").val("");
    }else {
        $("#hqyzm").attr("class","yzxis");
        $("#hqyzm").val("获取验证码");
        $.ajax({
            url:"/company/getIsPhone",
            type:"POST",
            data:{
                phoneNumber : phoneNumber,
            },
            success : function (msg) {
                var OkorFalse = msg.data;
                console.log(OkorFalse);
                if(OkorFalse == 2){
                    $("#hqyzm").attr("class","yzxis");
                    $("#hqyzm").val("获取验证码");
                }else{
                    $('.alera').show();
                    setTimeout('$(".alera").hide()',1000);
                    $("#hqyzm").attr("class","");
                    $("#hqyzm").val("");
                }
            }
        })
    }
}
$(document).ready(function() {
    
	$(".toolbarframe").hide();
	
	/*$(".dlaniuss").click(function(){
		$(".toolbarframe").show();
	    	setTimeout('$(".toolbarframe").hide()',600);
		});*/


    $(".yzxis").click(function(){
        $('.zhengque').show();
                setTimeout('$(".zhengque").hide()',900);
        var phoneNumber =$('#phoneNumber').val();
        $.ajax({
            url:"/mobileCode/code",
            type:"POST",
            data:{
                phone : phoneNumber,
            },
            success : function (res) {
                mobileCode =res.data.mobile_code;
				$.cookie("yzm",mobileCode);
            },
            error : function () {
                $('.huoqushibai').show();
                setTimeout('$(".huoqushibai").hide()',900);
            }
		})
	})
});

function jinru() {
	var mobileCode = $.cookie("yzm");
    var phoneNumber =$('#phoneNumber').val();
    var code  =$('#code').val();
    console.log(mobileCode + "--------"+ code);

    if(mobileCode == code){
        /*$.cookie("zt", "1", { expires: 10 });
        $.cookie("phone", phoneNumber, { expires: 10 });*/
        sessionStorage.setItem("phone",phoneNumber);
        window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
	}else {
        $('.buzhengque').show();
        setTimeout('$(".buzhengque").hide()',900);
	}
}

function mimadl() {
    window.location.href="/transition/mimadl"
}
function zhucefanhui() {
    window.location.href="/transition/go_wo_de";
}

