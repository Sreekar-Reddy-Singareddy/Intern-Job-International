package com.example.apple.interninternational.Utilities;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class provides the dummy data for the expandable list of the opportunity details
 */
public class OppDetailsUtils {

    /**
     * This array list gives the group data
     */
    private static ArrayList<String> groupData;
    /**
     * This hash map gives the child data
     */
    private static HashMap<String,ArrayList<String>> childData;

    /**
     * This static block of code is executed and kept ready when the
     * class loads into the memory for the first time
     */
    static {
        createGroupData();
        createChildData();
    }

    private static void createChildData() {

    }

    private static void createGroupData() {
        groupData = new ArrayList<>();
        groupData.add("Job Details");
        groupData.add("Description");
        groupData.add("Requirements");
        groupData.add("Skills");
    }
}
