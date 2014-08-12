package com.lelander.mbaize.e_sloop;

import android.app.DialogFragment;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import static android.view.View.OnClickListener;

public class PostPositionWanted extends ActionBarActivity implements AdapterView.OnItemSelectedListener, DatePickerFragment.DateSetListener {
    TextView mFromDate, mToDate;
    String fromDateString, toDateString;
    Button mPostWanted, mCancelPost, mPostButton, mCancelButton;
    int buttonSelected;
    Boolean captain, catamaran, catboat, ketch, schooner, sloop, sunfish, yawl;
    Boolean firstmate;
    Boolean secondmate;
    Boolean commercial;
    Boolean racing;
    Boolean recreational;
    String email, experienceHad;


    /*Button toButton = (Button) findViewById(R.id.toDateButton);
            int buttonSelected;
        */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_position_wanted);

        setYearsSpinner();

        mFromDate = (TextView) findViewById(R.id.wantedFromDateTextView);
        Button fromButton = (Button) findViewById(R.id.wantedFromDateButton);

        fromButton.setOnClickListener(new OnClickListener() {

          public void onClick(View v)
          {
              //Keeps track of which date picker was clicked
              buttonSelected = 0;
              showDatePickerDialog(v);

          }

        });

        mToDate = (TextView) findViewById(R.id.wantedToDateTextView);
        Button toButton = (Button) findViewById(R.id.wantedToDateButton);
        toButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //Keeps track of which date picker was clicked
                buttonSelected = 1;
                showDatePickerDialog(v);
            }

        });

        mPostButton = (Button) findViewById(R.id.postPositionWantedButton);
        mPostButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                postPositionWanted(v);
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancelPostWantedButton);
        mCancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cancelPost(v);
            }
        });

    }

    public void postPositionWanted(View v) {
        captain = ((CheckBox) findViewById(R.id.captainWantedCheckBox)).isChecked();
        firstmate = ((CheckBox) findViewById(R.id.firstMateWanted)).isChecked();
        secondmate = ((CheckBox) findViewById(R.id.secondMateWanted)).isChecked();
        commercial = ((CheckBox) findViewById(R.id.commercialCheckBox)).isChecked();
        racing = ((CheckBox) findViewById(R.id.racingCheckBox)).isChecked();
        recreational = ((CheckBox) findViewById(R.id.recreationalCheckBox)).isChecked();
        catamaran = ((CheckBox) findViewById(R.id.postWantedCatamaranCheckBox)).isChecked();
        catboat = ((CheckBox) findViewById(R.id.postWantedCatboatCheckBox)).isChecked();
        ketch = ((CheckBox) findViewById(R.id.postWantedKetchCheckBox)).isChecked();
        schooner = ((CheckBox) findViewById(R.id.postWantedSchoonerCheckBox)).isChecked();
        sloop = ((CheckBox) findViewById(R.id.postWantedSloopCheckBox)).isChecked();
        sunfish = ((CheckBox) findViewById(R.id.postWantedSunfishCheckBox)).isChecked();
        yawl = ((CheckBox) findViewById(R.id.postYawlWantedCheckBox)).isChecked();



        email = ((EditText) findViewById(R.id.emailContactPostWanted)).getText().toString();
        fromDateString = mFromDate.getText().toString();
        toDateString = mToDate.getText().toString();

        ParseObject positionWanted = new ParseObject("PositionWanted");
        positionWanted.put("captain", captain);
        positionWanted.put("firstMate", firstmate);
        positionWanted.put("secondMate", secondmate);
        positionWanted.put("experienceHad", experienceHad);
        positionWanted.put("commercial", commercial);
        positionWanted.put("racing", racing);
        positionWanted.put("recreational", recreational);
        positionWanted.put("fromDate", fromDateString);
        positionWanted.put("toDate", toDateString);
        positionWanted.put("email", email);
        positionWanted.put("catamaran", catamaran);
        positionWanted.put("catboat", catboat);
        positionWanted.put("ketch", ketch);
        positionWanted.put("schooner", schooner);
        positionWanted.put("sloop", sloop);
        positionWanted.put("sunfish", sunfish);
        positionWanted.put("yawl", yawl);
        positionWanted.saveInBackground();

        finish();
    }

    public void cancelPost(View v) {
        finish();
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        //boolean checked = ((CheckBox) view).isChecked();

        CheckBox crewBox = ((CheckBox) findViewById(R.id.crewWantedCheckBox));

        if (crewBox.isChecked()){
            View firstMate = (View)findViewById(R.id.firstMateWanted);
            firstMate.setVisibility(View.VISIBLE);
            View secondMate = (View)findViewById(R.id.secondMateWanted);
            secondMate.setVisibility(View.VISIBLE);
        }

    }
    private void setYearsSpinner() {
        //Create experience_years_captain_spinner
        Spinner yearsSpinner = (Spinner) findViewById(R.id.experience_years_spinnerQ);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.experience_years_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        yearsSpinner.setAdapter(adapter);
        yearsSpinner.setOnItemSelectedListener(this);

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

    public void setmFromDate(TextView mFromDate) {
        this.mFromDate = mFromDate;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
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
        experienceHad = parent.getItemAtPosition(position).toString();

        /*Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.experience_preferred_spinnerQ) {

            //parent.getItemAtPosition(position).toString();
        }*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
