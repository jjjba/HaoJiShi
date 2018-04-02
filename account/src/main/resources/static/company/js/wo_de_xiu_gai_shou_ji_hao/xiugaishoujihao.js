$(document).ready(function() {
        $.ajax({
            type:"post",
            url:"/company/updatePhoneNu",
            dataType:"json",
            success:function (msg) {
                var PhoneNum = msg.data;
                $("#phoneNum").html(PhoneNum.phone);
            }
        })
});
var clock = '';
var nums = 60;
var btn;
function sendCode(thisBtn)
{
    /*PhoneYzm($("#phoneNum").html());*/
    PhoneYzm("13733319694");
    btn = thisBtn;
    btn.disabled = true; //将按钮置为不可点击
    btn.value = nums+'s';
    clock = setInterval(doLoop, 1000); //一秒执行一次
}
function doLoop()
{
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
function PhoneYzm(phone) {
    $.ajax({
        url:"/mobileCode/codes",
        data:{"phone":phone},
        datatype:"json",
        success:function (msg) {
          $("#yzmyc").val(msg.data.mobile_code);
        }
    })
}
function sendCode1(thisBtn)
{
    /*PhoneYzm($("#phoneNum").html());*/
    var newPhoneNum = $("#newPhoneNum").val();
    if(newPhoneNum !='' && newPhoneNum !="" && newPhoneNum != undefined){

    }
    PhoneYzm("13733319694");
    btn = thisBtn;
    btn.disabled = true; //将按钮置为不可点击
    btn.value = nums+'s';
    clock = setInterval(doLoop, 1000); //一秒执行一次
}