package cn.itcast.shop.categorysecond.vo;

import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.vo.Product;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brian on 2017/1/14.
 */
public class CategorySecond {
    private  Integer csid;
    private  String csname;
    //配置一级分类对象
    private Category category;
    //配置商品集合
    private  Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
