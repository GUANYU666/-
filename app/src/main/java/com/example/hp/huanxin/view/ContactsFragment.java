package com.example.hp.huanxin.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.adapter.ContactAdapter;
import com.example.hp.huanxin.common.BaseFragment;
import com.example.hp.huanxin.presenter.ContactsPresenter;
import com.example.hp.huanxin.presenter.ContactsPresenterImp;
import com.example.hp.huanxin.utils.ToastUtils;
import com.example.hp.huanxin.widget.ContactListview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends BaseFragment implements ContactsView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.contactListview)
    ContactListview contactListview;
    Unbinder unbinder;

    private ContactAdapter contactAdapter;
    private ContactsPresenter mcontactsPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, view);
        mcontactsPresenter = new ContactsPresenterImp(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactListview.onswipeRefreshLayout(this);
        mcontactsPresenter.initContacts();

    }

    @Override
    protected void initTitle(TextView tv) {
        tv.setText("联系人");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showContacts(List<String> contacts) {
         contactAdapter = new ContactAdapter(contacts);
        contactListview.setAdapter(contactAdapter);
    }

    @Override
    public void updataContact(boolean success) {
        contactListview.setRefreshing(false);
        if(success)
        {
            contactAdapter.notifyDataSetChanged();
        }
        else
        {

            ToastUtils.showToast(getContext(),"同步失败");
        }
    }

    @Override
    public void onRefresh() {

        mcontactsPresenter.updataContact();
    }
}
