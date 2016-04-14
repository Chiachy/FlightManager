package com.shanshan.flightmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class ActivityManagerView extends FragmentActivity {

    private Toolbar mManagerViewToolbar ;
    FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        mManagerViewToolbar = (Toolbar) findViewById(R.id.manager_view_toolbar);
        mManagerViewToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        mManagerViewToolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
        setActionBar(mManagerViewToolbar);

        android.app.FragmentManager fm = getFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.manager_viewpager);
        pagerAdapter = new MyPagerAdapter( getSupportFragmentManager() );
        viewPager.setAdapter(pagerAdapter);
    }

    private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.manager_exit:
                    Log.i("menu", String.valueOf(mOnMenuItemClickListener));
                    startActivity( new Intent( ActivityManagerView.this, ActivityUserLogin.class) );
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


    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 3;


        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 :
                    return FragmentManagingFlight.newInstance(0 , "Page Managing Flight");
                case 1 :
                    return FragmentManagingUser.newInstance("1" , "Page Managing User");
                case 2 :
                    return FragmentManagingUser.newInstance("2" , "Page Managing Order");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        public CharSequence getPageTitle(int position){
            return "Page" + position;
        }
    }

}

