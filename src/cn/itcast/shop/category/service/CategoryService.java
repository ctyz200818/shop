package cn.itcast.shop.category.service;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by brian on 2017/1/14.
 */
@Transactional
public class CategoryService {
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        //查询所有一级分类

        return  categoryDao.findAll();
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Category findByCid(Integer cid) {
       return categoryDao.findByCid(cid);
    }

    public void delete(Category category) {
        categoryDao.delete(category);

    }


    public void update(Category category) {
        categoryDao.update(category);
    }
}
