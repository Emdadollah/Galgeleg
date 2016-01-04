package com.example.emdadollah.android_lektion3spil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Emdadollah on 16-11-2015.
 */
public class Hoved_frag extends Fragment implements View.OnClickListener {

    Button vejledning;
    Button spil;
    Button score;
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.tre_knapper, container, false);

        vejledning = (Button) rod.findViewById(R.id.vejledning);


        spil = (Button) rod.findViewById(R.id.knap2);


       score = (Button) rod.findViewById(R.id.knap3);


        vejledning.setOnClickListener(this);
        spil.setOnClickListener(this);
        score.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {

        if(v==vejledning){

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new Hjaelp_frag())
                    .addToBackStack(null)
                    .commit();

        }

        if(v==spil){
            Fragment fragment = new Galgeleg_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new Galgeleg_frag())
                    .addToBackStack(null)
                    .commit();

        }


        if(v==score){
            Fragment fragment = new Liste_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)  // tom container i layout
                    .addToBackStack(null)
                    .commit();

        }

    }
}
