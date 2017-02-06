package cn.itcast.shop.product.adminaction;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by brian on 2017/1/17.
 */
public class AdminProductAction extends ActionSupport implements ModelDriven {
    private Product product =new Product();
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    //收到page
    private Integer page;
    //注入二级分类service
    private CategorySecondService categorySecondService;
    //文件上传需要的参数
    private File upload;//要与add.jsp中参数一到致
    private  String uploadFileName;//接收文件上传的文件名
    private  String uploadContextType;//接收文件上传的类型；

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }

    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Object getModel() {
        return product;
    }
    //带分布查询商品
    public  String findAllByPage(){
        PageBean<Product> pageBean = productService.findAllByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPage";
    }
    //跳转到添加页面方法
    public String addPage(){
        //查询所有二级分类
        List<CategorySecond> cslist = categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("cslist",cslist);
        //页面跳转
        return "addPageSuccess";
    }
    //保存添加页面方法
    public String save() throws IOException {
        product.setPdate(new Date());
        if(upload!=null){
            //获得文件的磁盘绝对路径
            String readlPath = ServletActionContext.getServletContext().getRealPath("/products");
            //创建一个文件,含有地址，空文件
            File diskFile  = new File(readlPath+"//"+uploadFileName);
            FileUtils.copyFile(upload,diskFile);//相当于将文件复制到此路径下的空文件
            product.setImage("products/"+uploadFileName);
        }
       productService.save(product);
        return "saveSuccess";
    }
    //删除方法
    public String delete(){
        //称查询再删除
         product = productService.findByPid(product.getPid());
       //图片路径：products/1/cs10001.jpg
        String path = product.getImage();
        if(path!=null){
            //获得的是磁盘地址
            String realPath= ServletActionContext.getServletContext().getRealPath("/"+path);
            File file = new File(realPath);
            file.delete();
        }
        productService.delete(product);
        return  "deleteSuccess";
    }
    //编辑商品页面
    public String edit(){
        //根据商品id查询商品
        product =productService.findByPid(product.getPid());
        //查询所有的二级分类
        List<CategorySecond> cslist = categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("cslist",cslist);
        return "editSuccess";
    }
    //修改商品
    public  String update() throws IOException {
        product.setPdate(new Date());
        //修改文件上传
        if(upload!=null){
        //将原来商品的图片删除
            String path = product.getImage();
            File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
            file.delete();
            //更新商品图片
        String realPath = ServletActionContext.getServletContext().getRealPath("/products");
        File diskFile = new File(realPath+"//"+uploadFileName);
        FileUtils.copyFile(upload,diskFile);
        product.setImage("products/"+uploadFileName);
        }
        //更新商品
        productService.update(product);
        return "updateSuccess";
    }

}
