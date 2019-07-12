//作者：吴勇
package com.ipa.demo.controller;

import com.ipa.demo.model.User;
import com.ipa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/front")
public class InfoController {
    @Autowired
    UserService userService;

    //获取信息
    @PostMapping("/getInfo")
    public User user(@RequestParam(name = "username") String username) {
        return userService.findByName(username);
    }

    //修改信息
    @PostMapping("/doChangeInfo")
    public String doChangeInfo(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        User user = userService.findByName(username);
        user.setNickname(map.get("nickname"));
        user.setSex(map.get("sex"));
        user.setPermail(map.get("email"));
        user.setAutorgragh(map.get("autorGraph"));
        userService.save(user);
        return "success";
    }
}
