package com.example.apple.interninternational.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.OpportunitiesListAdapter;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Utilities.OpportunitiesUtils;

public class OpportunityFragment extends Fragment {

    // UI Properties
    private View mainView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.opportunities_list,container,false);
        initialiseUi();
        return mainView;
    }

    /**
     * Initialisation of the user interface
     */
    private void initialiseUi(){
        recyclerView = (RecyclerView) mainView.findViewById(R.id.opportunities_list_rv_recyclerview);
        // Create adapter and set it to the recycler view
        OpportunitiesListAdapter adapter = new OpportunitiesListAdapter(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), OpportunitiesUtils.getOpportunities(),HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater());
        recyclerView.setAdapter(adapter);
        // Create layout manager and set it to the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}
