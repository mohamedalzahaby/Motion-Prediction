package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

public class TeamPerformanceDetails extends ModelHelper
{
    @SerializedName("moveId")
    public int moveId;
    @SerializedName("totalCorrectMoves")
    public int totalCorrectMoves;
    @SerializedName("totalwrongMoves")
    public int totalwrongMoves;
    @SerializedName("totalDuration")
    public int totalDuration;
    @SerializedName("performancePercentage")
    public float performancePercentage;
    @SerializedName("teamPerformanceId")
    public int teamPerformanceId;
    @SerializedName("modelName")
    public final String modelName = ClassFinder.getClassName(this);




}
