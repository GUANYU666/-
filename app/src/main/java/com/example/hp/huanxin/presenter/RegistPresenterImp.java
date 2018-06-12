package com.example.hp.huanxin.presenter;

import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.utils.ThreadUtils;
import com.example.hp.huanxin.view.RegistView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegistPresenterImp implements RegistPresenter {
    private RegistView registView;
    public RegistPresenterImp(RegistView registView)
    {
        this.registView=registView;
    }


    @Override
    public void regist(final String username, final String password) {
        //显示进度条对话框
        registView.showProgressDialog("正在注册");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(final User user, BmobException e) {

                if(e==null)
                {
                    ThreadUtils.runOnsubThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMClient.getInstance().createAccount(username,password);
                                ThreadUtils.runOnuiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        registView.hideProgressDialog();
                                        registView.afterregist(user,true,null);
                                    }
                                });

                            } catch (final HyphenateException e1) {
                                e1.printStackTrace();
                                ThreadUtils.runOnuiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        registView.hideProgressDialog();
                                       registView.afterregist(user,false,e1.getMessage());
                                    }
                                });
                            }
                        }
                    });
                }
                else
                {
                    registView.hideProgressDialog();
                    registView.afterregist(user,false,e.getMessage());
                }
            }
        });
    }
}