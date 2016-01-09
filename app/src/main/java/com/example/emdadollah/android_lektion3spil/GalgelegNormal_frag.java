package com.example.emdadollah.android_lektion3spil;

import android.app.Fragment;
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

public class GalgelegNormal_frag extends Fragment implements View.OnClickListener {

    // laver et kald til klassen Galgelogik så jeg kan bruge klassens metoder
    static Galgelogik galgelogik = new Galgelogik();

    ImageView imgview;
    private TextView tvinfo;
    private Button check;
    private EditText et;
    String ord;
    String gætbogstav;
    private TextView tvinfo2;
    Button genstart;

    private static final String tag = "Dr server";


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

/**
        Parse.initialize(this.getActivity());
        Logik.spiller = new ParseObject("Spiller");

        Logik.user.logInInBackground("muddi", "123", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

            }
        });
 */

        View rod = i.inflate(R.layout.activity_main, container, false);

        imgview=(ImageView)rod.findViewById(R.id.Imgview);
        // sætter første billed ind i imageview.
        imgview.setImageResource(R.drawable.galge);

        tvinfo=(TextView)rod.findViewById(R.id.th);
        tvinfo2=(TextView)rod.findViewById(R.id.tv2);
        check = (Button)rod.findViewById(R.id.check);
        genstart=(Button)rod.findViewById(R.id.genstart);
        et=(EditText)rod.findViewById(R.id.editText);


        //System.out.println("----------"+Logik.user.getCurrentUser().getObjectId()+"----------");

        check.setOnClickListener(this);
        genstart.setOnClickListener(this);

        galgelogik.logStatus();
        galgelogik.opdaterSynligtOrd();
        // sætter den generede ord ind i textview  .
        tvinfo.setText("Gæt Ordet :" + galgelogik.getSynligtOrd());
        tvinfo2.setText("Brugte bogstaver :");

       // denne asyntask henter ord fra DRs server som sættes ind i  array.
        System.out.println("Henter ord fra DRs server....");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    galgelogik.hentOrdFraDr();
                    return Log.d(tag,"Der er hentet data fra Dr");
                } catch (Exception e) {
                    e.printStackTrace();
                    return Log.d(tag,"Der er hentet data fra Dr"+e);
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                //et.setText("resultat: \n" + resultat);
            }
        }.execute();

       return rod;


    }


    @Override
    public void onClick(View v) {

        // her henter den indtastede input i mit Edittext og sætter det ind i gætBogstav metoden.
        galgelogik.gætBogstav(et.getText().toString());


        gætbogstav = et.getText().toString();
        // her checker vi at længden er lig med 1 bogstav når vi indtaster i vores editText.
        if(gætbogstav.length()!=1){
            et.setError("skriv kun et bogstav");

        }
        galgelogik.normalOrd();

      if(v==check) {
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
              }
              // efter den 6 gang så får man besked at man har tabt
              else if (galgelogik.erSpilletTabt()) {

                  Toast.makeText(getActivity(), "du har tabt spillet", Toast.LENGTH_SHORT).show();

                  tvinfo.setText("Ordet er : " + galgelogik.getOrdet());
                  //Logik.user.getCurrentUser().setEmail("hejhej");

                  //Logik.spiller.saveInBackground();

              }

          } else if (galgelogik.erSidsteBogstavKorrekt() == true) {

              Toast.makeText(getActivity(), "Bogstavet er korrekt", Toast.LENGTH_SHORT).show();

              galgelogik.opdaterSynligtOrd();

              // denne metode gør de bogstaver der er korrekte synlige.
              tvinfo.setText("Gæt Ordet : " + galgelogik.getSynligtOrd());

              if (galgelogik.erSpilletVundet()) {

                  galgelogik.opdaterSynligtOrd();

                  Toast.makeText(getActivity(), "Du har vundet tillykke", Toast.LENGTH_SHORT).show();
              }

          }


          tvinfo2.setText("Brugte bogstaver " + galgelogik.getBrugteBogstaver()+galgelogik.getAntalForkerteBogstaver());

      }

        // når der trykkes på genstart så nulstiles mit textview, imageview.
        if(v==genstart){
            // denne metode nulstiller alt i galgelogiken
            galgelogik.nulstil();

            et.setText("");
            imgview.setImageResource(R.drawable.galge);
            ord = galgelogik.getSynligtOrd();

            Toast.makeText(getActivity(), "Genstartet", Toast.LENGTH_SHORT).show();

            tvinfo2.setText("Brugte bogstaver: ");

            tvinfo.setText("Gæt Ordet : "+ord);
        }
    }
}
