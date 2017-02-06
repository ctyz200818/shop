package cn.itcast.shop.order.adminaction;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * Created by brian on 2017/1/18.
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
    private Order order = new Order();
    private OrderService orderService ;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    private  Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Order getModel() {
        return order;
    }
    //带分布查询方法
    public String findAllByPage(){
        PageBean<Order> pageBean = orderService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }
    //根据定单id查询定单项
    public String findOrderItem(){
        List<OrderItem> list = orderService.findOderItem(order.getOid());
        ActionContext.getContext().getValueStack().set("list",list);
        return "findOrderItemSuccess";
    }
    //修改订单状态
    public String updateState(){
        order = orderService.findByOid(order.getOid());
        order.setState(3);
        orderService.update(order);
        return  "updateSuccess";
    }
}
