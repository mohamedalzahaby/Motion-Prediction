package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SessionDetails extends ModelHelper implements Serializable {

    @SerializedName("move")
    public Move move;
    @SerializedName("start")
    public int start;
    @SerializedName("end")
    public int end;


}
