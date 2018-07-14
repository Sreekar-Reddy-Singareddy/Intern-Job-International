package com.example.apple.interninternational.Utilities;

public class DataBaseConstants {

    /**
     * Seperator values used for parsing the request data.
     * These are used on the server side.
     */
    public static String VALUE_SEPERATOR = "=";
    public static String PAIR_SEPERATOR = "#";

    public static class Users {
        /**
         * Static constants that hold the names of the columns
         * of the Users table from main MySQL database
         */
        public static String COL_EMAIL = "email";
        public static String COL_PASSWORD = "nrml_pwd";
        public static String COL_NAME = "name";
        public static String COL_MOBILE = "mobile";
    }

}
