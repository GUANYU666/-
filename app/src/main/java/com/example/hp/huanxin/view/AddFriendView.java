package com.example.hp.huanxin.view;

import com.example.hp.huanxin.model.User;

import java.util.List;

public interface AddFriendView {

    void afterSerch(List<User> users, List<String> contacts, boolean isSuccess );

    void afterAddCoutact(boolean success,String msg,String username);
}

