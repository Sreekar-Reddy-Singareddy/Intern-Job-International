package com.example.apple.interninternational.Fragment;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.R;

public class InternationalFragment extends Fragment implements View.OnClickListener {

    // UI Properties
    private View fragmentMainView;
    private CardView indiaCard, chinaCard, germanyCard, ijCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainView = inflater.inflate(R.layout.frag_international_screen,container,false);
        initialiseUi();
        return fragmentMainView;
    }

    /**
     * Initialises the fragment
     */
    @SuppressLint("RestrictedApi")
    public void initialiseUi() {
        HomeScreen.HOMESCREEN_REFERENCE.getSupportActionBar().invalidateOptionsMenu();
        indiaCard = (CardView) fragmentMainView.findViewById(R.id.frag_international_screen_cv_india);
        chinaCard = (CardView) fragmentMainView.findViewById(R.id.frag_international_screen_cv_china);
        germanyCard = (CardView) fragmentMainView.findViewById(R.id.frag_international_screen_cv_germany);
        ijCard = (CardView) fragmentMainView.findViewById(R.id.frag_international_screen_cv_ij);
        indiaCard.setOnClickListener(this);
        chinaCard.setOnClickListener(this);
        germanyCard.setOnClickListener(this);
        ijCard.setOnClickListener(this);
    }

    /**
     * Called when any of the four cards is clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,new InternationalDetailsFragment()).commit();
        switch (v.getId()) {
            case R.id.frag_international_screen_cv_india:
                Toast.makeText(getContext(),"India",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                break;
            case R.id.frag_international_screen_cv_china:
                Toast.makeText(getContext(),"China",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                break;
            case R.id.frag_international_screen_cv_germany:
                Toast.makeText(getContext(),"Germany",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                break;
            case R.id.frag_international_screen_cv_ij:
                Toast.makeText(getContext(),"I&J",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                break;
        }
        if (HomeScreen.shouldShowDownloadIcon){
            programActionBar();
        }
    }

    /**
     * This method creates a new menu bar on the host activity's
     * action bar
     */
    @SuppressLint("RestrictedApi")
    private boolean programActionBar(){
        HomeScreen.HOMESCREEN_REFERENCE.getSupportActionBar().invalidateOptionsMenu();
        return false;
    }
}

