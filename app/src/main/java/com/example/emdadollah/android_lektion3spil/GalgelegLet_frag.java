package com.example.emdadollah.android_lektion3spil;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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

public class GalgelegLet_frag extends Fragment implements View.OnClickListener {

    // laver et kald til klassen Galgelogik så jeg kan bruge klassens metoder
    static Galgelogik galgelogik = new Galgelogik();
    DbHelper myDbhelper;

    ImageView imgview;
    private TextView tvinfo;
    private Button check;
    private EditText et;
    String ord;
    String currentBruger;
    String gætbogstav;
    private TextView tvinfo2;
    Button genstart;

    private Button a;
    private Button b;
    private Button c;
    private Button d;
    private Button e;
    private Button f;
    private Button g;
    private Button h;
    private Button i;
    private Button j;
    private Button k;
    private Button l;
    private Button m;
    private Button n;
    private Button o;
    private Button p;
    private Button q;
    private Button r;
    private Button s;
    private Button t;
    private Button u;
    private Button v;
    private Button w;
    private Button x;
    private Button y;
    private Button z;
    private Button æ;
    private Button ø;
    private Button å;



    private static final String tag = "Dr server";


    @Override
    public View onCreateView(LayoutInflater in, ViewGroup container, Bundle savedInstanceState) {
        myDbhelper = new DbHelper(this.getActivity());
        View rod = in.inflate(R.layout.activity_spil, container, false);

        imgview = (ImageView) rod.findViewById(R.id.Imgview);
        // sætter første billed ind i imageview.
        imgview.setImageResource(R.drawable.galge);

        tvinfo = (TextView) rod.findViewById(R.id.th);
        tvinfo2 = (TextView) rod.findViewById(R.id.tv2);
        check = (Button) rod.findViewById(R.id.check);
        genstart = (Button) rod.findViewById(R.id.genstart);
        et = (EditText) rod.findViewById(R.id.editText);

        a = (Button) rod.findViewById(R.id.a);
        b = (Button) rod.findViewById(R.id.b);
        c = (Button) rod.findViewById(R.id.c);
        d = (Button) rod.findViewById(R.id.d);
        e = (Button) rod.findViewById(R.id.e);
        f = (Button) rod.findViewById(R.id.f);
        g = (Button) rod.findViewById(R.id.g);
        h = (Button) rod.findViewById(R.id.h);
        i = (Button) rod.findViewById(R.id.i);
        j = (Button) rod.findViewById(R.id.j);
        k = (Button) rod.findViewById(R.id.k);
        l = (Button) rod.findViewById(R.id.l);


        check.setOnClickListener(this);
        genstart.setOnClickListener(this);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);
        i.setOnClickListener(this);
        j.setOnClickListener(this);
        k.setOnClickListener(this);
        l.setOnClickListener(this);
        /**
        m.setOnClickListener(this);
        n.setOnClickListener(this);
        o.setOnClickListener(this);
        p.setOnClickListener(this);
        q.setOnClickListener(this);
        r.setOnClickListener(this);
        s.setOnClickListener(this);
        t.setOnClickListener(this);
        u.setOnClickListener(this);
        v.setOnClickListener(this);
        w.setOnClickListener(this);
        x.setOnClickListener(this);
        y.setOnClickListener(this);
        z.setOnClickListener(this);
        æ.setOnClickListener(this);
        ø.setOnClickListener(this);
        å.setOnClickListener(this);
*/
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
                galgelogik.letteOrd();
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
        et.clearFocus();

        v1.setVisibility(v1.INVISIBLE);
        gætbogstav = v.toString();

        System.out.println("BOGSTAVET ER!!! " + v1.getText());


        // her checker vi at længden er lig med 1 bogstav når vi indtaster i vores editText.
        if (gætbogstav.length() != 1) {
            et.setError("skriv kun et bogstav");
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
        if (v == genstart) {
            // denne metode nulstiller alt i galgelogiken
            galgelogik.nulstil();

            et.setText("");
            imgview.setImageResource(R.drawable.galge);
            ord = galgelogik.getSynligtOrd();

            Toast.makeText(getActivity(), "Genstartet", Toast.LENGTH_SHORT).show();

            tvinfo2.setText("Brugte bogstaver: ");

            tvinfo.setText("Gæt Ordet : " + ord);
        }
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

}