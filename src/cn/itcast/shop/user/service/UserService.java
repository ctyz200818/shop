package cn.itcast.shop.user.service;
import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brian on 2017/1/12.
 */
@Transactional
public class UserService {
    //注入UserDao
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    //安用户名查询用户方法
    public User findByUserName(String username){
        return userDao.findByUserName(username);
    }

    public void save(User user) {
        user.setState(0);//0代表未激活，1代表激活
        String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
        user.setCode(code);
        userDao.save(user);
        MailUtils.sendMail(user.getEmail(),user.getCode());
    }

    public User findUserByCode(String code) {
        return  userDao.findUserByCode(code);
    }

    public void update(User exsitUser) {
        userDao.update(exsitUser);
    }

    public User login(User user) {
       return userDao.login(user);
    }
}
