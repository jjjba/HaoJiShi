var license;
$(function () {
    setTimeout('configwx()', 100);
    $('.shangchaunzhaopian').click(function () {
        chooseImage();
    });
    $('.tijiaotupian').click(function () {
        $.ajax({
            url:"/company/shenhezhaopian",
            type:"POST",
            data:{
                license : license
            },
            success :function (res) {










            },
        })
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
    wx.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success: function (res) {
            images.localId = res.localIds;
            wx.uploadImage({
                localId: images.localId[0],
                isShowProgressTips: 1,
                success: function (res) {
                    $.ajax({
                        type: 'POST',
                        url: "/weChat/uploadWeiXinImg",
                        data: {
                            mediaId: res.serverId
                        },
                        success: function (res) {
                            license =res.data.imgUrl;
                            $('#dynamicImage').hide();
                            $('.shangchaunzhaopian').hide();
                            $('.tijiaotupian').show();
                            $('.license').append('<img src="'+license+'"/>')
                        }
                    });
                }
            });
        }
    });

}