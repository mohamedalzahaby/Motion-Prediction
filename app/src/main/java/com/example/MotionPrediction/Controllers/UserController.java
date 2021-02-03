package com.example.MotionPrediction.Controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.MotionPrediction.Activity.MainActivity;
import com.example.MotionPrediction.Activity.MainActivity2;
import com.example.MotionPrediction.Entities.UserTypeId;
import com.example.MotionPrediction.Interfaces.IShowDataViewBehaviour;
import com.example.MotionPrediction.Models.Coach;
import com.example.MotionPrediction.Models.User;
import com.example.MotionPrediction.Other.ClassFinder;
import com.example.MotionPrediction.Other.Filter;
import com.example.MotionPrediction.ValidationDecorator.EmailValidator;
import com.example.MotionPrediction.ValidationDecorator.EmptyStringValidator;
import com.example.MotionPrediction.ValidationDecorator.IValidate;
import com.example.MotionPrediction.ValidationDecorator.InternetConnectionValidator;
import com.example.MotionPrediction.ValidationDecorator.ValidationDecorator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController extends Controller implements IController
{
    public User user;
    public IShowDataViewBehaviour view;

    private String TAG = ClassFinder.getClassTAG(this);

    public UserController() {
        this.user = new User();
    }

    public boolean validate(Context context, String email, String password) {
        IValidate decorator = new ValidationDecorator();
        decorator = new InternetConnectionValidator(decorator, context);
        decorator = new EmptyStringValidator(decorator, email);
        decorator = new EmptyStringValidator(decorator, password);
        decorator = new EmailValidator(decorator, email);
        decorator.validate();
        boolean status =  decorator.isStatus();
        String message = decorator.getMessage();
        if (!status) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
        return status;
    }

    public String filterSpaces(String str)
    {
        return Filter.filterSpaces(str);
    }
    public String hash(String password)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public void controllerSignin(final Context context) {
        Call<User> call = apiInterface.signIn(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@Nullable Call<User> call, @Nullable Response<User> response)
            {
                if (response.body() == null) {
                    Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = response.body();
                setView(context);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(context, "bad internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setView(Context context) {
        if (user.userTypeId == UserTypeId.COACH.id)
        {
            getCoachData(context);
        }
    }

    private void getCoachData(final Context context) {
        Call<Coach> teamCall = apiInterface.getCoachTeamsData(user.id);
        teamCall.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                if (response.body() == null) {
                    Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                Coach coach = response.body();
                coach.id = user.id;
                Intent activity = new Intent(context, MainActivity.class);
                activity.putExtra(coach.getModelName(),coach);
                activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(activity);
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(context, "bad internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCoachPlayers(final Context context) {
        Call<Coach> playersCall = apiInterface.getCoachPlayers(user.id);
        playersCall.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                if (response.body() == null) {
                    Toast.makeText(context, "not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                Coach coach = response.body();
                view.showData(coach);
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(context, "bad internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
