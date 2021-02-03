package com.example.MotionPrediction.DBConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitNetworkClient
{

    private static Retrofit retrofit;
    private static String BASE_URL = "http://192.168.1.11:5000/";


    public static Retrofit getRetrofit()
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        if (retrofit == null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
