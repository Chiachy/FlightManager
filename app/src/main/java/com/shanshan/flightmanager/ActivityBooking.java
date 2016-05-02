package com.shanshan.flightmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class ActivityBooking extends Activity {

    private Toolbar  mToolbar;
    private EditText mWhereFrom;
    private EditText mWhereTo;
    private TextView mDay;
    private TextView mTimeBegin;
    private TextView mTimeEnd;
    private TextView mFlightNumber;
    private Button   mSearchButton;
    private TextView mBroswingAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_browsing_new);

        initViews();

        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mToolbar);

        mBroswingAll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void initViews(){
        mBroswingAll = (TextView) findViewById(R.id.new_browsing_all_flight);
        mToolbar = (Toolbar) findViewById( R.id.new_toolbar_view );
/*        mWhereFrom = (EditText) findViewById(R.id.new_where_from);
        mWhereTo = (EditText) findViewById(R.id.new_where_to);
        mDay = (TextView) findViewById(R.id.new_day);
        mTimeBegin = (TextView) findViewById(R.id.new_search_button);
        mTimeEnd = (TextView) findViewById(R.id.new_time_end);
        mFlightNumber = (TextView) findViewById(R.id.new_flight_number);*/
        mSearchButton = (Button) findViewById(R.id.new_search_button);
    }

    public void NewButtonClick (View view){
        startActivity(new Intent(ActivityBooking.this, ActivityFlightBrowsing.class));
        Toast.makeText(ActivityBooking.this, "!!!!!", Toast.LENGTH_LONG);
    }


}
