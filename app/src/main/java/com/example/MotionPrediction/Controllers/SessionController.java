package com.example.MotionPrediction.Controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Polar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.PolarSeriesType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.ScaleTypes;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.scales.Linear;
import com.example.MotionPrediction.Activity.ProfileActivity;
import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.Models.Session;
import com.example.MotionPrediction.Models.SessionDetails;
import com.example.MotionPrediction.Other.ClassFinder;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionController extends Controller {
    public Context context;
    public Session session;
    public SessionDetails sessionDetails;
    private String TAG = ClassFinder.getClassTAG(this);

    public SessionController() {
        session = new Session();
        sessionDetails = new SessionDetails();
    }

    public void getPlayerSessions(int playerId) {
        Call<Player> sessionCall = apiInterface.getPlayerSessionsData(playerId);
        sessionCall.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(@Nullable Call<Player> call, @Nullable Response<Player> response) {
//                Player player =  new Player();
                Player player =  response.body();
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra(player.getModelName(),player);
                context.startActivity(i);

            }

            @Override
            public void onFailure(@Nullable Call<Player> call, @Nullable Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(context, "bad internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getMoves(Session session, Hashtable<String, Integer> moves) {
        for (SessionDetails sessionDetails : session.SessionDetailsList)
        {
            int duration = sessionDetails.end - sessionDetails.start;
            String moveName = sessionDetails.move.name;
            if (!moves.containsKey(moveName))
                moves.put(moveName, duration);
            else
                moves.put(moveName, moves.get(moveName)+duration);
        }
    }

    public void setDataEntry(Hashtable<String, Integer> moves, List<DataEntry> data) {
        java.util.Set<String> setOfCountries = moves.keySet();
        // Collection Iterator
        Iterator<String> iterator = setOfCountries.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            data.add(new CustomDataEntry(key,  moves.get(key), 0));
        }
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }

    public Polar getPolar(List<DataEntry> data) {
        Polar polar = AnyChart.polar();
        Set set = Set.instantiate();
        set.data(data);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        polar.column(series1Data);
        polar.title("Movements Durations");
        polar.sortPointsByX(true)
                .defaultSeriesType(PolarSeriesType.COLUMN)
                .yAxis(false)
                .xScale(ScaleTypes.ORDINAL);
        polar.title().margin().bottom(20d);
        ((Linear) polar.yScale(Linear.class)).stackMode(ScaleStackMode.VALUE);
        polar.tooltip().displayMode(TooltipDisplayMode.UNION);
        return polar;
    }
}
