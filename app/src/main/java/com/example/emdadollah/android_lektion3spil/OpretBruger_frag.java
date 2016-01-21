package com.example.emdadollah.android_lektion3spil;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * IKKE SYNLIG PÅ TELEFONEN
 *
 * KUN FOR UDVIKLER
 */

public class OpretBruger_frag extends Fragment implements View.OnClickListener {
    DbHelper myDbhelper;

    EditText username;
    EditText score;
    Button submit;
    Button viewAllbot;
    Button update;
    Button delete;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        myDbhelper = new DbHelper(this.getActivity());

        View rod = i.inflate(R.layout.opretbruger, container, false);


        username = (EditText) rod.findViewById(R.id.username);
        score = (EditText) rod.findViewById(R.id.score);

        submit = (Button) rod.findViewById(R.id.submit_button);
        viewAllbot = (Button) rod.findViewById(R.id.viewAll);
        update = (Button) rod.findViewById(R.id.update_button);
        delete = (Button) rod.findViewById(R.id.delete_button);


        submit.setOnClickListener(this);
        viewAllbot.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == submit) {
            submit();
        }
        if (v == viewAllbot) {
            viewAll();
        }
        if (v == update) {
            updateAll();
        }
        if (v == delete) {
            deleteData();
        }

    }

    public void deleteData() {
        Integer deletedRows = myDbhelper.deleteData(username.getText().toString());
        if (deletedRows > 0) {
            System.out.println("Data deleted");
            Toast.makeText(getActivity(), "Spiller slettet!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Data not deleted");
            Toast.makeText(getActivity(), "Spiller ikke slettet :(", Toast.LENGTH_LONG).show();
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

    public void updateAll() {
        boolean isUpdatet = myDbhelper.updateData(username.getText().toString(), score.getText().toString());
        if (isUpdatet) {
            System.out.println("Data Inserted");
            Toast.makeText(getActivity(), "Spiller gemt!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Data not Inserted");
            Toast.makeText(getActivity(), "Spiller ikke gemt :(", Toast.LENGTH_LONG).show();
        }
    }


    public void submit() {
        boolean isInserted = myDbhelper.insertData(username.getText().toString(), score.getText().toString());
        if (isInserted) {
            System.out.println("Data Inserted");
            Toast.makeText(getActivity(), "Spiller gemt!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Data not Inserted");
            Toast.makeText(getActivity(), "Spiller ikke gemt :(", Toast.LENGTH_LONG).show();
        }
    }
}