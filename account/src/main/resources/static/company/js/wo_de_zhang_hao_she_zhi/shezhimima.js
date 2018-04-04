function shezhimima() {
    var password = $("#password").val();
    if(password != '' && password != "" && password !=undefined){
        $.ajax({
            url:"/company/setPassword",
            type:"POST",
            data:{Password:password},
            success:function (msg) {
                if(msg.data == 1){
                    $('.passwordOk').show();
                    setTimeout('$(".passwordOk").hide()',1000);
                }else {
                    $('.passwordNo').show();
                    setTimeout('$(".passwordNo").hide()',1000);
                }

            }
        })
    }else{
        $('.passwordFalse').show();
        setTimeout('$(".passwordFalse").hide()',1000);
    }
}
function updatePassword() {
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    if(oldPassword == '' || oldPassword =="" || oldPassword ==undefined){
        $('.passwordFalse').show();
        setTimeout('$(".passwordFalse").hide()',1000);
    }else{
        if(newPassword == '' || newPassword =="" || newPassword == undefined){
            $('.newPasswordFalse').show();
            setTimeout('$(".newPasswordFalse").hide()',1000);
        }else{
            $.ajax({
                url:"/company/getUser",
                type:"POST",
                success:function (msg) {
                    if(msg.data.password == oldPassword){
                        $.ajax({
                            url:"/company/setPassword",
                            type:"POST",
                            data:{Password:newPassword},
                            success:function (msg) {
                                if(msg.data == 1){
                                    $('.passwordOk').show();
                                    setTimeout('$(".passwordOk").hide()',1000);
                                }else {
                                    $('.passwordNo').show();
                                    setTimeout('$(".passwordNo").hide()',1000);
                                }
                            }
                        })
                    }else {
                        $('.OldpasswordNo').show();
                        setTimeout('$(".OldpasswordNo").hide()',1000);
                    }
                }
            })
        }
    }

}

