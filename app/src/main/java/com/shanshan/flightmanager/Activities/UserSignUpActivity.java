package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ManagerUserDatas;

public class UserSignUpActivity extends Activity {

    private static final String TAG = "ManagerUserDatas";

    private EditText userId;
    private EditText userPassword;
    private EditText passwordConfirm;
    private Button signUp;
    private EditText userAge;
    private RadioGroup mRadioGroup;
    private EditText userName;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userId = (EditText) findViewById(R.id.user_add_account);
        userPassword = (EditText) findViewById(R.id.editText2);
        passwordConfirm = (EditText) findViewById(R.id.editText3);
        userAge = (EditText) findViewById(R.id.editText4);
        userName = (EditText) findViewById(R.id.editText21);
        signUp = (Button) findViewById(R.id.sign_up_button);

        userAge.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        mToolbar = (Toolbar) findViewById(R.id.sign_up_toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        mRadioGroup = (RadioGroup) findViewById(R.id.radio);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataBaseModel.getInstance(UserSignUpActivity.this)
                        .searchUser(userId.getText().toString()).getId() == null) {

                    if (userAge.getText().toString().length() == 18) {
                        if (userPassword.getText().toString().length() >= 6) {
                            if (userPassword.getText().toString().equals(passwordConfirm.getText().toString())) {

                                ManagerUserDatas managerUserDatas = new ManagerUserDatas();

                                managerUserDatas.setId(userId.getText().toString());
                                managerUserDatas.setPassword(userPassword.getText().toString());
                                managerUserDatas.setSex(mRadioGroup.getCheckedRadioButtonId()
                                        == R.id.male ? "男" : "女");
                                managerUserDatas.setName(userName.getText().toString());
                                managerUserDatas.setAge(userAge.getText().toString());

                                DataBaseModel.getInstance(UserSignUpActivity.this).saveUser(managerUserDatas);

                                Toast.makeText(UserSignUpActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                finish();

                            } else {
                                Toast.makeText(UserSignUpActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(UserSignUpActivity.this, "密码长度应至少六位", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(UserSignUpActivity.this, "请输入正确的身份证号", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UserSignUpActivity.this, "此用户已被注册", Toast.LENGTH_LONG).show();
                }
            }
                                  }

        );


    }
}
