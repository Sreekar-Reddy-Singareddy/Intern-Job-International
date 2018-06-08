package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.FilterInputsPagerAdapter;
import com.example.apple.interninternational.Animations.DepthPageTransformer;
import com.example.apple.interninternational.R;

public class NationalFragment extends Fragment implements View.OnClickListener {

    // UI Properties
    private View mainView;
    private ViewPager viewPager;
    private Button skip, applyFilters;
    private ImageButton next, previous;

    /**
     * Inflates a view with the provided layout xml file
     * Returns the view to the system, so the system can draw it on window
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_national_screen,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        viewPager = (ViewPager)mainView.findViewById(R.id.frag_national_screen_vp_pager);
        // Create adapter for pager and set it
        FilterInputsPagerAdapter adapter = new FilterInputsPagerAdapter(HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager(),4);
        viewPager.setAdapter(adapter);
        // Create animation for pager and set it
        DepthPageTransformer depthPageTransformer = new DepthPageTransformer();
        viewPager.setPageTransformer(true,depthPageTransformer);
        skip = (Button) mainView.findViewById(R.id.frag_national_screen_bt_skip);
        applyFilters = (Button) mainView.findViewById(R.id.frag_national_screen_bt_applyfilters);
        next = (ImageButton) mainView.findViewById(R.id.frag_national_screen_ib_next);
        previous = (ImageButton) mainView.findViewById(R.id.frag_national_screen_ib_previous);
        skip.setOnClickListener(this);
        applyFilters.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        applyFilters.setEnabled(false);
    }

    /**
     * Called whenever any of the registered views are clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == skip.getId()){
            // TODO: Skip the current screen and take the user to opportunities list
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),"Skip",Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == applyFilters.getId()){
            // TODO: Take the user given inputs and navigate them to opportunites list
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),"Apply",Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == next.getId()){
            // TODO: Flip the page in the page viewer by 1
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),"Next",Toast.LENGTH_SHORT).show();
            if (viewPager.getCurrentItem() == 1){
                viewPager.setCurrentItem(0);
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
        else if (v.getId() == previous.getId()){
            // TODO: Flip the page in the page viewer by -1
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),"Previous",Toast.LENGTH_SHORT).show();
            if (viewPager.getCurrentItem() > 0){
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        }
    }
}
