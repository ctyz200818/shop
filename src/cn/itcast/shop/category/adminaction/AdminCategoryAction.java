package cn.itcast.shop.category.adminaction;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by brian on 2017/1/17.
 * 后台一级分类的action
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven{
   private  Category category = new Category();
    private CategoryService categoryService ;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Object getModel() {
        return category;
    }
    //后台执行所有查询一级分类的
    public String findAll(){
        List<Category> clist = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("clist",clist);
        return  "findAll";
    }
    //保存一级分类
    public  String save(){
        categoryService.save(category);
        return "saveSuccess";
    }
    //删除一级分类
    public  String delete(){
        //如果删除一级分类二级也删除，则必根据id查询，再删除
        category = categoryService.findByCid(category.getCid());
        categoryService.delete(category);
        return "deleteSuccess";
    }
    //修改时先回显一级分类数据
    public  String edit(){
       category = categoryService.findByCid(category.getCid());
        return "editSuccess";
          }
          //后台修改一级分类数据
    public  String update(){
            categoryService.update(category);
        return "updateSuccess";
          }
}
