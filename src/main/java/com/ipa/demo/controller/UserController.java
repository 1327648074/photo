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

    //个人信息界面
    @RequestMapping("/info")
    public String info() {
        return "info";
    }

    //修改信息界面
    @RequestMapping("/changeInfo")
    public String changeInfo(){return "changeInfo";}

    //上传图片界面
    @RequestMapping("/upload")
    public String upload(){
        return "upload";
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

    //我的相册界面
    @RequestMapping("/myAlbum")
    public String myAlbum(){
        return "myAlbum";
    }

    //展示图片界面
    @RequestMapping("/showPhotos")
    public String showPhotos(){
        return "showPhotos";
    }

    //主页
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/album")
    public String album(){
        return "album";
    }
}
