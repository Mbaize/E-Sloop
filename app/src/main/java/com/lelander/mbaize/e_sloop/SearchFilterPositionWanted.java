package com.lelander.mbaize.e_sloop;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;



public class SearchFilterPositionWanted extends ActionBarActivity implements AdapterView.OnItemSelectedListener, DatePickerFragment.DateSetListener {

    Boolean captain, firstMate, secondMate, commercial, racing, recreational, catamaran, catboat, ketch, schooner, sloop, sunfish, yawl;
    String fromDate, toDate, experienceHad, profileImage;
    TextView mFromDate, mToDate;
    int buttonSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_position_wanted);

        //Create experience_years_captain_spinner
        Spinner yearsSpinner = (Spinner) findViewById(R.id.search_wanted_experience_preferred_spinnerQ);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.experience_years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        yearsSpinner.setAdapter(adapter);
        yearsSpinner.setOnItemSelectedListener(this);

        mFromDate = (TextView) findViewById(R.id.searchWantedFromDate);
        Button fromButton = (Button) findViewById(R.id.searchWantedFromDateButton);

        fromButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                //Keeps track of which date picker was clicked
                buttonSelected = 0;
                showDatePickerDialog(v);

            }

        });

        mToDate = (TextView) findViewById(R.id.searchWantedToDate);
        Button toButton = (Button) findViewById(R.id.searchWantedToDateButton);
        toButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Keeps track of which date picker was clicked
                buttonSelected = 1;
                showDatePickerDialog(v);
            }

        });
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

    public void searchPositionWanted(View v) {
        captain = ((CheckBox) findViewById(R.id.searchCaptainWantedCheckBox)).isChecked();
        firstMate = ((CheckBox) findViewById(R.id.searchFirstMateWantedCheckBox)).isChecked();
        secondMate = ((CheckBox) findViewById(R.id.searchSecondMateWantedCheckBox)).isChecked();
        commercial = ((CheckBox) findViewById(R.id.searchWantedCommercialCheckBox)).isChecked();
        racing = ((CheckBox) findViewById(R.id.searchWantedRacingCheckBox)).isChecked();
        recreational = ((CheckBox) findViewById(R.id.searchWantedRecreationalCheckBox)).isChecked();
        catamaran = ((CheckBox) findViewById(R.id.searchWantedCatamaranCheckBox)).isChecked();
        catboat = ((CheckBox) findViewById(R.id.searchWantedCatboatCheckBox)).isChecked();
        ketch   = ((CheckBox) findViewById(R.id.searchWantedKetchCheckBox)).isChecked();
        sloop = ((CheckBox) findViewById(R.id.searchWantedSloopCheckBox)).isChecked();
        schooner = ((CheckBox) findViewById(R.id.searchWantedSchoonerCheckBox)).isChecked();
        sunfish = ((CheckBox) findViewById(R.id.searchWantedSunfishCheckBox)).isChecked();
        yawl = ((CheckBox) findViewById(R.id.searchWantedYawlCheckBox)).isChecked();
        fromDate = ((TextView) findViewById(R.id.searchWantedFromDate)).getText().toString();
        toDate = ((TextView) findViewById(R.id.searchWantedToDate)).getText().toString();

        Intent i = new Intent(this, SearchResultsPositionWanted.class);
        Bundle ex = new Bundle();
        ex.putString("extra_profileImage", profileImage);
        ex.putBoolean("extra_captain", captain);
        ex.putBoolean("extra_firstMate", firstMate);
        ex.putBoolean("extra_secondMate", secondMate);
        ex.putString("extra_experienceHad", experienceHad);
        ex.putBoolean("extra_commercial", commercial);
        ex.putBoolean("extra_racing", racing);
        ex.putBoolean("extra_recreational", recreational);
        ex.putBoolean("extra_catamaran", catamaran);
        ex.putBoolean("extra_catboat", catboat);
        ex.putBoolean("extra_ketch", ketch);
        ex.putBoolean("extra_schooner", schooner);
        ex.putBoolean("extra_sloop", sloop);
        ex.putBoolean("extra_sunfish", sunfish);
        ex.putBoolean("extra_yawl", yawl);
        ex.putString("extra_fromDate", fromDate);
        ex.putString("extra_toDate", toDate);
        i.putExtras(ex);
        startActivity(i);

    }

    public void cancelSearch(View view) {
        finish();
    }

    public void onCheckboxClicked(View view) {

        CheckBox crewBox = ((CheckBox) findViewById(R.id.searchCrewWantedCheckBox));

        if (crewBox.isChecked()){
            View firstMate = (View)findViewById(R.id.searchFirstMateWantedCheckBox);
            firstMate.setVisibility(View.VISIBLE);
            View secondMate = (View)findViewById(R.id.searchSecondMateWantedCheckBox);
            secondMate.setVisibility(View.VISIBLE);
        }

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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        experienceHad = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
