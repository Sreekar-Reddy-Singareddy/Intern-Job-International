package com.example.apple.interninternational.Activity;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.interninternational.Beans.Register;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.RegisterLoader;
import com.example.apple.interninternational.Validations.RegisterValidation;

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private Register registerBean;

    // UI properties
    private EditText firstName, lastName, emailId, mobile, password, verifyPassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register_screen);
        initialiseUi();
        registerBean = createRegisterBean();
        System.out.println("RegScreenName: "+registerBean.getFirstName());
    }

    /**
     * Initialises the UI of the screen.
     * Creates objects for all the UI elements
     */
    private void initialiseUi(){
        firstName = (EditText) findViewById(R.id.act_register_screen_et_firstname);
        lastName = (EditText) findViewById(R.id.act_register_screen_et_lastname);
        emailId = (EditText) findViewById(R.id.act_register_screen_et_email);
        mobile = (EditText) findViewById(R.id.act_register_screen_et_mobile);
        password = (EditText) findViewById(R.id.act_register_screen_et_password);
        verifyPassword = (EditText) findViewById(R.id.act_register_screen_et_verify_password);
        register = (Button) findViewById(R.id.act_register_screen_bt_register);
        register.setOnClickListener(this);
    }

    /**
     * Collects the inputs from the register screen
     * and creates register bean with them
     * @return
     */
    private Register createRegisterBean() {
        Register registerBean = new Register();
        registerBean.setFirstName(this.firstName.getText().toString());
        registerBean.setLastName(this.lastName.getText().toString());
        registerBean.setEmail(this.emailId.getText().toString());
        registerBean.setMobile(this.mobile.getText().toString());
        registerBean.setPassword(this.password.getText().toString());
        registerBean.setVerifyPassword(this.verifyPassword.getText().toString());
        return registerBean;
    }

    /**
     * Called to handle the view click event.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == register.getId()) {
            registerBean = createRegisterBean();
            String validateMessageToken = RegisterValidation.validateAll(registerBean);
            if (!displayAlertForInvalidInput(validateMessageToken)) {
                // Atleast one validation has failed
                // Hence do not make any loaders
                return;
            }
            // Create a loader, which will take care of the rest
            Bundle loaderData = new Bundle();
            loaderData.putParcelable("RegisterBean",registerBean);
            // Find the loader with the ID
            // Check if the loader is already existing, if so, restart the loader
            Loader loader = getSupportLoaderManager().getLoader(2);
            if (loader != null) {
                // Loader exists already
                getSupportLoaderManager().restartLoader(2,loaderData,this).forceLoad();
            }
            else {
                // Create new loader
                getSupportLoaderManager().initLoader(2,loaderData,this).forceLoad();
            }
        }
    }

    // Loader callback methods, called when apt event occurs
    // All these methods are called by the system
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        // Create bundle with the apt data
        RegisterLoader registerLoader = new RegisterLoader(this, args);
        return registerLoader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        // Data contains some acknowledgement message
        if (data.equals("Insert Success")) {
            // TODO: Logic
        }
        else if(data.equals("Insert Fail")) {
            // TODO: Logic
        }
        else if (data.equals("User Exists")) {
            Toast.makeText(this,"User exists already!",Toast.LENGTH_SHORT).show();
        }
        loader.reset();
    }

    /**
     * This method takes the invalid message token returned by the
     * loader and checks which input is invalid.
     * Based on this, the method displays alert messages
     * @param data
     */
    private boolean displayAlertForInvalidInput(String data) {
        if (data.equals(RegisterValidation.ALL_VALID)) {
            return true;
        }
        if (data.equals(RegisterValidation.FIRSTNAME_INVALID)) {
            Toast.makeText(this,RegisterValidation.FIRSTNAME_INVALID,Toast.LENGTH_SHORT).show();
        }
        else if (data.equals(RegisterValidation.LASTNAME_INVALID)) {
            Toast.makeText(this,RegisterValidation.LASTNAME_INVALID,Toast.LENGTH_SHORT).show();
        }
        else if (data.equals(RegisterValidation.EMAIL_INVALID)) {
            Toast.makeText(this,RegisterValidation.EMAIL_INVALID,Toast.LENGTH_SHORT).show();
        }
        else if (data.equals(RegisterValidation.MOBILE_INVALID)) {
            Toast.makeText(this,RegisterValidation.MOBILE_INVALID,Toast.LENGTH_SHORT).show();
        }
        else if (data.equals(RegisterValidation.PASSWORD_INVALID)) {
            Toast.makeText(this,RegisterValidation.PASSWORD_INVALID,Toast.LENGTH_SHORT).show();
        }
        else if (data.equals(RegisterValidation.MATCH_INVALID)) {
            Toast.makeText(this,RegisterValidation.MATCH_INVALID,Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
