package com.example.dell.weather_forcest.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.List;

public abstract class BaseVH<T> extends RecyclerView.ViewHolder {

    public BaseVH(View itemView) {
        super(itemView);
    }
    public abstract void bind(List itemlist,BaseVH.onclick onclicklistener,int position);


    public interface onclick{
        void onItemClick(int position);
        void onGoDetailClick(List list);
    }




}
