package com.shanshan.flightmanager.Tools;


/**
 * Created by Shakugan on 16/4/3.
 */
public class ManagerUserDatas {

    private String id;
    private String password;
    private String name;
    private String sex;
    private String age;
    private int balance;

    public ManagerUserDatas() {
    }

    public ManagerUserDatas(String id, String password, String name, String sex, String age) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
