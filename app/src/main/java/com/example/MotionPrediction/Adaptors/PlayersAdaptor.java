package com.example.MotionPrediction.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MotionPrediction.Activity.MainActivity;
import com.example.MotionPrediction.Activity.ProfileActivity;
import com.example.MotionPrediction.Controllers.SessionController;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.R;

import java.util.List;

public class PlayersAdaptor extends RecyclerView.Adapter<PlayersAdaptor.MyViewHolder>
{
    private View row;
    private List<Player> players ;
    private Context context;

    public PlayersAdaptor(List<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayersAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(R.layout.row_player,parent,false) ;
        return new PlayersAdaptor.MyViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayersAdaptor.MyViewHolder holder, final int position) {

        final PlayersAdaptor.MyViewHolder viewHolder = new PlayersAdaptor.MyViewHolder(row) ;

        holder.playerName.setText(players.get(position).name);
        //Glide.with(mContext).load(0).apply(option).into(holder.img_thumbnail);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionController sessionController = new SessionController();
                sessionController.context = context;
                sessionController.getPlayerSessions(players.get(position).id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView playerName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container =itemView.findViewById(R.id.container_player_row);
            playerName = itemView.findViewById(R.id.row_playerName);
        }
    }
}
