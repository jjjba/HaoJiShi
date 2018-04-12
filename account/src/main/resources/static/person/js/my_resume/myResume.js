﻿var avatarPath =[];
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
                    photo =item.photo;
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
                    expect_money =item.expect_money;
                    job_experience =item.job_experience;
                    hope_city =item.hope_city;
                    recordSchool =item.recordSchool;
                    mySelfInfo =item.mySelfInfo;
                    onceDo =item.onceDo;
                    myHometown =item.myHometown;
                    hope_job =item.hope_job;
                    if(onceDo != null && onceDo != "") {
                        if(onceDo.toString().length > 15){
                            onceDo =onceDo.toString().substring(0,15)+"...";
                        }
                    }
                    if(hope_city != null && hope_city != "") {
                        if(hope_city.toString().length > 15){
                            hope_city =hope_city.toString().substring(0,15)+"...";
                        }
                    }
                    if(hope_job != null && hope_job != "") {
                        if(hope_job.toString().length > 15){
                            hope_job =hope_job.toString().substring(0,15)+"...";
                        }
                    }
                    if(special != null && special != "") {
                        if(special.toString().length > 15){
                            special =special.toString().substring(0,15)+"...";
                        }
                    }
                    if(mySelfInfo != null && mySelfInfo != "") {
                        if(mySelfInfo.toString().length > 15){
                            mySelfInfo =mySelfInfo.toString().substring(0,15)+"...";
                        }
                    }
                    if(onceDo != null && onceDo != ""){
                        $('.cengjingzuoguos').removeClass("wtxyss");
                    }
                    if(photo == null || photo == ""){
                        $('.fbits').addClass("wtxyss");
                    }else {
                        $('.fbits').html("温馨提示:点击照片即可删除照片呦🙂").removeClass("wtxyss");
                    }
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

                var ziwodejieho ='<div class="zwjisss">'+
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
                    '<div class="fr youyss zhiiis wdoaioqian">'+
                    special+'<img src="../../person/images/yjts.png" class="yjtiss" />'+
                    '</div>'+
                    '</div>'+
                    '</a>'+
                    '</div>';
                $('.ziwodejieho').append(ziwodejieho);
                if(mySelfInfo == null || mySelfInfo == ""){
                    $('.wodejieshao').show();
                    $('.ziwojieshao').hide();
                }
                if(special == null || special == ""){
                    $('.wdoaioqian').html("未选择").addClass("wtxyss");
                }else {
                    $('.wdoaioqian').removeClass("wtxyss");
                }
                    var money ='<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">期望薪资</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger1" class="zhiiis wodegonzi">'+expect_money+'</div>'+
                        '<script type="text/javascript">\n' +
                        '                var weekdayArr=[\'3000元以下/月\',\'3000-5000元/月\',\'5000-8000元/月\',\'8000-12000元/月\',\'12000-15000元/月\',\'15000以上元/月\'];\n' +
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
                        '<div class="zhiiis wodegongzuoganwei">'+hope_job+'</div>'+
                        '</div> <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '<a href="#" onclick="goMyHopeCity()">'+
                        '<div class="sryikuys borxboms clearfix">'+
                        '<div class="fl zcsyks">意向城市</div>'+
                        '<div class="fr youkuiss woyaogongzuochenghsi">'+
                        hope_city+' <img src="../../person/images/yjts.png" class="yjtiss" />'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '<div class="sryikuys clearfix">'+
                        '<div class="fl zcsyks">工作经验</div>'+
                        '<div class="fr yogzztas">'+
                        '<div class="xzzwis">'+
                        '<div id="trigger3" class="zhiiis gongzuojingyan">'+job_experience+'</div>'+
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
                    if(job_experience == null || job_experience == ""){
                        $('.gongzuojingyan').html("未选择").addClass("wtxyss");
                    }
                    if(expect_money == null || expect_money == ""){
                        $('.wodegonzi').html("未选择").addClass("wtxyss");
                    }
                    hope_job =item.hope_job;
                    if(hope_job == null || hope_job == ""){
                        $('.wodegongzuoganwei').html("未选择").addClass("wtxyss");
                    }
                    hope_city =item.hope_city;
                    if(hope_city == null || hope_city == ""){
                        $('.woyaogongzuochenghsi').html("未选择").addClass("wtxyss");
                    }
                    var cuvjdi=
                        '<div class="sryikuys clearfix">\n' +
                        '     <!--左边开始-->\n' +
                        '     <div class="fl zcsyks">最高学历</div>\n' +
                        '     <!--左边结束-->\n' +
                        '     \n' +
                        '     <!--右边开始-->\n' +
                        '     <div class="fr yogzztas">\n' +
                        '       \n' +
                        '       <!--选择职位开始-->\n' +
                        '       <div class="xzzwis">\n' +
                        '           <div id="trigger4" class="zhiiis zuigoaxueli">'+recordSchool+'</div>\n' +
                        '            <script type="text/javascript">\n' +
                        '            \n' +
                        '                var weekdayArr4=["幼儿园","小学","初中","中专","高中","大专","本科","硕士研究生","博士研究生"];\n' +
                        '                \n' +
                        '                \n' +
                        '                var mobileSelect1 = new MobileSelect({\n' +
                        '                    trigger: \'#trigger4\', \n' +
                        '                    title: \'\',  \n' +
                        '                    wheels: [\n' +
                        '                                {data: weekdayArr4}\n' +
                        '                            ],\n' +
                        '                    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0\n' +
                        '                    transitionEnd:function(indexArr, data){\n' +
                        '                        $(".zuigoaxueli").html(data).remove("wtxyss");' +
                        '                    },\n' +
                        '                    callback:function(indexArr, data){\n' +
                        '                        $(".zuigoaxueli").html(data).remove("wtxyss");\n' +
                        '                    }\n' +
                        '                });\n' +
                        '            \n' +
                        '            \n' +
                        '            </script>\n' +
                        '        </div> <img src="../../person/images/yjts.png" class="yjtiss" />\n' +
                        '       <!--选择职位结束-->\n' +
                        '       \n' +
                        '     </div>\n' +
                        '     <!--右边结束-->\n' +
                        '     \n' +
                        '   </div>\n' +
                        '   <!--最高学历结束-->\n' +
                        '   \n' +
                        '   <!--曾经做过开始-->\n' +
                        '   <a href="#" onclick="gocengjingzuoguo()">\n' +
                        '     <div class="sryikuys borxboms clearfix">\n' +
                        '         <!--左边开始-->\n' +
                        '         <div class="fl zcsyks">曾经做过</div>\n' +
                        '         <div class="fr yogzztas">' +
                        '               <div class="xzzwis">' +
                        '<div id="trigger4" class="zhiiis cengjingzuoguos">'+onceDo+'</div>' +
                        '               </div><img src="../../person/images/yjts.png" class="yjtiss" />' +
                        '         </div>' +
                        '         \n' +
                        // '         <!--右边开始-->\n' +
                        // '         <div class="fr youkuiss cengjingzuoguos">\n' +
                        // '         </div>\n' +
                        // '         <!--右边结束-->\n' +
                        '         \n' +
                        '     </div>\n' +
                        '   </a>\n' +
                        '   <!--曾经做过结束-->\n' +
                        '   \n' +
                        '   <!--我的家乡开始-->\n' +
                        '   <a href="#">\n' +
                        '     <div class="sryikuys clearfix">\n' +
                        '         <!--左边开始-->\n' +
                        '         <div class="fl zcsyks">我的家乡</div>\n' +
                        '         <!--左边结束-->\n' +
                        '         \n' +
                        '         <!--右边开始-->\n' +
                        '         <div class="fr youkuiss">\n' +
                        '        <div class="xzeyosx">\n' +
                        '                 <section class="express-area">\n' +
                        '                     <a id="expressArea" href="javascript:void(0)" class="woejaxaing">\n' +
                        '                         <dl>\n' +
                        '                            <dd>'+myHometown+'</dd>\n' +
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
                        '         <!--右边结束-->\n' +
                        '         \n' +
                        '     </div>\n' +
                        '   </a>\n';

                $('.cuvjdi').append(cuvjdi);
                if(recordSchool == null || recordSchool == ""){
                    $('.zuigoaxueli').html("未选择").addClass("wtxyss");
                }
                if(onceDo == null || onceDo == ""){
                    $('.cengjingzuoguos').html("未选择").addClass("wtxyss");
                }
                if(myHometown == null || myHometown == ""){
                    $('.woejaxaing').html("未选择").addClass("wtxyss");
                }
                if(photo != null && photo != ""){
                    var photos =photo.split(",");
                    for(var i = 0;i < photos.length;i++){
                        avatarPath.push(photos[i]);
                        $('.tupians').append('<a href="#" class="'+photos[i]+'" onclick="delectphoto('+photos[i]+')"><img src="'+photos[i]+'" style="height: 5rem;width: 5rem;"/></a>');
                    }

                }
            }
        }
    });
}
function delectphoto(val) {
    $("."+val).remove();
    avatarPath.splice($.inArray(val,avatarPath),1);
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
            photo : avatarPath.join(","),
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
                debug: false,
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
                                $('.tupians').append('<a href="#" onclick="delectphoto('+imgUrl+')"><img src="'+imgUrl+'" style="height: 5rem;width: 5rem;"/></a>');
                                avatarPath.push(imgUrl);
                            }
                        });
                    }
                });
            }

        }
    });

}