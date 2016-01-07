package com.example.emdadollah.android_lektion3spil;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Created by Emdadollah on 03-11-2015.
 */
public class Hjaelp_frag extends Fragment {


    TextView th;
    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        Parse.initialize(this.getActivity());

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        View rod = i.inflate(R.layout.hjaelp, container, false);

        th =(TextView)rod.findViewById(R.id.th);

        return rod;


    }



}
