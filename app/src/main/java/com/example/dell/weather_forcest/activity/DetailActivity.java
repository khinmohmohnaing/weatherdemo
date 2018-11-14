package com.example.dell.weather_forcest.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.List;
import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.base.BaseVH;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity{
    @BindView(R.id.detailimg)ImageView detailimg;
    @BindView(R.id.detailweathers)TextView detailweather;
    @BindView(R.id.detailclouds)TextView detailcloud;
    @BindView(R.id.detailwinds)TextView detailwind;
    @BindView(R.id.detaildates)TextView detaildate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        detailweather.setText(intent.getStringExtra("weather"));
        detailcloud.setText(intent.getStringExtra("clouds"));
        detailwind.setText(intent.getStringExtra("wind"));
        detaildate.setText(intent.getStringExtra("date"));
        Glide.with(this).load(intent.getStringExtra("image")).into(detailimg);
    }




}
