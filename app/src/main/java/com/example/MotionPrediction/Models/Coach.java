package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Coach extends User implements Serializable
{
    @SerializedName("teamsList")
    public List<Team> teamsList;

}
