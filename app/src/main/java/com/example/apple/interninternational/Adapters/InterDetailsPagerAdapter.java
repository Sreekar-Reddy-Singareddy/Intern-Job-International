package com.example.apple.interninternational.Adapters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.apple.interninternational.Fragment.InternDetailsPageOne;
import com.example.apple.interninternational.Fragment.InternDetailsPageThree;
import com.example.apple.interninternational.Fragment.InternDetailsPageTwo;

/**
 * Created by subbuilla on 04/06/18.
 */

public class InterDetailsPagerAdapter extends FragmentStatePagerAdapter {

    public InterDetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Based on the position value, the apt fragment is created and returned
     * @param position: Index value of each page fragment
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            // Load the InternDetailsPageOne fragment
            return new InternDetailsPageOne();
        }
        else if (position == 1){
            // Load the InternDetailsPageTwo fragment
            return new InternDetailsPageTwo();
        }
        else if (position == 2){
            // Load the InternDetailsPageThree fragment
            return new InternDetailsPageThree();
        }
        return null;
    }

    /**
     * Returns the number of pages of the pager view
     * @return
     */
    @Override
    public int getCount() {
        return 3;
    }
}
