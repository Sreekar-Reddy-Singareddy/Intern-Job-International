package com.example.apple.interninternational.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Activity.LoginScreen;
import com.example.apple.interninternational.Adapters.InterDetailsPagerAdapter;
import com.example.apple.interninternational.Adapters.InternImagesAdapter;
import com.example.apple.interninternational.Animations.DepthPageTransformer;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.FileDownloadLoader;

import java.util.ArrayList;

public class InternationalDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList>, View.OnClickListener {

    // General properties for the purpose of other classes
    private int downloadLoaderId = 890;
    private Bundle downloadBundle = new Bundle();
    private ArrayList<Bitmap> allMainImages = new ArrayList<>();
    private int positionOfLastDownloadedImage = 0;
    private String [] imageUrls;

    // UI Properties
    private View fragmentView;
    private ViewPager pager, imagePager;
    private InterDetailsPagerAdapter adapter;
    private InternImagesAdapter imageAdapter;
    private TextView mainDescription, price;
    private Button apply;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.frag_international_details_screen,container,false);
        initialiseUi();
        return fragmentView;
    }

    /**
     * Initial state of the UI is created here
     */
    @SuppressLint("RestrictedApi")
    private void initialiseUi() {
        HomeScreen.shouldShowDownloadIcon = true;
        HomeScreen.HOMESCREEN_REFERENCE.getSupportActionBar().invalidateOptionsMenu();
        pager = (ViewPager) fragmentView.findViewById(R.id.frag_inter_details_screen_vp_pager);
        imagePager = (ViewPager) fragmentView.findViewById(R.id.frag_inter_details_screen_vp_image_pager);
        adapter = new InterDetailsPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager());
        // All images array by default has four objects in it all null
        // As and when the image is downloaded, the array is updated
        allMainImages.add(null); allMainImages.add(null); allMainImages.add(null); allMainImages.add(null);
        imageAdapter = new InternImagesAdapter(getContext(),allMainImages);
        pager.setPageTransformer(true,new DepthPageTransformer());
        pager.setAdapter(adapter);
        imagePager.setPageTransformer(true,new DepthPageTransformer());
        imagePager.setAdapter(imageAdapter);
        mainDescription = (TextView) fragmentView.findViewById(R.id.frag_inter_details_screen_tv_description);
        price = (TextView )fragmentView.findViewById(R.id.frag_inter_details_screen_tv_price);
        apply = (Button) fragmentView.findViewById(R.id.frag_inter_details_screen_bt_apply);
        apply.setOnClickListener(this);
        // Based on the user login status, either show apply or login
        if (HomeScreen.ACTIVE_USER_NAME == "Anonymous") {
            apply.setText("Login and Apply");
        }
        else {
            apply.setText("Apply");
        }
        // Country specific values
        mainDescription.setText(InternationalFragment.countriesData.get(InternationalFragment.SELECTED_COUNTRY).getMainDesc());
        price.setText(InternationalFragment.countriesData.get(InternationalFragment.SELECTED_COUNTRY).getPrice());
        imageUrls = InternationalFragment.countriesData.get(InternationalFragment.SELECTED_COUNTRY).getImageUrls();
        // Start downloading the very first image
        String url = imageUrls[0];
        // Property values for the download bundle
        // The bundle is used by the download loader to get information about the download
        downloadBundle.putString("File_Url",url);
        downloadBundle.putString("Save_Location","");
        downloadBundle.putString("File_Name","");
        HomeScreen.HOMESCREEN_REFERENCE.getSupportLoaderManager().initLoader(downloadLoaderId,downloadBundle,this).forceLoad();
    }

    /**
     * Download loader call back methods
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<ArrayList> onCreateLoader(int id, Bundle args) {
        Log.i("ImageLoader", "onCreateLoader: Image Loader Started");
        return new FileDownloadLoader(getContext(),args);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList> loader, ArrayList data) {
        Integer downlaodResultCode = (Integer) data.get(0);
        if (downlaodResultCode == FileDownloadLoader.FILE_DOWNLOADED_AND_SAVED) {
            Log.i("ImageLoader", "onLoadFinished: Image Downloaded");
            // Image was downloaded successfully, so display it to the user
            // Convert the image to bitmap and add it to the main images array
            // The array acts as the data set for the pager adapter
            byte[] imageBytes = (byte[]) data.get(1);
            Bitmap actualImage = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
            // When the image is downloaded, the object at that index is updated and then the index increased by one
            // So that the next image is correctly placed in the right position
            if (positionOfLastDownloadedImage >= 0 && positionOfLastDownloadedImage < 4) {
                // As of now only 4 images for each country
                Log.i("NewImage", "onLoadFinished: New Image: "+positionOfLastDownloadedImage);
                allMainImages.set(positionOfLastDownloadedImage, actualImage);
                imageAdapter.notifyDataSetChanged();
            }
            loader.reset();
        }
        // Update the index anyways
        positionOfLastDownloadedImage += 1;
        // Move on to download the next image at new position
        if (positionOfLastDownloadedImage < 4) {
            downloadBundle.putString("File_Url",imageUrls[positionOfLastDownloadedImage]);
            Loader downloadLoader = HomeScreen.HOMESCREEN_REFERENCE.getSupportLoaderManager().getLoader(downloadLoaderId);
            if (downloadLoader == null) {
                // Loader does not exist, so initialise it
                HomeScreen.HOMESCREEN_REFERENCE.getSupportLoaderManager().initLoader(downloadLoaderId,downloadBundle,this).forceLoad();
            }
            else {
                // Loader exists, so restart it
                HomeScreen.HOMESCREEN_REFERENCE.getSupportLoaderManager().restartLoader(downloadLoaderId,downloadBundle,this).forceLoad();
            }
        }
        loader.reset();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList> loader) {

    }

    @Override
    public void onClick(View v) {
        if (apply.getId() == v.getId()) {
            // Based on the text of the button, the action will be decided
            Log.i("ButtonName", "onClick: "+apply.getText());
            if (apply.getText().toString().toLowerCase().equals("apply")) {
                Toast.makeText(getContext(),"Apply", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(),"Login to Apply", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(getContext(), LoginScreen.class);
                startActivity(loginIntent);
            }
        }
    }
}
