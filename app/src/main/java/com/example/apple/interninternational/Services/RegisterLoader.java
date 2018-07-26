package com.example.apple.interninternational.Services;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.apple.interninternational.Beans.Register;
import com.example.apple.interninternational.Utilities.NetworkUtils;
import com.example.apple.interninternational.Validations.RegisterValidation;

/**
 * This loader class deals with communication with DB.
 * The DB is the User table.
 * The operations are inserting the data & fetching the data.
 * The data is the Register bean.
 */
public class RegisterLoader extends AsyncTaskLoader<String> {

    private Bundle bundle;

    public RegisterLoader(Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
    }

    /**
     * This method takes the Register bean
     * and sends the data to the database.
     * If the user exists already, some message is passed.
     * If the user does not exist, then the user is inserted and another message
     * is passed back to the client, which is the app.
     * @return
     */
    @Override
    public String loadInBackground() {
        Register registerBean = this.bundle.getParcelable("RegisterBean");
        // Use the above data and make a http request
        String httpResposneResult = NetworkUtils.fetchJsonResponseFrom("http://192.168.43.165:8080/InternServer/userregister",registerBean);
        Log.i("HttpResult", "loadInBackground: "+httpResposneResult);
        return httpResposneResult;
    }
}
