$(document).ready(function() {
    console.log("进来了-----");
        $.ajax({
            type:"post",
            url:"/company/updatePhoneNu",
            dataType:"json",
            success:function (msg) {
                var PhoneNum = msg.data;
                console.log(PhoneNum.phone);
                $("#phoneNum").html(PhoneNum.phone);
            }
        })
});
