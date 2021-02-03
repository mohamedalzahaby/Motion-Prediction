package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coach extends User implements Serializable
{
    @SerializedName("teamsList")
    public List<Team> teamsList;
    @SerializedName("playersList")
    public List<Player> playersList;

    public String getModelName() {
        return ClassFinder.getClassName(this);
    }


}
