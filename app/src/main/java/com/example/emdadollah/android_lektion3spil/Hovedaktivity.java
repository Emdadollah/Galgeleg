package com.example.emdadollah.android_lektion3spil;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class Hovedaktivity extends Activity { //implements Runnable

    //static Hovedaktivity hovedaktivitet = null;
    //Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedaktivity);


        if (savedInstanceState == null) {
            Fragment fragment = new Hoved_frag();
            getFragmentManager().beginTransaction()
                    //.replace(R.id.fragment, new Hoved_frag())
                    .add(R.id.fragment, fragment)  // tom container i layout
                    .addToBackStack(null)
                    .commit();
            //    handler.postDelayed(this, 3000); // <1> Kør run() om 3 sekunder
        }
        // hovedaktivitet = this;
    }


/**
 @Override public void run() {

 overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

 //hovedaktivitet.finish();  // <2> Luk velkomsaktiviteten
 // hovedaktivitet = null;

 }

 public void finish() {
 super.finish();
 handler.removeCallbacks(this);
 }
 */
}
