package com.example.hp.huanxin.adapter;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

public  abstract class TabSelectedListenerAdapter  implements BottomNavigationBar.OnTabSelectedListener {
    @Override
    public abstract  void onTabSelected(int position);

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}