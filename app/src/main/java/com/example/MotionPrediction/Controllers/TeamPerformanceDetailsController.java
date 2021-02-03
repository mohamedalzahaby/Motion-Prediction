package com.example.MotionPrediction.Controllers;

import android.util.Log;

import com.example.MotionPrediction.Models.TeamPerformanceDetails;

import java.util.ArrayList;

public class TeamPerformanceDetailsController extends Controller
{
    private static String TAG = "mytag_TPDController";
    public TeamPerformanceDetails teamPerformanceDetails;
    public java.util.ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList;

    public TeamPerformanceDetailsController() {
        this.teamPerformanceDetails = new TeamPerformanceDetails();
        teamPerformanceDetailsList = new ArrayList<>();
    }


}