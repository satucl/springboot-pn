package com.javatechie.valiadtion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PersonalNumberValidator implements ConstraintValidator<ValidPersonalNumber, String> {

    @Override
    public boolean isValid(String personalNumber, ConstraintValidatorContext constraintValidatorContext) {

        String cleanedPersonalNumber = personalNumber.replace("+", "");
        cleanedPersonalNumber = cleanedPersonalNumber.replace("-","");

        return lengthCorrect(cleanedPersonalNumber)
                && isNumeric(cleanedPersonalNumber)
                && checkLuhnValid(get10Digit(cleanedPersonalNumber));
    }

    private boolean lengthCorrect(String personalNumber){
        return personalNumber.length() == 10 || personalNumber.length() == 12;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String get10Digit(String personalNumber) {
        if (personalNumber.length() == 12) {
            return personalNumber.substring(2);
        }
        return personalNumber;
    }

    private boolean checkLuhnValid(String cardNo)
    {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits 
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

}
