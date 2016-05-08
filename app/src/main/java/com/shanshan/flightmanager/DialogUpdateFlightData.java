package com.shanshan.flightmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogUpdateFlightData extends Activity {

    private EditText mUpFlightId;
    private EditText mUpCompanyId;
    private EditText mUpFlightWhereFrom;
    private EditText mUpFlightWhereTo;
    private EditText mUpTimeBegin;
    private EditText mUpEnd;
    private EditText mUpTrancitys;
    private EditText mUpDay;
    private Button mOkay;
    private Button mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_update_flight_data);

        mUpFlightId = (EditText) findViewById(R.id.upflight_id);
        mUpCompanyId = (EditText) findViewById(R.id.upcompany_id);
        mUpFlightWhereFrom = (EditText) findViewById(R.id.upflight_wherefrom);
        mUpFlightWhereTo = (EditText) findViewById(R.id.upflight_whereto);
        mUpTimeBegin = (EditText) findViewById(R.id.uptime_begin);
        mUpEnd = (EditText) findViewById(R.id.uptime_end);
        mUpTrancitys = (EditText) findViewById(R.id.uptranscity);
        mUpDay = (EditText) findViewById(R.id.upday);
        mOkay = (Button) findViewById(R.id.upcancel);
        mCancel = (Button) findViewById(R.id.upcancel);

        mOkay.setOnClickListener(okayOnClickListener);
        mCancel.setOnClickListener(cancelOnClickListener);

    }

    View.OnClickListener okayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (DataBaseModel.getInstance(DialogUpdateFlightData.this)
                    .searchFlight(mUpFlightId.getText().toString()) != null){
                if(!( mUpCompanyId.getText().toString().equals(null)
                        || mUpFlightWhereFrom.getText().toString().equals(null)
                        || mUpTimeBegin.getText().toString().equals(null)
                        || mUpFlightWhereTo.getText().toString().equals(null)
                        || mUpEnd.getText().toString().equals(null)
                        || mUpTrancitys.getText().toString().equals(null)
                        || mUpDay.getText().toString().equals(null)
                )){
                    ManagerFlightDatas managerFlightDatas = new ManagerFlightDatas();
                    Log.i("id",mUpFlightId.getText().toString() );

                  /*  managerFlightDatas.setCompanyId(mConpanyId.getText().toString());
                    managerFlightDatas.setWhereFrom(mWhereFrom.getText().toString());
                    managerFlightDatas.setWhereTo(mWhereTo.getText().toString());
                    managerFlightDatas.setTimeBegin(mTimeBegin.getText().toString());
                    managerFlightDatas.setTimeEnd(mTimeEnd.getText().toString());
                    managerFlightDatas.setDay(mFlightDay.getText().toString());*/
                    Toast.makeText(DialogUpdateFlightData.this, "输入成功！",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DialogUpdateFlightData.this,"请完整填写航班信息！",Toast.LENGTH_LONG)
                            .show();
                }
            }else{
                Toast.makeText(DialogUpdateFlightData.this,"该班次已存在！",Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}

