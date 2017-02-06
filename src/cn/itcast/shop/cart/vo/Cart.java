package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by brian on 2017/1/15.
 */
public class Cart implements Serializable{
    //购物车属性
    //购物项的集合,map的key是pid，value是购物项
    private Map<Integer,CartItem> map= new LinkedHashMap<>();//HashMap是无序的
   //cart中有个属性叫cartItems，可用于页面显示
    public Collection<CartItem> getCartItems(){
       return map.values();
   }
    //购物总计
    private  double total;

    //购物车功能
    //1.将购物项添加到购物车
    public void addCart(CartItem cartItem){
        //判断购物车是否存在该对象，
        Integer pid=cartItem.getProduct().getPid();
        if(map.containsKey(pid)){
            //获得原来的购物项
            CartItem _cartItem1 =map.get(pid);
            _cartItem1.setCount(_cartItem1.getCount()+cartItem.getCount());
        }else{
            map.put(pid,cartItem);
        }
        // 如果存在，数量，总计=总计+购物项小计
        //如果不存在，在map中添加购物项，总计=总计+购物小计
        total = total+cartItem.getSubtotal();
    }

    //2.从购物车移除购物项
    public void removeCart(Integer pid){
        CartItem cartItem = map.remove(pid);
        //总计-移除的购物项小计
        total=total-cartItem.getSubtotal();
    }

    //3.清空购物车
    public void clearCart(){
        //将购物项清空，总数为0
        map.clear();
        total=0;
    }

    public Map<Integer, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, CartItem> map) {
        this.map = map;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
