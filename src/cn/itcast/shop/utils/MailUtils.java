package cn.itcast.shop.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by brian on 2017/1/13.
 */
public class MailUtils {
    //to是收件人，code是激活码
    public static void sendMail(String to ,String code){
        //获取链接对象
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.163.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ctyz200818@163.com","brian198918.1");
            }
        });
        //创建一个代表邮件的对象message
        Message message=  new MimeMessage(session);
        //发送邮件transport
        //设置发件人
        try {
            message.setFrom(new InternetAddress("ctyz200818@163.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //设置标题
            message.setSubject("test，来自163的注册邮件");
            message.setContent("<h1>官方激活邮件！点下面激活链接</h1><h3><a href='http://localhost:8080/user_active.action?code="+code+"'>http://localhost:8080/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static  void main(String args[]){
    sendMail("ctyz200818@126.com","xxx");
}
}
