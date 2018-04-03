// JavaScript Document
var clock = '';
var nums = 60;
var btn;
var isPhone;
var mobileCode;
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
			url:"../mobileCode/getIsPhone",
			type:"POST",
			data:{
				phoneNumber : phoneNumber,
			},
			success : function (res) {
				if(res.data.isPhone != "1"){
					$('.shuruzhengqueshouji').show();
                    setTimeout('$(".shuruzhengqueshouji").hide()',1000);
				}
            }
		})
    });

    $(".yzxis").click(function(){
        var phoneNumber =$('#phoneNumber').val();
        $.ajax({
            url:"../mobileCode/getIsPhone",
            type:"POST",
            data:{
                phoneNumber : phoneNumber,
            },
            success : function (res) {
                isPhone =res.data.isPhone;
                if(isPhone != "1"){
                    $('.shuruzhengqueshouji').show();
                    setTimeout('$(".shuruzhengqueshouji").hide()',1000);
                }
            }
        });
        if(isPhone == "1"){
            sendCode(this);
            var phoneNumber =$('#phoneNumber').val();
            console.log("phone======"+phoneNumber)
            $.ajax({
                url:"../mobileCode/code",
                type:"POST",
                data:{
                    phone : phoneNumber,
                },
                success : function (res) {
                    mobileCode =res.data.mobile_code;
                    $('.zhengque').show();
                    setTimeout('$(".zhengque").hide()',900);
                },
                error : function () {
                    $('.huoqushibai').show();
                    setTimeout('$(".huoqushibai").hide()',900);
                }
            })
		}
    });

});


function jinru() {
    var phoneNumber =$('#phoneNumber').val();
    $.ajax({
        url:"../mobileCode/getIsPhone",
        type:"POST",
        data:{
            phoneNumber : phoneNumber,
        },
        success : function (res) {
            isPhone =res.data.isPhone;
            if(isPhone != "1"){
                $('.shuruzhengqueshouji').show();
                setTimeout('$(".shuruzhengqueshouji").hide()',1000);
            }
        }
    });
    if(isPhone == "1"){
        var sjyzm =$('.sjyzm').val();
        if(sjyzm != mobileCode){
            $('.buzhengque').show();
            setTimeout('$(".buzhengque").hide()',1000);
        }else {
            window.location.href="";
        }
    }

}

function sendCode(thisBtn) {
    btn = thisBtn;
    btn.disabled = true; //将按钮置为不可点击
    btn.value = nums+'s';
    clock = setInterval(doLoop, 1000); //一秒执行一次
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