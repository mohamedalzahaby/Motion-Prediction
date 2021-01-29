package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coach extends User implements Serializable
{
    @SerializedName("teamsList")
    public Team teamsList;

}
