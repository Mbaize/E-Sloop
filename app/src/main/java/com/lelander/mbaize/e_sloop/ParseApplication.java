package com.lelander.mbaize.e_sloop;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Micah on 8/1/2014.
 */



public class ParseApplication extends Application {

    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "6G1HYLdyVlXlDVubmd1lARmdpYWgo8I9a62oSXGK", "SRYNStFiHicEuU71LphkRfOCB9Hu6EqMjc5oBUZ1");

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        preferences = getSharedPreferences("com.lelander.mbaize.e_sloop", Context.MODE_PRIVATE);
    }

}