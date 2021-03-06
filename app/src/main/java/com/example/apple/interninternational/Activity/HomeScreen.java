package com.example.apple.interninternational.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.Adapters.SkillsListAdapter;
import com.example.apple.interninternational.Fragment.AboutFragment;
import com.example.apple.interninternational.Fragment.BlogFragment;
import com.example.apple.interninternational.Fragment.CompaniesFragment;
import com.example.apple.interninternational.Fragment.CvtipsFragment;
import com.example.apple.interninternational.Fragment.InternationalFragment;
import com.example.apple.interninternational.Fragment.InternshipFragment;
import com.example.apple.interninternational.Fragment.NgoFragment;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.FileDownloadLoader;
import com.example.apple.interninternational.Utilities.ProjectUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ArrayList> {

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
    /**
     * This remembers whether the options menu should show address icon or not
     * Updated in this class itself
     */
    public static boolean shouldShowAddressIcon = false;
    /**
     * This integer count tells how much deeper the navigation has gone into
     * For every fragment that is replaced or added, the count is increased (developer's decision based)
     * 0: There is no back navigation, so display drawer icon
     * 1: There is one back navigation, so display back icon
     */
    public static int backStackCount = 0;
    /**
     * Details of the current logged in user
     */
    public static String ACTIVE_USER_NAME;
    public static String ACTIVE_USER_EMAIL;
    /**
     * Permission request codes
     */
    private static int WRITE_FILES_PERMISSION = 1;

    // Usual properties
    private Bundle downlaodLoaderBundle;

    // UI Properties
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout layout;
    private TextView nameTextview, emailTextview;

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
        View headerView = navigationView.getHeaderView(0);
        nameTextview = (TextView) headerView.findViewById(R.id.home_nav_drawer_header_title);
        emailTextview = (TextView) headerView.findViewById(R.id.home_nav_drawer_header_subtitle);

        if (getIntent().getStringExtra("Email").equals("") && getIntent().getStringExtra("Name").equals("")) {
            // User is not logged in
            // User us anonymous
            nameTextview.setText("Anonymous");
            emailTextview.setText("Anonymous"); emailTextview.setVisibility(View.INVISIBLE);
        }
        else {
            // User is logged in, so set the credits
            nameTextview.setText(getIntent().getStringExtra("Name"));
            emailTextview.setText(getIntent().getStringExtra("Email"));
        }
        ACTIVE_USER_EMAIL = emailTextview.getText().toString();
        ACTIVE_USER_NAME = nameTextview.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home_screen);
        System.out.println("The bundle sent from login screen is: "+getIntent().getStringExtra("Name"));
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
        else if (item.getItemId() == R.id.home_screen_menu_aboutus_item){
            // Navigate the user to the about us screen where all the profiles are displayed
            shouldShowAddressIcon = true;
            getSupportActionBar().invalidateOptionsMenu();
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "About us screen",Toast.LENGTH_SHORT).show();
            AboutFragment aboutFragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,aboutFragment).commit();
        }
        else if (item.getItemId() == R.id.home_screen_menu_cvtips_item){
            // Navigate the user to the about us screen where all the profiles are displayed
            getSupportActionBar().invalidateOptionsMenu();
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "CV Tips screen",Toast.LENGTH_SHORT).show();
            CvtipsFragment cvtipsFragment = new CvtipsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,cvtipsFragment).commit();
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
        // Create address icon menu option based on the corresponding flag
        if (shouldShowAddressIcon){
            getMenuInflater().inflate(R.menu.address_icon_menu,menu);
            shouldShowAddressIcon = false; // Reset the flag once the menu is created
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check which item is selected and perform action appropriately
        if (item.getItemId() == android.R.id.home) {
            // Differentiate which action the home up button should take
            if (backStackCount == 0) {
                // Show the nav drawer
                layout.openDrawer(GravityCompat.START);
            }
            else {
                // Go back to previous screen
                getSupportFragmentManager().popBackStackImmediate();
                backStackCount -= 1;
                // Check the count and update the icon accordingly
                if (backStackCount == 0){
                    actionBar.setHomeAsUpIndicator(R.drawable.home_nav_drawer_icon);
                }
            }
        }
        else if (item.getItemId() == R.id.download_icon_menu_download_item){
            // TODO: This is where the download of the apt brochure must happen
            // What ever the country is, the brochure PDF must be downloaded into device storage
            // Inputs - URL of the apt brochure
            // Outputs - Apt brochure downloaded into device memory
            // Processor - One class that takes this input and gives the output
            String fileName;
            String saveLocation = "";
            String url = InternationalFragment.countriesData.get(InternationalFragment.SELECTED_COUNTRY).getBrochureUrl();
            switch (InternationalFragment.SELECTED_COUNTRY) {
                case "INDIA":
                    Toast.makeText(this,"India Brochure...",Toast.LENGTH_LONG).show();
                    fileName = "Explore_India.pdf";
                    break;
                case "CHINA":
                    Toast.makeText(this,"China Brochure...",Toast.LENGTH_LONG).show();
                    fileName = "Explore_China.pdf";
                    break;
                case "GERMANY":
                    Toast.makeText(this,"Germany Brochure...",Toast.LENGTH_LONG).show();
                    fileName = "Explore_Germany.pdf";
                    break;
                case "IJ":
                    Toast.makeText(this,"I&J Brochure...",Toast.LENGTH_LONG).show();
                    fileName = "Explore_IJ_Company.pdf";
                    break;
                default:
                    fileName = "";
                    break;
            }
            // Create a bundle with the needed values for the purpose of the loader
            downlaodLoaderBundle = new Bundle();
            downlaodLoaderBundle.putString("File_Url",url);
            downlaodLoaderBundle.putString("Save_Location",saveLocation);
            downlaodLoaderBundle.putString("File_Name",fileName);
            // Take the required permissions and come back
            if (takePermissions()) {
                downloadFile();
            }
        }
        else if (item.getItemId() == R.id.aboutus_screen_address_item){
            Toast.makeText(this,"Address",Toast.LENGTH_LONG).show();
            // TODO: Load the address dialog box from which the user can select contact and make a call
            View addressView = getLayoutInflater().inflate(R.layout.about_contact_dialog,null);
            final RadioButton con1 = (RadioButton) addressView.findViewById(R.id.about_contact_dialog_rb_c1);
            final RadioButton con2 = (RadioButton) addressView.findViewById(R.id.about_contact_dialog_rb_c2);
            final RadioButton con3 = (RadioButton) addressView.findViewById(R.id.about_contact_dialog_rb_c3);
            con1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        // Since the first radio button is checked, uncheck the other two
                        con2.setChecked(false);
                        con3.setChecked(false);
                    }
                }
            });
            con2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        // Since the second radio button is checked, uncheck the other two
                        con1.setChecked(false);
                        con3.setChecked(false);
                    }
                }
            });
            con3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        // Since the third radio button is checked, uncheck the other two
                        con2.setChecked(false);
                        con1.setChecked(false);
                    }
                }
            });
            Button call = (Button) addressView.findViewById(R.id.about_contact_dialog_bt_call);
            AlertDialog.Builder addressDialog = new AlertDialog.Builder(this);
            addressDialog.setView(addressView);
            final AlertDialog dialog = addressDialog.create();
            dialog.show();
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (con1.isChecked()){
                        con2.setChecked(false);
                        con3.setChecked(false);
                        Toast.makeText(HomeScreen.this,"Pressed 1",Toast.LENGTH_SHORT).show();
                        ProjectUtils.makeACall(ProjectUtils.formatIndianNumber(con1.getText().toString()));
                        dialog.dismiss();
                    }
                    else if (con2.isChecked()){
                        con1.setChecked(false);
                        con3.setChecked(false);
                        Toast.makeText(HomeScreen.this,"Pressed 2",Toast.LENGTH_SHORT).show();
                        ProjectUtils.makeACall(ProjectUtils.formatIndianNumber(con2.getText().toString()));
                        dialog.dismiss();
                    }
                    else if (con3.isChecked()){
                        con2.setChecked(false);
                        con1.setChecked(false);
                        Toast.makeText(HomeScreen.this,"Pressed 3",Toast.LENGTH_SHORT).show();
                        ProjectUtils.makeACall(ProjectUtils.formatIndianNumber(con3.getText().toString()));
                        dialog.dismiss();
                    }
                    else {
                        // No contact is selected to make a call
                        // Show the toast message to the user
                        Toast.makeText(HomeScreen.this,"Select a contact",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method takes the permission needed by the app
     * to write the contents to external storage
     */
    private boolean takePermissions() {
        Log.i("Checker", "takePermissions: Inside");
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission == PackageManager.PERMISSION_GRANTED) {
            Log.i("Write Permission", "takePermissions: Granted");
            // Permission already granted return true
            return true;
        }
        else {
            Log.i("Write Permission", "takePermissions: Denied");
            // Permission needs to be taken from the user
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_FILES_PERMISSION);
            // The result of the permission will be sent to the onResult method
        }
        return false;
    }

    /**
     * Checks for the space available on the external storage
     * and then returns true if space is available
     * or returns false if space is not available
     * @return
     */
    private boolean isSpaceAvailableOnStorage() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    /**
     * Overrides the default functionality of the back button
     * The default loigc is replaced by the custom logic
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Back",Toast.LENGTH_LONG).show();
    }

    /**
     * Result of the requested permission
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();
            ProjectUtils.makeACall(SkillsListAdapter.phoneNumber);
            return;
        }
        if (requestCode == WRITE_FILES_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted to Write Files",Toast.LENGTH_SHORT).show();
            downloadFile();
            return;
        }
    }

    public static void customReplaceFragment(int id, Fragment fragment, String transationName, boolean shouldNavBack){
        HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager().
                beginTransaction().
                replace(id,fragment).
                addToBackStack(transationName).
                commit();
        if (shouldNavBack) {
            // User should be able to navigate back from this transaction
            // Hence increase the stack count by 1
            backStackCount += 1;
        }
        if (backStackCount > 0) {
            HomeScreen.HOMESCREEN_REFERENCE.
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);
        }
    }

    private void downloadFile() {
        Log.i("Checker", "downloadFile: Inside");
        // Before doing anything else, the external storage must have enough space
        if (!isSpaceAvailableOnStorage()) {
            // No space available on the storage to write files
            Log.i("Memory Checker", "downloadFile: No Space");
            return;
        }
        Loader fileDownloadLoader = getSupportLoaderManager().getLoader(111);
        if (fileDownloadLoader == null) {
            // Loader does not exist, so create one
            getSupportLoaderManager().initLoader(111,downlaodLoaderBundle,this).forceLoad();
        }
        else {
            // Loader exists, so restart the same
            getSupportLoaderManager().restartLoader(111,downlaodLoaderBundle,this).forceLoad();
        }
    }

    // Loader call back methods
    @Override
    public Loader<ArrayList> onCreateLoader(int id, Bundle args) {
        return new FileDownloadLoader(this,args);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList> loader, ArrayList data) {
        Integer downloadResultCode = (Integer) data.get(0);
        switch (downloadResultCode) {
            case FileDownloadLoader.FILE_DOWNLOADED_AND_SAVED:
                Toast.makeText(this, "File Downlaoded Succesfully!",Toast.LENGTH_SHORT).show();
                break;
            case FileDownloadLoader.FILE_DOWNLOADED_NOT_SAVED:
                Toast.makeText(this, "File Could not be saved",Toast.LENGTH_SHORT).show();
                break;
            case FileDownloadLoader.FILE_NOT_DOWNLOADED:
                Toast.makeText(this, "File Not Downloaded",Toast.LENGTH_SHORT).show();
                break;
            case FileDownloadLoader.FILE_NOT_FOUND:
                Toast.makeText(this, "File Not Found",Toast.LENGTH_SHORT).show();
                break;
        }
        loader.reset();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList> loader) {
        getSupportLoaderManager().destroyLoader(loader.getId());
    }
}
