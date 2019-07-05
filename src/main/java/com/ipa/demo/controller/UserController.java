package com.ipa.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Random;
import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;

@Controller
@RequestMapping("/front")
public class UserController {

    @Autowired
    UserService userService;

    //判断是否已经注册,true代表已经注册，false代表未注册
    public  Boolean registerUser(String username) {
        if (userService.findByName(username)==null) {
            return false;
        } else {
            return true;
        }
    }

    //生成验证码
    public static String createVerCode(){
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
    public String login(HttpServletRequest request,HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.FindNameAndPsw(username, password);
        if (user != null&&user.getState()==1) {
            session.setAttribute("username",username);
            return "success";
        } else {
            return "login";
        }
    }



    //修改个人信息
    @RequestMapping("/modInfo")
    public String modIfo(){
        return "modInfo";
    }
    @RequestMapping("/doModInfo")
    public String doModInfo(HttpServletRequest request,HttpSession session){
        String username = (String) session.getAttribute("username");
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");
        String autorGragh = request.getParameter("autorgragh");
        String perMail = request.getParameter("permail");
        User user = userService.findByName(username);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setAutorgragh(autorGragh);
        user.setPermail(perMail);
        userService.save(user);
        return "login";
    }


    @GetMapping(value = "sad")
    @ResponseBody
    public  User find(@RequestParam(value = "name")String name){
        return userService.findByName(name);
    }
}
