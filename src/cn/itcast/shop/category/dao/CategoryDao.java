package cn.itcast.shop.category.dao;

import cn.itcast.shop.category.vo.Category;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by brian on 2017/1/14.
 */
public class CategoryDao extends HibernateDaoSupport{
    //查询 所有一级分类
    public List<Category> findAll() {
        String hql="from Category";
        List<Category> clist=  this.getHibernateTemplate().find(hql);
        return  clist;
    }

    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    public Category findByCid(Integer cid) {
        return  this.getHibernateTemplate().get(Category.class,cid);
    }

    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }

    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }
}
