package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shanshan.flightmanager.R;

public class RecviewActivityFlightManaging extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview_activity_flight_managing);

        initViews();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_flight_managing);

    }

}
