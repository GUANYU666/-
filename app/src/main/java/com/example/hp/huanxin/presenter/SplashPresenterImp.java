package com.example.hp.huanxin.presenter;

import com.example.hp.huanxin.view.SplashView;
import com.hyphenate.chat.EMClient;

public class SplashPresenterImp implements SplashPresenter {

    private SplashView mSplashView;

    public SplashPresenterImp(SplashView mSplashView) {
        this.mSplashView = mSplashView;
    }

    @Override
    public void Loding() {

        if(EMClient.getInstance().isLoggedInBefore()&&EMClient.getInstance().isConnected())
        {
         mSplashView.isLogined(true);
        }
        else
        {
         mSplashView.isLogined(false);
        }
    }
}
