package com.example.hp.huanxin.presenter;

import com.example.hp.huanxin.adapter.EMCallbackAdapter;
import com.example.hp.huanxin.utils.ThreadUtils;
import com.example.hp.huanxin.view.PluginView;
import com.hyphenate.chat.EMClient;

public class PluginPresenterImp implements PluginPresenter {
    private PluginView mPluginView;
    public PluginPresenterImp(PluginView pluginView)
    {
        this.mPluginView=pluginView;
    }
    @Override
    public void logout() {

        EMClient.getInstance().logout(true,new EMCallbackAdapter(){

            @Override
            public void onSuccess() {
                super.onSuccess();
                gotoLogin(true,null);
            }

            @Override
            public void onError(int i, String s) {
                super.onError(i, s);
                gotoLogin(false,s);
            }
        });

    }

    private void gotoLogin(final boolean success, final String msg) {
        ThreadUtils.runOnuiThread(new Runnable() {
            @Override
            public void run() {
             mPluginView.afterLogout(success,msg);
            }
        });
    }
}
