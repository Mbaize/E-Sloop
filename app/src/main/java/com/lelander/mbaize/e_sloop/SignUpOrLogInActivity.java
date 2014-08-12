package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity which displays a registration screen to the user.
 */
public class SignUpOrLogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_log_in);

        // Log in button click handler
        ((Button) findViewById(R.id.logInButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent of the log in activity
                startActivity(new Intent(SignUpOrLogInActivity.this, LoginParse.class));
            }
        });

        // Sign up button click handler
        ((Button) findViewById(R.id.signUpButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(SignUpOrLogInActivity.this, SignUpActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up_or_login, menu);
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
