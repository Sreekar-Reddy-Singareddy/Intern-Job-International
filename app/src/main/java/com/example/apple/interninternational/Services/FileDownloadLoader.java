package com.example.apple.interninternational.Services;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.example.apple.interninternational.Utilities.FileDownloadUtils;

import java.util.ArrayList;

public class FileDownloadLoader extends AsyncTaskLoader<ArrayList> {

    /**
     * Static properties that define the output of the loader task
     */
    public static final int FILE_DOWNLOADED_AND_SAVED = 0;
    public static final int FILE_DOWNLOADED_NOT_SAVED = 1;
    public static final int FILE_NOT_DOWNLOADED = 2;
    public static final int FILE_NOT_FOUND = 3;

    // Bundle of values for the purpose of loader
    private Bundle loaderBundle;

    public FileDownloadLoader(Context context, Bundle bundle) {
        super(context);
        loaderBundle = bundle;
    }

    @Override
    public ArrayList loadInBackground() {
        String saveLocation = loaderBundle.getString("Save_Location");
        String fileName = loaderBundle.getString("File_Name");
        String url = loaderBundle.getString("File_Url");
        // Return what the downloader has returned
        return FileDownloadUtils.downloadFileAt(url,saveLocation,fileName);
    }

}
