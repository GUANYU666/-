package com.example.hp.huanxin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.model.User;


import java.util.List;

public class SeachAdapter extends RecyclerView.Adapter<SeachAdapter.SeachViewHolder> {
    private List<User> userlist;
    private List<String> contactlist;

    public SeachAdapter(List<User> userlist, List<String> contactlist) {
        this.userlist = userlist;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public SeachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_seach, parent, false);
        SeachViewHolder holder = new SeachViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeachViewHolder holder, int position) {
        final User user = userlist.get(position);
            holder.tv_name.setText(user.getUsername());
            holder.tv_time.setText(user.getCreatedAt());
            if(contactlist.contains(user.getUsername()))
            {
                holder.bu_add.setText("已是好友");
                holder.bu_add.setEnabled(false);
            }
            else
            {
                holder.bu_add.setText("添加");
                holder.bu_add.setEnabled(true);
                holder.bu_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(onAddFriendClickListener!=null)
                        {
                            onAddFriendClickListener.onClick(user.getUsername());
                        }
                    }
                });
            }

    }

    public  interface  OnAddFriendClickListener
    {
        void onClick(String username);
    }

    private  OnAddFriendClickListener onAddFriendClickListener;
    public void setOnAddFriendClickListener(OnAddFriendClickListener onAddFriendClickListener)
    {
        this.onAddFriendClickListener=onAddFriendClickListener;
    }
    @Override
    public int getItemCount() {
        return userlist.size();
    }

    class SeachViewHolder extends RecyclerView.ViewHolder
    {
        Button bu_add;
        TextView tv_time;
        TextView tv_name;

        public SeachViewHolder(View itemView) {
            super(itemView);
            tv_name = ((TextView) itemView.findViewById(R.id.tv_username));
            tv_time = ((TextView) itemView.findViewById(R.id.tv_time));
            bu_add = ((Button) itemView.findViewById(R.id.bt_add));
        }
    }
}
