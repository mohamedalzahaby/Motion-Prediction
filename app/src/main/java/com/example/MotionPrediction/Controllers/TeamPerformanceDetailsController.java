package com.example.MotionPrediction.Controllers;

import android.util.Log;

import com.example.MotionPrediction.Models.TeamPerformanceDetails;

import java.util.ArrayList;

public class TeamPerformanceDetailsController extends Controller
{
    private static String TAG = "mytag_TPController";
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
            int moth_when_row_was_created = Integer.parseInt(row.createdat.substring(5, 7));
            if (month == moth_when_row_was_created)
                monthTotalCorrectMoves += row.totalCorrectMoves;
        }
        return monthTotalCorrectMoves;
    }

    public static int getMonthTotalWrongMoves(int month, ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList)
    {
        int monthTotalWrongMoves = 0;
        for (TeamPerformanceDetails teamPerformanceDetails : teamPerformanceDetailsList)
        {
            Log.i(TAG , teamPerformanceDetails.createdat);
            int moth_when_row_was_created = Integer.parseInt(teamPerformanceDetails.createdat.substring(5, 7));
            if (month == moth_when_row_was_created)
                monthTotalWrongMoves += teamPerformanceDetails.totalwrongMoves;
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
