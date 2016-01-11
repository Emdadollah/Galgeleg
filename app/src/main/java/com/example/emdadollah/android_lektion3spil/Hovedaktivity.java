package com.example.emdadollah.android_lektion3spil;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.Button;


public class Hovedaktivity extends Activity {
    DbHelper myDbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedaktivity);
        myDbhelper = new DbHelper(this);




        // jeg starter min hovedfrag
        if (savedInstanceState == null) {
            Fragment fragment = new Hoved_frag();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)  // tom container i layout
                    .addToBackStack(null)
                    .commit();
        }

    }


}
