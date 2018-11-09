package com.example.dell.weather_forcest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.adapter.MyAdapter;
import com.example.dell.weather_forcest.api.Api_connect;
import com.example.dell.weather_forcest.api.Get_Retrofit;
import com.example.dell.weather_forcest.model.City;
import com.example.dell.weather_forcest.model.root;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

   @BindView(R.id.weather_recycler)RecyclerView weather_recycler;
   Api_connect api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weather_recycler.setLayoutManager(new LinearLayoutManager(this));
        api=Get_Retrofit.getRetrofit().create(Api_connect.class);

        Call<root> information=api.getroot(16.871311,96.199379);
       information.enqueue(new Callback<root>() {
           @Override
           public void onResponse(Call<root> call, Response<root> response) {
               if(response.isSuccessful()){
                   Log.i("MainActivity",response.body().getList().size()+" ");
                   weather_recycler.setAdapter(new MyAdapter(MainActivity.this,response.body()));
               }
               else Log.e("no no","not success");
           }

           @Override
           public void onFailure(Call<root> call, Throwable t) {
               Log.e("no no",t.getMessage());
           }

       });

    }
}
