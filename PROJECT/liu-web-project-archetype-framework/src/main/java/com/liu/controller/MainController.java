package com.liu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liu.dao.IPersonDao;
import com.liu.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/main")
public class MainController extends GeneralController {

    /**
     * 查询 UserInfo 数据列表，AJAX / JSON 发送前端
     */
    @RequestMapping(value = "/listUserInfo", method = RequestMethod.GET)
    public @ResponseBody String listUserInfo() throws JsonProcessingException {

        List<Person> list = personDao.queryList();

        // TODO 返回格式
        //    [
        //        {"id":1,"status":"0","timestamp":1649058899000,"job":"Web Developer","realName":"Nabiha Armstrong","email":"nabiha.armstrong@example.com","location":"Cluj Napoca, RO","avatar":null},
        //        {"id":2,"status":"0","timestamp":1649058931000,"job":"Product Designer","realName":"Dan Holding","email":"dan.holding@example.com","location":"New York, USA","avatar":null},
        //        {"id":3,"status":"0","timestamp":1649058971000,"job":"Will Smith","realName":"Sales Manager","email":"will.smith@example.com","location":"Los Angeles, USA","avatar":null}
        //    ]
        return mapper.writeValueAsString(list);
    }
}
