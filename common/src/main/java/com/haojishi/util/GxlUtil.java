package com.haojishi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
@Slf4j
public class GxlUtil {

    public static Integer now() {
        return (int) LocalDateTime.now().atOffset(ZoneOffset.ofHours(8)).toEpochSecond();
    }

    public static String createUniontid() {
        //Random random = new Random();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 1; i++) {
//            sb.append(random.nextInt(9));
//        }
        // 订单号
        return LocalDateTime.now().atOffset(ZoneOffset.ofHours(8)).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + new Random().nextInt(9);
    }


    public static boolean signCheck(Map paramMap, String key, String sign) {
        String signStr = MD5Utils.getSignParam(paramMap);
        log.debug("计算签名的报文为：{}", sign);
        signStr = MD5Utils.getKeyedDigest(signStr, key);
        return sign != null && sign.equals(signStr);

    }

    public static String getLogoUrl(String payconfig) {
        JSONObject appconfig = JSON.parseObject(payconfig);
        if (appconfig.containsKey("other")) {
            JSONObject other = appconfig.getJSONObject("other");
            return other.getString(GxCommon.APPLET_LOGO);
        }
        return null;
    }

    public static String getQrbgUrl(String payconfig) {
        JSONObject appconfig = JSON.parseObject(payconfig);
        if (appconfig.containsKey("other")) {
            JSONObject other = appconfig.getJSONObject("other");
            return other.getString(GxCommon.APPLET_QR_BG);
        }
        return null;

    }

    public static void wreteResponse(HttpServletResponse response, String msg) {
        response.setContentType("text/html; charset=utf-8");
        try (ServletOutputStream out = response.getOutputStream();) {
            out.write(msg.getBytes("utf-8"));
            out.flush();
        } catch (Exception e) {
        }
    }
}
