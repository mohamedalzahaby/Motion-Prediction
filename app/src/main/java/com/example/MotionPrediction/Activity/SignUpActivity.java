package com.example.MotionPrediction.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.MotionPrediction.DBConnection.ApiInterface;
import com.example.MotionPrediction.DBConnection.RetrofitNetworkClient;
import com.example.MotionPrediction.Handler.PasswordHashingBuilder;
import com.example.MotionPrediction.Models.User;
import com.example.MotionPrediction.R;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private HashMap<String, Integer> userTypeChoice;
    private final String TAG = "mytag_SignUpActivity";
    private String name;
    private String email;
    private String password;
    private int userTypeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userTypeChoice = new HashMap<>();
        userTypeChoice.put("Coach",1);
        userTypeChoice.put("Player",2);
        initView();

    }

    private void initVariables() {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        String key = radioButton.getText().toString();
        Log.i(TAG, "initVariables: key = "+key);
        userTypeId = userTypeChoice.get(key);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Get Started");
    }

    private void initView() {
        mToolbar = findViewById(R.id.signUp_toolbar);
        initToolbar();
        et_name = findViewById(R.id.signUp_et_name_field);
        et_email = findViewById(R.id.signUp_et_email_field);
        et_password = findViewById(R.id.signUp_et_password_field);
        radioGroup = findViewById(R.id.signUp_userType);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void signup(View view) {
//        initVariables();
//        signup();
        geToHomePage();
    }

    private void signup() {
        PasswordHashingBuilder passwordHashingBuilder = new PasswordHashingBuilder();
        password = passwordHashingBuilder.hash(password.toCharArray());
        Retrofit retrofit = RetrofitNetworkClient.getRetrofit();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        User user = new User(email,password, name,userTypeId);
        Call<Integer> call = apiInterface.signUp(user);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                String status;
                try {
                    int id = response.body();
                    if (id == -1)
                        status = "email already registed";
                    else if(id == 0)
                        status = "Unsuccessfull Attempt";
                    else {
                        status = "Added Succussfully";
                        Toast.makeText(SignUpActivity.this, status, Toast.LENGTH_SHORT).show();
                        geToHomePage();
                    }
                }
                catch (NullPointerException e){
                    status ="Unsuccessfull Attempt";
                }
                Toast.makeText(SignUpActivity.this, status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "bad connection to server please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void geToHomePage()
    {
        Intent activity = new Intent(this, MainActivity.class);
        startActivity(activity);
    }
}