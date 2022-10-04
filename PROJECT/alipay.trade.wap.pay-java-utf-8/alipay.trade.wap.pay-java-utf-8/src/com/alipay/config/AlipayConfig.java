package com.alipay.config;

/*
 * 
 * 启动域名子网穿透
 * ./natapp -authtoken=2c36e3c2b06107e6
 * 
 * 沙箱买家账号 	irsnoq8662@sandbox.com
 * 沙箱登陆密码 	111111
 * 沙箱支付密码 	111111
 * 
 */
public class AlipayConfig {
	
	public static String URL_ROOT = "http://dgqw5t.natappfree.cc/alipay.trade.wap.pay-java-utf-8";
	// 商户appid
	public static String APPID = "2021000119647649";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCBTHvT1QnCbqg+dSRf6Xu1AvzqiKraXk5nl+5qroaUoIPheMx1zHf3IawmMw1/ivE3rEqlNFdLHWe54h1+LyxEvGrdgEOoqphrbQDDjTXBd0zWrQ/MuID02gT3nJsiThILGAkSMOzNSiWH1DkcPZ+1rlbDWGwAka0EbEzNGUThFjNa4Ps3tLyccanPPBCPMq6H1i232Xw1yWuqx2LxKS8alE0A6hky887gb1P2bTRhuhtH0FfMhfAm9pWGiqwY1vJojC1d2h4DPE5JcW39brt7mWzE4AUcOgUHvy45YgaLAju0Dinm6PymKoFXGANBiKMvhaY+5m634h66FU2H007HAgMBAAECggEAXqsRPtgMfT9oD9LU/ySEXtQBOfc2oQvLfC3Rcmq2PrwH014O1XEPpPISiSV4DJo+YvTS8oKQJ38wIvlbfrgrQWhzFhqDb0r4ri7xEDyjzzL6H3Q2zT2JrbyzRlhzfXqL23ONsLQBrY1MTVclOXglJ3CuNfjXmtMOSt7AnKqTs/jyx4KvzhkeqUPL/aelWd8/twmNKwtejqlMz+8qEQbs8ZZDGKMjDFYRYstb5iKGM+DvC9eLlHkETkCNSp2Dy9jQ96/T1a5pLTFNoE3IP7VdBgHXACF2MQJwgrAn5CXhJzhGrgA4OS0QDeisMcadHMv+8mUy51O6dlLSZQMlRMgSAQKBgQC5tUkRHBu0xGuiLX6McbdwVV5ZVm0Wv9VG+grgeQKpX5Ty3M6pxk67CFDfg+h5tOgAWYLtBdZD+8P5LUu5DMUL7JA13NWH8p2HpMgF4Ftz9vP/6sIokbzxlzA/O4/84Un7ehc04Y6IFWe9FaiZjcpql6+yWIZBIGd3l8DC75oB0QKBgQCyPUCM43er5X9i4DSqQ4c6UoPCANVs60jC0QZLUxkhpBp44I2AagkMXtZUxSg3cJpDUv6A0hmlIVrs3t1jrL0nL5TJzlf9odmeLFN8svu8qMKiiLjCaDZjZXUu542Mn5jDereruvmbFyi4Vaal3S4nMBlcOwTqsyacQFkYmCcVFwKBgQCbC5vhSRJorcUfNdR99UMt6lhHRjz1zVuHUqNaHi32svJsgaWZYRPCW4MJ2dtyIXPBNvp0YXcuczh/BS4/ZUXuWqdcEO/F/O1AnnbRUSuJehjlBV7s+CBDo/KIGHZyrrVR2/EcGWUigJyH5VlKOnNQ4yuxPu932FIKKwyos9pkwQKBgF/cP45GfENQD73u1YRJAz6jyEcbya1lsWFNp/P1AkwshSxDoPbSkIE9tEotKx7+CYlx5n0EAlTkZpGEn08PRUABACW1O2+mj3LZZiFn5aomnV9q5utvvuigVXDfiip6X1+q7YOH8NyvjO/u6MzF8x+SZ+FJwLA1n+tjVEe89TALAoGBAJBNqBBtU+McycefeKEOb0Twa4mghzGwTCr5wbVQI0hJ07dKkkH1Nss9okRJeqIFKqXcQwqnevGB/jaP56hsGIJPu9S9dJchiflR6VFi6HQWR6RvCggBjPtc7ooVPRYo8jwYHDvpNNf1oOIvUsY8Vv5T/h1uF5AdCMZKjgSQ9vqG";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = URL_ROOT + "/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = URL_ROOT + "/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "utf-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk6Mk8z6Jh/RDqeqQ5e+/IfbeC1N6o9bLn2eD4MQt06hL2XmYVQ8TRqqAlmETtEE+RAkd/hZR7eSoWO60ikP484NNCrgxaQOkJeHkfoliYUikk2hUus28adV4HyfYNVik17iv5ukCA64AfPLc/uZJ2T9EbKfR1KYhrLX6UfcoUHM1mRP8o0MG1AGNtyaQ6O2J/0L5IolqsortkrIRfIMi07L5NIaF/o9+l7ryDR3eIvwcDPwzct9XYuDFlhqRVgXqZlvkHlI8JkS6ylfJxgO529zQgoYqpBdArvyVzAqXEX8T8y5ZmoaIbBQOa+6zvUpCPwUtDtcHh3j6fqQOXxTC4wIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
