package com.shanshan.flightmanager.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import com.shanshan.flightmanager.Activities.ActivityBooking;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Context mContext;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Intent intent = new Intent(getActivity(), ActivityBooking.class);
        intent.putExtra("com.shanshan.flightmanager.Fragments.year", year);
        intent.putExtra("com.shanshan.flightmanager.Fragments.month", monthOfYear);
        intent.putExtra("com.shanshan.flightmanager.Fragments.day", dayOfMonth);
    }
}
