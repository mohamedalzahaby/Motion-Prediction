package com.example.MotionPrediction.ValidationDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends ValidationDecorator {

    String password;
    String confirmPassword;
    public PasswordValidator(IValidate decorator, String password, String confirmPassword) {
        message = decorator.getMessage();
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.decorator = decorator;
    }
    @Override
    public void validate() {

        decorator.validate();
        boolean previousIsValid = decorator.isStatus();
        boolean passwordConfirmed = identical(password, confirmPassword);
        boolean correctForm = isCorrectForm(password);
        if (previousIsValid && (!passwordConfirmed || !correctForm))
        {
            if (!passwordConfirmed)
                message = "passwords are not identical";
            if (!correctForm)
                message = "must contain at least 1 symbol, [0-9], [A-Z]";
            status = false;
        }

    }

    private boolean isCorrectForm(String password) {
        //final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{3,}$";
        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[0-9]).{8,20})";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    private boolean identical(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
