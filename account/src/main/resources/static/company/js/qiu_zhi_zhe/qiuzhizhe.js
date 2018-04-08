
var positionName;
var sex;
var age;
var isRegist;//判断是否登陆
var isKuaiZhao;//是否开通快招服务  1开通并且未到期 2开通已到期 3未开通
var phone;  //求职者手机号
$(function () {
    loadPersonalData();
    loadUserInfo();
    $('#jishi').click(function (e) {
        positionName =$(e.target).attr("data");
        console.log("positionName======="+positionName);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#guanli').click(function (e) {
        positionName =$(e.target).attr("data");
        console.log("positionName======="+positionName);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#qianting').click(function (e) {
        positionName =$(e.target).attr("data");
        console.log("positionName======="+positionName);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#houqin').click(function (e) {
        positionName =$(e.target).attr("data");
        console.log("positionName======="+positionName);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#peixun').click(function (e) {
        positionName =$(e.target).attr("data");
        console.log("positionName======="+positionName);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#sex').click(function (e) {
        sex= $(e.target).attr("data");
        console.log("sex======="+sex);
        loadPersonalByParams(positionName,sex,age);
    });
    $('#age').click(function (e) {
        age= $(e.target).attr("data");
        console.log("age======="+age);
        loadPersonalByParams(positionName,sex,age);
    });
});


function loadPersonalData() {
    // var data ={
    //
    // }
    $.ajax({
        url:"/personal/getPersonal",
        type:"POST",
        // data:data,
        success :function (res) {
            if (res.success) {
                $('.personaldata').empty();
                var list = res.data;
                var avatar, sex;
                $.each(list, function (index, item) {
                    avatar =item.avatar;
                    if(avatar == null || avatar == ""){
                        if(item.sex == "男"){
                            avatar ="../../company/images/tupian01.png"
                        }else {
                            avatar ="../../company/images/tupian02.png"
                        }
                    }
                    sex =item.sex;
                    if(sex == "男"){
                        sex ="../../company/images/biao05.png"
                    }else {
                        sex ="../../company/images/biao06.png"
                    }
                    phone =item.phone;
                    var personaldata ='<a href="#" onclick="loadPersonalInfoById(\'/transition/go_qiu_zhi_zhe_xiang_qing?id='+item.id+'\')" >'+
                        '<div class="rcxinxis">'+
                        '<div class="rcktops">'+
                        '<div class="reclefs">'+
                        '<img src="'+avatar+'" />'+
                        '</div>'+
                        '<div class="recryous">'+
                        '<div class="clearfix rcvsise">'+
                        '<div class="fl rminsii">'+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                        '<div class="fr ciiuss">'+item.expect_money+'</div>'+
                        '</div>'+
                        '<div class="xnxyusie"> '+item.job_experience+' | '+item.state+' | '+item.address+' </div>'+
                        '</div>'+
                        '</div>'+
                        '<div class="pxbiuss">'+
                        '<div class="pxlefts">'+item.hope_job+'</div>'+
                        '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="tellPhone()"/></div>'+
                        '</div>'+
                        '</div>'+
                        '</a>';
                    $('.personaldata').append(personaldata);
                })
            }
        }
    })    
}
function loadPersonalByParams(positionName,sex,age) {
    $.ajax({
        url:"/personal/getPersonalByParams",
        type:"POST",
        data:{
            positionName : positionName,
            sex : sex,
            age : age,
        },
        success :function (res) {
            $('.personaldata').empty();
            var list = res.data;
            var avatar, sex;
            $.each(list, function (index, item) {
                avatar =item.avatar;
                if(avatar == null || avatar == ""){
                    if(item.sex == "男"){

                        avatar ="../../company/images/tupian01.png"
                    }else {
                        avatar ="../../company/images/tupian02.png"
                    }
                }
                sex =item.sex;
                if(sex == "男"){
                    sex ="../../company/images/biao05.png"
                }else {
                    sex ="../../company/images/biao06.png"
                }
                var personaldata ='<a href="#" onclick="loadPersonalInfoById(\'/transition/go_qiu_zhi_zhe_xiang_qing?id='+item.id+'\')" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    '<div class="fl rminsii">'+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expectMoney+'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.jobExperience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hopeJob+'</div>'+
                    '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="tellPhone()"/></div>'+
                    '</div>'+
                    '</div>'+
                    '</a>';
                $('.personaldata').append(personaldata);
            })
        }
    })
}

function loadUserInfo() {
    $.ajax({
        url:"/company/loadUserCompanyInfo",
        type:"POST",
        success :function (res) {
            console.log("data============="+JSON.stringify(res.data));
            isCollect =res.data[0].isCollect;
            isKuaiZhao =res.data[0].isKuaiZhao;
            isRegist =res.data[0].isRegist;
            console.log("isCollect============="+isCollect);
            console.log("isKuaiZhao============"+isKuaiZhao);
            console.log("isRegist=============="+isRegist);
        }
    })
}

function tellPhone() {
    if(isRegist == "2"){
        $('#wanshan').show();
        $("#zaikanwanshan").click(function(){
            $("#wanshan").hide();
        });
        $("#likewanshan").click(function(){
            $("#wanshan").hide();
            window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
        });
    }else if(isRegist == "3"){
        $('#denglu').show();
        $("#zaikandenglu").click(function(){
            $("#denglu").hide();
        });
        $("#likedenglu").click(function(){
            $("#denglu").hide();
            window.location.href="/transition/go_zhu_ce";
        });
    }else {
        if(isKuaiZhao == "1"){
            $.ajax({
                url:"/company/updatePhoneNum",
                type:"POST",
                success : function (res) {
                    console.log("更改次数成功");
                    window.location.href="tel:"+phone;
                },
                error : function (res) {
                    console.log("更改次数失败");
                    window.location.href="tel:"+phone;
                }
            });
        }else if(isKuaiZhao == "2"){
            $('.xianyin03').show();
            $("#zaikanxufei").click(function(){
                $('.xianyin03').hide();
            });
            $("#likexufei").click(function(){
                $('.xianyin03').hide();
                window.location.href="/transition/go_kuai_zhao";
            });
        }else if(isKuaiZhao == "3"){
            $('.xianyin02').show();
            $("#zaikankaitong").click(function(){
                $("#xianyin02").hide();
            });
            $("#likekaitong").click(function(){
                $("#xianyin02").hide();
                window.location.href="/transition/go_kuai_zhao";
            });
        }
    }
}





















function goIndex() {
    window.location.href="/transition/go_company_index"
}

function gowode() {
    window.location.href="/transition/go_wo_de";
}

function loadPersonalInfoById(url) {
    window.location.href=url;
}

