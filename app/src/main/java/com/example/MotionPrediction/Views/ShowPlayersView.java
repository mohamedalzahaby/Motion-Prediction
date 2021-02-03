package com.example.MotionPrediction.Views;

import androidx.fragment.app.FragmentTransaction;

import com.example.MotionPrediction.Fragment.PlayersFragment;
import com.example.MotionPrediction.Interfaces.IShowDataViewBehaviour;
import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.ModelHelper;
import com.example.MotionPrediction.Models.Player;
import com.example.MotionPrediction.R;

import java.util.ArrayList;

public class ShowPlayersView implements IShowDataViewBehaviour {
    public FragmentTransaction fragmentTransaction;
    public String CURRENT_TAG;
    @Override
    public void showData(ModelHelper model) {
        Coach coach = (Coach) model;
        setFragment(coach);

    }

    private void setFragment(Coach coach) {
        PlayersFragment fragment = new PlayersFragment();
        fragment.playersList = (ArrayList<Player>) coach.playersList;
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
