package com.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

/**
 * Created by baolei on 2016/2/16.
 */
@Controller
public class HelloController {
    private static Logger logger = Logger.getLogger(HelloController.class);

    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("=================================================>");
        logger.info("=======>info greeting");
        logger.debug("=======>debug greeting");
        logger.warn("=======>warn greeting");
        logger.error("=======>error greeting");
        Map<String, Object> map = new HashMap<>();
        map.put("userName", name);
        return new ModelAndView("/hello",map);
    }

    @RequestMapping("/greetingt")
    public String greetingt() {
//        System.out.println("Hello " + name);
//        Map<String, Object> map = new HashMap<>();
//        map.put("userName", name);
        return "/hello";
    }

    @RequestMapping("/returnuser")
    @ResponseBody
    public User getObject() {
        User user = new User();
        user.setUsername("user");
        user.setPasswd("passwd");
        return user;
    }

    @RequestMapping("/returndate")
    @ResponseBody
    public Date getDateTest() {
        Date date = new Date();
        return date;
    }
}
