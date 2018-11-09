package com.example.dell.weather_forcest.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get_Retrofit {
    public static Retrofit getRetrofit(){

        OkHttpClient.Builder client=new OkHttpClient.Builder();

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.client(client.build()).build();
        return retrofit;
    }
}
