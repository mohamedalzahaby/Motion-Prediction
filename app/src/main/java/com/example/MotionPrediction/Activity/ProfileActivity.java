package com.example.MotionPrediction.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.CircularGauge;
import com.anychart.charts.Polar;
import com.anychart.core.axes.Circular;
import com.anychart.core.cartesian.series.RangeColumn;
import com.anychart.core.gauge.pointers.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.PolarSeriesType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.ScaleTypes;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.anychart.graphics.vector.text.VAlign;
import com.anychart.scales.Linear;
import com.example.MotionPrediction.Adaptors.SessionAdaptor;
import com.example.MotionPrediction.Adaptors.TeamsDataAdaptor;
import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.Models.SessionDetails;
import com.example.MotionPrediction.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView_NoData;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView_NoData = findViewById(R.id.noDataToShow);
        player = new Player();
        if (!getIntent().hasExtra(player.getModelName()))
        {
            textView_NoData.setVisibility(View.VISIBLE);
            return;
        }
        player = (Player) getIntent().getSerializableExtra(player.getModelName());
        if (player.sessionList.isEmpty())
        {
            textView_NoData.setVisibility(View.VISIBLE);
            return;
        }

        RecyclerView recycler = findViewById(R.id.recyclerview_sessionData);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        SessionAdaptor teamsDataAdaptor= new SessionAdaptor(player.sessionList, this);
        recycler.setAdapter(teamsDataAdaptor);
        recycler.setItemAnimator(new DefaultItemAnimator());





    }





}