package com.shanshan.flightmanager.Tools;

/**
 * Created by shanshan on 2016/4/4.
 */
public class ManagerOrderDatas {

    private int id;
    private String userId;
    private String flight_number;
    private int price;

    public ManagerOrderDatas() {

    }

    public ManagerOrderDatas(String userId, String flight_number, int price) {
        this.userId = userId;
        this.flight_number = flight_number;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flight_number;
    }

    public void setFlightNumber(String flight_number) {
        this.flight_number = flight_number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
