package com.example.apple.interninternational.Adapters;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.apple.interninternational.R;

import java.util.ArrayList;

public class InternImagesAdapter extends PagerAdapter {

    private ArrayList<Bitmap> images;
    private Context context;
    private LayoutInflater inflater;

    public InternImagesAdapter (Context context, ArrayList<Bitmap> images) {
        this.context = context;
        this.images = images;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.i("No of pages", "getCount: "+images.size());
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.i("View insatance", "isViewFromObject: "+ view+ object);
        return view.equals((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("View Create", "instantiateItem: Inside " +position);
        View pageView = inflater.inflate(R.layout.international_image_cell,container,false);
        ImageView image = pageView.findViewById(R.id.international_image_cell_iv_image);
        ProgressBar progressBar = pageView.findViewById(R.id.international_image_cell_pb_progress);
        progressBar.setIndeterminate(true);
        if (images.get(position) == null) {
            Log.i("Checker", "instantiateItem: No Image at: "+position);
            // No image for this position, hence show progress bar
            progressBar.setVisibility(View.VISIBLE);
            image.setVisibility(View.INVISIBLE);
        }
        else {
            Log.i("Checker", "instantiateItem: Yes Image at: "+position);
            // Image exists for this page
            progressBar.setVisibility(View.INVISIBLE);
            image.setImageBitmap(images.get(position));
            image.setVisibility(View.VISIBLE);
        }
        ((ViewPager) container).addView(pageView, position);
        return pageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }
}
