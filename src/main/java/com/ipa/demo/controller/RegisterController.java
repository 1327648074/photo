package com.ipa.demo.controller;

import com.ipa.demo.model.User;
import com.ipa.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ipa.demo.service.UserService;

import java.security.GeneralSecurityException;
import java.util.Map;

import static com.ipa.demo.controller.UserController.createVerCode;

@Controller
@RequestMapping("/front")
public class RegisterController {
    @Autowired
    UserService userService;
    MailUtil mailUtil;

    //发送注册验证码
    @PostMapping(value = "sendRegCode")
    @ResponseBody
    public String sendVerCode(@RequestParam(name = "username")String userEmail){
        String username=userEmail;
        String verCode=createVerCode();
        if(userService.findByName(username)!=null){
            User user1=userService.findByName(username);
            if(user1.getState()==0){
                userService.delete(user1);
            }else{
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
            return verCode;
            //return "成功";
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "error";
    }

    //执行注册
    @PostMapping("/doRegister")
    @ResponseBody
    public String register(@RequestBody Map<String,String> map) {
        String username = map.get("username");
        String password = map.get("pwd1");
        String password2 = map.get("pwd2");
        String verCode=map.get("verCode");
        if (password.equals(password2)) {
            if(userService.findByName(username)==null){
                return "fail";
            }else{
                User user1=userService.findByName(username);
                if(user1.getState()==0){
                    if(user1.getVerCode().equals(verCode)){
                        user1.setState(1);
                        user1.setPassword(password);
                        userService.save(user1);
                        return "success";
                    }
                }
            }
        }
        return "fail";
    }
}
