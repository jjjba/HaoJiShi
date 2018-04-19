package com.haojishi.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haojishi.Wx.wechat.pay.util.OrderUtils;
import com.haojishi.Wx.wechat.pay.util.TxtUtil;
import com.haojishi.Wx.weixin.config.WxPayConfig;
import com.haojishi.Wx.weixin.util.CommonUtil;
import com.haojishi.Wx.weixin.util.RequestHandler;
import com.haojishi.Wx.weixin.util.Sha1Util;
import com.haojishi.Wx.weixin.util.WeixinPayUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 微信支付Controller
 * @author Jeff Xu
 * @since 2016-08-11
 */

@Controller
@RequestMapping("/wx")
public class WeixinPayController {

	private static String baseUrl = "http://su.vipgz1.idcfengye.com";
	
	/**
	 * 微信网页授权获取用户基本信息，先获取 code，跳转 url 通过 code 获取 openId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userAuth")
	public void userAuth(HttpServletRequest request, HttpServletResponse response,HttpSession session,String lx){
		session.setAttribute("lxlx",lx);
		try {
			/*String totalFee = request.getParameter("totalFee");*/
			/*System.out.println("in userAuth,orderId:" + orderId);*/
			/*session.setAttribute("orderId",orderId);*/
			
			//授权后要跳转的链接
			String backUri =  baseUrl+ "/wx/getOpenId";/*/transition/kuaizhao*/  /*/wx/toPay*/
			backUri = backUri; /*+ "?orderId=" + orderId+"&totalFee="+totalFee;*/
			//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
			backUri = URLEncoder.encode(backUri);
			//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
					"appid=" + WxPayConfig.appid +
					"&redirect_uri=" +
					 backUri+
					"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			System.out.println("url:" + url);
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("getOpenId")
	public String getOpenId(HttpServletRequest request,HttpSession session){
		//网页授权后获取传递的参数
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		session.setAttribute("code",code);
		//获取统一下单需要的openid
		String openId ="";
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WxPayConfig.appid + "&secret=" + WxPayConfig.appsecret + "&code=" + code + "&grant_type=authorization_code";
		System.out.println("URL:"+URL);
		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject) {
			openId = jsonObject.getString("openid");
			System.out.println("openId:" + openId);
			session.setAttribute("open",openId);
		}
		String lx = (String)session.getAttribute("lxlx");
		String url = "";
		if("快招".equals(lx)){
			url= "company/company_myself/kuai_zhao";
		}
		if("委托".equals(lx)){
			url =  "company/company_index/kai_tong_wei_tuo";
		}
		return  url;
	}


	@RequestMapping("toPay")
	public void toPay(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model,String ACCOUNT,String num,String lx){
		if("快招".equals(lx)){
			String money = "";
			String type = "";
			if("150次急速拨打电话卡".equals(ACCOUNT)){
				money = "0.01";
				type="按次数计费";
			}
			if("200次急速拨打电话卡".equals(ACCOUNT)){
				money = "0.02";
				type="按次数计费";
			}
			if("300次急速拨打电话卡".equals(ACCOUNT)){
				money = "0.03";
				type="按次数计费";
			}
			if("半年卡".equals(ACCOUNT)){
				money = "0.04";
				type="按时间计费";
			}
			if("全年卡".equals(ACCOUNT)){
				money = "0.04";
				type="按时间计费";
			}
			try {
				String orderId = OrderUtils.genOrderNo();
				System.out.println("in toPay,orderId:" + orderId);
				//充值金额
				String totalFeeStr = money;
				Float totalFee = 0.0f;

				if(StringUtils.isNotBlank(totalFeeStr)){
					totalFee = new Float(totalFeeStr);
				}

				//网页授权后获取传递的参数
				String code = (String)session.getAttribute("code");
				System.out.println("code:"+code);

				//获取统一下单需要的openid
				String openId =(String) session.getAttribute("open");;

				//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
				//随机数
				//String nonce_str = "1add1a30ac87aa2db72f57a2375d8fec";
				String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
				//商品描述
				String body = orderId;
				//商户订单号
				String out_trade_no = orderId;
				//订单生成的机器 IP
				String spbill_create_ip = request.getRemoteAddr();
				//总金额（转换为分）
				//TODO
				Integer total_fee = Math.round(totalFee*100);

				//商户号
				//String mch_id = partner;
				//子商户号  非必输
				//String sub_mch_id="";
				//设备号   非必输
				//String device_info="";
				//附加数据
				//String attach = userId;
				//总金额以分为单位，不带小数点
				//int total_fee = intMoney;
				//订 单 生 成 时 间   非必输
				//String time_start ="";
				//订单失效时间      非必输
				//String time_expire = "";
				//商品标记   非必输
				//String goods_tag = "";
				//非必输
				//String product_id = "";

				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				String notify_url = baseUrl + "/wx/notifyUrl";

				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", WxPayConfig.appid);
				packageParams.put("mch_id", WxPayConfig.partner);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("body", body);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", total_fee+"");
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);
				packageParams.put("trade_type", WxPayConfig.trade_type);
				packageParams.put("openid", openId);

				RequestHandler reqHandler = new RequestHandler(request, response);
				reqHandler.init(WxPayConfig.appid, WxPayConfig.appsecret, WxPayConfig.partnerkey);




				String sign = reqHandler.createSign(packageParams);
				System.out.println("sign:"+sign);
				String xml="<xml>"+
						"<appid>"+WxPayConfig.appid+"</appid>"+
						"<mch_id>"+WxPayConfig.partner+"</mch_id>"+
						"<nonce_str>"+nonce_str+"</nonce_str>"+
						"<sign>"+sign+"</sign>"+
						"<body><![CDATA["+body+"]]></body>"+
						"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
						"<total_fee>"+total_fee+""+"</total_fee>"+
						"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
						"<notify_url>"+notify_url+"</notify_url>"+
						"<trade_type>"+WxPayConfig.trade_type+"</trade_type>"+
						"<openid>"+openId+"</openid>"+
						"</xml>";
				System.out.println("xml："+xml);

				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String prepay_id="";
				try {
					prepay_id = WeixinPayUtil.getPayNo(createOrderURL, xml);
					System.out.println("prepay_id:" + prepay_id);
					if(prepay_id.equals("")){
						request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SortedMap<String, String> finalpackage = new TreeMap<String, String>();
				String timestamp = Sha1Util.getTimeStamp();
				String packages = "prepay_id="+prepay_id;
				finalpackage.put("appId", WxPayConfig.appid);
				finalpackage.put("timeStamp", timestamp);
				finalpackage.put("nonceStr", nonce_str);
				finalpackage.put("package", packages);
				finalpackage.put("signType", WxPayConfig.signType);

				SortedMap<String, String> packageParams1 = new TreeMap<String, String>();
				packageParams1.put("appId", WxPayConfig.appid);
				packageParams1.put("timeStamp", timestamp);
				packageParams1.put("nonceStr", nonce_str);
				packageParams1.put("package", packages);
				packageParams1.put("signType", WxPayConfig.signType);
				String sign1 = reqHandler.createSign(packageParams1);
				packageParams1.put("sign",sign1);

				packageParams1.put("prepay_id",prepay_id);
				packageParams1.put("money",money);
				packageParams1.put("ACCOUNT",ACCOUNT);
				packageParams1.put("type",type);
				packageParams.put("num",num);
				session.setAttribute("finalpack",packageParams1);



				String finalsign = reqHandler.createSign(finalpackage);
				System.out.println("/jsapi?appid="+WxPayConfig.appid+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+packages+"&sign="+finalsign);

				model.addAttribute("appid", WxPayConfig.appid);
				model.addAttribute("timeStamp", timestamp);
				model.addAttribute("nonceStr", nonce_str);
				model.addAttribute("packageValue", packages);
				model.addAttribute("sign", finalsign);

				model.addAttribute("bizOrderId", orderId);
				model.addAttribute("orderId", orderId);
				model.addAttribute("payPrice", total_fee);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		if("委托".equals(lx)){
			String money = "";
			if("半年".equals(ACCOUNT)){
				money = "0.01";
			}
			if("全年".equals(ACCOUNT)){
				money = "0.02";
			}
			try {
				String orderId = OrderUtils.genOrderNo();
				System.out.println("in toPay,orderId:" + orderId);
				//充值金额
				String totalFeeStr = money;
				Float totalFee = 0.0f;

				if(StringUtils.isNotBlank(totalFeeStr)){
					totalFee = new Float(totalFeeStr);
				}

				//网页授权后获取传递的参数
				String code = (String)session.getAttribute("code");
				System.out.println("code:"+code);

				//获取统一下单需要的openid
				String openId =(String) session.getAttribute("open");;

				//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
				//随机数
				//String nonce_str = "1add1a30ac87aa2db72f57a2375d8fec";
				String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
				//商品描述
				String body = orderId;
				//商户订单号
				String out_trade_no = orderId;
				//订单生成的机器 IP
				String spbill_create_ip = request.getRemoteAddr();
				//总金额（转换为分）
				//TODO
				Integer total_fee = Math.round(totalFee*100);

				//商户号
				//String mch_id = partner;
				//子商户号  非必输
				//String sub_mch_id="";
				//设备号   非必输
				//String device_info="";
				//附加数据
				//String attach = userId;
				//总金额以分为单位，不带小数点
				//int total_fee = intMoney;
				//订 单 生 成 时 间   非必输
				//String time_start ="";
				//订单失效时间      非必输
				//String time_expire = "";
				//商品标记   非必输
				//String goods_tag = "";
				//非必输
				//String product_id = "";

				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				String notify_url = baseUrl + "/wx/notifyUrl";

				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", WxPayConfig.appid);
				packageParams.put("mch_id", WxPayConfig.partner);
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("body", body);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", total_fee+"");
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);
				packageParams.put("trade_type", WxPayConfig.trade_type);
				packageParams.put("openid", openId);

				RequestHandler reqHandler = new RequestHandler(request, response);
				reqHandler.init(WxPayConfig.appid, WxPayConfig.appsecret, WxPayConfig.partnerkey);




				String sign = reqHandler.createSign(packageParams);
				System.out.println("sign:"+sign);
				String xml="<xml>"+
						"<appid>"+WxPayConfig.appid+"</appid>"+
						"<mch_id>"+WxPayConfig.partner+"</mch_id>"+
						"<nonce_str>"+nonce_str+"</nonce_str>"+
						"<sign>"+sign+"</sign>"+
						"<body><![CDATA["+body+"]]></body>"+
						"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
						"<total_fee>"+total_fee+""+"</total_fee>"+
						"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
						"<notify_url>"+notify_url+"</notify_url>"+
						"<trade_type>"+WxPayConfig.trade_type+"</trade_type>"+
						"<openid>"+openId+"</openid>"+
						"</xml>";
				System.out.println("xml："+xml);

				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String prepay_id="";
				try {
					prepay_id = WeixinPayUtil.getPayNo(createOrderURL, xml);
					System.out.println("prepay_id:" + prepay_id);
					if(prepay_id.equals("")){
						request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SortedMap<String, String> finalpackage = new TreeMap<String, String>();
				String timestamp = Sha1Util.getTimeStamp();
				String packages = "prepay_id="+prepay_id;
				finalpackage.put("appId", WxPayConfig.appid);
				finalpackage.put("timeStamp", timestamp);
				finalpackage.put("nonceStr", nonce_str);
				finalpackage.put("package", packages);
				finalpackage.put("signType", WxPayConfig.signType);

				SortedMap<String, String> packageParams1 = new TreeMap<String, String>();
				packageParams1.put("appId", WxPayConfig.appid);
				packageParams1.put("timeStamp", timestamp);
				packageParams1.put("nonceStr", nonce_str);
				packageParams1.put("package", packages);
				packageParams1.put("signType", WxPayConfig.signType);
				String sign1 = reqHandler.createSign(packageParams1);
				packageParams1.put("sign",sign1);

				packageParams1.put("prepay_id",prepay_id);
				packageParams1.put("money",money);
				packageParams1.put("ACCOUNT",ACCOUNT);
				session.setAttribute("finalpack",packageParams1);



				String finalsign = reqHandler.createSign(finalpackage);
				System.out.println("/jsapi?appid="+WxPayConfig.appid+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+packages+"&sign="+finalsign);

				model.addAttribute("appid", WxPayConfig.appid);
				model.addAttribute("timeStamp", timestamp);
				model.addAttribute("nonceStr", nonce_str);
				model.addAttribute("packageValue", packages);
				model.addAttribute("sign", finalsign);

				model.addAttribute("bizOrderId", orderId);
				model.addAttribute("orderId", orderId);
				model.addAttribute("payPrice", total_fee);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 微信异步回调，不会跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("notifyUrl")
	public void weixinReceive(HttpServletRequest request,HttpServletResponse response, Model model){
		System.out.println("==开始进入h5支付回调方法==");
		String xml_review_result = WeixinPayUtil.getXmlRequest(request);
		System.out.println("微信支付结果:"+xml_review_result);
		Map resultMap = null;
		try {
			resultMap = WeixinPayUtil.doXMLParse(xml_review_result);
			System.out.println("resultMap:"+resultMap);
			if(resultMap != null && resultMap.size() > 0){
				String sign_receive = (String)resultMap.get("sign");
				System.out.println("sign_receive:"+sign_receive);
				resultMap.remove("sign");
				String checkSign = WeixinPayUtil.getSign(resultMap,"014246");
				System.out.println("checkSign:"+checkSign);

				//签名校验成功
				if(checkSign != null && sign_receive != null &&
						checkSign.equals(sign_receive.trim())){
					System.out.println("weixin receive check Sign sucess");
                    try{
                    	//获得返回结果
                    	String return_code = (String)resultMap.get("return_code");
                    
                    	if("SUCCESS".equals(return_code)){
                    		String out_trade_no = (String)resultMap.get("out_trade_no");
                    		System.out.println("weixin pay sucess,out_trade_no:"+out_trade_no);
                    		//处理支付成功以后的逻辑，这里是写入相关信息到文本文件里面，如果有订单的处理订单
                    		try{
                    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mi:ss");
                    			String content = out_trade_no+"        "+sdf.format(new Date());
                    			String fileUrl = System.getProperty("user.dir") + File.separator+"WebContent" + File.separator + "data" + File.separator + "order.txt";
                    			TxtUtil.writeToTxt(content, fileUrl);
                    		}catch(Exception e){
                    			e.printStackTrace();
                    		}
                    	}else{
                    	    model.addAttribute("payResult", "0");
                    	    model.addAttribute("err_code_des", "通信错误");
                    	}
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
				}else{
					//签名校验失败
					System.out.println("weixin receive check Sign fail");
                    String checkXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"+
                    					"<return_msg><![CDATA[check sign fail]]></return_msg></xml>";
                    WeixinPayUtil.getTradeOrder("http://weixin.xinfor.com/wx/notifyUrl", checkXml);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 页面js返回支付成功后，查询微信后台是否支付成功，然后跳转结果页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("success")
	public String toWXPaySuccess(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException{
		String id = request.getParameter("orderId");
		System.out.println("toWXPaySuccess, orderId: " + id);
		try {
			Map resultMap = WeixinPayUtil.checkWxOrderPay(id);
			System.out.println("resultMap:" + resultMap);
			String return_code = (String)resultMap.get("return_code");
        	String result_code = (String)resultMap.get("result_code");
        	System.out.println("return_code:" + return_code + ",result_code:" + result_code);
        	if("SUCCESS".equals(return_code)){
        		if("SUCCESS".equals(result_code)){
            	    model.addAttribute("orderId", id);
        			model.addAttribute("payResult", "1");
        		}else{
        			String err_code = (String)resultMap.get("err_code");
            	    String err_code_des = (String)resultMap.get("err_code_des");
            	    System.out.println("weixin resultCode:"+result_code+",err_code:"+err_code+",err_code_des:"+err_code_des);

            	    model.addAttribute("err_code", err_code);
            	    model.addAttribute("err_code_des", err_code_des);
        			model.addAttribute("payResult", "0");
        		}
        	}else{
        	    model.addAttribute("payResult", "0");
        	    model.addAttribute("err_code_des", "通信错误");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/payResult";
	}



}
