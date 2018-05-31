package com.example.apple.interninternational;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        System.out.println("First Statement added");
    }
}
