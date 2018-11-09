package com.example.dell.weather_forcest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.activity.DetailActivity;
import com.example.dell.weather_forcest.model.root;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder>{
    private   Context context;
    private  root rootinfo;

    public   void addList(root rootinfos){
        this.rootinfo=rootinfos;
    }

    public MyAdapter(Context context) {
        this.context=context;

    }
    @NonNull
    @Override
    public MyAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapter.myHolder(LayoutInflater.from(context).inflate(R.layout.card_view,parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.myHolder holder, final int position) {
        holder.weather.setText(rootinfo.getList().get(position).getWeather().get(0).getDescription());
        holder.clouds.setText(rootinfo.getList().get(position).getClouds().getAll().toString());
        holder.wind.setText(rootinfo.getList().get(position).getWind().getSpeed().toString());
        holder.date.setText(rootinfo.getList().get(position).getDtTxt().toString());
        Glide.with(context).load("http://api.openweathermap.org/img/w/"+
                rootinfo.getList().get(position).getWeather().get(0).getIcon()).
                into(holder.img);
        holder.detailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("weather",holder.weather.getText());
                intent.putExtra("clouds",holder.clouds.getText().toString());
                intent.putExtra("wind",holder.wind.getText().toString());
                intent.putExtra("date",holder.date.getText().toString());
                intent.putExtra("image","http://api.openweathermap.org/img/w/"+
                        rootinfo.getList().get(position).getWeather().get(0).getIcon());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rootinfo.getList().size();
    }
    public class myHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.winds)TextView wind;
        @BindView(R.id.weathername)TextView weather;
        @BindView(R.id.cloudss)TextView clouds;
        @BindView(R.id.dates)TextView date;
        @BindView(R.id.img)ImageView img;
        @BindView(R.id.detailbtn)Button detailbtn;

        public myHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
