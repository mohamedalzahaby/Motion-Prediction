package com.example.MotionPrediction.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.MotionPrediction.Controllers.TeamPerformanceDetailsController;
import com.example.MotionPrediction.Controllers.UserController;
import com.example.MotionPrediction.Entities.UserTypeId;
import com.example.MotionPrediction.Models.TeamPerformanceDetails;
import com.example.MotionPrediction.Models.User;
import com.example.MotionPrediction.Other.ClassFinder;
import com.example.MotionPrediction.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
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

        Call<User> call = userController.apiInterface.signIn(userController.user);

        call.enqueue(new Callback<User>() {
            private ArrayList<TeamPerformanceDetails> ArrayList;

            @Override
            public void onResponse(@Nullable Call<User> call, @Nullable Response<User> response)
            {
                if (response.body() == null) {
                    Toast.makeText(MainActivity2.this, "not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                userController.user = response.body();

                if (userController.user.userTypeId == UserTypeId.COACH.id)
                {
                    Intent activity = new Intent(MainActivity2.this, MainActivity.class);
                    activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(activity);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity2.this, "bad internet Connection", Toast.LENGTH_SHORT).show();
            }
        });



    }
}

