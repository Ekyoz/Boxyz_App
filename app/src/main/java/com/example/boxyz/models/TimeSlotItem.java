package com.example.boxyz.models;

public class TimeSlotItem {

    private String day;
    private String hour;
    private String temp;
    private boolean status;

    public TimeSlotItem(String day, String hour, String temp, boolean status){
        this.day = day;
        this.hour = hour;
        this.temp = temp;
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getTemp() {
        return temp;
    }

    public boolean getStatus() {
        return status;
    }
}
