package com.shanshan.flightmanager.Activities;

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
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Fragments.ChooseWhereFromFragment;
import com.shanshan.flightmanager.Fragments.DatePickerDialogFragment;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.FlightManagerApplication;

public class FlightChooseActivity extends AppCompatActivity {

    private final static String TAG = "FlightChooseActivity";
    private final static String TEXTVIEWNAME_WHEREFROM = "mWhereFrom";
    private final static String TEXTVIEWNAME_WHERETO = "mWhereTo";
    private int mYear = 0;
    private int mMonth = 0;
    private int mDayOfMonth = 0;

    private Toolbar mToolbar;
    private TextView mWhereFrom;
    private TextView mWhereto;
    private TextView mDisplayDayTv;
    private Button mChooseDayBtn;
    private Button mSearchBtn;
    private TextView mBroswingAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        InitViews();

        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mToolbar);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sign_in: {
                        final FlightManagerApplication application =
                                (FlightManagerApplication) getApplication();
                        if (!application.getIsLogin()) {
                            startActivity(new Intent(
                                    FlightChooseActivity.this, UserSignInActivity.class)
                            );
                        } else {
                            startActivity(new Intent(
                                    FlightChooseActivity.this, UserDetailsActivity.class)
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


        mDisplayDayTv.setText(DisplayText(mYear, mMonth, mDayOfMonth));

        /*Intent intent = getIntent();
        getDate(intent);*/

        mWhereFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWhereFromDialog("mWhereFrom", mWhereFrom);
            }
        });

        mWhereto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWhereFromDialog("mWhereTo", mWhereto);
            }
        });

        mChooseDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(mDisplayDayTv);
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(FlightChooseActivity.this ,
                        mWhereFrom.getText() + "至" + mWhereto.getText() + mDisplayDayTv.getText() ,
                        Toast.LENGTH_SHORT
                ).show();*/
                if (mWhereFrom.getText().equals("始发地")
                        || mWhereto.getText().equals("目的地")
                        || mDisplayDayTv.getText().equals("待选择")) {
                    Toast.makeText(FlightChooseActivity.this,
                            "请输入完整的航班信息，再进行搜索",
                            Toast.LENGTH_LONG).show();
                } else {
                    FlightSearchResultActivity.datases = DataBaseModel.getInstance(FlightChooseActivity.this)
                            .searchFlight(mWhereFrom.getText().toString(), mWhereto.getText().toString());
                    Intent intent = new Intent(FlightChooseActivity.this, FlightSearchResultActivity.class);
                    startActivity(intent);
                }
            }
        });

        mBroswingAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlightChooseActivity.this, FlightDatasBrowsingActivity.class));
            }
        });

        mBroswingAll.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //添加下划线
    }

    public void InitViews() {
        mWhereFrom = (TextView) findViewById(R.id.search_wherefrom);
        mWhereto = (TextView) findViewById(R.id.search_whereto);
        mToolbar = (Toolbar) findViewById(R.id.new_toolbar_view);
        mChooseDayBtn = (Button) findViewById(R.id.choose_day);
        mDisplayDayTv = (TextView) findViewById(R.id.day);
        mSearchBtn = (Button) findViewById(R.id.new_search_button);
        mBroswingAll = (TextView) findViewById(R.id.new_browsing_all_flight);
    }

    /*public void getDate(Intent intent) {
        mYear = intent.getIntExtra("year", 0);
        mMonth = intent.getIntExtra("month", 0);
        mDayOfMonth = intent.getIntExtra("day", 0);
    }*/

    public String DisplayText(int year, int month, int day) {
        String finalYear = null;
        String finalMonth = null;
        String finalDay = null;

        if (year != 0 && month != 0 && day != 0) {
            finalYear = String.valueOf(year);
            finalMonth = String.valueOf(month);
            finalDay = String.valueOf(day);
            return finalYear + "." + finalMonth + "." + finalDay;
        } else return "待选择";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public void showWhereFromDialog(String textviewname, TextView tv) {
        ChooseWhereFromFragment chooseWhereFromDialog = new ChooseWhereFromFragment(textviewname, tv);
        chooseWhereFromDialog.show(getSupportFragmentManager(), "ChooseWhereFromFragment");
    }

    public void showDatePicker(TextView tV){
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment(tV);
        datePickerDialogFragment.show(getSupportFragmentManager(), "atePickerDialogFragment");
    }

}
