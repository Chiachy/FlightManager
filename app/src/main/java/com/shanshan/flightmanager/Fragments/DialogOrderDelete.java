package com.shanshan.flightmanager.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.R;

public class DialogOrderDelete extends Activity {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_order_delete);

        mEditText = (EditText) findViewById(R.id.order_number);

        mButton = (Button) findViewById(R.id.delete_confirm_button);
        mButton.setOnClickListener(deleteOnClickListener);

    }

    View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (DataBaseModel.getInstance(DialogOrderDelete.this).searchUser(mEditText.getText()
                    .toString()).getId() == null ){
                DataBaseModel.getInstance(DialogOrderDelete.this).deleteOrderData(mEditText
                        .getText().toString());
                Log.i("DataBaseModel", String.valueOf(DataBaseModel
                        .getInstance(DialogOrderDelete.this)
                        .deleteOrderData(mEditText.getText().toString())) );
                Toast.makeText(DialogOrderDelete.this, "该账单已删除", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DialogOrderDelete.this, "该账单不存在", Toast.LENGTH_LONG).show();
            }
        }
    };

}
