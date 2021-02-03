package com.example.MotionPrediction.Models;

import com.example.MotionPrediction.Other.ClassFinder;
import com.google.gson.annotations.SerializedName;

public class User extends ModelHelper  {
    @SerializedName("isverified")
    public boolean isverified;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("name")
    public String name;
    @SerializedName("userTypeId")
    public int userTypeId;
    @SerializedName("modelName")
    private final String modelName = ClassFinder.getClassName(this);

    public String getModelName() {
        return modelName;
    }

    public User(String email, String password, String name, int userTypeId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.userTypeId = userTypeId;
    }

    public User() {}
}
