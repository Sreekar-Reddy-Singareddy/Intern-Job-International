package com.example.apple.interninternational.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Adapters.FilterInputsAdapter;
import com.example.apple.interninternational.Beans.Branch;
import com.example.apple.interninternational.R;
import com.example.apple.interninternational.Services.NationalLoader;
import com.example.apple.interninternational.Utilities.NationalFragUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.ArrayList;

public class BranchListFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    // UI Properties
    private RecyclerView recyclerView;
    private View mainView;

    // Data Properties
    private ArrayList<Branch> branches = new ArrayList<>();
    FilterInputsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.list_input_filter,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        Bundle bundle = new Bundle();
        bundle.putString("FilterType","Branches");

        // Get the branch data to load into recycler view
        if (getLoaderManager().getLoader(309) == null) {
            // No loader exists with this id, so create one
            NationalLoader branchLoader = (NationalLoader) getLoaderManager().initLoader(309, bundle, this);
            branchLoader.forceLoad();
        }
        else {
            // Loader already exists with this id, so just restart it
            getLoaderManager().restartLoader(309,bundle,this).forceLoad();
        }

        recyclerView = (RecyclerView) mainView.findViewById(R.id.list_input_filter_rv_recycleview);
        /**
         * Create instance of layout manager and also adapter
         * Adapter helps in binding the data and recycler view
         * Layout Manager helps in laying out the recycler view either linear or grid style
         */
        adapter = new FilterInputsAdapter(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(),branches,"Branch",R.layout.custom_branch_cell);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * This method takes JSON string and converts it to
     * apt java beans
     * In this case, it converts the string into ArrayList<Branch>
     * @param jsonString
     */
    private void convertJson (String jsonString) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(jsonString).getAsJsonArray();
        for (JsonElement element : array) {
            JsonObject obj = element.getAsJsonObject();
            String name = obj.get("name").getAsString();
            int id = obj.get("id").getAsInt();
            int opps = obj.get("noOfOpportunities").getAsInt();
            boolean flag = obj.get("isOpen").getAsBoolean();

            Branch branch = new Branch(name, id, opps, flag);
            branches.add(branch);
        }
    }

    /**
     * Loader callback methods implementation
     */
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new NationalLoader(getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        // By now data has come back from loader in the form of JSON
        // Use this JSON and convert it to the java beans
        convertJson(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        loader.reset();
    }
}
