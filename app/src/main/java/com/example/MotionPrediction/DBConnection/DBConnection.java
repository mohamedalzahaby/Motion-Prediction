package com.example.MotionPrediction.DBConnection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DBConnection implements Callback<ResponseBody> {
    private Call<ResponseBody> call;
    public Context context;
    private String TAG = "mytag_DBConnection";

    public DBConnection(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        ResponseBody x = response.body();
        Toast.makeText(context, "message Recieved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.i(TAG, "onFailure: "+t.getMessage());
        Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
    }


    public void uploadToServer()
    {
        Retrofit retrofit = RetrofitNetworkClient.getRetrofit();
        String fileAcceptedType = "text/plain";
        String fileNewName = "file";
        //convert file to different parts for taking image
        RequestBody requestBody = RequestBody.create(MediaType.parse(fileAcceptedType), "heellloo");
//        MultipartBody.Part part = MultipartBody.Part.createFormData(fileNewName, "this_is_a_string", requestBody);
        RequestBody fullName = RequestBody.create(MediaType.parse("multipart/form-data"), "SELECT * FROM `me`;");
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        call = apiInterface.sendString(fullName);
        call.enqueue(this);
    }
}
