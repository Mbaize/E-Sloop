package com.lelander.mbaize.e_sloop;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Micah on 8/4/2014.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    DateSetListener mCallback;

    // Container Activity must implement this interface
    public interface DateSetListener {
        public void setTextViewDate(int year, int month, int day);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DateSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DateSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        // Send the event to the host activity
        mCallback.setTextViewDate(year, month, day);



        Toast.makeText(getActivity(), "Date selected", Toast.LENGTH_LONG).show();


    }

   /* public void showDatePickerDialog(View v, int buttonSelected) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getFragmentManager(), "datePicker");
        //newFragment.buttonSelected = buttonSelected;
    }*/
}