package com.example.apple.interninternational.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.Fragment.BlogDetailsFrag;
import com.example.apple.interninternational.R;

import org.w3c.dom.Text;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogListViewHolder> {

    // Properties of the adapter
    private LayoutInflater inflater;
    private Context context;

    // TODO: Modify the constrcutor to accept data list if any
    public BlogListAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }

    public class BlogListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // UI Sub view properties
        private TextView title, description;
        private Button readmore;

        public BlogListViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.blog_list_cell_tv_title);
            description = (TextView)itemView.findViewById(R.id.blog_list_cell_tv_desc);
            readmore = (Button)itemView.findViewById(R.id.blog_list_cell_bt_readmore);
            readmore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Read more button has been clicked
            Toast.makeText(context,"Reading more...",Toast.LENGTH_SHORT).show();
            // Take the user to the next detailed screev
            BlogDetailsFrag blogDetailsFrag = new BlogDetailsFrag();
            HomeScreen.HOMESCREEN_REFERENCE.getSupportFragmentManager().beginTransaction().replace(R.id.act_home_screen_fl_frag,blogDetailsFrag).commit();
        }
    }

    @Override
    public BlogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View blogView = inflater.inflate(R.layout.blog_list_cell,parent,false);
        BlogListViewHolder holder = new BlogListViewHolder(blogView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BlogListViewHolder holder, int position) {
        // TODO: Bind the actual data to the subviews of this view holder
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
