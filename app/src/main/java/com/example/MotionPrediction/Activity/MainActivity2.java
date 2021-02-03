package com.example.MotionPrediction.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.MotionPrediction.Controllers.UserController;
import com.example.MotionPrediction.Entities.UserTypeId;
import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.User;
import com.example.MotionPrediction.Other.ClassFinder;
import com.example.MotionPrediction.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    final String TAG = ClassFinder.getClassTAG(this);
    UserController userController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        userController = new UserController();
        String email = "mohamedazahaby@gmail.com";
        String password = "Mohamedazahaby123";

        signin(email, password);
    }

    public void signin(View view) {
        String email = "mohamedazahaby@gmail.com";
        String password = "Mohamedazahaby123";

        signin(email, password);

    }



    private void signin(String email, String password) {

        email = userController.filterSpaces(email);
        password = userController.filterSpaces(password);
        password = userController.hash(password);
        if (!userController.validate(this, email, password)) return;

        userController.user.email = email;
        userController.user.password = password;

        userController.controllerSignin(this);


    }




}

