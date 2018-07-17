package com.example.apple.interninternational.Utilities;

import android.os.Environment;
import android.util.Log;

import com.example.apple.interninternational.Beans.Login;
import com.example.apple.interninternational.Beans.Register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.apple.interninternational.Utilities.DataBaseConstants.PAIR_SEPERATOR;
import static com.example.apple.interninternational.Utilities.DataBaseConstants.Users;
import static com.example.apple.interninternational.Utilities.DataBaseConstants.VALUE_SEPERATOR;

/**
 * This class contains methods which take URL
 * and return the resposne in the form of JSON string
 */
public class FileDownloadUtils {

    /**
     * Takes the url string and there by downloads the file
     * Downloaded file is saved into the location passed here
     * @param url: Path for the resource on remote server
     * @param saveLocation: Path where the downloaded resource will be saved
     * @return: true if the task is success, else false
     */
    public static int downloadFileAt(String url, String saveLocation, String fileName) {
        try {
            URL mainUrl = createUrlFromString(url);
            if (mainUrl == null) {
                return 3;
            }
            InputStream inputStream = makeConnectionFromUrl(mainUrl);
            if (inputStream == null) {
                return 3;
            }
            if (createFileFromStream(inputStream,saveLocation,fileName)) {
                // File downlaoded and saved successfully
                return 0;
            }
        } catch (MalformedURLException e) {
            Log.i("URL Exception", "downloadFileAt: MalformedURL");
            return 3;
        } catch (IOException e) {
            Log.i("IO Exception", "downloadFileAt: IO Exception because "+e.getMessage());
            return 3;
        }
        return 3;
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
        connection.connect();
        connectionResponse = connection.getInputStream();
        return connectionResponse;
    }

    /**
     * Converts the raw data into actual file and saves it in the given location
     * with the given name
     * @param inputStream: downloaded data
     * @param saveLocation: location of the file
     * @param fileName: name of the actual file
     * @return
     */
    private static boolean createFileFromStream (InputStream inputStream, String saveLocation, String fileName) throws IOException {
        byte [] buffer = new byte[512];
        int bufferSize = 0;
        Log.i("Before", "createFileFromStream: ------------------");
        File mainFile = new File(Environment.getExternalStorageDirectory(),fileName);
        Log.i("Intermediate 1", "createFileFromStream: ------------------");
        FileOutputStream outputStream = new FileOutputStream(mainFile);
        while ((bufferSize = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer,0,bufferSize);
        }
        inputStream.close();
        outputStream.close();
        Log.i("After", "createFileFromStream: ------------------");
        Log.i("Full File Location", "createFileFromStream: full path = "+mainFile.getAbsolutePath());
        return true;
    }
}
