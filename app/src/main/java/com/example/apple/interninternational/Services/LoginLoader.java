package com.example.apple.interninternational.Services;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.apple.interninternational.Activity.LoginScreen;
import com.example.apple.interninternational.Beans.Login;
import com.example.apple.interninternational.Utilities.NetworkUtils;
import com.google.gson.Gson;

public class LoginLoader extends AsyncTaskLoader<Boolean> {

    private Bundle inputData;
    private String username;
    private String password;

    /**
     * Default constructor which takes context and calls the constructor
     * of the parent. There is some hidden code in parent constructor
     * using this context
     * @param context
     */
    public LoginLoader(Context context, Bundle inputData) {
        super(context);
        this.inputData = inputData;
        // Save the one-time user data in global variables
        this.username = this.inputData.getString("Username");
        this.password = this.inputData.getString("Password");
    }

    /**
     * This method is called when the loader is started
     * This method runs on a seperate thread
     * @return
     */
    @Override
    public Boolean loadInBackground() {
        Log.i("LoginLoader", "loadInBackground: runs on thread - "+Thread.currentThread());
        // TODO: Interact with DB and decide what to return
        Log.i("LoginLoader", "loadInBackground: Username: "+inputData.getString("Username"));
        Log.i("LoginLoader", "loadInBackground: Password: "+inputData.getString("Password"));
        String result = NetworkUtils.fetchJsonResponseFrom("http://192.168.43.165:8081/",this.username,this.password);
        Log.i("Server Response", "loadInBackground: Result: "+result);
        // Check the result and decide whether user exists or not
        try {
            if (result.equals("")){
                // User does not exist
                return false;
            }
            else {
                // User exists
                // Convert the JSON String into User object
                Gson gson = new Gson();
                LoginScreen.CURRENT_USER = gson.fromJson(result, Login.class);
                return true;
            }
        }
        catch (Exception e) {
            // Some exception occured, so handle it and return false
            Log.i("LoginLoader", "loadInBackground: "+e.getMessage());
            return false;
        }
    }

    @Override
    protected Boolean onLoadInBackground() {
        Log.i("LoginLoader", "onLoadInBackground: Inside");
        return super.onLoadInBackground();
    }

    public Bundle getInputData() {
        return inputData;
    }

    public void setInputData(Bundle inputData) {
        this.inputData = inputData;
    }
}
