package com.shanshan.flightmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogFlightDelete extends Activity {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_flight_delete);

        mEditText = (EditText) findViewById(R.id.delete_flight_ed);
        mButton = (Button) findViewById(R.id.delete_flight_confirm);

        mButton.setOnClickListener(deleteFlightOnClickListener);
    }

    View.OnClickListener deleteFlightOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (DataBaseModel.getInstance(DialogFlightDelete.this).searchUser(mEditText.getText()
                    .toString()).getId() == null ){
                DataBaseModel.getInstance(DialogFlightDelete.this).deleteFlightData(mEditText
                        .getText().toString());
                Log.i("DataBaseModel", String.valueOf(DataBaseModel
                        .getInstance(DialogFlightDelete.this)
                        .deleteFlightData(mEditText.getText().toString())) );
                Toast.makeText( DialogFlightDelete.this, "该班次已删除", Toast.LENGTH_LONG ).show();
            }else{
                Toast.makeText( DialogFlightDelete.this, "该班次不存在", Toast.LENGTH_LONG ).show();
            }
        }
    };

}
