package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.interninternational.R;

public class InternshipFragment extends Fragment implements View.OnClickListener {

    private CardView internationalCard, nationalCard;
    private View fragmentMainView;

    /**
     * Constructor for the fragment
     */
    public InternshipFragment() {

    }

    /**
     * This is the call back method called by the android system
     * in order to draw the view of this fragment.
     * @param inflater: helps in inflating the fragment
     * @param container: container of the fragment
     * @param savedInstanceState: any buundle data
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainView = inflater.inflate(R.layout.frag_internship_screen,container,false);
        initialiseUi();
        return fragmentMainView;
    }

    /**
     * Called to do the intial setup of the fragment
     */
    public void initialiseUi() {
        internationalCard = (CardView) fragmentMainView.findViewById(R.id.frag_internship_screen_inter_card);
        nationalCard = (CardView) fragmentMainView.findViewById(R.id.frag_internship_screen_nation_card);
        internationalCard.setOnClickListener(this);
        nationalCard.setOnClickListener(this);
    }

    /**
     * Called when either card is clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.frag_internship_screen_inter_card) {
            Toast.makeText(getContext(),"International Card",Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.frag_internship_screen_nation_card) {
            Toast.makeText(getContext(),"National Card",Toast.LENGTH_SHORT).show();
        }
    }

}
