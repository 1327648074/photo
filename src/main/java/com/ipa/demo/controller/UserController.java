package com.ipa.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.GeneralSecurityException;
import java.util.Random;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;
import com.ipa.demo.util.MailUtil;


@Controller
@RequestMapping("/front")
public class UserController {

    @Autowired
    UserService userService;
    MailUtil mailUtil;

    //判断是否已经注册,true代表已经注册，false代表未注册
    public Boolean registerUser(String username) {
        if (userService.findByName(username)==null) {
            return false;
        } else {
            return true;
        }
    }

    //生成验证码
    public String createVerCode(){
        String verCode="";
        Random rnd=new Random();
        for(int i=0;i<6;i++){
            int j=rnd.nextInt(10);
            String k=Integer.toString(j);
            verCode=verCode+k;
        }
        return verCode;
    }

    //初始界面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //注册界面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    //登陆界面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //找回密码界面
    @RequestMapping("/findPwd")
    public String findPwd(){ return "findPwd"; }

    //发送验证码界面
    @RequestMapping("/verCode")
    public String verCode(){
        return "verCode";
    }

    //注册成功界面
    @RequestMapping("/register_success")
    public String register_success(){return "register_success";}

    //重置成功界面
    @RequestMapping("/reset_success")
    public String reset_success(){return "reset_success";}

    //重置密码界面
    @RequestMapping("/reset")
    public String reset(){return "reset";}

    //成功登陆界面
    @RequestMapping("/success")
    public String success(){return "success";}

    //执行登录
    @RequestMapping("/doLogin")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.FindNameAndPsw(username, password);
        if (user != null&&user.getState()==1) {
            return "success";
        } else {
            return "login";
        }
    }

    //发送注册验证码
    @RequestMapping("/sendRegCode")
    public String sendVerCode(HttpServletRequest request){
        String username=request.getParameter("username");
        String verCode=createVerCode();
        if(registerUser(username)){
            User user1=userService.findByName(username);
            if(user1.getState()==0){
                userService.delete(user1);
            }else{
                return "login";
            }
        }else {}
        try {
            mailUtil.send_mail(username, "你的验证码是"+verCode);
            System.out.println("邮件发送成功!");
            User user1=new User();
            user1.setUsername(username);
            user1.setVerCode(verCode);
            user1.setState(0);
            userService.save(user1);
            return "verCode";
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "register";
    }

    //发送找回密码验证码
    @RequestMapping("/sendFindCode")
    public String sendFindCode(HttpServletRequest request) {
        String username=request.getParameter("username");
        User user1=userService.findByName(username);
        String verCode=createVerCode();
        if(registerUser(username)&&user1.getState()==1){
            try {
                mailUtil.send_mail(username, "你的验证码是"+verCode);
                System.out.println("邮件发送成功!");
                user1.setVerCode(verCode);
                userService.save(user1);
                return "verCode";
            } catch (javax.mail.MessagingException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
        return "findPwd";
    }

    //执行注册
    @RequestMapping("/doRegister")
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String verCode=request.getParameter("verCode");
        if (password.equals(password2)) {
            if(!registerUser(username)){
                return "register";
            }else{
                User user1=userService.findByName(username);
                if(user1.getState()==0){
                    if(user1.getVerCode().equals(verCode)){
                        user1.setState(1);
                        user1.setPassword(password);
                        userService.save(user1);
                        return "login";
                    }else{
                        return "register";
                    }
                }else {
                    return "login";
                }
            }
        }else{
            return "register";
        }
    }

    //执行找回密码
    @RequestMapping("/doFindPwd")
    public String doFindPwd(HttpServletRequest request){
        String username=request.getParameter("username");
        String pwd1=request.getParameter("pwd1");
        String pwd2=request.getParameter("pwd2");
        User user1=userService.findByName(username);
        if(pwd1.equals(pwd2)){
            if(registerUser(username)&&user1.getState()==1){
                return "reset_success";
            }
        }
        return "findPwd";
    }

    //验证验证码
    @RequestMapping("/verifyFindCode")
    public String verifyFindCode(HttpServletRequest request){
        String verCode=request.getParameter("verCode");
        String username=request.getParameter("usernema");
        User user1=userService.findByName(username);
        if(registerUser(username)&&verCode.equals(user1.getVerCode())){
            return "doFindPwd";
        }
        return "findPwd";
    }
}
