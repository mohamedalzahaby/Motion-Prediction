package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Session extends ModelHelper implements Serializable
{
    @SerializedName("SessionDetailsList")
    public ArrayList<SessionDetails>  SessionDetailsList;

}
