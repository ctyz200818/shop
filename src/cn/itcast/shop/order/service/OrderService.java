package cn.itcast.shop.order.service;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by brian on 2017/1/15.
 */
@Transactional
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void sava(Order order) {
        orderDao.save(order);
    }

    public PageBean<Order> findByUidPage(Integer uid, Integer page) {
        PageBean pagebean =new PageBean();
        pagebean.setPage(page);
        Integer limit =5;
        pagebean.setLimit(limit);
        Integer begin =(page-1)*limit;
        Integer totalCount =null;
        totalCount=orderDao.findCountByUid(uid);
        pagebean.setTotalCount(totalCount);
        Integer totalpage =null;
        if(totalCount%limit==0){
            totalpage = totalCount/limit;
        }else{
            totalpage = totalCount/limit+1;
        }
        pagebean.setTotalPage(totalpage);
        List<Order> list = orderDao.findOderByUid(uid,limit,begin);
        pagebean.setList(list);

        return pagebean;
    }

    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    public void update(Order currorder) {
        orderDao.update(currorder);
    }

    public PageBean<Order> findByPage(Integer page) {
        PageBean pagebean =new PageBean();
        pagebean.setPage(page);
        Integer limit =5;
        pagebean.setLimit(limit);
        Integer begin =(page-1)*limit;
        Integer totalCount =null;
        totalCount=orderDao.findCountByPage();
        pagebean.setTotalCount(totalCount);
        Integer totalpage =null;
        if(totalCount%limit==0){
            totalpage = totalCount/limit;
        }else{
            totalpage = totalCount/limit+1;
        }
        pagebean.setTotalPage(totalpage);
        List<Order> list = orderDao.findOderByPage(limit,begin);
        pagebean.setList(list);

        return pagebean;
    }

    public List<OrderItem> findOderItem(Integer oid) {
       return orderDao.findOrderItem(oid);
    }
}
