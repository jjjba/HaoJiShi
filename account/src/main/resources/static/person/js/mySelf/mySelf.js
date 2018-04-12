var num =100;
$(function () {
    loadPersonalData();

});

function loadPersonalData() {
    $.ajax({
        url:"/personal/getPersonalInfo",
        type: "POST",
        success :function (res) {
            var name,age,avatar,sex,state,hope_city,expect_money,hope_job,info,job_experience,mySelfInfo,myHometown,special,
                recordSchool,onceDo,photo;
            var per =res.data;
            onceDo =per.onceDo;
            if(onceDo == '' || onceDo == null){
                num = num - 5;
            }
            recordSchool =per.recordSchool;
            if(recordSchool == '' || recordSchool == null){
                num = num - 5;
            }
            special =per.special;
            if(special == '' || special == null){
                num = num - 5;
            }
            myHometown =per.myHometown;
            if(myHometown == '' || myHometown == null){
                num = num - 5;
            }
            mySelfInfo =per.mySelfInfo;
            if(mySelfInfo == '' || mySelfInfo == null){
                num = num - 5;
            }
            job_experience =per.job_experience;
            if(job_experience == '' || job_experience == null){
                num = num - 5;
            }
            info =per.info;
            if(info == '' || info == null){
                num = num - 5;
            }
            hope_job =per.hope_job;
            if(hope_job == '' || hope_job == null){
                num = num - 5;
            }
            expect_money =per.expect_money;
            if(expect_money == '' || expect_money == null){
                num = num - 5;
            }
            photo =per.photo;
            if(photo == '' || photo == null){
                num = num - 5;
            }
            hope_city =per.hope_city;
            if(hope_city == '' || hope_city == null){
                num = num - 5;
            }
            state =per.state;
            if(state == '' || state == null){
                num = num - 5;
                state ="未填写"
            }
            name =per.name;
            if(name == '' || name == null){
                name ="未填写"
                num = num - 10;
            }
            age =per.age;
            if(age == '' || age == null){
                num = num - 10;
                age ="未填写"
            }
            avatar =per.avatar;
            if(avatar == '' || avatar == null){
                num = num - 10;
                if(per.sex == '男'){
                    avatar="../../person/images/tupian.png";
                }else{
                    avatar="../../person/images/tupian02.png";
                }

            }

            sex =per.sex;
            if(sex == null || sex == ""){
                num = num - 10;
            }
            if(sex == '男'){
                sex="../../person/images/biao05.png";
            }else{
                sex="../../person/images/biao06.png";
            }
            $('.sblefs').append('<img src="'+avatar+'" />');
            $('.sbyours').append('<div class="woemignzi">'+name+'<span><img src="'+sex+'">'+age+'</span></div><div class="grxhzls">'+state+'</div>');
            if(sex == '../../person/images/biao05.png'){
                $('.woemignzi').addClass("lylyus");
            }else{
                $('.woemignzi').addClass("lylyus02");
            }
            $('.progressbar span').html(num);
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