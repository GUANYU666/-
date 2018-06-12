package com.example.hp.huanxin.view;

import com.example.hp.huanxin.model.User;

public interface RegistView  extends BaseView{

    void afterregist(User user,boolean success,String msg);
}
