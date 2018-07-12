package com.example.apple.interninternational.Validations;

import android.util.Log;

import com.example.apple.interninternational.Beans.Register;

import java.util.regex.Pattern;

/**
 * Contains all the validations for the register details
 */
public class RegisterValidation {

    /**
     * These static contants are used to tell which user input is invalid
     */
    public static final String FIRSTNAME_INVALID = "Invalid_FirstName";
    public static final String LASTNAME_INVALID = "Invalid_LastName";
    public static final String EMAIL_INVALID = "Invalid_Email";
    public static final String MOBILE_INVALID = "Invalid_Mobile";
    public static final String PASSWORD_INVALID = "Invalid_Password";
    public static final String MATCH_INVALID = "Password_Mismatch";
    public static final String ALL_VALID = "All_Valid";

    public static String validateAll(Register register) {
        boolean firstNameFlag = validateName(register.getFirstName());
        boolean lastNameFlag = validateName(register.getLastName());
        boolean emailFlag = validateEmail(register.getEmail());
        boolean mobFlag = validateMobile(register.getMobile());
        boolean passFlag = validatePassword(register.getPassword());
        boolean verPassFlag = validatePassword(register.getVerifyPassword());
        boolean confirmPassFlag = arePasswordsSame(register.getPassword(),register.getVerifyPassword());
        System.out.println("All Flags: \n"+firstNameFlag+lastNameFlag+emailFlag+mobFlag+passFlag);
        // Based on which input is invalid, send an error message
        if (!firstNameFlag) {
            Log.i("Validations", "validateAll: Firstname");
            return FIRSTNAME_INVALID;
        }
        if (!lastNameFlag) {
            Log.i("Validations", "validateAll: Lastname");
            return LASTNAME_INVALID;
        }
        if (!emailFlag) {
            Log.i("Validations", "validateAll: Email");
            return EMAIL_INVALID;
        }
        if (!mobFlag) {
            Log.i("Validations", "validateAll: Mobile");
            return MOBILE_INVALID;
        }
        if (!passFlag || !verPassFlag) {
            Log.i("Validations", "validateAll: Password");
            return PASSWORD_INVALID;
        }
        if (!confirmPassFlag) {
            Log.i("Validations", "validateAll: Match Password");
            return MATCH_INVALID;
        }
        return ALL_VALID;
    }

    private static boolean validateName (String name) {
        if (!name.toLowerCase().trim().matches("[a-z]+")) {
            return false;
        }
        return true;
    }

    private static boolean validateEmail (String email) {
        if (!email.toLowerCase().matches("[\\w\\.]+[@]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+")) {
            return false;
        }
        return true;
    }

    private static boolean validateMobile (String mobile) {
        System.out.println("Mobile Number: "+mobile);
        if (!mobile.matches("\\d{10}")) {
            return false;
        }
        return true;
    }

    private static boolean validatePassword (String password) {
        if (!(password.length() >= 8)) {
            return false;
        }
        return true;
    }

    private static boolean arePasswordsSame (String password, String verifyPassword) {
        return password.equals(verifyPassword);
    }
}
