package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.ManageFlightDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;

import java.util.ArrayList;
import java.util.List;

public class ManageFlightDisplayDetailActivity extends Activity {

    private Toolbar mToolBar;
    private TextView mConpanyNameTV;
    private TextView mFlightNumberTV;
    private TextView mDayTV;
    private TextView mTimeBeginTV;
    private TextView mTimeEndTV;
    private TextView mWhereFromTV;
    private TextView mWhereToTV;
    private TextView mTranscityTV;
    private Button mUpdateBtn;
    private Button mDeleteBtn;
    private ManagerFlightDatas datas;
    public static List<ManagerFlightDatas> mDatas = new ArrayList<>();

    /*public ManageFlightDisplayDetailActivity( List<ManagerFlightDatas> mDatas) {
        this.mDatas = mDatas;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight_displaydetail);

        initViews();

        mToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mToolBar);

        datas = ManageFlightDatasAdapter.mDatas.get(getIntent().getIntExtra("id", 0));

        setTextsToViews(datas);

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageFlightDisplayDetailActivity.this,
                        ManageFlightDataUpdateActivity.class);
                intent.putExtra("flight_id", mFlightNumberTV.getId());
                startActivity(intent);
            }
        });

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showComfirmAlert(datas);
            }
        });

    }

    public void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.manage_details_toolbar);
        mConpanyNameTV = (TextView) findViewById(R.id.manage_flight_company_id);
        mTimeBeginTV = (TextView) findViewById(R.id.manage_flight_time_begin);
        mTimeEndTV = (TextView) findViewById(R.id.manage_flight_time_end);
        mWhereFromTV = (TextView) findViewById(R.id.manage_flight_where_from);
        mWhereToTV = (TextView) findViewById(R.id.manage_flight_where_to);
        mFlightNumberTV = (TextView) findViewById(R.id.manage_flight_number);
        mDayTV = (TextView) findViewById(R.id.manage_flight_day);
        mTranscityTV = (TextView) findViewById(R.id.manage_flight_transcity);
        mUpdateBtn = (Button) findViewById(R.id.manage_flight_update_btn);
        mDeleteBtn = (Button) findViewById(R.id.manage_flight_delete_btn);
    }

    public void setTextsToViews(ManagerFlightDatas datas) {
        mConpanyNameTV.setText(datas.getCompanyId());
        mFlightNumberTV.setText(datas.getId());
        mDayTV.setText(datas.getDay());
        mTimeBeginTV.setText(datas.getTimeBegin());
        mTimeEndTV.setText(datas.getTimeEnd());
        mWhereFromTV.setText(datas.getWhereFrom());
        mWhereToTV.setText(datas.getWhereTo());
        mTranscityTV.setText(datas.getTransCity());
    }

    public void showComfirmAlert(ManagerFlightDatas datas) {
        ConfirmDeleteDialogFragment fg = new ConfirmDeleteDialogFragment(datas);
        fg.show(getFragmentManager(), "showComfirmAlert");
    }

    public class ConfirmDeleteDialogFragment extends DialogFragment {

        ManagerFlightDatas mdatas;

        public ConfirmDeleteDialogFragment(ManagerFlightDatas datas) {
            this.mdatas = datas;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.ConfirmDeleteDialogFragmentTitle)
                    .setPositiveButton(R.string.ConfirmOkay, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DataBaseModel abm =
                                    DataBaseModel.getInstance(ManageFlightDisplayDetailActivity.this);
                            abm.deleteFlightData(mdatas.getId());
                            finish();
                            //sendBroadcast(new Intent());
                            startActivity(new Intent(
                                    ManageFlightDisplayDetailActivity.this, ManagerUIActivity.class
                            ));

                            ManageFlightDisplayDetailActivity manageFlightDisplayDetailActivity =
                                    new ManageFlightDisplayDetailActivity();
                            manageFlightDisplayDetailActivity.finish();
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

}
