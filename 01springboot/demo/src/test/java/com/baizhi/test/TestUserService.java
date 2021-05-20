package com.baizhi.test;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest(classes = DemoApplication.class)//类对象 .class,区别类的对象 new
//@RunWith(SpringRunner.class)//未加，要报错
public class TestUserService extends BasicTest{//将注解移到工具类中,继承此类则注解生效，避免多类重复加注解

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("小明123");
        user.setAge(23);
        user.setSalary(2300.3);
        userService.save(user);
    }
    @Test
    public void testFindAll() {
        userService.findAll().forEach(user -> System.out.println(user));
    }

}
