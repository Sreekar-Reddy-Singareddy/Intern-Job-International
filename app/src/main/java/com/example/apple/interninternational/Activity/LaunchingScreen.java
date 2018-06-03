package com.example.apple.interninternational.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apple.interninternational.R;

public class LaunchingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launch_screen);
        // Handler to post a Runnable after a specified time
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Lanuch the home screen after 2 seconds
                Intent homeScreenIntent = new Intent(LaunchingScreen.this,HomeScreen.class);
                startActivity(homeScreenIntent);
            }
        },1000);
    }
}
