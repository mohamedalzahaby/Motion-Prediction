package com.example.MotionPrediction.Views;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.example.MotionPrediction.Models.TeamPerformance;
import com.example.MotionPrediction.Models.TeamPerformanceDetails;
import com.example.MotionPrediction.Other.IMonths;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class TeamPerformanceView {

    public void setSeriesOfBarCharData(ArrayList<TeamPerformance> teamPerformanceList, List<DataEntry> seriesData) {
        for (TeamPerformance teamPerformance : teamPerformanceList)
        {
            String month = IMonths.months[teamPerformance.month-1];

            int totalCorrectMovesOfTheMonth = teamPerformance.totalCorrectMoves;
            int totalWrongMovesOfTheMonth = teamPerformance.totalWrongMoves;
            seriesData.add(
                    new CustomDataEntry(month, totalCorrectMovesOfTheMonth, -totalWrongMovesOfTheMonth)
            );
        }
    }



    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number value2, Number value3)
        {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
        CustomDataEntry(String x, Number correctMoves, Number wrongMoves) {
            super(x, correctMoves);
            setValue("wrongMoves", wrongMoves);
        }
    }
    public void fillRadarData(List<DataEntry> data, Hashtable<String, Integer> correctMovesByYear) {
        // get keys() from Hashtable and iterate
        Enumeration<String> enumeration = correctMovesByYear.keys();
        // iterate using enumeration object
        while(enumeration.hasMoreElements()) {
            String k = enumeration.nextElement();
            Integer v = correctMovesByYear.get(k);
            data.add(new CustomDataEntry(k, v,v,v));
        }
    }


    public ArrayList<String> getMovesList(ArrayList<TeamPerformance> teamPerformanceList) {
        ArrayList<String> moves = new ArrayList<>();

        for (TeamPerformance teamPerformance : teamPerformanceList)
        {
            for (TeamPerformanceDetails teamPerformanceDetails : teamPerformance.teamPerformanceDetailsList)
            {
                if(!moves.contains(teamPerformanceDetails.move.name))
                    moves.add(teamPerformanceDetails.move.name);
            }
        }
        return moves;
    }
}
