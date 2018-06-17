package com.example.apple.interninternational.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apple.interninternational.Adapters.SkillsListAdapter;
import com.example.apple.interninternational.Fragment.BlogFragment;
import com.example.apple.interninternational.Fragment.CompaniesFragment;
import com.example.apple.interninternational.Fragment.InternationalFragment;
import com.example.apple.interninternational.Fragment.InternshipFragment;
import com.example.apple.interninternational.Fragment.NgoFragment;
import com.example.apple.interninternational.R;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * STATIC reference of the current activity
     * Updated every time new instance of activity is created
     */
    public static HomeScreen HOMESCREEN_REFERENCE;
    /**
     * This remembers whether the options menu should show download icon or not
     * Updated in the InternationalFragment.java
     */
    public static boolean shouldShowDownloadIcon = false;

    // UI Properties
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout layout;

    public void initialiseUi() {
        System.out.println("Main Ref: "+this);
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
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_screen_menu_internships_item){
            // Replaces the frame with internship fragment
            InternshipFragment internshipFragment = new InternshipFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,internshipFragment).commit();
        }
        else if (item.getItemId() == R.id.home_screen_menu_home_item) {
            Toast.makeText(this,"Home Screen",Toast.LENGTH_SHORT).show();
            getSupportActionBar().invalidateOptionsMenu();
        }
        else if (item.getItemId() == R.id.home_screen_menu_companies_item) {
            // Navigate to companies screen
            // Invalidate the options menu once and then navigate to the screen
            getSupportActionBar().invalidateOptionsMenu();
            Toast.makeText(this,"Companies Screen",Toast.LENGTH_SHORT).show();
            CompaniesFragment companiesFragment = new CompaniesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,companiesFragment).commit();
        }
        else if (item.getItemId() == R.id.home_screen_menu_ngos_item){
            // Navigate the user to ngos screen
            getSupportActionBar().invalidateOptionsMenu();
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "NGO screen",Toast.LENGTH_SHORT).show();
            NgoFragment ngoFragment = new NgoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,ngoFragment).commit();
        }
        else if (item.getItemId() == R.id.home_screen_menu_blog_item) {
            // Navigate the user to the blogs list screen
            getSupportActionBar().invalidateOptionsMenu();
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "Blog list screen",Toast.LENGTH_SHORT).show();
            BlogFragment blogFragment = new BlogFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,blogFragment).commit();
        }
        layout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create download icon menu option based on the flag
        if (shouldShowDownloadIcon){
            shouldShowDownloadIcon = false; // Reset the flag to false
            getMenuInflater().inflate(R.menu.download_icon_menu,menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check which item is selected and perform action appropriately
        if (item.getItemId() == android.R.id.home) {
            layout.openDrawer(GravityCompat.START);
        }
        else if (item.getItemId() == R.id.download_icon_menu_download_item){
            Toast.makeText(this,"Downloading...",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Back",Toast.LENGTH_LONG).show();
    }


    public static void makeACall(String phoneNumber){
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        // Check the call permission
        System.out.println("Static Ref: "+HOMESCREEN_REFERENCE);
        System.out.println("Permission Code: "+HomeScreen.HOMESCREEN_REFERENCE.checkSelfPermission(Manifest.permission.CALL_PHONE));
        if (HomeScreen.HOMESCREEN_REFERENCE.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED){
            // Permission is not given
            System.out.println("Perimission Denied");
            HomeScreen.HOMESCREEN_REFERENCE.requestPermissions(new String [] {Manifest.permission.CALL_PHONE},1234);
        }
        else {
            // Permission is given
            System.out.println("Perimission Granted");
            System.out.println("URI Data: "+callIntent.getData());
            HOMESCREEN_REFERENCE.startActivity(callIntent);
        }
    }

    /**
     * Result of the requested permission
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();
            makeACall(SkillsListAdapter.phoneNumber);
        }
    }
}
