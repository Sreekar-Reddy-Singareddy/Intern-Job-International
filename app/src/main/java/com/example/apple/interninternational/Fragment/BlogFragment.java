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
import com.example.apple.interninternational.Adapters.BlogListAdapter;
import com.example.apple.interninternational.R;

public class BlogFragment extends Fragment {

    // UI properties
    private View mainView;
    private RecyclerView blogsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_blog_screen,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        blogsList = (RecyclerView) mainView.findViewById(R.id.frag_blog_screen_rv_blogs_list);
        // Create adapter for the recycler view and set it
        BlogListAdapter adapter = new BlogListAdapter(HomeScreen.HOMESCREEN_REFERENCE.getLayoutInflater(),getContext());
        blogsList.setAdapter(adapter);
        // Create layout manager for the recycler view and set it
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        blogsList.setLayoutManager(layoutManager);
    }
}
