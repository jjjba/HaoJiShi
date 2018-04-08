var isRegist;

$(function () {
    $.ajax({
        url:"../personal/getPersonalState",
        type: "POST",
        success :function (res) {
            isRegist =res.data;
            if(isRegist == "2"){
                $('.lylyus').append("请完善信息");
            }else if(isRegist == "3"){
                $('.lylyus').append("登陆/注册");
            }
        }
        });

    $('.dyikuys').click(function () {
        if(isRegist == "2"){
            window.location.href="../transition/go_wan_shan_xin_xi";
        }else if(isRegist == "3"){
            window.location.href="../transition/go_zhu_ce1";
        }
    })

    $('.progressbar').each(function(index, el) {
        var num = $(this).find('span').text();
        $(this).addClass('progressbar -' + num);
    });

});

// function collectPosition() {
//     if(isRegist == "2"){
//         window.location.href="";
//     }else if(isRegist == "3"){
//         window.location.href="";
//     }
// }
//
// function deliveryRecords() {
//     if(isRegist == "2"){
//         window.location.href="";
//     }else if(isRegist == "3"){
//         window.location.href="";
//     }
// }
//
// function commonProblem() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }
// }
//
// function accountSettings() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }
// }
//
// function switchingIdentity() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }}
//
// function goIndex() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }}
//
// function goPosition() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }}
//
// function privacySetting() {
//     if(isRegist == "2"){
//         $('.lylyus').append("请完善信息");
//     }else if(isRegist == "3"){
//         $('.lylyus').append("登陆/注册");
//     }}