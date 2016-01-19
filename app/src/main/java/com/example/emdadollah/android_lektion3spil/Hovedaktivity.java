package com.example.emdadollah.android_lektion3spil;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Hovedaktivity extends Activity {


    static BroadcastReceiver reciever = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("xxx", this + " " + intent);
            Toast.makeText(context, this + " " + intent, Toast.LENGTH_LONG).show();
            System.out.println("INTENT!! " + intent);
            // Se http://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html
            // for flere muligheder

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedaktivity);

        IntentFilter i = new IntentFilter();
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(reciever, i);
        Toast.makeText(this, "Registreret", Toast.LENGTH_LONG).show();


        // jeg starter min hovedfrag
        if (savedInstanceState == null) {
            Fragment fragment = new Hoved_frag();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)  // tom container i layout
                            //.addToBackStack(null)
                    .commit();
        }
    }
}
