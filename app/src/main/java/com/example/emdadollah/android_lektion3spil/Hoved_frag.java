package com.example.emdadollah.android_lektion3spil;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by Emdadollah on 16-11-2015.
 */
public class Hoved_frag extends Fragment implements View.OnClickListener {

    Button opretSpiller;
    Button spil;
    Button score;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        View rod = i.inflate(R.layout.tre_knapper, container, false);

        opretSpiller = (Button) rod.findViewById(R.id.opretSpiller);
        relativeLayout = (RelativeLayout) rod.findViewById(R.id.relative);


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

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new OpretBruger_frag())
                    .addToBackStack(null)
                    .commit();

        }

        if (v == spil) {
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

            // Fragment fragment = new GalgelegLet_frag();
            getFragmentManager().beginTransaction()
                    //.replace(R.id.fragment, new GalgelegLet_frag())
                    .replace(R.id.fragment, new Svaerhedsgrad_frag())
                    .addToBackStack(null)
                    .commit();

        }


        if (v == score) {


            Fragment fragment = new Liste_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)  // tom container i layout
                    .addToBackStack(null)
                    .commit();

        }

    }
}
