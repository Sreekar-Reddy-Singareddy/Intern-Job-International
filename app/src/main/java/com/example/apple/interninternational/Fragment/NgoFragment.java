package com.example.apple.interninternational.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.interninternational.Activity.HomeScreen;
import com.example.apple.interninternational.R;

public class NgoFragment extends Fragment implements View.OnClickListener {

    // UI Properties
    private View mainView;
    private CardView ngoCard, skillsDevCard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.frag_ngo_screen,container,false);
        initialiseUi();
        return mainView;
    }

    private void initialiseUi() {
        ngoCard = (CardView) mainView.findViewById(R.id.frag_ngo_screen_ngos_card);
        skillsDevCard = (CardView) mainView.findViewById(R.id.frag_ngo_screen_skillsdev_card);
        ngoCard.setOnClickListener(this);
        skillsDevCard.setOnClickListener(this);
    }

    /**
     * Called when the user clicks either of the two cards
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.frag_ngo_screen_ngos_card) {
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "NGOs",Toast.LENGTH_SHORT).show();
            NgosListFragment ngosListFragment = new NgosListFragment();
            HomeScreen.customReplaceFragment(R.id.act_home_screen_fl_frag,ngosListFragment,"ShowNgoList",true);
        }
        else if (v.getId() == R.id.frag_ngo_screen_skillsdev_card){
            Toast.makeText(HomeScreen.HOMESCREEN_REFERENCE.getBaseContext(), "Skills Dev",Toast.LENGTH_SHORT).show();
            SkillsDevFragment skillsDevFragment = new SkillsDevFragment();
            HomeScreen.customReplaceFragment(R.id.act_home_screen_fl_frag,skillsDevFragment,"ShowSkillsList",true);
        }
    }
}
