package com.example.dell.weather_forcest.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.weather_forcest.R;
import com.example.dell.weather_forcest.adapter.MyAdapter;
import com.example.dell.weather_forcest.api.Get_Retrofit;
import com.example.dell.weather_forcest.api.mWeatherApiInterface;
import com.example.dell.weather_forcest.model.root;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherActivity extends AppCompatActivity {
    @BindView(R.id.cityedt)EditText cityedt;
    @BindView(R.id.okbtn)Button okbtn;
    @BindView(R.id.cityWeatherInfoRecycler)RecyclerView cityWeatherInfoRecycler;
    @BindView(R.id.linear)LinearLayout linearLayout;
    @BindView(R.id.progressbar)ProgressBar progressBar;
    @BindView(R.id.notfoundtxt)TextView notfoundtxt;
    mWeatherApiInterface api;
    MyAdapter myAdapter;
    String entertxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityweather);
        ButterKnife.bind(this);
        cityWeatherInfoRecycler.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapter(this);
        progressBar.setVisibility(View.GONE);

        api= Get_Retrofit.getRetrofit().create(mWeatherApiInterface.class);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                entertxt=cityedt.getText().toString();
                Log.i("CityWeatherActivuty","Enter name : "+entertxt);

                if(MainActivity.checkconnection(getApplicationContext())){
                    callCityWeatherInfo();
                }
                else{
                   callInfoFail();
                }


            }
        });

    }

    private void callInfoFail() {
        Snackbar snackbar=Snackbar.make(linearLayout,"Check your connectin",Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(MainActivity.checkconnection(v.getContext())){
                            callCityWeatherInfo();
                        }
                        else{
                            callInfoFail();
                        }
                    }
                });
        snackbar.show();
    }

    public void callCityWeatherInfo(){
        progressBar.setVisibility(View.GONE);
        Call<root> weatherinfo = api.getcityweatherinfo(entertxt);
        weatherinfo.enqueue(new Callback<root>() {
            @Override
            public void onResponse(Call<root> call, Response<root> response) {
                if (response.isSuccessful()) {
                    cityWeatherInfoRecycler.setVisibility(View.VISIBLE);
                    notfoundtxt.setVisibility(View.GONE);
                    Log.i("CityWeatherActivuty", response.body().getList().size() + " "+
                            response.body().getMessage());
                    myAdapter.addList(response.body());
                    cityWeatherInfoRecycler.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();


                } else {
                    Log.e("CityWeatherActivuty", "not success");
                    cityWeatherInfoRecycler.setVisibility(View.GONE);
                    notfoundtxt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<root> call, Throwable t) {

                Log.e("CityWeatherActivuty", t.getMessage());
            }

        });
    }
}
