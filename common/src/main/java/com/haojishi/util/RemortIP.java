package com.haojishi.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 梁闯
 * @date 2018/03/07 20.19
 *
 */
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
        System.out.println(ip);
        return ip;
        }

    /**
     * 根据登录用户IP地址获取用户地址
     *
     * @return 用户地址
     */
    public String getAddressByIP(HttpServletRequest request) {
        try{
            String city =null;
            String Ip=RemortIP.getIpAddr(request);
            if(Ip.equals( "127.0.0.1")){
                URL url = new URL( "http://ip.qq.com/cgi-bin/searchip?searchip1=" + Ip);
                URLConnection conn = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
                String line = null;
                StringBuffer result = new StringBuffer();
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                Ip = result.substring(result.indexOf( "该IP所在地为：" ));
                Ip = Ip.substring(Ip.indexOf( "：") + 1);
//            String province = strIP.substring(6, strIP.indexOf("省"));
                city = Ip.substring(Ip.indexOf("省") + 1, Ip.indexOf("市"));
            }else {
                city="本地";
            }
            return city;

        }catch( IOException e) {
            return "获取地址失败";
        }
    }



}
