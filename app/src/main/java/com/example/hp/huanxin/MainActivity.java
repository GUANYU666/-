package com.example.hp.huanxin;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.hp.huanxin.adapter.TabSelectedListenerAdapter;
import com.example.hp.huanxin.common.BaseActivity;
import com.example.hp.huanxin.common.BaseFragment;
import com.example.hp.huanxin.utils.FragmentFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_content)
    FrameLayout fr;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottomNavigation;

    private TextBadgeItem badgeItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initBottomBar();
    }
    private void initBottomBar() {
         badgeItem = new TextBadgeItem();
         badgeItem.setText("5")
                .hide()
                .setGravity(Gravity.RIGHT)
                .setBackgroundColor(Color.RED)
                .setHideOnSelect(false)
                .setAnimationDuration(100)
                .show();
        bottomNavigation
                .setActiveColor("#00ACFF").setInActiveColor("#ABADBB")
                .addItem(new BottomNavigationItem(R.drawable.conversation_selected_2, "消息").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.contact_selected_2, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.plugin_selected_2, "动态"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigation.setTabSelectedListener(new TabSelectedListenerAdapter() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.hide(FragmentFactory.getFragment(0));
                fragmentTransaction.hide(FragmentFactory.getFragment(1));
                fragmentTransaction.hide(FragmentFactory.getFragment(2));
                BaseFragment fragment = FragmentFactory.getFragment(position);

                if(!fragment.isAdded())
                 {
                    fragmentTransaction.add(R.id.fl_content,fragment,position+"");
                }

                fragmentTransaction.show(fragment);
                fragmentTransaction.commit();
            }
        });

    }

    private void initFragment() {

        BaseFragment fragment = FragmentFactory.getFragment(0);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content,fragment,"0").commit();

    }
}