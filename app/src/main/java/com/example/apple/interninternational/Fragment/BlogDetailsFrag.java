package com.example.apple.interninternational.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.apple.interninternational.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class BlogDetailsFrag extends Fragment implements View.OnClickListener {

    // UI Properties
    private View mainView;
    private RoundedImageView profileDp;
    private Button comment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.blog_details,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        comment = (Button) mainView.findViewById(R.id.blog_details_bt_comment);
        profileDp = (RoundedImageView) mainView.findViewById(R.id.blog_details_riv_profiledp);
        comment.setOnClickListener(this);
        profileDp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.blog_details_bt_comment){
            Toast.makeText(getContext(),"Commented",Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.blog_details_riv_profiledp) {
            Toast.makeText(getContext(),"Author profile...",Toast.LENGTH_SHORT).show();
            // Load the author profile in a dialog box
            View profileView = getLayoutInflater().inflate(R.layout.blog_details_author_profile,null);
            AlertDialog.Builder profileDialog = new AlertDialog.Builder(getContext());
            profileDialog.setView(profileView);
            profileDialog.create().show();
        }
    }
}
