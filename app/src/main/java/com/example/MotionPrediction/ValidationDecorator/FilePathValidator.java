package com.example.MotionPrediction.ValidationDecorator;

import android.net.Uri;


public class FilePathValidator extends ValidationDecorator
{
    private IValidate decorator;
    private Uri filePath;

    public FilePathValidator(Uri filePath , IValidate decorator) {
        message = decorator.getMessage();
        this.filePath = filePath;
        this.decorator = decorator;
    }

    public FilePathValidator(IValidate decorator, Uri filePath) {
        message = decorator.getMessage();
        this.filePath = filePath;
        this.decorator = decorator;
    }

    @Override
    public void validate() {

        decorator.validate();
        boolean isValid = decorator.isStatus();
        if (filePath == null && isValid)
        {
            message = "choose a file first";
            status = false;
        }
    }
}
