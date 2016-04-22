package com.shanshan.flightmanager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogOrderDelete extends Activity {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_order_delete);

        mEditText = (EditText) findViewById(R.id.order_number);

        mButton = (Button) findViewById(R.id.delete_confirm_button);
        mButton.setOnClickListener(deleteOnClickListener);

    }

    View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(FlightManagerDB.getInstance(DialogOrderDelete.this).searchUser(mEditText.getText()
                    .toString()).getId() == null ){
                FlightManagerDB.getInstance(DialogOrderDelete.this).deleteOrderData(mEditText
                        .getText().toString());
                Log.i("FlightManagerDB", String.valueOf(FlightManagerDB
                        .getInstance(DialogOrderDelete.this)
                        .deleteOrderData(mEditText.getText().toString())) );
                Toast.makeText(DialogOrderDelete.this, "该账单已删除", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DialogOrderDelete.this, "该账单不存在", Toast.LENGTH_LONG).show();
            }
        }
    };

}
