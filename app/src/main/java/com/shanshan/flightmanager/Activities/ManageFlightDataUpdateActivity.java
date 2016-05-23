package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.ManageFlightDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;

import java.util.List;

public class ManageFlightDataUpdateActivity extends Activity {

    private ManagerFlightDatas datas;
    private List<ManagerFlightDatas> mDatas;

    private Toolbar mToolBar;
    private EditText mCompanyNameTV;
    private EditText mFlightNumberTV;
    private EditText mDayTV;
    private EditText mTimeBeginTV;
    private EditText mTimeEndTV;
    private EditText mWhereFromTV;
    private EditText mWhereToTV;
    private EditText mTranscityTV;

    private ImageButton mClearCompanyIdBtn;
    private ImageButton mClearFlightIdBtn;
    private ImageButton mClearDayBtn;
    private ImageButton mClearTimeBeginBtn;
    private ImageButton mClearTimeEndBtn;
    private ImageButton mClearWhereFromBtn;
    private ImageButton mClearWhereToBtn;
    private ImageButton mClearTranscityBtn;

    private Button mOkayBtn;
    private Button mCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight_update);

        initViews();
        //datas =
        datas = ManageFlightDatasAdapter.mDatas.get(getIntent().getIntExtra("id", 0));

        setTextsToViews(datas);

        ClearButtonsAction();

        mOkayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mToolBar
                mCompanyNameTV
                        mTimeBeginTV =
                        mTimeEndTV = (
                                mWhereFromTV =
                                        mWhereToTV = (
                                                mFlightNumberT
                mDayTV = (Text
                mTranscityTV =*/

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
        mCompanyNameTV = (EditText) findViewById(R.id.manage_flight_update_company_id);
        mTimeBeginTV = (EditText) findViewById(R.id.manage_flight_update_time_begin);
        mTimeEndTV = (EditText) findViewById(R.id.manage_flight_update_time_end);
        mWhereFromTV = (EditText) findViewById(R.id.manage_flight_update_where_from);
        mWhereToTV = (EditText) findViewById(R.id.manage_flight_update_where_to);
        mFlightNumberTV = (EditText) findViewById(R.id.manage_flight_update_number);
        mDayTV = (EditText) findViewById(R.id.manage_flight_update_day);
        mTranscityTV = (EditText) findViewById(R.id.manage_flight_update_transcity);

        mOkayBtn = (Button) findViewById(R.id.manage_flight_update_confirm_btn);
        mCancelBtn = (Button) findViewById(R.id.manage_flight_update_cancel_btn);
    }

    public void setTextsToViews(ManagerFlightDatas datas) {
        mCompanyNameTV.setText(datas.getCompanyId());
        mFlightNumberTV.setText(datas.getId());
        mDayTV.setText(datas.getDay());
        mTimeBeginTV.setText(datas.getTimeBegin());
        mTimeEndTV.setText(datas.getTimeEnd());
        mWhereFromTV.setText(datas.getWhereFrom());
        mWhereToTV.setText(datas.getWhereTo());
        mTranscityTV.setText(datas.getTransCity());

        mClearCompanyIdBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_company_btn);
        mClearFlightIdBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_flight_number_btn);
        mClearDayBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_btn);
        mClearTimeBeginBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_timebegin);
        mClearTimeEndBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_timeend);
        mClearWhereFromBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_wherefrom);
        mClearWhereToBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_whereto);
        mClearTranscityBtn = (ImageButton) findViewById(R.id.manage_flight_update_clear_trancity);
    }

    public void ClearButtonsAction() {

        //company
        mClearCompanyIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompanyNameTV.setText("");
            }
        });

        //flightId
        mClearFlightIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlightNumberTV.setText("");
            }
        });

        //day
        mClearDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDayTV.setText("");
            }
        });

        //wherefrom
        mClearWhereFromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhereFromTV.setText("");
            }
        });

        //whereto
        mClearWhereToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhereToTV.setText("");
            }
        });

        //timebegin
        mClearTimeBeginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeBeginTV.setText("");
            }
        });

        //timeend
        mClearTimeEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeEndTV.setText("");
            }
        });

        //transcity
        mClearTranscityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTranscityTV.setText("");
            }
        });
    }

}
