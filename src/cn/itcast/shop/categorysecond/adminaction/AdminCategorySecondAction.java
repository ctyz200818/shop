package cn.itcast.shop.categorysecond.adminaction;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by brian on 2017/1/17.
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven{
    private CategorySecond categorySecond =new CategorySecond();
    private CategorySecondService categorySecondService;
    //注入一级分类service，供二级分类添加时使用
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }
    @Override
    public Object getModel() {
        return categorySecond;
    }
    //接收page
    private  Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }
//显示二级分类
    public String findAllByPage(){
      PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }
    //添加二级分类页面
    public String addPage(){
        //查询所有一级分类
        List<Category> clist = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("clist",clist);
        return "addPageSuccess";
    }
    //保存二级分类的添加
    public String save(){
        categorySecondService.save(categorySecond);
        return  "saveSuccess";
    }
    //删除二级分类
    public String delete(){
        //如果要集联删除，要先查询，配置cascade
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }
    //编辑二级分类页面
    public String edit(){
        //根据二级分类查询二级分类对象
        categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
        //查询所有一级分类
        List<Category> clist = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("clist",clist);
        return "editSuccess";
    }
    //更新二级分类修改页面
    public String update(){
        categorySecondService.update(categorySecond);
        return "updateSuccess";
    }
}
