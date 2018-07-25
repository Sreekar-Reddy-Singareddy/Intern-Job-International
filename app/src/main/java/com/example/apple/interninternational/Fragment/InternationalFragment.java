package com.example.apple.interninternational.Fragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
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
import com.example.apple.interninternational.DataHolders.InternationalCountry;
import com.example.apple.interninternational.R;

import java.util.ArrayList;
import java.util.HashMap;

public class InternationalFragment extends Fragment implements View.OnClickListener {

    /**
     * These static objects hold the static data related to the
     */
    public static HashMap<String,InternationalCountry> countriesData = new HashMap<>();
    public static String SELECTED_COUNTRY = "";

    /**
     * Country codes
     */
    public static final String CHINA = "CHINA";
    public static final String INDIA = "INDIA";
    public static final String GERMANY = "GERMANY";
    public static final String IJ = "IJ";

    // When the main list is loaded, the static is all loaded
    // objects of InternationalCountry class are created
    static {
        Resources resources = HomeScreen.HOMESCREEN_REFERENCE.getResources();
        String whatTitle = resources.getString(R.string.what_title);
        String whoTitle = resources.getString(R.string.who_title);
        String whenTitle = resources.getString(R.string.when_title);
        String [] whatContentArray = resources.getStringArray(R.array.what_content);
        String [] whoContentArray = resources.getStringArray(R.array.who_content);
        String [] whenContentArray = resources.getStringArray(R.array.when_content);
        String [] mainDescArray = resources.getStringArray(R.array.main_desc);
        String [] priceArray = resources.getStringArray(R.array.internship_price);
        String [] brochureArray = resources.getStringArray(R.array.international_brochures);
        String [] imagesUrlsChina = resources.getStringArray(R.array.china_images);
        String [] imagesUrlsIndia = resources.getStringArray(R.array.india_images);
        String [] imagesUrlsGermany = resources.getStringArray(R.array.germany_images);
        String [] imagesUrlsIJ = resources.getStringArray(R.array.ij_images);
        // China
        InternationalCountry china = new InternationalCountry(whatTitle,whoTitle,whenTitle,
                whatContentArray[0],whoContentArray[0],whenContentArray[0],
                mainDescArray[0],priceArray[0],brochureArray[0],imagesUrlsChina);
        // India
        InternationalCountry india = new InternationalCountry(whatTitle,whoTitle,whenTitle,
                whatContentArray[1],whoContentArray[1],whenContentArray[1],
                mainDescArray[1],priceArray[1],brochureArray[1],imagesUrlsIndia);
        // Germany
        InternationalCountry ij = new InternationalCountry(whatTitle,whoTitle,whenTitle,
                whatContentArray[2],whoContentArray[2],whenContentArray[2],
                mainDescArray[2],priceArray[2],brochureArray[2],imagesUrlsGermany);
        // I and J
        InternationalCountry germany = new InternationalCountry(whatTitle,whoTitle,whenTitle,
                whatContentArray[3],whoContentArray[3],whenContentArray[3],
                mainDescArray[3],priceArray[3],brochureArray[3],imagesUrlsIJ);
        countriesData.put(CHINA,china);
        countriesData.put(INDIA,india);
        countriesData.put(GERMANY,germany);
        countriesData.put(IJ,ij);
    }

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
        InternationalDetailsFragment internationalDetailsFragment = new InternationalDetailsFragment();
        HomeScreen.customReplaceFragment(R.id.act_home_screen_fl_frag,internationalDetailsFragment,"ShowInternationalDetails",true);
        switch (v.getId()) {
            case R.id.frag_international_screen_cv_india:
                Toast.makeText(getContext(),"India",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                SELECTED_COUNTRY = INDIA;
                break;
            case R.id.frag_international_screen_cv_china:
                Toast.makeText(getContext(),"China",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                SELECTED_COUNTRY = CHINA;
                break;
            case R.id.frag_international_screen_cv_germany:
                Toast.makeText(getContext(),"Germany",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                SELECTED_COUNTRY = GERMANY;
                break;
            case R.id.frag_international_screen_cv_ij:
                Toast.makeText(getContext(),"I&J",Toast.LENGTH_SHORT).show();
                HomeScreen.shouldShowDownloadIcon = true;
                SELECTED_COUNTRY = IJ;
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

