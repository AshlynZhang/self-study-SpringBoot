package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController//等价于 @Controller + @ResponseBody组合注解， 作用将controller中所有方法返回值都转换为json进行响应
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("demo")
//    @ResponseBody
    public String demo() {
        System.out.println("demo ok");
        return "demo ok";
    }

    @RequestMapping("test")
//    @ResponseBody
    public String test() {
        System.out.println("test ok");
        return "test ok";
    }
}
