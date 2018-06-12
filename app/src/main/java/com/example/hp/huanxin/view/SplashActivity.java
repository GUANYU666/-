package com.example.hp.huanxin.view;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.huanxin.MainActivity;
import com.example.hp.huanxin.R;
import com.example.hp.huanxin.common.BaseActivity;
import com.example.hp.huanxin.presenter.SplashPresenter;
import com.example.hp.huanxin.presenter.SplashPresenterImp;
import com.example.hp.huanxin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
public class SplashActivity extends BaseActivity implements SplashView{
    private static final long DURATION = 2000;
    private SplashPresenter mSplashPresenter;
    @BindView(R.id.iv)
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mSplashPresenter = new SplashPresenterImp(this);

        mSplashPresenter.Loding();
    }
    @Override
    public void isLogined(boolean isLogined) {
        if(isLogined)
        {
            startActivity(MainActivity.class,true);
            //跳转主界面
        }
        else
        {
            //跳转登陆界面
            ObjectAnimator.ofFloat(iv,"alpha",0,1).setDuration(DURATION).start();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(LoginActivity.class,true);
                }
            },DURATION);
        }
    }
}