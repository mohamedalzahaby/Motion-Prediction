package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Move extends ModelHelper implements Serializable
{
    @SerializedName("name")
    public String name;

}
