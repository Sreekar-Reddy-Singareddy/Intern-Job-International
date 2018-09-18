package com.example.apple.interninternational.Utilities;

import com.example.apple.interninternational.Beans.Duration;
import com.example.apple.interninternational.Beans.Location;
import com.example.apple.interninternational.R;

import java.util.ArrayList;

public class NationalFragUtils {

    /**
     * The three arrays represent the data source of the
     * three user input types - branch, location, duration
     */
    private static ArrayList<Object> branches = new ArrayList<>();
    private static ArrayList<Object> locations = new ArrayList<>();
    private static ArrayList<Object> durations = new ArrayList<>();

    /**
     * This static block of code initialises the three arrays with the data needed
     */
    static {
        createBranches();
        createLocations();
        createDurations();
    }

    /**
     * Method to create branches list
     */
    private static void createBranches() {
    }

    /**
     * Method to create locations list
     */
    private static void createLocations() {
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
        locations.add(new Location(R.drawable.ic_launcher_background,"Location 1",0));
    }

    /**
     * Method to create durations list
     */
    private static void createDurations() {
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
        durations.add(new Duration("Duration 1",0));
    }

    /**
     * Method to return the list of inputs
     */
    public static ArrayList<Object> getBranches() {
        return branches;
    }

    public static ArrayList<Object> getLocations() {
        return locations;
    }

    public static ArrayList<Object> getDurations() {
        return durations;
    }
}
