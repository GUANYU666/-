package com.example.hp.huanxin.view;
import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.huanxin.MainActivity;
import com.example.hp.huanxin.R;
import com.example.hp.huanxin.common.BaseActivity;
import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.presenter.LoginPresenter;
import com.example.hp.huanxin.presenter.LoginPresenterImp;
import com.example.hp.huanxin.utils.StringUtils;

import java.security.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class LoginActivity extends BaseActivity implements LoginView, TextView.OnEditorActionListener {
    @BindView(R.id.et_username2)
    EditText etUsername;
    @BindView(R.id.et_password2)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_newuser)
    TextView tvNewuser;
    private static final int REQUEST_PERMISSION_WRITE_SDCARD=1;
    private LoginPresenter mloginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mloginPresenter = new LoginPresenterImp(this);
        etPassword.setOnEditorActionListener(this);
        //数据回显
         User user = getUser();
         etUsername.setText(user.getUsername());
         etPassword.setText(user.getPassword2());
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user=getUser();
        etUsername.setText(user.getUsername());
        etPassword.setText(user.getPassword2());
    }

    @OnClick({R.id.bt_login, R.id.tv_newuser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;

            case R.id.tv_newuser:
                startActivity(RegistActivity.class,false);
                break;
        }
    }
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_GO)
        {
            login();
            return true;
        }
        return false;
        }
        private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(!StringUtils.checkUsername(username))
        {
            showToast("用户名不合法,3到20位,首字母必须是大写");
            return;
        }
        else if(!StringUtils.checkPassword(password))
        {
            showToast("密码不合法");
            return;
        }
        //检查权限

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PermissionChecker.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_WRITE_SDCARD);
            //没有授权
        }
        else
        {
            mloginPresenter.login(username,password);
        }
            //请求用户给权限
                //获得权限


    }

    @Override
    public void afterLogin(User user, boolean isSuccess, String msg) {
   if(isSuccess)
   {
       savaUser(user);
       //跳转到主界面
       startActivity(MainActivity.class,true);
   }
    else
     {
    showToast(msg);
     }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION_WRITE_SDCARD)
        {
            if(permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)&&grantResults[0]==0)
            {
                login();
            }
            else
            {
                showToast("你把我拒绝了,不让你用了 ");
            }
        }
    }

    @Override
    public void showProgressDialog(String msg) {
        showDialog(msg,false);
    }

    @Override
    public void hideProgressDialog() {
     hidDialog();
    }
}