package com.example.MotionPrediction.Models;


import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeamPerformanceDetails extends ModelHelper implements Serializable
{
    @SerializedName("move")
    public Move move;
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
