package cn.itcast.shop.adminuser.dao;

import cn.itcast.shop.adminuser.action.AdminUserAction;
import cn.itcast.shop.adminuser.vo.AdminUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by brian on 2017/1/17.
 */
public class AdminUserDao extends HibernateDaoSupport {
    public AdminUser login(AdminUser adminUser) {
        String hql = "from AdminUser where username=? and password=?";
        List<AdminUser> list =  this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
