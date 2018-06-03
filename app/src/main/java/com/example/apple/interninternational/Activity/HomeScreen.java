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

import com.example.apple.interninternational.Fragment.HomeFragment;
import com.example.apple.interninternational.R;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        // Animation for screen navigation
        overridePendingTransition(R.anim.enter_right,R.anim.exit_left);
        // Initialise UI
        initialiseUi();
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.act_home_screen_fl_frag,homeFragment).commit();
    }

    /**
     * This method is called when a menu item is selected from nav drawer
     * @param item : the menu item that has been selected
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
