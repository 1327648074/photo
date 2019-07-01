package com.ipa.demo.model;

import javax.persistence.Entity;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//用户id

    //用户邮箱
    private String username;

    //用户密码
    private String password;

    //用户验证码
    private String verCode;

    //用户激活状态：0表示未激活，1表示已激活
    private int state;

    private String nickname;
    private String sex;
    private String autorgragh;
    private String permail;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getVerCode() {
        return verCode;
    }
    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getNickname(){
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public String getAutorgragh(){
        return autorgragh;
    }
    public void setAutorgragh(String autorgragh){
        this.autorgragh = autorgragh;
    }
    public String getPermail(){
        return permail;
    }
    public void setPermail(String permail){
        this.permail = permail;
    }
}
