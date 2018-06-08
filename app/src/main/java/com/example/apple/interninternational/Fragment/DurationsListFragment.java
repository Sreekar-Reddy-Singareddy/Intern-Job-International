package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.FilterInputsAdapter;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Utilities.NationalFragUtils;

public class DurationsListFragment extends Fragment {

    // UI properties
    private View mainView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.list_input_filter,container,false);
        initialiseUi();
        return mainView;
    }

    /**
     * Initialises the UI for the fragment
     */
    private void initialiseUi() {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.list_input_filter_rv_recycleview);
        /**
         * Create layout manager and adapter
         * and set them to the recycler view
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        FilterInputsAdapter adapter = new FilterInputsAdapter(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), NationalFragUtils.getDurations(),"Duration",R.layout.custom_duration_cell);
        recyclerView.setAdapter(adapter);
    }
}
