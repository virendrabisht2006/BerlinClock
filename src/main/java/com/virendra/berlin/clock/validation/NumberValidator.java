package com.virendra.berlin.clock.validation;

/**
 * Created by Virendra Singh on 06/05/18.
 */
public class NumberValidator {

    public static boolean validate(String number) {
        String numRegex = "\\d+";
        return number.matches(numRegex);
    }

    public static boolean numberRangeCheck(String number) {
        int num = Integer.parseInt(number);
        return num >= 00 && num <= 59;
    }
}
