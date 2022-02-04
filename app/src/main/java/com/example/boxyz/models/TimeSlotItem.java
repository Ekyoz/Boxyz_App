package com.example.boxyz.models;

public class TimeSlotItem {

    private String temp;
    private String name;
    private boolean status;


    public TimeSlotItem(String temp, String name){
        this.temp = temp;
        this.name = name;
        this.status = status;
    }

    public String getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }
}
