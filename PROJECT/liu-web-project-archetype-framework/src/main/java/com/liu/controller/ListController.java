package com.liu.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liu.pojo.Person;

/**
 * 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 * 用户列表页面
 */

//声明 RESTful 接口，简化绑定 URL GET级别参数
@RestController
//类级别控制转发
@RequestMapping("/list")
public class ListController extends GeneralController {

	@RequestMapping("/")
	public ModelAndView listPerson() {
		
		return new ModelAndView("list_person");
	}
	
	@RequestMapping("/")
	public ModelAndView listPerson2() {
		
		List<Person> list = personDao.queryList();
		ModelAndView mv = new ModelAndView("list_person");
		mv.addObject(list);
		
		return mv;
	}

	/**
	 * Ajax 方式获取后端数据，不带分页
	 */
	@GetMapping("/jsonListPerson")
    public @ResponseBody String jsonListPerson() throws JsonProcessingException {

        List<Person> list = personDao.queryList();

        // TODO 返回格式
        //    [
        //        {"id":1,"status":"0","timestamp":1649058899000,"job":"Web Developer","realName":"Nabiha Armstrong","email":"nabiha.armstrong@example.com","location":"Cluj Napoca, RO","avatar":null},
        //        {"id":2,"status":"0","timestamp":1649058931000,"job":"Product Designer","realName":"Dan Holding","email":"dan.holding@example.com","location":"New York, USA","avatar":null},
        //        {"id":3,"status":"0","timestamp":1649058971000,"job":"Will Smith","realName":"Sales Manager","email":"will.smith@example.com","location":"Los Angeles, USA","avatar":null}
        //    ]
        return mapper.writeValueAsString(list);
    }
	
	/*
	 * Ajax 方式获取后端带分页数据并记录分页信息
	 */
	@GetMapping("/jsonListPersonPaging/{page}/{size}")
	public @ResponseBody String jsonListPersonPaging(
			@PathVariable Integer page,
			@PathVariable Integer size
			) throws JsonProcessingException {
		
		Pageable paging = PageRequest.of(page, size);
		Page<Person> pagePerson = personDao.queryListPage(paging);
		
		return mapper.writeValueAsString(pagePerson);
	}
	
}
