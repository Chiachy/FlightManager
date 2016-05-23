package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.FlightBroswingAdapter;
import com.shanshan.flightmanager.Adapters.AdapterSearchResult;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.FlightManagerApplication;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;
import com.shanshan.flightmanager.Tools.ManagerOrderDatas;
import com.shanshan.flightmanager.R;

import java.util.List;

public class FlightDetailsActivity extends Activity {

    private Toolbar mToolBar;
    private TextView mConpanyName;
    private TextView mFlightNumber;
    private TextView mDay;
    private TextView mTimeBegin;
    private TextView mTimeEnd;
    private TextView mWhereFrom;
    private TextView mWhereTo;
    private TextView mTranscity;
    private Button mBookingBtn;
    private ManagerFlightDatas datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        initViews();

        mToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mToolBar);

        mBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((FlightManagerApplication) getApplication()).getIsLogin()) {
                    List<ManagerOrderDatas> managerOrderDatases =
                            DataBaseModel.getInstance(FlightDetailsActivity.this)
                                    .searchOrderDatas(UserDetailsActivity.id);

                    for (ManagerOrderDatas managerOrderDatas : managerOrderDatases) {
                        if (managerOrderDatas.getFlightNumber().equals(datas.getId())) {
                            Toast.makeText(FlightDetailsActivity.this,
                                    "您已经预定过本次航班", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }

                    DataBaseModel.getInstance(FlightDetailsActivity.this)
                            .saveOrderData(new ManagerOrderDatas(
                                    UserDetailsActivity.id, datas.getId(), 100
                            ));
                    Toast.makeText(FlightDetailsActivity.this, "下单成功", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(FlightDetailsActivity.this, "请先登陆", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FlightDetailsActivity.this, UserSignInActivity.class);
                    startActivity(intent);
                }
            }
        });

        if (getIntent().getBooleanExtra("isSearchResult",false)) {
            datas = AdapterSearchResult.mDatas.get(getIntent().getIntExtra("id",0));
        } else {
            datas = FlightBroswingAdapter.mDatas.get(getIntent().getIntExtra("id", 0));
        }

        setTextsToViews(datas);
    }

    public void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.details_toolbar);
        mConpanyName = (TextView) findViewById(R.id.flight_company_id);
        mFlightNumber = (TextView) findViewById(R.id.flight_number);
        mDay = (TextView) findViewById(R.id.day);
        mTimeBegin = (TextView) findViewById(R.id.flight_time_begin);
        mTimeEnd = (TextView) findViewById(R.id.flight_time_end);
        mWhereFrom = (TextView) findViewById(R.id.flight_where_from);
        mWhereTo = (TextView) findViewById(R.id.flight_where_to);
        mTranscity = (TextView) findViewById(R.id.transcity);
        mBookingBtn = (Button) findViewById(R.id.booking_button);
    }

    public void setTextsToViews(ManagerFlightDatas datas) {
        mConpanyName.setText(datas.getCompanyId());
        mFlightNumber.setText(datas.getId());
        mDay.setText(datas.getDay());
        mTimeBegin.setText(datas.getTimeBegin());
        mTimeEnd.setText(datas.getTimeEnd());
        mWhereFrom.setText(datas.getWhereFrom());
        mWhereTo.setText(datas.getWhereTo());
        mTranscity.setText(datas.getTransCity());
    }
}
