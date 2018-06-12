package com.example.hp.huanxin.common;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.utils.ToastUtils;

public class BaseActivity extends AppCompatActivity {
    protected Handler handler = new Handler();
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private static final String username_key="username";
    private static final String password_key="password";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         progressDialog = new ProgressDialog(this);
        sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
    }

    public void savaUser(User user)
    {
     sharedPreferences.edit().putString(username_key,user.getUsername()).
             putString(password_key,user.getPassword2()).commit();
    }
    public User getUser()
    {
        String username = sharedPreferences.getString(username_key,"");
        String pass = sharedPreferences.getString(password_key, "");
        User user = new User(username,pass);
        return user;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();

    }

    public void showDialog(String msg,boolean isCancelabe)
    {
        progressDialog.setCancelable(isCancelabe);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }
    public void hidDialog()
    {
        if(progressDialog.isShowing())
        {
            progressDialog.hide();
        }
    }
    public void startActivity(Class clazz, boolean isfinish)
    {
      startActivity(new Intent(this,clazz));
      if(isfinish)
      {
          finish();
      }
    }
    public void showToast(String msg)
    {
        ToastUtils.showToast(this,msg);
    }
}