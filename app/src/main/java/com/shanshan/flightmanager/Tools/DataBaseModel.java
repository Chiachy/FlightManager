package com.shanshan.flightmanager.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshan on 2016/3/16.
 *
 * 数据库接口
 */
public class DataBaseModel {

    public static final String DB_NAME = "flight_manager" ; //数据库名

    public static final int VERSION = 1 ; //数据库版本

    private static DataBaseModel dataBaseModel;

    private SQLiteDatabase db;

    /** 将构造方法私有化*/
    private DataBaseModel(Context context) {
        DatabaseOpenHelper dbHelper = new DatabaseOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /** 获取FlightManagerDB的实例*/
    public synchronized static DataBaseModel getInstance(Context context) {
        if (dataBaseModel == null) {
            dataBaseModel = new DataBaseModel(context);
        }
        return dataBaseModel;
    }


    /**
     * 将FlightDatas实例存储到数据库
     * @param managerFlightDatas
     */
    public void saveFlightDatas(ManagerFlightDatas managerFlightDatas) {
        if (managerFlightDatas != null) {
            ContentValues values = new ContentValues();
            values.put("company_id", managerFlightDatas.getCompanyId());
            values.put("id", managerFlightDatas.getId());
            values.put("where_from", managerFlightDatas.getWhereFrom());        //始发地
            values.put("where_to", managerFlightDatas.getWhereTo());            //降落地
            values.put("time_begin", managerFlightDatas.getTimeBegin());        //起飞时间
            values.put("time_end", managerFlightDatas.getTimeEnd());            //降落时间
            values.put("trans_city", managerFlightDatas.getTransCity());        //中转城市
            values.put("day", managerFlightDatas.getDay());                     //航班飞行日
            values.put("isForigen", managerFlightDatas.getIsForigen());           //国内外
            db.insert("ManagerFlightDatas", null, values);
        }
    }

    /**
     * 从数据库读取所有航班信息
     * @return 航班信息表
     */
    public List<ManagerFlightDatas> loadFlightDatas() {
        List<ManagerFlightDatas> list = new ArrayList<ManagerFlightDatas>();
        Cursor cursor = db.query("ManagerFlightDatas", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ManagerFlightDatas managerFlightDatas = new ManagerFlightDatas();
                managerFlightDatas.setId(cursor.getString(cursor.getColumnIndex("id")));
                managerFlightDatas.setCompanyId(cursor.getString(cursor.getColumnIndex("company_id")));
                managerFlightDatas.setWhereFrom(cursor.getString(cursor.getColumnIndex("where_from")));
                managerFlightDatas.setWhereTo(cursor.getString(cursor.getColumnIndex("where_to")));
                managerFlightDatas.setTimeBegin(cursor.getString(cursor.getColumnIndex("time_begin")));
                managerFlightDatas.setTimeEnd(cursor.getString(cursor.getColumnIndex("time_end")));
                managerFlightDatas.setTransCity(cursor.getString(cursor.getColumnIndex("trans_city")));
                managerFlightDatas.setDay(cursor.getString(cursor.getColumnIndex("day")));
                managerFlightDatas.setIsForigen(cursor.getString(cursor.getColumnIndex("isForigen")));
                list.add(managerFlightDatas);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 搜索航班信息
     * @param row 搜索关键字
     * @return  返回搜索结果列表
     */
    public List<ManagerFlightDatas> searchFlight(String row) {
        List<ManagerFlightDatas> list = new ArrayList<>();
        Cursor cursor = null;
        String sql = "select * from ManagerFlightDatas where id=? or where_from=? or where_to=?";
        String[] args = {row};
        cursor = db.rawQuery(sql,args);
        if (cursor.moveToFirst()) {
            do {
                ManagerFlightDatas managerFlightDatas = new ManagerFlightDatas();
                managerFlightDatas.setId(cursor.getString(cursor.getColumnIndex("id")));
                managerFlightDatas.setCompanyId(cursor.getString(cursor.getColumnIndex("company_id")));
                managerFlightDatas.setWhereFrom(cursor.getString(cursor.getColumnIndex("where_from")));
                managerFlightDatas.setWhereTo(cursor.getString(cursor.getColumnIndex("where_to")));
                managerFlightDatas.setTimeBegin(cursor.getString(cursor.getColumnIndex("time_begin")));
                managerFlightDatas.setTimeEnd(cursor.getString(cursor.getColumnIndex("time_end")));
                managerFlightDatas.setTransCity(cursor.getString(cursor.getColumnIndex("trans_city")));
                managerFlightDatas.setDay(cursor.getString(cursor.getColumnIndex("day")));
                managerFlightDatas.setDay(cursor.getString(cursor.getColumnIndex("isForigen")));
                list.add(managerFlightDatas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    //public void updateFlightData()

    /**
     * 删除航班数据
     * @param id
     * @return
     */
    public int deleteFlightData(String id) {
        String[] ids = {id};
        return db.delete("ManagerFlightDatas", "id=?", ids);
    }

    /**
     * 订单数据
     * @param userId
     * @return
     */
    public List<ManagerOrderDatas> searchOrderDatas(String userId) {
        List<ManagerOrderDatas> list = new ArrayList<>();
        Cursor cursor = null;
        String sql = "select * from ManagerOrderDatas where user_id=?";
        String[] args = {userId};
        cursor = db.rawQuery(sql, args);
        if (cursor.moveToFirst()){
            do {
                ManagerOrderDatas managerOrderDatas = new ManagerOrderDatas();
                managerOrderDatas.setId(cursor.getInt(cursor.getColumnIndex("id")));
                managerOrderDatas.setFlightNumber(cursor.getString(cursor.getColumnIndex("flight_number")));
                managerOrderDatas.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                managerOrderDatas.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
                list.add(managerOrderDatas);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 加载所有订单信息
     * @return
     */
    public List<ManagerOrderDatas> loadOrderDatas() {
        List<ManagerOrderDatas> list = new ArrayList<ManagerOrderDatas>();
        Cursor cursor = db.query("ManagerOrderDatas", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ManagerOrderDatas managerOrderDatas = new ManagerOrderDatas();

                managerOrderDatas.setId(cursor.getInt(cursor.getColumnIndex("id")));
                managerOrderDatas.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                managerOrderDatas.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
                managerOrderDatas.setFlightNumber(cursor.getString(cursor.getColumnIndex("flight_number")));
                list.add(managerOrderDatas);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 保存订单信息
     * @param managerOrderDatas 订单数据对象
     */
    public void saveOrderData(ManagerOrderDatas managerOrderDatas) {
        if (managerOrderDatas != null) {
            ContentValues values = new ContentValues();
//            values.put("id",managerOrderDatas.getId());
            values.put("price", managerOrderDatas.getPrice());
            values.put("user_id", managerOrderDatas.getUserId());
            values.put("flight_number", managerOrderDatas.getFlightNumber());
            db.insert("ManagerOrderDatas", null, values);
        }
    }

    /**
     * 删除订单信息
     * @param id 订单id
     * @return  删除结果
     */
    public int deleteOrderData(String id) {
        String[] ids = {id};
        return db.delete("ManagerOrderDatas", "id=?", ids);
    }

    /**
     * 查找用户信息
     * @param id 需要查找的用户的id
     * @return  返回查找结果
     */

    public ManagerUserDatas searchUser(String id) {
        ManagerUserDatas managerUserDatas = new ManagerUserDatas();
        Cursor c = db.rawQuery("select * from ManagerUserDatas where id=?", new String[]{id});
        if(c.moveToFirst()) {
            managerUserDatas.setId(id);
            managerUserDatas.setAge(c.getString(c.getColumnIndex("age")));
            managerUserDatas.setName(c.getString(c.getColumnIndex("name")));
            managerUserDatas.setPassword(c.getString(c.getColumnIndex("password")));
            managerUserDatas.setBalance(c.getInt(c.getColumnIndex("balance")));
            managerUserDatas.setSex(c.getString(c.getColumnIndex("sex")));
        }
        c.close();
        return managerUserDatas;
    }

    /**
     * 保存用户信息
     * @param managerUserDatas
     */
    public void saveUser(ManagerUserDatas managerUserDatas) {
        if (managerUserDatas != null) {
            ContentValues values = new ContentValues();
            values.put("id", managerUserDatas.getId());
            values.put("age", managerUserDatas.getAge());
            values.put("sex", managerUserDatas.getSex());
            values.put("password", managerUserDatas.getPassword());
            values.put("name", managerUserDatas.getName());
            //values.put("balance",managerUserDatas.getBalance());
            db.insert("ManagerUserDatas", null, values);
        }
    }

    /**
     * @return 返回用户信息表
     */
    public List<ManagerUserDatas> loadUserDatas() {
        List<ManagerUserDatas> list = new ArrayList<ManagerUserDatas>();
        Cursor cursor = db.query("ManagerUserDatas", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ManagerUserDatas managerUserDatas = new ManagerUserDatas();
                managerUserDatas.setId(cursor.getString(cursor.getColumnIndex("id")));
                managerUserDatas.setId(cursor.getString(cursor.getColumnIndex("password")));
                managerUserDatas.setId(cursor.getString(cursor.getColumnIndex("name")));
                managerUserDatas.setId(cursor.getString(cursor.getColumnIndex("sex")));
                managerUserDatas.setId(cursor.getString(cursor.getColumnIndex("age")));
                list.add(managerUserDatas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public int deleteUserData(String id) {
        String[] ids = {id};
        return db.delete("ManagerUserDatas", "id=?", ids);
    }

    /**
     * 检查数据库表是否存在
     * @return true：数据库存在
     */
    public boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = "/data/data/com.shanshan.flightmanager/" + "ManagerFlightDatas";
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }catch(SQLiteException e){
            //database does't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

}
