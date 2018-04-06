package com.haojishi.config;

import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("weChat")
public class WxConfig {
    @Autowired
    private Environment environment;
    private static String appid = "wxb363c68215a1d3f1";
    private static String secret ="98e5dbf56fed8c9e0c5fb95f5d34ba05";
    private static String path = "/var/tomcat/tomcat-8/webapps/image";
    private static String ImageSrc = "http://wx.haojishi.net/image/";
    public static String getToken() {
        String accessToken = "";
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = token_url.replace("APPID",appid).replace("APPSECRET",secret);
        JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);
        if (jsonObject != null) {
            try {
                accessToken = jsonObject.getString("access_token");
                log.info("access_token============================"+accessToken);
            } catch (Exception e) {
                log.error("获取accessToken失败~~~~",e);
            }
        }
        return accessToken;
    }

    @RequestMapping("getSignInfo")
    public Map<String, String> sign(String url) {
        Map<String, String> ret = new HashMap<>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + getTicket() +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }catch (Exception e){
            log.error("获取sign方法错误===========",e);
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", getTicket());
        ret.put("nonce_str", nonce_str);
        ret.put("time_stamp", timestamp);
        ret.put("signa_ture", signature);
        ret.put("appid", environment.getProperty("api.appid"));
        ret.put("secret", environment.getProperty("api.secret"));

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public String getTicket(){
        String sign_ticket_create_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        JSONObject jsonObject = new JSONObject();
        JSONObject postjson=new JSONObject();
        String ticket =null;
        String url = sign_ticket_create_url.replace("ACCESS_TOKEN",getToken());
        log.info("url============================"+url);
        try {
            jsonObject = WeChatUtil.httpRequest(url, "POST",postjson.toString());
            ticket= jsonObject.getString("ticket");
            log.info("ticket============================"+ticket);
        }catch (Exception e) {
            log.error("获取ticket失败",e);
            e.printStackTrace();
        }
        return ticket;
    }

    /**
     * @return
     */
    @RequestMapping("uploadWeiXinImg")
    public static BusinessMessage uploadWeiXinImg(String mediaId) {
        BusinessMessage businessMessage =new BusinessMessage();
        try {

            String imgUrl=downloadMedia(mediaId,path);
            Map<String, Object> map = new HashMap();
            map.put("imgUrl", ImageSrc+imgUrl);
            log.info("imgUrl========================"+imgUrl);
            businessMessage.setData(map);
            businessMessage.setSuccess(true);
        } catch (Exception e) {
            log.error("保存图片失败",e);
        }
        return businessMessage;
    }

    /**
     * 获取媒体文件
     * @param mediaId 媒体文件id
     * @param savePath 文件在本地服务器上的存储路径
     * */
    public static String downloadMedia(String mediaId, String savePath) {
        String accessToken = getToken();
        String filePath;
        // 拼接请求地址
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = getFileexpandedName(conn.getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();
            conn.disconnect();
            String info = String.format("下载媒体文件成功，filePath=" + filePath);
            System.out.println(info);
            filePath=mediaId + fileExt;
        } catch (Exception e) {
            filePath = null;
            String error = String.format("下载媒体文件失败：%s", e);
            System.out.println(error);
        }
        return filePath;
    }

    /**
     * 根据内容类型判断文件扩展名
     *
     * @param contentType 内容类型
     * @return
     */
    public static String getFileexpandedName(String contentType) {
        String fileEndWitsh = "";
        if ("image/jpeg".equals(contentType))
            fileEndWitsh = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileEndWitsh = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileEndWitsh = ".amr";
        else if ("video/mp4".equals(contentType))
            fileEndWitsh = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileEndWitsh = ".mp4";
        return fileEndWitsh;
    }
}
