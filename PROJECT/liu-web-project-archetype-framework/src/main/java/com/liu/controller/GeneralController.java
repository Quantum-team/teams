package com.liu.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.dao.IUserDao;
import com.liu.dao.IPersonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
abstract class GeneralController {


    // 声明注入
    @Autowired
    protected IPersonDao personDao;
    
	@Autowired
	protected HttpSession session;
	
	@Autowired
	protected IUserDao userDao;

	/**
	 * 转发页面，默认不带 Request 参数
	 */
	protected ModelAndView forwardOrLogout(String page) {
		
		return this.forwardOrLogout(page, null);
	}
	
	/**
	 * 转发页面，带 Request 参数
	 */
	protected ModelAndView forwardOrLogout(String page, ModelAndView mv) {
		
		try {
			
			String usernameSession = session.getAttribute("username").toString();
			
			if(null != mv)
				mv.setViewName(page);
			
			return null == usernameSession || usernameSession.isEmpty() ? //
					logout() : 
						null != mv ?  
								mv : new ModelAndView(page);
			
		} catch (Exception e) {
			/*
			 * 无法通过简单 null == session 对 Spring 注入 session 判空
			 * 判空通过时 session.getAttribute("username")
			 * 仍然有 NullPointException
			 * catch 保证后端级别异常不到达页面
			 */
			return logout();
		}
	}
	
	protected ModelAndView logout() {
		
		// 注销当前 session
		session.invalidate();
		
		// 重定向至登陆
		return new ModelAndView("redirect:/");
	}

	/**
	 * 下载文件
	 */
	protected ResponseEntity<InputStreamResource> download(String fullPath) throws IOException {
		FileSystemResource file = new FileSystemResource(fullPath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment; filename=" + file.getFilename());
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity
				.ok() //
				.headers(headers) //
				.contentLength(file.contentLength()) //
				.contentType(MediaType.parseMediaType("application/octet-stream")) //
				.body(new InputStreamResource(file.getInputStream()));
	}
	
	/**
	 * JSON 工具类
	 */
	protected static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 老手艺纯手工拼装一个简单的 JSON 格式字符串
	 */
	protected String jsonResultTemplate(Object resultContent) throws JsonProcessingException {

		//	TODO 使用 ObjectMapper
		//	 Map<String, Object> map = new HashMap<>();
		//	 map.put("result", true);
		//	 mapper.writeValueAsString(map);

		return "{\"result\" : " + resultContent + " }";
	}


}
