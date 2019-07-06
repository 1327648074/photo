package com.ipa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipa.demo.model.User;
import com.ipa.demo.dao.UserDao;


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

    public void delete(User user1) {
        userDao.delete(user1);
    }

    public User findByName(String username) {
        return userDao.findByUsername(username);
    }
}
