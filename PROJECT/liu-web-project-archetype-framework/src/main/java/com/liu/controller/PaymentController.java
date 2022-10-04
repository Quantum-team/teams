package com.liu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import static com.liu.service.alipay.AlipayConfig.*;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 */

//声明 RESTful 接口，简化绑定 URL GET级别参数
@RestController
//类级别控制转发
@RequestMapping("/payment")
public class PaymentController extends GeneralController {
	
	/**
	 * 业务平台发起 结算 -> 支付 页面
	 */
	@GetMapping("/checkout/{id}")
	public ModelAndView checkout(
			@PathVariable String id, 
			HttpServletResponse response,
			ModelAndView mv
			) throws AlipayApiException, IOException {
		
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no 	= "order_" + id + "_" + System.currentTimeMillis();
		// 订单名称，必填
		String subject 			= "测试订单";
		// 付款金额，必填
		String total_amount 	= "120";
		// 商品描述，可空
		String body 			= "测试商品描述";
		// 超时时间 可空
		String timeout_express 	= "2m";
		// 销售产品码 必填
		String product_code 	= "QUICK_WAP_WAY";
		
		mv.addObject("out_trade_no", out_trade_no);
		mv.addObject("subject", subject);
		mv.addObject("total_amount", total_amount);
		mv.addObject("body", body);
		mv.addObject("timeout_express", timeout_express);
		mv.addObject("product_code", product_code);

		
		// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
		// 调用RSA签名方式
		AlipayClient client = new DefaultAlipayClient(
				URL, 				//
				APPID,				//
				RSA_PRIVATE_KEY, 	//
				FORMAT, 			//
				CHARSET, 			//
				ALIPAY_PUBLIC_KEY,	//
				SIGNTYPE			//
				);
		
		/*
		 * 注意封装的 PayRequest / PayModel 对象不同，适用不同支付场景
		 * 
		 * PC端，页面扫码支付
		 * AlipayTradePagePayRequest
		 * AlipayTradePagePayModel
		 * 
		 * APP端，调用支付宝APP支付
		 * AlipayTradeWapPayRequest
		 * AlipayTradeWapPayModel
		 * 
		 */
		
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(out_trade_no);
		model.setSubject(subject);
		model.setTotalAmount(total_amount);
		model.setBody(body);
		model.setTimeoutExpress(timeout_express);
		model.setProductCode(product_code);
		alipayRequest.setBizModel(model);
		// 设置异步通知地址
		alipayRequest.setNotifyUrl(notify_url);
		// 设置同步地址
		alipayRequest.setReturnUrl(return_url);
		
		String form_html = client.pageExecute(alipayRequest).getBody();
		System.out.println(form_html);
		
		response.setContentType("text/html;charset=" + CHARSET); 
	    response.getWriter().write(form_html);//直接将完整的表单html输出到页面 
	    response.getWriter().flush(); 
	    response.getWriter().close();
		
		return forwardOrLogout("checkout", mv);
	}
	
	/**
	 * 业务平台发起 结算 -> 支付 页面
	 */
	@GetMapping("/checkout_pc/{id}")
	public ModelAndView checkoutPc(
			@PathVariable String id, 
			HttpServletResponse response,
			ModelAndView mv
			) throws AlipayApiException, IOException {
		
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no 	= "order_" + id + "_" + System.currentTimeMillis();
		// 订单名称，必填
		String subject 			= "测试信息subject";
		// 付款金额，必填
		String total_amount 	= "120";
		// 商品描述，可空
		String body 			= "测试信息body";
		// 超时时间 可空
		String timeout_express 	= "2m";
		// 销售产品码 必填
		String product_code 	= "QUICK_WAP_WAY";
		
		mv.addObject("out_trade_no", out_trade_no);
		mv.addObject("subject", subject);
		mv.addObject("total_amount", total_amount);
		mv.addObject("body", body);
		mv.addObject("timeout_express", timeout_express);
		mv.addObject("product_code", product_code);
		
		//获得初始化的AlipayClient
		AlipayClient client = new DefaultAlipayClient(
				URL, 					//
				APPID, 					//
				RSA_PRIVATE_KEY, 		//
				"json", 				//
				CHARSET, 				//
				ALIPAY_PUBLIC_KEY, 		//
				SIGNTYPE				//
				);
		
		/*
		 * 注意封装的 PayRequest / PayModel 对象不同，适用不同支付场景
		 * 
		 * PC端，页面扫码支付
		 * -
		 * AlipayTradePagePayRequest
		 * AlipayTradePagePayModel
		 * 
		 * APP端，调用支付宝APP支付
		 * -
		 * AlipayTradeWapPayRequest
		 * AlipayTradeWapPayModel
		 * 
		 */
		
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();

		// 封装请求支付信息
		AlipayTradePagePayModel model = new AlipayTradePagePayModel();
		model.setOutTradeNo(out_trade_no);
		model.setSubject(subject);
		model.setTotalAmount(total_amount);
		model.setBody(body);
		model.setTimeoutExpress(timeout_express);
		model.setProductCode(product_code);
		alipayRequest.setBizModel(model);
		// 设置异步通知地址
		alipayRequest.setNotifyUrl(notify_url);
		// 设置同步地址
		alipayRequest.setReturnUrl(return_url);

		alipayRequest.setBizContent(
				"{"
					+ "\"out_trade_no\":\"" + out_trade_no + "\"," 
					+ "\"total_amount\":\"" + total_amount + "\"," 
					+ "\"subject\":\"" + subject + "\"," 
					+ "\"body\":\"" + body + "\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\""
				+ "}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result = client.pageExecute(alipayRequest).getBody();
		System.out.println(result);
		
		//输出
		response.setContentType("text/html;charset=" + CHARSET);
		
		//直接将完整的表单html输出到页面 
	    response.getWriter().write(result);
	    response.getWriter().flush(); 
	    response.getWriter().close();

		return forwardOrLogout("checkout_pc", mv);
		
	}
	
	
	/**
	 * 支付宝异步通知页面
	 */
	@RequestMapping("/notify")
	public ModelAndView notify_url() {

		return new ModelAndView("notify");
	}
	
	
	/**
	 * 支付成功后 AliPay 回调页面
	 * @throws AlipayApiException 
	 */
	@RequestMapping("/return")
	public ModelAndView return_url(HttpServletRequest request, ModelAndView mv) throws AlipayApiException {

		System.out.println("支付宝回调参数");
		// charset : utf-8
		// out_trade_no : order_1_1649313663180
		// method : alipay.trade.wap.pay.return
		// total_amount : 120.00
		// sign : RupO/wVkIeAejJfaoXcXLE9ELQM67SshFUKUyeQM1cJUKKOn0iHzzB3oogNsDZ64I7bVjX8gSa0z7i29ccCf5DJaM3qu9OD2QamX22PCOre+xnaYwdItbWuYIyisdLpkBd7awv8fZ7RiPKdqUSnhP/LffukZjW3orLeX6UVECN+KloNuKoT7v6I/jYOPVXMkP/5UNsZzDhO8Dy+8zS9YxLIDceSnyM0b3p1Qb9/fjwhEtUpJ7t0aFw/7AkbXRhQ7OKqXgvYnTGIV/YJBCAj4Epw87AgSSAIY8tRKf72oByXf50Kxhl6RSYZWsOI389Jr5MAC8eEKQxCwlHRWdra1Lw==
		// trade_no : 2022040722001482120502981458
		// auth_app_id : 2021000119647649
		// version : 1.0
		// app_id : 2021000119647649
		// sign_type : RSA2
		// seller_id : 2088621958142183
		// timestamp : 2022-04-07 14:41:22
		
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<>();
		request.getParameterMap().forEach((name, values ) -> {
			
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? //
						valueStr + values[i] : valueStr + values[i] + ",";
			}
			
			System.out.println(name + " : " + valueStr);
			
			params.put(name, valueStr);
		});
		
		mv.addAllObjects(params);
		
		// 支付宝平台校验结果
		boolean verify_result = 
				AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");

		mv = verify_result ? //
				mv.addObject("result", "支付验证成功！") : mv.addObject("result", "支付验证失败！");

		return forwardOrLogout("return", mv);
	}

	
}
