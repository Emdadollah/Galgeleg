package com.example.emdadollah.android_lektion3spil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    Context c;
    String[] players;
    int[] images;
    LayoutInflater inflater;

    public CustomListAdapter(Context context, String[] players, int[] images) {
        super(context, R.layout.rowmodel,players);
        // TODO Auto-generated constructor stub

        this.c = context;
        this.players =players;
        this.images = images;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       if(convertView==null){

           inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=inflater.inflate(R.layout.rowmodel,null);
       }
            TextView nametv =(TextView) convertView.findViewById(R.id.nameTv);

            ImageView img =(ImageView) convertView.findViewById(R.id.imageView1);

            nametv.setText(players[position]);
            img.setImageResource(images[position]);


        return convertView;
    }
}


