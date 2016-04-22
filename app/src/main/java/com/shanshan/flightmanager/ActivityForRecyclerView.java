package com.shanshan.flightmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ActivityForRecyclerView extends Activity {

    private RecyclerView mRecyclerView;
    private AdapterOrderManagering mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_for_recycler_view);

        mAdapter = new AdapterOrderManagering(this, FlightManagerDB.getInstance(this).loadOrderDatas());

        mRecyclerView.setAdapter(mAdapter);

    }
}
