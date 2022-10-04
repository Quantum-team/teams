package com.liu.service.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

/**
*
* @author 刘老师
*
* - 源码请所有同学合理使用，禁止非学习用途。
* - 参照源码多想多练多Debug，杜绝无脑Copy！
* - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
* - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
*
* 支付宝沙箱非 Web 测试单元，该单元仅测试远程接口是否调通，Web 项目中不需要使用
*/
public class AlipayClient {

	public static void main(String[] args) throws AlipayApiException {

		
		DefaultAlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipaydev.com/gateway.do",
				"2021000119647649", 
				"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCBTHvT1QnCbqg+dSRf6Xu1AvzqiKraXk5nl+5qroaUoIPheMx1zHf3IawmMw1/ivE3rEqlNFdLHWe54h1+LyxEvGrdgEOoqphrbQDDjTXBd0zWrQ/MuID02gT3nJsiThILGAkSMOzNSiWH1DkcPZ+1rlbDWGwAka0EbEzNGUThFjNa4Ps3tLyccanPPBCPMq6H1i232Xw1yWuqx2LxKS8alE0A6hky887gb1P2bTRhuhtH0FfMhfAm9pWGiqwY1vJojC1d2h4DPE5JcW39brt7mWzE4AUcOgUHvy45YgaLAju0Dinm6PymKoFXGANBiKMvhaY+5m634h66FU2H007HAgMBAAECggEAXqsRPtgMfT9oD9LU/ySEXtQBOfc2oQvLfC3Rcmq2PrwH014O1XEPpPISiSV4DJo+YvTS8oKQJ38wIvlbfrgrQWhzFhqDb0r4ri7xEDyjzzL6H3Q2zT2JrbyzRlhzfXqL23ONsLQBrY1MTVclOXglJ3CuNfjXmtMOSt7AnKqTs/jyx4KvzhkeqUPL/aelWd8/twmNKwtejqlMz+8qEQbs8ZZDGKMjDFYRYstb5iKGM+DvC9eLlHkETkCNSp2Dy9jQ96/T1a5pLTFNoE3IP7VdBgHXACF2MQJwgrAn5CXhJzhGrgA4OS0QDeisMcadHMv+8mUy51O6dlLSZQMlRMgSAQKBgQC5tUkRHBu0xGuiLX6McbdwVV5ZVm0Wv9VG+grgeQKpX5Ty3M6pxk67CFDfg+h5tOgAWYLtBdZD+8P5LUu5DMUL7JA13NWH8p2HpMgF4Ftz9vP/6sIokbzxlzA/O4/84Un7ehc04Y6IFWe9FaiZjcpql6+yWIZBIGd3l8DC75oB0QKBgQCyPUCM43er5X9i4DSqQ4c6UoPCANVs60jC0QZLUxkhpBp44I2AagkMXtZUxSg3cJpDUv6A0hmlIVrs3t1jrL0nL5TJzlf9odmeLFN8svu8qMKiiLjCaDZjZXUu542Mn5jDereruvmbFyi4Vaal3S4nMBlcOwTqsyacQFkYmCcVFwKBgQCbC5vhSRJorcUfNdR99UMt6lhHRjz1zVuHUqNaHi32svJsgaWZYRPCW4MJ2dtyIXPBNvp0YXcuczh/BS4/ZUXuWqdcEO/F/O1AnnbRUSuJehjlBV7s+CBDo/KIGHZyrrVR2/EcGWUigJyH5VlKOnNQ4yuxPu932FIKKwyos9pkwQKBgF/cP45GfENQD73u1YRJAz6jyEcbya1lsWFNp/P1AkwshSxDoPbSkIE9tEotKx7+CYlx5n0EAlTkZpGEn08PRUABACW1O2+mj3LZZiFn5aomnV9q5utvvuigVXDfiip6X1+q7YOH8NyvjO/u6MzF8x+SZ+FJwLA1n+tjVEe89TALAoGBAJBNqBBtU+McycefeKEOb0Twa4mghzGwTCr5wbVQI0hJ07dKkkH1Nss9okRJeqIFKqXcQwqnevGB/jaP56hsGIJPu9S9dJchiflR6VFi6HQWR6RvCggBjPtc7ooVPRYo8jwYHDvpNNf1oOIvUsY8Vv5T/h1uF5AdCMZKjgSQ9vqG", 
				"json", 
				"utf-8", 
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk6Mk8z6Jh/RDqeqQ5e+/IfbeC1N6o9bLn2eD4MQt06hL2XmYVQ8TRqqAlmETtEE+RAkd/hZR7eSoWO60ikP484NNCrgxaQOkJeHkfoliYUikk2hUus28adV4HyfYNVik17iv5ukCA64AfPLc/uZJ2T9EbKfR1KYhrLX6UfcoUHM1mRP8o0MG1AGNtyaQ6O2J/0L5IolqsortkrIRfIMi07L5NIaF/o9+l7ryDR3eIvwcDPwzct9XYuDFlhqRVgXqZlvkHlI8JkS6ylfJxgO529zQgoYqpBdArvyVzAqXEX8T8y5ZmoaIbBQOa+6zvUpCPwUtDtcHh3j6fqQOXxTC4wIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB", 
				"RSA2");
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
		request.setBizModel(model);
		model.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
		model.setTotalAmount("88.88");
		model.setSubject("Iphone6 16G");
		AlipayTradePrecreateResponse response = alipayClient.execute(request);
		System.out.println(response.getBody());
		System.out.println(response.getQrCode());

	}

}
