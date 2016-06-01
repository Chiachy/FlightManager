package com.shanshan.flightmanager.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.AdapterSearchResult;
import com.shanshan.flightmanager.Tools.ManagerFlightDatas;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shakugan on 16/4/5.
 */
public class FlightSearchResultActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView resultList;
    private AdapterSearchResult adapter;
    public static List<ManagerFlightDatas> datases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        mToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        resultList = (RecyclerView) findViewById(R.id.result_list);

        ToolsRecyclerViewDividerLine recycViewDividerLine = new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        recycViewDividerLine.setSize(15);
        recycViewDividerLine.setColor(0xFFDDDDDD);

        resultList.addItemDecoration(recycViewDividerLine);

        linearLayoutManager = new LinearLayoutManager(this);

        resultList.setLayoutManager(linearLayoutManager);

        adapter = new AdapterSearchResult(this,datases);
        resultList.setAdapter(adapter);

        if (datases.size() > 0) {
            TextView textView = (TextView) findViewById(R.id.result_text);
            textView.setVisibility(View.GONE);
        }
    }
}
