package com.example.hp.huanxin.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.view.ContactsFragment;

public class ContactListview extends RelativeLayout {

    private RecyclerView re;

    private SwipeRefreshLayout swipeRefreshLayout;
    public ContactListview(Context context) {
        this(context,null);
    }

    public ContactListview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContactListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       LayoutInflater.from(context).inflate(R.layout.contact_list, this, true);
        re = (RecyclerView)findViewById(R.id.contact_re);
         swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sw);


    }

    public ContactListview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }
    public  void  setAdapter(RecyclerView.Adapter adapter){
        re.setLayoutManager(new LinearLayoutManager(getContext()));
        re.setAdapter(adapter);
    }

    public void onswipeRefreshLayout(SwipeRefreshLayout.OnRefreshListener onRefreshListener)
    {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }
    public void setRefreshing(boolean b) {
      if(swipeRefreshLayout!=null)
      {
      swipeRefreshLayout.setRefreshing(b);
      }
    }
}