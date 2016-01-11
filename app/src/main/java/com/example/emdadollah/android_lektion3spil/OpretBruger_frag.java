package com.example.emdadollah.android_lektion3spil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * Created by Emdadollah on 03-11-2015.
 */
public class OpretBruger_frag extends Fragment implements View.OnClickListener {


    EditText username;
    EditText password;
    Button submit;

   // public ParseUser User = user.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {

        Parse.initialize(this.getActivity());
/**
 ParseObject testObject = new ParseObject("TestObject");
 testObject.put("foo", "bar");
 testObject.saveInBackground();
 */

        View rod = i.inflate(R.layout.opretbruger, container, false);


        username = (EditText) rod.findViewById(R.id.username);
        password = (EditText) rod.findViewById(R.id.password1);
        submit = (Button) rod.findViewById(R.id.submit_button);

        submit.setOnClickListener(this);


        return rod;
    }


    @Override
    public void onClick(View view) {
        Logik.user = new ParseUser();

        Logik.user.setUsername(username.getText().toString());
        Logik.user.setPassword(password.getText().toString());

        Logik.user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //vis fejl
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    //start intent

                    Intent intent = new Intent(getActivity(), Hovedaktivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //user.logInInBackground(username.getText().toString(), password.getText().toString());
                    //user.getCurrentUser().setEmail("muddi@dk.dk");
                }
            }
        });


    }
}
