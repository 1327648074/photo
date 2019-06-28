package com.ipa.demo.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.GeneralSecurityException;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;
import com.ipa.demo.util.MailUtil;

@Controller
@RequestMapping("/front")
public class UserController {

    @Autowired
    UserService userService;
    MailUtil mailUtil;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 去注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/findPwd")
    public String findPwd(){ return "findPwd"; }
    /**
     * 执行注册 成功后登录页面 否则调回注册页面
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/doRegister")
    public String register(HttpServletRequest request, User user) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)) {
            if (registerUser(username) == true) {
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(password);
                userService.save(user1);
                try {
                    mailUtil.send_mail("1327648074@qq.com", String.valueOf(Math.random() * 999));
                    System.out.println("邮件发送成功!");
                } catch (javax.mail.MessagingException e) {
                    e.printStackTrace();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                return "login";
            } else {
                return "register";
            }
        } else {
            return "register";
        }
    }

    public Boolean registerUser(String username) {
        Boolean a = true;
        if (userService.findByName(username)==null) {
            return a;
        } else {
            return false;
        }
    }

    /**
     * 去登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 执行登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/doLogin")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.FindNameAndPsw(username, password);
        String str = "";
        if (user != null) {
            str = "success";
        } else {
            str = "login";
        }
        return str;
    }

    @RequestMapping("/doFindPwd")
    public String doFindPwd(HttpServletRequest request){
        String username=request.getParameter("username");
        String number=request.getParameter("number");
        if(number.equals("2")){
            User targetUser = userService.findByName(username);
            String pwd1=request.getParameter("pwd1");
            String pwd2=request.getParameter("pwd2");
            if(pwd1.equals(pwd2)){
                targetUser.setPassword(pwd1);
                userService.save(targetUser);
                return "login";
            }else {
                return "findPwd";
            }
        }
        return "register";
    }

}
