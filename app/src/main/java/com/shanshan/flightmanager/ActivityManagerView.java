package com.shanshan.flightmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class ActivityManagerView extends Activity {

    private Toolbar mManagerViewToolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        mManagerViewToolbar = (Toolbar) findViewById(R.id.manager_view_toolbar);
        mManagerViewToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        mManagerViewToolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
        setActionBar(mManagerViewToolbar);
    }

    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
                

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.manager_exit : {
                    Toast.makeText(ActivityManagerView.this, "clicked", Toast.LENGTH_LONG ).show();
                    startActivity(new Intent(ActivityManagerView.this, ActivityUserLogin.class));
                }
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manager_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
