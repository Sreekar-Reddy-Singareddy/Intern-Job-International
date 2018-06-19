package com.example.apple.interninternational.Utilities;

import com.example.apple.interninternational.Beans.Profile;
import com.example.apple.interninternational.R;

import java.util.ArrayList;

public class ProfilesUtils {

    private static ArrayList<Profile> PROFILES_LIST;

    static {
        createProfiles();
    }

    private static void createProfiles() {
        PROFILES_LIST = new ArrayList<>();
        PROFILES_LIST.add(new Profile(R.drawable.china_one,"Keerthi Chand Reddy.S","Founder and Managing Director"));
        PROFILES_LIST.add(new Profile(R.drawable.china_two,"Sri Harsha Ramayanam","Founder and CEO"));
        PROFILES_LIST.add(new Profile(R.drawable.china_three,"Nitesh Vemulapalli","Chief Operating Officer"));
    }

    public static ArrayList<Profile> getProfileData() {
        return PROFILES_LIST;
    }
}
