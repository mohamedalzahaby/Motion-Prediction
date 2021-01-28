package com.example.MotionPrediction.Controllers;

import android.content.Context;
import android.widget.Toast;

import com.example.MotionPrediction.Models.User;
import com.example.MotionPrediction.Other.Filter;
import com.example.MotionPrediction.ValidationDecorator.EmailValidator;
import com.example.MotionPrediction.ValidationDecorator.EmptyStringValidator;
import com.example.MotionPrediction.ValidationDecorator.IValidate;
import com.example.MotionPrediction.ValidationDecorator.InternetConnectionValidator;
import com.example.MotionPrediction.ValidationDecorator.ValidationDecorator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserController extends Controller implements IController
{
    public User user;

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
}
