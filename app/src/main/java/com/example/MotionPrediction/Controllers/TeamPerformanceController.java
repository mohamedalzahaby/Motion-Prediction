package com.example.MotionPrediction.Controllers;

import com.example.MotionPrediction.Models.TeamPerformance;
import com.example.MotionPrediction.Models.TeamPerformanceDetails;
import com.example.MotionPrediction.Views.TeamPerformanceView;

import java.util.ArrayList;
import java.util.Hashtable;

public class TeamPerformanceController extends Controller
{
    private static String TAG = "mytag_TPController";
    public TeamPerformance teamPerformance;
    public TeamPerformanceView teamPerformanceView;
    public ArrayList<TeamPerformance> teamPerformanceList;

    public TeamPerformanceController() {
        teamPerformance = new TeamPerformance();
        teamPerformanceView = new TeamPerformanceView();
    }

    public Hashtable<String, Integer> getCorrectMovesByYear(ArrayList<String> moves, ArrayList<TeamPerformance> teamPerformanceList)
    {
        Hashtable<String, Integer> correctMovesTable = new Hashtable<>();
        for (String move: moves)
        {
            int totalCorrectMoves = getTotalCorrectMoves(teamPerformanceList, move);
            correctMovesTable.put(move, totalCorrectMoves);
        }
        return correctMovesTable;
    }
    private int getTotalCorrectMoves(ArrayList<TeamPerformance> teamPerformanceList, String move) {
        int totalPerformancePercentage = 0;
        for (TeamPerformance teamPerformance: teamPerformanceList)
        {
            for (TeamPerformanceDetails teamPerformanceDetails : teamPerformance.teamPerformanceDetailsList)
            {
                boolean sameMove = teamPerformanceDetails.move.name.equals(move);
                if (sameMove)
                    totalPerformancePercentage += teamPerformanceDetails.performancePercentage;

            }
        }
        return totalPerformancePercentage;
    }
}
