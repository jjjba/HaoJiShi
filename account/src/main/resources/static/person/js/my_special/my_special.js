
var sp = [];
var special;
$(function () {
    $.ajax({
        url:"/personal/getPersonalInfo",
        type:"POST",
        success :function (res) {
            var spec =res.data.special.split(",");
            for(var i = 0;i < spec.length;i++){
                sp.push(spec[i]);
                $('#neirong').append('<li class="'+spec[i]+'">'+spec[i]+'<a href="#" onclick="'+spec[i]+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
            }
        }
    })


    $(".yanzhi").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".shencai").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".jishu").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".xiujiao").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".caier").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".piaoliang").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".keai").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".goutong").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".fengfu").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".chiku").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".qinhe").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".huitou").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".dianzhong").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".quanneng").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".shoufa").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".chengxin").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".xuexi").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".fucong").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".zeren").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".zhixing").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".shuaiqi").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });
    $(".fuwu").click(function(e) {
        special= $(e.target).attr("data");
        $('#neirong').append('<li class="'+special+'">'+special+'<a href="#" onclick="'+special+'()"> <img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
        sp.push(special);
        console.log(sp)
    });

})

function 颜值高() {
    $('.颜值高').remove();
    sp.splice($.inArray('颜值高',sp),1);
    console.log(sp)
}

function 身材好() {
    $('.身材好').remove();
    sp.splice($.inArray('身材好',sp),1);
    console.log(sp)
}
function 技术好() {
    $('.技术好').remove();
    sp.splice($.inArray('技术好',sp),1);
    console.log(sp)
}
function 会修脚() {
    $('.会修脚').remove();
    sp.splice($.inArray('会修脚',sp),1);
    console.log(sp)
}
function 会采耳() {
    $('.会采耳').remove();
    sp.splice($.inArray('会采耳',sp),1);
    console.log(sp)
}
function 年轻漂亮() {
    $('.年轻漂亮').remove();
    sp.splice($.inArray('年轻漂亮',sp),1);
    console.log(sp)
}
function 青春可爱() {
    $('.青春可爱').remove();
    sp.splice($.inArray('青春可爱',sp),1);
    console.log(sp)
}
function 善于沟通() {
    $('.善于沟通').remove();
    sp.splice($.inArray('善于沟通',sp),1);
    console.log(sp)
}
function 经验丰富() {
    $('.经验丰富').remove();
    sp.splice($.inArray('经验丰富',sp),1);
    console.log(sp)
}
function 吃苦耐劳() {
    $('.吃苦耐劳').remove();
    sp.splice($.inArray('吃苦耐劳',sp),1);
    console.log(sp)
}
function 有亲和力() {
    $('.有亲和力').remove();
    sp.splice($.inArray('有亲和力',sp),1);
    console.log(sp)
}
function 回头客多() {
    $('.回头客多').remove();
    sp.splice($.inArray('回头客多',sp),1);
    console.log(sp)
}
function 点钟达人() {
    $('.点钟达人').remove();
    sp.splice($.inArray('点钟达人',sp),1);
    console.log(sp)
}
function 全能技师() {
    $('.全能技师').remove();
    sp.splice($.inArray('全能技师',sp),1);
    console.log(sp)
}
function 手法不错() {
    $('.手法不错').remove();
    sp.splice($.inArray('手法不错',sp),1);
    console.log(sp)
}
function 诚信正直() {
    $('.诚信正直').remove();
    sp.splice($.inArray('诚信正直',sp),1);
    console.log(sp)
}
function 学习力强() {
    $('.学习力强').remove();
    sp.splice($.inArray('学习力强',sp),1);
    console.log(sp)
}
function 服从管理() {
    $('.服从管理').remove();
    sp.splice($.inArray('服从管理',sp),1);
    console.log(sp)
}
function 责任心强() {
    $('.责任心强').remove();
    sp.splice($.inArray('责任心强',sp),1);
    console.log(sp)
}
function 执行力强() {
    $('.执行力强').remove();
    sp.splice($.inArray('执行力强',sp),1);
    console.log(sp)
}
function 年轻帅气() {
    $('.年轻帅气').remove();
    sp.splice($.inArray('年轻帅气',sp),1);
    console.log(sp)
}
function 服务意向强() {
    $('.服务意向强').remove();
    sp.splice($.inArray('服务意向强',sp),1);
    console.log(sp)
}

function quedingbaocun() {
    $.ajax({
        url:"/personal/updatePersonalByPersonalOpenid",
        type:"POST",
        data : {
            special : sp.toString()
        },
        success : function (res) {
            window.location.href="/transition/my_resume";
        }
    })
}

function fanhui() {
    window.location.href="/transition/my_resume";
}