package com.example.MotionPrediction.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//import java.util.Calendar;
public class ModelHelper implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("createdat")
    public String createdat;
    @SerializedName("updatedat")
    public String updatedat;
    @SerializedName("isdeleted")
    public boolean isdeleted;

  /*  public ModelHelper() {
        this.createdat = String.valueOf(Calendar.getInstance().getTime());
        this.updatedat = String.valueOf(Calendar.getInstance().getTime());
    }*/




}
