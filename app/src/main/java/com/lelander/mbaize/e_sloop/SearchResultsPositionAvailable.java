package com.lelander.mbaize.e_sloop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SearchResultsPositionAvailable extends ActionBarActivity {
    // Declare Variables
    ParseFile boatImage;
    StringBuilder builder = new StringBuilder();
    Boolean foundPosition = false;
    ListView listview;
    List<ParseObject> compoundQueryResults;
    ProgressDialog mProgressDialog;
    PositionAvailableSearchListViewAdapter adapter;
    private List<PositionAvailable> positionavailablelist = null;


    Boolean captain, firstMate, secondMate, commercial, racing, recreational, catamaran, catboat, ketch, schooner, sloop, sunfish, yawl;
    String fromDate, toDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_positions_available_search_listview);

        extractBundle();
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();

    }



    public void extractBundle() {
        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            captain = ex.getBoolean("extra_captain");
            firstMate = ex.getBoolean("extra_firstMate");
            secondMate = ex.getBoolean("extra_secondMate");
            commercial = ex.getBoolean("extra_commercial");
            racing = ex.getBoolean("extra_racing");
            recreational = ex.getBoolean("extra_recreational");
            catamaran = ex.getBoolean("extra_catamaran");
            catboat = ex.getBoolean("extra_catboat");
            ketch = ex.getBoolean("extra_ketch");
            schooner = ex.getBoolean("extra_schooner");
            sloop = ex.getBoolean("extra_sloop");
            sunfish = ex.getBoolean("extra_sunfish");
            yawl = ex.getBoolean("extra_yawl");
            fromDate = ex.getString("extra_fromDate");
            toDate = ex.getString("extra_toDate");
        }
    }

    public void createPositionsString(ParseObject positionsAvailable) {
        builder = new StringBuilder();

        foundPosition = false;
        if ((Boolean) positionsAvailable.get("captain") != null) {
            if ((Boolean) positionsAvailable.get("captain")) {
                foundPosition = true;
                builder.append("Captain");
            }
        }
        if ((Boolean) positionsAvailable.get("firstMate") != null) {
            if ((Boolean) positionsAvailable.get("firstMate")) {
                if (!foundPosition) {
                    foundPosition = true;
                    builder.append("First Mate");
                } else {
                    builder.append(", First Mate");
                }
            }
        }
        if ((Boolean) positionsAvailable.get("secondMate") != null) {
            if ((Boolean) positionsAvailable.get("secondMate")) {
                if (!foundPosition) {
                    builder.append("Second Mate");
                } else {
                    builder.append(", Second Mate");
                }
            }
        }
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SearchResultsPositionAvailable.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Esloop");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            // Create the array
            positionavailablelist = new ArrayList<PositionAvailable>();
            try {
                // Locate the class table named "PositionAvailable" in Parse.com

                List<ParseQuery<ParseObject>> positionsQueries = new ArrayList<ParseQuery<ParseObject>>();
                if (captain) {
                    ParseQuery<ParseObject> captainQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    captainQuery.whereEqualTo("captain", true);
                    positionsQueries.add(captainQuery);
                }
                if (firstMate) {
                    ParseQuery<ParseObject> firstMateQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    firstMateQuery.whereEqualTo("firstMate", true);
                    positionsQueries.add(firstMateQuery);
                }
                if (secondMate) {
                    ParseQuery<ParseObject> secondMateQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    secondMateQuery.whereEqualTo("secondMate", true);
                    positionsQueries.add(secondMateQuery);
                }


                ParseQuery<ParseObject> positionsOrQuery = ParseQuery.or(positionsQueries);




                //Below needs added once changes to experiencePref values are made
                //List<ParseQuery<ParseObject>> experienceQuery = new ArrayList<ParseQuery<ParseObject>>();

                List<ParseQuery<ParseObject>> activityQueries = new ArrayList<ParseQuery<ParseObject>>();
                if (commercial) {
                    ParseQuery<ParseObject> commercialQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    commercialQuery.whereEqualTo("boatingActivity", "commercial");
                    activityQueries.add(commercialQuery);
                }
                if (racing) {
                    ParseQuery<ParseObject> racingQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    racingQuery.whereEqualTo("boatingActivity", "racing");
                    activityQueries.add(racingQuery);
                }
                if (recreational) {
                    ParseQuery<ParseObject> recreationalQuery = new ParseQuery<ParseObject>(
                            "PositionAvailable");
                    recreationalQuery.whereEqualTo("boatingActivity", "recreational");
                    activityQueries.add(recreationalQuery);
                }

                ParseQuery<ParseObject> activityOrQuery = ParseQuery.or(activityQueries);

                List<ParseQuery<ParseObject>> boatTypeQueries = new ArrayList<ParseQuery<ParseObject>>();
                if (catamaran){
                    ParseQuery<ParseObject> catamaranQuery = ParseQuery.getQuery("PositionAvailable");
                    catamaranQuery.whereEqualTo("boatType", "Catamaran");
                    boatTypeQueries.add(catamaranQuery);
                }
                if (catboat){
                    ParseQuery<ParseObject> catboatQuery = ParseQuery.getQuery("PositionAvailable");
                    catboatQuery.whereEqualTo("boatType", "Catboat");
                    boatTypeQueries.add(catboatQuery);
                }
                if (ketch){
                    ParseQuery<ParseObject> ketchQuery = ParseQuery.getQuery("PositionAvailable");
                    ketchQuery.whereEqualTo("boatType", "Ketch");
                    boatTypeQueries.add(ketchQuery);
                }
                if (schooner){
                    ParseQuery<ParseObject> schoonerQuery = ParseQuery.getQuery("PositionAvailable");
                    schoonerQuery.whereEqualTo("boatType", "Schooner");
                    boatTypeQueries.add(schoonerQuery);
                }
                if (sloop){
                    ParseQuery<ParseObject> sloopQuery = ParseQuery.getQuery("PositionAvailable");
                    sloopQuery.whereEqualTo("boatType", "Sloop");
                    boatTypeQueries.add(sloopQuery);
                }
                if (sunfish){
                    ParseQuery<ParseObject> sunfishQuery = ParseQuery.getQuery("PositionAvailable");
                    sunfishQuery.whereEqualTo("boatType", "Sunfish");
                    boatTypeQueries.add(sunfishQuery);
                }
                if (yawl){
                    ParseQuery<ParseObject> yawlQuery = ParseQuery.getQuery("PositionAvailable");
                    yawlQuery.whereEqualTo("boatType", "Yawl");
                    boatTypeQueries.add(yawlQuery);
                }


                //Run compound query for above
                ParseQuery<ParseObject> boatTypesQuery = ParseQuery.or(boatTypeQueries);
                boatTypesQuery.whereMatchesKeyInQuery("objectId", "objectId", positionsOrQuery);
                boatTypesQuery.whereMatchesKeyInQuery("objectId", "objectId", activityOrQuery);
                compoundQueryResults = boatTypesQuery.find();


                for (ParseObject positionsAvailable : compoundQueryResults) {


                    createPositionsString(positionsAvailable);


                    ParseFile boatImage = (ParseFile) positionsAvailable.get("boatImage");


                    PositionAvailable availableListing = new PositionAvailable();
                    availableListing.setBoatImage(boatImage.getUrl()); //positionsAvailable.get("boatImage"));
                    availableListing.setPositions(builder.toString());
                    availableListing.setBoatingActivity((String) positionsAvailable.get("boatingActivity"));
                    availableListing.setExperiencePref((String) positionsAvailable.get("experiencePref"));
                    availableListing.setDeparture((String) positionsAvailable.get("departure"));
                    availableListing.setDestination((String) positionsAvailable.get("destination"));
                    availableListing.setBoatType((String) positionsAvailable.get("boatType"));
                    availableListing.setEmail((String) positionsAvailable.get("email"));
                    positionavailablelist.add(availableListing);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.positionAvailableListView);
            // Pass the results into ListViewAdapter.java
            adapter = new PositionAvailableSearchListViewAdapter(SearchResultsPositionAvailable.this,
                    positionavailablelist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

    public void showFullBoat(View v) {
        Intent intent = new Intent(this, BoatViewActivity.class);
        String boatImage = v.toString();
        intent.putExtra("boatImage", boatImage);
        startActivity(intent);

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



