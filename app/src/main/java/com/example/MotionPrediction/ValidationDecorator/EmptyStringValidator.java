package com.example.MotionPrediction.ValidationDecorator;

public class EmptyStringValidator extends ValidationDecorator {
    String testingString;
    public EmptyStringValidator(IValidate decorator, String testingString) {
        this.decorator = decorator;
        message = decorator.getMessage();
        this.testingString = testingString;
    }
    @Override
    public void validate() {

        decorator.validate();
        boolean notValid = testingString.isEmpty() && decorator.isStatus();
        if (notValid)
        {
            message = "input is empty";
            status = false;
        }

    }
}
