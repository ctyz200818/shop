package cn.itcast.shop.index.action;

/**
 * Created by brian on 2017/1/12.
 */

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/***
 * 访问首页
 */

public class IndexAction extends ActionSupport{
    //注入一级分类的service;
    private CategoryService categoryService;
    //注入商品的service
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String execute(){
        //完成一级分类的查询
       List<Category> clist= categoryService.findAll();
        //System.out.println(clist.size());
        //将一级分类存入session中
        ActionContext.getContext().getSession().put("clist",clist);

        //查询热门商品
        List<Product> hlist = productService.findHot();
        ActionContext.getContext().getValueStack().set("hlist",hlist);

        //查询最新商品
       List<Product> nlist = productService.findNew();
       // System.out.println(nlist.size());
        ActionContext.getContext().getValueStack().set("nlist",nlist);

        return "index";
    }


}
