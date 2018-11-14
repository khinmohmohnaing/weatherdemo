package com.example.dell.weather_forcest.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.List;
import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.activity.DetailActivity;
import com.example.dell.weather_forcest.base.BaseVH;

import butterknife.BindView;
import butterknife.ButterKnife;

public  class weatherViewHolder extends BaseVH{
    @BindView(R.id.winds)
    TextView wind;
    @BindView(R.id.weathername)
    TextView weather;
    @BindView(R.id.cloudss)
    TextView clouds;
    @BindView(R.id.dates)
    TextView date;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.detailbtn)
    Button detailbtn;


    public weatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final List itemlist, final BaseVH.onclick onclick, final int position) {
        weather.setText(itemlist.getWeather().get(0).getDescription());
        clouds.setText(itemlist.getClouds().getAll().toString());
        wind.setText(itemlist.getWind().getSpeed().toString());
        date.setText(itemlist.getDtTxt().toString());
        Glide.with(itemView.getContext()).load("http://api.openweathermap.org/img/w/" +
                itemlist.getWeather().get(0).getIcon()).
                into(img);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onItemClick(position);

            }
        });
        detailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onGoDetailClick(itemlist);
            }
        });
    }
}
