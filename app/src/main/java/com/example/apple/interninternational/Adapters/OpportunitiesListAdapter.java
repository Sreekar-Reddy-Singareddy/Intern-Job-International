package com.example.apple.interninternational.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.interninternational.Beans.Opportunity;
import com.example.apple.interninternational.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OpportunitiesListAdapter extends RecyclerView.Adapter<OpportunitiesListAdapter.OpportunitiesListViewHolder> {

    // Properties of Adapter
    private Context context;
    private ArrayList<Opportunity> data;
    private LayoutInflater inflater;

    // Simple constructor for the adapter
    public OpportunitiesListAdapter(Context context, ArrayList<Opportunity> data, LayoutInflater inflater) {
        this.context = context;
        this.data = data;
        this.inflater = inflater;
    }

    /**
     * Creates the view holder instance with the help of the apt class
     * and returns the instance. This view holder will be used to display
     * items of the recycler view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public OpportunitiesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = inflater.inflate(R.layout.opportunity_list_cell_layout,parent,false);
        OpportunitiesListViewHolder holder = new OpportunitiesListViewHolder(holderView);
        return holder;
    }

    /**
     * Gets the view holder instance created in the above method
     * and uses it to bind the corresponding data
     * @param holder: view holder instance created above
     * @param position: position of the item in the recycler view
     */
    @Override
    public void onBindViewHolder(OpportunitiesListViewHolder holder, int position) {
        holder.name.setText(data.get(position).getCompanyName());
        holder.location.setText(data.get(position).getLocation());
    }

    /**
     * The number of items in the recycler view
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * View holder class creates the view holder instance
     * All the subviews are also created based on the custom layout xml file
     */
    public class OpportunitiesListViewHolder extends RecyclerView.ViewHolder {

        // Subview properties
        private ImageView image;
        private TextView name, location, duration;

        public OpportunitiesListViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.opp_list_cell_layout_iv_image);
            name = (TextView) itemView.findViewById(R.id.opp_list_cell_layout_tv_name);
            location = (TextView) itemView.findViewById(R.id.opp_list_cell_layout_tv_loaction);
            duration = (TextView) itemView.findViewById(R.id.opp_list_cell_layout_tv_duration);
        }
    }
}
