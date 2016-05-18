package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.shanshan.flightmanager.R;

import java.util.Calendar;

public class ActivityCanlander extends Activity {

    private DatePicker mDataPicker;
    private EditText mFromSpinner;
    private EditText mToSpinner;
    private Button mOkayBtn;
    private Button mCancelBtn;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_canlander);
        initViews();

        mDataPicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ActivityCanlander.this.mYear = year;
                ActivityCanlander.this.mMonth = monthOfYear;
                ActivityCanlander.this.mDay = dayOfMonth;

            }
        });

        mFromSpinner.setInputType(0x00000000);
        mToSpinner.setInputType(0x00000000);

        mOkayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityCanlander.this, ActivityBooking.class);
                intent.putExtra("year", mYear);
                intent.putExtra("month", mMonth);
                intent.putExtra("day", mDay);
                startActivity(intent);
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initViews() {
        mDataPicker = (DatePicker) findViewById(R.id.datePicker);
        mFromSpinner = (EditText) findViewById(R.id.id_from_spinner);
        mToSpinner = (EditText) findViewById(R.id.id_to_spinner);
        mOkayBtn = (Button) findViewById(R.id.id_calendar_okay_button);
        mCancelBtn = (Button) findViewById(R.id.id_calendar_cancel_button);

        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }


}
