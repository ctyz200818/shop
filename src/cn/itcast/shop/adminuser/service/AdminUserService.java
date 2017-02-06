package cn.itcast.shop.adminuser.service;

import cn.itcast.shop.adminuser.action.AdminUserAction;
import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brian on 2017/1/17.
 */
@Transactional
public class AdminUserService {
    private AdminUserDao adminUserDao;

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    public AdminUser login(AdminUser adminUser) {
        return  adminUserDao.login(adminUser);
    }
}
