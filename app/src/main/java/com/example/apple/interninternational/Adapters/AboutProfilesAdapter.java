package com.example.apple.interninternational.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.interninternational.Beans.Profile;
import com.example.apple.interninternational.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AboutProfilesAdapter extends RecyclerView.Adapter<AboutProfilesAdapter.AboutProfilesViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Profile> profileArrayList;

    public AboutProfilesAdapter(Context context, LayoutInflater inflater, ArrayList<Profile> profileArrayList) {
        this.context = context;
        this.inflater = inflater;
        this.profileArrayList = profileArrayList;
    }

    public class AboutProfilesViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView image;
        private TextView name, role;

        public AboutProfilesViewHolder(View itemView) {
            super(itemView);
            image = (RoundedImageView) itemView.findViewById(R.id.about_list_cell_iv_image);
            name = (TextView) itemView.findViewById(R.id.about_list_cell_tv_name);
            role = (TextView) itemView.findViewById(R.id.about_list_cell_tv_role);
        }
    }

    @Override
    public AboutProfilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View profileView = inflater.inflate(R.layout.about_list_cell,parent,false);
        AboutProfilesViewHolder holder = new AboutProfilesViewHolder(profileView);
        return holder;
    }

    @Override
    public void onBindViewHolder(AboutProfilesViewHolder holder, int position) {
        Profile tempProfile = profileArrayList.get(position);
        holder.image.setImageResource(tempProfile.getImageId());
        holder.name.setText(tempProfile.getName());
        holder.role.setText(tempProfile.getRole());
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
