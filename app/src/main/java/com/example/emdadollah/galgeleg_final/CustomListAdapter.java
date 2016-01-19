package com.example.emdadollah.galgeleg_final;

/**
 * Created by Emdadollah on 19-01-2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    Context c;
    ArrayList<String> names;
    ArrayList<String> scores;
    LayoutInflater inflater;

    public CustomListAdapter(Context context, ArrayList<String> names, ArrayList<String> scores) {
        super(context, R.layout.rowmodel,names);
        // TODO Auto-generated constructor stub

        this.c = context;
        this.names =names;
        this.scores = scores;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){

            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.rowmodel,null);
        }
        TextView nametv =(TextView) convertView.findViewById(R.id.players);

        TextView scoretv =(TextView) convertView.findViewById(R.id.score);

        nametv.setText(names.get(position));
        scoretv.setText("Score: "+scores.get(position));


        return convertView;
    }
}
