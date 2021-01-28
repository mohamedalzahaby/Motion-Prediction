package com.example.MotionPrediction.Controllers;

import com.example.MotionPrediction.Models.TeamPerformanceDetails;

import java.util.ArrayList;

public class TeamPerformanceDetailsController extends Controller {
    public TeamPerformanceDetails teamPerformanceDetails;
    public java.util.ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList;

    public TeamPerformanceDetailsController() {
        this.teamPerformanceDetails = new TeamPerformanceDetails();
        teamPerformanceDetailsList = new ArrayList<>();
    }

    public String getMonth()
    {
        String month = teamPerformanceDetails.createdat.substring(5,7);
        return month;
    }
    public static int getMonthTotalCorrectMoves(int month, ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList)
    {
        int monthTotalCorrectMoves = 0;
        for (TeamPerformanceDetails row :
                teamPerformanceDetailsList) {
            if (month == Integer.parseInt(row.createdat.substring(5,7)))
                monthTotalCorrectMoves += row.totalCorrectMoves;
        }
        return monthTotalCorrectMoves;
    }

    public static int getMonthTotalWrongMoves(int month, ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList)
    {
        int monthTotalWrongMoves = 0;
        for (TeamPerformanceDetails row :
                teamPerformanceDetailsList) {
            if (month == Integer.parseInt(row.createdat.substring(5,7)))
                monthTotalWrongMoves += row.totalwrongMoves;
        }
        return monthTotalWrongMoves;
    }
    public int getMonthTotaltotalDuration(int month, ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList)
    {
        int monthTotalDuration = 0;
        for (TeamPerformanceDetails row :
                teamPerformanceDetailsList) {
            if (month == Integer.parseInt(row.createdat.substring(5,7)))
                monthTotalDuration += row.totalDuration;
        }
        return monthTotalDuration;
    }
}
