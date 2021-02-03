package com.example.MotionPrediction.Adaptors;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Radar;
import com.anychart.core.axes.Linear;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Orientation;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.MotionPrediction.Controllers.TeamPerformanceController;
import com.example.MotionPrediction.Models.Team;
import com.example.MotionPrediction.Models.TeamPerformance;
import com.example.MotionPrediction.Other.IMonths;
import com.example.MotionPrediction.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TeamsDataAdaptor extends RecyclerView.Adapter<TeamsDataAdaptor.MyViewHolder>
{
    private View[] progressBar;
    private View row;
    private Context context;
    private List<Team> teamsList;
    private TeamPerformanceController teamPerformanceController;
    public TeamsDataAdaptor(List<Team> teamsList, Context context)
    {
        this.context = context;
        this.teamsList = teamsList;
        teamPerformanceController = new TeamPerformanceController();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(R.layout.container_team_data_row,parent,false) ;
        return new TeamsDataAdaptor.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TeamsDataAdaptor.MyViewHolder viewHolder = new TeamsDataAdaptor.MyViewHolder(row) ;
        Team team = teamsList.get(position);
        teamPerformanceController.teamPerformanceList = (ArrayList<TeamPerformance>) team.teamPerformanceList;
        holder.textView_teamName.setText(team.name);
        setProgressBar(row);
        setCharts(row, teamPerformanceController.teamPerformanceList);
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    private void setCharts(View view, ArrayList<TeamPerformance> teamPerformanceList) {
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(progressBar[0]);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        getPieChart(anyChartView, teamPerformanceList);

        AnyChartView radar_chart_view = view.findViewById(R.id.radar_chart_view);
        radar_chart_view.setProgressBar(progressBar[1]);
        APIlib.getInstance().setActiveAnyChartView(radar_chart_view);
        getRadarChart(radar_chart_view, teamPerformanceList);

        AnyChartView bar_chart = view.findViewById(R.id.bar_chart);
        bar_chart.setProgressBar(progressBar[2]);
        APIlib.getInstance().setActiveAnyChartView(bar_chart);
        getBarChartFromDataBase(bar_chart, teamPerformanceList);
    }

    private void getBarChartFromDataBase(AnyChartView anyChartView, ArrayList<TeamPerformance> teamPerformanceList) {
        Cartesian barChart = AnyChart.bar();
        setBarChartView(barChart);
        List<DataEntry> seriesData = new ArrayList<>();
        teamPerformanceController.teamPerformanceView.setSeriesOfBarCharData(teamPerformanceList, seriesData);
        insertDataToBarChart(barChart, seriesData);
        anyChartView.setChart(barChart);
    }

    private void insertDataToBarChart(Cartesian barChart, List<DataEntry> seriesData) {
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping coreectMovesSeries = set.mapAs("{ x: 'x', value: 'correctMoves' }");
        Mapping wrongMovesSeries   = set.mapAs("{ x: 'x', value: 'wrongMoves' }");

        Bar series2 = barChart.bar(coreectMovesSeries);
        series2.name("Correct");
        series2.tooltip()
                .position("left")
                .anchor(Anchor.LEFT_CENTER);

        Bar series1 = barChart.bar(wrongMovesSeries);
        series1.name("Wrong")
                .color("Red");
        series1.tooltip()
                .position("right")
                .anchor(Anchor.RIGHT_CENTER);


        barChart.legend().enabled(true);
        barChart.legend().inverted(true);
        barChart.legend().fontSize(13d);
        barChart.legend().padding(0d, 0d, 20d, 0d);
    }

    private void setBarChartView(Cartesian barChart) {
        barChart.animation(true);

        barChart.padding(10d, 20d, 5d, 20d);

        barChart.yScale().stackMode(ScaleStackMode.VALUE);

        barChart.yAxis(0).labels().format(
                "function() {\n" +
                        "    return Math.abs(this.value).toLocaleString();\n" +
                        "  }");

        barChart.yAxis(0d).title("Number Of Moves");

        barChart.xAxis(0d).overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        Linear xAxis1 = barChart.xAxis(1d);
        xAxis1.enabled(true);
        xAxis1.orientation(Orientation.RIGHT);
        xAxis1.overlapMode(LabelsOverlapMode.ALLOW_OVERLAP);

        barChart.title("Year Summary");

        barChart.interactivity().hoverMode(HoverMode.BY_X);

        barChart.tooltip()
                .title(false)
                .separator(false)
                .displayMode(TooltipDisplayMode.SEPARATED)
                .positionMode(TooltipPositionMode.POINT)
                .useHtml(true)
                .fontSize(12d)
                .offsetX(5d)
                .offsetY(0d)
                .format(
                        "function() {\n" +
                                "      return  Math.abs(this.value).toLocaleString() + '<span style=\"color: #D9D9D9\"> moves</span>';\n" +
                                "    }");
    }

    private void setProgressBar(View view) {
        progressBar = new View[3];
        progressBar[0] = view.findViewById(R.id.chart_progress_bar);
        progressBar[1] = view.findViewById(R.id.radar_progress_bar);
        progressBar[2] = view.findViewById(R.id.bar_progress_bar);
        for(View bar: progressBar)
            bar.setBackgroundColor(Color.RED);
    }

    private void getPieChart(AnyChartView anyChartView, ArrayList<TeamPerformance> teamPerformanceList)
    {
        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Team Performance");

        cartesian.yAxis(0).title("performance");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = getDataEntries(teamPerformanceList);

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");


        Line series3 = cartesian.line(series3Mapping);
        series3.name("Performance");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }

    private List<DataEntry> getDataEntries(ArrayList<TeamPerformance> teamPerformanceList) {
        List<DataEntry> seriesData = new ArrayList<>();

        for (TeamPerformance teamPerformance : teamPerformanceList)
        {
            String month = IMonths.months[teamPerformance.month-1];
            float performancePercentage = teamPerformance.performancePercentage;
            seriesData.add(new ValueDataEntry(month, performancePercentage));
        }
        return seriesData;
    }

    private void getRadarChart(AnyChartView anyChartView, ArrayList<TeamPerformance> teamPerformanceList) {
        Radar radar = AnyChart.radar();

        radar.title("Successful Moves");

        radar.yScale().minimum(0d);
        radar.yScale().minimumGap(0d);
        radar.yScale().ticks().interval(500d);

        radar.xAxis().labels().padding(1d, 1d, 1d, 1d);

        radar.legend()
                .align(Align.CENTER)
                .enabled(true);

        List<DataEntry> data = new ArrayList<>();
        ArrayList<String> moves = teamPerformanceController.teamPerformanceView.getMovesList(teamPerformanceList);

        Hashtable<String, Integer> correctMovesByYear =  teamPerformanceController.getCorrectMovesByYear(moves, teamPerformanceList);
        teamPerformanceController.teamPerformanceView.fillRadarData(data, correctMovesByYear);


        Set set = Set.instantiate();
        set.data(data);
        Mapping movesData = set.mapAs("{ x: 'x', value: 'value' }");

        com.anychart.core.radar.series.Line movesLine = radar.line(movesData);
        movesLine.name("Right Moves");
        movesLine.markers()
                .enabled(true)
                .type(MarkerType.CIRCLE)
                .size(7d);

        radar.tooltip().format("Value: {%Value}");

        anyChartView.setChart(radar);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;
        public TextView textView_teamName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container =itemView.findViewById(R.id.container_team_data_row);
            textView_teamName = itemView.findViewById(R.id.textView_teamName);

        }
    }
}
