package com.example.MotionPrediction.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.MotionPrediction.R;

public class WelcomePage extends AppCompatActivity {

    private Button mBtnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        initView();
    }

    private void initView() {
        mBtnGetStarted = findViewById(R.id.btn_getStarted);

    }


    public void getStarted(View view) {
        Intent activity = new Intent(this, SignInActivity.class);
        activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(activity);
    }
}