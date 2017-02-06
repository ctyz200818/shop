package cn.itcast.shop.cart.action;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.scenario.effect.impl.sw.sse.SSERendererDelegate;
import org.apache.struts2.ServletActionContext;

import java.util.Properties;

/**
 * Created by brian on 2017/1/15.
 */
public class CartAction extends ActionSupport {
    //将购物荐添加到购物车
    //接收pid和数量count
    private  Integer pid;
    private Integer count;
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public  String addCart(){
        //封装一个cartItem对象，
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        //设置商品,根据pid查询商品
        Product product=productService.findByPid(pid);
        cartItem.setProduct(product);
        //将购物项添加到购物车，不然new出来，不然每次只有最后次买的商品，应从sessionk 获得
        Cart cart = getCart();
        cart.addCart(cartItem);
        return "addCart";
    }

    //清空购物车
    public String clearCart(){
        //获得购物车对象，调用其清空方法
        Cart cart =getCart();
        cart.clearCart();
        return  "clearCart";
    }
    //删除购物项
    public String removeCart(){
        Cart cart =getCart();
        cart.removeCart(pid);
        return  "removeCart";
    }
//跳转到购物车
    public  String myCart(){
        return "myCart";
    }
    public Cart getCart() {
       Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
        }
        return cart;
    }
}
