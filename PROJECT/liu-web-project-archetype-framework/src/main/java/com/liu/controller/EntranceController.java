package com.liu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liu.pojo.User;

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
@RequestMapping("/")
public class EntranceController extends GeneralController {

	// 方法级别控制转发
	/*
	 * ModelAndView => MV/C
	 * Model : Request + Response 
	 * View  : Page
	 */
	@RequestMapping("/")
	public ModelAndView login() {
		
		return new ModelAndView("login");
	}
	
	/**
	 * TODO
	 *  AJAX JSON 登陆检验
	 *  /
	 *  JSON 格式是字符串/String，比如
	 *  {username: "3234", password: "23"}
	 *  仅用于数据数据传输，发送端/Page和接收端/Server
	 *  都要解析成 Object 处理。
	 *  /
	 *  页面
	 *  JSON.stringify()	JSON Object(0) -> JSON String(1)
	 *  JSON.parse()		JSON String -> JSON Object
	 *  /
	 *  后端
	 *  @RequestBody		JSON String(1) -> JSON Object(2)
	 *  @ResponseBody		JSON Object -> JSON String
	 *
	 * 
	 * @RequestBody User user		绑定并自动解析页面 JSON 数据
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public @ResponseBody String doLogin(@RequestBody User user) throws JsonProcessingException {

		User user_ = userDao.queryByUsername(user.getUsername());
		
		Boolean result = 
				null != user_ 	// 校验 user 不存在
				&& 				// 逻辑短路
				user_.getPassword().equals(user.getPassword()); // 校验密码 
		
		// 向前端返回 JSON "{\"result\" : false }"
		String jsonResult = 
				result ? jsonResultTemplate(true) : jsonResultTemplate(false);

		// TODO
		//  对比 (? :) 与 if-else
		//  -
		//		 		String jsonResult_ = null;
		//				if (result) {
		//					jsonResult_ = jsonResultTemplate(true);
		//				} else {
		//					jsonResult_ = jsonResultTemplate(false);
		//				}

		// 登陆成功时 session 绑定
		if (result)
			session.setAttribute("username", user.getUsername());
		
		return jsonResult;
	}
	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		return forwardOrLogout("index");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {

		return super.logout();
	}
	
	
	/**
	 * RESTful URL 
	 * 仅用于 GET 请求，在 URL 绑定公有参数
	 * 
	 * @GetMapping("/{id}")
	 * @PathVariable Long id
	 */
//	@GetMapping("/{id}")
//	public @ResponseBody User showUser(@PathVariable Long id) {
//		
//		return null;
//	}

}
