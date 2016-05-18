package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.ToolClassies.FlightManagerApplication;
import com.shanshan.flightmanager.ToolClassies.ManagerUserDatas;

public class ActivityUserLogin extends Activity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private static String MANAGER_ACCOUNT = "manager";

    private static String MANAGER_PASSWORD = "manager";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Toolbar mUserLoginToolbar;
    private TextView mSignUpTextView;
    private ProgressDialog dialog;
    private ImageButton mClearAccountButton;
    private ImageButton mClearPasswordButton;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        initViews();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    dialog = new ProgressDialog(ActivityUserLogin.this);
                    dialog.setProgressStyle(R.attr.progressBarStyle);
                    dialog.setMessage("加载中...");
                    dialog.setIndeterminate(true);              //设置进度条是否为不明确
                    dialog.setCancelable(false);                //设置进度条是否可以按退回键取消
                    dialog.setCanceledOnTouchOutside(false);    //设置点击进度对话框外的区域对话框不消失
                    dialog.show();
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mUserLoginToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mUserLoginToolbar);

        mSignUpTextView.setPaintFlags(mSignUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mSignUpTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityUserLogin.this, ActivitySignUp.class));
            }
        });

        mClearAccountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailView.setText("");
            }
        });

        mClearPasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordView.setText("");
            }
        });
    }

    public void initViews() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mUserLoginToolbar = (Toolbar) findViewById(R.id.user_login_toolbar);
        mSignUpTextView = (TextView) findViewById(R.id.sign_up);
        mClearAccountButton = (ImageButton) findViewById(R.id.clear_account_btn);
        mClearPasswordButton = (ImageButton) findViewById(R.id.clear_password_btn);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (email.equals(MANAGER_ACCOUNT) && password.equals(MANAGER_PASSWORD)){
            startActivity(new Intent(ActivityUserLogin.this, ActivityManagerView.class));
            Toast.makeText(this, "管理员登陆成功", Toast.LENGTH_LONG);
            finish();
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void showProgress(final boolean show) {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private ManagerUserDatas managerUserDatas;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            managerUserDatas = DataBaseModel.getInstance(ActivityUserLogin.this).searchUser(mEmail);
            if (managerUserDatas.getId() == null) {
                return false;
            } else if (managerUserDatas.getPassword().equals(mPassword)) {
                ActivityUserDetails.id = mEmail;
                return true;
            }
            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                finish();
                Toast.makeText(ActivityUserLogin.this, "登入成功", Toast.LENGTH_LONG ).show();
                final FlightManagerApplication application = (FlightManagerApplication) getApplication();
                application.setIsLogin(true);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
            dialog.dismiss();
        }
    }
}

