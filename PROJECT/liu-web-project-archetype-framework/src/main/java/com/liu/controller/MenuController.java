package com.liu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

// 声明 RESTful 接口，简化绑定 URL GET级别参数
@RestController
// 类级别控制转发
@RequestMapping("/menu")
public class MenuController extends GeneralController {

	@RequestMapping("/")
	public ModelAndView menu() {

		return forwardOrLogout("menu");
	}


	@RequestMapping("/payment")
	public ModelAndView payment() {

		return forwardOrLogout("payment");
	}
	
	@RequestMapping("/list_person")
	public ModelAndView listPerson() {
		
		return forwardOrLogout("list_person");
	}
	
	@RequestMapping("/list_product")
	public ModelAndView listProduct() {

		return forwardOrLogout("list_product");
	}

}
