package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Component 原生用来创建对象的注解 衍生了三个子注解：@Controller @Service @Repository
 * 区分三层，但mybatis没有实现类无@Repository
 */
@Service//用来通知springboot 扫描当前注解 作用：用来在工厂中创建对象 相当于配置文件中<bean id="" class="com.baizhi.UserServiceImpl">
@Transactional//控制事务    作用：用来控制事务   修饰范围：类-代表类中所有方法加入事务；方法上-当前方法加入事务控制
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(User user) {

        System.out.println("执行DAO方法之前id:"+user.getId());
        //保存订单
        userDAO.save(user);
        System.out.println("执行DAO方法之前id:"+user.getId());

    }

    /**
     * 在一个业务层里调用其他的业务层，涉及到事务的传播，eg:JDBC中保证一个线程始终是一个连接对象.
     * spring框架把事务做得更细，认为jdbc的操作在开发中是有局限的（只能保证一个用户始终在一个链接中），
     * 但实际需求很多时候希望本次操作控制在不同的事务中eg:取钱,取钱和日志记录应该是独立事务,则会用到传播属性
     * Propagation.REQUIRED：默认值,需要事务(创建一个链接conn1),外部存在事务则融入事务，没有事务则开启新事务
     * Propagation.SUPPORTS：支持事务,有,这融入,无,不开启新事务.多用于查询方法,若类不支持事务，则必须要写，
     *                      eg:AService.save(){ BService.find(){ CService.update(){}}}多层业务时保证事务继续向下传递
     * Propagation.REQUIRES_NEW：需要新事物,外部存在事务,外部事务挂起;自己创建的事务运行;运行结束回复外部事务
     * Propagation.MANDATORY：强制事务,没有事务报错.
     * Propagation.NOT_SUPPORTED：不支持事务,若有事务,外部事务挂起,自己以非事务运行,运行后外部事物恢复.
     * Propagation.NEVER：不需要事务,有事务会报错.
     * Propagation.NESTED：嵌套事务,数据库不支持.
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)//查询没有事务效率更高；propagation:事务传播属性，发生在多个业务层之间事务的传递
    public List<User> findAll() {//取钱 若取钱用REQUIRED传播级别，则只会记录成功记录，失败则没有记录

        //1.取钱  独立事务
        //2.记录日志    独立事务
        //logService.log()
        return userDAO.findAll();
    }
}

//@Transactional(propagation = Propagation.REQUIRES_NEW)
//LogService log方法
