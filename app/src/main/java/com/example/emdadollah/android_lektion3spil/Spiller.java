package com.example.emdadollah.android_lektion3spil;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Mourhaf on 08-01-2016.
 */

@ParseClassName("Spiller")
public class Spiller extends ParseObject {
    public String getSpillerNavn() {
        return getString("Spiller_navn");
    }

    public void setSpillerNavn(String navn){
        put("Spiller_navn",navn);
    }

    @Override
    public String toString(){
        return getString("Spiller_navn");
    }
}
