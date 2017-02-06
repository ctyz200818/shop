package cn.itcast.shop.order.action;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.Date;

/**
 * Created by brian on 2017/1/15.
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
 //模型驱动使用对象
    private  Order order = new Order();
    private OrderService orderService;
    //接收page参数
    private  Integer page;
    //接收哪个银行支付
    private  String pd_FrpId;
    //接收付款成功后响应数据，订单号，金额
    private  String r6_Order;
    private  String r3_Amt;

    public void setR6_FrpId(String r6_FrpId) {
        this.r6_Order = r6_FrpId;
    }

    public void setR3_Amt(String r3_Amt) {
        this.r3_Amt = r3_Amt;
    }

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    //生成定单
    public String save(){
        //保存定单数据到数据库
        //定单数据的补全
        order.setOrdertime(new Date());
        order.setState(1);//1.未付款，2已付，但未发货，3发货但没确认收货，4定单完成
        //总计的信息是购物车的信息
        Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if(cart==null){
            this.addActionError("亲！您还未购物，请先去购物！");
            return "msg";
        }
        order.setTotal(cart.getTotal());
        //设置定单中的定单项
        for(CartItem cartItem: cart.getCartItems()){
            OrderItem orderItem  = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        //设置订单所属用户
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(user==null){
            this.addActionError("您还未登陆，请先去登陆");
            return "login";
        }
        order.setUser(user);
        orderService.sava(order);
        //清空购物车的东西
        cart.clearCart();
        //将定单数据显示到页面上
        return "saveSuccess";
    }
    //通过用户的uid来查询用户的定单
    public  String findByUid(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
       PageBean<Order> pageBean= orderService.findByUidPage(user.getUid(),page);
        ActionContext.getContext().getValueStack().set("pageBean" ,pageBean);
        return "findByUid";
    }
    //根据定单oid查询用户的定单
    public String findByOid(){
        order = orderService.findByOid(order.getOid());
        return "findByOidSuccess";
    }
    //定单付款
    public  String payOrder() throws IOException {
        //修改订单，更改地址之类的
        Order currorder = orderService.findByOid(order.getOid());
        currorder.setAddr(order.getAddr());
        currorder.setName(order.getName());
        currorder.setPhone(order.getPhone());
        orderService.update(currorder);
        //为定单付款
        // 付款需要的参数:
        String p0_Cmd = "Buy"; // 业务类型:
        String p1_MerId = "10001126856";// 商户编号:
        String p2_Order = order.getOid().toString();// 订单编号:
        String p3_Amt = "0.01"; // 付款金额:
        String p4_Cur = "CNY"; // 交易币种:
        String p5_Pid = ""; // 商品名称:
        String p6_Pcat = ""; // 商品种类:
        String p7_Pdesc = ""; // 商品描述:
        String p8_Url = "http://192.168.36.69:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
        String p9_SAF = ""; // 送货地址:
        String pa_MP = ""; // 商户扩展信息:
        String pd_FrpId = this.pd_FrpId;// 支付通道编码:
        String pr_NeedResponse = "1"; // 应答机制:
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue); // hmac
        // 向易宝发送请求:
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        // 重定向:向易宝出发:
        ServletActionContext.getResponse().sendRedirect(sb.toString());
        return NONE;
    }
    public String callBack(){
        //修改定单状态为已经付款
        Order order = orderService.findByOid(Integer.parseInt(r6_Order));
        order.setState(2);
        orderService.update(order);
        //在页面显示付款成功信息
        this.addActionMessage("订单付款成功！订单编号："+r6_Order+"付款金金额"+r3_Amt);

        return "msg";
    }
    //修改订单状态，确认收货
    public  String updateState(){
       order =  orderService.findByOid(order.getOid());
        order.setState(4);
        orderService.update(order);
        return "updateStateSuccess";
    }
    @Override
    public Order getModel() {
        return order;
    }
}
