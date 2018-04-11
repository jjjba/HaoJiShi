var isRegist;

$(function () {
    $.ajax({
        url:"/personal/getPersonalState",
        type: "POST",
        success :function (res) {
            isRegist =res.data.isRegist;
            console.log("========"+isRegist)
            if(isRegist == "2"){
                $('.lylyus').append("请完善信息");
            }else if(isRegist == "3"){
                $('.lylyus').append("登陆/注册");
            }
        }
        });

    $('.dyikuys').click(function (){
        if(isRegist == "2"){
            window.location.href="/transition/go_wan_shan_xin_xi";
        }else if(isRegist == "3"){
            window.location.href="/transition/go_zhu_ce1";
        }
    })
    $('.dengluzhuce').click(function (){
        if(isRegist == "2"){
            window.location.href="/transition/go_wan_shan_xin_xi";
        }else if(isRegist == "3"){
            window.location.href="/transition/go_zhu_ce1";
        }
    })

    $('.progressbar').each(function(index, el) {
        var num = $(this).find('span').text();
        $(this).addClass('progressbar -' + num);
    });
});
function goIndex() {
    window.location.href="/transition/transition_goIndex";
}

function goPosition() {
    window.location.href="/transition/transition_all_position";
}