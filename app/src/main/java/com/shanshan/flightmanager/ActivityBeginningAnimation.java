package com.shanshan.flightmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

public class ActivityBeginningAnimation extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.beginning_animation, null);
        setContentView(view);

        //检查FlightDatas表是否存在
        FlightManagerDB mFlightDatabaseOpenHelper = FlightManagerDB
                .getInstance(ActivityBeginningAnimation.this);
        if(!mFlightDatabaseOpenHelper.checkDataBase()){ initFlightDatas4DB(); }

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(2000);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {  }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(ActivityBeginningAnimation.this, ActivityBooking.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {  }
        });

    }

    private void initFlightDatas4DB() {

        FlightManagerDB db = FlightManagerDB.getInstance(ActivityBeginningAnimation.this);

        FlightDatas first = new FlightDatas("四川航空", "3U8548" ,"成都", "上海", "8:06", "10:38",
                "南昌", "2016.04.11");
        db.saveFlightDatas(first);

        FlightDatas first21 = new FlightDatas("四川航空", "3U1548" ,"成都", "上海", "14:46", "15:56",
                "南昌", "2016.04.22");
        db.saveFlightDatas(first21);

        FlightDatas first2 = new FlightDatas("南方航空", "CZ9902", "深圳", "上海", "8:06", "10:38",
                "杭州", "2016.03.21");
        db.saveFlightDatas(first2);

        FlightDatas first22 = new FlightDatas("南方航空", "CZ9912", "深圳", "上海", "19:06", "22:08",
                "杭州", "2016.04.27");
        db.saveFlightDatas(first22);

        FlightDatas first3 = new FlightDatas("中国国航", "CA4194","北京", "九龙", "14:06", "15:58",
                "长沙","2016.03.38");
        db.saveFlightDatas(first3);

        FlightDatas first23 = new FlightDatas("中国国航", "CA1194","北京", "九龙", "4:06", "6:58",
                "长沙","2016.03.38");
        db.saveFlightDatas(first23);

        FlightDatas first4 = new FlightDatas("山东航空", "SC4194", "济南", "西藏", "8:06", "10:38",
                "兰州", "2016.03.31");
        db.saveFlightDatas(first4);

        FlightDatas first24 = new FlightDatas("山东航空", "SC4594", "济南", "西藏", "23:06", "1:38",
                "兰州", "2016.04.31");
        db.saveFlightDatas(first24);

        FlightDatas first5 = new FlightDatas("西藏航空" ,"TV6102", "拉萨", "上海", "10:06", "10:38",
                "太原", "2016.03.23");
        db.saveFlightDatas(first5);

        FlightDatas first25 = new FlightDatas("西藏航空" ,"TV6162", "拉萨", "上海", "1:06", "3:38",
                "太原", "2016.04.23");
        db.saveFlightDatas(first25);

        FlightDatas first6 = new FlightDatas("深圳航空", "ZH4194", "北京", "深圳", "8:06", "10:38",
                "广州", "2016.05.31");
        db.saveFlightDatas(first6);

        FlightDatas first26 = new FlightDatas("深圳航空", "ZH4594", "北京", "深圳", "19:06", "20:38",
                "广州", "2016.05.01");
        db.saveFlightDatas(first26);

        FlightDatas first7 = new FlightDatas("四川航空" , "3U8896","成都", "深圳", "8:06", "10:38",
                "重庆", "2016.04.16");
        db.saveFlightDatas(first7);

        FlightDatas first27 = new FlightDatas("四川航空" , "3U1896","成都", "深圳", "22:06", "23:38",
                "重庆", "2016.03.16");
        db.saveFlightDatas(first27);

        FlightDatas first8 = new FlightDatas("海南航空" , "HU7147", "北京", "海口", "8:06", "10:38",
                "南宁", "2016.03.03");
        db.saveFlightDatas(first8);

        FlightDatas first28 = new FlightDatas("海南航空" , "HU1141", "北京", "海口", "15:26", "16:38",
                "南宁", "2016.05.01");
        db.saveFlightDatas(first28);

        FlightDatas first9 = new FlightDatas("海南航空", "HU7141", "三亚", "上海", "8:06", "10:38",
                "厦门", "2016.03.30");
        db.saveFlightDatas(first9);

        FlightDatas first29 = new FlightDatas("海南航空", "HU0141", "三亚", "上海", "11:06", "13:38",
                "厦门", "2016.05.10");
        db.saveFlightDatas(first29);

        FlightDatas first10 = new FlightDatas("中国国航","CA1405", "拉萨", "重庆", "8:06", "10:38",
                "成都", "2016.04.18");
        db.saveFlightDatas(first10);

        FlightDatas first30 = new FlightDatas("中国国航","CA1235", "拉萨", "重庆", "18:06", "21:38",
                "成都", "2016.02.18");
        db.saveFlightDatas(first30);

        FlightDatas first11 = new FlightDatas("中国国航","CA1415","九龙", "上海", "10:06", "10:38",
                "厦门", "2016.01.21");
        db.saveFlightDatas(first11);

        FlightDatas first31 = new FlightDatas("中国国航","CA1415","九龙", "上海", "3:06", "4:08",
                "厦门", "2016.02.21");
        db.saveFlightDatas(first31);

        FlightDatas first12 = new FlightDatas("山东航空","SC1405","南昌", "济南", "8:06", "10:38",
                "合肥", "2016.02.19");
        db.saveFlightDatas(first12);

        FlightDatas first32 = new FlightDatas("山东航空","SC1445","南昌", "济南", "8:06", "10:38",
                "合肥", "2016.01.19");
        db.saveFlightDatas(first32);

        FlightDatas first13 = new FlightDatas("西藏航空","TV6111","拉萨", "上海", "8:06", "10:38",
                "西宁", "2016.06.08");
        db.saveFlightDatas(first13);

        FlightDatas first33 = new FlightDatas("西藏航空","TV7111","拉萨", "上海", "9:06", "10:38",
                "西宁", "2016.01.08");
        db.saveFlightDatas(first33);

        FlightDatas first14 = new FlightDatas("深圳航空","ZH1405","北京", "南京", "8:06", "10:38",
                "长沙", "2016.04.18");
        db.saveFlightDatas(first14);

        FlightDatas first34 = new FlightDatas("深圳航空","ZH1425","北京", "南京", "17:06", "19:38",
                "长沙", "2016.02.18");
        db.saveFlightDatas(first34);

        FlightDatas first15 = new FlightDatas("联合航空","KN5215","石家庄", "贵阳", "8:06", "10:38",
                "南昌", "2016.06.01");
        db.saveFlightDatas(first15);

        FlightDatas first35 = new FlightDatas("联合航空","KN5415","石家庄", "贵阳", "8:06", "10:38",
                "南昌", "2016.03.01");
        db.saveFlightDatas(first35);

        FlightDatas first16 = new FlightDatas("厦门航空","MF1836","武汉", "福州", "8:06", "10:38",
                "重庆", "2016.05.18");
        db.saveFlightDatas(first16);

        FlightDatas first17 = new FlightDatas("东方航空","MU3597","上海", "北京", "8:06", "10:38",
                "南昌", "2016.01.18");
        db.saveFlightDatas(first17);

        FlightDatas first18 = new FlightDatas("深圳航空","ZH1415","北京", "上海", "8:06", "10:38",
                "苏州", "2016.06.28");
        db.saveFlightDatas(first18);

        FlightDatas first19 = new FlightDatas("东方航空","3U8882","乌鲁木齐", "上海", "8:06", "10:38",
                "兰州", "2016.06.17");
        db.saveFlightDatas(first19);

        FlightDatas first20 = new FlightDatas("东方航空","MU3561","呼和浩特", "深圳", "8:06", "10:38",
                "武汉", "2016.02.18");
        db.saveFlightDatas(first20);

        Toast.makeText(ActivityBeginningAnimation.this, "数据库生成完毕" , Toast.LENGTH_LONG).show();
    }
}
