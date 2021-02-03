package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TeamPerformance extends ModelHelper implements Serializable
{
    @SerializedName("TeamPerformanceDetailsList")
    public List<TeamPerformanceDetails> teamPerformanceDetailsList;
    @SerializedName("team")
    public int team;
    @SerializedName("totalCorrectMoves")
    public int totalCorrectMoves;
    @SerializedName("totalWrongMoves")
    public int totalWrongMoves;
    @SerializedName("totalDuration")
    public int totalDuration;
    @SerializedName("year")
    public int year;
    @SerializedName("month")
    public int month;
    @SerializedName("performancePercentage")
    public float performancePercentage;

    @SerializedName("modelName")
    public final String modelName = ClassFinder.getClassName(this);
}
