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
import com.example.apple.interninternational.Beans.Branch;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Utilities.NationalFragUtils;

import java.util.ArrayList;

public class BranchListFragment extends Fragment {

    // UI Properties
    private RecyclerView recyclerView;
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.list_input_filter,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.list_input_filter_rv_recycleview);
        ArrayList<Object> data = NationalFragUtils.getBranches();
        /**
         * Create instance of layout manager and also adapter
         * Adapter helps in binding the data and recycler view
         * Layout Manager helps in laying out the recycler view either linear or grid style
         */
        FilterInputsAdapter adapter = new FilterInputsAdapter(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),data,"Branch",R.layout.custom_branch_cell);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
