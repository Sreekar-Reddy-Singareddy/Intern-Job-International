package com.example.apple.interninternational.Utilities;

import com.example.apple.interninternational.Beans.Opportunity;

import java.util.ArrayList;

public class OpportunitiesUtils {

    private static ArrayList<Opportunity> opportunities;

    static {
        /**
         * When this class is loaded into the memory
         * the opportunities data is created and kept ready for use
         */
        createOpportunities();
    }

    private OpportunitiesUtils() {

    }

    private static void createOpportunities() {
        opportunities = new ArrayList<>();
        opportunities.add(new Opportunity("Reliance","Kadapa"));
        opportunities.add(new Opportunity("Reliance","Kadapa"));
        opportunities.add(new Opportunity("Real Fresh","Mysore"));
        opportunities.add(new Opportunity("Reliance","Kadapa"));
        opportunities.add(new Opportunity("Reliance","Kadapa"));
    }

    public static ArrayList<Opportunity> getOpportunities(){
        return opportunities;
    }
}
