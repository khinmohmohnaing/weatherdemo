package com.example.dell.weather_forcest.activity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.List;
import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.adapter.MyAdapter;
import com.example.dell.weather_forcest.api.Get_Retrofit;
import com.example.dell.weather_forcest.api.mWeatherApiInterface;
import com.example.dell.weather_forcest.base.BaseVH;
import com.example.dell.weather_forcest.model.root;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements BaseVH.onclick{

    @BindView(R.id.weather_recycler)RecyclerView weather_recycler;
    @BindView(R.id.relativelayout)RelativeLayout relativelayout;
    @BindView(R.id.progress)ProgressBar progressBar;
    @BindView(R.id.cityweatherbtn)Button cityweatherbtn;
    Context context;

    mWeatherApiInterface api;
    MyAdapter adapter;
    public static boolean isConnected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weather_recycler.setLayoutManager(new LinearLayoutManager(this));
        api = Get_Retrofit.getRetrofit().create(mWeatherApiInterface.class);
        adapter = new MyAdapter(this);
        if(checkconnection(this)){
            callweatherInfo();
        }
        else {
            connectionfail();
        }

        cityweatherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CityWeatherActivity.class);
                startActivity(intent);
            }
        });

    }
    public static boolean checkconnection(Context context){

        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()){
            isConnected = true;
        }

        return isConnected;
    }
    public void callweatherInfo(){

        progressBar.setVisibility(View.GONE);
        Call<root> information = api.getweatherinfo(16.871311, 96.199379);
        information.enqueue(new Callback<root>() {
            @Override
            public void onResponse(Call<root> call, Response<root> response) {
                if (response.isSuccessful()) {
                    Log.i("MainActivity", response.body().getList().size() + " ");
                    adapter.addList(response.body());
                    weather_recycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setOnClick(MainActivity.this);
                } else Log.e("MainActivity", "not success");
            }

            @Override
            public void onFailure(Call<root> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
            }

        });
    }

    public void connectionfail(){
        Snackbar snackbar=Snackbar
                .make(relativelayout,"Check your connection",Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkconnection(v.getContext())){
                            callweatherInfo();
                        }
                        else{
                            connectionfail();
                        }
                    }
                });
        snackbar.show();
    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getApplicationContext(),"this is "+position +" click",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGoDetailClick(List list) {
        Intent intent=new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("weather",list.getWeather().get(0).getDescription());
        intent.putExtra("clouds",list.getClouds().getAll().toString());
        intent.putExtra("wind",list.getWind().getSpeed().toString());
        intent.putExtra("date",list.getDtTxt().toString());
        intent.putExtra("image","http://api.openweathermap.org/img/w/"+
                list.getWeather().get(0).getIcon());
        MainActivity.this.startActivity(intent);
    }

}

