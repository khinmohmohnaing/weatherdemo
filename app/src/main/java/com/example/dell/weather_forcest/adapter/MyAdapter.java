package com.example.dell.weather_forcest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.activity.DetailActivity;
import com.example.dell.weather_forcest.base.BaseVH;
import com.example.dell.weather_forcest.model.root;
import com.example.dell.weather_forcest.viewholder.weatherViewHolder;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<BaseVH>{
    private  Context context;
    private  root rootinfo;
    BaseVH.onclick onclicklistener;

    public   void addList(root rootinfos){
        this.rootinfo=rootinfos;
    }

    public MyAdapter(Context context) {
        this.context=context;

    }
    public  void setOnClick(BaseVH.onclick onclicklistener){
        this.onclicklistener=onclicklistener;
    }


    @NonNull
    @Override
    public BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new weatherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVH holder, int position) {
        holder.bind(rootinfo.getList().get(position),onclicklistener,position);
    }

    @Override
    public int getItemCount() {
        return rootinfo.getList().size();
    }

}
