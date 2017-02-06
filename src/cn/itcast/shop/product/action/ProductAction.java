package cn.itcast.shop.product.action;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**

 * 
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 用于接收数据的模型驱动.
	private Product product = new Product();
	//接收一级分类的cid
	private Integer cid;
	//接收二级分类的id
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//接收当前页数
	private  int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	// 注入商品的Service
	private ProductService productService;

	// 注入一级分类的Service,调用其方法查询所有一级分类
	private CategoryService categoryService;


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}

	// 根据商品的ID进行查询商品:执行方法:
	public String findByPid() {
		// 调用Service的方法完成查询.
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	public String findByCid(){
		//List<Category> clist =categoryService.findAll();可以不用再查了，session中有
		//完成带分页的一级分类查询
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return  "findByCid";
	}
	//二级分类查询
	public String findByCsid(){
	PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return  "findByCsid";
	}


}
