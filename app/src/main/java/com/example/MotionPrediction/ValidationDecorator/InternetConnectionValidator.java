package com.example.MotionPrediction.ValidationDecorator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionValidator extends ValidationDecorator
{
    private IValidate decorator;
    private Context context;

    public InternetConnectionValidator(IValidate decorator, Context context) {
        this.decorator = decorator;
        this.context = context;
        message = decorator.getMessage();
    }

    /*public boolean isStatus() {
        return this.status;
    }*/


    @Override
    public void validate() {

        this.decorator.validate();
        if (!isConnectedToItnernet() && decorator.isStatus())
        {
            message = "no Internet Connection";
            this.status = false;
        }

    }

    private boolean isConnectedToItnernet()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        return false;
    }
}
