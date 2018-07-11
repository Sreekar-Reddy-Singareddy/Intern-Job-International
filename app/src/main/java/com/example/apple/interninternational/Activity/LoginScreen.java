package com.example.apple.interninternational.Activity;


import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.LoginLoader;
import com.example.apple.interninternational.Validations.LoginValidation;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Boolean> {

    /**
     * These are the three constants to let the next activity know about the
     * user's choice
     */
    private static int SKIP_LOGIN = 0;
    private static int LOGIN = 1;
    private static int REGISTER = 2;

    // UI properties
    private Button login;
    private TextView signUp;
    private EditText username, password;

    // Global intent for HomeScreen
    private Intent homeIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_screen);
        // Animation for screen navigation
        overridePendingTransition(R.anim.enter_right,R.anim.exit_left);
        initialiseUi();
        homeIntent = new Intent(this,HomeScreen.class);
    }

    /**
     * Creates UI instances and binds them to the actual UI
     */
    private void initialiseUi() {
        login = (Button) findViewById(R.id.act_login_screen_bt_login);
        login.setOnClickListener(this);
        signUp = (TextView) findViewById(R.id.act_login_screen_tv_signup);
        signUp.setOnClickListener(this);
        username = (EditText) findViewById(R.id.act_login_screen_et_username);
        password = (EditText) findViewById(R.id.act_login_screen_et_password);
    }

    /**
     * Called when a registered view in login screen is clicked
     * The click event is handled here
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == login.getId()) {
            homeIntent.putExtra("UserChoice",LOGIN);
            // Validate fields before proceeding
            if (!validateLoginCredits()){
                return;
            }
            // Create a bundle of data as input for the loader
            // This will send the loader bundle with user given input credentials
            Bundle loaderInputData = new Bundle();
            loaderInputData.putString("Username",this.username.getText().toString().toLowerCase());
            loaderInputData.putString("Password",this.password.getText().toString());
            // Check if the loader already exists of type LoginLoader
            Loader loader = getSupportLoaderManager().getLoader(1);
            if (loader != null){
                // Loader already exists, so update the bundle
                getSupportLoaderManager().restartLoader(1,loaderInputData,this);
            }
            getSupportLoaderManager().initLoader(1,loaderInputData,this).forceLoad();
            login.setEnabled(false);
        }
        else if (v.getId() == signUp.getId()) {
            // TODO: Logic to collect and insert user inputs and insert in database
            Toast.makeText(this, "Sign up loading...",Toast.LENGTH_SHORT).show();
            homeIntent.putExtra("UserChoice",REGISTER);
        }
        // Loads the next activity
    }

    /**
     * This method validates the user inputs and gives apt messages
     * Checks for empty fields
     * Checks for proper username
     * @return: true if all validations pass, else false
     */
    private boolean validateLoginCredits() {
        // Validate all the fields first and then proceed
        if (this.username.getText().toString().isEmpty() || this.password.getText().toString().isEmpty()) {
            // Empty fields found
            Toast.makeText(this,"Enter All Fields",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!LoginValidation.validateEmailUsername(this.username.getText().toString())) {
            // Invalid email id format
            Toast.makeText(this,"Invalid Username",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
        // Check the result data and act accordingly
        if (data) {
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            // Navigate to next screen
            startActivity(homeIntent);
        }
        else {
            Toast.makeText(this,"Login Failure",Toast.LENGTH_SHORT).show();
        }
        // Once done with the loader, reset it
        loader.reset();
        login.setEnabled(true);
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
