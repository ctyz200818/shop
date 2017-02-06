package cn.itcast.shop.order.dao;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by brian on 2017/1/15.
 */
public class OrderDao extends HibernateDaoSupport{
    public void save(Order order) {
        this.getHibernateTemplate().save(order);
    }

    public Integer findCountByUid(Integer uid) {
        String hql ="select count(*) from Order o where o.user.uid=? ";
        List<Long > list = this.getHibernateTemplate().find(hql,uid);
        if(list!=null&&list.size()>0){
           return list.get(0).intValue();
        }
        return null;
    }

    public List<Order> findOderByUid(Integer uid, Integer limit, Integer begin) {
        String hql="from Order o where o.user.uid=? order by orderTime desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin,limit));
        return list;
    }

    public Order findByOid(Integer oid) {
        return this.getHibernateTemplate().get(Order.class,oid);
    }

    public void update(Order currorder) {
        this.getHibernateTemplate().update(currorder);
    }

    public Integer findCountByPage() {
        String hql = "select count(*) from Order ";
        List<Long> list= this.getHibernateTemplate().find(hql);
        if(list!=null&&list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    public List<Order> findOderByPage(Integer limit, Integer begin) {
        String hql = "from Order order by ordertime desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,null,begin,limit));
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List<OrderItem> findOrderItem(Integer oid) {
        String hql = "from OrderItem oi where oi.order.oid=? ";
        List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
