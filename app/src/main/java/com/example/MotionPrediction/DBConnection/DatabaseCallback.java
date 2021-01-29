package com.example.MotionPrediction.DBConnection;

import javax.security.auth.callback.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class DatabaseCallback<TeamPerformanceDetails> implements Callback {

    public void onResponse(Call<TeamPerformanceDetails> call, Response<TeamPerformanceDetails> response) {


    }


    public void onFailure(Call<TeamPerformanceDetails> call, Throwable t) {

    }
}
