package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.AboutProfilesAdapter;
import com.example.apple.interninternational.Beans.Profile;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Utilities.ProfilesUtils;

import java.util.ArrayList;

public class AboutFragment extends Fragment {

    private View mainView;
    private RecyclerView profilesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_about_screen,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        profilesList = (RecyclerView) mainView.findViewById(R.id.frag_about_screen_rv_profile_list);
        // Create and set adapter for the recycler view
        ArrayList<Profile> profileArrayList = ProfilesUtils.getProfileData();
        AboutProfilesAdapter adapter = new AboutProfilesAdapter(getContext(), HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater(),profileArrayList);
        profilesList.setAdapter(adapter);
        // Create and set layout manager for the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        profilesList.setLayoutManager(layoutManager);
    }
}
