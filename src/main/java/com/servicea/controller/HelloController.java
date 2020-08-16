package com.servicea.controller;

import com.servicea.entity.User;
import com.servicea.service.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
    @Autowired
    private Crawler crawler;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        System.out.println("This is serviceA !");
        try{
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "This is serviceA !";
    }

    @RequestMapping("/timeout")
    @ResponseBody
    public String timeout(){

        return "timeout";
    }

    @RequestMapping("/sixunhuan")
    @ResponseBody
    public String sixunhuan(){
        List<User> userList = new ArrayList<>();
        while (true){
            userList.add(new User());
        }
    }

    @RequestMapping("/pachong")
    @ResponseBody
    public String pachong()throws IOException {
        crawler.start();
        return null;
    }
}