package com.ipa.demo.controller;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/front")
public class LoginController {

    @Autowired
    UserService userService;
    //执行登录
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(@RequestBody Map<String,String> map, HttpSession session) {
        String username = map.get("username");
        String password = map.get("pwd");
        User user = userService.FindNameAndPsw(username, password);
        if (user != null&&user.getState()==1) {
            session.setAttribute("username",username);
            return "success";
        } else {
            return "fail";
        }
    }

}
