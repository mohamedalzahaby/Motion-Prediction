package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TeamPerformance extends ModelHelper implements Serializable {

    @SerializedName("TeamPerformanceDetailsList")
    public List<TeamPerformanceDetails> teamPerformanceDetailsList;
    @SerializedName("modelName")
    public final String modelName = ClassFinder.getClassName(this);
}
