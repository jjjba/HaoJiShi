
$(function () {
    loadPersonalData();


    $('.progressbar').each(function(index, el) {
        var num = $('.progressbar').find('span').text();
        $('.progressbar').addClass('progressbar-' + num);
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
            avatar =per.avatar;
            if(avatar == '' || avatar == null){
                if(per.sex == '男'){
                    avatar="../../person/images/tupian.png";
                }else{
                    avatar="../../person/images/tupian02.png";
                }

            }
            sex =per.sex;
            if(sex == '男'){
                sex="../../person/images/biao05.png";
            }else{
                sex="../../person/images/biao06.png";
            }


            var sbjltitis ='<div class="sbtopxxs">'+
                '<div class="sblefs">'+
                '<img src="'+avatar+'" style="width:6.2rem;height:6.2rem;"/>'+
                '</div>'+
                '<div class="sbyours">'+
                '<div class="woemignzi">'+name+' <span><img src="'+sex+'">'+age+'</span></div>'+ //class="lylyus"
                '<div class="grxhzls">'+state+'</div>'+
                '</div>'+
                '</div>'+
                '<a href="/transition/my_resume" onclick="loadPersonalInfo()">'+
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
            if(sex == '../../person/images/biao05.png'){
                $('.woemignzi').addClass("lylyus");
            }else{
                $('.woemignzi').addClass("lylyus02");
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
    window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
}

function goIndex() {
    window.location.href="/transition/transition_goIndex";
}

function goPosition() {
    window.location.href="/transition/transition_all_position";
}

function loadPersonalInfo() {
    window.location.href="/transition/my_resume";
}

function privacySetting() {
    window.location.href="/transition/go_privacy_setting"
}