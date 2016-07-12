package com.shanshan.flightmanager.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView mDisplayDate;
    private String mDate;

    private int mYear;
    private int mMonth;
    private int mDay;

    public DatePickerDialogFragment(TextView textView) {
        this.mDisplayDate = textView;
    }

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
        monthOfYear += 1;
        mDate = year + "." + monthOfYear + "." + dayOfMonth;
        mDisplayDate.setText(mDate);
    }
}
