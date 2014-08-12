package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.parse.ParseUser;

/**
 * Activity which starts an intent for either the logged in (MainActivity) or logged out
 * (SignUpOrLoginActivity) activity.
 */

public class DispatchActivity extends Activity {

    public DispatchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, SearchFilterPositionAvailable.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, SignUpOrLogInActivity.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dispatch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
