package com.lelander.mbaize.e_sloop;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;


public class ProfileUpdate extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap picBitmap = null;
    Button mCancelButton;
    Button mSaveButton;
    CheckBox captainBox, crewBox, firstMateBox, secondMateBox, deckHandBox;
    String mRealNameString, mLocationString, mAddInfoString;
    EditText mRealName, mLocation, mAddInfo;
    ParseFile profileImage;
    Boolean captain, crew, deckHand, firstMate, secondMate;
    ParseUser user;
    ParseImageView mProfileImageView;
    ParseFile mProfileImage;
    int captainExperienceInt, crewExperienceInt;
    String captainExperienceString, crewExperienceString;
    String[] checkBoxValues = new String[]{"captain", "crew", "firstMate", "secondMate", "deckHand"};
    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        user = ParseUser.getCurrentUser();

        //Update the block below to display the user's current uploaded photo, or the default "no photo" pic
        mProfileImageView = (ParseImageView) findViewById(R.id.photo_profile);
        mProfileImageView.setPlaceholder(getResources().getDrawable(R.drawable.no_picture_uploaded));

        mProfileImage = (ParseFile) user.get("profileImage");

        mProfileImageView.setParseFile(mProfileImage);
        mProfileImageView.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                //Log.i ("ParseImageView", "Fetched! Data length: " + bytes.length + ", or exception: " + e.getMessage());
            }
        });
        mRealName = (EditText) findViewById(R.id.NameEdit);
        mRealName.setText(user.getString("name"));
        mLocation = (EditText) findViewById(R.id.LocationEdit);
        mLocation.setText(user.getString("location"));

        checkBoxes= new CheckBox[]{
                (CheckBox) findViewById(R.id.CaptainCheckBox),
                (CheckBox) findViewById(R.id.CrewCheckBox),
                (CheckBox) findViewById(R.id.FirstMateCheckBox),
                (CheckBox) findViewById(R.id.SecondMateMateCheckBox),
                (CheckBox) findViewById(R.id.DeckHandCheckBox)
        };

        for (int i = 0; i < 5; i++) {
            if (user.getBoolean(checkBoxValues[i]))
                checkBoxes[i].setChecked(true);
        }
               /* captainBox = (CheckBox) findViewById(R.id.CaptainCheckBox);
        if (user.getBoolean("captain"))
            captainBox.setChecked(true);
        crewBox = (CheckBox) findViewById(R.id.CrewCheckBox);
        if (user.getBoolean("crew"))
            crewBox.setChecked(true);*/


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

        /*//Retrieves any stored user info from SharedPreferences and inputs it into corresponding EditText box
        Context context = getApplicationContext();
        SharedPreferences userInfo = context.getSharedPreferences("user_info", MODE_PRIVATE);
        EditText mName = (EditText) findViewById(R.id.NameEdit);
        EditText mLocation = (EditText) findViewById(R.id.LocationEdit);


        String prefName = userInfo.getString("Name", "");

        String prefLocation = userInfo.getString("Location", "");
        String prefCaptainExperience = userInfo.getString("CaptainExperience", "");
        String prefCrewExperience = userInfo.getString("CrewExperience", "");
        mName.setText(prefName);

        mLocation.setText(prefLocation);*/

        captainExperienceInt = user.getInt("captainExperienceInt");
        crewExperienceInt = user.getInt("crewExperienceInt");

        captainExperienceString = String.valueOf(captainExperienceInt);
        crewExperienceString = String.valueOf(crewExperienceInt);
        //ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) adapter.getAdapter();

        //Sets spinner to user's current info (update for parse.com)
        int spinnerPosition1 = adapter.getPosition(captainExperienceString);

        captainSpinner.setSelection(spinnerPosition1);

        int spinnerPosition2 = adapter2.getPosition(crewExperienceString);

        captainSpinner.setSelection(spinnerPosition2);


        mSaveButton = (Button) findViewById(R.id.save_changes_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveProfile(v);
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancel_changes_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelChanges(v);
            }
        });
    }


    public void saveProfile(View v) {
        //SharedPreferences userInfo;


        mSaveButton = (Button) findViewById(R.id.save_changes_button);
        mRealName = (EditText) findViewById(R.id.NameEdit);
        mRealNameString = mRealName.getText().toString().trim();

        mLocation = (EditText) findViewById(R.id.LocationEdit);
        mLocationString = mLocation.getText().toString().trim();
        mAddInfo = (EditText) findViewById(R.id.CommentsEdit);
        mAddInfoString = mAddInfo.getText().toString().trim();
        captainBox = (CheckBox) findViewById(R.id.CaptainCheckBox);
        captain = captainBox.isChecked();
        crewBox = (CheckBox) findViewById(R.id.CrewCheckBox);
        crew = crewBox.isChecked();
        firstMateBox = (CheckBox) findViewById(R.id.FirstMateCheckBox);
        firstMate = firstMateBox.isChecked();
        secondMateBox = (CheckBox) findViewById(R.id.SecondMateMateCheckBox);
        secondMate = secondMateBox.isChecked();
        deckHandBox = (CheckBox) findViewById(R.id.DeckHandCheckBox);
        deckHand = deckHandBox.isChecked();
        /*Context context = getApplicationContext();
        userInfo = context.getSharedPreferences("user_info", 0);
        SharedPreferences.Editor ed = userInfo.edit();
        nameString = mRealName.getText().toString().trim(); //users choice
        locationString = mLocation.getText().toString().trim();*/

        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        picBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] picBytes = stream.toByteArray();

        profileImage = new ParseFile("profilePic.jpg", picBytes);
        profileImage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                ParseUser user = ParseUser.getCurrentUser();
                user.put("name", mRealNameString);
                user.put("location", mLocationString);
                user.put("profileImage", profileImage);
                user.put("mAddInfo", mAddInfoString);
                user.put("captain", captain);
                user.put("crew", crew);
                user.put("firstMate", firstMate);
                user.put("secondMate", secondMate);
                user.put("deckHand", deckHand);
                user.put("captainExperienceInt", captainExperienceInt);
                user.put("crewExperienceInt", crewExperienceInt);
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Intent intent = new Intent(ProfileUpdate.this, ProfileDisplay.class);
                        startActivity(intent);
                    }
                });                                     //Below needs to be replaced with code that uses Parse.com
                //Should determine current user and then update the current user ParseUser object
                /*ed.putString("Name", nameString);
                ed.putString("Location", locationString);
                ed.putString("Experience", experienceString);
                ed.apply();*/

            }
        });
    }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView profilePic = (ImageView) findViewById(R.id.photo_profile);
            picBitmap = BitmapFactory.decodeFile(picturePath);
            int height = picBitmap.getHeight();
            int width = picBitmap.getWidth();

            if (height > 1280 && width > 960) {
                picBitmap = BitmapFactory.decodeFile(picturePath, options);
                profilePic.setImageBitmap(picBitmap);

                System.out.println("Need to resize");

            } else {
                profilePic.setImageBitmap(picBitmap);
                System.out.println("WORKS");
            }
            //Toast.makeText(this, imageFilePath.toString(), Toast.LENGTH_LONG).show();

            //boatPic.setImageBitmap(picBitmap);
            //boatPic.setVisibility(View.VISIBLE);


        }
    }


    public void viewGallery(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void cancelChanges(View v) {
        finish();
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        //Check which checkbox was clicked and make corresponding elements visible
        switch (view.getId()) {
            case R.id.CaptainCheckBox: {
                TextView textView1 = (TextView) findViewById(R.id.ExperienceYears);
                Spinner spinner1 = (Spinner) findViewById(R.id.experience_years_captain_spinner);
                if (checked) {
                    textView1.setVisibility(View.VISIBLE);
                    spinner1.setVisibility(View.VISIBLE);
                } else {
                    textView1.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);

                }
            }
            case R.id.CrewCheckBox: {
                TextView textView2 = (TextView) findViewById(R.id.ExperienceYearsCrew);
                Spinner spinner2 = (Spinner) findViewById(R.id.experience_years_crew_spinner);
                CheckBox firstMateBox = (CheckBox) findViewById(R.id.FirstMateCheckBox);
                CheckBox secondMateBox = (CheckBox) findViewById(R.id.SecondMateMateCheckBox);
                CheckBox deckHandBox = (CheckBox) findViewById(R.id.DeckHandCheckBox);

                if (checked) {
                    textView2.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.VISIBLE);
                    firstMateBox.setVisibility(View.VISIBLE);
                    secondMateBox.setVisibility(View.VISIBLE);
                    deckHandBox.setVisibility(View.VISIBLE);
                } else {
                    textView2.setVisibility(View.GONE);
                    spinner2.setVisibility(View.GONE);
                    firstMateBox.setVisibility(View.GONE);
                    secondMateBox.setVisibility(View.GONE);
                    deckHandBox.setVisibility(View.GONE);
                }
            }
        }

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.experience_years_captain_spinner) {
            captainExperienceInt = Integer.parseInt(parent.getItemAtPosition(pos).toString());
            Log.i("expYear", String.valueOf(captainExperienceInt));
        } else if (spinner.getId() == R.id.experience_years_crew_spinner) {
            crewExperienceInt = Integer.parseInt(parent.getItemAtPosition(pos).toString());
            Log.i("crewExp", String.valueOf(crewExperienceInt));
        }
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

