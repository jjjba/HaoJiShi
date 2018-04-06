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
var newPhoneNum;
function sendCode(thisBtn)
{
    $('.zhengque').show();
    setTimeout('$(".zhengque").hide()',1000);
    btn = thisBtn;
    btn.disabled = true; //将按钮置为不可点击
    btn.value = nums+'s';
    clock = setInterval(doLoop, 1000); //一秒执行一次
    PhoneYzm($("#phoneNum").html());
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
function PhoneYzm2(phone) {
    $.ajax({
        url:"/mobileCode/codes",
        data:{"phone":phone},
        datatype:"json",
        success:function (msg) {
            $("#yzmyc2").val(msg.data.mobile_code);
        }
    })
}
function sendCode1(thisBtn)
{
    newPhoneNum= $("#newPhoneNum").val();
    if(newPhoneNum =='' || newPhoneNum =="" || newPhoneNum == undefined){
        $('.Newshurushouji').show();
        setTimeout('$(".Newshurushouji").hide()',1000);
    }else{
        $.ajax({
            type:"POST",
            url:"/mobileCode/getIsPhone",
            data:{phoneNumber : newPhoneNum},
            success:function (msg) {
                if(msg.data.isPhone=="2"){
                    $('.shuruzhengqueshouji').show();
                    setTimeout('$(".shuruzhengqueshouji").hide()',1000);
                }else{
                    $('.zhengque').show();
                    setTimeout('$(".zhengque").hide()',1000);
                    btn = thisBtn;
                    btn.disabled = true; //将按钮置为不可点击
                    btn .value = nums+'s';
                    clock = setInterval(doLoop, 1000); //一秒执行一次
                    PhoneYzm2(newPhoneNum);
                }
            }
        })

    }
}
function updatePhone() {
    var oldyzm = $("#yzmyc").val();
    var newPhoneNum = $("#newPhoneNum").val();
    var newyzm = $("#yzmyc2").val();
    var oldyzm1 =$("#yzmold").val();
    var newyzm1 =$("#yzmnew").val();
    if(oldyzm1== '' || oldyzm1=="" ||oldyzm1==undefined){
        $('.diyici').show();
        setTimeout('$(".diyici").hide()',1000);
    }
    if(newyzm1 =='' || newyzm1 =="" || newyzm1==undefined){
        $('.dierci').show();
        setTimeout('$(".dierci").hide()',1000);
    }
    if(oldyzm == oldyzm1 && newyzm != newyzm1){
        $('.diyiciyanzhengma').show();
        setTimeout('$(".diyiciyanzhengma").hide()',1000);
    }
    if(oldyzm != oldyzm1 && newyzm == newyzm1){
        $('.dierciyanzhengma').show();
        setTimeout('$(".dierciyanzhengma").hide()',1000);
    }
    if(oldyzm == oldyzm1 && newyzm == newyzm1){
        $.ajax({
            type:"POST",
            url:"/company/updatePhone",
            data:{phoneNum:newPhoneNum},
            success:function (msg) {
                if(msg.data.data == 1){
                    $('.updateok').show();
                    setTimeout('$(".updateok").hide()',1000);
                    window.location.href="/transition/go_wo_de";
                }else {
                    $('.updatefalse').show();
                    setTimeout('$(".updatefalse").hide()',1000);
                }
            }

        })
    }
}