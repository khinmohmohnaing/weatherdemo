package com.example.dell.weather_forcest.api;

import com.example.dell.weather_forcest.model.City;
import com.example.dell.weather_forcest.model.root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api_connect {

    @Headers("x-api-key: 51dbe8eca0f8d327405da57e6eed0eb9")
    @GET("data/2.5/forecast")
    Call<City> getWeatherinfo(@Query("lat") Double lat,
                              @Query("lon") Double lon);
    @Headers("x-api-key: 51dbe8eca0f8d327405da57e6eed0eb9")
    @GET("data/2.5/forecast")
    Call<root> getroot(@Query("lat") Double lat,
                              @Query("lon") Double lon);
}
