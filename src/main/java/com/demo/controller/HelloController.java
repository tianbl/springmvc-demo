package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by baolei on 2016/2/16.
 */
@Controller
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("=================================================>");
        logger.info("=======>info {} greeting","占位符{}");
        logger.debug("=======>debug greeting");
        logger.warn("=======>warn greeting");
        logger.error("=======>error greeting");
        Map<String, Object> map = new HashMap<>();
        map.put("userName", name);
        return new ModelAndView("/hello",map);
    }

    @RequestMapping("/greetingt")
    public String greetingt() {
        System.out.println("Hello ");
        return "/hello";
    }

    @RequestMapping("/returnuser")
    @ResponseBody
    public User getObject() {
        User user = userService.selectByPrimaryKey(1L);
        return user;
    }

    @RequestMapping("/returndate")
    @ResponseBody
    public Date getDateTest() {
        Date date = new Date();
        return date;
    }
}