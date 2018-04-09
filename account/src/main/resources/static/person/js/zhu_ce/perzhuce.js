// JavaScript Document
var clock = '';
var nums = 60;
var btn;
var mobileCode;
var isRegist;
$(document).ready(function() {

	// $(".dlaniuss").click(function(){
	//
	// 	$(".toolbarframe").show();
	//     setTimeout('$(".toolbarframe").hide()',600);
	//
	// 	});

    $("#phoneNumber").blur(function(){
        var phoneNumber =$('#phoneNumber').val();
        $.ajax({
            url:"/mobileCode/isRegist",
            type:"POST",
            data:{
                phone : phoneNumber,
            },
            success : function (res) {
                isRegist =res.data.isRegist;
            }
        })
    });

    $(".yzxis").click(function(){


    });

});


function jinru() {
    var phoneNumber =$('#phoneNumber').val();
    $.ajax({
        url:"/mobileCode/getIsPhone",
        type:"POST",
        data:{
            phoneNumber : phoneNumber,
        },
        success : function (res) {
            var isPhone =res.data.isPhone;
            if(isPhone != "1"){
                $('.shuruzhengqueshouji').show();
                setTimeout('$(".shuruzhengqueshouji").hide()',1000);
            }else {
                var sjyzm =$('.sjyzm').val();
                if(sjyzm != mobileCode){
                    $('.buzhengque').show();
                    setTimeout('$(".buzhengque").hide()',1000);
                }else {
                    if(isRegist == "1"){
                        window.location.href="/transition/go_wan_shan_xin_xi";
                    }else if(isRegist == "2"){
                        window.location.href="/transition/transition_goMySelf";
                    }else if(isRegist == "3"){
                        $.ajax({
                            url:"/personal/registerPersonal",
                            type:"POST",
                            data:{
                                phone : phoneNumber,
                            },
                            success : function () {
                                window.location.href="/transition/go_wan_shan_xin_xi";
                            },
                            error : function () {
                                $('.zhuceshibai').show();
                                setTimeout('$(".zhuceshibai").hide()',1000);
                            }
                        })
                    }

                    }
            }
        }
    });


}

function sendCode(thisBtn) {
    var phoneNumber =$('#phoneNumber').val();
    $.ajax({
        url:"/mobileCode/getIsPhone",
        type:"POST",
        data:{
            phoneNumber : phoneNumber,
        },
        success : function (res) {
            var isPhone =res.data.isPhone;
            if(isPhone != "1"){
                $('.yzxis').disabled = true
                $('.shuruzhengqueshouji').show();
                setTimeout('$(".shuruzhengqueshouji").hide()',1000);
            }else {
                btn = thisBtn;
                btn.disabled = true; //将按钮置为不可点击
                btn.value = nums+'s';
                clock = setInterval(doLoop, 1000); //一秒执行一次
                var phoneNumber =$('#phoneNumber').val();

                $.ajax({
                    url:"/mobileCode/code",
                    type:"POST",
                    data:{
                        phone : phoneNumber,
                    },
                    success : function (res) {
                        mobileCode =res.data.mobile_code;
                        $('.zhengque').show();
                        setTimeout('$(".zhengque").hide()',900);
                        console.log("phone======"+phoneNumber)
                        console.log("code =========="+mobileCode);
                    },
                    error : function () {
                        $('.huoqushibai').show();
                        setTimeout('$(".huoqushibai").hide()',900);
                    }
                })
            }
        }
    });

}
function doLoop() {
    nums--;
    if(nums > 0){
        btn.value = nums+'s';
    }else{
        clearInterval(clock); //清除js定时器
        btn.disabled = false;
        btn.value = '重新获取验证码';
        nums = 10; //重置时间
    }
}