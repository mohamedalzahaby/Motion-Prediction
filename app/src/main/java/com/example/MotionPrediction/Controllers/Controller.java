package com.example.MotionPrediction.Controllers;

import com.example.MotionPrediction.DBConnection.ApiInterface;
import com.example.MotionPrediction.DBConnection.RetrofitNetworkClient;

import retrofit2.Retrofit;

public abstract class Controller {
    public final static Retrofit retrofit = RetrofitNetworkClient.getRetrofit();
    public final static ApiInterface apiInterface = retrofit.create(ApiInterface.class);
}
