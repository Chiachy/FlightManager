package com.shanshan.flightmanager.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Fragments.ManagingFlightFragment;
import com.shanshan.flightmanager.Fragments.ManagingOrderFragment;
import com.shanshan.flightmanager.Fragments.FragmentManagingUser;
import com.shanshan.flightmanager.R;

public class ManagerUIActivity extends FragmentActivity {

    private Toolbar mManagerViewToolbar;
    private FragmentPagerAdapter mPagerAdapter;
    private PagerTabStrip mPagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        mManagerViewToolbar = (Toolbar) findViewById(R.id.manager_view_toolbar);
        mManagerViewToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setActionBar(mManagerViewToolbar);
        mManagerViewToolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);

        android.app.FragmentManager fm = getFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.manager_viewpager);
        mPagerAdapter = new MyPagerAdapter( getSupportFragmentManager() );

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.view_pager_header);
        mPagerTabStrip.setTabIndicatorColor(Color.parseColor("#0090ff"));
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    //Toolbar点击事件
    public Toolbar.OnMenuItemClickListener mOnMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.manager_exit: {
                    finish();
                    startActivity(new Intent(ManagerUIActivity.this, UserSignInActivity.class));
                    break;
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

    //Fragment适配器
    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 :
                    return ManagingFlightFragment.newInstance(0, "Page Managing Flight");
                case 1 :
                    return FragmentManagingUser.newInstance("1" , "Page Managing User");
                case 2 :
                    return ManagingOrderFragment.newInstance("2", "Page Managing Order");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0 :
                    return "航班管理";
                case 1 :
                    return "用户管理";
                case 2 :
                    return "订单管理";
            }
            return null;
        }
    }

}

