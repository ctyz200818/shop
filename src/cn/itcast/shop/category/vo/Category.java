package cn.itcast.shop.category.vo;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by brian on 2017/1/14.
 */
public class Category implements Serializable {
    private  Integer cid;
    private  String cname;

    //一级分类中存放二级分类的集合
    private Set<CategorySecond> categorySeconds = new HashSet<>();

    public Set<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}