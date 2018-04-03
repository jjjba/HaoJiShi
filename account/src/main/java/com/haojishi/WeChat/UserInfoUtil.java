package com.haojishi.WeChat;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserInfoUtil {

    public static String getOpenId(String code){
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?  appid=wx3e4as9adbc62b1e9&secret=1cafcbae8abed3fb3ba31394509c12c9&code="+code+"&grant_type=authorization_code";
        String openId="";
        try {
            URL getUrl=new URL(url);
            HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);


            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);


            String message = new String(b, "UTF-8");

            JSONObject json = JSONObject.fromObject(message);
            openId = json.getString("openid");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return openId;
    }
}
