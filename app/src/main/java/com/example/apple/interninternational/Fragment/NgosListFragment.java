package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.NgosListAdapter;
import com.example.apple.interninternational.R;

public class NgosListFragment extends Fragment{

    // UI Properties
    private View mainView;
    private ExpandableListView ngosList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_ngo_list,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        ngosList = (ExpandableListView) mainView.findViewById(R.id.frag_ngo_list_rv_ngos_list);
        // Create adapter and set it to the expandable list view
        NgosListAdapter adapter = new NgosListAdapter(getContext());
        System.out.println("ViewGroup Fragment: "+ngosList);
        ngosList.setAdapter(adapter);
        // Implement listener interfaces to listen to events
        ngosList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getContext(), "Group ID: "+id,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        ngosList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), "Child ID: "+id,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
