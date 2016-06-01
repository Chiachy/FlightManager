package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.shanshan.flightmanager.R;

public class ManagerUserDetailActivity extends Activity {

    private EditText mUserNameET;
    private EditText mUserIdET;
    private EditText mUserAgeET;

    private ImageButton mClearAccountImBtn;
    private ImageButton mClearNameImBtn;
    private ImageButton mClearAgeImBtn;
    private Button mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_detail);

        initViews();


        mClearAccountImBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanText(mUserIdET);
            }
        });

    }


    public void initViews() {
        mUserNameET = (EditText) findViewById(R.id.manager_user_name);
        mUserIdET = (EditText) findViewById(R.id.manager_user_id);
        mUserAgeET = (EditText) findViewById(R.id.manager_user_age);
        mClearAccountImBtn = (ImageButton) findViewById(R.id.manager_user_clear_account);
        mClearNameImBtn = (ImageButton) findViewById(R.id.manager_user_clear_name);
        mClearAgeImBtn = (ImageButton) findViewById(R.id.manager_user_clear_age);
        mConfirmBtn = (Button) findViewById(R.id.confirm_user_update_button);
    }

    public void cleanText(EditText editText) {
        editText.setText("");
    }

}
