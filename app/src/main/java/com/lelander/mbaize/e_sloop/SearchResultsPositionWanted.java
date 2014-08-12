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

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SearchResultsPositionWanted extends ActionBarActivity {
    // Declare Variables
    ParseFile profileImage;
    StringBuilder positionsBuilder = new StringBuilder();
    StringBuilder boatTypeBuilder = new StringBuilder();
    Boolean foundBoatType = false;
    ListView listview;
    List<ParseObject> compoundQueryResults;
    ProgressDialog mProgressDialog;
    PositionWantedSearchListViewAdapter adapter;
    private List<PositionWanted> positionwantedlist = null;


    Boolean captain, firstMate, secondMate, commercial, racing, recreational, catamaran, catboat, ketch, schooner, sloop, sunfish, yawl;
    String experienceHad, fromDate, toDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_position_wanted_search_listview);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();


        extractBundle();

        //The toast below does show the proper value for captain
        //Toast.makeText(this, captain.toString(), Toast.LENGTH_LONG).show();


        //buildParseQuery();
    }



    public void extractBundle() {
        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            captain = ex.getBoolean("extra_captain");
            firstMate = ex.getBoolean("extra_firstMate");
            secondMate = ex.getBoolean("extra_secondMate");
            experienceHad = ex.getString("extra_experienceHad");
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

    public void createBoatTypeString(ParseObject positionWanted) {
        boatTypeBuilder = new StringBuilder();

        foundBoatType = false;
        if ((Boolean) positionWanted.get("catamaran") != null) {
            if ((Boolean) positionWanted.get("catamaran")) {
                foundBoatType = true;
                boatTypeBuilder.append("Catamaran");
            }
        }
        if ((Boolean) positionWanted.get("catboat") != null) {
            if ((Boolean) positionWanted.get("catboat")) {
                if (!foundBoatType) {
                    foundBoatType = true;
                    boatTypeBuilder.append("Catboat");
                } else {
                    boatTypeBuilder.append(", catboat");
                }
            }
        }
        if ((Boolean) positionWanted.get("ketch") != null) {
            if ((Boolean) positionWanted.get("ketch")) {
                if (!foundBoatType) {
                    boatTypeBuilder.append("Ketch");
                } else {
                    boatTypeBuilder.append(", Ketch");
                }
            }
        }
        if ((Boolean) positionWanted.get("schooner") != null) {
            if ((Boolean) positionWanted.get("schooner")) {
                if (!foundBoatType) {
                    boatTypeBuilder.append("Schooner");
                } else {
                    boatTypeBuilder.append(", schooner");
                }
            }
        }
        if ((Boolean) positionWanted.get("sloop") != null) {
            if ((Boolean) positionWanted.get("sloop")) {
                if (!foundBoatType) {
                    boatTypeBuilder.append("Sloop");
                } else {
                    boatTypeBuilder.append(", sloop");
                }
            }
        }
        if ((Boolean) positionWanted.get("sunfish") != null) {
            if ((Boolean) positionWanted.get("sunfish")) {
                if (!foundBoatType) {
                    boatTypeBuilder.append("Sunfish");
                } else {
                    boatTypeBuilder.append(", sunfish");
                }
            }
        }
        if ((Boolean) positionWanted.get("yawl") != null) {
            if ((Boolean) positionWanted.get("yawl")) {
                if (!foundBoatType) {
                    boatTypeBuilder.append("Yawl");
                } else {
                    boatTypeBuilder.append(", yawl");
                }
            }
        }

    }

    public void createPositionsString(ParseObject positionWanted) {
        positionsBuilder = new StringBuilder();

        foundBoatType = false;
        if ((Boolean) positionWanted.get("captain") != null) {
            if ((Boolean) positionWanted.get("captain")) {
                foundBoatType = true;
                positionsBuilder.append("Captain");
            }
        }
        if ((Boolean) positionWanted.get("firstMate") != null) {
            if ((Boolean) positionWanted.get("firstMate")) {
                if (!foundBoatType) {
                    foundBoatType = true;
                    positionsBuilder.append("First Mate");
                } else {
                    positionsBuilder.append(", First Mate");
                }
            }
        }
        if ((Boolean) positionWanted.get("secondMate") != null) {
            if ((Boolean) positionWanted.get("secondMate")) {
                if (!foundBoatType) {
                    positionsBuilder.append("Second Mate");
                } else {
                    positionsBuilder.append(", Second Mate");
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
            mProgressDialog = new ProgressDialog(SearchResultsPositionWanted.this);
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
            positionwantedlist = new ArrayList<PositionWanted>();
           try {


               List<ParseQuery<ParseObject>> positionsQueries = new ArrayList<ParseQuery<ParseObject>>();
               if (captain != null) {
                   if (captain) {
                       ParseQuery<ParseObject> captainQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       captainQuery.whereEqualTo("captain", true);
                       positionsQueries.add(captainQuery);
                   }
               }
               if (firstMate != null) {
                   if (firstMate) {
                       ParseQuery<ParseObject> firstMateQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       firstMateQuery.whereEqualTo("firstMate", true);
                       positionsQueries.add(firstMateQuery);
                   }
               }
               if (secondMate != null) {
                   if (secondMate) {
                       ParseQuery<ParseObject> secondMateQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       secondMateQuery.whereEqualTo("secondMate", true);
                       positionsQueries.add(secondMateQuery);
                   }
               }


               ParseQuery<ParseObject> positionsOrQuery = ParseQuery.or(positionsQueries);

               List<ParseQuery<ParseObject>> activityQueries = new ArrayList<ParseQuery<ParseObject>>();
               if (commercial != null) {
                   if (commercial) {
                       ParseQuery<ParseObject> commercialQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       commercialQuery.whereEqualTo("commercial", true);
                       activityQueries.add(commercialQuery);
                   }
               }
               if (racing != null) {
                   if (racing) {
                       ParseQuery<ParseObject> racingQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       racingQuery.whereEqualTo("racing", true);
                       activityQueries.add(racingQuery);
                   }
               }
               if (recreational != null) {
                   if (recreational) {
                       ParseQuery<ParseObject> recreationalQuery = new ParseQuery<ParseObject>(
                               "PositionWanted");
                       recreationalQuery.whereEqualTo("recreational", true);
                       activityQueries.add(recreationalQuery);
                   }
               }

               ParseQuery<ParseObject> activityOrQuery = ParseQuery.or(activityQueries);

               List<ParseQuery<ParseObject>> boatTypeQueries = new ArrayList<ParseQuery<ParseObject>>();
               if (catamaran != null) {
                   if (catamaran){
                       ParseQuery<ParseObject> catamaranQuery = ParseQuery.getQuery("PositionWanted");
                   catamaranQuery.whereEqualTo("catamaran", true);
                       boatTypeQueries.add(catamaranQuery);
                   }
               }
               if (catboat != null) {
                   if (catboat){
                       ParseQuery<ParseObject> catboatQuery = ParseQuery.getQuery("PositionWanted");
                       catboatQuery.whereEqualTo("catboat", true);
                       boatTypeQueries.add(catboatQuery);
                   }
               }
               if (ketch != null) {
                   if (ketch){
                       ParseQuery<ParseObject> ketchQuery = ParseQuery.getQuery("PositionWanted");
                       ketchQuery.whereEqualTo("ketch", true);
                       boatTypeQueries.add(ketchQuery);
                   }
               }
               if (schooner != null) {
                   if (schooner){
                       ParseQuery<ParseObject> schoonerQuery = ParseQuery.getQuery("PositionWanted");
                       schoonerQuery.whereEqualTo("schooner", true);
                       boatTypeQueries.add(schoonerQuery);
                   }
               }
               if (sloop != null) {
                   if (sloop){
                       ParseQuery<ParseObject> sloopQuery = ParseQuery.getQuery("PositionWanted");
                       sloopQuery.whereEqualTo("sloop", true);
                       boatTypeQueries.add(sloopQuery);
                   }
               }
               if (sunfish != null) {
                   if (sunfish){
                       ParseQuery<ParseObject> sunfishQuery = ParseQuery.getQuery("PositionWanted");
                       sunfishQuery.whereEqualTo("sunfish", true);
                       boatTypeQueries.add(sunfishQuery);
                   }
               }
               if (yawl != null) {
                   if (yawl){
                       ParseQuery<ParseObject> yawlQuery = ParseQuery.getQuery("PositionWanted");
                       yawlQuery.whereEqualTo("yawl", true);
                       boatTypeQueries.add(yawlQuery);
                   }
               }


               //Run compound query for above
               ParseQuery<ParseObject> boatTypesQuery = ParseQuery.or(boatTypeQueries);
               boatTypesQuery.whereMatchesKeyInQuery("objectId", "objectId", positionsOrQuery);
               boatTypesQuery.whereMatchesKeyInQuery("objectId", "objectId", activityOrQuery);
               compoundQueryResults = boatTypesQuery.find();






                for (ParseObject positionWanted : compoundQueryResults) {
                    // Locate images in flag column
                    createPositionsString(positionWanted);
                    ParseFile profileImage = (ParseFile) positionWanted.get("profileImage");


                    PositionWanted wantedListing = new PositionWanted();
                    wantedListing.setProfileImage(profileImage.getUrl());
                    wantedListing.setBoatingActivity((String) positionWanted.get("boatingActivity"));
                    wantedListing.setBoatType(boatTypeBuilder.toString());
                    wantedListing.setPositions(positionsBuilder.toString());
                    wantedListing.setExperienceHad((String) positionWanted.get("experienceHad"));
                    wantedListing.setBoatType((String) positionWanted.get("boatType"));
                    wantedListing.setEmail((String) positionWanted.get("email"));
                    positionwantedlist.add(wantedListing);
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
            listview = (ListView) findViewById(R.id.positionWantedListView);
            // Pass the results into ListViewAdapter.java
            adapter = new PositionWantedSearchListViewAdapter(SearchResultsPositionWanted.this,
                    positionwantedlist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

    public void showFullProfileImage(View v) {
        Intent intent = new Intent(this, BoatViewActivity.class);
        String profileImage = v.toString();
        intent.putExtra("profileImage", profileImage);
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