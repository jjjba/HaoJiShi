﻿
var hopeMoney;
var experience;
var school;

$(function() {


    loadPersonalInfo();



});

function loadPersonalInfo() {
    $.ajax({
        type: "POST",
        url: "/personal/getPersonalInfo",
        success: function (res) {
            if (res.success) {
                $('.txuyse').empty();
                $('.zwjisss').empty();
                $('#money').empty();
                var item = res.data;
                var id, name, age, sex, avatar, state, mySelfInfo, special, expect_money, hope_job, hope_city, job_experience,
                    recordSchool, onceDo, myHometown, photo;

                    id = item.id;
                    name =item.name;
                    if(name == null || name == ""){
                        name ="未填写";
                    }
                    age =item.age;
                    if(age == null || age == ""){
                        age ="未填写";
                    }
                    avatar =item.avatar;
                    if(avatar == null || avatar == ""){
                        if(item.sex == "男"){
                            avatar ="../../person/images/tupian.png";
                        }else {
                            avatar ="../../person/images/tupian02.png";
                        }

                    }
                    sex =item.sex;
                    if(sex == "男"){
                        sex ="../person/images/biao05.png";
                    }else {
                        sex ="../person/images/biao06.png";
                    }

                    state =item.state;
                    if(state == null || state == ""){
                        state ="未填写";
                    }
                    mySelfInfo =item.mySelfInfo;
                    if(mySelfInfo == null || mySelfInfo == ""){
                        mySelfInfo ="您还没有填写有关您的信息呦~~";
                    }
                    special =item.special;
                    if(special == null || special == ""){
                        special ="未填写";
                    }
                    expect_money =item.expect_money;
                    if(expect_money == null || expect_money == ""){
                        expect_money ="未填写";
                    }
                    hope_job =item.hope_job;
                    if(hope_job == null || hope_job == ""){
                        hope_job ="未填写";
                    }
                    hope_city =item.hope_city;
                    if(hope_city == null || hope_city == ""){
                        hope_city ="未填写";
                    }
                    job_experience =item.job_experience;
                    if(job_experience == null || job_experience == ""){
                        job_experience ="未填写";
                    }
                    recordSchool =item.recordSchool;
                    if(recordSchool == null || recordSchool == ""){
                        recordSchool ="未填写";
                    }
                    onceDo =item.onceDo;
                    if(onceDo == null || onceDo == ""){
                        onceDo ="未填写";
                    }
                    myHometown =item.myHometown;
                    if(myHometown == null || myHometown == ""){
                        myHometown ="未填写";
                    }
                    photo =item.photo;
                    if(photo == null || photo == ""){
                        photo ="您还没有上传相片呦~~~";
                    }

                    var txuyse = '<div class="sbtopxxs">'+
                        '<div class="sblefs">' +
                        '<img src="'+avatar+'" />' +
                        '</div>' +
                        '<div class="sbyours">' +
                        '<div class="lylyus">'+name+' <span><img src="'+sex+'">'+age+'</span></div>' +
                        '<div class="grxhzls">'+state+'</div>'+
                        '<div class="ycjstts">'+
                        '<a href="#" onclick="goWoDeJiBenXinXi()">基本信息 <img src="../../person/images/yjts.png" /></a>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                    $('.txuyse').append(txuyse);

                var myselfInfo ='<div class="zwjisss">'+
                    '<a href="#" onclick="loadMySelfInfo()">'+
                    '<div class="stopsis">'+
                    '<div class="zuoius01">自我介绍</div>'+
                    '<div class="zuoius02">'+mySelfInfo+'</div>'+
                    '<div class="zuoius03"><img src="../../person/images/yjts.png" /></div>'+
                    '</div>'+
                    '</a>'+
                    '<a href="#" onclick="loadSpecial()">'+
                    '<div class="sryikuys clearfix">'+
                    '<div class="fl zcsyks">个人标签</div>'+
                    '<div class="fr youyss">'+
                    '<img src="../../person/images/yjts.png" class="yjtiss" />'+
                    '</div>'+
                    '</div>'+
                    '</a>'+
                    '</div>';
                $('.myselfInfo').append(myselfInfo);

                    var money ='<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">期望薪资</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger1" class="zhiiis">'+expect_money+'</div>'+
                        '<script type="text/javascript">\n' +
                        '                var weekdayArr=[\'3000元以下/月\',\'3000-5000/月\',\'5000-8000/月\',\'8000-12000/月\',\'12000-15000/月\',\'15000以上/月\'];\n' +
                        '                var mobileSelect1 = new MobileSelect({\n' +
                        '                    trigger: \'#trigger1\', \n' +
                        '                    title: \'\',  \n' +
                        '                    wheels: [\n' +
                        '                                {data: weekdayArr}\n' +
                        '                            ],\n' +
                        '                    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                    transitionEnd:function(indexArr, data){\n' +
                        '                        hopeMoney = data;\n' +
                        '                    },\n' +
                        '                    callback:function(indexArr, data){\n' +
                        '                        hopeMoney = data;\n' +
                        '                    }\n' +
                        '                });\n' +
                        '            </script>'+
                        '</div> <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>'+
                        '<a href="#" onclick="goMyHopeJob()">'+
                        '<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">求职岗位</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger2" class="zhiiis">'+hope_job+'</div>'+
                        '</div> <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '<a href="#" onclick="goMyHopeCity()">'+
                        '<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">意向城市</div>'+
                        '<div class="fr youkuiss">'+
                        hope_city+' <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '<div class="sryikuys clearfix">'+
                        '<div class="fl zcsyks">工作经验</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger3" class="zhiiis">'+job_experience+'</div>'+
                        '<script type="text/javascript">\n' +
                        '                var weekdayArr3=[\'不足1年经验\',\'1年经验以上\',\'3年经验以上\',\'5年经验以上\',\'8年经验以上\',\'10年经验以上\'];\n' +
                        '                var mobileSelect1 = new MobileSelect({\n' +
                        '                    trigger: \'#trigger3\', \n' +
                        '                    title: \'\',  \n' +
                        '                    wheels: [\n' +
                        '                                {data: weekdayArr3}\n' +
                        '                            ],\n' +
                        '                    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                    transitionEnd:function(indexArr, data){\n' +
                        '                        experience =data;\n' +
                        '                    },\n' +
                        '                    callback:function(indexArr, data){\n' +
                        '                        experience =data;\n' +
                        '                    }\n' +
                        '                });\n' +
                        '            </script>'+
                        '</div> <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>';
                    $('#money').append(money);

                    var xueli='<div class="sryikuys clearfix">'+
                        '<div class="fl zcsyks">最高学历</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger4" class="zhiiis">'+recordSchool+'</div>'+
                        '<script type="text/javascript">\n' +
                        '                var weekdayArr4=[\'幼儿园\',\'小学\',\'初中\',\'中专\',\'高中\',\'大专\',\'本科\',\'硕士研究生\',\'博士研究生\'];\n' +
                        '                var mobileSelect1 = new MobileSelect({\n' +
                        '                    trigger: \'#trigger4\', \n' +
                        '                    title: \'\',  \n' +
                        '                    wheels: [\n' +
                        '                                {data: weekdayArr4}\n' +
                        '                            ],\n' +
                        '                    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                    transitionEnd:function(indexArr, data){\n' +
                        '                        school =data;\n' +
                        '                    },\n' +
                        '                    callback:function(indexArr, data){\n' +
                        '                        school =data;\n' +
                        '                    }\n' +
                        '                });\n' +
                        '            </script>'+
                        '</div> <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div></div>'+
                        '<a href="#">'+
                        '<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">曾经做过</div>'+
                        '<div class="fr youkuiss">'+
                        onceDo+' <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div></div></a>'+
                        '<a href="#">'+
                        '<div class="sryikuys clearfix">'+
                        '<div class="fl zcsyks">我的家乡</div>'+
                        '<div class="fr youkuiss">'+
                        myHometown+' <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div></a>';
                    $('#xueli').append(xueli);


            }
        }
    });
}

function save() {
    if(school != null){
        school =school.toString()
    }
    if(experience != null){
        experience =experience.toString()
    }
    if(hopeMoney != null){
        hopeMoney =hopeMoney.toString()
    }
    
    $.ajax({
        url:"/personal/updatePersonalByPersonalId",
        type:"POST",
        data:{
            recordSchool : school,
            jobExperience :experience,
            expectMoney : hopeMoney,
        },
        success : function (res) {
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        },
        error :function () {
            $(".toolbarframe03").show()
            setTimeout('$(".toolbarframe03").hide()',1000);
        }
    })
}


function loadMySelfInfo() {
    window.location.href="/transition/go_personal_profile";
}

function loadSpecial() {
    window.location.href="/transition/update_my_special";
}

function onBack() {
    window.location.href="/transition/transition_goMySelf";
}

function goMyHopeCity() {
    window.location.href="/transition/go_my_hope_city";
}

function goMyHopeJob() {
    window.location.href="/transition/go_my_hope_job";
}

function goWoDeJiBenXinXi() {
    window.location.href="/transition/go_wo_de_ji_ben_xin_xi";
}