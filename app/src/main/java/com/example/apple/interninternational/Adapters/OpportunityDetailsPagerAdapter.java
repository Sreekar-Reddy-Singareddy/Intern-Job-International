package com.example.apple.interninternational.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.apple.interninternational.Fragment.OppDetailsFullInfoFragment;
import com.example.apple.interninternational.Fragment.OppDetailsSummaryFragment;

public class OpportunityDetailsPagerAdapter extends FragmentStatePagerAdapter {

    /**
     * Simple constructor
     * @param fm
     */
    public OpportunityDetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Returns the appropriate fragment for every page in the pager
     * @param position: current page position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new OppDetailsSummaryFragment();
        }
        else if (position == 1){
            return new OppDetailsFullInfoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
