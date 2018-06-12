package com.example.hp.huanxin.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.common.BaseActivity;
import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.presenter.RegistPresenter;
import com.example.hp.huanxin.presenter.RegistPresenterImp;
import com.example.hp.huanxin.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements RegistView, TextView.OnEditorActionListener {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_regist)
    Button btRegist;

    private RegistPresenter mregistPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        etPassword.setOnEditorActionListener(this);
        mregistPresenter = new RegistPresenterImp(this);

    }

    @OnClick(R.id.bt_regist)
    public void onViewClicked() {
        regist();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId== EditorInfo.IME_ACTION_GO)
        {
            regist();
            return true;
        }
        return false;
    }
    private void regist() {
        String pass = etPassword.getText().toString().trim();
        String name = etUsername.getText().toString().trim();
         if(!StringUtils.checkUsername(name))
         {
             showToast("用户名不合法");

             return;
         }
         else if(!StringUtils.checkPassword(pass))
         {
             showToast("密码不合法");
             return;
         }
         mregistPresenter.regist(name,pass);

    }
    @Override
    public void showProgressDialog(String msg) {

     showDialog(msg,true);
    }

    @Override
    public void hideProgressDialog() {
    hidDialog();
    }

    @Override
    public void afterregist(User user, boolean success,String msg) {
    if(success)
    {
        savaUser(user);
        //跳转到登陆界面
        startActivity(LoginActivity.class,true);
    }
    else
    {
      showToast("注册失败"+msg);
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}