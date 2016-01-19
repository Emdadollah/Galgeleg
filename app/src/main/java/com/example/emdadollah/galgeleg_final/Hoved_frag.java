package com.example.emdadollah.galgeleg_final;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class Hoved_frag extends Fragment implements View.OnClickListener {
    DbHelper myDbhelper;
    MediaPlayer lyd;

    Button opretSpiller;
    Button spil;
    Button score;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;



    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        myDbhelper = new DbHelper(this.getActivity());

        View rod = i.inflate(R.layout.tre_knapper, container, false);

        lyd = MediaPlayer.create(getActivity(), R.raw.thoziclick);

        relativeLayout = (RelativeLayout) rod.findViewById(R.id.relative);

        opretSpiller = (Button) rod.findViewById(R.id.opretSpiller);

        spil = (Button) rod.findViewById(R.id.knap2);

        score = (Button) rod.findViewById(R.id.knap3);

        opretSpiller.setOnClickListener(this);
        spil.setOnClickListener(this);
        score.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {

        if (v == opretSpiller) {
            lyd.start();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new OpretBruger_frag())
                    .addToBackStack(null)
                    .commit();
        }
        if (v == spil) {
            lyd.start();




            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new Svaerhedsgrad_frag())  // tom container i layout
                    .addToBackStack(null)
                    .commit();
        }


/**
 layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup, null);

 popupWindow = new PopupWindow(container, 400, 400, true);
 popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);

 container.setOnTouchListener(new View.OnTouchListener() {
@Override public boolean onTouch(View v, MotionEvent motionEvent) {
popupWindow.dismiss();
return true;
}
});*/


        if (v == score) {
            lyd.start();
            viewAll();

            /** Fragment fragment = new Liste_frag();
             getFragmentManager().beginTransaction()
             .replace(R.id.fragment, fragment)  // tom container i layout
             .addToBackStack(null)
             .commit();*/

        }
    }



    public void viewAll() {
        Cursor res = myDbhelper.getAllData();
        if (res.getCount() == 0) {
            //message
            showMessage("Error", "No data found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Spiller : " + res.getString(1) + "\n");
            buffer.append("Score : " + res.getString(2) + "\n\n");
        }
        //Show all data
        showMessage("Spiller score", buffer.toString());
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setIcon(R.drawable.forkert6);

        builder.setMessage(Message);
        builder.show();
    }
}
