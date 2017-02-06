package cn.itcast.shop.user.dao;

import cn.itcast.shop.user.vo.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by brian on 2017/1/12.
 */
public class UserDao extends HibernateDaoSupport{
    //按名称查询是否有该用户
    public User findByUserName(String username){
        String hql="from  User where username=?";
        List<User> list =this.getHibernateTemplate().find(hql,username);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return  null;
    }

    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    public User findUserByCode(String code) {
        String hql="from User where code=?";
        List<User> list=this.getHibernateTemplate().find(hql,code);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return  null;
    }

    public void update(User exsitUser) {
        this.getHibernateTemplate().update(exsitUser);
    }

    public User login(User user) {
        String hql="from User where username=?and password=?and state=?";
        List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return  null;
    }
}
