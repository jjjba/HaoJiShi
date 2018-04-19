function getBack() {
    window.location.href="/transition/go_wei_tuo_zhao_pin";
}

function updat(ns) {
    $("#disylyInput").val(ns);
}
function ljzfwt() {
    var  ns =  $("#disylyInput").val();
    alert(ns);
    if(ns !=null && ns != "" && ns!='' && ns!=undefined){
        alert("开始进ajax")
        $.ajax({
            url:"/wx/toPay",
            type:"GET",
            data:{ACCOUNT:ns,lx:'委托'},
            success:function (msg) {
                $.ajax({
                    url:"/company/getTan",
                    type:"GET",
                    success:function (ms) {
                        onBridgeReady(ms.data.appId,ms.data.timeStamp,ms.data.nonceStr,ms.data.package,ms.data.sign,ms.data.prepay_id,ms.data.money,ms.data.ACCOUNT);
                    }
                })
            }
        })
    }else {
        alert("请选择后支付");
    }
}
function onBridgeReady(appid,timeStamp,nonceStr,packageValue,sign,prepay_id,money,ACCOUNT){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appid,     //公众号名称，由商户传入
            "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr":nonceStr, //随机串
            "package":packageValue,
            "signType":"MD5",         //微信签名方式：
            "paySign":sign //微信签名
        },
        function(res){
            // 使用以下方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            if(res.err_msg == "get_brand_wcpay_request:ok"){
                alert("回来了----------");//这个alert走  下面不走
                $.ajax({
                    url:"/wxpayall/weituozhaopin",
                   data:{prepay_id : prepay_id , money : money, ACCOUNT : ACCOUNT},
                    success:function (msg) {
                        if(msg.data == 1){
                            window.location.href="/transition/go_wo_de";
                        }else {
                            alert("系统异常，请及时联系客服 15558026165");
                        }
                    }
                });
            }else if(res.err_msg == "get_brand_wcpay_request:fail"){
                alert('支付失败');
            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                alert('支付取消');
            }else{
                alert(res.err_msg);
            }
        }
    );
}
