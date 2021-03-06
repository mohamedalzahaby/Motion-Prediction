package com.example.MotionPrediction.DBConnection;

import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.Models.TeamPerformance;
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
    Call<TeamPerformance> getTeamPerformanceDetails();


    @POST("getCoachTeamsData")
    Call<Coach> getCoachTeamsData(@Query("coachId") int id);

    @POST("getCoachPlayers")
    Call<Coach> getCoachPlayers(@Query("coachId") int id);


    @POST("getPlayerSessionDetails")
    Call<Player> getPlayerSessionsData(@Query("playerId") int id);
}
