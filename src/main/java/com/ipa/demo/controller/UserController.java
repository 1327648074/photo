package com.ipa.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;

@Controller
@RequestMapping("/front")
public class UserController {

    @Autowired
    UserService userService;

    //生成验证码
    public static String createVerCode() {
        String verCode = "";
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            int j = rnd.nextInt(10);
            String k = Integer.toString(j);
            verCode = verCode + k;
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
    public String findPwd() {
        return "findPwd";
    }

    //主界面
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    //注册成功界面
    @RequestMapping("/register_success")
    public String register_success() {
        return "register_success";
    }

    //重置成功界面
    @RequestMapping("/reset_success")
    public String reset_success() {
        return "reset_success";
    }

    //重置密码界面
    @RequestMapping("/reset")
    public String reset() {
        return "reset";
    }

    //修改密码界面
    @RequestMapping("/changePwd")
    public String changePwd() {
        return "changePwd";
    }

    @RequestMapping("/ifo")
    public String ifo() {
        return "ifo";
    }

    //修改密码
    @PostMapping("/doChangePwd")
    @ResponseBody
    public void doChangePwd(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String oldPwd = map.get("oldPwd");
        String newPwd1 = map.get("newPwd1");
        String newPwd2 = map.get("newPwd2");
        User user = userService.findByName(username);
        if (oldPwd.equals(user.getPassword())) {
            if (newPwd1.equals(newPwd2)) {
                user.setPassword(newPwd1);
                userService.save(user);
            }
        }
    }

//    //修改个人信息
//    @RequestMapping("/modInfo")
//    public String modIfo(){
//        return "modInfo";
//    }
//    @RequestMapping("/doModInfo")
//    public String doModInfo(HttpServletRequest request,HttpSession session){
//        String username = (String) session.getAttribute("username");
//        String nickname = request.getParameter("nickname");
//        String sex = request.getParameter("sex");
//        String autorGragh = request.getParameter("autorgragh");
//        String perMail = request.getParameter("permail");
//        User user = userService.findByName(username);
//        user.setNickname(nickname);
//        user.setSex(sex);
//        user.setAutorgragh(autorGragh);
//        user.setPermail(perMail);
//        userService.save(user);
//        return "login";
//    }
//
//
//
//    @GetMapping(value = "sad")
//    @ResponseBody
//    public  User find(@RequestParam(value = "name")String name){
//        return userService.findByName(name);
//    }
}
