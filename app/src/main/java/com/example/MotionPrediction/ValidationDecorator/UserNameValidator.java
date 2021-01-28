package com.example.MotionPrediction.ValidationDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator extends ValidationDecorator {
    IValidate decorator;
    String name;

    public UserNameValidator(IValidate decorator, String name) {
        this.decorator = decorator;
        message = decorator.getMessage();
        this.name = name;
    }

    @Override
    public void validate() {

        decorator.validate();
        boolean previousIsValid = decorator.isStatus();
        boolean wrongForm = isWrongForm(name);
        if (previousIsValid && wrongForm)
        {
            message = "name invalid";
            status = false;
        }

    }

    private boolean isWrongForm(String password)
    {
        final String NAME_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{0,2})";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
