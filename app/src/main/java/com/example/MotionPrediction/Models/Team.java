package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team extends ModelHelper implements Serializable {

    @SerializedName("name")
    public String name;
    @SerializedName("club")
    public Club club;
    @SerializedName("teamPerformanceList")
    public List<TeamPerformance> teamPerformanceList;

}
