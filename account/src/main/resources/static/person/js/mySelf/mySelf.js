
$(function () {
    loadPersonalData();


    $('.progressbar').each(function(index, el) {
        var num = $(this).find('span').text();
        $(this).addClass('progressbar-' + num);
    });

});

function loadPersonalData() {
    $.ajax({
        url:"/personal/getPersonalInfo",
        type: "POST",
        success :function (res) {
            var name,age,avatar,sex,state;
            var per =res.data;
            state =per.state;
            if(state == '' || state == null){
                state ="未填写"
            }
            name =per.name;
            if(name == '' || name == null){
                name ="未填写"
            }
            age =per.age;
            if(age == '' || age == null){
                age ="未填写"
            }

            var sbjltitis ='<div class="sbtopxxs">'+
                '<div class="sblefs">'+
                '<div class="icon"></div>'+
                '</div>'+
                '<div class="sbyours">'+
                '<div class="lylyus">'+name+' <span class="sex"></span></div>'+
                '<div class="grxhzls">'+state+'</div>'+
                '</div>'+
                '</div>'+
                '<a href="#">'+
                '<div class="xibkass">'+
                '<div class="progressbar">'+
                '<span>35</span>%'+
                '</div>'+
                '<div class="ycbuyss">'+
                '完善简历，获取更多工作机会'+
                '</div>'+
                '<div class="ycjtouis"><img src="../../person/images/yjts.png" /></div>'+
                '</div>'+
                '</a>';
            $('.sbjltitis').append(sbjltitis);
            avatar =per.avatar;
            if(avatar == '' || avatar == null){
                $('.icon').append('<img src="../../person/images/tupian.png}" />');
            }else {
                $('.icon').append('<img src="'+avatar+'" />');
            }
            sex =per.sex;
            if(sex == '男'){
                $('.sex').append('<img src="../../person/images/biao05.png">'+age);
            }else{
                $('.icon').append('<img src="../../person/images/biao06.png">'+age);
            }
        },
        error :function (res) {

        }

    })
}

function collectPosition() {
    window.location.href="/transition/collect_position";
}

function deliveryRecords() {
    window.location.href="/transition/delivery_records";
}

function commonProblem() {
    window.location.href="/transition/common_problem";
}

function accountSettings() {
    window.location.href="/transition/account_settings";
}

function switchingIdentity() {
    window.location.href="/transition/switching_identity";
}