package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.R;

/**
 * Created by subbuilla on 04/06/18.
 */

public class InternDetailsPageTwo extends Fragment {

    // UI Properties
    private View fragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.international_details_page_2,container,false);
        return fragmentView;
    }
}
