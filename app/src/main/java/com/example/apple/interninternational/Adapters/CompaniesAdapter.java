package com.example.apple.interninternational.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.R;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder> {

    /**
     * Class for holding the individual company view
     */
    public class CompaniesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // UI properties in the custom cell
        private ImageView image;
        private TextView name;

        /**
         * Constrcutor to construct the view holder instance using a view
         */
        public CompaniesViewHolder(View companyView){
            super(companyView);
            image = (ImageView) companyView.findViewById(R.id.company_cell_riv_image);
            name = (TextView) companyView.findViewById(R.id.company_cell_tv_name);
            companyView.setOnClickListener(this);
        }

        /**
         * Called when an individual company item is clicked on the recycler view
         * This method takes the apt view that has been clicked
         * @param v
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(context,"Company Clicked "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
        }
    }

    // Properties of the adapter
    private Context context;
    private LayoutInflater inflater;

    /**
     * Normal constructor to constrcut the adapter
     */
    public CompaniesAdapter(Context context, LayoutInflater inflater){
        this.context = context;
        this.inflater = inflater;
    }

    /**
     * This method creates the view holder instance using the above inner class
     * This view holder holds a view to which the data will be binded
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CompaniesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.company_cell,parent,false);
        CompaniesViewHolder holder = new CompaniesViewHolder(view);
        return holder;
    }

    /**
     * Allows us to bind the data to the created view holders
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CompaniesViewHolder holder, int position) {

    }

    /**
     * Returns the count of the items in the recycler view
     * @return
     */
    @Override
    public int getItemCount() {
        return 20;
    }
}
