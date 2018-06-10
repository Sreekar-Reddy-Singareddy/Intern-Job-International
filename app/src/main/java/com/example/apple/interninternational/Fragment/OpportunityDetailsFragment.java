package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.OpportunityDetailsPagerAdapter;
import com.example.apple.interninternational.R;

public class OpportunityDetailsFragment extends Fragment {

    // UI Properties
    private View mainView;
    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_opportunity_details,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi(){
        pager = (ViewPager) mainView.findViewById(R.id.frag_opportunity_details_vp_pager);
        // Create adapter and set it to the pager
        OpportunityDetailsPagerAdapter adapter = new OpportunityDetailsPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
