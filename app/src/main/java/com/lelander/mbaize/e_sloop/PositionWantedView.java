package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Micah on 8/1/2014.
 */
public class PositionWantedView extends Activity {
    // Declare Variables
    String profileImage;
    String positions;
    String experienceHad;
    String departure;
    String destination;
    String boatType;
    String email;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.list_item_position_wanted_search);

        Intent i = getIntent();
        // Get the result of boatImage
        profileImage = i.getStringExtra("profileImage");
        // Get the result of positions
        positions = i.getStringExtra("positions");
        // Get the result of experiencePref
        experienceHad = i.getStringExtra("experienceHad");
        // Get the result of departure
        departure = i.getStringExtra("departure");
        destination = i.getStringExtra("destination");
        boatType = i.getStringExtra("boatType");
        email = i.getStringExtra("email");

        // Locate the TextViews in list_item_position_wanted_search.xml
        TextView txtpositions = (TextView) findViewById(R.id.list_item_position_wanted);
        TextView txtexperiencePref = (TextView) findViewById(R.id.listItemExperienceHad);
        TextView txtboatingactivity = (TextView) findViewById(R.id.listItemBoatingActivityWantedPlaceholder);
        TextView txtboattype= (TextView) findViewById(R.id.listItemWantedBoatType);
        TextView txtemail = (TextView) findViewById(R.id.listItemWantedEmail);

        // Locate the ImageView in list_item_position_available_search.xmlrch.xml
        ImageView imgprofile = (ImageView) findViewById(R.id.positionWantedProfileImage);

        // Set results to the TextViews
        txtpositions.setText(positions);
        txtexperiencePref.setText(experienceHad);

        txtboattype.setText(boatType);
        txtemail.setText(email);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(profileImage, imgprofile);
    }
    public void showFullProfilePicture(View v) {
        Intent intent = new Intent(this, ProfilePictureViewActivity.class);
        intent.putExtra("profileImage", profileImage);
        startActivity(intent);

    }
}
