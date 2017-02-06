package cn.itcast.shop.intercepter;

import cn.itcast.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by brian on 2017/1/18.
 */
public class PrivilegeIntercepter extends MethodFilterInterceptor {
    @Override
    //执行拦截方法，对没有登陆的用户不进入后台
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
       //判断session中是否保存了用户的信息
       AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
        if(existAdminUser==null){
            //获得正在进行的action
            ActionSupport actionSupport  = (ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("您 还未登陆，没有权限操作");
            return "loginFail";

        }else {
            return actionInvocation.invoke();
        }

    }
}
