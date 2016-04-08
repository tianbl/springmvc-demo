package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.IUserService;
import com.demo.websocket.WebSocketSessionContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by baolei on 2016/2/16.
 */
@Controller
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        System.out.println("Hello ");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username==null||password==null){
            return "/login";
        }else {
            model.put("userName","登录成功");
            return "/hello";
        }
    }

    @RequestMapping("/websocket")
    public String websocket(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        return "/websocket";
    }

    @RequestMapping("/pushMessage")
    @ResponseBody
    public String pushMessage(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        System.out.println("开始自动推送");
        TextMessage returnMessage = new TextMessage("启动推送每隔两秒钟向前台推送");
        try {
            WebSocketSessionContainer.session.sendMessage(returnMessage);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 1;
                    TextMessage returnMessage = new TextMessage("这是自动推送的信息");
                    while (true){
                        try {
                            System.out.println("这是第"+i+"条信息");
                            Thread.sleep(2000);
                            WebSocketSessionContainer.session.sendMessage(returnMessage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return "信息推送成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "信息推送成功";
        }
    }

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