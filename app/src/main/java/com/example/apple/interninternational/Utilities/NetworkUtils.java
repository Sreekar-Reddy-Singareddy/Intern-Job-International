package com.example.apple.interninternational.Utilities;

import android.util.Log;

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

    private static String USERNAME;
    private static String PASSWORD;

    /**
     * This is a static method that takes string URL
     * and returns the JSON string back to the requester
     * @param url: path for the resource
     * @return
     */
    public static String fetchJsonResponseFrom(String url, String username, String password) {
        USERNAME = username; PASSWORD = password;
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
        Log.i("NetworkUtils", "makeConnectionFromUrl: Inside");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream connectionResponse = null;
        // If the connection is not null, start the connection
        if (connection != null) {
            connection.setRequestMethod("POST");
            String userCredentialsData =
                    Users.COL_EMAIL + VALUE_SEPERATOR + USERNAME +
                    PAIR_SEPERATOR +
                    Users.COL_PASSWORD + VALUE_SEPERATOR + PASSWORD;
            Log.i("ResponseData", "makeConnectionFromUrl: response data - "+userCredentialsData);
            byte [] reqData = userCredentialsData.getBytes();
            connection.getOutputStream().write(reqData);
            connection.getOutputStream().close();
            connection.connect();
            connectionResponse = connection.getInputStream();
        }
        return connectionResponse;
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
