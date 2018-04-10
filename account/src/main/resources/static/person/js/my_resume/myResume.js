﻿﻿var avatarPath =[];
var hopeMoney;
var experience;
var school;

$(function() {
    loadPersonalInfo();
    setTimeout('configwx()', 100);
    $('.jiahaos').click(function () {
        chooseImage();
    })

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
                $('#xueli').empty();
                var item = res.data;
                var id, name, age, sex, avatar, state, mySelfInfo, special, expect_money, hope_job, hope_city, job_experience,
                    recordSchool, onceDo, myHometown, photo;

                    id = item.id;
                    name =item.name;
                    age =item.age;
                    state =item.state;
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
                    mySelfInfo =item.mySelfInfo;
                    onceDo =item.onceDo;
                    myHometown =item.myHometown;

                    var txuyse = '<div class="sbtopxxs">'+
                        '<div class="sblefs">' +
                        '<img src="'+avatar+'" style="width:6.2rem;height:6.2rem;"/>' +
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
                    '<div class="zuoius02 ziwojieshao">'+mySelfInfo+'</div>'+
                    '<div class="zuoius02 wtxyss wodejieshao" style="display: none">填写自我介绍，快速提升找工作</div>'+
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
                if(mySelfInfo == null || mySelfInfo == ""){
                    $('.wodejieshao').show();
                    $('.ziwojieshao').hide();
                }
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
                    var cuvjdi=
                        '<div class="sryikuys clearfix auiagv">\n' +
                        '         <div class="fl zcsyks afage">最高学历</div>\n' +
                        '     <div class="fr yogzztas aaegaef" style="display: none">\n' +
                        '       <div class="xzzwis">\n' +
                        '           <div id="trigger5" class="zhiiis wtxyss">未填写</div>\n' +
                        '            <script type="text/javascript">\n' +
                        '                var weekdayArr5=["幼儿园","小学","初中","中专","高中","大专","本科","硕士研究生","博士研究生"];\n' +
                        '                var mobileSelect2 = new MobileSelect({\n' +
                        '                    trigger: \'#trigger5\', \n' +
                        '                    title: \'\',  \n' +
                        '                    wheels: [\n' +
                        '                                {data: weekdayArr5}\n' +
                        '                            ],\n' +
                        '                    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                    transitionEnd:function(indexArr, data){\n' +
                        '                        console.log(data);\n' +
                        '                    },\n' +
                        '                    callback:function(indexArr, data){\n' +
                        '                        console.log(data);\n' +
                        '                    }\n' +
                        '                });\n' +
                        '            </script>\n' +
                        '        </div> <img src="../../person/images/yjts.png" class="yjtiss" />\n' +
                        '     </div>\n' +

                        '   </div>\n' +
                        '         <div class="fr yogzztas afavaae">\n' +
                        '             <div class="xzzwis">\n' +
                        '                 <div id="trigger4" class="zhiiis zuigoaxueli">'+recordSchool+'</div>\n' +
                        '                 <script type="text/javascript">\n' +
                        '                     var weekdayArr4=["幼儿园","小学","初中","中专","高中","大专","本科","硕士研究生","博士研究生"];\n' +
                        '                     var mobileSelect1 = new MobileSelect({\n' +
                        '                         trigger: \'#trigger4\',\n' +
                        '                         title: \'\',\n' +
                        '                         wheels: [\n' +
                        '                             {data: weekdayArr4}\n' +
                        '                         ],\n' +
                        '                         position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                         transitionEnd:function(indexArr, data){\n' +
                        '                             console.log(data);\n' +
                        '                         },\n' +
                        '                         callback:function(indexArr, data){\n' +
                        '                             console.log(data);\n' +
                        '                         }\n' +
                        '                     });\n' +
                        '                 </script>\n' +
                        '             </div> <img src="../../person/images/yjts.png" class="yjtiss" />\n' +
                        '         </div>\n' +
                        '     </div>\n' +

                        '     <a href="#" onclick="gocengjingzuoguo()" class="asaeevd">\n' +
                        '         <div class="sryikuys borxboms clearfix">\n' +
                        '             <!--左边开始-->\n' +
                        '             <div class="fl zcsyks">曾经做过</div>\n' +
                        '             <div class="fr youkuiss cnengjingzuoguo" >\n' +
                        onceDo+'            <img src="images/yjts.png" class="yjtiss" />\n' +
                        '             </div>\n' +
                        '         </div>\n' +
                        '     </a>\n' +


                        '     <div class="sryikuys borxboms clearfix aveavgaeg">\n' +
                        '         <div class="fl zcsyks">我的家乡</div>\n' +
                        '         <div class="fr youkuiss">\n' +
                        '             <div class="xzeyosx">\n' +
                        '                 <section class="express-area">\n' +
                        '                     <a id="expressArea" href="javascript:void(0)">\n' +
                        '                         <dl>\n' +
                        '                            <dd class="wtxyss">'+myHometown+'</dd>\n' +
                        '                         </dl>\n' +
                        '                     </a>\n' +
                        '                 </section>\n' +
                        '                 <section id="areaLayer" class="express-area-box">\n' +
                        '                     <header>\n' +
                        '                         <h3>选择地区</h3>\n' +
                        '                         <a id="backUp" class="back" href="javascript:void(0)" title="返回"></a>\n' +
                        '                         <a id="closeArea" class="close" href="javascript:void(0)" title="关闭"></a>\n' +
                        '                     </header>\n' +
                        '                     <article id="areaBox">\n' +
                        '                         <ul id="areaList" class="area-list"></ul>\n' +
                        '                     </article>\n' +
                        '                 </section>\n' +
                        '                 <div id="areaMask" class="mask"></div>\n' +
                        '                 <script src="../../person/js/wan_shan_xin_xi/jquery.area.js"></script>\n' +
                        '             </div>\n' +
                        '             <img src="../../person/images/yjts.png" class="yjtiss" />\n' +
                        '         </div>\n' +
                        '     </div>';

                if(recordSchool == null || recordSchool == ""){
                    $('.aaegaef').show();
                    $('.afavaae').hide();
                }
                if(onceDo == null || onceDo == ""){
                    $('.asaeevd').show();
                    $('.wocengjafn').hide();
                }
                if(myHometown == null || myHometown == ""){
                    $('.qwqwtqf').show();
                    $('.aveavgaeg').hide();
                }

                $('.cuvjdi').append(cuvjdi);

            }
        }
    });
}
function gocengjingzuoguo() {
    window.location.href="/transition/go_ceng_jing_zuo_guo";
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
            avatar : avatarPath.join(","),
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

function configwx() {
    var url = window.location.href;
    $.ajax({
        url: "/weChat/getSignInfo",
        type: "GET",
        data: {
            "url": url
        },
        success: function (res) {
            var nonce_str = res.nonce_str;
            var timesta = res.time_stamp;
            var signatur = res.signa_ture;
            var appid = res.appid;

            wx.config({
                debug: true,
                appId: appid,
                timestamp: timesta,
                nonceStr: nonce_str,
                signature: signatur,
                jsApiList: [
                    'chooseImage',
                    'previewImage',
                    'uploadImage',
                    'downloadImage',
                    'getLocalImgData'
                ]
            });
        }
    });
}
function chooseImage() {
    var images = {
        localId: [],
        serverId: [],
        imgbase64: []
    };
    //拍照或从手机相册中选图接口
    wx.chooseImage({
        count: 5, // 最多能选择多少张图片，默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            images.localId = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            var imageNumbers = res.localIds.length;
            for(var i = 0;i < imageNumbers;i++){
                wx.uploadImage({
                    localId: images.localId[i], // 需要上传的图片的本地ID，由chooseImage接口获得
                    isShowProgressTips: 1, // 默认为1，显示进度提示
                    success: function (res) {
                        $.ajax({
                            type: 'POST',
                            url: "/weChat/uploadWeiXinImg",
                            data: {
                                mediaId: res.serverId
                            },
                            success: function (res) {
                                var imgUrl =res.data.imgUrl;
                                $('.tupians').append('<img src="'+imgUrl+'" />');
                                avatarPath.push(imgUrl);
                            }
                        });
                    }
                });
            }

        }
    });

}