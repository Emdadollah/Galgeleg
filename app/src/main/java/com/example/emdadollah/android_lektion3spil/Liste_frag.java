package com.example.emdadollah.android_lektion3spil;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

// array af options

public class Liste_frag extends DialogFragment implements OnItemClickListener, View.OnClickListener {

    ListView list;

    String[] players ={"Emdah", "Kim", "Jonas", "Uffe den spage", "Jasser", "Daggar"};

    int[] images={
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
            R.drawable.pers1,
    };


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        View rod = i.inflate(R.layout.dialog, container, false);

        list= (ListView) rod.findViewById(R.id.list);

        //getDialog().setTitle("spiller");

        Adapter adapter=new CustomListAdapter(getActivity(), players, images);

        list.setAdapter((ListAdapter) adapter);

        list.setOnItemClickListener(this);

        return rod;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String Slecteditem= players[+position];
        Toast.makeText(getActivity(), Slecteditem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}
