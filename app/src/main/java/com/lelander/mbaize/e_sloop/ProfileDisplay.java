package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;

public class ProfileDisplay extends Activity {
    Button updateButton;
    ParseImageView mProfileImage;
    TextView mName, mPosition, mLocation, mExperience, mComments, mCaptainExperience, mCrewExperience;
    ParseUser user;
    String mCaptainExperienceString, mCrewExperienceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);

        user = ParseUser.getCurrentUser();

        /*Context context = getApplicationContext();
        SharedPreferences userInfo = context.getSharedPreferences("user_info", MODE_PRIVATE);*/
         mName = (TextView) findViewById(R.id.NameDisplay);
         //mPosition = (TextView) findViewById(R.id.PositionDisplay);
         mLocation = (TextView) findViewById(R.id.LocationDisplay);
         mCaptainExperience = (TextView) findViewById(R.id.CaptainExperienceDisplay);
        mCrewExperience = (TextView) findViewById(R.id.CrewExperienceDisplay);
        mComments = (TextView) findViewById(R.id.addInfoDisplay);
         mProfileImage = (ParseImageView) findViewById(R.id.profileDisplayImageView);
         mProfileImage.setPlaceholder(getResources().getDrawable(R.drawable.no_picture_uploaded));

        mName.setText(user.getString("name"));
        //Will need a string builder for positions
        // mPosition.setText(prefPosition);
        mLocation.setText(user.getString("location"));
        mCaptainExperience.setText(String.valueOf(user.getInt("captainExperience")));
        mCrewExperience.setText(String.valueOf(user.getInt("crewExperience")));
        mComments.setText(user.getString("comments"));


        //Will need a string builder for positions
        //String prefPosition = user.getString("position");
        ParseFile profilePic = (ParseFile) user.get("profileImage");

        mProfileImage.setParseFile(profilePic);
        mProfileImage.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                //Log.i ("ParseImageView", "Fetched! Data length: " + bytes.length + ", or exception: " + e.getMessage());
            }
        });
        /*profilePic.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if (e == null){





                } else {
                    Toast.makeText(ProfileDisplay.this, "Sorry, profile picture couldn't be retrieved", Toast.LENGTH_LONG).show();
                }

            }
        });*/


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
