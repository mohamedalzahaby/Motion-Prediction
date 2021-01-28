package com.example.MotionPrediction.ValidationDecorator;

import android.telephony.PhoneNumberUtils;

public class PhoneValidator extends ValidationDecorator
{
    String phoneNumber;
    public PhoneValidator(IValidate decorator, String phoneNumber) {
        this.decorator = decorator;
        this.phoneNumber = phoneNumber;
        message = decorator.getMessage();
    }
    @Override
    public void validate() {

        decorator.validate();
         boolean isGlobalNumber = PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
         boolean isvalidLengh = (phoneNumber.length() >= 11 && phoneNumber.length() <= 13);
         boolean validNumber = isGlobalNumber && isvalidLengh;
        if (!validNumber && decorator.isStatus())
        {
            message = "phone number not valid";
            status = false;
        }

    }
}
