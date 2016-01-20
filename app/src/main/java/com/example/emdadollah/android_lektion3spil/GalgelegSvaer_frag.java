package com.example.emdadollah.android_lektion3spil;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalgelegSvaer_frag extends Fragment implements View.OnClickListener {

    // laver et kald til klassen Galgelogik så jeg kan bruge klassens metoder
    static Galgelogik galgelogik = new Galgelogik();
    DbHelper myDbhelper;

    ImageView imgview;
    private TextView tvinfo;

    String ord;
    String currentBruger;
    String gætbogstav;
    private TextView tvinfo2;


    private SensorManager manager;
    private Sensor accelerometer;
    private ShakeHandler shakeHandler;


    private Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, bogstavae, bogstavoe, bogstavaa;

    Button[] buttons = {a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, bogstavae, bogstavoe, bogstavaa};

    private static final String tag = "Dr server";


    @Override
    public View onCreateView(LayoutInflater in, ViewGroup container, Bundle savedInstanceState) {
        myDbhelper = new DbHelper(this.getActivity());
        View rod = in.inflate(R.layout.activity_spil, container, false);


        String[] ids = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "bogstavae", "bogstavoe", "bogstavaa"};

        imgview = (ImageView) rod.findViewById(R.id.Imgview);
        // sætter første billed ind i imageview.
        imgview.setImageResource(R.drawable.galge);

        tvinfo = (TextView) rod.findViewById(R.id.th);
        tvinfo2 = (TextView) rod.findViewById(R.id.tv2);


        for (int i = 0; i < buttons.length; i++) {
            int resId = getActivity().getResources().getIdentifier(ids[i], "id", "com.example.emdadollah.android_lektion3spil");
            System.out.println("RETUNER RESID : " + resId);
            buttons[i] = (Button) rod.findViewById(resId);
            buttons[i].setOnClickListener(this);

        }

        manager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeHandler = new ShakeHandler();
        shakeHandler.setOnShakeListener(new ShakeHandler.OnShakeListener() {

            @Override
            public void onShake(int count) {

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setVisibility(buttons[i].VISIBLE);
                }
                // denne metode nulstiller alt i galgelogiken
                galgelogik.nulstil();


                imgview.setImageResource(R.drawable.galge);
                ord = galgelogik.getSynligtOrd();

                Toast.makeText(getActivity(), "Genstartet", Toast.LENGTH_SHORT).show();

                tvinfo2.setText("Brugte bogstaver: ");

                tvinfo.setText("Gæt Ordet : " + ord);
            }
        });



        // denne asyntask henter ord fra DRs server som sættes ind i  array.
        System.out.println("Henter ord fra DRs server....");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    galgelogik.hentOrdFraDr();

                    return Log.d(tag, "Der er hentet data fra Dr");
                } catch (Exception e) {
                    e.printStackTrace();
                    return Log.d(tag, "Der er hentet data fra Dr" + e);
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                //et.setText("resultat: \n" + resultat);
                galgelogik.svaerOrd();
                tvinfo.setText("Gæt Ordet :" + galgelogik.getSynligtOrd());
                tvinfo2.setText("Brugte bogstaver :");
            }
        }.execute();

        // sætter den generede ord ind i textview  .

        galgelogik.logStatus();
        galgelogik.opdaterSynligtOrd();

        return rod;
    }

    @Override
    public void onClick(View v) {
        Button v1 = (Button) v;
        // her henter den indtastede input i mit Edittext og sætter det ind i gætBogstav metoden.
        galgelogik.gætBogstav(v1.getText().toString());
        //v.setVisibility(v.INVISIBLE);


        v1.setVisibility(v1.INVISIBLE);
        gætbogstav = v.toString();

        System.out.println("BOGSTAVET ER!!! " + v1.getText());

        // her checker vi at længden er lig med 1 bogstav når vi indtaster i vores editText.
        if (gætbogstav.length() != 1) {

        }
        // if (v == check) {
        // hvis den gættede ord ikke er korrekt så skal den gøre følgende
        if (galgelogik.erSidsteBogstavKorrekt() == false) {
            Toast.makeText(getActivity(), "Bogstavet er forkert", Toast.LENGTH_SHORT).show();
            // for hver gang man gætter forkert så bliver vores imageview opdateret med et nyt billed
            if (galgelogik.getAntalForkerteBogstaver() == 1) {
                imgview.setImageResource(R.drawable.forkert1);
            } else if (galgelogik.getAntalForkerteBogstaver() == 2) {
                imgview.setImageResource(R.drawable.forkert2);
            } else if (galgelogik.getAntalForkerteBogstaver() == 3) {
                imgview.setImageResource(R.drawable.forkert3);
            } else if (galgelogik.getAntalForkerteBogstaver() == 4) {
                imgview.setImageResource(R.drawable.forkert4);
            } else if (galgelogik.getAntalForkerteBogstaver() == 5) {
                imgview.setImageResource(R.drawable.forkert5);
            } else if (galgelogik.getAntalForkerteBogstaver() == 6) {
                imgview.setImageResource(R.drawable.forkert6);
                galgelogik.logStatus();
            } else if (galgelogik.erSpilletTabt()) {
                // efter den 6 gang så får man besked at man har tabt

                Toast.makeText(getActivity(), "du har tabt spillet", Toast.LENGTH_SHORT).show();
                tvinfo.setText("Ordet er : " + galgelogik.getOrdet() + "                Din score er: " + Integer.toString(galgelogik.getScore()));
                System.out.println("DIN SCORE ER NU!! " + Integer.toString(galgelogik.getScore()));
                if (currentBruger == null) {
                    showMessage("Score", "Ønsker du at gemme din score?");
                } else {
                    updateAll(currentBruger, Integer.toString(galgelogik.getScore()));
                }

            }

        } else if (galgelogik.erSidsteBogstavKorrekt() == true) {

            Toast.makeText(getActivity(), "Bogstavet er korrekt", Toast.LENGTH_SHORT).show();

            galgelogik.opdaterSynligtOrd();

            // denne metode gør de bogstaver der er korrekte synlige.
            tvinfo.setText("Gæt Ordet : " + galgelogik.getSynligtOrd());

            if (galgelogik.erSpilletVundet()) {

                galgelogik.opdaterSynligtOrd();

                Toast.makeText(getActivity(), "Du har vundet tillykke", Toast.LENGTH_SHORT).show();
                if (currentBruger == null) {
                    showMessage("Score", "Ønsker du at gemme din score?");
                } else {
                    updateAll(currentBruger, Integer.toString(galgelogik.getScore()));
                }
            }
        }
        tvinfo2.setText("Brugte bogstaver " + galgelogik.getBrugteBogstaver());
        // }

        // når der trykkes på genstart så nulstiles mit textview, imageview.
    }


    public void showMessage(String title, String Message) {
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        View subView = inflater.inflate(R.layout.dialog_layout, null);
        final EditText subEditText = (EditText) subView.findViewById(R.id.dialogEditText);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(title);
        builder.setIcon(R.drawable.forkert6);
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.setView(subView);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }
        );
        builder.setPositiveButton("Gem din score!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentBruger = subEditText.getText().toString();
                submit(currentBruger, Integer.toString(galgelogik.getScore()));
                Toast.makeText(getActivity(), subEditText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });


        builder.show();
    }

    public void submit(String spiller, String score) {
        boolean isInserted = myDbhelper.insertData(spiller, score);
        if (isInserted) {
            System.out.println("Data Inserted");
            Toast.makeText(getActivity(), "Spiller gemt!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Data not Inserted");
            Toast.makeText(getActivity(), "Spiller ikke gemt :(", Toast.LENGTH_LONG).show();
        }
    }

    public void updateAll(String spiller, String score) {
        boolean isUpdatet = myDbhelper.updateData(spiller, score);
        if (isUpdatet) {
            System.out.println("Data Inserted");
            Toast.makeText(getActivity(), "Spiller gemt!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Data not Inserted");
            Toast.makeText(getActivity(), "Spiller ikke gemt :(", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        manager.registerListener(shakeHandler, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        manager.unregisterListener(shakeHandler);
        super.onPause();
    }
}