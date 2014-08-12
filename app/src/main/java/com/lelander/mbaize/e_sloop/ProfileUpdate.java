package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;


public class ProfileUpdate extends Activity implements AdapterView.OnItemSelectedListener{

    Button mCancelButton;
    Button mSaveButton;
    String experienceString;
    EditText mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_profile_update);
        //private View.onClickListener mSaveChanges;

        //Create experience_years_captain_spinner
        Spinner captainSpinner = (Spinner) findViewById(R.id.experience_years_captain_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.experience_years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        captainSpinner.setAdapter(adapter);
        captainSpinner.setOnItemSelectedListener(this);


        //Create experience_years_crew_spinner
        Spinner crewSpinner = (Spinner) findViewById(R.id.experience_years_crew_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.experience_years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        crewSpinner.setAdapter(adapter);
        crewSpinner.setOnItemSelectedListener(this);

        //Retrieves any stored user info from SharedPreferences and inputs it into corresponding EditText box
        Context context = getApplicationContext();
        SharedPreferences userInfo = context.getSharedPreferences("user_info", MODE_PRIVATE);
        EditText mName = (EditText) findViewById(R.id.NameEdit);
        EditText mLocation = (EditText) findViewById(R.id.LocationEdit);


        String prefName = userInfo.getString("Name", "");

        String prefLocation = userInfo.getString("Location", "");
        String prefCaptainExperience = userInfo.getString("CaptainExperience", "");
        String prefCrewExperience = userInfo.getString("CrewExperience", "");
        mName.setText(prefName);

        mLocation.setText(prefLocation);

        

        //ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) adapter.getAdapter();

        int spinnerPosition1 = adapter.getPosition(prefCaptainExperience);

        captainSpinner.setSelection(spinnerPosition1);

        int spinnerPosition2 = adapter2.getPosition(prefCrewExperience);

        captainSpinner.setSelection(spinnerPosition2);




        mSaveButton = (Button) findViewById(R.id.save_changes_button);
        mSaveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveProfile(v);
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancel_changes_button);
        mCancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cancelChanges(v);
            }
        });
    }

    public void saveProfile(View v) {
        SharedPreferences userInfo;

        mSaveButton = (Button) findViewById(R.id.save_changes_button);
        mUserName   = (EditText) findViewById(R.id.NameEdit);
        EditText mLocation = (EditText) findViewById(R.id.LocationEdit);

        Context context = getApplicationContext();
        userInfo = context.getSharedPreferences("user_info", 0);
        SharedPreferences.Editor ed = userInfo.edit();
        String nameString = mUserName.getText().toString().trim(); //users choice
        String locationString = mLocation.getText().toString().trim();

        ed.putString("Name", nameString);
        ed.putString("Location", locationString);
        ed.putString("Experience", experienceString);
        ed.apply();

        //The parse-related code below is problematic because it tries to create the same user as a new user, so it's left out
        /*ParseUser user = new ParseUser();
        user.setUsername(nameString);
        user.setPassword("my pass");
        user.setEmail("email@esloop.com");


        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    ParseUser user = ParseUser.getCurrentUser();
                    ParseObject profile = new ParseObject("Profile");
                    profile.put("User", user);
               *//*     profile.put("name", nameString);
                    profile.put("location", locationString);
                    profile.put("experience", experienceString);*//*
                    profile.saveInBackground();

                    Toast.makeText(ProfileUpdate.this, "Changes Saved", Toast.LENGTH_LONG).show();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(ProfileUpdate.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });*/



        Intent intent = new Intent(this, ProfileDisplay.class);
        startActivity(intent);

    }

    public void cancelChanges(View v){
        finish();
    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        //Check which checkbox was clicked and make corresponding elements visible
        switch(view.getId()) {
            case R.id.CaptainCheckBox:
                {
                    TextView textView1 = (TextView) findViewById(R.id.ExperienceYears);
                    Spinner spinner1 = (Spinner) findViewById(R.id.experience_years_captain_spinner);
                    if (checked) {
                        textView1.setVisibility(View.VISIBLE);
                        spinner1.setVisibility(View.VISIBLE);
                    }
                    else {
                        textView1.setVisibility(View.GONE);
                        spinner1.setVisibility(View.GONE);

                    }
                }
            case R.id.CrewCheckBox:
                {
                    TextView textView2  = (TextView)findViewById(R.id.ExperienceYearsCrew);
                    Spinner spinner2 = (Spinner)findViewById(R.id.experience_years_crew_spinner);
                    CheckBox checkBox1 = (CheckBox)findViewById(R.id.FirstMateCheckBox);
                    CheckBox checkBox2 = (CheckBox)findViewById(R.id.SecondMateMateCheckBox);
                    CheckBox checkBox3 = (CheckBox)findViewById(R.id.DeckHandCheckBox);

                    if (checked) {
                        textView2.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        checkBox1.setVisibility(View.VISIBLE);
                        checkBox2.setVisibility(View.VISIBLE);
                        checkBox3.setVisibility(View.VISIBLE);
                    }
                    else {
                        textView2.setVisibility(View.GONE);
                        spinner2.setVisibility(View.GONE);
                        checkBox1.setVisibility(View.GONE);
                        checkBox2.setVisibility(View.GONE);
                        checkBox3.setVisibility(View.GONE);
                    }
                }
        }

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        experienceString = parent.getItemAtPosition(pos).toString();
    }



    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
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


}

