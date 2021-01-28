package com.example.MotionPrediction.ValidationDecorator;

public class ValidationDecorator implements IValidate {
    protected static String message;
    protected static boolean status;
    protected IValidate decorator;

    public boolean isStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public ValidationDecorator(){
        this.status = true;
        message = "";
    }
    @Override
    public void validate() {
        message = "";
        this.status = true;
    }
}
