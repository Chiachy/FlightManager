package com.shanshan.flightmanager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ActivityManagerViewNew extends Activity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_new);
        initViews();
        setToolbar();


    }


    public void initViews(){
        toolbar = (Toolbar) findViewById(R.id.manager_view_new_toolbar);
    }

    private void setToolbar(){
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
    }

    //private void set

}
