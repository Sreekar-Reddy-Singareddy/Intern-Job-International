package com.example.apple.interninternational.Validations;

import java.util.regex.Pattern;

/**
 * This class contains various methods that validate the user
 * inputs while logging in
 */
public class LoginValidation {

    /**
     * This method validates the username.
     * Username is email id in this case.
     * Email must contain pattern as [something]@[something].[something]
     */
    public static boolean validateEmailUsername(String username) {
        boolean result = Pattern.matches("[\\w\\.]+[@]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+",username.toLowerCase());
        return result;
    }
}
