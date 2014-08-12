package com.lelander.mbaize.e_sloop;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.ParseObject;
import com.parse.ParseUser;

public class PostPositionAvailable extends ActionBarActivity implements AdapterView.OnItemSelectedListener, DatePickerFragment.DateSetListener {

    private static final int PICK_IMAGE = 1;
    ParseUser currentUser;
    Button mPostButton;
    Button mCancelButton;
    String boatTypeString;
    String boatingActivity;
    Boolean captain;
    Boolean firstmate;
    Boolean secondmate;
    String departure;
    String destination;
    ImageView boatPic;
    TextView mFromDate;
    TextView mToDate;
    String email;
    String experiencePref;
    int buttonSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_position_available);

        mFromDate = (TextView) findViewById(R.id.availableFromDate);
        Button fromButton = (Button) findViewById(R.id.availableFromDateButton);

        fromButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                //Keeps track of which date picker was clicked
                buttonSelected = 0;
                showDatePickerDialog(v);

            }

        });

        mToDate = (TextView) findViewById(R.id.availableToDate);
        Button toButton = (Button) findViewById(R.id.availableToDateButton);
        toButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Keeps track of which date picker was clicked
                buttonSelected = 1;
                showDatePickerDialog(v);
            }

        });

        //Create boat_type_spinner
        Spinner boatSpinner = (Spinner) findViewById(R.id.boat_type_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> boatAdapter = ArrayAdapter.createFromResource(this,
                R.array.boat_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        boatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        boatSpinner.setAdapter(boatAdapter);
        boatSpinner.setOnItemSelectedListener(this);

        //Create experience_years_captain_spinner
        Spinner yearsSpinner = (Spinner) findViewById(R.id.experience_preferred_spinnerQ);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.experience_years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        yearsSpinner.setAdapter(adapter);
        yearsSpinner.setOnItemSelectedListener(this);

        mPostButton = (Button) findViewById(R.id.postPositionAvailableButton);
        mPostButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                PostPosition(v);
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancelPostAvailableButton);
        mCancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cancelPost(v);
            }
        });


    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        //boolean checked = ((CheckBox) view).isChecked();

        CheckBox crewBox = ((CheckBox) findViewById(R.id.crewAvailableCheckBox));

        if (crewBox.isChecked()){
            View firstMate = (View)findViewById(R.id.firstMateAvailableCheckBox);
            firstMate.setVisibility(View.VISIBLE);
            View secondMate = (View)findViewById(R.id.secondMateAvailableCheckBox);
            secondMate.setVisibility(View.VISIBLE);
        }

    }
    public void cancelPost(View v){
        finish();
    }

    public void PostPosition(View v) {

        captain = ((CheckBox) findViewById(R.id.captainAvailableCheckBox)).isChecked();
        firstmate = ((CheckBox) findViewById(R.id.firstMateAvailableCheckBox)).isChecked();
        secondmate = ((CheckBox) findViewById(R.id.secondMateAvailableCheckBox)).isChecked();
        departure = ((EditText) findViewById(R.id.departureEditText)).getText().toString();
        destination = ((EditText) findViewById(R.id.destinationEditText)).getText().toString();
        email = ((EditText) findViewById(R.id.emailContactEditText)).getText().toString();
        currentUser = ParseUser.getCurrentUser();


        ParseObject positionAvailable = new ParseObject("PositionAvailable");
        positionAvailable.put("captain", captain);
        positionAvailable.put("firstMate", firstmate);
        positionAvailable.put("secondMate", secondmate);
        positionAvailable.put("experiencePref", experiencePref);
        positionAvailable.put("boatingActivity", boatingActivity);
        positionAvailable.put("departure", departure);
        positionAvailable.put("destination", destination);
        positionAvailable.put("boatType", boatTypeString);
        positionAvailable.put("email", email);
        positionAvailable.put("parseUser", currentUser);
        positionAvailable.saveInBackground();

        //Enter code to go to display of position available post

        /*Intent intent = new Intent(this, PositionsAvailableSearchResults.class);
        startActivity(intent);*/
        finish();
    }

    public void setTextViewDate(int year, int month, int day) {
        if (buttonSelected == 0) {
            mFromDate.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));
        }
        else {
            mToDate.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year)
                    .append(" "));
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            Uri _uri = data.getData();

            //User had pick an image.
            Cursor cursor = getContentResolver().query(_uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
            cursor.moveToFirst();

            //Link to the image
            final String imageFilePath = cursor.getString(0);
            cursor.close();
            Toast.makeText(this, imageFilePath, Toast.LENGTH_LONG).show();
            //boatPic.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void viewGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_log_in:
                Intent intent5 = new Intent(this, SignUpOrLogInActivity.class);
                startActivity(intent5);
                return true;
            case R.id.action_profile:
                //setContentView(R.layout.activity_profile_update);
                Intent intent = new Intent(this, ProfileDisplay.class);
                startActivity(intent);
                return true;
            case R.id.action_post_available:
                Intent intent1 = new Intent(this, PostPositionAvailable.class);
                startActivity(intent1);
                return true;
            case R.id.action_post_wanted:
                Intent intent2 = new Intent(this, PostPositionWanted.class);
                startActivity(intent2);
                return true;
            case R.id.action_position_available_search:
                Intent intent3 = new Intent(this, SearchFilterPositionAvailable.class);
                startActivity(intent3);
                return true;
            case R.id.action_position_wanted_search:
                Intent intent4 = new Intent(this, SearchFilterPositionWanted.class);
                startActivity(intent4);
                return true;
            case R.id.action_log_out:
                ParseUser.logOut();
                // Start and intent for the dispatch activity
                Intent intent6 = new Intent(this, DispatchActivity.class);
                intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent6);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.experience_preferred_spinnerQ) {
            experiencePref = parent.getItemAtPosition(position).toString();
        }
        else if(spinner.getId() == R.id.boat_type_spinner) {
                boatTypeString = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Another interface callback
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_commercial:
                if (checked)
                    boatingActivity = "commercial";
                    break;
            case R.id.radio_racing:
                if (checked)
                    boatingActivity = "racing";
                    break;
            case R.id.radio_recreational:
                if (checked)
                   boatingActivity = "recreational";
                    break;
        }
    }
}