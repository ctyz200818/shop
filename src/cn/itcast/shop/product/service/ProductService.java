package cn.itcast.shop.product.service;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by brian on 2017/1/14.
 */
@Transactional
public class ProductService {
    private ProductDao productDao;
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findHot() {
        //首页热门商品
        return  productDao.findHot();

}

    public List<Product> findNew() {
       return  productDao.findNew();
    }

    public Product findByPid(Integer pid) {
        return  productDao.findByPid(pid);
    }
    //根据一级分类带有分类查询
    public PageBean<Product> findByPageCid(Integer cid, int page) {
        PageBean<Product> pagebean = new PageBean<>();
        pagebean.setPage(page);
        int limit =8;
        pagebean.setLimit(limit);
        //设置总记录数
        int totalCount = 0;
        totalCount = productDao.findCountCid(cid);
        pagebean.setTotalCount(totalCount);
        //设置总页数
        //Math.ceil(totalCount/limit)
        int totalPage = 0;
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }else {
            totalPage=totalCount/limit+1;
        }
        pagebean.setTotalPage(totalPage);
        //显示查询数据集合
        //从哪开始
        int begin=(page-1)*limit;
       List<Product> list= productDao.findByPageCid(cid,begin,limit);
        pagebean.setList(list);
        return  pagebean;
    }
//根据二级分类查询商品信息
    public PageBean<Product> findByPageCsid(Integer csid, int page) {
        PageBean<Product> pagebean = new PageBean<>();
        pagebean.setPage(page);
        int limit =8;
        pagebean.setLimit(limit);
        //设置总记录数
        int totalCount = 0;
        totalCount = productDao.findCountCsid(csid);
        pagebean.setTotalCount(totalCount);
        //设置总页数
        //Math.ceil(totalCount/limit)
        int totalPage = 0;
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }else {
            totalPage=totalCount/limit+1;
        }
        pagebean.setTotalPage(totalPage);
        //显示查询数据集合
        //从哪开始
        int begin=(page-1)*limit;
        List<Product> list= productDao.findByPageCsid(csid,begin,limit);
        pagebean.setList(list);
        return  pagebean;
    }

    public PageBean<Product> findAllByPage(Integer page) {
        PageBean<Product> pagebean = new PageBean<>();
        pagebean.setPage(page);
        int limit =10;
        pagebean.setLimit(limit);
        //设置总记录数
        int totalCount = 0;
        totalCount = productDao.findCount();
        pagebean.setTotalCount(totalCount);
        //设置总页数
        //Math.ceil(totalCount/limit)
        int totalPage = 0;
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }else {
            totalPage=totalCount/limit+1;
        }
        pagebean.setTotalPage(totalPage);
        //显示查询数据集合
        //从哪开始
        int begin=(page-1)*limit;
        List<Product> list= productDao.findByPage(begin,limit);
        pagebean.setList(list);
        return  pagebean;
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public void delete(Product product) {
        productDao.delete(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }
}
