package com.example.hp.huanxin;

import android.app.Application;
import android.content.Context;

import com.example.hp.huanxin.utils.DBUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import cn.bmob.v3.Bmob;

public class QQApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBUtils.init(this);
       inithuanxin();
        initBomb();
    }
    private void initBomb() {
        Bmob.initialize(this,"36d818439079765bb2efd7f443e6397e");
  }

    private void inithuanxin() {
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}