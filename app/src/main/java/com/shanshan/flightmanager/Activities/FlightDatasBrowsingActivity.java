package com.shanshan.flightmanager.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.shanshan.flightmanager.Adapters.FlightBroswingAdapter;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.FlightManagerApplication;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;

/**
 * A Flight Browsing User interface
 * 航班信息浏览界面，可以进行预定航班和查询、更改航班信息的操作，并登陆个人账号
 * */
public class FlightDatasBrowsingActivity extends AppCompatActivity {

    //private FloatingActionButton flightBroChooseButton;
    private RecyclerView userRecyclerView;
    private FlightBroswingAdapter mAdapter;
    /*
     *  设置menu item的监听器
     */
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_sign_in: {

                    final FlightManagerApplication application = (FlightManagerApplication) getApplication();

                    if(!application.getIsLogin()){
                        startActivity(new Intent(
                                FlightDatasBrowsingActivity.this, UserSignInActivity.class)
                        );
                    }else{
                        startActivity(new Intent(
                                FlightDatasBrowsingActivity.this, UserDetailsActivity.class)
                        );
                    }
                    break;
                }
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_browsing);

        /**Toolbar 配置代码块*/
        Toolbar fbToolbar = (Toolbar) findViewById( R.id.user_details_toolbar);
        fbToolbar.setTitleTextColor( Color.parseColor( "#ffffff" ) );
        setActionBar(fbToolbar);

        fbToolbar.setOnMenuItemClickListener( onMenuItemClickListener );

        /** recycleView 配置代码块 */
        initViews();//初始化RecycleView

        mAdapter = new FlightBroswingAdapter(this, DataBaseModel.getInstance(this).loadFlightDatas());

        userRecyclerView.setAdapter(mAdapter);
        //设置re-View的布局，并调用
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL , false);
        userRecyclerView.setLayoutManager(linearLayoutManager);
        userRecyclerView.setHasFixedSize(true);//提高性能
        //设置分割线属性，并调用
        ToolsRecyclerViewDividerLine recycViewDividerLine = new ToolsRecyclerViewDividerLine(
                ToolsRecyclerViewDividerLine.HORIZONTAL);
        recycViewDividerLine.setSize(15);
        recycViewDividerLine.setColor(0xFFDDDDDD);
        userRecyclerView.addItemDecoration(recycViewDividerLine);
    }

    /**
     * 初始化View
     * */
    private void initViews() {
        userRecyclerView = (RecyclerView) findViewById(R.id.userlistView);
    }

    /*
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。
     * 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

}

