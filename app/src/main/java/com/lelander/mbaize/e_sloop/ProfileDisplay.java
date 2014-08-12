package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class ProfileDisplay extends Activity {
    Button updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);

        Context context = getApplicationContext();
        SharedPreferences userInfo = context.getSharedPreferences("user_info", MODE_PRIVATE);
        TextView mName = (TextView) findViewById(R.id.NameDisplay);
        TextView mPosition = (TextView) findViewById(R.id.PositionDisplay);
        TextView mLocation = (TextView) findViewById(R.id.LocationDisplay);
        TextView mExperience = (TextView) findViewById(R.id.ExperienceDisplay);

        String prefName = userInfo.getString("Name", "");
        String prefPosition = userInfo.getString("Position", "");
        String prefLocation = userInfo.getString("Location", "");
        String prefExperience = userInfo.getString("Experience", "");

        mName.setText(prefName);
        mPosition.setText(prefPosition);
        mLocation.setText(prefLocation);
        mExperience.setText(prefExperience);

        Toast.makeText(this, prefName, Toast.LENGTH_LONG).show();
    }

    public void startProfileUpdate (View v) {
        Intent intent = new Intent(this, ProfileUpdate.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_display, menu);
        return true;
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

}
