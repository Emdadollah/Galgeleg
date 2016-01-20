package com.example.emdadollah.android_lektion3spil;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class Hovedaktivity extends Activity{
    public static boolean isConnected = true;
    static Activity a;
     BroadcastReceiver reciever = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("xxx", this + " " + intent);
             System.out.println("INTENT!! " + intent);
            // Se http://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html
            // for flere muligheder
           isConnected = forbindelse();
            Toast.makeText(context, "Ændring registreret.  Forbindelse? " + isConnected, Toast.LENGTH_LONG).show();

        }
    };

    boolean forbindelse() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedaktivity);
        a= this;

        isConnected = forbindelse();
        IntentFilter i = new IntentFilter();
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(reciever, i);
        //Toast.makeText(this, "Registreret", Toast.LENGTH_LONG).show();


        // jeg starter min hovedfrag
        if (savedInstanceState == null) {
            if(!isConnected){
                Toast.makeText(this,"Ingen netværksforbindelse!",Toast.LENGTH_LONG).show();
            }
            Fragment fragment = new Hoved_frag();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)  // tom container i layout
                            //.addToBackStack(null)
                    .commit();
        }
    }
}
