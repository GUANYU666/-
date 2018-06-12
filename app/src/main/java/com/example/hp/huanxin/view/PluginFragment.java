package com.example.hp.huanxin.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.huanxin.MainActivity;
import com.example.hp.huanxin.R;
import com.example.hp.huanxin.common.BaseFragment;
import com.example.hp.huanxin.presenter.PluginPresenter;
import com.example.hp.huanxin.presenter.PluginPresenterImp;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PluginFragment extends BaseFragment  implements  PluginView{


    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.bt_logout)
    Button btLogout;
    Unbinder unbinder;

    private PluginPresenter mpluginPresenter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plugin, container, false);
        unbinder = ButterKnife.bind(this, view);
        mpluginPresenter = new PluginPresenterImp(this);
        String user = EMClient.getInstance().getCurrentUser();
        btLogout.setText("退("+user+")出");
        return view;
    }

    @Override
    protected void initTitle(TextView tv) {
        tv.setText("动态");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_logout)
    public void onViewClicked() {

        mpluginPresenter.logout();
    }

    @Override
    public void afterLogout(boolean success, String msg) {

             MainActivity activity = (MainActivity) getActivity();
             activity.startActivity(LoginActivity.class,true);
             if(!success)
             {
                 activity.showToast(msg);
             }
    }
}
