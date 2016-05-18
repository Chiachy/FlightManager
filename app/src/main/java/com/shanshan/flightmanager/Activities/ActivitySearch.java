package com.shanshan.flightmanager.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.R;

/**
 * Created by Shakugan on 16/4/5.
 */
public class ActivitySearch extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText searchEdit;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        searchBtn = (Button) findViewById(R.id.search_btn);
        mToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String row = searchEdit.getText().toString();
                if (row.equals("")||row.contains(" ")) {
                    Toast.makeText(ActivitySearch.this,"请输入合法关键字",Toast.LENGTH_LONG).show();
                } else {
                    ActivitySearchResult.datases = DataBaseModel.getInstance(ActivitySearch.this)
                            .searchFlight(searchEdit.getText().toString());
                    Intent intent = new Intent(ActivitySearch.this, ActivitySearchResult.class);
                    startActivity(intent);
                }
            }
        });
    }
}
