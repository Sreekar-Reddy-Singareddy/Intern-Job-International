package com.example.apple.interninternational.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.apple.interninternational.Fragment.OppDetailsDescFragment;
import com.example.apple.interninternational.Fragment.OppDetailsFullInfoFragment;
import com.example.apple.interninternational.Fragment.OppDetailsJobDetFragment;
import com.example.apple.interninternational.Fragment.OppDetailsPicsFragment;
import com.example.apple.interninternational.Fragment.OppDetailsRequireFragment;
import com.example.apple.interninternational.Fragment.OppDetailsSkillsFragment;
import com.example.apple.interninternational.Fragment.OppDetailsSummaryFragment;

public class OppDetailsFullInfoPagerAdapter extends FragmentStatePagerAdapter {

    /**
     * Simple constructor
     * @param fm
     */
    public OppDetailsFullInfoPagerAdapter(FragmentManager fm) {
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
            return new OppDetailsJobDetFragment();
        }
        else if (position == 1){
            return new OppDetailsDescFragment();
        }
        else if (position==2){
            return new OppDetailsSkillsFragment();
        }
        else if (position==3){
            return new OppDetailsRequireFragment();
        }
        else if (position==4){
            return new OppDetailsPicsFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Job Details";
        }
        else if (position==1){
            return "Description";
        }
        else if (position==2){
            return "Skills";
        }
        else if (position==3){
            return "Requirements";
        }
        else if (position==4){
            return "Pictures";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 5;
    }
}
