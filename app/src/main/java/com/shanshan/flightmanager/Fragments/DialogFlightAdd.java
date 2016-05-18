package com.shanshan.flightmanager.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.ToolClassies.ManagerFlightDatas;
import com.shanshan.flightmanager.R;

import java.util.List;

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
    private List<ManagerFlightDatas> mAFlightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_flight_add);

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
                String rowId = mFlightId.getText().toString();
                mAFlightData = DataBaseModel.getInstance(DialogFlightAdd.this)
                        .searchFlight(rowId);

                // TODO: 2016/4/20 判断是否存在该班次的语句
                    if(mConpanyId.getText().toString().length() == 0
                            || mWhereFrom.getText().toString().length() == 0
                            || mWhereTo.getText().toString().length() == 0
                            || mTimeBegin.getText().toString().length() == 0
                            || mTimeEnd.getText().toString().length() == 0
                            || mTranscity.getText().toString().length() == 0
                            || mFlightDay.getText().toString().length() == 0
                    ){
                        Toast.makeText(DialogFlightAdd.this,"请输入完整的航班信息",Toast.LENGTH_LONG);
                    }else{
                        String Id = new String(mFlightId.getText().toString());
                        String cId = new String(mConpanyId.getText().toString());
                        String wf = new String(mWhereFrom.getText().toString());
                        String wt = new String(mWhereTo.getText().toString());
                        String tb = new String(mTimeBegin.getText().toString());
                        String te = new String(mTimeEnd.getText().toString());
                        String tran = new String(mTranscity.getText().toString());
                        String d = new String(mFlightDay.getText().toString());

                        ManagerFlightDatas managerFlightDatas = new ManagerFlightDatas(cId, Id, wf, wt, tb, te, tran, d);

                        DataBaseModel.getInstance(DialogFlightAdd.this).saveFlightDatas(managerFlightDatas);

                        Toast.makeText(DialogFlightAdd.this, "储存航班信息成功！", Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

