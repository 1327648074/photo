//作者：吴勇
package com.ipa.demo.controller;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;
import com.ipa.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Date;

@Controller
@RequestMapping("/front")
public class FindPwdController {
    @Autowired
    UserService userService;
    MailUtil mailUtil;

    //发送找回密码验证码
    @PostMapping("/sendFindCode")
    @ResponseBody
    public void sendFindCode(@RequestParam(name = "username") String email, HttpSession httpSession) {
        String username = email;
        User user1 = userService.findByName(username);
        String verCode = UserController.createVerCode();
        if (userService.findByName(username) != null && user1.getState() == 1) {
            try {
                mailUtil.send_mail(username, "你的验证码是" + verCode);
                Date sendTime = new Date();
                System.out.println("邮件发送成功!");
                user1.setVerCode(verCode);
                userService.save(user1);
                httpSession.setAttribute("mail", username);
                httpSession.setAttribute("sendFindTime", sendTime);
            } catch (javax.mail.MessagingException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
    }

    //验证验证码
    @PostMapping(value = "/verifyFindCode")
    @ResponseBody
    public String verifyFindCode(@RequestBody Map<String, String> map) {
        String verCode = map.get("verCode");
        String username = map.get("username");
        User user1 = userService.findByName(username);
        if (user1 != null && verCode.equals(user1.getVerCode()) && user1.getState() == 1) {
            return "success";
        }
        return "fail";
    }

    //重置密码
    @PostMapping("/resetPwd")
    @ResponseBody
    public String doFindPwd(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String username = httpSession.getAttribute("mail").toString();
        String pwd1 = map.get("pwd1");
        String pwd2 = map.get("pwd2");
        User user1 = userService.findByName(username);
        if (pwd1.equals(pwd2)) {
            user1.setPassword(pwd1);
            userService.save(user1);
            return "success";
        }
        return "fail";
    }
}
