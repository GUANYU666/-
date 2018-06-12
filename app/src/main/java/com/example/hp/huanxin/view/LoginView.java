package com.example.hp.huanxin.view;

import com.example.hp.huanxin.model.User;

public interface LoginView extends BaseView{
    void afterLogin(User user,boolean isSuccess,String msg);

}
