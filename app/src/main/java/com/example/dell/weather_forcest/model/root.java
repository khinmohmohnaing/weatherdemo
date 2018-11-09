package com.example.dell.weather_forcest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class root {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public java.util.List<com.example.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
