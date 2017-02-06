package cn.itcast.shop.user.action;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;


import cn.itcast.shop.utils.MailUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.apache.struts2.ServletActionContext.getResponse;

/**
 * Created by brian on 2017/1/12.
 */
public class UserAction extends ActionSupport implements ModelDriven {
    private UserService userService;
    // 模型驱动使用的对象
    private  User user = new User();
    //接收验证码
    private  String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    @Override
    public Object getModel() {
        return user;
    }
    // 注入UserService
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String registPage(){
        return  "registerPage";
    }
    //进行异步校验用户名方法findByName

    public String findByName() throws IOException {
        // 调用Service进行查询:
       // System.out.println(user.getName());
        User existUser = userService.findByUserName(user.getUsername());
       // User existUser = userService.findByUserName("aa");
        // 获得response对象,项页面输出:
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        // 判断
        if (existUser != null) {
            // 查询到该用户:用户名已经存在
            response.getWriter().println("<font color='red'>用户名已经存在</font>");
        } else {
            // 没查询到该用户:用户名可以使用
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }
        return NONE;
    }
    public String regist(){
        //判断验证码，先从session中获取随机值
        String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
        if(!checkcode1.equalsIgnoreCase(checkcode)){
            this.addActionError("验证码输入有误");
            return "checkcodeFail";
        }
        userService.save(user);
      this.addActionMessage("注册成功！请去邮箱激活");
        return  "msg";
    }
    //用户激活
    public String active(){
        User exsitUser = userService.findUserByCode(user.getCode());
        if(exsitUser==null){
            this.addActionMessage("激活码失效，激活失败");
        }else{
            this.addActionMessage("激活成功，请去登陆");
            exsitUser.setCode(null);
            exsitUser.setState(1);
            userService.update(exsitUser);
        }
        return  "msg";
    }
    //用户登陆界面
    public String loginPage(){
        return  "loginPage";
    }
    //用户登陆
    public String login(){
        User existUser=userService.login(user);
        if(existUser==null){
            //登陆失败
            this.addActionError("登陆失败，用户名账号或密码错误");
            return LOGIN;
        }else{
            //登陆成功，将用户信息保存到session中
            //System.out.println(existUser.getUsername());
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginSuccess";
        }
    }
    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();
        return  "quit";
    }


}
