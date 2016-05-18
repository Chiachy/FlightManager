package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.shanshan.flightmanager.Adapters.AdapterOrderManagering;
import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.R;

public class RecviewActivityOrderManaging extends Activity {

    private RecyclerView mRecyclerView;
    private AdapterOrderManagering mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview_activity_order_managing);

        mAdapter = new AdapterOrderManagering(this, DataBaseModel.getInstance(this).loadOrderDatas());

        mRecyclerView.setAdapter(mAdapter);

    }
}
