package com.example.MotionPrediction.ValidationDecorator;

public class EmailValidator extends ValidationDecorator
{
    IValidate decorator;
    String email;

    public EmailValidator(IValidate decorator, String email) {
        message = decorator.getMessage();
        this.decorator = decorator;
        this.email = email;
    }

    @Override
    public void validate() {

        decorator.validate();
        boolean previousIsValid = decorator.isStatus();
        boolean correctForm = isCorrectForm(email);
        if (previousIsValid && !correctForm){
            message = "invalid email address";
            status = false;
        }
    }

    private boolean isCorrectForm(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.trim().matches(emailPattern) && !email.trim().matches(emailPattern2)) return false;
        return true;
    }
}
