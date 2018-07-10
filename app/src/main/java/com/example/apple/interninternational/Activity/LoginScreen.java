package com.example.apple.interninternational.Activity;


import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.LoginLoader;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Boolean> {

    /**
     * These are the three constants to let the next activity know about the
     * user's choice
     */
    private static int SKIP_LOGIN = 0;
    private static int LOGIN = 1;
    private static int REGISTER = 2;

    private Button login;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_screen);
        // Animation for screen navigation
        overridePendingTransition(R.anim.enter_right,R.anim.exit_left);
        initialiseUi();
    }

    /**
     * Creates UI instances and binds them to the actual UI
     */
    private void initialiseUi() {
        login = (Button) findViewById(R.id.act_login_screen_bt_login);
        login.setOnClickListener(this);
        signUp = (TextView) findViewById(R.id.act_login_screen_tv_signup);
        signUp.setOnClickListener(this);
    }

    /**
     * Called when a registered view in login screen is clicked
     * The click event is handled here
     * @param v
     */
    @Override
    public void onClick(View v) {
        // Intent to load the next activity - HomeScreen
        Intent homeScreenIntent = new Intent(this,HomeScreen.class);
        if (v.getId() == login.getId()) {
            // TODO: Logic to validate the user credentials
            Toast.makeText(this, "Login verifying...",Toast.LENGTH_SHORT).show();
            // Put some extra data in the intent for the reciever purpose
            homeScreenIntent.putExtra("Username","XYZ");
            homeScreenIntent.putExtra("Password","ABC");
            homeScreenIntent.putExtra("UserChoice",LOGIN);
            // Create a bundle of data as input for the loader
            Bundle loaderInputData = new Bundle();
            loaderInputData.putString("Username","Sreekar Reddy");
            loaderInputData.putString("Password","gopiusha");
            getSupportLoaderManager().initLoader(1,loaderInputData,this).forceLoad();
        }
        else if (v.getId() == signUp.getId()) {
            // TODO: Logic to collect and insert user inputs and insert in database
            Toast.makeText(this, "Sign up loading...",Toast.LENGTH_SHORT).show();
            homeScreenIntent.putExtra("UserChoice",REGISTER);
        }
        // Loads the next activity
    }

    /**
     * Creates a loader using the bundle args and with the given id
     * @param id: ID of the loader
     * @param args: bundle of args if any
     * @return
     */
    @Override
    public Loader<Boolean> onCreateLoader(int id, Bundle args) {
        Log.i("LoginScreen", "onCreateLoader: Inside");
        // Create a new loader of type LoginLoader
        LoginLoader loginLoader = new LoginLoader(LoginScreen.this,args);
        return loginLoader;
    }

    /**
     * Called when the loader finishes loading the data
     * @param loader: loader running
     * @param data: data returned by the loader
     */
    @Override
    public void onLoadFinished(Loader<Boolean> loader, Boolean data) {
        Log.i("LoginScreen", "onLoadFinished: Inside");
        Log.i("Data", "onLoadFinished: "+data);
        Log.i("LoginScreen", "onLoadFinished: runs on thread - "+Thread.currentThread());
    }

    /**
     * Called when the loader has to be reset to initial state
     * Usually called when the data is no more needed and has to be destroyed
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<Boolean> loader) {
        Log.i("LoginScreen", "onLoaderReset: Inside");
    }
}
