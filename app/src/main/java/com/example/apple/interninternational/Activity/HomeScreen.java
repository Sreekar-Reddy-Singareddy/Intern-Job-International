package com.example.apple.interninternational.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apple.interninternational.Fragment.InternationalFragment;
import com.example.apple.interninternational.Fragment.InternshipFragment;
import com.example.apple.interninternational.R;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * STATIC reference of the current activity
     * Updated every time new instance of activity is created
     */
    public static HomeScreen HOMESCREEN_REFERENCE;

    // UI Properties
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout layout;

    public void initialiseUi() {
        navigationView = (NavigationView) findViewById(R.id.act_home_screen_nv);
        toolbar = (Toolbar) findViewById(R.id.act_home_screen_toolbar);
        layout = (DrawerLayout) findViewById(R.id.act_home_screen_drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.home_nav_drawer_icon);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home_screen);
        HOMESCREEN_REFERENCE = this;
        // Animation for screen navigation
        overridePendingTransition(R.anim.enter_right,R.anim.exit_left);
        // Initialise UI
        initialiseUi();
    }

    /**
     * This method is called when a menu item is selected from nav drawer
     * @param item : the menu item that has been selected
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_screen_menu_internships_item){
            // Replaces the frame with internship fragment
            InternshipFragment internshipFragment = new InternshipFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,internshipFragment).commit();
        }
        else if (item.getItemId() == R.id.home_screen_menu_home_item) {
            Toast.makeText(this,"Home Screen",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.home_screen_menu_home_item) {
            // Return to home screen
        }
        layout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check which item is selected and perform action appropriately
        if (item.getItemId() == android.R.id.home) {
            layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
