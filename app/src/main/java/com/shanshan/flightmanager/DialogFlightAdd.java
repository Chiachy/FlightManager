package com.shanshan.flightmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogFlightAdd extends Activity {

    private EditText mFlightId;
    private EditText mConpanyId;
    private EditText mWhereFrom;
    private EditText mWhereTo;
    private EditText mTimeBegin;
    private EditText mTimeEnd;
    private EditText mTranscity;
    private EditText mFlightDay;
    private Button mCancel;
    private Button mOkay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_flight_add);

        mFlightId = (EditText) findViewById(R.id.maflight_id);
        mConpanyId = (EditText) findViewById(R.id.macompany_id);
        mWhereFrom = (EditText) findViewById(R.id.maflight_wherefrom);
        mWhereTo = (EditText) findViewById(R.id.maflight_whereto);
        mTimeBegin = (EditText) findViewById(R.id.matime_begin);
        mTimeEnd = (EditText) findViewById(R.id.matime_end);
        mTranscity = (EditText) findViewById(R.id.matranscity);
        mFlightDay = (EditText) findViewById(R.id.maday);
        mCancel = (Button) findViewById(R.id.cancel);
        mOkay = (Button) findViewById(R.id.okay);

        mOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FlightManagerDB.getInstance(DialogFlightAdd.this)
                        .searchFlight(mFlightId.getText().toString()) != null){
                    if(!( mConpanyId.getText().toString().equals(null)
                            || mWhereFrom.getText().toString().equals(null)
                            || mTimeBegin.getText().toString().equals(null)
                            || mWhereTo.getText().toString().equals(null)
                            || mTimeEnd.getText().toString().equals(null)
                            || mTranscity.getText().toString().equals(null)
                            || mFlightDay.getText().toString().equals(null)
                            || mCancel.getText().toString().equals(null)
                            || mOkay.getText().toString().equals(null)
                    )){
                        FlightDatas flightDatas = new FlightDatas();
                        Log.i("id",mConpanyId.getText().toString() );

                        flightDatas.setId(mFlightId.getText().toString());
                        flightDatas.setCompanyId(mConpanyId.getText().toString());
                        flightDatas.setWhereFrom(mWhereFrom.getText().toString());
                        flightDatas.setWhereTo(mWhereTo.getText().toString());
                        flightDatas.setTimeBegin(mTimeBegin.getText().toString());
                        flightDatas.setTimeEnd(mTimeEnd.getText().toString());
                        flightDatas.setDay(mFlightDay.getText().toString());
                        Toast.makeText(DialogFlightAdd.this, "输入成功！",Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(DialogFlightAdd.this,"请完整填写航班信息！",Toast.LENGTH_LONG)
                                .show();
                    }
                }else{
                    Toast.makeText(DialogFlightAdd.this,"该班次已存在！",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

/*if(FlightManagerDB.getInstance(DialogFlightAdd.this)
                        .searchFlight(mFlightId.getText().toString()) == null){
                    if(!( mConpanyId.getText().toString().equals(null)
                            || mWhereFrom.getText().toString().equals(null)
                            || mTimeBegin.getText().toString().equals(null)
                            || mWhereTo.getText().toString().equals(null)
                            || mTimeEnd.getText().toString().equals(null)
                            || mTranscity.getText().toString().equals(null)
                            || mFlightDay.getText().toString().equals(null)
                            || mCancel.getText().toString().equals(null)
                            || mOkay.getText().toString().equals(null)
                    )){
                        FlightDatas flightDatas = new FlightDatas();

                        flightDatas.setId(mFlightId.getText().toString());
                        flightDatas.setCompanyId(mConpanyId.getText().toString());
                        flightDatas.setWhereFrom(mWhereFrom.getText().toString());
                        flightDatas.setWhereTo(mWhereTo.getText().toString());
                        flightDatas.setTimeBegin(mTimeBegin.getText().toString());
                        flightDatas.setTimeEnd(mTimeEnd.getText().toString());
                        flightDatas.setDay(mFlightDay.getText().toString());
                        Toast.makeText(DialogFlightAdd.this, "输入成功！",Toast.LENGTH_LONG);
                        finish();
                    }else{
                        Toast.makeText(DialogFlightAdd.this,"请完整填写航班信息！",Toast.LENGTH_LONG);
                    }
                }else{
                    Toast.makeText(DialogFlightAdd.this,"该班次已存在！",Toast.LENGTH_LONG);
                }*/
}
