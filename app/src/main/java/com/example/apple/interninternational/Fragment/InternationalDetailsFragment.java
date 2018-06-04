package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.InterDetailsPagerAdapter;
import com.example.apple.interninternational.Animations.DepthPageTransformer;
import com.example.apple.interninternational.R;

public class InternationalDetailsFragment extends Fragment {

    // UI Properties
    private View fragmentView;
    private ViewPager pager;
    private InterDetailsPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.frag_international_details_screen,container,false);
        initialiseUi();
        return fragmentView;
    }

    private void initialiseUi() {
        pager = (ViewPager) fragmentView.findViewById(R.id.frag_inter_details_screen_vp_pager);
        adapter = new InterDetailsPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager());
        pager.setPageTransformer(true,new DepthPageTransformer());
        pager.setAdapter(adapter);
    }
}
