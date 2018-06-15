package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.apple.interninternational.Adapters.SkillsListAdapter;
import com.example.apple.interninternational.R;

public class SkillsDevFragment extends Fragment {

    // UI Properties
    private View mainView;
    private ExpandableListView skillsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_skills_dev_list,container,false);
        initialiseUi();
        return mainView;
    }

    /**
     * Initialises the fragment
     */
    private void initialiseUi() {
        skillsList = (ExpandableListView) mainView.findViewById(R.id.frag_skills_dev_list_elv_skills_list);
        // Create adapter and set the adapter for the skills list
        SkillsListAdapter adapter = new SkillsListAdapter(getContext());
        skillsList.setAdapter(adapter);
    }
}
