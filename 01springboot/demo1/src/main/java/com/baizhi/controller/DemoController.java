package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("demo")
    @ResponseBody//将控制器返回值转为json格式字符串 并响应浏览器
    public String demo() {
        System.out.println("demo ok");
        //默认去找名为'demo ok'视图
        return "demo ok";
    }

    @RequestMapping("index")
    public String index() {
        System.out.println("index ok");
        return "index";//返回jsp页面作为视图，需要去application.properties文件中进行相应配置
    }

}
