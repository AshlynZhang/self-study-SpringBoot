package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    //用插件自动生成,注意默认级别是info,debug日志需要去配置文件中开启
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 删除用户信息
     * @param id
     * @param user
     * @return
     */
    @RequestMapping("delete")
    public String delete(Integer id,User user) {
//        System.out.println("删除用户信息："+user);
        log.debug("删除用户信息:{}",user);
        userService.delete(id);
        return "redirect:/user/findAll";

    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("update")
    public String update(User user) {
//        System.out.println("修改用户信息:"+user);
        log.debug("修改用户信息:{},***{}***",user,"这是占位");
        userService.update(user);
        return "redirect:/user/findAll";//重定向避免url不变重复刷新，表单重复提交
    }

    /**
     * 查询一个
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("findById")
    public String findById(Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "showOne";
    }
    /**
     * 添加用户
     */
    @RequestMapping("save")
    public String save(User user,String name) {
//        System.out.println("name:"+name);
//        System.out.println("user:"+user);
        log.debug("name:{}",name);
        log.debug("user:{}",user);
        userService.save(user);
        return "redirect:/user/findAll";
    }

    /**
     * 查询所有
     */
    @RequestMapping("findAll")
    public String findAll(HttpServletRequest request, Model model) {
        List<User> users = userService.findAll();
        log.debug("用户总数：{}",users.size());
        //保存在作用域
        model.addAttribute("users", users);
        return "showAll";
    }
}
