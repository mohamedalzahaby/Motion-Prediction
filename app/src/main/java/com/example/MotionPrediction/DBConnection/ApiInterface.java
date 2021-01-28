package com.example.MotionPrediction.DBConnection;

import com.example.MotionPrediction.Models.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

//    @Headers("Content-Type: application/json")
    @Multipart
    @POST("sendString")
    Call<ResponseBody> sendString(@Part("query") RequestBody fullName );

//    @Headers("Content-Type: application/json")
    @POST("getUser")
    Call<User> getUser(@Query("id") int id);
    @POST("insert")
    Call<Integer> addUser(@Body User user);

    @POST("signUp")
    Call<Integer> signUp(@Body User user);

    @POST("signIn")
    Call<User> signIn(@Body User user);

    @POST("getTeamPerformanceDetails")
    Call<ResponseBody> getTeamPerformanceDetails();
}
