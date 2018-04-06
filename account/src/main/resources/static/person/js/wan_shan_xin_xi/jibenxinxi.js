var avatarPath;
$(function () {
    loadUserInfo();
    setTimeout('configwx()', 100);


    $("#dynamicImage").click(function(e){
        chooseImage();
    });

    $('.bcanius').click(function(){
        var address =$("#expressArea dl dd").html();
        if(address == "省-市-区/县"){
            address =null;
        }
        $.ajax({
            url:"/personal/updatePersonalByPersonalId",
            type:"POST",
            data:{
                name :$('.name').val(),
                sex :$('.sex').val(),
                state :$('.state').val(),
                phone :$('.phone').val(),
                age :$('.age').val(),
                address :address,
                avatar : avatarPath,
            },
            success : function () {
                $(".toolbarframe02").show()
                setTimeout('$(".toolbarframe02").hide()',1000);
            },
            error : function () {
                $(".toolbarframe03").show()
                setTimeout('$(".toolbarframe03").hide()',1000);

            }
        })

    });
});
function loadUserInfo() {
    $.ajax({
        url:"/personal/getPersonalInfo",
        type:"POST",
        success : function (res) {
            var per =res.data;
            var avatar =per.avatar;
            var name =per.name;
            var sex =per.sex;
            var age = per.age;
            var address =per.address;
            var phone =per.phone;
            var state =per.state;
            if(avatar == null || avatar == ""){
                if(sex == "男"){
                    avatar ="../../person/images/tupian.png"
                }else {
                    avatar ="../../person/images/tupian02.png"
                }
            }
            if(address == null || address == ""){
                address ="<dd>省-市-区/县</dd>"
            }
            $('.license').append('<img src="../../person/images/tupian.png" id="dynamicImage"/>')
            $('.namesl').append('<input type="text" placeholder="'+name+'" class="stkius name" /><img src="../../person/images/yjts.png" class="yjtiss" />')
            $('.sexsl').append('<div class="d-guanzhu sex">'+sex+'</div> <img src="../../person/images/yjts.png" class="yjtiss" />')
            $('.agesl').append('<input type="text" placeholder="'+age+'" class="stkius age" /> <img src="../../person/images/yjts.png" class="yjtiss" />')
            $('.addresssl').append('<dd>'+address+'</dd>')
            $('.phonesl').append('<input type="text" placeholder="'+phone+'" class="stkius phone" /> <img src="../../person/images/yjts.png" class="yjtiss" />')
            $('.statesl').append('<div id="trigger1" class="zhiiis">'+state+'</div>' +
                '                 <script type="text/javascript">' +
                '                     var weekdayArr=["离职-随时到岗","在职-考虑状态","离职-需要准备"];' +
                '                     var mobileSelect1 = new MobileSelect({' +
                '                         trigger: "#trigger1",' +
                '                         title: "",' +
                '                         wheels: [' +
                '                             {data: weekdayArr}' +
                '                         ],' +
                '                         position:[2],' +
                '                         transitionEnd:function(indexArr, data){' +
                '                             console.log(data);' +
                '                         },' +
                '                         callback:function(indexArr, data){' +
                '                             console.log(data);' +
                '                         }' +
                '                     });' +
                '                 </script>');
        }
    })
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
                        }
                    });
                }
            });
        }
});
    
}
