package com.example.apple.interninternational.Fragment;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.InterDetailsPagerAdapter;
import com.example.apple.interninternational.Animations.DepthPageTransformer;
import com.example.apple.interninternational.R;

public class InternationalDetailsFragment extends Fragment {

    // General properties for the purpose of other classes


    // UI Properties
    private View fragmentView;
    private ViewPager pager;
    private InterDetailsPagerAdapter adapter;
    private TextView mainDescription, price;

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
        adapter = new InterDetailsPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager());
        pager.setPageTransformer(true,new DepthPageTransformer());
        pager.setAdapter(adapter);
        mainDescription = (TextView) fragmentView.findViewById(R.id.frag_inter_details_screen_tv_description);
        price = (TextView )fragmentView.findViewById(R.id.frag_inter_details_screen_tv_price);
        switch (InternationalFragment.SELECTED_COUNTRY) {
            case "INDIA":
                mainDescription.setText(InternationalFragment.countriesData.get("INDIA").getMainDesc());
                price.setText(InternationalFragment.countriesData.get("INDIA").getPrice());
                break;
            case "CHINA":
                mainDescription.setText(InternationalFragment.countriesData.get("CHINA").getMainDesc());
                price.setText(InternationalFragment.countriesData.get("CHINA").getPrice());
                break;
            case "GERMANY":
                mainDescription.setText(InternationalFragment.countriesData.get("GERMANY").getMainDesc());
                price.setText(InternationalFragment.countriesData.get("GERMANY").getPrice());
                break;
            case "IJ":
                mainDescription.setText(InternationalFragment.countriesData.get("IJ").getMainDesc());
                price.setText(InternationalFragment.countriesData.get("IJ").getPrice());
                break;
        }
    }
}
