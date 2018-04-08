var avatarPath;
$(function () {
    setTimeout('configwx()', 100);


    $('.bcanius').click(function(){
        var name =$('.name').val();
        if(name == "" && name == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }
        var sex =$('.sex').val();
        if(sex == "" && sex == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }
        var age =$('.age').val();
        if(address == "" && address == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }
        var address =$("#expressArea dl dd").html();
        if(address == "" && address == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }
        var phone =$('.phone').val();
        if(address == "" && address == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }
        var state =$('.state').val();
        if(address == "" && address == null){
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }



        // $.ajax({
        //     url:"/personal/updatePersonalByPersonalId",
        //     type:"POST",
        //     data:{
        //         name :
        //         sex :
        //         state :
        //         phone :
        //         age :
        //         address :address,
        //         avatar : avatarPath,
        //     },
        //     success : function () {
        //         $(".toolbarframe02").show()
        //         setTimeout('$(".toolbarframe02").hide()',1000);
        //     },
        //     error : function () {
        //         $(".toolbarframe03").show()
        //         setTimeout('$(".toolbarframe03").hide()',1000);
        //
        //     }
        // })

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
