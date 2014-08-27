package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Micah on 8/1/2014.
 */
public class PositionAvailableView extends Activity {
    // Declare Variables
    String boatImage;
    String positions;
    String experiencePref;
    String departure;
    String destination;
    String boatType;
    String email;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.list_item_position_available_search);

        Intent i = getIntent();
        // Get the result of boatImage
        boatImage = i.getStringExtra("boatImage");
        // Get the result of positions
        positions = i.getStringExtra("positions");
        // Get the result of experiencePref
        experiencePref = i.getStringExtra("experiencePref");
        // Get the result of departure
        departure = i.getStringExtra("departure");
        destination = i.getStringExtra("destination");
        boatType = i.getStringExtra("boatType");
        email = i.getStringExtra("email");

        // Locate the TextViews in list_item_position_available_search.xmlrch.xml
        TextView txtpositions = (TextView) findViewById(R.id.crewWanted);
        TextView txtexperiencePref = (TextView) findViewById(R.id.experiencePref);
        TextView txtdeparture = (TextView) findViewById(R.id.departure);
        TextView txtdestination = (TextView) findViewById(R.id.destination);
        TextView txtboattype= (TextView) findViewById(R.id.boatType);
        TextView txtemail = (TextView) findViewById(R.id.email);

        // Locate the ImageView in list_item_position_available_search.xmlrch.xml
        ImageView imgboat = (ImageView) findViewById(R.id.boatImage);

        // Set results to the TextViews
        txtpositions.setText(positions);
        txtexperiencePref.setText(experiencePref);
        txtdeparture.setText(departure);
        txtdestination.setText(destination);
        txtboattype.setText(boatType);
        txtemail.setText(email);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(boatImage, imgboat);

        /*txtemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(v);
            }
        });*/



        imgboat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullBoat(v);
            }
        });
    }

    /*public void composeEmail (View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String address = email;
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "E-Sloop post interest");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }*/
    public void showFullBoat(View v) {
        Intent intent = new Intent(this, BoatViewActivity.class);
        intent.putExtra("boatImage", boatImage);
        startActivity(intent);

   }
}
