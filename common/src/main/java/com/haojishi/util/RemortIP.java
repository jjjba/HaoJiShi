package com.haojishi.util;

import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 梁闯
 * @date 2018/03/07 20.19
 *
 */
@Slf4j
public class RemortIP {

    /**
     * 获取登录用户IP地址
     *
     * @param request
     * @return ip - IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
            }
        System.out.println("ip地址是======================="+ip);
        return ip;
        }

    public static String getAddressByIP(HttpServletRequest request) { //
        String ip =getIpAddr(request);
        String address ="";
        if(ip.equals("127.0.0.1") || ip.equals("192.168.0.107")){
            address ="石家庄市";
        }else {
            String url ="http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
            StringBuilder json = new StringBuilder();

            try {
                URL oracle = new URL(url);
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream(), "utf-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                    System.out.println("json=========="+json);
                }
                JSONObject js =new JSONObject(json.toString());
                int code = (int) js.get("code");
                if(code == 0){
                    JSONObject data = (JSONObject) js.get("data");
                    address = (String) data.get("city");
                    System.out.println("城市是==============="+address);
                }else {
                    log.error("获取城市信息失败!!!!!!!!!!!!!!!!!!!!");
                }
                in.close();
            } catch (Exception e) {
                log.error("根据IP地址获取城市异常！！！！！！！！",e);
            }
        }

        return address;
    }

//    public static void main(String[] args) throws Exception{
////        RemortIP r =new RemortIP();
//getAddressByIP();
////        System.out.println("城市是=========="+js.get("data"));
//    }

}
