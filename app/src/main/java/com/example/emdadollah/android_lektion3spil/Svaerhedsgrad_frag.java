package com.example.emdadollah.android_lektion3spil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Mourhaf on 09-01-2016.
 */
public class Svaerhedsgrad_frag extends Fragment implements View.OnClickListener {

    Button letteOrd;
    Button normaleOrd;
    Button svaereOrd;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_svaerhedsgrad, container, false);
        letteOrd = (Button) rod.findViewById(R.id.letteOrdbutton);
        normaleOrd = (Button) rod.findViewById(R.id.normaleOrdbutton);
        svaereOrd = (Button) rod.findViewById(R.id.svaereOrdbutton);

        letteOrd.setOnClickListener(this);
        normaleOrd.setOnClickListener(this);


        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == letteOrd) {
            Fragment fragment = new Galgeleg_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new Galgeleg_frag())
                    .addToBackStack(null)
                    .commit();

        }
        if (v == normaleOrd) {
            Fragment fragment = new Galgeleg_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new GalgelegNormal_frag())
                    .addToBackStack(null)
                    .commit();

        }
        if (v == svaereOrd) {
            Fragment fragment = new Galgeleg_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new Galgeleg_frag())
                    .addToBackStack(null)
                    .commit();

        }
    }
}
