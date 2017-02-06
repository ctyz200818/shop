package cn.itcast.shop.adminuser.action;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * Created by brian on 2017/1/16.
 */
public class AdminUserAction extends ActionSupport implements ModelDriven{
    private  AdminUser adminUser = new AdminUser();
    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    //后台登陆
    public String login(){
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if(existAdminUser==null){
            this.addActionError("用户名或密码不对");
            return "loginFail";
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser);
            return "loginSuccess";
        }
    }

    @Override
    public Object getModel() {
        return adminUser;
    }
}
