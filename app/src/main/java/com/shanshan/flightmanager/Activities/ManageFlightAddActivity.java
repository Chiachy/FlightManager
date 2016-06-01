package com.shanshan.flightmanager.Activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;

import java.util.List;

public class ManageFlightAddActivity extends AppCompatActivity {

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
    private List<ManagerFlightDatas> mFlightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight_add);

        initViews();

        mOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmAddDialog();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initViews() {
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
    }

    private void showConfirmAddDialog() {
        ConfirmAddDialogFragment confirmAddDialogFragment = new ConfirmAddDialogFragment();
        confirmAddDialogFragment.show(getFragmentManager(), "ConfirmAddDialogFragment");
    }

    public class ConfirmAddDialogFragment extends DialogFragment {

        /*public ConfirmAddDialogFragment(ManagerFlightDatas datas){
            this.mdatas = datas;
        }*/

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.ConfirmAddDialogFragmentTitle)
                    .setPositiveButton(R.string.ConfirmOkay, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String rowId = mFlightId.getText().toString();
                            mFlightData = DataBaseModel.getInstance(ManageFlightAddActivity.this)
                                    .searchFlight(mWhereFrom.getText().toString(), mWhereTo.getText().toString(),
                                            mFlightDay.getText().toString());

                            // TODO: 2016/4/20 判断是否存在该班次的语句 | 页面刷新
                            if (mConpanyId.getText().toString().length() == 0
                                    || mWhereFrom.getText().toString().length() == 0
                                    || mWhereTo.getText().toString().length() == 0
                                    || mTimeBegin.getText().toString().length() == 0
                                    || mTimeEnd.getText().toString().length() == 0
                                    || mTranscity.getText().toString().length() == 0
                                    || mFlightDay.getText().toString().length() == 0
                                    ) {
                                Toast.makeText(getActivity(), "请输入完整的航班信息",
                                        Toast.LENGTH_LONG).show();
                            } else {

                                String Id = new String(mFlightId.getText().toString());
                                String cId = new String(mConpanyId.getText().toString());
                                String wf = new String(mWhereFrom.getText().toString());
                                String wt = new String(mWhereTo.getText().toString());
                                String tb = new String(mTimeBegin.getText().toString());
                                String te = new String(mTimeEnd.getText().toString());
                                String tran = new String(mTranscity.getText().toString());
                                String d = new String(mFlightDay.getText().toString());

                                ManagerFlightDatas managerFlightDatas = new ManagerFlightDatas(
                                        cId, Id, wf, wt, tb, te, tran, d);

                                DataBaseModel.getInstance(ManageFlightAddActivity.this)
                                        .saveFlightDatas(managerFlightDatas);

                                Toast.makeText(ManageFlightAddActivity.this, "储存航班信息成功！",
                                        Toast.LENGTH_LONG).show();

                                finish();
                            }
                        }
                    })
                    .setNegativeButton(R.string.ConfirmCancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return builder.create();
        }
    }

    View.OnClickListener CleanTextContent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}

