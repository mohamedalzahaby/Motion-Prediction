package com.example.MotionPrediction.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.MotionPrediction.Adaptors.PlayersAdaptor;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersFragment newInstance(String param1, String param2) {
        PlayersFragment fragment = new PlayersFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_photos, container, false);
        RecyclerView recycler = inflate.findViewById(R.id.recyclerview_players);
        List<Player> players = new ArrayList<>();
        players.add(new Player("mohamed alzahaby"));
        players.add(new Player("Bassel Emad"));
        players.add(new Player("Moataz Samir"));
        players.add(new Player("Hazem Alaa"));
        if (players.isEmpty()){
            TextView textView = inflate.findViewById(R.id.recyclerview_players_no_data_to_show);
            textView.setVisibility(View.VISIBLE);
            return inflate;
        }
        PlayersAdaptor playersAdaptor= new PlayersAdaptor(players, inflate.getContext());
        recycler.setLayoutManager(new LinearLayoutManager(inflate.getContext()));
        recycler.setAdapter(playersAdaptor);
        recycler.setItemAnimator(new DefaultItemAnimator());

        return inflate;
    }
}