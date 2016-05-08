package com.shanshan.flightmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class ActivityBooking extends AppCompatActivity {

    private Toolbar  mToolbar;
    private TextView mWhereFrom;
    private TextView mWhereTo;
    private TextView mDay;
    private TextView mTimeBegin;
    private TextView mTimeEnd;
    private Button mChooseDay;
    private Button mChooseTimeBegin;
    private Button mChooseTimeEnd;
    private Button   mSearchButton;
    private TextView mBroswingAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_browsing_new);
        InitViews();

        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sign_in: {
                        final FlightSystemApplication application =
                                (FlightSystemApplication) getApplication();
                        if (!application.getIsLogin()) {
                            startActivity(new Intent(
                                    ActivityBooking.this, ActivityUserLogin.class)
                            );
                        } else {
                            startActivity(new Intent(
                                    ActivityBooking.this, ActivityUserDetails.class)
                            );
                        }
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });

        OnClicks();

        mBroswingAll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void InitViews() {
        mToolbar = (Toolbar) findViewById( R.id.new_toolbar_view );
        mWhereFrom = (TextView) findViewById(R.id.search_wherefrom);
        mWhereTo = (TextView) findViewById(R.id.search_whereto);
        mDay = (TextView) findViewById(R.id.day);
        mTimeBegin = (TextView) findViewById(R.id.time_begin);
        mTimeEnd = (TextView) findViewById(R.id.time_end);
        mChooseDay = (Button) findViewById(R.id.choose_day);
        mChooseTimeBegin = (Button) findViewById(R.id.choose_time_begin);
        mChooseTimeEnd = (Button) findViewById(R.id.choose_time_end);
        mSearchButton = (Button) findViewById(R.id.new_search_button);
        mBroswingAll = (TextView) findViewById(R.id.new_browsing_all_flight);
    }

    public void OnClicks() {
        mWhereFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mWhereTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mChooseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mChooseTimeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mChooseTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBroswingAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityBooking.this, ActivityFlightBrowsing.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }



}
