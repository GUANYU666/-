package com.example.hp.huanxin.presenter;

import com.example.hp.huanxin.adapter.EMCallbackAdapter;
import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.utils.ThreadUtils;
import com.example.hp.huanxin.view.LoginView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView mlogview;
    public  LoginPresenterImp(LoginView loginView)
    {
        this.mlogview=loginView;
    }
    @Override
    public void login(final String user, final String password) {

        //显示对话框
        mlogview.showProgressDialog("正在登陆");
        EMClient.getInstance().login(user, password, new EMCallbackAdapter() {
            @Override
            public void onSuccess() {
                hideDialog(new User(user,password),true,null);
            }

            @Override
            public void onError(int i, String s) {
           hideDialog(new User(user,password),false,s);
            }

        });
    }
    private void hideDialog(final User user, final boolean issuccess, final String msg)
    {
        ThreadUtils.runOnuiThread(new Runnable() {
            @Override
            public void run() {
                mlogview.afterLogin(user,issuccess,msg);
                mlogview.hideProgressDialog();
            }
        });
    }
}
