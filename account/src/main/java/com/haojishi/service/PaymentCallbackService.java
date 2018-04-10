package com.haojishi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.ServicesMapper;
import com.haojishi.util.ClientIPUtil;
import com.haojishi.util.WxPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;

/**
 * @author 梁闯
 * @date 2018/04/06 21.19
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentCallbackService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ServicesMapper servicesMapper;

    /**
     * 微信支付回调
     *
     * @param request  请求信息
     * @param response
     * @return 支付应答
     */
    public void weChatCallback(HttpServletRequest request, HttpServletResponse response) {
        log.debug("接收微信回调");
        //获取返回通知结果
        try (InputStreamReader isr = new InputStreamReader(request.getInputStream(), "UTF-8"); BufferedReader br = new BufferedReader(isr); OutputStream os = response.getOutputStream()) {
            String buffer;
            StringBuilder sb = new StringBuilder();
            while ((buffer = br.readLine()) != null) {
                sb.append(buffer);
            }

            log.debug("微信回调参数：{}", sb.toString());

            //解析返回的xml
            String temp = sb.toString().replaceAll("<!\\[CDATA\\[", "").replaceAll("]]>", "");

            log.debug("处理后的参数：{}", temp);

            // 初始化回调数据
            String returnData = "";

            XmlMapper xmlMapper = new XmlMapper();
            Map<String, String> data = xmlMapper.readValue(temp, new TypeReference<Map<String, String>>() {
            });
            if (data.containsKey("sign")) {
                //封装参数
                SortedMap<String, Object> params = new TreeMap<>();
                data.keySet().forEach(k -> {
                    if ("sign".equals(k)) {
                        return;
                    }
                    params.put(k, data.get(k));
                });

                String orderId = params.get("out_trade_no").toString();

                //业务结果
                if (data.containsKey("result_code") && "SUCCESS".equals(data.get("result_code"))) {
                    String clientIp = ClientIPUtil.getClientIP(request);
                    data.put("clientIp", clientIp);

                    //通知微信已收到回调通知
                    returnData = WxPayUtil.generateReturnData("SUCCESS", "OK");
                }
            } else {
                //通知微信已收到回调通知
                returnData = WxPayUtil.generateReturnData("FAIL", "SIGNERROR");
            }
            log.info("回复微信消息：{}", returnData);
            response.setContentType(MediaType.APPLICATION_XML_VALUE);
            os.write(returnData.getBytes("UTF-8"));
        } catch (IOException e) {
            log.error("微信回调失败，{}", e.getMessage());
        }
    }
}
