
/*$(document).ready(function() { $.ajax({url:"/wx/getOpenId"})})*/
function hqDd(xx,gg) {
    $("#input_hide").val(xx);
    $("#input_hid").val(gg);
}
function Ljzhifu() {
    var gg = $("#input_hid").val();
    $.ajax({
        url:"/wxpayall/getFfjl",
        type:"POST",
        success:function (iop) {
            console.log(iop);
            console.log(gg);
            if(iop.dataOne == "未过期" && iop.data[0].type != gg){
                if(iop.data[0].type =="按时间计费"){
                    $("#maiguocishu").show();
                }
                if(iop.data[0].type =="按次数计费"){
                    $("#maiguoshijian").show();
                }
            }else {
                if(iop.dataOne == "未过期"){
                    xufeiOrChongzhi("9999","快招");
                }else {
                    xufeiOrChongzhi("1111","快招");
                }
            }
        }
    })
}
function xufeiOrChongzhi(num,lx) {
    var ACCOUNT = $("#input_hide").val();
    if(ACCOUNT != null && ACCOUNT!="" && ACCOUNT!=''){
        $.ajax({
            url:"/wx/toPay",
            type:"GET",
            data:{ACCOUNT:ACCOUNT,num:num,lx:lx},
            success:function (msg) {
                $.ajax({
                    url:"/company/getTan",
                    type:"GET",
                    success:function (ms) {
                        onBridgeReady(ms.data.appId,ms.data.timeStamp,ms.data.nonceStr,ms.data.package,ms.data.sign,ms.data.prepay_id,ms.data.money,ms.data.ACCOUNT,ms.data.type,num);
                    }
                })
            }
        })
    }
}


function onBridgeReady(appid,timeStamp,nonceStr,packageValue,sign,prepay_id,money,name,type,num){
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
            if(res.err_msg == "get_brand_wcpay_request:ok") {
                $.ajax({
                    url:"/wxpayall/addService",
                    type:"POST",
                    data:{order_id:prepay_id,money:money,name:name,type:type,num:num},
                    success:function (fun) {
                        if(fun.data == 1){
                            sessionStorage.setItem("jiemian","fukuan");
                            window.location.href="/transition/kuaizhao_fufeijilu";
                        }else {
                            alert("系统异常，请及时联系客服 15558026165");
                        }
                    }
                })
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

if (typeof('WeixinJSBridge') == "undefined"){
    if( document.addEventListener ){
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    }else if (document.attachEvent){
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    }
}else{
    onBridgeReady();
}