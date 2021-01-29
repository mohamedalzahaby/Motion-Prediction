package com.example.MotionPrediction.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.MotionPrediction.Controllers.TeamPerformanceDetailsController;
import com.example.MotionPrediction.Models.TeamPerformance;
import com.example.MotionPrediction.Models.TeamPerformanceDetails;
import com.example.MotionPrediction.Other.IMonths;
import com.example.MotionPrediction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment
{
    public TeamPerformance teamPerformance;
    private View[] progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setProgressBar(view);

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(progressBar[0]);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        getPieChart(anyChartView);

        AnyChartView radar_chart_view = view.findViewById(R.id.radar_chart_view);
        radar_chart_view.setProgressBar(progressBar[1]);
        APIlib.getInstance().setActiveAnyChartView(radar_chart_view);
        getRadarChart(radar_chart_view);

        AnyChartView bar_chart = view.findViewById(R.id.bar_chart);
        bar_chart.setProgressBar(progressBar[2]);
        APIlib.getInstance().setActiveAnyChartView(bar_chart);
        List<TeamPerformanceDetails> hello = teamPerformance.teamPerformanceDetailsList;
//        getBarChart(bar_chart);
        getBarChartFromDataBase(bar_chart, (ArrayList<TeamPerformanceDetails>) teamPerformance.teamPerformanceDetailsList);


        return view;
    }

    private void getBarChartFromDataBase(AnyChartView anyChartView, ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList) {
        Cartesian barChart = AnyChart.bar();
        setBarChartView(barChart);
        List<DataEntry> seriesData = new ArrayList<>();
        setSeriesOfBarCharData(teamPerformanceDetailsList, seriesData);
        insertDataToBarChart(barChart, seriesData);
        anyChartView.setChart(barChart);
    }

    private void setSeriesOfBarCharData(ArrayList<TeamPerformanceDetails> teamPerformanceDetailsList, List<DataEntry> seriesData) {
        for (int i = 0; i < 12; i++) {
            String month = IMonths.months[i];
            int monthTotalWrongMoves = TeamPerformanceDetailsController.getMonthTotalWrongMoves(i + 1, teamPerformanceDetailsList);
            int monthTotalCorrectMoves = TeamPerformanceDetailsController.getMonthTotalCorrectMoves(i + 1, teamPerformanceDetailsList);
            seriesData.add(
                    new CustomDataEntry(month, monthTotalCorrectMoves, -monthTotalWrongMoves )
            );

        }
    }

    private void insertDataToBarChart(Cartesian barChart, List<DataEntry> seriesData) {
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping coreectMovesSeries = set.mapAs("{ x: 'x', value: 'correctMoves' }");
        Mapping wrongMovesSeries = set.mapAs("{ x: 'x', value: 'wrongMoves' }");

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
                                "      return  Math.abs(this.value).toLocaleString() + '<span style=\"color: #D9D9D9\"> oves</span>';\n" +
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

    private void getBarChart(AnyChartView anyChartView) {
        Cartesian barChart = AnyChart.bar();

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
                                "      return  Math.abs(this.value).toLocaleString() + '<span style=\"color: #D9D9D9\"> oves</span>';\n" +
                                "    }");

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("Jan", 5376, -229));
        seriesData.add(new CustomDataEntry("Feb", 10987, -932));
        seriesData.add(new CustomDataEntry("March", 7624, -5221));
        seriesData.add(new CustomDataEntry("April", 8814, -256));
        seriesData.add(new CustomDataEntry("May", 8998, -308));
        seriesData.add(new CustomDataEntry("June", 9321, -432));
        seriesData.add(new CustomDataEntry("July", 8342, -701));
        seriesData.add(new CustomDataEntry("Aug", 6998, -908));
        seriesData.add(new CustomDataEntry("Sep", 9261, -712));
        seriesData.add(new CustomDataEntry("Oct", 5376, -9229));
        seriesData.add(new CustomDataEntry("Nov", 10987, -13932));
        seriesData.add(new CustomDataEntry("Dec", 7624, -10221));


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping coreectMovesSeries = set.mapAs("{ x: 'x', value: 'correctMoves' }");
        Mapping wrongMovesSeries = set.mapAs("{ x: 'x', value: 'wrongMoves' }");

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

        anyChartView.setChart(barChart);
    }


    private void getPieChart(AnyChartView anyChartView) {
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

        List<DataEntry> seriesData = new ArrayList<>();

//        String month;
//        int performancePercentage;
//        seriesData.add(new ValueDataEntry(month, performancePercentage));
        seriesData.add(new ValueDataEntry("2000", 1.5));

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

    private void getRadarChart(AnyChartView anyChartView) {
        Radar radar = AnyChart.radar();

        radar.title("Successful Moves");

        radar.yScale().minimum(0d);
        radar.yScale().minimumGap(0d);
        radar.yScale().ticks().interval(50d);

        radar.xAxis().labels().padding(5d, 5d, 5d, 5d);

        radar.legend()
                .align(Align.CENTER)
                .enabled(true);

        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomDataEntry("PASS", 136, 199, 43));
        data.add(new CustomDataEntry("V", 79, 125, 56));
        data.add(new CustomDataEntry("DRIBBLING", 149, 173, 101));
        data.add(new CustomDataEntry("ZIGZAG", 135, 33, 202));
        data.add(new CustomDataEntry("SHOOT", 158, 64, 196));

        Set set = Set.instantiate();
        set.data(data);
        Mapping movesData = set.mapAs("{ x: 'x', value: 'value' }");

        com.anychart.core.radar.series.Line movesLine = radar.line(movesData);
        movesLine.name("Right Moves");
        movesLine.markers()
                .enabled(true)
                .type(MarkerType.CIRCLE)
                .size(3d);

        radar.tooltip().format("Value: {%Value}");

        anyChartView.setChart(radar);

    }



    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
        CustomDataEntry(String x, Number correctMoves, Number wrongMoves) {
            super(x, correctMoves);
            setValue("wrongMoves", wrongMoves);
        }
    }
}