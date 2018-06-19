package com.example.apple.interninternational.Utilities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import com.example.apple.interninternational.Activity.HomeScreen;

public class ProjectUtils {

    /**
     * This method takes a raw string and converts it into
     * well formatted phone number using the phone number utils class
     * @param phoneNumberString: raw string representing the phone number
     * @return: well formatted phone number in Indian format
     */
    public static String formatIndianNumber(String phoneNumberString) {
        String formattedPhoneNumber = PhoneNumberUtils.formatNumber(phoneNumberString,"IN");
        return formattedPhoneNumber;
    }

    /**
     * This method takes a well formatted number as a string and
     * makes a call using the number
     * @param phoneNumber: the string of well-formatted number based on country code
     */
    public static void makeACall(String phoneNumber){
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        // Check the call_icon permission
        System.out.println("Static Ref: "+HomeScreen.HOMESCREEN_REFERENCE);
        System.out.println("Permission Code: "+ HomeScreen.HOMESCREEN_REFERENCE.checkSelfPermission(Manifest.permission.CALL_PHONE));
        if (HomeScreen.HOMESCREEN_REFERENCE.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
            // Permission is not given
            System.out.println("Perimission Denied");
            HomeScreen.HOMESCREEN_REFERENCE.requestPermissions(new String [] {Manifest.permission.CALL_PHONE},1234);
        }
        else {
            // Permission is given
            System.out.println("Perimission Granted");
            System.out.println("URI Data: "+callIntent.getData());
            HomeScreen.HOMESCREEN_REFERENCE.startActivity(callIntent);
        }
    }
}
