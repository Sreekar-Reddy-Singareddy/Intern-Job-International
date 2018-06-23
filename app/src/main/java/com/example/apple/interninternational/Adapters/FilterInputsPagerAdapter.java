package com.example.apple.interninternational.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.apple.interninternational.Fragment.BranchListFragment;
import com.example.apple.interninternational.Fragment.DurationsListFragment;
import com.example.apple.interninternational.Fragment.LocationListFragment;

public class FilterInputsPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager manager;
    private int noOfPages;

    public FilterInputsPagerAdapter(FragmentManager fm, int noOfPages) {
        super(fm);
        System.out.println("Inside FilterInputsPagerAdapter");
        this.manager = fm;
        this.noOfPages = noOfPages;
    }

    /**
     * This method returns the number of pages in the viewer
     * @return
     */
    @Override
    public int getCount() {
        Log.i("**", "getCount: "+noOfPages);
        return noOfPages;
    }

    /**
     * This method returns the fragment for the given position of the pager
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        System.out.println("Inside getItem");
        switch (position){
            case 0:
                Log.i("**", "getItem: 0");
                return new BranchListFragment();
            case 1:
                return new LocationListFragment();
            case 2:
                return new DurationsListFragment();
            default:
                return null;
        }
    }
}
