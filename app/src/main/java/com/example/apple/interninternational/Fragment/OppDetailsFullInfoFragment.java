package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.OppDetailsFullInfoPagerAdapter;
import com.example.apple.interninternational.R;

public class OppDetailsFullInfoFragment extends Fragment {

    // UI Properties
    private View mainView;
    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.opp_details_full_info,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi(){
        pager = (ViewPager) mainView.findViewById(R.id.opp_details_full_info_vp_pager);
        OppDetailsFullInfoPagerAdapter adapter = new OppDetailsFullInfoPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager());
        pager.setAdapter(adapter);
        // Bind the tabs to the pager
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) mainView.findViewById(R.id.opp_details_full_info_tabs);
        tabStrip.setViewPager(pager);
    }
}
