package com.example.hp.huanxin.model;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {


    public  User(){}
    public  User(String username,String password)
    {
    setUsername(username);
    setPassword(password);
    }
    private String password2;

    public void setPassword(String password)
    {
        super.setPassword(password);
        this.password2=password;
    }

    public String getPassword2()
    {
        return password2;
    }
}
