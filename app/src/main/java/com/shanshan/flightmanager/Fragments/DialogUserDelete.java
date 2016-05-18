package com.shanshan.flightmanager.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.R;

public class DialogUserDelete extends Activity {

    private EditText mUserdeleteEd;
    private Button mUserDeleteBtn;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_user_delete);

        mUserdeleteEd = (EditText) findViewById(R.id.dia_delete_user);
        mUserDeleteBtn = (Button) findViewById(R.id.user_delete_confirm_btn);
        mRecyclerView = (RecyclerView) findViewById(R.id.user_delete_recyclerview);

        mUserDeleteBtn.setOnClickListener(userDeleteOnCLiser);

        // TODO: 2016/4/18 remove null
        mRecyclerView.setAdapter(null);

    }

    View.OnClickListener userDeleteOnCLiser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (DataBaseModel.getInstance(DialogUserDelete.this).searchUser(mUserdeleteEd
                    .getText().toString()).getId() == null){
                DataBaseModel.getInstance(DialogUserDelete.this).deleteUserData(mUserdeleteEd
                        .getText().toString());
                Toast.makeText(DialogUserDelete.this, "账户已被删除", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(DialogUserDelete.this, "该账户不存在！！", Toast.LENGTH_LONG).show();
            }

        }
    };
}
