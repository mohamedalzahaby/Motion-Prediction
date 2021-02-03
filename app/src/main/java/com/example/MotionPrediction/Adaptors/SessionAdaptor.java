package com.example.MotionPrediction.Adaptors;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Polar;
import com.example.MotionPrediction.Activity.ProfileActivity;
import com.example.MotionPrediction.Controllers.SessionController;
import com.example.MotionPrediction.Models.Session;
import com.example.MotionPrediction.R;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SessionAdaptor extends RecyclerView.Adapter<SessionAdaptor.MyViewHolder>
{

    private SessionController sessionController;
    private ArrayList<Session> sessions;
    private View row;
    private ProfileActivity context;
    public SessionAdaptor(ArrayList<Session> sessions, ProfileActivity context) {
        this.sessions = sessions;
        this.context = context;
        sessionController = new SessionController();
    }

    @NonNull
    @Override
    public SessionAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(R.layout.container_session_data_row,parent,false) ;
        return new SessionAdaptor.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final SessionAdaptor.MyViewHolder viewHolder = new SessionAdaptor.MyViewHolder(row) ;
        Session session = sessions.get(position);

        String text = "session" + (position + 1);
        viewHolder.textView_sessionName.setText(text);

        Hashtable<String, Integer> moves = new Hashtable<>();
        sessionController.getMoves(session, moves);
        List<DataEntry> data = new ArrayList<>();
        sessionController.setDataEntry(moves, data);
        Polar polar = sessionController.getPolar(data);
        holder.anyChartView.setChart(polar);

    }




    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout container;
        public View progressBar;
        public TextView textView_sessionName;
        public AnyChartView anyChartView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container =itemView.findViewById(R.id.container_session_data_row);
            textView_sessionName = itemView.findViewById(R.id.textView_sessionName);
            progressBar = itemView.findViewById(R.id.polar_progress_bar);
            anyChartView = itemView.findViewById(R.id.polarView);
            anyChartView = row.findViewById(R.id.polarView);
            progressBar.setBackgroundColor(Color.RED);
            anyChartView.setProgressBar(progressBar);

        }
    }
}
