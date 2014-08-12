package com.lelander.mbaize.e_sloop;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    /* Request code used to invoke sign in user interactions. *//*
    private static final int RC_SIGN_IN = 0;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        /*Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);*/

        setContentView(R.layout.activity_main);

        Parse.initialize(this, "6G1HYLdyVlXlDVubmd1lARmdpYWgo8I9a62oSXGK", "SRYNStFiHicEuU71LphkRfOCB9Hu6EqMjc5oBUZ1");

        ParseUser user = new ParseUser();
        user.setUsername("Captain Hook");
        user.setPassword("my pass");
        user.setEmail("hook@esloop.com");

        // other fields can be set just like with ParseObject
        user.put("phone", "650-555-hook");

        /*user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(MainActivity.this, "No Parse error!", Toast.LENGTH_LONG).show();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });*/

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            Toast.makeText(this, "CurrentUser exists", Toast.LENGTH_LONG).show();
        } else {
            // show the signup or login screen
            Toast.makeText(this, "NO CurrentUser", Toast.LENGTH_LONG).show();
           /* Intent intent = new Intent(this, ParseLoginActivity.class);
            startActivity(intent);*/
        }
        /*if (mGoogleApiClient == null) {
            // Initializing google plus api client
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                    .addScope(Plus.SCOPE_PLUS_LOGIN).build();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }*/



        //The code below is unnecessary since onConnect isn't called until onStart, which is after onCreate
        /*if (mGoogleApiClient.isConnected()) {
            Toast.makeText(this, "Client connected!", Toast.LENGTH_LONG).show();
        }
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(this, "Client NOT connected!", Toast.LENGTH_LONG).show();
        }*/
    }


    /*protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search_filter:
                Intent intent4 = new Intent(this, SearchFilterPositionAvailable.class);
                startActivity(intent4);
                return true;
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
                Intent intent3 = new Intent(this, SearchResultsPositionAvailable.class);
                startActivity(intent3);
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



    /*@Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }*/

    /*@Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(this, "Connection failed", Toast.LENGTH_LONG).show();
        if (!mIntentInProgress && result.hasResolution()) {
            try {
                mIntentInProgress = true;
                result.startResolutionForResult(this,
                        RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                Toast.makeText(this, "Connection FAILED", Toast.LENGTH_LONG).show();
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }*/

   /* public void onConnected(Bundle connectionHint) {
        // We've resolved any connection errors.  mGoogleApiClient can be used to
        // access Google APIs on behalf of the user.
        Toast.makeText(MainActivity.this, "mGoogleApiClient connected!", Toast.LENGTH_LONG).show();
        *//*Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);*//*
    }
    */

    protected void onStop() {
        super.onStop();

        /*if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }*/
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ArrayList<String> captainsArr = new ArrayList<String>();

            captainsArr.add("Popeye");
            captainsArr.add("Olive Oil");
            captainsArr.add("Ahab");
            captainsArr.add("Captain Crunch");
            captainsArr.add("Captain America");
            captainsArr.add("Captain D");
            captainsArr.add("Captain Obvious");
            captainsArr.add("Christopher Columbus");
            captainsArr.add("Captain Phillips");
            Context context = getActivity();
            ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.list_item_profile_search, R.id.list_item_profile_search_textview, captainsArr);

            ListView listView = (ListView) rootView.findViewById(R.id.listview_profile_search);
            listView.setAdapter(adapter);
            /*ImageView image = (ImageView) rootView.findViewById(R.id.imageView);
            image.setImageResource(R.drawable.sailboat_thumb);*/
            return rootView;
        }
    }
}
