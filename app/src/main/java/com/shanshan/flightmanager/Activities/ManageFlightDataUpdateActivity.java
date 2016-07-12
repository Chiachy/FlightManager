package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.ManageFlightDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;

import java.util.List;

public class ManageFlightDataUpdateActivity extends Activity {

    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company_id";
    private static final String TAG_DAY = "day";
    private static final String TAG_TIMEBEGIN = "time_begin";
    private static final String TAG_TIMEEND = "time_end";
    private static final String TAG_WHEREFROM = "where_from";
    private static final String TAG_WHERETO = "where_to";
    private static final String TAG_TRANSCITY = "trans_city";

    private ManagerFlightDatas datas;
    private ManagerFlightDatas newDatas;
    private List<ManagerFlightDatas> mDatas;

    private Toolbar mToolBar;
    private EditText mCompanyNameET;
    private EditText mFlightNumberET;
    private EditText mDayET;
    private EditText mTimeBeginET;
    private EditText mTimeEndET;
    private EditText mWhereFromET;
    private EditText mWhereToET;
    private EditText mTranscityET;

    private ImageButton mClearCompanyIdBtn;
    private ImageButton mClearFlightIdBtn;
    private ImageButton mClearDayBtn;
    private ImageButton mClearTimeBeginBtn;
    private ImageButton mClearTimeEndBtn;
    private ImageButton mClearWhereFromBtn;
    private ImageButton mClearWhereToBtn;
    private ImageButton mClearTranscityBtn;
    private DataBaseModel db;
    private Button mOkayBtn;
    private Button mCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight_update);
        initViews();

        datas = ManageFlightDatasAdapter.mDatas.get(Integer.parseInt("1"));
        setTextContentsToViews(datas);

        mToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        clearButtonsAction();

        db = DataBaseModel.getInstance(ManageFlightDataUpdateActivity.this);
        mOkayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formerFlightID = getIntent().getStringExtra("FlightNumber");
                newDatas = new ManagerFlightDatas();
                newDatas.setId(mFlightNumberET.getText().toString());
                Log.i(TAG_ID, newDatas.getId());

                newDatas.setCompanyId(mCompanyNameET.getText().toString());
                Log.i(TAG_ID, newDatas.getCompanyId());

                newDatas.setTimeBegin(mTimeBeginET.getText().toString());
                Log.i(TAG_ID, newDatas.getTimeBegin());

                newDatas.setTimeEnd(mTimeEndET.getText().toString());
                Log.i(TAG_ID, newDatas.getTimeEnd());

                newDatas.setWhereFrom(mWhereFromET.getText().toString());
                Log.i(TAG_ID, newDatas.getWhereFrom());

                newDatas.setWhereTo(mWhereToET.getText().toString());
                Log.i(TAG_ID, newDatas.getWhereTo());

                newDatas.setDay(mDayET.getText().toString());
                Log.i(TAG_ID, newDatas.getDay());

                newDatas.setTransCity(mTranscityET.getText().toString());
                Log.i(TAG_ID, newDatas.getTransCity());

                db.updateFlightDatas(newDatas, formerFlightID);
                Toast.makeText(ManageFlightDataUpdateActivity.this, "航班信息已更新，重新登录后生效",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ManageFlightDataUpdateActivity.this, ManagerUIActivity.class));
                finish();
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
        mToolBar = (Toolbar) findViewById(R.id.manage_flight_update_toolbar);

        mFlightNumberET = (EditText) findViewById(R.id.manage_flight_update_number);
        mCompanyNameET = (EditText) findViewById(R.id.manage_flight_update_company_id);
        mTimeBeginET = (EditText) findViewById(R.id.manage_flight_update_time_begin);
        mTimeEndET = (EditText) findViewById(R.id.manage_flight_update_time_end);
        mWhereFromET = (EditText) findViewById(R.id.manage_flight_update_where_from);
        mWhereToET = (EditText) findViewById(R.id.manage_flight_update_where_to);
        mDayET = (EditText) findViewById(R.id.manage_flight_update_day);
        mTranscityET = (EditText) findViewById(R.id.manage_flight_update_transcity);

        mOkayBtn = (Button) findViewById(R.id.manage_flight_update_confirm_btn);
        mCancelBtn = (Button) findViewById(R.id.manage_flight_update_cancel_btn);
    }

    public void setTextContentsToViews(ManagerFlightDatas datas) {
        mFlightNumberET.setText(getIntent().getStringExtra("FlightNumber"));
        mCompanyNameET.setText(getIntent().getStringExtra("CompanyName"));
        mTimeBeginET.setText(getIntent().getStringExtra("TimeBegin"));
        mTimeEndET.setText(getIntent().getStringExtra("TimeEnd"));
        mWhereFromET.setText(getIntent().getStringExtra("WhereFrom"));
        mWhereToET.setText(getIntent().getStringExtra("WhereTo"));
        mDayET.setText(getIntent().getStringExtra("Day"));
        mTranscityET.setText(getIntent().getStringExtra("Transcity"));

        mClearCompanyIdBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_company_btn);
        mClearFlightIdBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_flight_number_btn);
        mClearDayBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_btn);
        mClearTimeBeginBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_timebegin);
        mClearTimeEndBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_timeend);
        mClearWhereFromBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_wherefrom);
        mClearWhereToBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_whereto);
        mClearTranscityBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_trancity);
    }

    public void clearButtonsAction() {

        //company
        mClearCompanyIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompanyNameET.setText("");
            }
        });

        //flightId
        mClearFlightIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlightNumberET.setText("");
            }
        });

        //day
        mClearDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDayET.setText("");
            }
        });

        //wherefrom
        mClearWhereFromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhereFromET.setText("");
            }
        });

        //whereto
        mClearWhereToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhereToET.setText("");
            }
        });

        //timebegin
        mClearTimeBeginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeBeginET.setText("");
            }
        });

        //timeend
        mClearTimeEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeEndET.setText("");
            }
        });

        //transcity
        mClearTranscityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTranscityET.setText("");
            }
        });
    }

}
