package test;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.TestExecutionListeners;

/**
 * Created by brian on 2017/1/13.
 */
public class UserTest {
    @Test
    public void testUserService(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService= (UserService) ctx.getBean("userService");
       User u= userService.findByUserName("aa");
        System.out.println(u.getName());
    }
}
