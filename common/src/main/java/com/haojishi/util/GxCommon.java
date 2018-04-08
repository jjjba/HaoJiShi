package com.haojishi.util;

public class GxCommon {

    String PAY_LOG_KEY_PREFIX = "pay-log:";
    String PAY_LOG_PROCESS_KEY_PREFIX = "pay-log-process:";
    // 订单支付日志队列标识
//    String PAY_LOG_QUEUE = "pay-log-queue";
    String PROPERTY_PAY_LOG_QUEUE = "spring.rabbitmq.pay-log-queue";
    String MACHID = "machid";
    public static String APIKEY = "apikey";


    String KEY_CODE_BG = "code_bg";
    String KEY_SMS_SIGN = "sms_sign";
    //    String ORDER_AFTER ="__wsc" ;
    String KEY_KEY = "sdfjkfki766837323";
    String THIRD_REQUEST = "third_request";
    String REQUEST = "request";
    String CREATE_ORDER_LIMIT = "下单过于频繁";
    public static String APPLET_LOGO = "applet_logo";
    public static String APPLET_QR_BG = "applet_qr_bg";
    String EXPORT_LITMIT = "请选择起止时间导出,并且间隔不要超过一天,或者选择一用户进行导出";
    String EXPORT_NO_CONTENT = "没有数据可导出";
    String EXPORT_LITMIT_CUSTOMER = "请选择起止时间导出,并且间隔不要超过一天";
}
