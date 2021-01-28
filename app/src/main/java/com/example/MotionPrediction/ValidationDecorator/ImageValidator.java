package com.example.MotionPrediction.ValidationDecorator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageValidator extends ValidationDecorator
{
    IValidate decorator;
    String filepath;

    public ImageValidator(IValidate decorator, String filepath) {
        message = decorator.getMessage();
        this.decorator = decorator;
        this.filepath = filepath;
    }

    @Override
    public void validate() {

        decorator.validate();
        boolean previousIsValid = decorator.isStatus();
        boolean isImage = isImage(filepath);
        if (previousIsValid && !isImage) {
            message = "choose image file";
            status = false;
        }
    }

    private boolean isImage(String filepath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
        if (options.outWidth != -1 && options.outHeight != -1) {
            // This is an image file.
            return true;
        }
        return false;
    }

}
