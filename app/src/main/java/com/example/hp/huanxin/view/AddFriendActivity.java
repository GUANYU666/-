package com.example.hp.huanxin.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.adapter.SeachAdapter;
import com.example.hp.huanxin.common.BaseActivity;
import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.presenter.AddFriendPresenter;
import com.example.hp.huanxin.presenter.AddFriendPresenterImp;
import com.example.hp.huanxin.utils.ThreadUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFriendActivity extends BaseActivity implements AddFriendView,TextView.OnEditorActionListener {

    private InputMethodManager inputMethodManager;
    private AddFriendPresenter mAddFriendPresenter;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.seach)
    ImageView seach;
    @BindView(R.id.iv_nodata)
    ImageView ivNodata;
    @BindView(R.id.searchRe)
    RecyclerView searchRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        ButterKnife.bind(this);
        searchRe.setVisibility(View.GONE);
        tvTitle.setText("搜索");
        etUsername.setOnEditorActionListener(this);
        mAddFriendPresenter = new AddFriendPresenterImp(this);
         inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

    }

    @OnClick(R.id.seach)
    public void onViewClicked() {
        seacho();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId== EditorInfo.IME_ACTION_SEARCH)
        {
            seacho();
            return true;
        }
        return false;

    }

    private void seacho() {

        String name = etUsername.getText().toString().trim();
        if(TextUtils.isEmpty(name))
        {
            showToast("你让我搜鬼呀");
            return;
        }
        mAddFriendPresenter.seachFriend(name);

        if(inputMethodManager.isActive())
        {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void afterSerch(List<User> users, List<String> contacts, boolean isSuccess) {

        if(isSuccess)
        {
            ivNodata.setVisibility(View.GONE);
            searchRe.setVisibility(View.VISIBLE);
            searchRe.setLayoutManager(new LinearLayoutManager(this));
            SeachAdapter seachAdapter = new SeachAdapter(users,contacts);
            seachAdapter.setOnAddFriendClickListener(new SeachAdapter.OnAddFriendClickListener() {
                @Override
                public void onClick(String username) {
                    mAddFriendPresenter.addContact(username);

                }
            });
            searchRe.setAdapter(seachAdapter);
            showToast("搜索完毕");
        }
        else
        {
            ivNodata.setVisibility(View.VISIBLE);
            searchRe.setVisibility(View.GONE);
            showToast("没有搜到到结果");
        }
    }

    @Override
    public void afterAddCoutact(boolean success, String msg, String username) {

        if(success)
        {
            showToast("添加"+username+"请求发送成功");
        }
        else
        {
            showToast("添加"+username+"请求发送失败"+msg);
        }
    }
}
