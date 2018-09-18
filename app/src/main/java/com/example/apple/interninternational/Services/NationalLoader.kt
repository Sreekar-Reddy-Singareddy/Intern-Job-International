package com.example.apple.interninternational.Services


import android.content.Context
import android.os.Bundle
import android.support.v4.content.AsyncTaskLoader
import android.util.Log.INFO
import android.util.Log.println
import com.example.apple.interninternational.Utilities.NetworkUtils
import java.util.*

/**
 * This class does the following operations
 * 1. Get list of available branches from database
 * 2. Get list of available locations from database
 * 3. Get list of available durations from database
 * This service can be used by any fragment or activity, therefore
 * the return data is of type String thus generalising for any data type
 */
class NationalLoader(var cont: Context, var bundle: Bundle) : AsyncTaskLoader<String>(cont) {

    /**
     * Does the background processing and returns the result when done
     */
    override fun loadInBackground(): String {
        var urlString = "http://192.168.43.165:8080/InternServer/branches";
        var jsonResponse = NetworkUtils.fetchJsonResponseFrom(urlString,null);
        println(INFO,"Service Data", "Json String Returned: ${jsonResponse}");
        return jsonResponse;
    }

}