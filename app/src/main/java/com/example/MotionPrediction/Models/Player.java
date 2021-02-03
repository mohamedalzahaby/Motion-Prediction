package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends User implements Serializable {


    @SerializedName("team")
    public Team team;
    @SerializedName("SessionList")
    public ArrayList<Session> sessionList;

    public String getModelName() {
        return ClassFinder.getClassName(this);
    }



    public Player(String name) {
        this.name = name;
    }

    public Player() { }
}
