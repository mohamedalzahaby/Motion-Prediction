package com.example.MotionPrediction.DBConnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.MotionPrediction.R;

public class MainActivity extends AppCompatActivity {
    String TAG = "mytag_MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void send(View view) {





    }
    /*public void add(){
        Retrofit retrofit = RetrofitNetworkClient.getRetrofit();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Integer> call = apiInterface.addUser(user);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                String status;
                try {
                    int id = response.body();
                    Log.i(TAG, "onResponse: id "+ id);
                    status = (id != 0)?"Added Succussfully" : "Unsuccessfull Attempt";
                }
                catch (NullPointerException e){
                    status ="Unsuccessfull Attempt";
                }
                Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(MainActivity.this, "bad connection to server please try again", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

}