package com.example.apple.interninternational.Utilities;

import android.util.Log;

import com.example.apple.interninternational.Beans.Login;
import com.example.apple.interninternational.Beans.Register;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.example.apple.interninternational.Utilities.DataBaseConstants.PAIR_SEPERATOR;
import static com.example.apple.interninternational.Utilities.DataBaseConstants.Users;
import static com.example.apple.interninternational.Utilities.DataBaseConstants.VALUE_SEPERATOR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class contains methods which take URL
 * and return the resposne in the form of JSON string
 */
public class NetworkUtils {

    private static Object REQUEST_DATA;

    /**
     * This is a static method that takes string URL
     * and returns the JSON string back to the requester
     * @param url: path for the resource
     * @return
     */
    public static String fetchJsonResponseFrom(String url, Object requestData) {
        REQUEST_DATA = requestData;
        Log.i("NetworkUtils", "fetchJsonResponseFrom: Inside");
        URL mainUrl = null;
        InputStream connectionResponse = null;
        String jsonString = "";
        try {
            // Convert the string into URL
            mainUrl = createUrlFromString(url);
            // Check if the url is not null
            // Make a http connection
            if (mainUrl != null) {
                connectionResponse = makeConnectionFromUrl(mainUrl);
            }
            // Check if the input stream is not null
            // and convert the stream to JSON String
            if (connectionResponse != null) {
                jsonString = parseJsonFromStream(connectionResponse);
            }
        }
        catch (MalformedURLException e) {
            Log.i("NetworkUtils", "fetchJsonResponseFrom: Failed to convert the string to URL");
        }
        catch (IOException e) {
            Log.i("NetworkUtils", "fetchJsonResponseFrom: Failed to make HTTP connection for this URL -> \n"+mainUrl);
            Log.i("ErrorInfo", "fetchJsonResponseFrom: error because - "+e.getMessage());
        }
        // After everything, return the json string
        // irrespective of what is there in it
        finally {
            return jsonString;
        }
    }

    /**
     * This method converts the string into URL
     * @param url: string form of the url that needs to be converted to URL
     * @return
     * @throws MalformedURLException
     */
    private static URL createUrlFromString(String url) throws MalformedURLException {
        Log.i("NetworkUtils", "createUrlFromString: Inside");
        URL mainUrl = new URL(url);
        return mainUrl;
    }

    /**
     * This method creates Http connection and returns the response stream
     * @param url
     * @return
     * @throws IOException
     */
    private static InputStream makeConnectionFromUrl(URL url) throws IOException {
        String requestData = null;
        Log.i("NetworkUtils", "makeConnectionFromUrl: Inside");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream connectionResponse = null;
        // If the connection is not null, start the connection
        if (connection == null) {
            return null;
        }
        // Check whether there is any data being posted to the server
        if (REQUEST_DATA == null) {
            // No data, so the method could be GET
            connection.setRequestMethod("GET");
        }
        else {
            // Data exists, so the method could be POST
            connection.setRequestMethod("POST");
            // Check the type of data and write query
            requestData = getDataStringFor(REQUEST_DATA);
            Log.i("ResponseData", "makeConnectionFromUrl: response data - "+requestData);
            byte [] reqData = requestData.getBytes();
            connection.getOutputStream().write(reqData);
            connection.getOutputStream().close();
        }
        connection.connect();
        connectionResponse = connection.getInputStream();
        return connectionResponse;
    }

    /**
     * This method checks the type of data sent.
     * Based on this, the SQL query is made and returned to
     * calling environment
     * @return
     */
    private static String getDataStringFor(Object requestData) {
        String finalRequestData = null;
        if (requestData instanceof Login) {
            // Data is of type Login.class
            Login loginData = (Login) requestData;
            finalRequestData =
                    Users.COL_EMAIL + VALUE_SEPERATOR + loginData.getEmail() +
                    PAIR_SEPERATOR +
                    Users.COL_PASSWORD + VALUE_SEPERATOR + loginData.getNrml_pwd();
            Log.i("LoginData", "giveQueryFor: Final Data - "+finalRequestData);
        }
        else if (requestData instanceof Register) {
            // Data is of type Register.class
            Register registerData = (Register) requestData;
            finalRequestData =
                            Users.COL_NAME + VALUE_SEPERATOR + registerData.getName() +
                            PAIR_SEPERATOR +
                            Users.COL_EMAIL + VALUE_SEPERATOR + registerData.getEmail() +
                            PAIR_SEPERATOR +
                            Users.COL_MOBILE + VALUE_SEPERATOR + registerData.getMobile() +
                            PAIR_SEPERATOR +
                            Users.COL_PASSWORD + VALUE_SEPERATOR + registerData.getPassword();
            System.out.println("*****"+finalRequestData);
        }
        return finalRequestData;
    }

    /**
     * Converts the input stream into a string
     * This string is in the form of JSON notation
     * @param rawData: The input stream that needs to be converted into string format
     * @return: converted data into string format
     * @throws IOException
     */
    private static String parseJsonFromStream (InputStream rawData) throws IOException {
        Log.i("NetworkUtils", "parseJsonFromStream: Inside");
        int readCount;
        char [] charArr = new char[128];
        StringBuilder mainJsonStringBuilder = new StringBuilder("");
        BufferedReader reader = new BufferedReader(new InputStreamReader(rawData));
        // Read the stream until all the chars are read
        while ((readCount = reader.read(charArr)) > -1) {
            String tempString = new String(charArr,0,readCount);
            // Append the data read, to the main string
            mainJsonStringBuilder.append(tempString);
        }
        Log.d("NetworkUtils", "parseJsonFromStream() returned: " + mainJsonStringBuilder.toString());
        return mainJsonStringBuilder.toString();
    }

}
