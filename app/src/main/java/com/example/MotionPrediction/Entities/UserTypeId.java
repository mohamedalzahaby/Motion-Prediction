package com.example.MotionPrediction.Entities;

public enum UserTypeId {

    COACH(1), PLAYER(2), ClubManager(3), Admin(4);

    public int id;

    UserTypeId(int id) {
        this.id = id;
    }
}
