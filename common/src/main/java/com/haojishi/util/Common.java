package com.haojishi.util;

public class Common {

    public static final String CHARSET = "UTF-8";

    //测试URL
//    public static final String URL = "http://116.228.235.114:38080/External_Connect/newWechats/newWeChatpayment_mobile.action";
    //线上URL
    public static final String URL = "http://extman.kefupay.cn/newWechats/newWeChatpayment_mobile.action";

    // 商户标识(根据分配的用户标识进行修改)
//    改为从配置文件读取
//    public static final String userid = "995418";
//    public static final String key = "edb73bc10b8329e1b1c8b780c3f557b5";


    /**
     * 交易类型---------BEGIN
     */
    public static final String newRegister = "hf_newRegister";//注册商户接口
    public static final String newDownLoadKey = "hf_newDownLoadKey";//下载密钥接口
    public static final String newVerifyInfo = "hf_newVerifyInfo";//验卡接口
    public static final String newWeixinPay = "hf_newWeixinPay";//二维码支付接口
    public static final String newOrderConfirm = "hf_newOrderConfirm";//订单状态查询接口
    public static final String newChangeRate = "hf_newChangeRate";//同步商户签约费率
    public static final String newGetACodePay = "hf_newGetACodePay";//公众号支付接口
    public static final String newALiPay = "hf_newALiPay";//二维码支付宝支付接口


    /**
     * 易试扫码支付
     */
    public static final String ys_ALiPay = "ys_ALiPay";//易试支付宝扫码支付
    public static final String ys_WXPay = "ys_WXPay";//易试微信支付
    public static final String ys_WeChatPay = "ys_WeChatPay";//易试微信公众号支付
    public static final String ys_Withdraw = "ys_Withdraw";//易试代付
    public static final String ys_WithdrawCheck = "ys_WithdrawCheck";//易试代付查询
}
