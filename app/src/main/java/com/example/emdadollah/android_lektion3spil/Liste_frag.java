package com.example.emdadollah.android_lektion3spil;

import android.app.DialogFragment;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

// array af options

public class Liste_frag extends DialogFragment implements View.OnClickListener {
    DbHelper myDbhelper;
    ListView list;
    SimpleCursorAdapter myCursorAdapter;
    ArrayAdapter myArrayAdapter;


    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> scores = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        myDbhelper = new DbHelper(this.getActivity());
        View rod = i.inflate(R.layout.dialog, container, false);

        rod.setBackgroundColor(Color.BLACK);

        list = (ListView) rod.findViewById(R.id.list);

        Cursor res = myDbhelper.getAllData();
        if (res.getCount() == 0) {

            //
        }
        else {


            while (res.moveToNext()) {
                names.add(res.getString(1));
                scores.add(res.getString(2));
            }
        }

        Adapter adapter = new CustomListAdapter(getActivity(), names, scores);

        list.setAdapter((ListAdapter) adapter);



        return rod;
    }

    /**
     * @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     * String Slecteditem = players[+position];
     * Toast.makeText(getActivity(), Slecteditem, Toast.LENGTH_SHORT).show();
     * }
     */
    @Override
    public void onClick(View v) {

    }

}