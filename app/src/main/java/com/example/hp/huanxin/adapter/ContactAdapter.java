package com.example.hp.huanxin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.hp.huanxin.R;
import com.example.hp.huanxin.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements SectionIndexer{
    private SparseArray sparseArray = new SparseArray();
    private List<String>data;
    public List<String> getData()
    {
        return data;
    }
    public ContactAdapter(List<String> data)
    {
     this.data=data;
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        String username = data.get(position);
        String init = StringUtils.getInit(username);
        holder.tvusername.setText(username);
        holder.tvsection.setText(init);
        if(position==0)
        {
            holder.tvsection.setVisibility(View.VISIBLE);
        }
        else
        {
            String preusername = data.get(position - 1);
            String preinit = StringUtils.getInit(preusername);
            if(init.equals(preinit))
            {
             holder.tvsection.setVisibility(View.GONE);
            }
            else
            {
             holder.tvsection.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public String[] getSections() {
        ArrayList<String> sections = new ArrayList<>();

        for (int i = 0; i <data.size(); i++) {
            String init = StringUtils.getInit(data.get(i));
            if(sections.contains(init))
            {
                sections.add(init);
                sparseArray.put(sections.size()-1,i);
            }
        }
        return sections.toArray(new String[sections.size()]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return (int) sparseArray.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


    class ContactViewHolder extends RecyclerView.ViewHolder
    {

         TextView tvsection;
         ImageView ivavatar;
         TextView tvusername;

        public ContactViewHolder(View itemView) {
            super(itemView);
            tvsection = ((TextView) itemView.findViewById(R.id.tv_section));
            ivavatar = ((ImageView) itemView.findViewById(R.id.iv_avatar));
            tvusername = ((TextView) itemView.findViewById(R.id.tv_username));

        }
    }
}