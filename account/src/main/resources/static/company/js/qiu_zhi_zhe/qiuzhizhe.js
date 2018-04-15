
$(function () {
    loadPersonal();
});

function loadPersonal(){
    var phone = $.cookie("phone");
    console.log(phone);
    $.ajax({
        url:"/company/getIndexPersonal",
        type:"POST",
        data:{phone:phone},
        success : function (res) {
            sessionStorage.setItem("com_id",res.dataOne);
            console.log(res);
            $('.personaldata').empty();
            var list =res.data;
            var avatar,sex,cla;
            $.each(list, function (index, item) {
                avatar =item.avatar;
                phone =item.phone;
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
                    cla = "<div class=\"fl rminsii\">";
                }else {
                    sex ="../../company/images/biao06.png"
                    cla = "<div class=\"fl rminsii02\">";
                }
                var personalList ='<a href="#" onclick="loadPersonalInfoById(&quot;'+item.id+'&quot;)" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    cla+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expect_money +'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.job_experience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div></a>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hope_job+'</div>'+
                    '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="puanduan(&quot;'+item.id+'&quot;)"/></div>'+
                    '</div>'+
                    '</div>';
                $('.personaldata').append(personalList);
            });
        }
    })
}

function loadPersonalInfoById(id) {
    console.log(id);
    sessionStorage.setItem("where","zuoce");
    sessionStorage.setItem("po_id",id);
    window.location.href="/transition/go_qiu_zhi_zhe_xiang_qing";
}



function puanduan(id) {
    var com_id = sessionStorage.getItem("com_id");
    console.log(com_id);
    console.log("进来了");
    if(com_id != null && com_id!='' && com_id!="" && com_id !=undefined && com_id>0){
        $.ajax({
            url: "/company/PDJyMy",
            data:{id:id},
            success: function (msg) {
                if (msg.data == 1) {
                    //正常开通
                    if (msg.dataOne != null) {
                        console.log(msg.dataOne);
                        window.location.href = "tel:" + msg.dataOne;
                    }
                }
                if (msg.data == 2) {
                    //未开通
                    $("#weikaitong").show();
                }
                if (msg.data == 3) {
                    //已经过期了
                    $("#yijingguoqi").show();
                }
            }
        })
    }else {
        console.log("进来了");
        $("#dengludianhua").show();
    }

}

function goIndex() {
    window.location.href="/transition/go_company_index"
}

function gowode() {
    window.location.href="/transition/go_wo_de";
}


function fenlei(tj) {
    sessionStorage.setItem("tj",tj);
    sousuo();
}
function Xzsex(sex) {
        sessionStorage.setItem("sex",sex);
}
function Xzage(age) {
        sessionStorage.setItem("age",age);
}

function queding() {
    sousuo();
}
function sousuo() {
    var fenlei = sessionStorage.getItem("tj");
    var sex = sessionStorage.getItem("sex");
    var age = sessionStorage.getItem("age");
    $.ajax({
        url:"/company/tjcx",
        data:{zw:fenlei,sex:sex,age:age},
        success:function (res) {
            console.log(res);
            $('.personaldata').empty();
            var list =res.data;
            var avatar,sex,cla;
            $.each(list, function (index, item) {
                avatar =item.avatar;
                phone =item.phone;
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
                    cla = "<div class=\"fl rminsii\">";
                }else {
                    sex ="../../company/images/biao06.png"
                    cla = "<div class=\"fl rminsii02\">";
                }
                var personalList ='<a href="#" onclick="loadPersonalInfoById(&quot;'+item.id+'&quot;)" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    cla+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expect_money +'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.job_experience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div></a>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hope_job+'</div>'+
                    '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="puanduan(&quot;'+item.id+'&quot;)"/></div>'+
                    '</div>'+
                    '</div>';
                $('.personaldata').append(personalList);
            });
        }
    })
}

function tjcx() {
    var phone = $.cookie("phone");
    console.log(phone);
    $.ajax({
        url:"/company/getIndexPersonal",
        type:"POST",
        data:{phone:phone,tj:tj,sex:sex,age:age},
        success : function (res) {
            sessionStorage.setItem("com_id",res.dataOne);
            console.log(res);
            $('.personal').empty();
            var list =res.data;
            var avatar,sex,cla;
            $.each(list, function (index, item) {
                avatar =item.avatar;
                phone =item.phone;
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
                    cla = "<div class=\"fl rminsii\">";
                }else {
                    sex ="../../company/images/biao06.png"
                    cla = "<div class=\"fl rminsii02\">";
                }
                var personalList ='<a href="#" onclick="loadPersonalInfoById(&quot;'+item.id+'&quot;)" >'+
                    '<div class="rcxinxis">'+
                    '<div class="rcktops">'+
                    '<div class="reclefs">'+
                    '<img src="'+avatar+'" />'+
                    '</div>'+
                    '<div class="recryous">'+
                    '<div class="clearfix rcvsise">'+
                    cla+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                    '<div class="fr ciiuss">'+item.expect_money +'</div>'+
                    '</div>'+
                    '<div class="xnxyusie"> '+item.job_experience+' | '+item.state+' | '+item.address+' </div>'+
                    '</div>'+
                    '</div></a>'+
                    '<div class="pxbiuss">'+
                    '<div class="pxlefts">'+item.hope_job+'</div>'+
                    '<div class="pxyouls"><img src="./../company/images/biao07.png" onclick="puanduan(&quot;'+item.id+'&quot;)"/></div>'+
                    '</div>'+
                    '</div>';
                $('.personaldata').append(personalList);
            });
        }
    })
}