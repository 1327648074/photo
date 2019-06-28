package com.ipa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipa.demo.model.User;
import com.ipa.demo.dao.UserDao;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User FindNameAndPsw(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
    public void save(User user1) {
        userDao.save(user1);
    }
    public void delete(User user1){userDao.delete(user1);}
    public User findByName(String username) {
        return userDao.findByUsername(username);
    }

    public String verCode(){
        String verCode="";
        Random rnd=new Random();
        for(int i=0;i<6;i++){
            int j=rnd.nextInt(10);
            String k=Integer.toString(j);
            verCode=verCode+k;
        }

        return "你的验证码是："+verCode;
    }
}
