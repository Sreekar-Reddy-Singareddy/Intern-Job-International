package com.example.apple.interninternational.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.interninternational.Beans.Branch;
import com.example.apple.interninternational.Beans.Duration;
import com.example.apple.interninternational.Beans.Location;
import com.example.apple.interninternational.R;

import java.util.ArrayList;

public class FilterInputsAdapter extends RecyclerView.Adapter<FilterInputsAdapter.FilterInputsViewHolder> {

    // Global properties\
    private Context context;
    private ArrayList data;
    private LayoutInflater inflater = null;
    private String whatInput;
    private int customLayoutCode;

    /**
     * Constructor that constrcuts the adapter based on the inputs given
     * @param context: context of the activity using the adapter
     * @param data: data to be binded to the recycler view
     * @param whatInput: which type of user input is being populated
     * @param customLayoutCode: custom layout xml file's resource id
     */
    public FilterInputsAdapter(Context context, ArrayList data, String whatInput, int customLayoutCode){
        this.context = context;
        this.data = data;
        this.whatInput = whatInput;
        this.customLayoutCode = customLayoutCode;
    }

    /**
     * Single view holder class that can create custom items for Branch, Location and Duration
     * For every item populated in the recycler view, this class's constructor creates the appropriate
     * view holder and binds the apt sub views in it
     * Ex: One type of view holder binds all the subviews of the branch such as ImageView, TextView
     * This view holder object can be used to bind the data to the list item
     */
    public class FilterInputsViewHolder extends RecyclerView.ViewHolder {

        // Global varibales of the class
        private ImageView branchImage, locationImage;
        private TextView branchName, branchCount, locationName, locationCount, durationName, durationCount;

        public FilterInputsViewHolder(View itemView, String whatInput) {
            super(itemView);
            if (whatInput.equals("Branch")){
                branchImage = (ImageView)itemView.findViewById(R.id.custom_branch_cell_iv_image);
                branchName = (TextView)itemView.findViewById(R.id.custom_branch_cell_tv_branch_name);
                branchCount = (TextView)itemView.findViewById(R.id.custom_branch_cell_tv_count);
            }
            else if (whatInput.equals("Location")){
                locationCount = (TextView) itemView.findViewById(R.id.custom_location_cell_tv_count);
                locationName = (TextView) itemView.findViewById(R.id.custom_location_cell_tv_place);
                locationImage = (ImageView) itemView.findViewById(R.id.custom_location_cell_iv_image);
            }
            else if (whatInput.equals("Duration")){
                durationName = (TextView) itemView.findViewById(R.id.custom_duration_cell_tv_duration);
                durationCount = (TextView) itemView.findViewById(R.id.custom_duration_cell_tv_count);
            }
        }
    }

    /**
     * Creates a view holder instance for every item in the list
     * Uses the view holder inner class defined above
     * View holder creation creates objects for all (branch, location, duration)
     * @param parent: The parent which is going to hold the view holder
     * @param viewType: DO NOT KNOW at the point of writing this code
     * @return
     */
    @Override
    public FilterInputsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Check if the inflater is already created
        if (this.inflater == null){
            this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        View holderView = inflater.inflate(this.customLayoutCode,parent,false);
        FilterInputsViewHolder holder = new FilterInputsViewHolder(holderView,this.whatInput);
        System.out.println("ViewHolder: "+holder);
        return holder;
    }

    /**
     * Binds the view holder object to the data of the corresponding index
     * @param holder: the view holder object that holds all the details about the sub view instances
     * @param position: position of the item in the recycler view. Helpful to get the correct data
     */
    @Override
    public void onBindViewHolder(FilterInputsViewHolder holder, int position) {
        /**
         * Distinuish among the type of list that has called the adapter
         */
        if (this.whatInput.equals("Branch")){
            Branch branch = (Branch) data.get(position);
            holder.branchName.setText(branch.getName());
            holder.branchCount.setText(branch.getId());
            holder.branchImage.setImageResource(R.drawable.ic_launcher_background);
        }
        else if (this.whatInput.equals("Location")){
            Location location = (Location) data.get(position);
            holder.locationImage.setImageResource(location.getImageId());
            holder.locationName.setText(location.getName());
            holder.locationCount.setText(String.valueOf(location.getCount()));
        }
        else if(this.whatInput.equals("Duration")){
            Duration duration = (Duration) data.get(position);
            holder.durationName.setText(duration.getDuration());
            holder.durationCount.setText(String.valueOf(duration.getCount()));
        }
    }

    /**
     * Returns the number of items in the data source
     * For the reference of the recycler view functioning
     * @return
     */
    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
