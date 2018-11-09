package com.example.dell.weather_forcest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weather_forcest.Activity.MainActivity;
import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.model.root;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHolder>{
    Context context;
    root rootinfo;

    public MyAdapter(Context context, root rootinfo) {
        this.context=context;
        this.rootinfo=rootinfo;
    }
    @NonNull
    @Override
    public MyAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapter.myHolder(LayoutInflater.from(context).inflate(R.layout.card_view,parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myHolder holder, int position) {
       holder.weather.setText(rootinfo.getList().get(position).getWeather().get(0).getDescription());
       holder.clouds.setText(rootinfo.getList().get(position).getClouds().getAll().toString());
       holder.wind.setText(rootinfo.getList().get(position).getWind().getSpeed().toString());
       holder.date.setText(rootinfo.getList().get(position).getDtTxt().toString());

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


        public myHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
