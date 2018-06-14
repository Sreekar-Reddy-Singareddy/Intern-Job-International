package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.CompaniesAdapter;
import com.example.apple.interninternational.R;

public class CompaniesFragment extends Fragment {

    // UI Properties
    private View mainView;
    private RecyclerView companiesCollection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_companies_screen,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi(){
        companiesCollection = (RecyclerView) mainView.findViewById(R.id.frag_companies_screen_rv_recyclerview);
        // Create and set an adapter for the company
        CompaniesAdapter adapter = new CompaniesAdapter(HomeScreen.HOMESCREEN_REFERENCE,HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater());
        companiesCollection.setAdapter(adapter);
        // Create and set a layout manager for the company
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeScreen.HOMESCREEN_REFERENCE,3);
        companiesCollection.setLayoutManager(gridLayoutManager);

    }
}
