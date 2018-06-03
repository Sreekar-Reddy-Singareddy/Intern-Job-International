package com.example.apple.interninternational.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apple.interninternational.R;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_screen);
        // Animation for screen navigation
        overridePendingTransition(R.anim.enter_right,R.anim.exit_left);
    }
}
