var avatarPath;
$(function () {
    setTimeout('configwx()', 100);

    var hopeCity =sessionStorage.getItem("hopeCity");
    var hopeJob =sessionStorage.getItem("hopeJob");
    var special =sessionStorage.getItem("special");
    var name =sessionStorage.getItem("name");
    var sex =sessionStorage.getItem("sex");
    var age =sessionStorage.getItem("age");
    var state =sessionStorage.getItem("state");
    var gzjy =sessionStorage.getItem("gzjy");

    if(name == null || name == ""){
        $('.namemingzi').append('<input type="text" placeholder="请输入姓名" class="stkius xingming" /> <img src="../../person/images/yjts.png"\n' +
            '                                                                                 class="yjtiss" />')
    }else {
        $('.namemingzi').append('<input type="text" placeholder="请输入姓名" class="stkius xingming" value="'+name+'"/> <img src="../../person/images/yjts.png"\n' +
            '                                                                                 class="yjtiss" />')
    }
    if(sex == null || sex == ""){
        $('.xingbie').append('请选择性别');
    }else {
        $('.xingbie').append(sex);
    }
    if(age == null || age == ""){
        $('.nianliangas').append('<input type="text" placeholder="请输入年龄" class="stkius nianling" /> <img src="../../person/images/yjts.png"\n' +
            '                                                                                class="yjtiss" />')
    }else {
        $('.nianliangas').append('<input type="text" placeholder="请输入年龄" class="stkius nianling" value="'+age+'" /> <img src="../../person/images/yjts.png"\n' +
            '                                                                                class="yjtiss" />')
    }
    if(gzjy == null || gzjy == ""){
        $('.gongzuojingyan').append("请选择工作经验")
    }else {
        $('.gongzuojingyan').append(gzjy)
    }
    if(hopeJob == null || hopeJob == ""){
        $('.woqiuzhifia').append('请选择求职岗位<img src="../../person/images/yjts.png" class="yjtiss" />')
    }else {
        $('.woqiuzhifia').append(hopeJob+'<img src="../../person/images/yjts.png" class="yjtiss" />')
    }
    if(hopeCity == null || hopeCity == ""){
        $('.avjahb').append('请选择意向城市<img src="../../person/images/yjts.png" class="yjtiss" />')
    }else {
        $('.avjahb').append(hopeCity+'<img src="../../person/images/yjts.png" class="yjtiss" />')
    }
    if(special == null || special == ""){
        $('.gerfngj').append('请选择个人优势<img src="../../person/images/yjts.png" class="yjtiss" />')
    }else {
        $('.gerfngj').append(special+'<img src="../../person/images/yjts.png" class="yjtiss" />')
    }
    if(state == null || state == ""){
        $('.gongzuozhuangtai').append('请选择工作状态')
    }else {
        $('.gongzuozhuangtai').append(state)
    }

    $(".xingming").blur(function(){
        sessionStorage.setItem("name",$('.xingming').val());
    })

    $(".nianling").blur(function(){
        sessionStorage.setItem("age",$('.nianling').val());
    })

    $('.bcanius').click(function(){
        var hopeCity =sessionStorage.getItem("hopeCity");
        var hopeJob =sessionStorage.getItem("hopeJob");
        var special =sessionStorage.getItem("special");
        var name =sessionStorage.getItem("name");
        var sex =sessionStorage.getItem("sex");
        var age =sessionStorage.getItem("age");
        var state =sessionStorage.getItem("state");
        var gzjy =sessionStorage.getItem("gzjy");

        console.log("name===="+name)
        console.log("sex====="+sex)
        console.log("age======"+age)
        console.log("state======="+state)
        console.log("gzjy======="+gzjy)
        console.log("hopeCity======="+hopeCity)
        console.log("hopeJob======="+hopeJob)
        console.log("special======="+special)
        if(special == "" || special == null || special == "请选择个人优势"){
            $(".gerenyoushi").show()
            setTimeout('$(".gerenyoushi").hide()',1000);
        }
        if(hopeJob == "" || hopeJob == null || hopeJob == "请选择求职岗位"){
            $(".qiuzhizhegangwei").show()
            setTimeout('$(".qiuzhizhegangwei").hide()',1000);
        }
        if(gzjy == "" || gzjy == null || gzjy == "请选择工作经验"){
            $(".gongzuojingyan").show()
            setTimeout('$(".gongzuojingyan").hide()',1000);
        }

        if(name == "" || name == null){
            $(".xingmingxinxi").show()
            setTimeout('$(".xingmingxinxi").hide()',1000);
        }

        if(sex == "" || sex == null || sex == "请选择性别"){
            $(".xingbiexinxi").show()
            setTimeout('$(".xingbiexinxi").hide()',1000);
        }

        if(age == "" || age == null){
            $(".nianlingxinxi").show()
            setTimeout('$(".nianlingxinxi").hide()',1000);
        }

        if(hopeCity == "" || hopeCity == null || hopeCity == "请选择意向城市"){
            $(".yixiangchenshi").show()
            setTimeout('$(".yixiangchenshi").hide()',1000);
        }

        if(state == "" || state == null || state == "请选择工作状态"){
            $(".gongzuozhuangtai").show()
            setTimeout('$(".gongzuozhuangtai").hide()',1000);
        }
        if(name != null && name != "" && sex != null && sex != "" && sex != "请选择性别" && age != null && age != "" &&
            gzjy != null && gzjy != ""&& gzjy != "请选择工作经验" && state != null && state != "" && state != "请选择工作状态"
            && hopeCity != null && hopeCity != "" && hopeCity != "请选择意向城市" && hopeJob != null && hopeJob != "" && hopeJob != "请选择求职岗位"
            && special != null && special != "" && special != "请选择个人优势"){
            $.ajax({
                url:"/personal/perfectPersonalInfo",
                type:"POST",
                data:{
                    hopeCity : hopeCity,
                    hopeJob : hopeJob,
                    special : special,
                    name : name,
                    sex : sex,
                    state : state,
                    age : age,
                    gzjy :gzjy,
                    avatar : avatarPath,
                },
                success : function () {
                    window.location.href="/transition/transition_goMySelf";
                },
                error : function () {
                    $(".baocunshibai").show()
                    setTimeout('$(".baocunshibai").hide()',1000);

                }
            })
        }

    });
});
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
        count: 1, // 最多能选择多少张图片，默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            images.localId = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
            //上传图片接口
            wx.uploadImage({
                localId: images.localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    $.ajax({
                        type: 'POST',
                        url: "/weChat/uploadWeiXinImg",
                        data: {
                            mediaId: res.serverId
                        },
                        success: function (res) {
                            avatarPath =res.data.imgUrl;
                            $('#dynamicImage').hide();
                            $('.license').append('<img src="'+avatarPath+'"/>')
                        }
                    });
                }
            });
        }
    });

}
